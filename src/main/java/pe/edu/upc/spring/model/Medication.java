package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Medicacion")
public class Medication implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMedication;
	
	@ManyToOne
	@JoinColumn(name="idMedicine", nullable=false)
	private Medicine medicine;
	
	@ManyToOne
	@JoinColumn(name="idEDLM", nullable=false)
	private MedicineStatus medicinestatus;
	
	@Column(name="fechaMedicacion", nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateMedication;
	
	@ManyToOne
	@JoinColumn(name="idPM", nullable=false)
	private MedicalStaff medicalstaff;
	
	@ManyToOne
	@JoinColumn(name="idPatient", nullable=false)
	private Patient patient;

	public Medication() {
		super();
	}

	public Medication(int idMedication, Medicine medicine, MedicineStatus medicinestatus, Date dateMedication,
			MedicalStaff medicalstaff, Patient patient) {
		super();
		this.idMedication = idMedication;
		this.medicine = medicine;
		this.medicinestatus = medicinestatus;
		this.dateMedication = dateMedication;
		this.medicalstaff = medicalstaff;
		this.patient = patient;
	}

	public int getIdMedication() {
		return idMedication;
	}

	public void setIdMedication(int idMedication) {
		this.idMedication = idMedication;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public MedicineStatus getMedicinestatus() {
		return medicinestatus;
	}

	public void setMedicinestatus(MedicineStatus medicinestatus) {
		this.medicinestatus = medicinestatus;
	}

	public Date getDateMedication() {
		return dateMedication;
	}

	public void setDateMedication(Date dateMedication) {
		this.dateMedication = dateMedication;
	}

	public MedicalStaff getMedicalstaff() {
		return medicalstaff;
	}

	public void setMedicalstaff(MedicalStaff medicalstaff) {
		this.medicalstaff = medicalstaff;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	
}
