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
@Table(name="Alimentacion")
public class Feeding implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFeeding;
	
	@ManyToOne
	@JoinColumn(name="idTDA", nullable=false)
	private FeedingType feedingtype;
	
	@ManyToOne
	@JoinColumn(name="idTRDA", nullable=false)
	private FeedingShift feedingshift;
	
	@Column(name="fechaAlimentacion", nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFeeding;
	
	@ManyToOne
	@JoinColumn(name="idPatient", nullable=false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="idPM", nullable=false)
	private MedicalStaff medicalstaff;

	public Feeding() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feeding(int idFeeding, FeedingType feedingtype, FeedingShift feedingshift, Date dateFeeding, Patient patient,
			MedicalStaff medicalstaff) {
		super();
		this.idFeeding = idFeeding;
		this.feedingtype = feedingtype;
		this.feedingshift = feedingshift;
		this.dateFeeding = dateFeeding;
		this.patient = patient;
		this.medicalstaff = medicalstaff;
	}

	public int getIdFeeding() {
		return idFeeding;
	}

	public void setIdFeeding(int idFeeding) {
		this.idFeeding = idFeeding;
	}

	public FeedingType getFeedingtype() {
		return feedingtype;
	}

	public void setFeedingtype(FeedingType feedingtype) {
		this.feedingtype = feedingtype;
	}

	public FeedingShift getFeedingshift() {
		return feedingshift;
	}

	public void setFeedingshift(FeedingShift feedingshift) {
		this.feedingshift = feedingshift;
	}

	public Date getDateFeeding() {
		return dateFeeding;
	}

	public void setDateFeeding(Date dateFeeding) {
		this.dateFeeding = dateFeeding;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public MedicalStaff getMedicalstaff() {
		return medicalstaff;
	}

	public void setMedicalstaff(MedicalStaff medicalstaff) {
		this.medicalstaff = medicalstaff;
	}

	
	
}

