package com.luxlivesback.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.luxlivesback.model.Usuarios;
import com.luxlivesback.repository.UsuariosRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	/**
	 * Nome ou email do usuário;
	 * */
	
	static final String CLAIM_KEY_USERNAME = "sub";
	
	/**
	 * perfil do usuário, admin ou funcionario.
	 * */
	
	static final String CLAIM_KEY_ROLE = "role";
	
	/**
	 * definição de quando foi criado.
	 * */
	
	static final String CLAIM_KEY_CREATED = "created";
	
	/**
	 * Os valores das propriedades secret e expiration são abstratidos 
	 * de application.properties através da interpolação ${} e inseridos dentro
	 * das propriedades String e Long com o auxilio da anotação @Value.
	 * */
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	/**
	 * Realiza um parse do token JWT para extrair as informações contidas no corpo dele..
	 * 
	 * @param token
	 * @return Claims
	 * */
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	/**
	 * Obtém o username (email) contido no token JWT.
	 * 
	 * @param token
	 * @return String
	 * */
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	/**
	 * Retorna a data de expiração de um token JWT.
	 * 
	 * @param token
	 * @return Date
	 * */
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	/**
	 * Gerar um novo token JWT contendo os dados (claims) fornecidos.
	 * 
	 * @param claims
	 * @return String
	 * */
	
	private String gerarToken(Map<String, Object> claims) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setExpiration(gerarDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();		
	}
	
	/**
	 * Cria um novo token JWT.
	 * 
	 * @param token
	 * @return String
	 * */
	
	public String refreshToken(String token) {
		String refreshToken = "";
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshToken = gerarToken(claims);
		} catch (Exception e) {
			expiration = null;
		}
		return refreshToken;
	}
	
	/**
	 * Retorna a data de expiração com base na data atual.
	 * 
	 * @return Date
	 * */
	
	private Date gerarDataExpiracao() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	
	/**
	 * Verifica se um token JTW está expirado.
	 * 
	 * @param token
	 * @return boolean
	 * */
	
	private boolean tokenExpirado(String token) {
		Date dataExpiracao = this.getExpirationDateFromToken(token);
		if (dataExpiracao == null) {
			return false;
		}
		return dataExpiracao.before(new Date());
	}
	
	/**
	 * Verifica e retorna se um token JWT é válido.
	 * 
	 * @param token
	 * @return boolean
	 * */
	
	public boolean tokenValido(String token) {
		return !tokenExpirado(token);
	}
	
	/**
	 * Retorna um novo token JWT com base nos dados do usuário.
	 * Criando o Payload do token, onde poderá ser enviados dados para manipulação.
	 * 
	 * @param userDetails
	 * @return String
	 * */
	
	public String obterToken(UserDetails userDetails) {
		
		Usuarios usuario = usuariosRepository.findByEmail(userDetails.getUsername());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());		
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		claims.put("id", usuario.getId());
		claims.put("nome", usuario.getNome());
		claims.put("login", usuario.getLogin());
		claims.put("tipo", usuario.getTipo());
		return gerarToken(claims);
	}
}
