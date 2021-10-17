package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Medicina")
public class Medicine implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMedicine;
	
	@Column(name="nombreMedicina", length=60, nullable=false)
	private String nameMedicine;

	public Medicine() {
		super();
	}

	public Medicine(int idMedicine, String nameMedicine) {
		super();
		this.idMedicine = idMedicine;
		this.nameMedicine = nameMedicine;
	}

	public int getIdMedicine() {
		return idMedicine;
	}

	public void setIdMedicine(int idMedicine) {
		this.idMedicine = idMedicine;
	}

	public String getNameMedicine() {
		return nameMedicine;
	}

	public void setNameMedicine(String nameMedicine) {
		this.nameMedicine = nameMedicine;
	}
	
}
