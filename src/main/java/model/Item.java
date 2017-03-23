package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Entity
@Table(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", namespace = "http://model/item")
public class Item implements Serializable {
	private static final long serialVersionUID = -1189707208682888972L;
	
	@Id
	@Column(name="seq_item")
	@SequenceGenerator(name = "item", sequenceName = "s_item", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item")
	@XmlElement(required = true)
	private Long seqItem;
	
	@Column(name = "quantidade", nullable = false)
	@XmlElement(required = true)
	private Long quantidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seq_produto")
	@XmlElement(required = true)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seq_pedido")
	@XmlElement(required = true)
	@XStreamOmitField
	private Pedido pedido;
	
	@Column(name="valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	public Long getSeqItem() {
		return seqItem;
	}

	public void setSeqItem(Long seqItem) {
		this.seqItem = seqItem;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
