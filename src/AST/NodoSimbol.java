package AST;

public class NodoSimbol extends NodoBase
{
	
	private NodoBase obj_1;
	private NodoBase obj_2; 
	private  String tipo;
	
	
	
	
	public NodoSimbol()
	{
		
	}
	
	public NodoSimbol(String tipo)
	{
		this.tipo = tipo;
	}
	
	public void set_valor1(NodoBase obj)
	{
		this.obj_1 =obj;
	}
	
	public void set_valor2(NodoBase obj)
	{
		this.obj_2 =obj;
	}
	
	public String get_Tipo(){
		
		return this.tipo;
	}
	
	public NodoBase get_HI(){
		
		return this.obj_1;
	}

	public NodoBase get_HD(){
	
	return this.obj_2;
	}

}