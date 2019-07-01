package com.luxlivesback.dto;

import java.util.Calendar;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

public class UsuariosDto {
	
	private Long id;
	private String login;
	private String password;
	private String email;
	private String tipo;
	private String nome;
	private String cpf;
	private String rg;
	private String fotoRosto;
	private String fotoDocumento;
	private Calendar dataNasc;
	private String banco;
	private String agencia;
	private String conta;	
	private String genero;
	private Boolean homem;
	private Boolean mulher;
	private Boolean trans;
	private Boolean casal;
	private Calendar dataCad;
	private Calendar dataAlt;
	private Long paisesId;
	
	public UsuariosDto() {}
	
	public UsuariosDto(Long id) { this.id = id; }	

	public UsuariosDto(Long id, String login, String password, String email, String tipo, String nome, String cpf,
			String rg, String fotoRosto, String fotoDocumento, Calendar dataNasc, String banco, String agencia,
			String conta, String genero, Boolean homem, Boolean mulher, Boolean trans, Boolean casal, Calendar dataCad,
			Calendar dataAlt, Long paisesId) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.tipo = tipo;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.fotoRosto = fotoRosto;
		this.fotoDocumento = fotoDocumento;
		this.dataNasc = dataNasc;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.genero = genero;
		this.homem = homem;
		this.mulher = mulher;
		this.trans = trans;
		this.casal = casal;
		this.dataCad = dataCad;
		this.dataAlt = dataAlt;
		this.paisesId = paisesId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Email(message = "Email invalido!")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@CPF(message = "Cpf invalido!")		
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getFotoRosto() {
		return fotoRosto;
	}

	public void setFotoRosto(String fotoRosto) {
		this.fotoRosto = fotoRosto;
	}

	public String getFotoDocumento() {
		return fotoDocumento;
	}

	public void setFotoDocumento(String fotoDocumento) {
		this.fotoDocumento = fotoDocumento;
	}

	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Boolean getHomem() {
		return homem;
	}

	public void setHomem(Boolean homem) {
		this.homem = homem;
	}

	public Boolean getMulher() {
		return mulher;
	}

	public void setMulher(Boolean mulher) {
		this.mulher = mulher;
	}

	public Boolean getTrans() {
		return trans;
	}

	public void setTrans(Boolean trans) {
		this.trans = trans;
	}

	public Boolean getCasal() {
		return casal;
	}

	public void setCasal(Boolean casal) {
		this.casal = casal;
	}

	public Calendar getDataCad() {
		return dataCad;
	}

	public void setDataCad(Calendar dataCad) {
		this.dataCad = dataCad;
	}

	public Calendar getDataAlt() {
		return dataAlt;
	}

	public void setDataAlt(Calendar dataAlt) {
		this.dataAlt = dataAlt;
	}

	public Long getPaisesId() {
		return paisesId;
	}

	public void setPaisesId(Long paisesId) {
		this.paisesId = paisesId;
	}

	@Override
	public String toString() {
		return "UsuariosDto [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", tipo="
				+ tipo + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", fotoRosto=" + fotoRosto
				+ ", fotoDocumento=" + fotoDocumento + ", dataNasc=" + dataNasc + ", banco=" + banco + ", agencia="
				+ agencia + ", conta=" + conta + ", genero=" + genero + ", homem=" + homem + ", mulher=" + mulher
				+ ", trans=" + trans + ", casal=" + casal + ", dataCad=" + dataCad + ", dataAlt=" + dataAlt
				+ ", paisesId=" + paisesId + "]";
	}
}
