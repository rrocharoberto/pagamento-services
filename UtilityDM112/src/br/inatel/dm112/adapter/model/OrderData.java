package br.inatel.dm112.adapter.model;

import java.util.Calendar;
import java.util.Date;

public class OrderData {

	private Float Valor;
	private String TipoDeDocumento;
	private String Aceite;
	private Float Desconto;
	private Float Deducao;
	private Float Mora;
	private Float Acrecimo;
	private Float ValorCobrado;
	private Date DataDoDocumento;
	private Date DataDoVencimento;	
	

	Calendar cal = Calendar.getInstance();
	
	public OrderData() {
		
		this.setValor(1234.56f);
		this.setDesconto(12.34f);
		this.setDeducao(0.00f);
		this.setMora(0.5f);
		this.setAcrecimo(1.2f);
		this.setValorCobrado(1234.56f);
		this.setDataDoDocumento(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 10);
		this.setDataDoVencimento(cal.getTime());
	}
	public Float getValor() {
		return Valor;
	}

	public void setValor(Float valor) {
		Valor = valor;
	}

	public String getTipoDeDocumento() {
		return TipoDeDocumento;
	}

	public void setTipoDeDocumento(String tipoDeDocumento) {
		TipoDeDocumento = tipoDeDocumento;
	}

	public String getAceite() {
		return Aceite;
	}

	public void setAceite(String aceite) {
		Aceite = aceite;
	}

	public Float getDesconto() {
		return Desconto;
	}

	public void setDesconto(Float desconto) {
		Desconto = desconto;
	}

	public Float getDeducao() {
		return Deducao;
	}

	public void setDeducao(Float deducao) {
		Deducao = deducao;
	}

	public Float getMora() {
		return Mora;
	}

	public void setMora(Float mora) {
		Mora = mora;
	}

	public Float getAcrecimo() {
		return Acrecimo;
	}

	public void setAcrecimo(Float acrecimo) {
		Acrecimo = acrecimo;
	}

	public Float getValorCobrado() {
		return ValorCobrado;
	}

	public void setValorCobrado(Float valorCobrado) {
		ValorCobrado = valorCobrado;
	}

	public Date getDataDoDocumento() {
		return DataDoDocumento;
	}

	public void setDataDoDocumento(Date dataDoDocumento) {
		DataDoDocumento = dataDoDocumento;
	}

	public Date getDataDoVencimento() {
		return DataDoVencimento;
	}

	public void setDataDoVencimento(Date dataDoVencimento) {
		DataDoVencimento = dataDoVencimento;
	}

}
