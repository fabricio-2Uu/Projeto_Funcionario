package telas;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dominio.Endereco;
import dominio.FuncionarioAbstrato;

public class TelaFuncionario {
	 //Atributo que mantem uma referencia ao objeto funcionario e endere�o
	  private FuncionarioAbstrato funcionario;
	  
	  //Atributo para fazer leitura pelo teclado.
	  protected static Scanner scan = new Scanner(System.in);
	  
	  //Construtor.
	  public TelaFuncionario(FuncionarioAbstrato a) {
	    setFuncionario(a);
	  }
	  
	  public void setFuncionario(FuncionarioAbstrato a){
	    if (a == null) {
	      throw new RuntimeException("Erro: funcionario null.");
	    }
	    else {
	      this.funcionario = a;
	    }
	  }
	  
	  public FuncionarioAbstrato getFuncionario() {
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

	  public void leSexo() {
	    System.out.print("Digite o sexo (M ou F): ");
	    char sexo = scan.nextLine().charAt(0);
	    funcionario.setSexo(sexo);
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
	  
	  public void leTetoINSS(){
		  System.out.print("Digite o teto INSS: ");
		  double teto = scan.nextDouble();
		  scan.nextLine();
		  FuncionarioAbstrato.setLimiteINSS(teto);
	  }
	  
	  public void leDataNascimento(){
		  System.out.print("Digite data de nascimento (dd/mm/aaaa): ");
		  String date = scan.nextLine();
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  try {
			  Date d = sdf.parse(date);
			  funcionario.setDataNascimento(d);
		    }
		    catch (Exception e) {
		      System.out.println(e.getLocalizedMessage());
		    }
	  }
	  
	  public void leAtributos() {
	    leNome();
	    leCpf();
	    leSexo();
	    leEndereco();
	    leDataNascimento();
	  }

	  public void imprime() {
		String salarioBruto = Double.toString(funcionario.salarioBruto());
		
		//Vari�vel tempor�ria para avaliar se a txINSS atingiu o teto e tranform�-la em String.
		String txInss;		
		if ((Math.floor((funcionario.salarioBruto() * funcionario.getTxINSS())*100)/100) > 900)
			txInss = "11% -> teto";
		else
			txInss = String.valueOf(funcionario.getTxINSS()*100) + "%";
		
		//Vari�vel tempor�ria para avaliar a txIR e tranform�-la em String.
		String txIR;		
		txIR = String.valueOf(funcionario.getTxIR()*100) + "%";
		
		//Vari�vel para formatar os valores para decimal.
		NumberFormat df = NumberFormat.getCurrencyInstance();
	
		//Verifica��o se hoje � anivers�rio do funcion�rio
		Date d = new Date();
		String mensagem = new String();
		if (d.getDate() == funcionario.getDataNascimento().getDate() && d.getMonth() == funcionario.getDataNascimento().getMonth()){
			mensagem = "Parab�ns, hoje � seu anivers�rio!!";
		}
		else if (d.getMonth() > funcionario.getDataNascimento().getMonth()){
			mensagem = "Seu anives�rio j� passou!!";
		}
		else if (d.getMonth() < funcionario.getDataNascimento().getMonth()){
			int dias = funcionario.getDataNascimento().getDate() - d.getDate();
			int mes = (funcionario.getDataNascimento().getMonth() - d.getMonth())*30;
			mensagem = "Faltam " + (mes + dias) + " para seu anivers�rio!";
		}
		else if (d.getMonth() == funcionario.getDataNascimento().getMonth()){
			int dias = funcionario.getDataNascimento().getDate() - d.getDate();
			mensagem = "Seu anives�rio � este m�s!! Faltam " + dias + " dias.";
		}
	    System.out.println(
	    		"----------------------------------" +
			    "\nDados do Funcion�rio" +
			    "\n----------------------------------" +
				"\nNome = " + funcionario.getNome() + 
				"\nSexo = " + funcionario.getSexo() +  
				"\nCPF = " + funcionario.getCpf() + 
				"\nData de Nascimento = " + DateFormat.getDateInstance().format(funcionario.getDataNascimento()) +
				"\nAviso = " + mensagem +
				"\n----------------------------------" +
				"\nSal�rio Bruto            = " + df.format(funcionario.salarioBruto()) +
				"\nINSS (" + txInss + ")             =  " + df.format(funcionario.getValorINSS()) +
				"\n                     -------------" +
				"\nSal�rio Base IR          = " + df.format(funcionario.getSalarioLiqINSS()) +
				"\nIR (" + txIR + ")               =  " + df.format(funcionario.getValorIR()) +
				"\n                     -------------" +
				"\nSal�rio L�quido          = " + df.format(funcionario.getSalarioLiquido()) +
				"\n----------------------------------"+
				"\nEndere�o: " + funcionario.getEndereco().getRua() + " Numero: " + funcionario.getEndereco().getNumero()
	    		);
	  }
	  
	}//Telafuncionario
