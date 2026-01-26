package com.roy.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roy.model.Dept;
import com.roy.repository.DeptsRepository;
import com.roy.service.IDeptsService;

@Service
public class DeptsServiceJpa implements IDeptsService {
	
	@Autowired
	private DeptsRepository deptsRepo;
	
	@Override
	public List<Dept> buscarTodas() {
		return deptsRepo.findAll();
	}

	@Override
	public Dept buscarPorNumero(Integer deptNo) {
		Optional<Dept> optional = deptsRepo.findById(deptNo);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Dept departamento) {
		deptsRepo.save(departamento);

	}

	@Override
	public void eliminar(Integer deptNo) {
		deptsRepo.deleteById(deptNo);

	}

	@Override
	public Page<Dept> buscarTodas(Pageable page) {
		return deptsRepo.findAll(page);
	}

}
