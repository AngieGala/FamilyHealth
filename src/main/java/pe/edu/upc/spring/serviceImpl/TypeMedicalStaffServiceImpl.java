
package pe.edu.upc.spring.serviceImpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TypeMedicalStaff;

import pe.edu.upc.spring.repository.ITypeMedicalStaffRepository;

import pe.edu.upc.spring.service.ITypeMedicalStaffService;


@Service
public class TypeMedicalStaffServiceImpl implements ITypeMedicalStaffService{
	
	@Autowired
	private ITypeMedicalStaffRepository dTypeMedicalStaff;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<TypeMedicalStaff> listar() {
		return dTypeMedicalStaff.findAll();
	}

	
	
}
