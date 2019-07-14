package com.luxlivesback.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.luxlivesback.model.Usuarios;
import com.luxlivesback.service.UsuariosService;
import com.luxlivesback.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuariosControllerTest {
	
	/*@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UsuariosService usuariosService;	
	
	private static final Long ID = Long.valueOf(1);
	private static final String CPF = "457.081.060-85";
	private static final String LOGIN = "teste";
	private static final String SENHA = PasswordUtils.gerarBCrypt("teste");
	private static final String EMAIL = "teste@teste.com";
	private static final String TIPO = "ADMIN";
	private static final String URL = "/usuarios/";*/
	
	/*@Test
	public void testFindByIdInvalid() throws Exception {
		BDDMockito.given(this.usuariosService.findById(Mockito.anyLong())).willReturn(Optional.empty());		
		mvc.perform(MockMvcRequestBuilders.get(URL + ID).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors").value("Usuário nao encontrado para o id 1"));
	}
	
	@Test
	public void testFindByIdValid() throws Exception {
		BDDMockito.given(this.usuariosService.findById(Mockito.anyLong())).willReturn(Optional.of(this.obterDadosUsuario()));		
		mvc.perform(MockMvcRequestBuilders.get(URL + ID).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.password").value(SENHA))
				.andExpect(jsonPath("$.data.email", equalTo(EMAIL)))
				.andExpect(jsonPath("$.data.login", equalTo(LOGIN)))
				.andExpect(jsonPath("$.data.cpf", equalTo(CPF)))
				.andExpect(jsonPath("$.data.tipo", equalTo(TIPO)));				
	}
	
	@Test
	public void testSave() throws Exception {
		BDDMockito.given(this.usuariosService.save(Mockito.any(Usuarios.class))).willReturn(this.obterDadosUsuario());
		mvc.perform(MockMvcRequestBuilders.post("/usuarios/cadastro").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.password").value(SENHA))
		.andExpect(jsonPath("$.data.email", equalTo(EMAIL)))
		.andExpect(jsonPath("$.data.login", equalTo(LOGIN)))
		.andExpect(jsonPath("$.data.cpf", equalTo(CPF)))
		.andExpect(jsonPath("$.data.tipo", equalTo(TIPO)));				
	}
	
	private Usuarios obterDadosUsuario() {		
		Usuarios usuario = new Usuarios();		
		usuario.setId(ID);;
		usuario.setEmail(EMAIL);
		usuario.setLogin(LOGIN);
		usuario.setPassword(SENHA);
		usuario.setCpf(CPF);
		usuario.setTipo(TIPO);
		return usuario;
	}*/

}
