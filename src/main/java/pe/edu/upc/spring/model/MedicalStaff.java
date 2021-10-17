package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PersonalMedico")
public class MedicalStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPM;
	
	@Column(name="nombrePM", length=60, nullable=false)
	private String namePM;
	
	@Column(name="apellidoPM", length=60, nullable=false)
	private String lastnamePM;
	
	@Column(name="numeroPM", length=60, nullable=false)
	private String numberPM;
	
	@Column(name="dniPM", length=60, nullable=false)
	private String dniPMe;
	
	@ManyToOne
	@JoinColumn(name="idTDPM", nullable=false)
	private TypeMedicalStaff typemedical;

	public MedicalStaff() {
		super();
	}

	public MedicalStaff(int idPM, String namePM, String lastnamePM, String numberPM, String dniPMe,
			TypeMedicalStaff typemedical) {
		super();
		this.idPM = idPM;
		this.namePM = namePM;
		this.lastnamePM = lastnamePM;
		this.numberPM = numberPM;
		this.dniPMe = dniPMe;
		this.typemedical = typemedical;
	}

	public int getIdPM() {
		return idPM;
	}

	public void setIdPM(int idPM) {
		this.idPM = idPM;
	}

	public String getNamePM() {
		return namePM;
	}

	public void setNamePM(String namePM) {
		this.namePM = namePM;
	}

	public String getLastnamePM() {
		return lastnamePM;
	}

	public void setLastnamePM(String lastnamePM) {
		this.lastnamePM = lastnamePM;
	}

	public String getNumberPM() {
		return numberPM;
	}

	public void setNumberPM(String numberPM) {
		this.numberPM = numberPM;
	}

	public String getDniPMe() {
		return dniPMe;
	}

	public void setDniPMe(String dniPMe) {
		this.dniPMe = dniPMe;
	}

	public TypeMedicalStaff getTypemedical() {
		return typemedical;
	}

	public void setTypemedical(TypeMedicalStaff typemedical) {
		this.typemedical = typemedical;
	}
	
}
