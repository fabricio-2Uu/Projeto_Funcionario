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
	 //Atributo que mantem uma referencia ao objeto funcionario e endereço
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
		
		//Variável temporária para avaliar se a txINSS atingiu o teto e tranformá-la em String.
		String txInss;		
		if ((Math.floor((funcionario.salarioBruto() * funcionario.getTxINSS())*100)/100) > 900)
			txInss = "11% -> teto";
		else
			txInss = String.valueOf(funcionario.getTxINSS()*100) + "%";
		
		//Variável temporária para avaliar a txIR e tranformá-la em String.
		String txIR;		
		txIR = String.valueOf(funcionario.getTxIR()*100) + "%";
		
		//Variável para formatar os valores para decimal.
		NumberFormat df = NumberFormat.getCurrencyInstance();
	
		//Verificação se hoje é aniversário do funcionário
		Date d = new Date();
		String mensagem = new String();
		if (d.getDate() == funcionario.getDataNascimento().getDate() && d.getMonth() == funcionario.getDataNascimento().getMonth()){
			mensagem = "Parabéns, hoje é seu aniversário!!";
		}
		else if (d.getMonth() > funcionario.getDataNascimento().getMonth()){
			mensagem = "Seu anivesário já passou!!";
		}
		else if (d.getMonth() < funcionario.getDataNascimento().getMonth()){
			int dias = funcionario.getDataNascimento().getDate() - d.getDate();
			int mes = (funcionario.getDataNascimento().getMonth() - d.getMonth())*30;
			mensagem = "Faltam " + (mes + dias) + " para seu aniversário!";
		}
		else if (d.getMonth() == funcionario.getDataNascimento().getMonth()){
			int dias = funcionario.getDataNascimento().getDate() - d.getDate();
			mensagem = "Seu anivesário é este mês!! Faltam " + dias + " dias.";
		}
	    System.out.println(
	    		"----------------------------------" +
			    "\nDados do Funcionário" +
			    "\n----------------------------------" +
				"\nNome = " + funcionario.getNome() + 
				"\nSexo = " + funcionario.getSexo() +  
				"\nCPF = " + funcionario.getCpf() + 
				"\nData de Nascimento = " + DateFormat.getDateInstance().format(funcionario.getDataNascimento()) +
				"\nAviso = " + mensagem +
				"\n----------------------------------" +
				"\nSalário Bruto            = " + df.format(funcionario.salarioBruto()) +
				"\nINSS (" + txInss + ")             =  " + df.format(funcionario.getValorINSS()) +
				"\n                     -------------" +
				"\nSalário Base IR          = " + df.format(funcionario.getSalarioLiqINSS()) +
				"\nIR (" + txIR + ")               =  " + df.format(funcionario.getValorIR()) +
				"\n                     -------------" +
				"\nSalário Líquido          = " + df.format(funcionario.getSalarioLiquido()) +
				"\n----------------------------------"+
				"\nEndereço: " + funcionario.getEndereco().getRua() + " Numero: " + funcionario.getEndereco().getNumero()
	    		);
	  }
	  
	}//Telafuncionario
