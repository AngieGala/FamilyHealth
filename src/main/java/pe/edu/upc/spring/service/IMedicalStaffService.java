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
	public List<MedicalStaff> buscartype(String nameTDPM);
	public List<MedicalStaff> buscarNombre(String namePM);
	public List<MedicalStaff> buscarApellido(String lastnamePM);
	public List<MedicalStaff> buscarDNI(String dniPMe);
}
