package AST;

public class NodoOperacion extends NodoBase{

	private String tipo;
	private NodoBase Tabla_Izq;
	private NodoBase Tabla_Der;
	
	
	
	
	public NodoOperacion (String tipo)
	{
		this.tipo = tipo;
	}
	
	
	
	public void Set_Izq(NodoBase Tabla_Izq)
	{
		this.Tabla_Izq = Tabla_Izq;
	}
	
	
	
	public void Set_Der(NodoBase Tabla_Der)
	{
		this.Tabla_Der = Tabla_Der;
	}
	
	
	public  Object Get_tablaIzq()
	{
		
		return Tabla_Izq;
		
		
		
	}
	
	
	public  Object Get_tablaDer()
	{
		
			return Tabla_Der;
		
		
		
	}
	
	public String Get_tipo()
	{
		return this.tipo;
	}
	
	
}
