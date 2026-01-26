package com.roy.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roy.model.Dept;
import com.roy.repository.DepartamentosRepository;
import com.roy.service.IDepartamentosService;

@Service
public class DepartamentosServiceJpa implements IDepartamentosService {
	
	@Autowired
	private DepartamentosRepository departamentosRepo;

	@Override
	public List<Dept> buscarTodas() {
		return departamentosRepo.findAll();
	}

	@Override
	public Dept buscarPorNumero(Integer deptNo) {
		Optional<Dept> optional = departamentosRepo.findById(deptNo);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Dept departamento) {
		departamentosRepo.save(departamento);

	}

	@Override
	public void eliminar(Integer deptNo) {
		departamentosRepo.deleteById(deptNo);

	}

	@Override
	public Page<Dept> buscarTodas(Pageable page) {
		return departamentosRepo.findAll(page);
	}

}
