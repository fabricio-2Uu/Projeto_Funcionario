
public class Endereco {

  private String rua;
  private int numero;
  
  	  public Endereco(String rua, int numero) {
	    this.setRua(rua);
	    this.setNumero(numero);
	  }

	  public String getRua() {
	    return rua;
	  }
	  
	  public Endereco(){
		  
	  }
	  public void setRua(String rua) {
	    if (rua==null) {
	      //Lancando um excecao.
	      throw new RuntimeException("Erro: rua = null");
	    }
	    else if (rua.equals("")) {
	      //Lancando um excecao.
	      throw new RuntimeException("Erro: rua = \"\"");
	    }
	    else {
	      this.rua = rua;
	    }
	  }

	  public int getNumero() {
	    return numero;
	  }

	  public void setNumero(int numero) {
	    this.numero = numero;
	  }

	  @Override
	  public String toString() {
	    return "Endereco [rua=" + rua + ", numero=" + numero + "]";
	  }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (numero != other.numero)
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}
}
