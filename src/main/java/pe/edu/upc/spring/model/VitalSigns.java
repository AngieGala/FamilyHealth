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
@Table(name="SignosVitales")
public class VitalSigns implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSV;
	
	@Column(name="saturacionSV", nullable=false)
	private Double saturationSV;
	
	@Column(name="presionSV", nullable=false)
	private Double pressureSV;
	
	@Column(name="temperaturaSV", nullable=false)
	private Double temperatureSV;
	
	@Column(name="fechaSV", nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateSV;
	
	@ManyToOne
	@JoinColumn(name="idpPatient", nullable=false)
	private Patient patient;

	public VitalSigns() {
		super();
	}

	public VitalSigns(int idSV, Double saturationSV, Double pressureSV, Double temperatureSV, Date dateSV,
			Patient patient) {
		super();
		this.idSV = idSV;
		this.saturationSV = saturationSV;
		this.pressureSV = pressureSV;
		this.temperatureSV = temperatureSV;
		this.dateSV = dateSV;
		this.patient = patient;
	}

	public int getIdSV() {
		return idSV;
	}

	public void setIdSV(int idSV) {
		this.idSV = idSV;
	}

	public Double getSaturationSV() {
		return saturationSV;
	}

	public void setSaturationSV(Double saturationSV) {
		this.saturationSV = saturationSV;
	}

	public Double getPressureSV() {
		return pressureSV;
	}

	public void setPressureSV(Double pressureSV) {
		this.pressureSV = pressureSV;
	}

	public Double getTemperatureSV() {
		return temperatureSV;
	}

	public void setTemperatureSV(Double temperatureSV) {
		this.temperatureSV = temperatureSV;
	}

	public Date getDateSV() {
		return dateSV;
	}

	public void setDateSV(Date dateSV) {
		this.dateSV = dateSV;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
}
