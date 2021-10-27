package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.FamilyPatient;
import pe.edu.upc.spring.repository.IFamilyPatientRepository;
import pe.edu.upc.spring.service.IFamilyPatientService;

@Service
public class FamilyPatientServiceImpl implements IFamilyPatientService{
	
	@Autowired
	private IFamilyPatientRepository dFamilyPatient;
	
	@Override
	@Transactional
	public boolean grabar(FamilyPatient familypatient) {
		FamilyPatient objFamilyPatient = dFamilyPatient.save(familypatient);
		if (objFamilyPatient == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idFDP) {
		dFamilyPatient.deleteById(idFDP);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<FamilyPatient> listarId(int idFDP) {
		return dFamilyPatient.findById(idFDP);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FamilyPatient> listar() {
		return dFamilyPatient.findAll();
	}
	
}
