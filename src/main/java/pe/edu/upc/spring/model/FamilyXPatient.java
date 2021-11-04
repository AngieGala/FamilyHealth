package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FamiliarPorPaciente")
public class FamilyXPatient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFXP;
	
	@ManyToOne
	@JoinColumn(name="idPatient", nullable=false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="idFamilyPatient", nullable=false)
	private FamilyPatient familypatient;

	public FamilyXPatient() {
		super();
	}

	public FamilyXPatient(int idFXP, Patient patient, FamilyPatient familypatient) {
		super();
		this.idFXP = idFXP;
		this.patient = patient;
		this.familypatient = familypatient;
	}

	public int getIdFXP() {
		return idFXP;
	}

	public void setIdFXP(int idFXP) {
		this.idFXP = idFXP;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public FamilyPatient getFamilypatient() {
		return familypatient;
	}

	public void setFamilypatient(FamilyPatient familypatient) {
		this.familypatient = familypatient;
	}

}
