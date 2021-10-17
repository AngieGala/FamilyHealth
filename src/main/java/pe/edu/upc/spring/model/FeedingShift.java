package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TurnoDeAlimentacion")
public class FeedingShift implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTRDA;
	
	@Column(name="nombreTRDA", length=60, nullable=false)
	private String nameTRDA;

	public FeedingShift() {
		super();
	}

	public FeedingShift(int idTRDA, String nameTRDA) {
		super();
		this.idTRDA = idTRDA;
		this.nameTRDA = nameTRDA;
	}

	public int getIdTRDA() {
		return idTRDA;
	}

	public void setIdTRDA(int idTRDA) {
		this.idTRDA = idTRDA;
	}

	public String getNameTRDA() {
		return nameTRDA;
	}

	public void setNameTRDA(String nameTRDA) {
		this.nameTRDA = nameTRDA;
	}

}
