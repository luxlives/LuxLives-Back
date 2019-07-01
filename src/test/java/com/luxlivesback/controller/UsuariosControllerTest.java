package com.luxlivesback.controller;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuariosControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UsuariosService usuarioService;
	
	@Test
	public void testBuscarClienteInvalido() throws Exception {
		BDDMockito.given(this.usuarioService.findById(Mockito.anyLong())).willReturn(Optional.empty());
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/1")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors").value("Usuário nao encontrado para o id 1"));
	}
	
	@Test
	public void testeBuscarClienteValido() throws Exception {
		BDDMockito.given(this.usuarioService.findById(Mockito.anyLong())).willReturn(Optional.of(this.obterDadosUsuario()));
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(1))
				.andExpect(jsonPath("$.data.email").value("admin@admin.com"))
				.andExpect(jsonPath("$.data.login").value("admin"));
		
	}
	
	private Usuarios obterDadosUsuario() {
		
		Usuarios usuario = new Usuarios();
		Long id = (long) 1;
		usuario.setId(id);;
		usuario.setEmail("admin@admin.com");
		usuario.setLogin("admin");
		return usuario;
	}

}
