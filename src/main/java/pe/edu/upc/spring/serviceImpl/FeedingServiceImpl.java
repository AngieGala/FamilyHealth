package pe.edu.upc.spring.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Feeding;
import pe.edu.upc.spring.repository.IFeedingRepository;
import pe.edu.upc.spring.service.IFeedingService;

@Service
public class FeedingServiceImpl implements IFeedingService{
	
	@Autowired
	private IFeedingRepository dFeeding;
	
	@Override
	@Transactional
	public boolean grabar(Feeding feeding) {
		Feeding objFeeding = dFeeding.save(feeding);
		if (objFeeding == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idFeeding) {
		dFeeding.deleteById(idFeeding);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Feeding> listarId(int idFeeding) {
		return dFeeding.findById(idFeeding);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Feeding> buscarId(int idFeeding) {
		return dFeeding.findById(idFeeding);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Feeding> listar() {
		return dFeeding.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Feeding> buscarPaciente(String namePatient) {
		return dFeeding.buscarPaciente(namePatient);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Feeding> buscarMedico(String namePM) {
		return dFeeding.buscarMedico(namePM);
	}
	
	@Override
	public List<Feeding> findBydateFeeding(Date dateFeeding) {
		return dFeeding.findBydateFeeding(dateFeeding);
	}

	
}
