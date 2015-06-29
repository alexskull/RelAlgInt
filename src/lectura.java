import java.awt.List;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.google.common.collect.Table;

import AST.*;
import Guava.*;


public class lectura {

	private Metodo_Guava Obj_guava;
	private static tabla tabla_simbolos ;
	private static int tabla_simbolos_cant ;
	
	
	
	public lectura() {
	
		tabla_simbolos = new tabla();
		tabla_simbolos_cant =0;
		this.Obj_guava = new Metodo_Guava();
	
	}
	
	
	public lectura(NodoBase lectura) throws ParseException
	{
		
		
		tabla_simbolos = new tabla();
		tabla_simbolos_cant =0;
		
		this.Obj_guava = new Metodo_Guava();
		
		
		tabla tabla_final = this.recorrido(lectura);
		
		
		
		
		if(tabla_final != null)
		//	System.out.print("adsa");
			this.Obj_guava.imprimir_tabfinal_tabla(tabla_final);
		else 
			System.out.println("hay error en la ejecucion");
		
		System.out.print("termino la ejecucion");
		
	}
	
	
	//DANIEL
	
	public ArrayList<String> Argumentos_anidados(NodoBase raiz)
	{
		
		NodoPro temp = (NodoPro) raiz;
		
		
		ArrayList<String> argumentos = new ArrayList<String>();
		
 
		      
		if(temp.get_Args() instanceof NodoArgumento)
		{
					
			NodoArgumento arg = (NodoArgumento) temp.get_Args();
					
					
			do
			{
			
				String  resul =  new String();
				
				if(arg.get_alias() != null && !arg.get_alias().isEmpty())
				{
					
					if(Belong_tabla(arg.get_nombre(), arg.get_alias()))
					{
						argumentos.add(arg.get_nombre());
					}
					else
					{
						System.out.println("ERROR COLUMNA ALIAS");
						return null;
					}
					
				}
				else
				{
					argumentos.add(arg.get_nombre());
				}		
				arg = arg.get_hijo();
						
			}
			while(arg != null);

		return argumentos;			
			
		}
	
		return null;
		
	}
		
	public tabla Nodo_Pro_Basic(NodoBase raiz)
	{
		
		NodoPro temp = (NodoPro) raiz;
		
		
		ArrayList<String> argumentos = new ArrayList<String>();
		
		
		
		String alias_tabla  = new String();
		String nomb = new String();
		
		
		boolean flag_alias = false;

		
		
		
		if(temp.get_Tabla() instanceof NodoTabla)
		{
			NodoTabla tab = (NodoTabla) temp.get_Tabla();
		
			nomb = tab.Get_tabla();
			
		      if(tab.Get_alias() != null && !tab.Get_alias().equals(""))
		      {
		    	  flag_alias = true;
		    	  alias_tabla = tab.Get_alias();
		      }
		      
		      
		      
		      
		      if(temp.get_Args() instanceof NodoArgumento)
				{
					NodoArgumento arg = (NodoArgumento) temp.get_Args();
					
					
					do{
						
						

						if ( arg.get_alias() == null  ||  (flag_alias  && arg.get_alias().equals(alias_tabla)) )
						{
							
							argumentos.add(arg.get_nombre());
						
						}
						else
						{
							System.out.println("Error proyecion alias columna "+arg.get_nombre()+" alias: "+arg.get_alias());
							
							return new tabla().Set_exist();
						
						}
							
						
						arg = arg.get_hijo();
						
					}while(arg != null);

					//PRO a.nombre_sucursal (asdasdaa  a);
					
					tabla new_tabla =Obj_guava.proyeccion(nomb, argumentos);
					
	
					
					
					return new_tabla;			
					
					
			
				}
				else
				{
					System.out.println("Error falta de argumentos");
					
					
					return new tabla().Set_exist();
				}

		      
		      
		      
		}
		return new tabla().Set_exist();
		
	}
		
	public tabla Nodo_sent3(NodoBase raiz)
	{
		NodoOperacion nodo_origen = (NodoOperacion) raiz;
		
		String tablaIzq = new String();
		String tablaDer = new String();
		
		
		if(nodo_origen.Get_tablaIzq() instanceof NodoTabla)
		{
			tablaIzq = ((NodoTabla) nodo_origen.Get_tablaIzq()).Get_tabla();
		}
		else
		{
			System.out.println("ERROR  TABLA ES COMPUESTA");
			return null;
		}
		
		if(nodo_origen.Get_tablaDer() instanceof NodoTabla)
		{
			tablaDer = ((NodoTabla) nodo_origen.Get_tablaDer()).Get_tabla();
		}
		else 
		{
			
			System.out.println("ERROR  TABLA ES COMPUESTA");
			return null;
		}
		
		
		if(nodo_origen.Get_tipo().equals("Proc"))
		{
			
			 return this.Obj_guava.cartesiano(tablaIzq, tablaDer);
		}
		else if (nodo_origen.Get_tipo().equals("Uni"))
		{
			return this.Obj_guava.union_tabla(tablaIzq, tablaDer);
		}
		else if (nodo_origen.Get_tipo().equals("Int"))
		{
			return this.Obj_guava.interseccion(tablaIzq, tablaDer);
		}
		else if (nodo_origen.Get_tipo().equals("Dif"))
		{
			return this.Obj_guava.diferencia(tablaIzq, tablaDer);
		}
		
		System.out.print("error general ");
		return null;
	}
	
	public tabla  Nodo_sent3(tabla der, tabla izq , String tipo)
	{
		
		if(tipo.equals("Proc"))
		{
			
			return this.Obj_guava.cartesiano(izq, der);
		}
		else if (tipo.equals("Uni"))
		{
			return this.Obj_guava.union_tabla(izq, der);
		}
		else if (tipo.equals("Int"))
		{
			return this.Obj_guava.interseccion(izq, der);
		}
		else if (tipo.equals("Dif"))
		{
			return this.Obj_guava.diferencia(izq, der);
		}
		
		System.out.print("error general ");
		return null;

		
		
	}
	
	public tabla  Nodo_sent3(tabla tab_s, String tab_t , String tipo)
	{
		
		if(tipo.equals("Proc"))
		{
			
			return this.Obj_guava.cartesiano(tab_s, tab_t);
		}
		else if (tipo.equals("Uni"))
		{
			
			return this.Obj_guava.union_tabla(tab_s, tab_t);
		
		}
		else if (tipo.equals("Int"))
		{
			
			return this.Obj_guava.interseccion(tab_s, tab_t);
			
		}
		else if (tipo.equals("Dif"))
		{
			
			return this.Obj_guava.diferencia(tab_s, tab_t);
			//return null;
		}
		
		System.out.print("error general ");
		return null;

		
		
	}
	
	
	
	
	
	
	public static ArrayList<String> Get_aliasto_listColumn(String alias)
	{
		
		
		
		for(Object fil :tabla_simbolos.getGuava_table().rowKeySet())
		{
			String alias_t  = tabla_simbolos.getGuava_table().get(fil, "alias").toString();
			
			
			
			if(alias_t.equals(alias))
			{
				
				return ( (ArrayList<String>) tabla_simbolos.getGuava_table().get(fil, "List_colm"));
			}
		}
		System.out.print("ERROR  alias no corresponde con la tabla de simbolos");
		return null;
	}
	
	
	
	public static boolean Belong_tabla(String column, String alias)
	{
		ArrayList<String> list_co = Get_aliasto_listColumn(alias);
		
		if(list_co != null &&list_co.contains(column))
		{
			return true;
		}
		else
			return false;
		
	}
	
	
	
	public void saveInformation( String alias, String tabla)
	{
		
		if(tabla_simbolos.getGuava_table().column("alias").containsValue(alias))
		{
			System.out.println("ERROR alias ya registrado");
			return ;
		}
		else
		{
		
			tabla_simbolos.getGuava_table().put(tabla_simbolos_cant, "alias", alias);
	
			Table tab =(new Metodo_Guava()).tranfor(tabla);
			
			ArrayList<String> list_col =  new ArrayList<String>();
			
			for(Object col : tab.columnKeySet())
			{
				list_col.add( col.toString());
			}
			
			tabla_simbolos.getGuava_table().put(tabla_simbolos_cant, "List_colm", list_col);
			
			tabla_simbolos_cant++;
	
			//(new Metodo_Guava()).imprimir_tabfinal_tabla(tabla_simbolos.getGuava_table());
			
			
		}
		
		
		
		
	}
	//DANIEL
	
	
	//domingo
	
	


	//domingo

	public void Filtro(Object Filtro, String Tabla) throws ParseException {

		String Simbolo;
		Metodo_Guava gv = new Metodo_Guava();

		if (Filtro instanceof NodoOperacionLogica) {

			Object HjIzq = ((NodoOperacionLogica) (Filtro)).get_HI();
			Object HjDer = ((NodoOperacionLogica) (Filtro)).get_HD();

			Filtro(HjIzq, Tabla);
			Filtro(HjDer, Tabla);

			try {
				((NodoOperacionLogica) Filtro).Set_tabla_Gauva(gv.sel_caso1(
						((NodoBase) (HjIzq)).Get_tabla_Guava(),
						((NodoOperacionLogica) (Filtro)).get_Tipo(),
						((NodoBase) (HjDer)).Get_tabla_Guava()));
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		if (Filtro instanceof NodoSimbol) {

			Object HijoIzq = ((NodoSimbol) (Filtro)).get_HI();
			Object HijoDer = ((NodoSimbol) (Filtro)).get_HD();

			Simbolo = ((NodoSimbol) (Filtro)).get_Tipo();

			
			try {
				if (HijoIzq instanceof NodoColumna && HijoDer instanceof NodoColumna) {
					
					
					if(((NodoColumna) (HijoIzq)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias())){
							Error(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias());
							return;
							}
					}
					if(((NodoColumna) (HijoDer)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias())){
							Error(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias());
							return;
							}							
					}

					if(VerificarColumnas(((NodoColumna) (HijoIzq)).get_nombre(), gv.seleccion_simple(Tabla))){ 
						if(VerificarColumnas(((NodoColumna) (HijoDer)).get_nombre(), gv.seleccion_simple(Tabla))){
								((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso3(Tabla,
										((NodoColumna) (HijoIzq)).get_nombre(), Simbolo,
										((NodoColumna) (HijoDer)).get_nombre()));
								}else{
									Error(2, Tabla,((NodoColumna) (HijoDer)).get_nombre());
								}
					}else{
						Error(2, Tabla,((NodoColumna) (HijoIzq)).get_nombre());
					}
					
				}

				if (HijoIzq instanceof NodoColumna && HijoDer instanceof NodoValor) {
					
					if(((NodoColumna) (HijoIzq)).get_alias()!=null){
						
						String a,b;
						a=((NodoColumna) (HijoIzq)).get_nombre();
						b=((NodoColumna) (HijoIzq)).get_alias();
						
						if(!Belong_tabla(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias())){
							Error(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias());
							return;
							}
					}
					
					if(VerificarColumnas(((NodoColumna) (HijoIzq)).get_nombre(), gv.seleccion_simple(Tabla))){
						if (((NodoValor) (HijoDer)).getValor() instanceof String)
							((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2(Tabla,
									((NodoColumna) (HijoIzq)).get_nombre(), Simbolo,
									(String) ((NodoValor) (HijoDer)).getValor()));

						if (((NodoValor) (HijoDer)).getValor() instanceof Integer)
							((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2(Tabla,
									((NodoColumna) (HijoIzq)).get_nombre(), Simbolo,
									Integer.toString((Integer) ((NodoValor) (HijoDer))
											.getValor())));
					}else{
						Error(2, Tabla,((NodoColumna) (HijoIzq)).get_nombre());
					}
				}

				if (HijoIzq instanceof NodoValor && HijoDer instanceof NodoColumna) {

					if(((NodoColumna) (HijoDer)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias())){
							Error(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias());
							return;
							}							
					}
					
					if(VerificarColumnas(((NodoColumna) (HijoDer)).get_nombre(), gv.seleccion_simple(Tabla))){
					if (((NodoValor) (HijoIzq)).getValor() instanceof String)

						((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2(Tabla,
								((NodoColumna) (HijoDer)).get_nombre(), Simbolo,
								(String) ((NodoValor) (HijoIzq)).getValor()));

					if (((NodoValor) (HijoIzq)).getValor() instanceof Integer)

						((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2(Tabla,
								((NodoColumna) (HijoDer)).get_nombre(), Simbolo,
								Integer.toString((Integer) ((NodoValor) (HijoIzq))
										.getValor())));

					}else{
						Error(2, Tabla,((NodoColumna) (HijoDer)).get_nombre());
					}
				}
			} catch (Exception e) {
				Error(3, Tabla);
			}

		}

	}
	
	public void Filtro(Object Filtro, tabla Tabla) throws ParseException
	{

		String Simbolo;
		Metodo_Guava gv = new Metodo_Guava();

		if (Filtro instanceof NodoOperacionLogica) {

			Object HjIzq = ((NodoOperacionLogica) (Filtro)).get_HI();
			Object HjDer = ((NodoOperacionLogica) (Filtro)).get_HD();

			Filtro(HjIzq, Tabla);
			Filtro(HjDer, Tabla);

			try {
				((NodoOperacionLogica) Filtro).Set_tabla_Gauva(gv.sel_caso1(
						((NodoBase) (HjIzq)).Get_tabla_Guava(),
						((NodoOperacionLogica) (Filtro)).get_Tipo(),
						((NodoBase) (HjDer)).Get_tabla_Guava()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (Filtro instanceof NodoSimbol) {

			Object HijoIzq = ((NodoSimbol) (Filtro)).get_HI();
			Object HijoDer = ((NodoSimbol) (Filtro)).get_HD();

			Simbolo = ((NodoSimbol) (Filtro)).get_Tipo();
			
			

			try {
				if (HijoIzq instanceof NodoColumna && HijoDer instanceof NodoColumna) {
					
					if(((NodoColumna) (HijoIzq)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias())){
							Error(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias());
							return;
							}
					}
					if(((NodoColumna) (HijoDer)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias())){
							Error(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias());
							return;
							}							
					}
					
					if(VerificarColumnas(((NodoColumna) (HijoIzq)).get_nombre(), Tabla)){ 
						if(VerificarColumnas(((NodoColumna) (HijoDer)).get_nombre(), Tabla)){
								((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso3(Tabla,
										((NodoColumna) (HijoIzq)).get_nombre(), Simbolo,
										((NodoColumna) (HijoDer)).get_nombre()));
								}else{
									Error(2, Tabla.getNombre(),((NodoColumna) (HijoDer)).get_nombre());
								}
					}else{
						Error(2, Tabla.getNombre(),((NodoColumna) (HijoIzq)).get_nombre());
					}
					
				}

				if (HijoIzq instanceof NodoColumna && HijoDer instanceof NodoValor) {
					
					if(((NodoColumna) (HijoIzq)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias())){
							Error(((NodoColumna) (HijoIzq)).get_nombre(), ((NodoColumna) (HijoIzq)).get_alias());
							return;
							}
					}
					
					if(VerificarColumnas(((NodoColumna) (HijoIzq)).get_nombre(), Tabla)){
						
						if (((NodoValor) (HijoDer)).getValor() instanceof String)
							((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2Tabla(Tabla,
									((NodoColumna) (HijoIzq)).get_nombre(), Simbolo,
									(String) ((NodoValor) (HijoDer)).getValor()));

						if (((NodoValor) (HijoDer)).getValor() instanceof Integer)
							((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2Tabla(Tabla,
									((NodoColumna) (HijoIzq)).get_nombre(), Simbolo,
									Integer.toString((Integer) ((NodoValor) (HijoDer))
											.getValor())));

					}else{
						
						Error(2, Tabla.getNombre(),((NodoColumna) (HijoIzq)).get_nombre());
						
					}
				}

				if (HijoIzq instanceof NodoValor && HijoDer instanceof NodoColumna) {

					if(((NodoColumna) (HijoDer)).get_alias()!=null){
						if(!Belong_tabla(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias())){
							Error(((NodoColumna) (HijoDer)).get_nombre(), ((NodoColumna) (HijoDer)).get_alias());
							return;
							}							
					}
					
					if(VerificarColumnas(((NodoColumna) (HijoDer)).get_nombre(), Tabla)){
					
					if (((NodoValor) (HijoIzq)).getValor() instanceof String)

						((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2Tabla(Tabla,
								((NodoColumna) (HijoDer)).get_nombre(), Simbolo,
								(String) ((NodoValor) (HijoIzq)).getValor()));

					if (((NodoValor) (HijoIzq)).getValor() instanceof Integer)

						((NodoSimbol) Filtro).Set_tabla_Gauva(gv.sel_caso2Tabla(Tabla,
								((NodoColumna) (HijoDer)).get_nombre(), Simbolo,
								Integer.toString((Integer) ((NodoValor) (HijoIzq))
										.getValor())));

					}else{
						
						Error(2, Tabla.getNombre(),((NodoColumna) (HijoDer)).get_nombre());
						
					}
				}
			} catch (Exception e) {
				Error(3, Tabla.getNombre());
			}

		}

	}

//Nuevas
	public int verificar_tablaBase(String NombreTabla){
		
		File pepe = new File("./src/carpeta");
        String[] ficheros = pepe.list();


        if (ficheros.length == 0) {
            System.out.println("No hay ficheros en el directorio especificado");
            return 1;
        } else {
            for (int x = 0; x < ficheros.length; x++) {
                ficheros[x] = ficheros[x].replace(".txt", "");
                if(ficheros[x].compareTo(NombreTabla)==0)
                	return 2;
            }
        }
		return 0;
	}
	
	public boolean Error(String NombreTabla){
		
		boolean a=true;
		switch (verificar_tablaBase(NombreTabla)) {
		case 0:
			InvocarGrafico("Error 0x5486783 la Tabla "+ NombreTabla +" No Existe");
			a=false;
			break;
		case 1:
			InvocarGrafico("Error 1x5486783 No hay ficheros en el Directorio");
			a=false;
			break;

		}
		return a;
	}
	
	public void Error(int Error, String Tabla, String Columna){
		
		switch (Error) {
		case 2:
			InvocarGrafico("Error 2x5486783 la Columna "+ Columna +" no existe en la Tabla: "+Tabla);
			break;

		}
	}
	
	public void Error(int Error, String Tabla){
		
		switch (Error) {
		case 3:
			InvocarGrafico("Error 3x5486783 de comparacion de Tipos");
			break;
		}
	}
	public void Error(String Columna, String Alias){
		InvocarGrafico("\nError 4x5486783 El Alias "+Alias+" no esta registrado para la Columna "+Columna);				
	}
	
	public boolean VerificarColumnas(String Nombre,	tabla Table){
		for (String o: Table.getColumnas()){
			if(o.equals(Nombre))
				return true;
		}		
		return false;
		
	}
	
	public void InvocarGrafico(String Mensaje){
		
		interfaz_tabla Inter = new interfaz_tabla();
		
		Inter.error(Mensaje);
		
	}
//Nuevas	
	
// domingo
	
	// domingo

	
	

	
	
	
	public tabla recorrido(NodoBase lectura) throws ParseException
	{
		
		if (lectura instanceof NodoPro)
		{
			NodoPro temp = (NodoPro) lectura;
			
			if(temp.get_Tabla() instanceof NodoTabla)
			{
				
				
				if(((NodoTabla)temp.get_Tabla()).Get_alias() != null  )
				{
					String alias =((NodoTabla)temp.get_Tabla()).Get_alias();
					String tabla =((NodoTabla)temp.get_Tabla()).Get_tabla();
					
					saveInformation(alias, tabla);
				}
				
				
				
				return this.Nodo_Pro_Basic(lectura);
			}
			else
			{
				
				
				tabla new_tabla = recorrido(     ((NodoPro) lectura).get_Tabla()   );
				ArrayList<String > args =this.Argumentos_anidados(lectura);
				
				
				
				
				
				
				 return this.Obj_guava.proyeccion_tabla(null, new_tabla.getGuava_table(),args );
				 
				
			}
			
		}
		
		
		else if(lectura instanceof NodoSel)
		{
			NodoSel temp = (NodoSel) lectura;
			String Tabla = null;
			Metodo_Guava gv = new Metodo_Guava();
			String NombreTabla;
			
			if (temp.get_Filtro() == null) { // Cuando el Filtro es Nulo

				if (temp.get_Tabla() instanceof NodoTabla) {
					// Modifique
					NombreTabla = ((NodoTabla)(temp.get_Tabla())).Get_tabla();
					if(Error(NombreTabla)){
					// Modifique
					
					if(((NodoTabla)temp.get_Tabla()).Get_alias() != null  )
					{
						String alias =((NodoTabla)temp.get_Tabla()).Get_alias();
						String tabla =((NodoTabla)temp.get_Tabla()).Get_tabla();
						
						saveInformation(alias, tabla);
					}
					
					
					
					lectura.Set_tabla_Gauva(gv.seleccion_simple(((NodoTabla)(temp.get_Tabla())).Get_tabla()));
					}
					return lectura.Get_tabla_Guava();
					
					
				}else{
					
					
					tabla tip_2 = recorrido(temp.get_Tabla());
				
					lectura.Set_tabla_Gauva(tip_2);
					
					return lectura.Get_tabla_Guava();
					
				}
				
			} else { // Cuando el Filtro no es Nulo

				Object Filtro = temp.get_Filtro();
			

				if (temp.get_Tabla() instanceof NodoTabla) {

					
					// Modifique
					NombreTabla = ((NodoTabla)(temp.get_Tabla())).Get_tabla();
					if(Error(NombreTabla)){//Modifique
					// Modifique	
					
					if(((NodoTabla)temp.get_Tabla()).Get_alias() != null  )
					{
						String alias =((NodoTabla)temp.get_Tabla()).Get_alias();
						String tabla =((NodoTabla)temp.get_Tabla()).Get_tabla();
						
						saveInformation(alias, tabla);
					}
					
					
					Tabla = (String)((NodoTabla)temp.get_Tabla()).Get_tabla();
					
					Filtro(Filtro, Tabla);
					
					// Al salir Filtro tiene La tabla Temporal de Guava en teoria
					lectura.Set_tabla_Gauva(((NodoBase) (Filtro)).Get_tabla_Guava());
					
					}//Modifique
					return lectura.Get_tabla_Guava();

				}else{
					//Que pasa si tengo una tabla generada
					
					//NodoTabla tab = (NodoTabla) temp.get_Tabla();
					
					tabla tab = recorrido(temp.get_Tabla());
					
					
					Filtro(Filtro, tab);
					// Al salir Filtro tiene La tabla Temporal de Guava en teoria
					
					
					lectura.Set_tabla_Gauva(((NodoBase) (Filtro)).Get_tabla_Guava());
					return lectura.Get_tabla_Guava();
					
				}

				
			}
			
			
		}
		else if (lectura instanceof NodoOperacion)
		{
		
			NodoOperacion raiz = (NodoOperacion) lectura;
			
			
			
			if(raiz.Get_tablaDer() instanceof NodoTabla && raiz.Get_tablaIzq() instanceof NodoTabla )
			{
				if(((NodoTabla)raiz.Get_tablaDer()).Get_alias() != null  )
				{
					String alias = ((NodoTabla)raiz.Get_tablaDer()).Get_alias();
					String tabla = ((NodoTabla)raiz.Get_tablaDer()).Get_tabla();
					
					saveInformation(alias, tabla);
				}
				
				
				if(((NodoTabla)raiz.Get_tablaIzq()).Get_alias() != null  )
				{
					String alias = ((NodoTabla)raiz.Get_tablaIzq()).Get_alias();
					String tabla = ((NodoTabla)raiz.Get_tablaIzq()).Get_tabla();
					
					saveInformation(alias, tabla);
				}
				
				
				return this.Nodo_sent3(raiz);
			}
			else 
			{
				
				tabla tabla_der = null;
				tabla tabla_izq = null;
				boolean flag_izq = false;
				boolean flag_der = false;
				
				if(!(raiz.Get_tablaDer() instanceof NodoTabla ))
				{
				    NodoBase obj = (NodoBase) raiz.Get_tablaDer();
				    
					tabla_der = recorrido(obj);
					
					flag_der = true;
					
				}
				
				if(!(raiz.Get_tablaIzq() instanceof NodoTabla ))
				{
				    NodoBase obj = (NodoBase) raiz.Get_tablaIzq();
				    
				
				    tabla_izq = recorrido(obj);
				/*
			    (new Metodo_Guava()).imprimir_tabfinal_tabla(tabla_izq);
				    System.out.println(".");
				    System.out.println(".");
				    System.out.println(".");
				    System.out.println(".");
			    */
					flag_izq = true;
				}
				
				tabla olp = null;
				if(flag_der && flag_izq)
				{
					
					return  Nodo_sent3(tabla_der, tabla_izq, raiz.Get_tipo());
				}
				else if (!flag_der)
				{
					NodoTabla obj =  (NodoTabla) raiz.Get_tablaDer();
					
					
					
					if(obj.Get_alias() != null  )
					{
						String alias = obj.Get_alias();
						String tabla = obj.Get_tabla();
						
						saveInformation(alias, tabla);
					}
					
					
					return  Nodo_sent3(tabla_izq, obj.Get_tabla(), raiz.Get_tipo());
				}
				else 
				{
					NodoTabla obj =  (NodoTabla) raiz.Get_tablaIzq();
					
					
					if(obj.Get_alias() != null  )
					{
						String alias = obj.Get_alias();
						String tabla = obj.Get_tabla();
						
						saveInformation(alias, tabla);
					}
					
					
					
					return Nodo_sent3(tabla_der, obj.Get_tabla(), raiz.Get_tipo());
				}
				
				
			}
			
			
		}
		else
		{
			System.out.print("error no reconocido tipo de nodo");
			return null;
		}
		
		
	}
	
	
}