
package pe.edu.upc.spring.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.MedicineStatus;
import pe.edu.upc.spring.repository.IMedicineStatusRepository;
import pe.edu.upc.spring.service.IMedicineStatusService;


@Service
public class MedicineStatusServiceImpl implements IMedicineStatusService{
	
	@Autowired
	private IMedicineStatusRepository dMedicineStatus;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<MedicineStatus> listar() {
		return dMedicineStatus.findAll();
	}

	
}
