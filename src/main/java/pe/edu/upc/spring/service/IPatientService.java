package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Patient;

public interface IPatientService {
	public boolean grabar(Patient patient);
	public void eliminar(int idPatient);
	public Optional<Patient> listarId(int idPatient);
	public List<Patient> listar();
	public List<Patient> buscarNombre(String namePatient);
}
