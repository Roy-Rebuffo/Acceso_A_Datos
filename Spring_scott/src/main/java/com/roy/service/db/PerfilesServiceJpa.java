package com.roy.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roy.model.Perfile;
import com.roy.repository.PerfilesRepository;
import com.roy.service.IPerfilesService;

@Service
public class PerfilesServiceJpa implements IPerfilesService {

	@Autowired
	private PerfilesRepository perfilesRepo;

	@Override
	public List<Perfile> buscarTodas() {
		return perfilesRepo.findAll();
	}

	@Override
	public Perfile buscarPorId(Integer perfilId) {
		Optional<Perfile> optional = perfilesRepo.findById(perfilId);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Perfile perfil) {
		perfilesRepo.save(perfil);
	}

	@Override
	public void eliminar(Integer perfilId) {
		perfilesRepo.deleteById(perfilId);
	}

	@Override
	public Page<Perfile> buscarTodas(Pageable page) {
		return perfilesRepo.findAll(page);
	}

}
