package AST;

public class NodoOperacionLogica extends NodoBase {

	
	private String tipo;
	private NodoBase nodoIzq;
	private NodoBase nodoDer;
	
	public  NodoOperacionLogica(){}
	
	
	public  NodoOperacionLogica(String tipo)
	{
		this.tipo = tipo;
	}
	
	public void set_Izq(NodoBase nodo)
	{
		this.nodoIzq = nodo;
	}
	
	public void set_Der(NodoBase nodo)
	{
		this.nodoDer = nodo;
	}
	//
	public NodoBase get_HI(){
		
		return this.nodoIzq;
	}

	public NodoBase get_HD(){
	
	return this.nodoDer;
	}
	
	public String get_Tipo(){
		
		return this.tipo;
		}
	//
	
	
}
