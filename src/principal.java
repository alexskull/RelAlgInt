import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import AST.NodoTabla;
import Guava.Metodo_Guava;
import Guava.interfaz_tabla;
import Guava.tabla;


public class principal {

	
	public static void main(String[] args) throws Exception {
	


		
		
		parser analizador;
		analizador = new parser( new Yylex(System.in) );
		analizador.parse();
		
	/*	

		ArrayList<String> eq = new ArrayList<String>();
		
		Metodo_Guava a = new Metodo_Guava();
		
				
		tabla resul = a.union_tabla("A", "C");
		a.imprimir_tabfinal_tabla(resul);
*/
		
		
	}

}
