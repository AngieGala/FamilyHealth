package pe.edu.upc.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Patient;

public interface IPatientService {
	public boolean grabar(Patient patient);
	public void eliminar(int idPatient);
	public Optional<Patient> listarId(int idPatient);
	public Optional<Patient> buscarId(int idPatient);
	
	public List<Patient> listar();
	public List<Patient> buscarNombre(String namePatient);
	public List<Patient> buscarApellido(String lastnamePatient);
	public List<Patient> buscarDNI(String dniPatient);
	public List<Patient> buscarCama(int bednumberPatient);
	public List<Patient> findByDatePatient(Date datePatient);
}
