package com.luxlivesback.security.dto;

public class PasswordDto {
	
	private Long id;
	private String oldPassword;
	private String newPassword;
	
	public PasswordDto() {}
	
	public PasswordDto(Long id) { this.id = id; }

	public PasswordDto(Long id, String oldPassword, String newPassword) {
		super();
		this.id = id;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordDto [id=" + id + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}	

}
