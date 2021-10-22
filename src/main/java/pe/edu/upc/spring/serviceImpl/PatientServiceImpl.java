package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Patient;
import pe.edu.upc.spring.repository.IPatientRepository;
import pe.edu.upc.spring.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{

	@Autowired
	private IPatientRepository dPatient;
	
	@Override
	@Transactional
	public boolean grabar(Patient patient) {
		Patient objPatient = dPatient.save(patient);
		if(objPatient == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPatient) {
		dPatient.deleteById(idPatient);	
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Patient> listarId(int idPatient) {
		return dPatient.findById(idPatient);
	}
	
	@Override
	public Optional<Patient> buscarId(int idPatient) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Patient> listar() {
		return dPatient.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> buscarNombre(String namePatient) {
		return dPatient.buscarNombre(namePatient);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> buscarApellido(String lastnamePatient) {
		return dPatient.buscarApellido(lastnamePatient);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> buscarDNI(String dniPatient) {
		return dPatient.buscarDNI(dniPatient);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Patient> buscarCama(int bednumberPatient) {
		return dPatient.buscarCama(bednumberPatient);
	}
	
}
