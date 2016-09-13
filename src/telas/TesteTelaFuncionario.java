package telas;

import dominio.Funcionario;

public class TesteTelaFuncionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  
		Funcionario f1 = new Funcionario();
		Funcionario f2 = new Funcionario();
	    
	    TelaFuncionario tela = new TelaFuncionario(f1);

	    tela.leAtributos();
	    tela.imprime();

	    tela.setFuncionario(f2);
	    tela.leAtributos();
	    tela.imprime();
	    
	    tela.setFuncionario(f1);
	    tela.imprime();
	    
	    System.out.println(f1.equals(f2));
	}

}
