package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.ComprasExtratoDto;
import com.luxlivesback.model.ComprasExtrato;
import com.luxlivesback.service.ComprasExtratoService;
import com.luxlivesback.service.ProdutosService;
import com.luxlivesback.service.TiposTransacaoService;
import com.luxlivesback.service.UsuariosService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class ComprasExtratoFunction {
	
	private static final Logger log = LoggerFactory.getLogger(ComprasExtratoFunction.class);
	
	@Autowired
	private ComprasExtratoService comprasExtratoService;
	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private ProdutosService produtosService;
	
	@Autowired
	private TiposTransacaoService tiposTransacaoService;
	
	public ComprasExtratoDto convertEntityToDto(ComprasExtrato comprasExtrato) {
		
		log.info("Convertendo Entidade para Dto: {}", comprasExtrato.toString());
		ComprasExtratoDto dto = new ComprasExtratoDto();
		dto.setId(comprasExtrato.getId());
		dto.setValor(comprasExtrato.getValor());
		dto.setEntrada(comprasExtrato.getEntrada());
		dto.setDataTransacao(comprasExtrato.getDataTransacao());		
		dto.setUsuariosId(comprasExtrato.getUsuarios().getId());
		dto.setProdutosId(comprasExtrato.getProdutos().getId());
		dto.setTipoTransacaoId(comprasExtrato.getTipoTransacao().getId());
		return dto;		
	}
	
	public ComprasExtrato convertDtoToEntity(ComprasExtratoDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());		
		ComprasExtrato comprasExtrato = new ComprasExtrato();
		
		if (OptionalV2.isPresent(dto.getId()))
			comprasExtrato.setId(dto.getId());
		
		comprasExtrato.setValor(dto.getValor());
		comprasExtrato.setEntrada(dto.getEntrada());
		
		usuariosService.findById(dto.getUsuariosId()).ifPresent(x -> comprasExtrato.setUsuarios(x));
		produtosService.findById(dto.getProdutosId()).ifPresent(x -> comprasExtrato.setProdutos(x));
		tiposTransacaoService.findById(dto.getTipoTransacaoId()).ifPresent(x -> comprasExtrato.setTipoTransacao(x));
		
		return comprasExtrato;
	}
	
	public void validaUpdate(Long id, ComprasExtratoDto dto, BindingResult result) {
		
		log.info("Validando update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<ComprasExtrato> comprasExtrato = comprasExtratoService.findById(dto.getId());
				if (comprasExtrato.isPresent()) {
					this.validaUsuarioAndProdutoAndTipoTransacao(dto.getUsuariosId(), dto.getProdutosId(), dto.getTipoTransacaoId(), result);
				} else {
					result.addError(new ObjectError("comprasExtrato", "comprasExtrato_Id: " + dto.getId() + " não existe."));
				}					
			} else {
				result.addError(new ObjectError("comprasExtrato", "Id da url ou do Corpo json não podem ser diferentes."));
			}			
		} else {
			result.addError(new ObjectError("comprasExtrato", "Id da url ou do Corpo json não podem ser nulos."));
		}
	}
	
	public void validaUsuarioAndProdutoAndTipoTransacao(Long user_id, Long produto_id, Long tipoTransacao_id, BindingResult result) {
		
		log.info("Validando user_id: {}, produto_id: {} e tipoTranascao_id: {}", user_id, produto_id, tipoTransacao_id);
		
		if (!usuariosService.findById(user_id).isPresent())
			result.addError(new ObjectError("comprasExtrato", "Usuario_id: " + user_id + " não existe."));
		
		if (!produtosService.findById(produto_id).isPresent())
			result.addError(new ObjectError("comprasExtrato", "Produto_id: " + produto_id + " não existe."));
		
		if (!tiposTransacaoService.findById(tipoTransacao_id).isPresent())
			result.addError(new ObjectError("comprasExtrato", "TipoTransacao_id: " + tipoTransacao_id + " não existe."));
	}	
	
	public String validaUsuarioAndProdutoOrTipoTransacao(Long user_id, String coluna, Long coluna_id) {
		
		log.info("Validando, user_id: {}, {}_id: {}", user_id, coluna, coluna_id);		
		
		String erro = "";
		
		if (!usuariosService.findById(user_id).isPresent())
			erro = erro.concat("Usuario_id: " + user_id + " não existe. ");
		
		switch(coluna) {
			case "produto": if (!produtosService.findById(coluna_id).isPresent())
				erro = erro.concat("Produto_id: " + coluna_id + " não existe."); break;
			case "tipoTransacao": if (!tiposTransacaoService.findById(coluna_id).isPresent())
				erro = erro.concat("TipoTransacao_id: " + coluna_id + " não existe."); break;
		}		
		System.out.println("_________" + erro);
		return erro;		
	}

}
