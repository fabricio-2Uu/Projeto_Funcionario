package dominio;

public class FuncionarioFixo extends FuncionarioAbstrato{

	private float salario = 5000;

	public float salarioBruto() {
		return salario;
	}

	public void setSalario(float salarioBruto) {
		if (salarioBruto >=0)
			this.salario = salarioBruto;
		else
			throw new RuntimeException("Erro: Salário deve ser igual ou maior do que zero.");
	}
	
}
