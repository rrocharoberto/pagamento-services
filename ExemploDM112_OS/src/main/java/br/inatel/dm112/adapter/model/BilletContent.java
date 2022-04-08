package br.inatel.dm112.adapter.model;

import java.util.Date;

public class BilletContent {

	// dados do cliente
	private String UF;
	private String Localidade;
	private String Cep;
	private String Bairro;
	private String Logradouro;
	private String Numero;
	private String Complemento;
	private String Pais;

	// dados do cliente
	private String CPRFSacado;
	private String NomeSacado;

	// dados da empresa
	private Integer NumeroDaConta;
	private Integer Agencia;
	private String DigitoAgencia;
	private String Banco;
	private String TipoDeMoeda;
	private Integer Carteira;

	// dados da empresa
	private String CPRFCedente;
	private String NomeCedente;

	// dados da empresa
	private String NumeroDoDocumento;
	private String NossoNumero;
	private String DigitoDoNossoNumero;

	// instrucoes de pagamento
	private String LocalPagamento1 = "";
	private String InstrucaoAoSacado = "";
	private String Instrucao1 = "";
	private String Instrucao2 = "";

	// dados do pedido
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

	public BilletContent() {

	}

	public void setBusinessData(BilletBusinessData businessData) {

		this.setNumeroDaConta(businessData.getNumeroDaConta());
		this.setAgencia(businessData.getAgencia());
		this.setDigitoAgencia(businessData.getDigitoAgencia());
		this.setBanco(businessData.getBanco());
		this.setTipoDeMoeda(businessData.getTipoDeMoeda());
		this.setCarteira(businessData.getCarteira());

		this.setNumeroDoDocumento(businessData.getNumeroDoDocumento());
		this.setNossoNumero(businessData.getNossoNumero());
		this.setDigitoDoNossoNumero(businessData.getDigitoDoNossoNumero());

		this.setCPRFCedente(businessData.getCPRFCedente());
		this.setNomeCedente(businessData.getNomeCedente());

		this.setTipoDeDocumento(businessData.getTipoDeDocumento());
		this.setAceite(businessData.getAceite());

		this.setLocalPagamento1(businessData.getLocalPagamento1());
		this.setInstrucaoAoSacado(businessData.getInstrucaoAoSacado());
		this.setInstrucao1(businessData.getInstrucao1());
		this.setInstrucao2(businessData.getInstrucao2());
	}

	public void setOrderData(OrderData orderData) {
		this.setValor(orderData.getValor());
		this.setTipoDeDocumento(orderData.getTipoDeDocumento());
		this.setAceite(orderData.getAceite());
		this.setDesconto(orderData.getDesconto());
		this.setDeducao(orderData.getDeducao());
		this.setMora(orderData.getMora());
		this.setAcrecimo(orderData.getAcrecimo());
		this.setValorCobrado(orderData.getValorCobrado());
		this.setDataDoDocumento(orderData.getDataDoDocumento());
		this.setDataDoVencimento(orderData.getDataDoVencimento());
	}

	public void setCustomerData(CustomerData customerData) {
		this.setUF(customerData.getUF());
		this.setLocalidade(customerData.getLocalidade());
		this.setCep(customerData.getCep());
		this.setBairro(customerData.getBairro());
		this.setLogradouro(customerData.getLogradouro());
		this.setNumero(customerData.getNumero());
		this.setComplemento(customerData.getComplemento());
		this.setPais(customerData.getPais());

		this.setCPRFSacado(customerData.getCPRFSacado());
		this.setNomeSacado(customerData.getNomeSacado());
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getLocalidade() {
		return Localidade;
	}

	public void setLocalidade(String localidade) {
		Localidade = localidade;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public Integer getNumeroDaConta() {
		return NumeroDaConta;
	}

	public void setNumeroDaConta(Integer numeroDaConta) {
		NumeroDaConta = numeroDaConta;
	}

	public Integer getAgencia() {
		return Agencia;
	}

	public void setAgencia(Integer agencia) {
		Agencia = agencia;
	}

	public String getDigitoAgencia() {
		return DigitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		//System.out.println("DigitoAgencia: " + DigitoAgencia);
		DigitoAgencia = digitoAgencia;
	}

	public String getBanco() {
		return Banco;
	}

	public void setBanco(String banco) {
		Banco = banco;
	}

	public String getTipoDeMoeda() {
		return TipoDeMoeda;
	}

	public void setTipoDeMoeda(String tipoDeMoeda) {
		TipoDeMoeda = tipoDeMoeda;
	}

	public Integer getCarteira() {
		return Carteira;
	}

	public void setCarteira(Integer carteira) {
		Carteira = carteira;
	}

	public String getNumeroDoDocumento() {
		return NumeroDoDocumento;
	}

	public void setNumeroDoDocumento(String numeroDoDocumento) {
		NumeroDoDocumento = numeroDoDocumento;
	}

	public String getNossoNumero() {
		return NossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		NossoNumero = nossoNumero;
	}

	public String getDigitoDoNossoNumero() {
		return DigitoDoNossoNumero;
	}

	public void setDigitoDoNossoNumero(String digitoDoNossoNumero) {
		DigitoDoNossoNumero = digitoDoNossoNumero;
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

	public String getCPRFCedente() {
		return CPRFCedente;
	}

	public void setCPRFCedente(String cPRFCedente) {
		CPRFCedente = cPRFCedente;
	}

	public String getNomeCedente() {
		return NomeCedente;
	}

	public void setNomeCedente(String nomeCedente) {
		NomeCedente = nomeCedente;
	}

	public String getCPRFSacado() {
		return CPRFSacado;
	}

	public void setCPRFSacado(String cPRFSacado) {
		CPRFSacado = cPRFSacado;
	}

	public String getNomeSacado() {
		return NomeSacado;
	}

	public void setNomeSacado(String nomeSacado) {
		NomeSacado = nomeSacado;
	}

	public String getLocalPagamento1() {
		return LocalPagamento1;
	}

	public void setLocalPagamento1(String localPagamento1) {
		LocalPagamento1 = localPagamento1;
	}

	public String getInstrucaoAoSacado() {
		return InstrucaoAoSacado;
	}

	public void setInstrucaoAoSacado(String instrucaoAoSacado) {
		InstrucaoAoSacado = instrucaoAoSacado;
	}

	public String getInstrucao1() {
		return Instrucao1;
	}

	public void setInstrucao1(String instrucao1) {
		Instrucao1 = instrucao1;
	}

	public String getInstrucao2() {
		return Instrucao2;
	}

	public void setInstrucao2(String instrucao2) {
		Instrucao2 = instrucao2;
	}

}
