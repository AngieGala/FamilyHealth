package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Medication;

public interface IMedicationService {
	public boolean grabar(Medication medication);
	public void eliminar(int idMedication);
	public Optional<Medication> listarId(int idMedication);
	
	public List<Medication> listar();
	public List<Medication> buscarPaciente(String namePaciente);
}
