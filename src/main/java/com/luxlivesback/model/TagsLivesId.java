package com.luxlivesback.model;

import java.io.Serializable;

public class TagsLivesId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5612356935609813527L;
	
	private Long lives;
	private Long tags;
	
	public TagsLivesId() {}

	public TagsLivesId(Long lives, Long tags) {
		super();
		this.lives = lives;
		this.tags = tags;
	}

	public Long getLives() {
		return lives;
	}

	public void setLives(Long lives) {
		this.lives = lives;
	}

	public Long getTags() {
		return tags;
	}

	public void setTags(Long tags) {
		this.tags = tags;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lives == null) ? 0 : lives.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		TagsLivesId other = (TagsLivesId) obj;
		
		if (lives == null) {
			if (other.lives != null) 
				return false;
		} else if (!lives.equals(other.lives)) {
			return false;
		}
		
		if (tags == null) {
			if (other.tags != null) 
				return false;
		} else if (!tags.equals(other.tags)) {
			return false;
		}
		
		return true;		
	}

}
