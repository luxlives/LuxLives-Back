package com.luxlivesback.dto;

public class TagsLivesDto {
	
	private Long livesId;
	private Long tagsId;
	
	public TagsLivesDto() {}

	public TagsLivesDto(Long livesId, Long tagsId) {
		super();
		this.livesId = livesId;
		this.tagsId = tagsId;
	}

	public Long getLivesId() {
		return livesId;
	}

	public void setLivesId(Long livesId) {
		this.livesId = livesId;
	}

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
