package com.luxlivesback.function;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.luxlivesback.dto.UsuariosDto;
import com.luxlivesback.model.Paises;
import com.luxlivesback.model.Usuarios;
import com.luxlivesback.repository.PaisesRepository;
import com.luxlivesback.security.dto.PasswordDto;
import com.luxlivesback.service.UsuariosService;
import com.luxlivesback.utils.OptionalV2;
import com.luxlivesback.utils.PasswordUtils;

@Service
public class UsuariosFunction {
	
	private static final Logger log = LoggerFactory.getLogger(UsuariosFunction.class);
	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private PaisesRepository paisesRepository;
	
	public Usuarios convertDtoToEntity(UsuariosDto dto) {
		
		log.info("convertDtoToEntity: {}", dto.toString());
		
		Usuarios usuarios = new Usuarios();
		usuarios.setId(dto.getId());
		usuarios.setLogin(dto.getLogin());		
		usuarios.setEmail(dto.getEmail());
		usuarios.setTipo(dto.getTipo());
		usuarios.setNome(dto.getNome());
		usuarios.setCpf(dto.getCpf());
		usuarios.setRg(dto.getRg());
		usuarios.setFotoDocumento(dto.getFotoDocumento());
		usuarios.setFotoRosto(dto.getFotoRosto());
		usuarios.setDataNasc(dto.getDataNasc());
		usuarios.setBanco(dto.getBanco());
		usuarios.setAgencia(dto.getAgencia());
		usuarios.setConta(dto.getConta());
		usuarios.setGenero(dto.getGenero());
		usuarios.setHomem(dto.getHomem());
		usuarios.setMulher(dto.getMulher());
		usuarios.setTrans(dto.getTrans());
		usuarios.setCasal(dto.getCasal());
		usuarios.setDataAlt(dto.getDataAlt());	
		
		if (OptionalV2.isPresent2(dto.getPaisesId())) {
			Paises paises = paisesRepository.findById(dto.getPaisesId()).get();
			usuarios.setPaises(paises);
		}	
		
		if (!OptionalV2.isPresent(dto.getId()))
			usuarios.setPassword(PasswordUtils.gerarBCrypt(dto.getPassword()));
		
		return usuarios;
	}
	
	public Usuarios convertDtoToEntityToUpdate(UsuariosDto dto) {
		
		log.info("convertDtoToEntityUpdate: {}", dto.toString());

		Usuarios usuarios = usuariosService.findById(dto.getId()).get();
		usuarios.setLogin(dto.getLogin());		
		usuarios.setEmail(dto.getEmail());
		usuarios.setTipo(dto.getTipo());
		usuarios.setNome(dto.getNome());
		usuarios.setCpf(dto.getCpf());
		usuarios.setRg(dto.getRg());
		usuarios.setFotoDocumento(dto.getFotoDocumento());
		usuarios.setFotoRosto(dto.getFotoRosto());
		usuarios.setDataNasc(dto.getDataNasc());
		usuarios.setBanco(dto.getBanco());
		usuarios.setAgencia(dto.getAgencia());
		usuarios.setConta(dto.getConta());
		usuarios.setGenero(dto.getGenero());
		usuarios.setHomem(dto.getHomem());
		usuarios.setMulher(dto.getMulher());
		usuarios.setTrans(dto.getTrans());
		usuarios.setCasal(dto.getCasal());
		
		if (OptionalV2.isPresent2(dto.getPaisesId())) {
			Paises paises = paisesRepository.findById(dto.getPaisesId()).get();
			usuarios.setPaises(paises);
		}
		
		return usuarios;
	}
	
	public UsuariosDto convertEntityToDto(Usuarios usuarios) {
		
		log.info("convertEntityToDto: {}", usuarios.toString());
		
		UsuariosDto dto = new UsuariosDto();
		dto.setId(usuarios.getId());
		dto.setLogin(usuarios.getLogin());
		dto.setPassword(usuarios.getPassword());
		dto.setEmail(usuarios.getEmail());
		dto.setTipo(usuarios.getTipo());
		dto.setNome(usuarios.getNome());
		dto.setCpf(usuarios.getCpf());
		dto.setRg(usuarios.getRg());
		dto.setFotoDocumento(usuarios.getFotoDocumento());
		dto.setFotoRosto(usuarios.getFotoRosto());
		dto.setDataNasc(usuarios.getDataNasc());
		dto.setBanco(usuarios.getBanco());
		dto.setAgencia(usuarios.getAgencia());
		dto.setConta(usuarios.getConta());
		dto.setGenero(usuarios.getGenero());
		dto.setHomem(usuarios.getHomem());
		dto.setMulher(usuarios.getMulher());
		dto.setTrans(usuarios.getTrans());
		dto.setCasal(usuarios.getCasal());
		dto.setDataAlt(usuarios.getDataAlt());
		
		if (usuarios.getPaises() != null)
			dto.setPaisesId(usuarios.getPaises().getId());
		
		return dto;
	}
	
	public void validCad(UsuariosDto dto, BindingResult result) {
		
		log.info("valida cadastro: {}", dto.toString());
		
		if (OptionalV2.isPresent(dto.getEmail()))
			usuariosService.findByEmail(dto.getEmail())
				.ifPresent(x -> result.addError(new ObjectError("usuarios", "Email já cadastrado")));
		else
			result.addError(new ObjectError("usuarios", "Campo Email não pode ser vazio"));		
		
		if (OptionalV2.isPresent(dto.getLogin()))
			usuariosService.findByLogin(dto.getLogin())
				.ifPresent(x -> result.addError(new ObjectError("usuarios", "Login já cadastrado")));
		else
			result.addError(new ObjectError("usuarios", "Campo Login não pode ser vazio"));
		
		if (OptionalV2.isPresent(dto.getCpf()))
			usuariosService.findByCpf(dto.getCpf())
				.ifPresent(x -> result.addError(new ObjectError("usuarios", "Cpf já cadastrado")));
		
		if (!OptionalV2.isPresent(dto.getPassword()))
			result.addError(new ObjectError("usuarios", "Campo Senha não pode ser vazio"));
		
	}
	
	public void validUpdate(Long id, UsuariosDto dto, BindingResult result) {
		
		log.info("valida update, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Usuarios> usuarios = usuariosService.findById(id);
				if (usuarios.isPresent()) {
					
					if (OptionalV2.isPresent(dto.getCpf()))
						usuariosService.findByCpf(dto.getCpf()).ifPresent(usuarioAux -> {
							if (!usuarioAux.getCpf().equals(usuarios.get().getCpf()))
								result.addError(new ObjectError("usuarios", "Usuarios do cpf: " + usuarioAux.getCpf() + " já cadastrado no id: " + usuarioAux.getId()));
						});					
					
					if (OptionalV2.isPresent(dto.getEmail()))
						usuariosService.findByEmail(dto.getEmail()).ifPresent(usuarioAux -> {
							if (!usuarioAux.getEmail().equals(usuarios.get().getEmail()))
								result.addError(new ObjectError("usuarios", "Usuarios do email: " + usuarioAux.getEmail() + " já cadastrado no id: " + usuarioAux.getId()));
						});						
					else
						result.addError(new ObjectError("usuarios", "Campo de email não pode ser nulo ou vazio!"));					
					
					if (OptionalV2.isPresent(dto.getLogin()))
						usuariosService.findByLogin(dto.getLogin()).ifPresent(usuarioAux -> {
							if (!usuarioAux.getLogin().equals(usuarios.get().getLogin()))
								result.addError(new ObjectError("usuarios", "Usuarios do login: " + usuarioAux.getLogin() + " já cadastrado no id: " + usuarioAux.getId()));
						});						
					else
						result.addError(new ObjectError("usuarios", "Campo de login não pode ser nulo ou vazio!"));
					
				} else {
					result.addError(new ObjectError("usuarios", "Usuário de Id: " + id + " não existe!"));
				}				
			} else {
				result.addError(new ObjectError("usuarios", "Id da url ou do Corpo json não podem ser diferentes."));
			}			
		} else {
			result.addError(new ObjectError("usuarios", "Id da url ou do Corpo json não podem ser nulos."));
		}		
	}
	
	public void validUpdatePassword(Long id, PasswordDto dto, BindingResult result) {
		
		log.info("valida update password, id: {}, dto: {}", id, dto.toString());
		
		if (OptionalV2.isPresent(id) && OptionalV2.isPresent(dto.getId())) {
			if (dto.getId().equals(id)) {
				Optional<Usuarios> usuarios = usuariosService.findById(id);
				if (usuarios.isPresent()) {
					if (!PasswordUtils.senhaValida(dto.getOldPassword(), usuarios.get().getPassword())) 
						result.addError(new ObjectError("usuarios", "Senha inválida"));
				} else {
					result.addError(new ObjectError("usuarios", "Usuário de Id: " + id + " não existe!"));
				}							
			} else {
				result.addError(new ObjectError("usuarios", "Id da url ou do Corpo json não podem ser diferentes."));
			}			
		} else {
			result.addError(new ObjectError("usuarios", "Id da url ou do Corpo json não podem ser nulos."));
		}		
	}
}
