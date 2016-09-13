import java.util.Scanner;

import dominio.Endereco;
import dominio.Funcionario;

public class Testa_Funcionario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Digite o Nome: ");
		String nome = keyboard.nextLine();
		
		System.out.println("Digite o Sexo (M ou F): ");
		char sexo = keyboard.nextLine().charAt(0);
		
		System.out.println("Digite o CPF: ");
		String cpf = keyboard.nextLine();
		
		System.out.println("Digite o Salário: ");
		double salario = keyboard.nextDouble();
		keyboard.nextLine();
		
		System.out.println("Digite o Endereço: ");
		String rua = keyboard.nextLine();
		
		System.out.println("Digite o Número: ");
		int numero = keyboard.nextInt();
		
		keyboard.close();
		
		Endereco e = new Endereco(rua, numero);
		Funcionario Func1 = new Funcionario(nome, sexo, cpf, salario, e);
		Funcionario Func2 = new Funcionario("Jorge", 'M', "654.456.369-95", 3214.32, e);
		Funcionario Func3 = Func2;
		System.out.println(Func1.equals(Func2));
		System.out.println(Func1.equals(Func3));
		System.out.println(Func2.equals(Func3));
		System.out.println();
		
	}

}
