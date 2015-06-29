package AST;

public class NodoArgumento extends NodoColumna
{
	
	
	private NodoArgumento son;
	
	
	
	
	public NodoArgumento()
	{
		
	}
	
	public NodoArgumento(NodoColumna a)
	{
		super(a);
		
	}

	public void set_hijo( NodoArgumento	 b)
	{
		this.son =b;
	}
	
	public NodoArgumento get_hijo()
	{
		return son;
	}
	
	
	
	   
}
