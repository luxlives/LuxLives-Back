package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.IdiomasDto;
import com.luxlivesback.model.Idiomas;
import com.luxlivesback.service.IdiomasService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class IdiomasFunction {
	
	private static final Logger log = LoggerFactory.getLogger(IdiomasFunction.class);
	
	@Autowired
	private IdiomasService idiomasService;
	
	public IdiomasDto converteEntityToDto(Idiomas idiomas) {
		
		log.info("Convertendo Entidade para Dtp: {}", idiomas.toString());
		IdiomasDto dto = new IdiomasDto();
		dto.setId(idiomas.getId());
		dto.setLingua(idiomas.getLingua());
		return dto;
	}
	
	public Idiomas convertDtoToIdiomasDto(IdiomasDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());
		Idiomas idiomas = new Idiomas();
		
		if (OptionalV2.isPresent(dto.getLingua()))
			idiomas.setId(dto.getId());
		
		idiomas.setLingua(dto.getLingua());
		
		return idiomas;
	}
	
	public void validaCad(IdiomasDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());
		
		if (!OptionalV2.isPresent(dto.getLingua()))
			result.addError(new ObjectError("idiomas", "Campo não pode ser vazio"));		
		else
			idiomasService.findByLinguaIgnoreCase(dto.getLingua())
				.ifPresent(idiomas -> result.addError(new ObjectError("idiomas", "Idiomas já cadastrado no id: " + idiomas.getId())));
	}
	
	public void validaUpdate(Long id, IdiomasDto dto, BindingResult result) {
		
		log.info("Validando update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Idiomas> idiomas = idiomasService.findById(dto.getId());
				if (idiomas.isPresent()) {
					idiomasService.findByLinguaIgnoreCase(dto.getLingua()).ifPresent(idioma -> {
						if (!OptionalV2.compareStrings(idioma.getLingua(), idiomas.get().getLingua()))
							result.addError(new ObjectError("idiomas", "Idioma " + idioma.getLingua() + " já cadastrado no id: " + idioma.getId()));
					});
				} else {
					result.addError(new ObjectError("idiomas", "Idioma de Id: " + id + " não existe!"));
				}				
			} else {
				result.addError(new ObjectError("idiomas", "Id da url ou do Corpo json não podem ser diferentes."));
			}			
		} else {
			result.addError(new ObjectError("idiomas", "Id da url ou do Corpo json não podem ser nulos."));
		}		
	}

}
