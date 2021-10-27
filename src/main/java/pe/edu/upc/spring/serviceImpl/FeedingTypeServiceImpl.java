
package pe.edu.upc.spring.serviceImpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.FeedingType;
import pe.edu.upc.spring.repository.IFeedingTypeRepository;
import pe.edu.upc.spring.service.IFeedingTypeService;


@Service
public class FeedingTypeServiceImpl implements IFeedingTypeService{
	
	@Autowired
	private IFeedingTypeRepository dFeedingType;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<FeedingType> listar() {
		return dFeedingType.findAll();
	}

	
	
}
