package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pedido", namespace = "http://model/pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 2900984748385465232L;

	@Id
	@Column(name="seq_pedido")
	@SequenceGenerator(name = "pedido", sequenceName = "s_pedido", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido")
	@XmlElement(required = true)
	private Long seqPedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seq_cliente")
	@XmlElement(required = true)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seq_vendedor")
	@XmlElement(required = true)
	private Vendedor vendedor;
	
	@Column(name="valor_total_pedido", nullable = false)
	private BigDecimal valorTotalPedido;
	
	@Column(name = "data_pedido")
	@Temporal(TemporalType.DATE)
	private Date dataPedido;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
	@XmlElement(required = true)
	private List<Item> listaitens;
	
	public Long getSeqPedido() {
		return seqPedido;
	}

	public void setSeqPedido(Long seqPedido) {
		this.seqPedido = seqPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor ;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Item> getListaitens() {
		return listaitens;
	}

	public void setListaitens(List<Item> listaitens) {
		this.listaitens = listaitens;
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

}
