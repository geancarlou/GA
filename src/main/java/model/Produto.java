package model;

import java.io.Serializable;
import java.math.BigDecimal;

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
	
	@Column(name="descricao", nullable = false, length = 250)
	@XmlElement(required = true)
	private String descricao;
	
	@Column(name="valor", nullable = false)
	@XmlElement(defaultValue = "0")
	private BigDecimal valor;

	public Long getSeqProduto() {
		return seqProduto;
	}

	public void setSeqProduto(Long seqProduto) {
		this.seqProduto = seqProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
