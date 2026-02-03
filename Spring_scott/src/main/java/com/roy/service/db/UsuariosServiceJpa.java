package com.roy.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roy.model.Usuario;
import com.roy.repository.UsuariosRepository;
import com.roy.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {
	
	@Autowired
	private UsuariosRepository usuariosRepo;

	@Override
	public List<Usuario> buscarTodas() {
		return usuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorNumero(Integer userId) {
		Optional<Usuario> optional = usuariosRepo.findById(userId);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer userId) {
		usuariosRepo.deleteById(userId);
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		return usuariosRepo.findAll(page);
	}

	@Override
    public Usuario buscarPorUsername(String username) {
        return usuariosRepo.findByUsername(username);
    }

}
