package br.inatel.dm112.adapter.model;

public class BilletBusinessData {

	// dados da empresa
	private Integer NumeroDaConta=123456;
	private Integer Agencia = 1234;
	private String DigitoAgencia = "x";
	private String Banco = "Banco X";
	private String TipoDeMoeda = "REAL";
	private Integer Carteira = 30;

	// dados da empresa
	private String CPRFCedente = "304.132.341-93";
	private String NomeCedente = "José da Silva";

	// dados da empresa
	private String TipoDeDocumento;
	private String Aceite;

	// dados da empresa
	private String NumeroDoDocumento = "1234567890";
	private String NossoNumero = "98765432123";
	private String DigitoDoNossoNumero = "5";

	// instrucoes de pagamento
	private String LocalPagamento1 = "Pagável em qualquer agência até o vencimento.";
	private String InstrucaoAoSacado = "Receber somente em dinheiro.";
	private String Instrucao1 = "Multa de 2% ao mês após o vencimento.";
	private String Instrucao2 = "Após o vencimento, cobrar juros.";

	public BilletBusinessData(String orderNumber, String cpf) {

		NumeroDaConta=123456;
		Agencia = 1234;
		DigitoAgencia = "x";
		Banco = "Banco X";
		TipoDeMoeda = "REAL";
		Carteira = 30;

		NumeroDoDocumento = orderNumber;
		NossoNumero = "98765432123";
		DigitoDoNossoNumero = "5";

		TipoDeDocumento = "FAT_FATURA";
		Aceite = "A";
		
		CPRFCedente = cpf;
		NomeCedente = "José da Silva";

		LocalPagamento1 = "Pagável em qualquer agência até o vencimento.";
		InstrucaoAoSacado = "Receber somente em dinheiro.";
		Instrucao1 = "Multa após o vencimento.";
		Instrucao2 = "Após o vencimento, cobrar juros.";
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

	public String getCPRFCedente() {
		return CPRFCedente;
	}

	public void setCPRFCedente(String cPRFCedente) {
		CPRFCedente = cPRFCedente;
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

	public String getNomeCedente() {
		return NomeCedente;
	}

	public void setNomeCedente(String nomeCedente) {
		NomeCedente = nomeCedente;
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
