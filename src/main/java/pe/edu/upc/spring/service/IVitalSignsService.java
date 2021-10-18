package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.VitalSigns;

public interface IVitalSignsService {
	public boolean grabar (VitalSigns vitalsigns);
	public void eliminar(int idSV);
	public Optional<VitalSigns> listarId(int idSV);
	public List<VitalSigns> listar();
	public List<VitalSigns> buscarPaciente(String namePatient);
}
