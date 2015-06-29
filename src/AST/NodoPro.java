package AST;

public class NodoPro extends NodoBase {

	private NodoBase Tabla;
	private NodoBase Arg;
	
	
	public NodoPro()
	{
		
	}
	
	
	public NodoBase get_Args( )
	{
		return this.Arg;
	}
	
	public NodoBase get_Tabla()
	{
		return this.Tabla;
	}
	
	public NodoPro(NodoBase tabla, NodoBase Arg)
	{
		this.Tabla = tabla;
		this.Arg = Arg;
		
	}
	
	
	
}
