package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.FamilyPatient;

public interface IFamilyPatientService {
	public boolean grabar (FamilyPatient familypatient);
	public void eliminar(int idFDP);
	public Optional<FamilyPatient> listarId(int idFDP);
	public Optional<FamilyPatient> buscarId(int idFDP);
	
	public List<FamilyPatient> listar();
	public List<FamilyPatient> buscarNombre(String namePM);
	public List<FamilyPatient> buscarApellido(String lastnameFDP);
	
}
