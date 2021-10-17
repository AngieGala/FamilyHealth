package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TipoDePersonalMedico")
public class TypeMedicalStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTDPM;
	
	@Column(name="nombreTDPM", length=60, nullable=false)
	private String nameTDPM;

	public TypeMedicalStaff() {
		super();
	}

	public TypeMedicalStaff(int idTDPM, String nameTDPM) {
		super();
		this.idTDPM = idTDPM;
		this.nameTDPM = nameTDPM;
	}

	public int getIdTDPM() {
		return idTDPM;
	}

	public void setIdTDPM(int idTDPM) {
		this.idTDPM = idTDPM;
	}

	public String getNameTDPM() {
		return nameTDPM;
	}

	public void setNameTDPM(String nameTDPM) {
		this.nameTDPM = nameTDPM;
	}
	
}
