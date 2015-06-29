package AST;

public class NodoValor extends NodoBase {

	Object valor;
	int tipo;
	
	
	public NodoValor(Object valor, int tipo)
	{
		this.valor = valor;
		this.tipo = tipo;	
	}

//
	public Object getValor() {
		return valor;
	}

	public int getTipo() {
		return tipo;
	}
	
//
	
}