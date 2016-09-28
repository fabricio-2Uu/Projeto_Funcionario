package dominio;

public class FuncionarioComissionado extends FuncionarioAbstrato {

	private float salarioBase = 1000;
	private float valorVendas = 80000;
	private float porcentagemComissao = 0.10f;
	
	//Definindo salarioBruto
	public float salarioBruto() {
		float salario = this.getSalarioBase() + (this.getValorVendas() * this.getPorcentagemComissao());
		return salario;
	}

	public float getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(float salarioBase) {
		this.salarioBase = salarioBase;
	}

	public float getValorVendas() {
		return valorVendas;
	}

	public void setValorVendas(float valorVendas) {
		this.valorVendas = valorVendas;
	}

	public float getPorcentagemComissao() {
		return porcentagemComissao;
	}

	public void setPorcentagemComissao(float porcentagemComissao) {
		this.porcentagemComissao = porcentagemComissao;
	}
	
	
}
