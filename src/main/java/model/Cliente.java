package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cliente", namespace = "http://model/cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 5593952461564152276L;

	@Id
	@Column(name="seq_cliente")
	@SequenceGenerator(name = "cliente", sequenceName = "s_cliente", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente")
	@XmlElement(required = true)
	private Long seqCliente;
	
	@Column(name="nome", nullable = false, length = 250)
	@XmlElement(required = true)
	private String nome;
	
	public Long getSeqCliente() {
		return seqCliente;
	}

	public void setSeqCliente(Long seqCliente) {
		this.seqCliente = seqCliente;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
