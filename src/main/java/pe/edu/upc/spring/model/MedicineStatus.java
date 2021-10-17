package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EstadoDeLaMedicina")
public class MedicineStatus implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEDLM;
	
	@Column(name="nombreEDLMA", length=60, nullable=false)
	private String nameEDLMA;

	public MedicineStatus() {
		super();
	}

	public MedicineStatus(int idEDLM, String nameEDLMA) {
		super();
		this.idEDLM = idEDLM;
		this.nameEDLMA = nameEDLMA;
	}

	public int getIdEDLM() {
		return idEDLM;
	}

	public void setIdEDLM(int idEDLM) {
		this.idEDLM = idEDLM;
	}

	public String getNameEDLMA() {
		return nameEDLMA;
	}

	public void setNameEDLMA(String nameEDLMA) {
		this.nameEDLMA = nameEDLMA;
	}

}
