package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.MedicalStaff;
import pe.edu.upc.spring.repository.IMedicalStaffRepository;
import pe.edu.upc.spring.service.IMedicalStaffService;

@Service
public class MedicalStaffServiceImpl implements IMedicalStaffService{
	
	@Autowired
	private IMedicalStaffRepository dMedicalStaff;
	
	@Override
	@Transactional
	public boolean grabar(MedicalStaff medicalstaff) {
		MedicalStaff objMedicalStaff = dMedicalStaff.save(medicalstaff);
		if (objMedicalStaff == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPM) {
		dMedicalStaff.deleteById(idPM);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MedicalStaff> listarId(int idPM) {
		return dMedicalStaff.findById(idPM);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<MedicalStaff> buscarId(int idPM) {
		return dMedicalStaff.findById(idPM);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MedicalStaff> listar() {
		return dMedicalStaff.findAll();
	}

	@Override
	public List<MedicalStaff> buscartype(String nameTDPM) {
		return dMedicalStaff.buscartype(nameTDPM);
	}

	@Override
	public List<MedicalStaff> buscarNombre(String namePM) {
		// TODO Auto-generated method stub
		return dMedicalStaff.buscarNombre(namePM);
	}

	@Override
	public List<MedicalStaff> buscarApellido(String lastnamePM) {
		// TODO Auto-generated method stub
		return dMedicalStaff.buscarApellido(lastnamePM);
	}

	@Override
	public List<MedicalStaff> buscarDNI(String dniPMe) {
		// TODO Auto-generated method stub
		return dMedicalStaff.buscarDNI(dniPMe);
	}


	
}
