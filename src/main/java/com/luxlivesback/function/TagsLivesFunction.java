package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.TagsLivesDto;
import com.luxlivesback.model.Lives;
import com.luxlivesback.model.Tags;
import com.luxlivesback.model.TagsLives;
import com.luxlivesback.repository.LivesRepository;
import com.luxlivesback.repository.TagsRepository;
import com.luxlivesback.service.TagsLivesService;

@Service
public class TagsLivesFunction {
	
	private static final Logger log = LoggerFactory.getLogger(TagsLivesFunction.class);
	
	@Autowired
	private TagsLivesService tagsLivesService;
	
	@Autowired
	private TagsRepository tagsRepository;
	
	@Autowired
	private LivesRepository livesRepository;
	
	public TagsLivesDto convertEntityToDto(TagsLives tagsLives) {
		
		log.info("Convertendo Entidade para Dtp: {}", tagsLives.toString());
		TagsLivesDto dto = new TagsLivesDto();
		dto.setLivesId(tagsLives.getLives().getId());
		dto.setTagsId(tagsLives.getTags().getId());
		return dto;
	}
	
	public TagsLives convertDtoToEntity(TagsLivesDto dto) {
		
		log.info("Convertendo Dto para Entidade: {}", dto.toString());
		TagsLives tagsLives = new TagsLives();
		tagsLives.setLives(livesRepository.findById(dto.getLivesId()).get());
		tagsLives.setTags(tagsRepository.findById(dto.getTagsId()).get());
		return tagsLives;
	}
	
	public void validaCad(TagsLivesDto dto, BindingResult result) {
		
		log.info("Validando cadastro, dto: {}", dto.toString());
		
		Optional<Lives> lives = livesRepository.findById(dto.getLivesId());
		if (!lives.isPresent()) result.addError(new ObjectError("tagsLives", "Lives_id: " + dto.getLivesId() + " não existe"));
		
		Optional<Tags> tags = tagsRepository.findById(dto.getTagsId());
		if (!tags.isPresent()) result.addError(new ObjectError("tagsLives", "Tags_id: " + dto.getTagsId() + " não existe"));
		
		if (lives.isPresent() && tags.isPresent())
			if (tagsLivesService.findByLivesIdAndTagsIdLazyToEager(lives.get().getId(), tags.get().getId()).isPresent())
				result.addError(new ObjectError("tagsLives", "TagsLives já existe"));
	}
}
