package br.inatel.dm112.adapter.model;

public class CustomerData {

	private String UF;
	private String Localidade;
	private String Cep;
	private String Bairro;
	private String Logradouro;
	private String Numero;
	private String Complemento;
	private String Pais;

	private String CPRFSacado;
	private String NomeSacado;

	private String email = "your_email@email.com";

	public CustomerData(String cpf) {
		this.setUF("MG");
		this.setLocalidade("Santa Rita do Sapucaí");
		this.setCep("37545-000");
		this.setBairro("Inatel");
		this.setLogradouro("Av. João de Camargo");
		this.setNumero("510");
		this.setComplemento("Ap. 2");

		this.setCPRFSacado(cpf);
		this.setNomeSacado("João da Silva");
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
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

}
