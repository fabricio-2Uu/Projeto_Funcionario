package telas;

import java.util.Scanner;

import dominio.Endereco;
import dominio.Funcionario;

public class TelaFuncionario {
	 //Atributo que mantem uma referencia ao objeto funcionario e endere�o
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
	    System.out.print("Digite o sal�rio: ");
	    double d = scan.nextDouble();
	    scan.nextLine(); //Necess�rio sempre depois que l� um n�mero (para limpar o ENTER do buffer do teclado).
	    funcionario.setSalarioBruto(d);
	  }

	  public void leSexo() {
	    System.out.print("Digite o sexo (M ou F): ");
	    String sexo = scan.next();
	    Funcionario.setSexo(sexo);
	 }
	  
	  public void leEndereco() {
		    System.out.print("Digite o endere�o: ");
		    String s = scan.nextLine();
		    System.out.print("Digite o N�mero: ");
		    int numero = scan.nextInt();
		    scan.nextLine(); //Necess�rio sempre depois que l� um n�mero (para limpar o ENTER do buffer do teclado).
		    
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
		
		//Vari�vel tempor�ria para avaliar se a txINSS atingiu o teto e tranform�-la em String.
		String txInss;		
		if ((Math.floor((funcionario.getSalarioBruto() * funcionario.getTxINSS())*100)/100) > 900)
			txInss = "11% -> teto";
		else
			txInss = String.valueOf(funcionario.getTxINSS()*100) + "%";
		
		//Vari�vel tempor�ria para avaliar a txIR e tranform�-la em String.
		String txIR;		
		txIR = String.valueOf(funcionario.getTxIR()*100) + "%";
		
	    System.out.println(
	    		"----------------------------------" +
			    "\nDados do Funcion�rio" +
			    "\n----------------------------------" +
				"\nNome = " + funcionario.getNome() + 
				"\nSexo = " + funcionario.getSexo() +  
				"\nCPF = " + funcionario.getCpf() + 
				"\n----------------------------------" +
				"\nSal�rio Bruto            = " + salarioBruto +
				"\nINSS (" + txInss + ")             =  " + funcionario.getValorINSS() +
				"\n                     -------------" +
				"\nSal�rio Base IR          = " + funcionario.getSalarioLiqINSS() +
				"\nIR (" + txIR + ")               =  " + funcionario.getValorIR() +
				"\n                     -------------" +
				"\nSal�rio L�quido          = " + funcionario.getSalarioLiquido() +
				"\n----------------------------------"+
				"\nEndere�o: " + funcionario.getEndereco().getRua() + " Numero: " + funcionario.getEndereco().getNumero()
	    		);
	  }
	  
	}//Telafuncionario
