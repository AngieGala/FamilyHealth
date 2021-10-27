
package pe.edu.upc.spring.serviceImpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.FeedingShift;
import pe.edu.upc.spring.repository.IFeedingShiftRepository;
import pe.edu.upc.spring.service.IFeedingShiftService;


@Service
public class FeedingShiftServiceImpl implements IFeedingShiftService{
	
	@Autowired
	private IFeedingShiftRepository dFeedingShift;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<FeedingShift> listar() {
		return dFeedingShift.findAll();
	}

	
	
}
