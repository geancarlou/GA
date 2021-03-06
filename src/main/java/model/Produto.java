package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "produto")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://model/produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1020950800821773964L;

	@Id
	@Column(name="seq_produto")
	@SequenceGenerator(name = "produto", sequenceName = "s_produto", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
	@XmlElement(required = true)
	private Long seqProduto;
	
	@Column(name="nome", nullable = false, length = 250)
	@XmlElement(required = true)
	private String nome;
	
	@Column(name="valor", nullable = false)
	@XmlElement(defaultValue = "0")
	private BigDecimal valor;

	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
	@XmlElement(required = true)
	private List<Item> listaitens;
	
	public Long getSeqProduto() {
		return seqProduto;
	}

	public void setSeqProduto(Long seqProduto) {
		this.seqProduto = seqProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "seqProduto: " + this.seqProduto+ " nome: " + this.nome + " valor: " + this.valor;
	}
	
}
