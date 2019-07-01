package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.PaisesDto;
import com.luxlivesback.model.Paises;
import com.luxlivesback.service.PaisesService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class PaisesFunction {
	
	private static final Logger log = LoggerFactory.getLogger(PaisesFunction.class);
	
	@Autowired
	private PaisesService paisesService;
	
	public PaisesDto converteEntityToDto(Paises paises) {
		
		log.info("Convertendo Entidade para Dto: {}", paises.toString());
		PaisesDto dto = new PaisesDto();
		dto.setId(paises.getId());
		dto.setNome(paises.getNome());
		return dto;
	}
	
	public Paises convertDtoToPaises(PaisesDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());
		Paises paises = new Paises();
		
		if (OptionalV2.isPresent(dto.getId()))
			paises.setId(dto.getId());
		
		paises.setNome(OptionalV2.formatString(dto.getNome()));
		
		return paises;
	}
	
	public void validaCad(PaisesDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());
		
		if (!OptionalV2.isPresent(dto.getNome()))
			result.addError(new ObjectError("paises", "Campo nome não pode ser vazio!"));
		else
			paisesService.findByNomeIgnoreCase(dto.getNome())
				.ifPresent(paises -> result.addError(new ObjectError("paises", "Pais já cadastrada no id: " + paises.getId())));
	}
	
	public void validaUpdate(Long id, PaisesDto dto, BindingResult result) {
		
		log.info("Validando update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Paises> paises = paisesService.findById(id);
				if (paises.isPresent()) {
					paisesService.findByNomeIgnoreCase(dto.getNome()).ifPresent(pais -> {
						if (!OptionalV2.compareStrings(pais.getNome(), paises.get().getNome()))
							result.addError(new ObjectError("paises", "Pais " + pais.getNome() + " já cadastrado no id: " + pais.getId()));
					});
				} else {
					result.addError(new ObjectError("paises", "Pais de Id: " + id + " não existe!"));
				}				
			} else {
				result.addError(new ObjectError("paises", "Id da url ou do Corpo json não podem ser diferentes."));
			}
		} else {
			result.addError(new ObjectError("paises", "Id da url ou do Corpo json não podem ser nulos."));
		}
	}
}
