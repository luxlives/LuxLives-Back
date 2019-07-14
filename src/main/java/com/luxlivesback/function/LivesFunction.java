package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.LivesDto;
import com.luxlivesback.model.Lives;
import com.luxlivesback.service.LivesService;
import com.luxlivesback.service.UsuariosService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class LivesFunction {
	
	private static final Logger log = LoggerFactory.getLogger(LivesFunction.class);
	
	@Autowired
	private LivesService livesService;
	
	@Autowired
	private UsuariosService usuariosService;
	
	public LivesDto convertEntityToDto(Lives lives) {
		
		log.info("Convertendo Entidade para Dto: {}", lives.toString());
		LivesDto dto = new LivesDto();
		dto.setId(lives.getId());
		dto.setUsuarioId(lives.getUsuarios().getId());
		dto.setTitulo(lives.getTitulo());
		dto.setGenero(lives.getGenero());
		dto.setDataIni(lives.getDataIni());
		dto.setDataFim(lives.getDataFim());
		return dto;
	}
	
	public Lives convertDtoToEntity(LivesDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());		
		Lives lives = new Lives();
		
		if (OptionalV2.isPresent(dto.getId()))
			lives.setId(dto.getId());
		
		usuariosService.findById(dto.getUsuarioId()).ifPresent(x -> lives.setUsuarios(x));
		
		lives.setTitulo(dto.getTitulo());
		lives.setGenero(dto.getGenero());
		lives.setDataIni(dto.getDataIni());
		lives.setDataFim(dto.getDataFim());
		return lives;
	}
	
	public void validaCad(LivesDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());
		
		if (OptionalV2.isPresent(dto.getUsuarioId()))
			if (!usuariosService.findById(dto.getUsuarioId()).isPresent())
				result.addError(new ObjectError("lives", "Usuario de Id: " + dto.getUsuarioId() + " não existe!"));
	}
	
	public void validaUpdate(Long id, LivesDto dto, BindingResult result) {
		
		log.info("Validando update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Lives> lives = livesService.findById(dto.getId());
				if (lives.isPresent()) {
					if (OptionalV2.isPresent(dto.getUsuarioId()))
						if (!usuariosService.findById(dto.getUsuarioId()).isPresent())
							result.addError(new ObjectError("lives", "Usuario de Id: " + dto.getUsuarioId() + " não existe!"));
				} else {
					result.addError(new ObjectError("lives", "Live de Id: " + id + " não existe!"));
				}			
			} else {
				result.addError(new ObjectError("lives", "Id da url ou do Corpo json não podem ser diferentes."));
			}
		} else {
			result.addError(new ObjectError("lives", "Id da url ou do Corpo json não podem ser nulos."));
		}
	}
}
