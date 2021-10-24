package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.VitalSigns;
import pe.edu.upc.spring.repository.IVitalSignsRepository;
import pe.edu.upc.spring.service.IVitalSignsService;

@Service
public class VitalSignsServiceImpl implements IVitalSignsService{
	
	@Autowired
	private IVitalSignsRepository dVitalSigns;
	
	@Override
	@Transactional
	public boolean grabar(VitalSigns vitalsigns) {
		VitalSigns objVitalSigns = dVitalSigns.save(vitalsigns);
		if (objVitalSigns == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idSV) {
		dVitalSigns.deleteById(idSV);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<VitalSigns> listarId(int idSV) {
		return dVitalSigns.findById(idSV);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<VitalSigns> buscarId(int idSV) {
		return dVitalSigns.findById(idSV);
	}

	@Override
	@Transactional(readOnly = true)
	public List<VitalSigns> listar() {
		return dVitalSigns.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<VitalSigns> buscarPaciente(String namePatient) {
		return dVitalSigns.buscarPaciente(namePatient);
	}

	
}
