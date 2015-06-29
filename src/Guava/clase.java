package Guava;



import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class clase {

	
	  static tabla[] lista_tablas; //rafael
	   String[] ficheros; //rafael

	
    private static void cargar(tabla[] lista_tablas) {

        for (int x = 0; x < lista_tablas.length; x++) {

            String[] pre_proc1 = null; //auxiliar	
            String[] columnas = null;
            String[] tipos = null;
            String[][] datos = null;

            int control = 0;
            int cant_col;
            String[] aux1;

            try {
                BufferedReader reader = new BufferedReader(new FileReader("./carpeta/" + lista_tablas[x].getNombre()));
                //System.out.println(lista_tablas[x].getNombre());
                String line = null;
                if ((line = reader.readLine()) != null) {
                    pre_proc1 = line.split(";");
                }

                cant_col = pre_proc1.length;
                columnas = new String[cant_col];
                tipos = new String[cant_col];
                //	String aux1[];
                for (int i = 0; i < pre_proc1.length; i++) {
                    aux1 = pre_proc1[i].split("\\(");
                    for (int j = 0; j < aux1.length; j++) {
                        if (j % 2 == 0) {
                            columnas[control] = aux1[j];
                            if (control < cant_col) {
                                control++;
                            }
                        }
                    }

                }

                control = 0;
                String aux2[];
                for (int i = 0; i < pre_proc1.length; i++) {
                    aux2 = pre_proc1[i].split("\\(");
                    for (int j = 0; j < aux2.length; j++) {
                        if (((j) % 2) == 1) {
                            tipos[control] = (aux2[j].subSequence(0, aux2[j].length() - 1)).toString();
                            if (control < cant_col) {
                                control++;
                            }
                        }
                    }
                }

                int cant_filas = 0;
                while ((line = reader.readLine()) != null) {
                    cant_filas++;
                }
                reader.close();

                datos = new String[cant_filas][cant_col];
                reader = new BufferedReader(new FileReader("./carpeta/" + lista_tablas[x].getNombre()));
                line = reader.readLine();
                String[] tok;
                control = 0;
                while ((line = reader.readLine()) != null) {
                    tok = line.split(";");
                    for (int i = 0; i < tok.length; i++) {
                        datos[control][i] = tok[i];
                    }
                    control++;
                }

                for (int i = 0; i < cant_filas; i++) {

                    for (int j = 0; j < cant_col; j++) {

                        lista_tablas[x].getGuava_table().put(i, columnas[j], new posicion(datos[i][j], tipos[j]));

                    }
                }

			//+" numero: "+lista_tablas[x].getGuava_table().rowMap().size()
                             /*      posicion pru=null;
                 System.out.println("");
                 System.out.println("tabla: "+lista_tablas[x].getNombre());
                 String a=null;
                 int b;
                 String c=null;
                                
                 for (int i = 0; i < cant_filas; i++) {
				
                 for (int j = 0; j < cant_col; j++) {
                                            
                 System.out.println("columna: "+columnas[j]);
                                            
                 pru=(posicion) lista_tablas[x].getGuava_table().get(i,columnas[j]);
                                            
                 if(pru.getTipo().equals("cadena")){
                 a=(String)pru.getElemento();
                 System.out.println("val: "+a);
                 }
                                            
                 else if(pru.getTipo().equals("entero")){
                 b=Integer.parseInt((String)pru.getElemento());
                 System.out.println("val: "+b);
                 }
                                            
                 else if(pru.getTipo().equals("fecha")){
                 c=(String)pru.getElemento();
                 System.out.println("val: "+c);
                 }
                 }
                               
                 }*/
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private static tabla proyectar(String nombre_tabla, ArrayList<String> colmunas, tabla[] lista_tablas) {

        int pos = buscar_num_tabla(nombre_tabla, lista_tablas);
        tabla nueva_tabla = new tabla(nombre_tabla);
       
        for (String object : colmunas) {
            int i=0;
            
            Collection<posicion> columnas_get = lista_tablas[pos].getGuava_table().column(object).values();

            for (posicion element : columnas_get) {
                
                nueva_tabla.getGuava_table().put(i, object, element);
                
            i++;
            }
        } 
        return  nueva_tabla;
    }

    private static int buscar_num_tabla(String nombre_tabla, tabla[] lista_tablas) {

        for (int i = 0; i < lista_tablas.length; i++) {

            if (lista_tablas[i].getNombre().equals(nombre_tabla)) {
                return i;
            }

        }

        return 0;
    }

    /*	RAFAEL	*/
    

    static boolean comparar (String tabl1, String tabla2) //rafael
    {
       boolean examen=false;
        int pos_1 = buscar_num_tabla(tabl1, lista_tablas);
        int pos_2 = buscar_num_tabla(tabla2, lista_tablas);
         Set<posicion> table1 = new HashSet<posicion>();
        table1 = ImmutableSet.copyOf(lista_tablas[pos_1].getGuava_table().values());
         System.out.println("columna1 "+lista_tablas[pos_1].getGuava_table().columnMap().size());
         System.out.println("columna1" +lista_tablas[pos_1].getGuava_table());
        Set<posicion> table2 = new HashSet<posicion>();
        table2 = ImmutableSet.copyOf(lista_tablas[pos_2].getGuava_table().values());
          System.out.println("columnas 2 "+lista_tablas[pos_2].getGuava_table().columnMap().size());

          ArrayList<String> column_a = new ArrayList<String>();
    	ArrayList<String> column_b = new ArrayList<String>();
        
        	for (Object elem :lista_tablas[pos_1].getGuava_table().columnKeySet())
    	{
    		column_a.add(elem.toString());
               
    	}
    	
    	for (Object elem :lista_tablas[pos_2].getGuava_table().columnKeySet())
    	{
    		column_b.add(elem.toString());;
    	}
        
        
           if(lista_tablas[pos_1].getGuava_table().columnMap().size()==lista_tablas[pos_2].getGuava_table().columnMap().size() && column_a.equals(column_b))
                      examen = true;
           return examen;
    }

    
   
    /*	RAFAEL	*/
    
}
