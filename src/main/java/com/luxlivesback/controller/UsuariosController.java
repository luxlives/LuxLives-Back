package com.luxlivesback.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxlivesback.dto.UsuariosDto;
import com.luxlivesback.function.UsuariosFunction;
import com.luxlivesback.model.Usuarios;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.security.dto.PasswordDto;
import com.luxlivesback.service.UsuariosService;
import com.luxlivesback.utils.PasswordUtils;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("${origem-permitida}")
public class UsuariosController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuariosController.class);	
	
	/**
	 * Essa propriedade irá controlar a quantidade de objetos por páginas em uma consulta JPQL;
	 * 
	 * paginacao.qtd_por_pagina -> propriedade criada em application.properties, assim
	 * todos os métodos que receberem essa propriedade, terão esse valor alterado em 
	 * application.properties.
	 * 
	 * @Value -> tranfere o valor da interpolação ${} para a propriedade qtdPorPagina
	 * criado abaixo dessa anotação.
	 * */
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private UsuariosFunction usuariosFunction;
	
	@GetMapping("/pag/{pag}")
	public ResponseEntity<ResponseList<UsuariosDto>> findAllOrderByNomeWithPag(@PathVariable int pag) {
		
		log.info("Buscar lista de usuários, pag: {}", pag);		
		ResponseList<UsuariosDto> response = new ResponseList<UsuariosDto>();
		List<Usuarios> usuarios = usuariosService.findAllByPage(PageRequest.of(pag, qtdPorPagina)).get();
		List<UsuariosDto> dto = new ArrayList<UsuariosDto>();
		usuarios.forEach(x -> dto.add(usuariosFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/nome/{nome}/pag/{pag}")
	public ResponseEntity<ResponseList<UsuariosDto>> teste(@PathVariable String nome, @PathVariable int pag) {
		
		log.info("Buscar lista de usuários pelo nome: {}, pag: {}", nome, pag);		
		ResponseList<UsuariosDto> response = new ResponseList<UsuariosDto>();
		Page<Usuarios> usuarios = usuariosService.findAllByNomeStartingWithIgnoreCaseOrderByNome(nome ,PageRequest.of(pag, qtdPorPagina)).get();
		List<UsuariosDto> dto = new ArrayList<UsuariosDto>();
		usuarios.forEach(x -> dto.add(usuariosFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<UsuariosDto>> findById(@PathVariable Long id) {
		
		log.info("Buscar usuário pelo id: {}", id);		
		Response<UsuariosDto> response = new Response<UsuariosDto>();
		Optional<Usuarios> usuario = usuariosService.findById(id);
		
		if (!usuario.isPresent()) {
			response.getErrors().add("Usuario não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		response.setData(usuariosFunction.convertEntityToDto(usuario.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/cadastro")
	public ResponseEntity<Response<UsuariosDto>> save(@Valid @RequestBody UsuariosDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo usuarios: {}", dto.toString());
		Response<UsuariosDto> response = new Response<UsuariosDto>();
		
		usuariosFunction.validCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Usuarios usuario = usuariosFunction.convertDtoToEntity(dto);
		usuario = usuariosService.save(usuario);
		response.setData(usuariosFunction.convertEntityToDto(usuario));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<UsuariosDto>> update(
			@PathVariable Long id, @Valid @RequestBody UsuariosDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update usuarios: {}", dto.toString());
		Response<UsuariosDto> response = new Response<UsuariosDto>();
		
		usuariosFunction.validUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Usuarios usuario = usuariosFunction.convertDtoToEntityToUpdate(dto);
		usuario = usuariosService.save(usuario);
		response.setData(usuariosFunction.convertEntityToDto(usuario));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/Password/{id}")
	public ResponseEntity<Response<Boolean>> updatePassword(
			@PathVariable Long id, @Valid @RequestBody PasswordDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update Password usuarios: {}", dto.toString());
		Response<Boolean> response = new Response<Boolean>();
		
		usuariosFunction.validUpdatePassword(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Usuarios usuarios = usuariosService.findById(id).get();
		usuarios.setPassword(PasswordUtils.gerarBCrypt(dto.getNewPassword()));
		
		try {
			usuariosService.save(usuarios);
		} catch (Exception e) {
			result.addError(new ObjectError("usuarios", "Erro ao atualizar a senha!"));
			return ResponseEntity.badRequest().body(response);	
		}		
		
		response.setData(true);
		return ResponseEntity.ok(response);
	}

}
