package AST;

public class NodoColumna extends NodoBase {
	
	private String Columna;
	private String Alias;
	
	
	public NodoColumna()
	{}
	
	
	
	
	public NodoColumna(String Columna)
	{
		this.Columna = Columna;
	}
	
	public NodoColumna(NodoColumna Columna)
	{
	

		this.Columna = Columna.Columna;
		this.Alias = Columna.Alias;
		
	}
	
	public NodoColumna(String alias,String Columna)
	{
		this.Alias = alias;
		this.Columna = Columna;
	}
	
	
	public String get_nombre()
	{
		return Columna;
	}
	
	
	public String get_alias ()
	{
		return Alias;
	}
}
