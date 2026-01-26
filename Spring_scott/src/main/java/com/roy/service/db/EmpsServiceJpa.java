package com.roy.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roy.model.Emp;
import com.roy.repository.EmpsRepository;
import com.roy.service.IEmpsService;

@Service
public class EmpsServiceJpa implements IEmpsService {

	@Autowired
	private EmpsRepository empsRepo;
	
	@Override
	public List<Emp> buscarTodas() {
		return empsRepo.findAll();
	}

	@Override
	public Emp buscarPorNumero(Integer empNo) {
		Optional<Emp> optional = empsRepo.findById(empNo);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Emp empleado) {
		empsRepo.save(empleado);
	}

	@Override
	public void eliminar(Integer empNo) {
		empsRepo.deleteById(empNo);
	}

	@Override
	public Page<Emp> buscarTodas(Pageable page) {
		return empsRepo.findAll(page);
	}

}
