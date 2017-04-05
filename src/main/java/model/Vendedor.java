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
@Table(name = "vendedor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://model/vendedor")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 824667406255384433L;

	@Id
	@Column(name="seq_vendedor")
	@SequenceGenerator(name = "vendedor", sequenceName = "s_vendedor", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendedor")
	@XmlElement(required = true)
	private Long seqVendedor;
	
	@Column(name="nome", nullable = true, length = 250)
	@XmlElement(required = true)
	private String nome;
	
	@Column(name="documento", nullable = false, length = 14)
	@XmlElement(required = true)
	private String documento;
	
	public Long getSeqVendedor() {
		return seqVendedor;
	}

	public void setSeqVendedor(Long seqVendedor) {
		this.seqVendedor = seqVendedor;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
