package AST;

public class NodoSel extends NodoBase{

	NodoBase Tabla;
	NodoBase Filtro;
	
	public NodoSel()
	{
		
	}
	
	
	public NodoSel(NodoBase Tabla)
	{
		this.Tabla = Tabla;
		this.Filtro = null;
	}
	
	public NodoSel(NodoBase Tabla, NodoBase Filtro)
	{
		this.Tabla = Tabla;
		this.Filtro = Filtro;
	}
	
	
	public NodoBase get_Tabla( )
	{
		return this.Tabla;
	}
	
	public NodoBase get_Filtro()
	{
		return this.Filtro;
	}
	
}