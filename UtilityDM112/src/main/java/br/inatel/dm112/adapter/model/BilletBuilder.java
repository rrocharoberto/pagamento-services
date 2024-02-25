package br.inatel.dm112.adapter.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.ConfiguracaoJRimum;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.banco.Agencia;
import org.jrimum.domkee.banco.Carteira;
import org.jrimum.domkee.banco.Cedente;
import org.jrimum.domkee.banco.ContaBancaria;
import org.jrimum.domkee.banco.NumeroDaConta;
import org.jrimum.domkee.banco.Sacado;
import org.jrimum.domkee.banco.TipoDeTitulo;
import org.jrimum.domkee.banco.Titulo;
import org.jrimum.domkee.banco.Titulo.Aceite;
import org.jrimum.domkee.pessoa.CEP;
import org.jrimum.domkee.pessoa.Endereco;
import org.jrimum.domkee.pessoa.UnidadeFederativa;

//classe que gera um boleto de teste usando o jrimum
public class BilletBuilder {

	private Cedente cedente;
	private Sacado sacado;
	private Endereco enderecoSac;
	private ContaBancaria contaBancaria;
	private Titulo titulo;
	private Boleto boleto;

	public BilletBuilder(BilletContent content) {
		ConfiguracaoJRimum.falharEmCPRFInvalido = true;
		ConfiguracaoJRimum.falharEmRegistroVazio = true;
		
		createCedente(content.getNomeCedente(), content.getCPRFCedente());
		createSacado(content.getNomeSacado(), content.getCPRFSacado());
		UnidadeFederativa uf = UnidadeFederativa.valueOfSigla(content.getUF());
		createEnderecoSac(uf, content.getLocalidade(), content.getCep(), content.getBairro(), content.getLogradouro(),
				content.getNumero());
		createContaBancaria(BancosSuportados.BANCO_DO_BRASIL, content.getNumeroDaConta(), "", content.getCarteira(),
				content.getAgencia(), content.getDigitoAgencia());
		createTitulo(content.getNumeroDoDocumento(), content.getNossoNumero(), content.getValor(),
				content.getDesconto(), content.getAcrecimo(), content.getDataDoDocumento(),
				content.getDataDoVencimento());
		createBillet(content.getLocalPagamento1(), content.getInstrucaoAoSacado(), content.getInstrucao1(),
				content.getInstrucao2());
	}

	public Cedente createCedente(String nome, String cpf) {
		cedente = new Cedente(nome, cpf);
		return cedente;
	}

	public Sacado createSacado(String nome, String cpf) {
		sacado = new Sacado(nome, cpf);
		return sacado;
	}

	public Endereco createEnderecoSac(UnidadeFederativa uf, String localidade, String cep, String bairro,
			String logradouro, String numero) {

		enderecoSac = new Endereco();
		enderecoSac.setUF(uf);
		enderecoSac.setLocalidade(localidade);
		enderecoSac.setCep(new CEP(cep));
		enderecoSac.setBairro(bairro);
		enderecoSac.setLogradouro(logradouro);
		enderecoSac.setNumero(numero);
		return enderecoSac;
	}

	public ContaBancaria createContaBancaria(BancosSuportados banco, Integer codigoDaConta, String digitoDaConta,
			Integer codigoCarteira, Integer codigoAgencia, String digitoAgencia) {

		contaBancaria = new ContaBancaria(banco.create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(codigoDaConta, digitoDaConta));
		contaBancaria.setCarteira(new Carteira(codigoCarteira));
		contaBancaria.setAgencia(new Agencia(codigoAgencia, digitoAgencia));
		return contaBancaria;
	}

	public Titulo createTitulo(String numeroDoDocumento, String nossoNumero, float valor, float desconto,
			float acrescimo, Date dataDoDocumento, Date dataDoVencimento) {

		titulo = new Titulo(contaBancaria, sacado, cedente);

		titulo.setNumeroDoDocumento(numeroDoDocumento);
		titulo.setNossoNumero(nossoNumero);
		titulo.setDigitoDoNossoNumero("5");
		titulo.setValor(BigDecimal.valueOf(valor));
		titulo.setDataDoDocumento(dataDoDocumento);
		titulo.setDataDoVencimento(dataDoVencimento);
		titulo.setTipoDeDocumento(TipoDeTitulo.FAT_FATURA);
		titulo.setAceite(Aceite.A);
		titulo.setDesconto(new BigDecimal(desconto));
		titulo.setDeducao(BigDecimal.ZERO);
		titulo.setMora(BigDecimal.ZERO);
		titulo.setAcrecimo(new BigDecimal(acrescimo));
		titulo.setValorCobrado(BigDecimal.ZERO);
		return titulo;
	}

	public void createBillet(String localPagamento1, String insturcaoAoSacado, String instrucao1, String instrucao2) {

		boleto = new Boleto(titulo);

		boleto.setLocalPagamento(localPagamento1);
		boleto.setInstrucaoAoSacado(insturcaoAoSacado);
		boleto.setInstrucao1(instrucao1);
		boleto.setInstrucao2(instrucao2);
	}

	public Boleto getBoleto() {
		return boleto;
	}
}
