package com.luxlivesback.dto;

import javax.validation.constraints.NotNull;

public class TagsLivesDto {
	
	private Long livesId;
	private Long tagsId;
	
	public TagsLivesDto() {}

	public TagsLivesDto(Long livesId, Long tagsId) {
		super();
		this.livesId = livesId;
		this.tagsId = tagsId;
	}

	@NotNull(message = "Lives_Id não pode ser vazio")
	public Long getLivesId() {
		return livesId;
	}

	public void setLivesId(Long livesId) {
		this.livesId = livesId;
	}

	@NotNull(message = "Tags_Id não pode ser vazio")
	public Long getTagsId() {
		return tagsId;
	}

	public void setTagsId(Long tagsId) {
		this.tagsId = tagsId;
	}

	@Override
	public String toString() {
		return "TagsLivesDto [livesId=" + livesId + ", tagsId=" + tagsId + "]";
	}
}
