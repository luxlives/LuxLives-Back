package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.TagsDto;
import com.luxlivesback.model.Tags;
import com.luxlivesback.service.TagsService;
import com.luxlivesback.utils.OptionalV2;

@Service
public class TagsFunction {
	
	private static final Logger log = LoggerFactory.getLogger(TagsFunction.class);
	
	@Autowired
	private TagsService tagsService;
	
	public Tags convertDtoToEntity(TagsDto dto) {
		
		log.info("Convertendo dto para entidade: {}", dto.toString());
		
		Tags tags = new Tags();
		tags.setNome(OptionalV2.formatString(dto.getNome()));
		
		if (!OptionalV2.isPresent(dto.getId()))
			tags.setContador((long) 1);
		else
			tags.setId(dto.getId());
			tags.setContador(dto.getContador());
		
		return tags;
	}
	
	public TagsDto convertEntityToDto(Tags tags) {
		
		log.info("Convertendo entidade para dto: {}", tags.toString());
		
		TagsDto dto = new TagsDto();
		dto.setId(tags.getId());
		dto.setNome(tags.getNome());
		dto.setContador(tags.getContador());
		return dto;
	}
	
	public void validaCad(TagsDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());
		
		if (!OptionalV2.isPresent(dto.getNome())) 
			result.addError(new ObjectError("tags", "Campo nome não pode ser vazio!"));
		else 
			tagsService.findByNomeIgnoreCase(dto.getNome())
				.ifPresent(tags -> result.addError(new ObjectError("tags", "Tag já cadastrada no id: " + tags.getId())));		
	}
	
	public void validUpdate(Long id,TagsDto dto, BindingResult result) {
		
		log.info("Validando update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Tags> tags = tagsService.findById(id);
				if (tags.isPresent()) {
					tagsService.findByNomeIgnoreCase(dto.getNome()).ifPresent(tag -> {
						if (!OptionalV2.compareStrings(tags.get().getNome(), tag.getNome()))
							result.addError(new ObjectError("tags", "Tag " + tag.getNome() + " já cadastrado no id: " + tag.getId()));
					});
				} else {
					result.addError(new ObjectError("tags", "Tags de Id: " + id + " não existe!"));
				}
			} else {
				result.addError(new ObjectError("tags", "Id da url ou do Corpo json não podem ser diferentes."));
			}			
		} else {
			result.addError(new ObjectError("tags", "Id da url ou do Corpo json não podem ser nulos."));
		}
	}
}
