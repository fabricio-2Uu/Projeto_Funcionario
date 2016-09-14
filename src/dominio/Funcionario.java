package dominio;

public class Funcionario {
	
	private String nome;
	private double salarioBruto;
	private String cpf;
	private static String sexo;
	private Endereco endereco;
	private static double limiteINSS;
	
	public static double getLimiteINSS() {
		return limiteINSS;
	}
	public static void setLimiteINSS() {
		Funcionario.limiteINSS = 900.00;
	}
	// Define o sexo como "MASCULINO" ou "FEMININO".
	public static void setSexo(String sexo) {
		
		if (sexo == "M" || sexo == "m" )
			Funcionario.sexo = "MASCULINO";
		if (sexo == "F" || sexo == "f" || sexo == "F" )
			Funcionario.sexo = "FEMININO";
		else
			throw new RuntimeException("Erro: Insira M para Masculino ou F para Feminino");
	}
	// Buscar o sexo.
	public String getSexo() {
		return Funcionario.sexo;
	}
	// Buscar o nome.
	public String getNome() {
		return "Nome : " + nome;
	}
	// Definir o nome com no mínimo dois caracteres.
	public void setNome(String nome) {
		if (nome.length() >= 2)
			this.nome = nome;
		else
			throw new RuntimeException("Erro: nome deve ter pelo menos dois caracteres");  
	}
	// Buscar o salário.
	public double getSalarioBruto() {
		return salarioBruto;
	}
	// Define o salário maior ou igual a zero.
	public void setSalarioBruto(double salarioBruto) {
		if (salarioBruto >=0)
			this.salarioBruto = salarioBruto;
		else
			throw new RuntimeException("Erro: Salário deve ser igual ou maior do que zero.");
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
		return "CPF: " + cpf;
	}
	// Define o CPF.
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	// Construtor com todos os parâmetros.
	public Funcionario(String n, String s, String cpf, double sal){
			Funcionario.setSexo(s);
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
		if ((Math.floor((salarioBruto * this.getTxINSS())*100)/100) < 900)
			return Math.floor((salarioBruto * this.getTxINSS())*100)/100;
		else
			return 900;
	}
	//Busca o valor do salário líquido.
	public double getSalarioLiqINSS(){
		return salarioBruto - this.getValorINSS();
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
		return Math.floor((salarioBruto - this.getValorINSS() - this.getValorIR())*100)/100;
	}
	
	//Construtor para todos os atributos.
	public Funcionario(String nome, String sexo, String cpf, double salarioBruto,
			Endereco endereco) {
		
		this.setNome(nome);
		this.setSalarioBruto(salarioBruto);
		this.setCpf(cpf);
		Funcionario.setSexo(sexo);
		this.setEndereco(endereco);
	}
	
	//Construtor vazio
	public Funcionario() {
		this.setNome("---");
		this.setSalarioBruto(0.0);
		this.setCpf("---");
		Funcionario.setSexo("F");
		
	    Endereco e;
	    e = new Endereco("---", 31);
	    this.setEndereco(e);
	}
	
	//Construtor para todos os atributos de Funcionario e Endereço.
     public Funcionario(String nome, String sexo, String cpf, double salarioBruto , 
    		 String rua, int numero) {
    	 
    	this.setNome(nome);
 		this.setSalarioBruto(salarioBruto);
 		this.setCpf(cpf);
 		Funcionario.setSexo(sexo);
 		
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
     
     
	//Sobrescrevi o toString() para imprimir todos os atributos do Funcionário chamando somente o objeto.
	@Override
	public String toString() {
		
		//Variável temporária para avaliar se a txINSS atingiu o teto e tranformá-la em String.
		String txInss;		
		if ((Math.floor((salarioBruto * this.getTxINSS())*100)/100) > 900)
			txInss = "11% -> teto";
		else
			txInss = String.valueOf(this.getTxINSS()*100) + "%";
		
		//Variável temporária para avaliar a txIR e tranformá-la em String.
		String txIR;		
		txIR = String.valueOf(this.getTxIR()*100) + "%";
		
		return  "----------------------------------" +
			    "\nDados do Funcionário" +
			    "\n----------------------------------" +
				"\nNome = " + nome + 
				"\nSexo = " + sexo +  
				"\nCPF = " + cpf + 
				"\n----------------------------------" +
				"\nSalário Bruto            = " + salarioBruto +
				"\nINSS (" + txInss + ")             =  " + this.getValorINSS() +
				"\n                     -------------" +
				"\nSalário Base IR          = " + this.getSalarioLiqINSS() +
				"\nIR (" + txIR + ")               =  " + this.getValorIR() +
				"\n                     -------------" +
				"\nSalário Líquido          = " + this.getSalarioLiquido() +
				"\n----------------------------------"+
				"\nEndereço: " + this.endereco.getRua() + " Numero: " + this.endereco.getNumero();
	}
	
	
}
