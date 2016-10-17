package dominio;

import java.text.DateFormat;
import java.util.Date;

public abstract class FuncionarioAbstrato {
	//Atributos de Classe
	private static double limiteINSS = 900;

	//Constantes.  
	public static final char MASCULINO = 'M';
	public static final char FEMININO  = 'F';

	//Atributos de Instancia
	private String nome;
	private char sexo;
	private String cpf;
	private double salarioBruto;
	private Endereco endereco;
	private Date dataNascimento;
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	abstract public float salarioBruto();
	
	// retorna o valor do limite do INSS
	public static double getLimiteINSS() {
		return limiteINSS;
	}
	
	// Define um valor fixo para limite do INSS
	public static void setLimiteINSS(double valor) {
		FuncionarioAbstrato.limiteINSS = valor;
	}
	
	// Define o sexo como "MASCULINO" ou "FEMININO".
	public void setSexo(char sexo) {
		if ((sexo==MASCULINO) || (sexo==FEMININO)) {
			this.sexo = sexo;
		}
	}
	// Buscar o sexo.
	public char getSexo() {
		return sexo;
	}
	// Buscar o nome.
	public String getNome() {
		return this.nome;
	}
	// Definir o nome com no mínimo dois caracteres.
	public void setNome(String nome) {
		if (nome.length() >= 2)
			this.nome = nome;
		else
			throw new RuntimeException("Erro: nome deve ter pelo menos dois caracteres");  
	}
	//Busca endereço.
	public Endereco getEndereco() {
		return endereco;
	}
	//Define o endereço.
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	// Buscar o CPF.	
	public String getCpf() {
		return this.cpf;
	}
	// Define o CPF.
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/* Construtor com todos os parâmetros.
	public FuncionarioAbstrato(String n, String s, String cpf, double sal){
			FuncionarioAbstrato.setSexo(s);
			this.nome = n;
			this.cpf = cpf;
			this.setsalarioBruto()(sal);
	}
	*/
	//Busca a taxa do INSS.
	public double getTxINSS(){
		if (salarioBruto() <= 1000)
			return 0.08;
		if (salarioBruto() > 1000 && salarioBruto() <= 2000)
			return 0.09;
		if (salarioBruto() > 2000 && salarioBruto() <= 3000)
			return 0.10;
		if (salarioBruto() > 3000)
			return 0.11;
		else
			return 0;
	}
	//Busca o valor do INSS.
	public double getValorINSS(){
		if ((Math.floor((salarioBruto() * this.getTxINSS())*100)/100) < FuncionarioAbstrato.getLimiteINSS())
			return Math.floor((salarioBruto() * this.getTxINSS())*100)/100;
		else
			return FuncionarioAbstrato.getLimiteINSS();
	}
	//Busca o valor do salário líquido.
	public double getSalarioLiqINSS(){
		return salarioBruto() - this.getValorINSS();
	}
	//Busca o valor da taxa de IR.
	public double getTxIR(){
		double salarioBase = this.getSalarioLiqINSS();
		
		if (salarioBase <= 1000)
			return 0;
		if (salarioBase > 1000 && salarioBase <= 3000)
			return 0.10;
		if (salarioBase > 3000 && salarioBase <= 5000)
			return 0.20;
		if (salarioBase > 5000)
			return 0.30;
		else
			return 0;	
	}
	//Busca o valor nominal do IR.
	public double getValorIR(){
		return Math.floor((this.getSalarioLiqINSS() * this.getTxIR())*100)/100;

	}
	//Busca o valor nominal do salário líquido.
	public double getSalarioLiquido(){
		return Math.floor((salarioBruto() - this.getValorINSS() - this.getValorIR())*100)/100;
	}
	
	//Construtor para todos os atributos.
	public FuncionarioAbstrato(String nome, char sexo, String cpf, Endereco endereco) {
		
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSexo(sexo);
		this.setEndereco(endereco);
	}
	
	//Construtor vazio
	public FuncionarioAbstrato() {
		this.setNome("---");
		this.setCpf("---");
		this.setSexo('-');
		
	    Endereco e;
	    e = new Endereco("---", 31);
	    this.setEndereco(e);
	}
	
	//Construtor para todos os atributos de Funcionario e Endereço.
     public FuncionarioAbstrato(String nome, char sexo, String cpf, String rua, int numero) {
    	 
    	this.setNome(nome);
 		this.setCpf(cpf);
 		this.setSexo(sexo);
 		
	    Endereco e;
	    e = new Endereco(rua, numero);
	    this.setEndereco(e);
	 }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioAbstrato other = (FuncionarioAbstrato) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(salarioBruto()) != Double
				.doubleToLongBits(other.salarioBruto()))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
	
}
