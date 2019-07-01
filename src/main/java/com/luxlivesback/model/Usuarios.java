package com.luxlivesback.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuarios implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5390869323304110252L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;	
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;	
	
	@Column(name = "login", unique = true, nullable = false)
	private String login;	
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "nome", nullable = false)
	private String nome;	
		
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "fotorosto")
	private String fotoRosto;
	
	@Column(name = "fotodocumento")
	private String fotoDocumento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datanasc")
	private Calendar dataNasc;
	
	@Column(name = "banco")
	private String banco;
	
	@Column(name = "agencia")
	private String agencia;
	
	@Column(name = "conta")
	private String conta;	
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "homem")
	private Boolean homem;
	
	@Column(name = "mulher")
	private Boolean mulher;
	
	@Column(name = "trans")
	private Boolean trans;
	
	@Column(name = "casal")
	private Boolean casal;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "datacad", updatable = false)
	private Calendar dataCad;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataalt")
	private Calendar dataAlt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id")
	private Paises paises;
	
	@OneToOne(mappedBy = "usuarios", fetch = FetchType.LAZY, optional = true)
	private Tokens tokens;
	
	@OneToMany(mappedBy = "usuarios", fetch = FetchType.LAZY, targetEntity = IdiomasUsuario.class)
	private List<IdiomasUsuario> idiomasUsuarios;
	
	@OneToMany(mappedBy = "usuarios", fetch = FetchType.LAZY, targetEntity = Lives.class)
	private List<Lives> lives;
	
	@OneToMany(mappedBy = "usuarios", fetch = FetchType.LAZY, targetEntity = ComprasExtrato.class)
	private List<ComprasExtrato> comprasEstratos;
	
	@OneToMany(mappedBy = "usuarioSeguidor", fetch = FetchType.LAZY, targetEntity = Seguidores.class)
	private List<Seguidores> usuariosSeguidores;
	
	@OneToMany(mappedBy = "usuarioSeguido", fetch = FetchType.LAZY, targetEntity = Seguidores.class)
	private List<Seguidores> usuariosSeguido;
	
	@OneToMany(mappedBy = "usuariosStreamer", fetch = FetchType.LAZY, targetEntity = HistoricoLives.class)
	private List<HistoricoLives> historicoLivesStreamers;
	
	@OneToMany(mappedBy = "usuariosEspectador", fetch = FetchType.LAZY, targetEntity = HistoricoLives.class)
	private List<HistoricoLives> historicoLivesEspectadores;
	
	public Usuarios() {}
	
	public Usuarios(Long id) { this.id = id; }	

	public Usuarios(Long id, @Email(message = "Email invalido!") String email, String login, String password,
			String tipo, String nome, @CPF(message = "Cpf invalido!") String cpf, String rg, String fotoRosto,
			String fotoDocumento, Calendar dataNasc, String banco, String agencia, String conta, String genero,
			Boolean homem, Boolean mulher, Boolean trans, Boolean casal, Calendar dataCad, Calendar dataAlt,
			Paises paises, Tokens tokens, List<IdiomasUsuario> idiomasUsuarios, List<Lives> lives,
			List<ComprasExtrato> comprasEstratos, List<Seguidores> usuariosSeguidores, List<Seguidores> usuariosSeguido,
			List<HistoricoLives> historicoLivesStreamers, List<HistoricoLives> historicoLivesEspectadores) {
		super();
		this.id = id;
		this.email = email;
		this.login = login;
		this.password = password;
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
		this.paises = paises;
		this.tokens = tokens;
		this.idiomasUsuarios = idiomasUsuarios;
		this.lives = lives;
		this.comprasEstratos = comprasEstratos;
		this.usuariosSeguidores = usuariosSeguidores;
		this.usuariosSeguido = usuariosSeguido;
		this.historicoLivesStreamers = historicoLivesStreamers;
		this.historicoLivesEspectadores = historicoLivesEspectadores;
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

	public Paises getPaises() {
		return paises;
	}

	public void setPaises(Paises paises) {
		this.paises = paises;
	}

	public Tokens getTokens() {
		return tokens;
	}

	public void setTokens(Tokens tokens) {
		this.tokens = tokens;
	}	

	public List<IdiomasUsuario> getIdiomasUsuarios() {
		return idiomasUsuarios;
	}

	public void setIdiomasUsuarios(List<IdiomasUsuario> idiomasUsuarios) {
		this.idiomasUsuarios = idiomasUsuarios;
	}	

	public List<Lives> getLives() {
		return lives;
	}

	public void setLives(List<Lives> lives) {
		this.lives = lives;
	}	

	public List<ComprasExtrato> getComprasEstratos() {
		return comprasEstratos;
	}

	public void setComprasEstratos(List<ComprasExtrato> comprasEstratos) {
		this.comprasEstratos = comprasEstratos;
	}

	public List<Seguidores> getUsuariosSeguidores() {
		return usuariosSeguidores;
	}

	public void setUsuariosSeguidores(List<Seguidores> usuariosSeguidores) {
		this.usuariosSeguidores = usuariosSeguidores;
	}

	public List<Seguidores> getUsuariosSeguido() {
		return usuariosSeguido;
	}

	public void setUsuariosSeguido(List<Seguidores> usuariosSeguido) {
		this.usuariosSeguido = usuariosSeguido;
	}

	public List<HistoricoLives> getHistoricoLivesStreamers() {
		return historicoLivesStreamers;
	}

	public void setHistoricoLivesStreamers(List<HistoricoLives> historicoLivesStreamers) {
		this.historicoLivesStreamers = historicoLivesStreamers;
	}

	public List<HistoricoLives> getHistoricoLivesEspectadores() {
		return historicoLivesEspectadores;
	}

	public void setHistoricoLivesEspectadores(List<HistoricoLives> historicoLivesEspectadores) {
		this.historicoLivesEspectadores = historicoLivesEspectadores;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", email=" + email + ", login=" + login + ", password=" + password + ", tipo="
				+ tipo + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", fotoRosto=" + fotoRosto
				+ ", fotoDocumento=" + fotoDocumento + ", dataNasc=" + dataNasc + ", banco=" + banco + ", agencia="
				+ agencia + ", conta=" + conta + ", genero=" + genero + ", homem=" + homem + ", mulher=" + mulher
				+ ", trans=" + trans + ", casal=" + casal + ", dataCad=" + dataCad + ", dataAlt=" + dataAlt + "]";
	}	
	
}
