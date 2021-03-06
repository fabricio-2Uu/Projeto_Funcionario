package dominio;

public class Funcionario {
	
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
	
	// retorna o valor do limite do INSS
	public static double getLimiteINSS() {
		return limiteINSS;
	}
	
	// Define um valor fixo para limite do INSS
	public static void setLimiteINSS(double valor) {
		Funcionario.limiteINSS = valor;
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
	// Definir o nome com no m�nimo dois caracteres.
	public void setNome(String nome) {
		if (nome.length() >= 2)
			this.nome = nome;
		else
			throw new RuntimeException("Erro: nome deve ter pelo menos dois caracteres");  
	}
	// Buscar o sal�rio.
	public double getSalarioBruto() {
		return salarioBruto;
	}
	// Define o sal�rio maior ou igual a zero.
	public void setSalarioBruto(double salarioBruto) {
		if (salarioBruto >=0)
			this.salarioBruto = salarioBruto;
		else
			throw new RuntimeException("Erro: Sal�rio deve ser igual ou maior do que zero.");
	}
	//Busca endere�o.
	public Endereco getEndereco() {
		return endereco;
	}
	//Define o endere�o.
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
	// Construtor com todos os par�metros.
	public Funcionario(String n, char s, String cpf, double sal){
			this.setSexo(s);
			this.nome = n;
			this.cpf = cpf;
			this.setSalarioBruto(sal);
	}
	//Busca a taxa do INSS.
	public double getTxINSS(){
		if (salarioBruto <= 1000)
			return 0.08;
		if (salarioBruto > 1000 && salarioBruto <= 2000)
			return 0.09;
		if (salarioBruto > 2000 && salarioBruto <= 3000)
			return 0.10;
		if (salarioBruto > 3000)
			return 0.11;
		else
			return 0;
	}
	//Busca o valor do INSS.
	public double getValorINSS(){
		if ((Math.floor((salarioBruto * this.getTxINSS())*100)/100) < Funcionario.getLimiteINSS())
			return Math.floor((salarioBruto * this.getTxINSS())*100)/100;
		else
			return Funcionario.getLimiteINSS();
	}
	//Busca o valor do sal�rio l�quido.
	public double getSalarioLiqINSS(){
		return salarioBruto - this.getValorINSS();
	}
	//Busca o valor da taxa de IR.
	public double getTxIR(){
		double salarioBase = this.getSalarioLiqINSS();
		
		if (salarioBase <= 1000)
			return 0;
		else if (salarioBase > 1000 && salarioBase <= 3000)
			return 0.10;
		else if (salarioBase > 3000 && salarioBase <= 5000)
			return 0.20;
		else if (salarioBase > 5000)
			return 0.30;
		else
			return 0;	
	}
	//Busca o valor nominal do IR.
	public double getValorIR(){
		return Math.floor((this.getSalarioLiqINSS() * this.getTxIR())*100)/100;

	}
	//Busca o valor nominal do sal�rio l�quido.
	public double getSalarioLiquido(){
		return Math.floor((salarioBruto - this.getValorINSS() - this.getValorIR())*100)/100;
	}
	
	//Construtor para todos os atributos.
	public Funcionario(String nome, char sexo, String cpf, double salarioBruto,
			Endereco endereco) {
		
		this.setNome(nome);
		this.setSalarioBruto(salarioBruto);
		this.setCpf(cpf);
		this.setSexo(sexo);
		this.setEndereco(endereco);
	}
	
	//Construtor vazio
	public Funcionario() {
		this.setNome("---");
		this.setSalarioBruto(0.0);
		this.setCpf("---");
		this.setSexo('-');
		
	    Endereco e;
	    e = new Endereco("---", 31);
	    this.setEndereco(e);
	}
	
	//Construtor para todos os atributos de Funcionario e Endere�o.
     public Funcionario(String nome, char sexo, String cpf, double salarioBruto , 
    		 String rua, int numero) {
    	 
    	this.setNome(nome);
 		this.setSalarioBruto(salarioBruto);
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
		Funcionario other = (Funcionario) obj;
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
		if (Double.doubleToLongBits(salarioBruto) != Double
				.doubleToLongBits(other.salarioBruto))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
     
     
	//Sobrescrevi o toString() para imprimir todos os atributos do Funcion�rio chamando somente o objeto.
	@Override
	public String toString() {
		
		//Vari�vel tempor�ria para avaliar se a txINSS atingiu o teto e tranform�-la em String.
		String txInss;		
		if ((Math.floor((salarioBruto * this.getTxINSS())*100)/100) > Funcionario.getLimiteINSS())
			txInss = "11% -> teto";
		else
			txInss = String.valueOf(this.getTxINSS()*100) + "%";
		
		//Vari�vel tempor�ria para avaliar a txIR e tranform�-la em String.
		String txIR;		
		txIR = String.valueOf(this.getTxIR()*100) + "%";
		
		return  "----------------------------------" +
			    "\nDados do Funcion�rio" +
			    "\n----------------------------------" +
				"\nNome = " + nome + 
				"\nSexo = " + sexo +  
				"\nCPF = " + cpf + 
				"\n----------------------------------" +
				"\nSal�rio Bruto            = " + salarioBruto +
				"\nINSS (" + txInss + ")             =  " + this.getValorINSS() +
				"\n                     -------------" +
				"\nSal�rio Base IR          = " + this.getSalarioLiqINSS() +
				"\nIR (" + txIR + ")               =  " + this.getValorIR() +
				"\n                     -------------" +
				"\nSal�rio L�quido          = " + this.getSalarioLiquido() +
				"\n----------------------------------"+
				"\nEndere�o: " + this.endereco.getRua() + " Numero: " + this.endereco.getNumero();
	}
	
	
}
