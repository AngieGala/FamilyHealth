package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TipoDeAlimentacion")
public class FeedingType implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTDA;
	
	@Column(name="nombreTDA", length=60, nullable=false)
	private String nameTDA;

	public FeedingType() {
		super();
	}

	public FeedingType(int idTDA, String nameTDA) {
		super();
		this.idTDA = idTDA;
		this.nameTDA = nameTDA;
	}

	public int getIdTDA() {
		return idTDA;
	}

	public void setIdTDA(int idTDA) {
		this.idTDA = idTDA;
	}

	public String getNameTDA() {
		return nameTDA;
	}

	public void setNameTDA(String nameTDA) {
		this.nameTDA = nameTDA;
	}
	
}
