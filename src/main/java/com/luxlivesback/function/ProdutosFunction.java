package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.ProdutosDto;
import com.luxlivesback.model.Produtos;
import com.luxlivesback.service.ProdutosService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class ProdutosFunction {
	
	private static final Logger log = LoggerFactory.getLogger(ProdutosFunction.class);
	
	@Autowired
	private ProdutosService produtosService;
	
	public ProdutosDto convertEntityToDto(Produtos produtos) {
		
		log.info("Convertendo Entidade para Dto: {}", produtos.toString());
		ProdutosDto dto = new ProdutosDto();
		dto.setId(produtos.getId());
		dto.setNome(produtos.getNome());
		dto.setPrecoReais(produtos.getPrecoReais());
		dto.setQtdTokens(produtos.getQtdTokens());
		dto.setStatus(produtos.getStatus());
		dto.setDataCad(produtos.getDataCad());
		dto.setDataAlt(produtos.getDataAlt());
		return dto;
	}
	
	public Produtos convertDtoToEntity(ProdutosDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());
		Produtos produtos = new Produtos();
		
		if (OptionalV2.isPresent(dto.getId())) {
			produtos.setId(dto.getId());
			produtos.setStatus(dto.getStatus());
			produtos.setDataCad(dto.getDataCad());
			produtos.setDataAlt(OptionalV2.dataAtual());
		} else {
			produtos.setStatus(true);
		}		
		
		produtos.setNome(dto.getNome());
		produtos.setPrecoReais(dto.getPrecoReais());
		produtos.setQtdTokens(dto.getQtdTokens());		
		
		return produtos;
	}
	
	public void validaCad(ProdutosDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());		
		
		produtosService.findByNomeIgnoreCase(dto.getNome())
			.ifPresent(produtos -> result.addError(new ObjectError("produtos", "Produto já cadastrado no id: " + produtos.getId())));
	}
	
	public void validaUpdate(Long id, ProdutosDto dto, BindingResult result) {
		
		log.info("Validando update, ud: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Produtos> produtos = produtosService.findById(id);
				if (produtos.isPresent()) {
					produtosService.findByNomeIgnoreCase(dto.getNome()).ifPresent(produto -> {
						if (!OptionalV2.compareStrings(produtos.get().getNome(), produto.getNome()))
							result.addError(new ObjectError("produtos", "Produto " + produto.getNome() + " já cadastrado no id: " + produto.getId()));
					});
				} else {
					result.addError(new ObjectError("produtos", "Produto de Id: " + id + " não existe!"));
				}				
			} else {
				result.addError(new ObjectError("produtos", "Id da url ou do Corpo json não podem ser diferentes."));
			}
		} else {
			result.addError(new ObjectError("produtos", "Id da url ou do Corpo json não podem ser nulos."));
		}
	}
}
