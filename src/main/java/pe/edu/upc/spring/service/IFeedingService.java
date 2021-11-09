package pe.edu.upc.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Feeding;

public interface IFeedingService {
	public boolean grabar (Feeding feeding);
	public void eliminar(int idFeeding);
	public Optional<Feeding> listarId(int idFeeding);
	public Optional<Feeding> buscarId(int idFeeding);
	
	public List<Feeding> listar();
	public List<Feeding> buscarPaciente(String namePatient);
	public List<Feeding> buscarMedico(String namePM);
	public List<Feeding> buscarTipo (String nameTDA);
	public List<Feeding>findBydateFeeding(Date dateFeeding);
}
