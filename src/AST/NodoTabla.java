package AST;

public class NodoTabla extends NodoBase
{
	private String alias;
	private String nombre;
	
	public NodoTabla()
	{
		
	}
	
	public NodoTabla(String nombre)
	{
		this.nombre = nombre;
	}
	
	public NodoTabla(String nombre, String alias)
	{
		this.nombre = nombre;
		this.alias = alias;
	}
	
	public String Get_tabla()
	{
		return nombre;
	}
	public String Get_alias()
	{
		return alias;
	}	
	
	
}
