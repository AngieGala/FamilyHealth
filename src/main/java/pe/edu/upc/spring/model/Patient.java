package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Paciente")
public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPatient;
	
	@Column(name="nombrePaciente", length=60, nullable=false)
	private String namePatient;
	
	@Column(name="apellidoPaciente", length=60, nullable=false)
	private String lastnamePatient;
	
	@Column(name="dniPaciente", length=8, nullable=false)
	private String dniPatient;
	
	@Column(name="edadPaciente", nullable=false)
	private int agePatient;
	
	@Column(name="ncamaPaciente", nullable=false)
	private int bednumberPatient;
	
	@Column(name="fechaPaciente", nullable=false)
	private Date datePatient;

	public Patient() {
		super();
	}

	public Patient(int idPatient, String namePatient, String lastnamePatient, String dniPatient, int agePatient,
			int bednumberPatient, Date datePatient) {
		super();
		this.idPatient = idPatient;
		this.namePatient = namePatient;
		this.lastnamePatient = lastnamePatient;
		this.dniPatient = dniPatient;
		this.agePatient = agePatient;
		this.bednumberPatient = bednumberPatient;
		this.datePatient = datePatient;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public String getNamePatient() {
		return namePatient;
	}

	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}

	public String getLastnamePatient() {
		return lastnamePatient;
	}

	public void setLastnamePatient(String lastnamePatient) {
		this.lastnamePatient = lastnamePatient;
	}

	public String getDniPatient() {
		return dniPatient;
	}

	public void setDniPatient(String dniPatient) {
		this.dniPatient = dniPatient;
	}

	public int getAgePatient() {
		return agePatient;
	}

	public void setAgePatient(int agePatient) {
		this.agePatient = agePatient;
	}

	public int getBednumberPatient() {
		return bednumberPatient;
	}

	public void setBednumberPatient(int bednumberPatient) {
		this.bednumberPatient = bednumberPatient;
	}

	public Date getDatePatient() {
		return datePatient;
	}

	public void setDatePatient(Date datePatient) {
		this.datePatient = datePatient;
	}
	
}
