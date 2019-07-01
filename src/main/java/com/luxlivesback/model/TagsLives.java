package com.luxlivesback.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(TagsLivesId.class)
@Table(name = "tags_lives", schema = "public")
public class TagsLives implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3748169951822826719L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "live_id", nullable = false, insertable = false, updatable = false)
	private Lives lives;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tags_id", nullable = false, insertable = false, updatable = false)
	private Tags tags;
	
	public TagsLives() {}
	
	public TagsLives(Lives lives) { this.lives = lives; }
	
	public TagsLives(Tags tags) { this.tags = tags; }

	public TagsLives(Lives lives, Tags tags) {
		super();
		this.lives = lives;
		this.tags = tags;
	}

	public Lives getLives() {
		return lives;
	}

	public void setLives(Lives lives) {
		this.lives = lives;
	}

	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TagsLives [lives_id=" + lives.getId() + ", tags_id=" + tags.getId() + "]";
	}	

}
