package dominio;

public class FuncionarioHorista extends FuncionarioAbstrato{

	private float horasTrabalhadas = 50;
	private float salarioPorHora = 200;
	
	//Definindo salarioBruto
	public float salarioBruto() {
		
		float salario =  this.getHorasTrabalhadas() * this.getSalarioPorHora();
		return salario;
	}
	
	public float getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(float horasTrabalhadas) {
		if (horasTrabalhadas >=0)
			this.horasTrabalhadas = horasTrabalhadas;
		else
			throw new RuntimeException("Erro: Horas trabalhadas devem ser igual ou maior do que zero.");
	}
	public float getSalarioPorHora() {
		return salarioPorHora;
	}
	public void setSalarioPorHora(float salarioPorHora) {
		if (salarioPorHora >=0)
			this.salarioPorHora = salarioPorHora;
		else
			throw new RuntimeException("Erro: Salário por hora deve ser igual ou maior do que zero.");
	}
	
	
}
