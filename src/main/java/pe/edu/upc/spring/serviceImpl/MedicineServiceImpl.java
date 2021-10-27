
package pe.edu.upc.spring.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Medicine;

import pe.edu.upc.spring.repository.IMedicineRepository;

import pe.edu.upc.spring.service.IMedicineService;


@Service
public class MedicineServiceImpl implements IMedicineService{
	
	@Autowired
	private IMedicineRepository dMedicine;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Medicine> listar() {
		return dMedicine.findAll();
	}

	
}
