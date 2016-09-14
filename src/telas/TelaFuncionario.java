package telas;

import java.util.Scanner;

import dominio.Endereco;
import dominio.Funcionario;

public class TelaFuncionario {
	 //Atributo que mantem uma referencia ao objeto funcionario e endereço
	  private Funcionario funcionario;
	  
	  //Atributo para fazer leitura pelo teclado.
	  protected static Scanner scan = new Scanner(System.in);
	  
	  //Construtor.
	  public TelaFuncionario(Funcionario a) {
	    setFuncionario(a);
	  }
	  
	  public void setFuncionario(Funcionario a){
	    if (a == null) {
	      throw new RuntimeException("Erro: funcionario null.");
	    }
	    else {
	      this.funcionario = a;
	    }
	  }
	  
	  public Funcionario getFuncionario() {
	    return funcionario;
	  }

	  public void leNome() {
	    System.out.print("Digite o nome: ");
	    String s = scan.nextLine();
	    funcionario.setNome(s);
	  }
	  
	  public void leCpf() {
		    System.out.print("Digite o CPF: ");
		    String s = scan.nextLine();
		    funcionario.setCpf(s);
		  }

	  public void leSalarioBruto() {
	    System.out.print("Digite o salário: ");
	    double d = scan.nextDouble();
	    scan.nextLine(); //Necessário sempre depois que lê um número (para limpar o ENTER do buffer do teclado).
	    funcionario.setSalarioBruto(d);
	  }

	  public void leSexo() {
	    System.out.print("Digite o sexo (M ou F): ");
	    String sexo = scan.next();
	    Funcionario.setSexo(sexo);
	 }
	  
	  public void leEndereco() {
		    System.out.print("Digite o endereço: ");
		    String s = scan.nextLine();
		    System.out.print("Digite o Número: ");
		    int numero = scan.nextInt();
		    scan.nextLine(); //Necessário sempre depois que lê um número (para limpar o ENTER do buffer do teclado).
		    
		    Endereco e = new Endereco();
		    e.setRua(s);
		    e.setNumero(numero);
		    funcionario.setEndereco(e);
	  }
	  
	  public void leAtributos() {
	    leNome();
	    leCpf();
	    leSexo();
	    leSalarioBruto();
	    leEndereco();
	    
	  }

	  public void imprime() {
		String salarioBruto = Double.toString(funcionario.getSalarioBruto());
		
		//Variável temporária para avaliar se a txINSS atingiu o teto e tranformá-la em String.
		String txInss;		
		if ((Math.floor((funcionario.getSalarioBruto() * funcionario.getTxINSS())*100)/100) > 900)
			txInss = "11% -> teto";
		else
			txInss = String.valueOf(funcionario.getTxINSS()*100) + "%";
		
		//Variável temporária para avaliar a txIR e tranformá-la em String.
		String txIR;		
		txIR = String.valueOf(funcionario.getTxIR()*100) + "%";
		
	    System.out.println(
	    		"----------------------------------" +
			    "\nDados do Funcionário" +
			    "\n----------------------------------" +
				"\nNome = " + funcionario.getNome() + 
				"\nSexo = " + funcionario.getSexo() +  
				"\nCPF = " + funcionario.getCpf() + 
				"\n----------------------------------" +
				"\nSalário Bruto            = " + salarioBruto +
				"\nINSS (" + txInss + ")             =  " + funcionario.getValorINSS() +
				"\n                     -------------" +
				"\nSalário Base IR          = " + funcionario.getSalarioLiqINSS() +
				"\nIR (" + txIR + ")               =  " + funcionario.getValorIR() +
				"\n                     -------------" +
				"\nSalário Líquido          = " + funcionario.getSalarioLiquido() +
				"\n----------------------------------"+
				"\nEndereço: " + funcionario.getEndereco().getRua() + " Numero: " + funcionario.getEndereco().getNumero()
	    		);
	  }
	  
	}//Telafuncionario
