package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.MedicalStaff;

public interface IMedicalStaffService {
	public boolean grabar (MedicalStaff medicalstaff);
	public void eliminar(int idPM);
	public Optional<MedicalStaff> listarId(int idPM);
	public Optional<MedicalStaff> buscarId(int idPM);
	
	public List<MedicalStaff> listar();
}
