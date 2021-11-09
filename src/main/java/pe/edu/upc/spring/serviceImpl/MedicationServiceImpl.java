package pe.edu.upc.spring.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Medication;
import pe.edu.upc.spring.repository.IMedicationRepository;
import pe.edu.upc.spring.service.IMedicationService;

@Service
public class MedicationServiceImpl implements IMedicationService{

	@Autowired
	private IMedicationRepository dMedication;

	@Override
	@Transactional
	public boolean grabar(Medication medication) {
		Medication objMedication = dMedication.save(medication);
		if(objMedication == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idMedication) {
		dMedication.deleteById(idMedication);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Medication> listarId(int idMedication) {
		return dMedication.findById(idMedication);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medication> listar() {
		return dMedication.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medication> buscarPaciente(String namePatient) {
		return dMedication.buscarPaciente(namePatient);
	}
	
	@Override
	public List<Medication> findBydateMedication(Date dateMedication){
		return dMedication.findBydateMedication(dateMedication);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Medication> buscarId(int idMedication) {
		
		return dMedication.findById(idMedication);
	}
	
}
