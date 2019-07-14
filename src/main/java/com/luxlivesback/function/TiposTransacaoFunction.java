package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.TiposTransacaoDto;
import com.luxlivesback.model.TiposTransacao;
import com.luxlivesback.service.TiposTransacaoService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class TiposTransacaoFunction {
	
	private static final Logger log = LoggerFactory.getLogger(TiposTransacaoFunction.class);
	
	@Autowired
	private TiposTransacaoService tiposTransacaoService;
	
	public TiposTransacaoDto convertEntityToDto(TiposTransacao tiposTransacao) {
		
		log.info("Convertendo Entidade para Dto: {}", tiposTransacao.toString());
		TiposTransacaoDto dto = new TiposTransacaoDto();
		dto.setId(tiposTransacao.getId());
		dto.setNome(tiposTransacao.getNome());
		return dto;
	}
	
	public TiposTransacao convertDtoToEntity(TiposTransacaoDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());
		TiposTransacao tiposTransacao = new TiposTransacao();
		
		if (OptionalV2.isPresent(dto.getId()))
			tiposTransacao.setId(dto.getId());
		
		tiposTransacao.setNome(dto.getNome());
		
		return tiposTransacao;
	}
	
	public void validaCad(TiposTransacaoDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());
		
		if (!OptionalV2.isPresent(dto.getNome()))
			result.addError(new ObjectError("tiposTransacao", "Campo nome não pode ser vazio!"));
		else
			tiposTransacaoService.findByNomeIgnoreCase(dto.getNome())
				.ifPresent(tiposTransacao -> result.addError(new ObjectError("tiposTransacao", "TipoTransacao já cadastrado no id: " + tiposTransacao.getId())));		
	}
	
	public void validaUpdate(Long id, TiposTransacaoDto dto, BindingResult result) {
		
		log.info("Validando update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<TiposTransacao> tiposTransacao = tiposTransacaoService.findById(id);
				if (tiposTransacao.isPresent()) {
					tiposTransacaoService.findByNomeIgnoreCase(dto.getNome()).ifPresent(tipoTransacao -> {
						if (!OptionalV2.compareStrings(tipoTransacao.getNome(), tiposTransacao.get().getNome()))
							result.addError(new ObjectError("tiposTransacao", "TipoTransacao " + tipoTransacao.getNome() + " já cadastrado no id: " + tipoTransacao.getId()));
					});
				} else {
					result.addError(new ObjectError("tiposTransacao", "TipoTransacao de Id: " + id + " não existe!"));
				}
			} else {
				result.addError(new ObjectError("tiposTransacao", "Id da url ou do Corpo json não podem ser diferentes."));
			}			
		} else {
			result.addError(new ObjectError("tiposTransacao", "Id da url ou do Corpo json não podem ser nulos."));
		}
	}
}
