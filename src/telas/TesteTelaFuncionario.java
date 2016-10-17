package telas;

import dominio.FuncionarioComissionado;
import dominio.FuncionarioFixo;
import dominio.FuncionarioHorista;

public class TesteTelaFuncionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  
		FuncionarioFixo f1 = new FuncionarioFixo();
		f1.setSalario(5000);
		FuncionarioHorista f2 = new FuncionarioHorista();
		FuncionarioComissionado f3 = new FuncionarioComissionado();
	    
	    TelaFuncionario tela = new TelaFuncionario(f1);
	    
	    //tela.leTetoINSS();
	    tela.leAtributos();
	    tela.imprime();

	    tela.setFuncionario(f2);
	    tela.leAtributos();
	    tela.imprime();
	    
	    tela.setFuncionario(f3);
	    tela.leAtributos();;
	    tela.imprime();
	    
	    System.out.println(f1.equals(f2));
	}

}
