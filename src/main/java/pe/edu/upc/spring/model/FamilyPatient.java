package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FamiliarDelPaciente")
public class FamilyPatient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFDP;
	
	@Column(name="nombreFDP", length=60, nullable=false)
	private String namePM;
	
	@Column(name="apellidoFDP", length=60, nullable=false)
	private String lastnameFDP;
	
	@Column(name="numeroFDP", length=60, nullable=false)
	private String numberFDP;
	
	@Column(name="dniFDP", length=60, nullable=false)
	private String dniFDP;

	public FamilyPatient() {
		super();
	}

	public FamilyPatient(int idFDP, String namePM, String lastnameFDP, String numberFDP, String dniFDP) {
		super();
		this.idFDP = idFDP;
		this.namePM = namePM;
		this.lastnameFDP = lastnameFDP;
		this.numberFDP = numberFDP;
		this.dniFDP = dniFDP;
	}

	public int getIdFDP() {
		return idFDP;
	}

	public void setIdFDP(int idFDP) {
		this.idFDP = idFDP;
	}

	public String getNamePM() {
		return namePM;
	}

	public void setNamePM(String namePM) {
		this.namePM = namePM;
	}

	public String getLastnameFDP() {
		return lastnameFDP;
	}

	public void setLastnameFDP(String lastnameFDP) {
		this.lastnameFDP = lastnameFDP;
	}

	public String getNumberFDP() {
		return numberFDP;
	}

	public void setNumberFDP(String numberFDP) {
		this.numberFDP = numberFDP;
	}

	public String getDniFDP() {
		return dniFDP;
	}

	public void setDniFDP(String dniFDP) {
		this.dniFDP = dniFDP;
	}
	
}
