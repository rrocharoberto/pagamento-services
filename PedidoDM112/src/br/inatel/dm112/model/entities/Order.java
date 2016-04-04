package br.inatel.dm112.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Pedido")
@XmlRootElement(name = "order")
public class Order {
	
	public static enum STATUS { FILLED, PENDING, CONFIRMED }

	@Id
	@Column(name = "numero")
	private int number;

	private String cpf;

	@Column(name = "valor")
	private float value;

	private int status;

	@Column(name = "dataPedido", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@Column(name = "dataEmissao", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;

	@Column(name = "dataPagamento", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	public Order() {
		this.status = STATUS.FILLED.ordinal();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Order [number=" + number + ", cpf=" + cpf + ", value=" + value + ", status=" + status + ", orderDate="
				+ orderDate + ", issueDate=" + issueDate + ", paymentDate=" + paymentDate + "]";
	}

}
