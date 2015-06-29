package Guava;


import com.google.common.collect.ImmutableSet;

import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box.Filler;

public class Metodo_Guava {

//------------------------ VARIABLES    
    private final tabla[] lista_tablas;

//------------------------ COSTRUCTORES
    public Metodo_Guava() {
        File pepe = new File("./src/carpeta");
          String[] ficheros = pepe.list();
          lista_tablas = new tabla[ficheros.length];

          if (ficheros.length == 0) {
              System.out.println("No hay ficheros en el directorio especificado");
          } else {
              for (int x = 0; x < ficheros.length; x++) {
                  lista_tablas[x] = new tabla(ficheros[x].replace(".txt", ""));
                  
              }
          }
          
          cargar(lista_tablas);
       
    }
    
    

//------------------------ METODOS PRINCIPALES
    
    
    
    public tabla proyeccion(String nombre_tabla, ArrayList <String> columnas ){
        
         int pos = buscar_num_tabla(nombre_tabla, this.lista_tablas);
         tabla nueva_tabla = new tabla(nombre_tabla);
       if(pos!=-1){
        for (String object : columnas) {
            int i=0;
            
            Collection<posicion> columnas_get = lista_tablas[pos].getGuava_table().column(object).values();

            for (posicion element : columnas_get) {
                
                nueva_tabla.getGuava_table().put(i, object, element);
                
            i++;
            }
        } 
    }
        return  nueva_tabla;
        
       
    }
    
    public tabla proyeccion_tabla(String nombre_tabla, Table tabla, ArrayList <String> columnas ){
        
         
         tabla nueva_tabla = new tabla(nombre_tabla);
       if(nueva_tabla!=tabla){
        for (String object : columnas) {
            int i=0;
            
            Collection<posicion> columnas_get = tabla.column(object).values();

            for (posicion element : columnas_get) {
                
                nueva_tabla.getGuava_table().put(i, object, element);
                
            i++;
            }
        } 
    }
        return  nueva_tabla;
    }
    
    //* domingo y yogo*//
    
    //Seleccion a partir de un string nombre de tabla
    public tabla seleccion_simple(String Nombre_tabla){
    
        int pos=buscar_num_tabla(Nombre_tabla, lista_tablas);
        if(pos!=-1){
        return lista_tablas[pos];
        }
        
        return null;
    }
    // si es seleccion de una tabla que ya existe llame al metodo imprimir_tabfinal 
    
    public tabla sel_caso2(String nombre_tabla,String columna,String operador,String argumento) throws ParseException{
    
        int pos=buscar_num_tabla(nombre_tabla, lista_tablas);
        if(pos!=-1){
        
            tabla temp=new tabla();
            int row=0,i=0;

            int [] colum_key=new int [  lista_tablas[pos].getGuava_table().rowKeySet().size()];
            for(Object o:lista_tablas[pos].getGuava_table().rowKeySet()){
                colum_key[i]=Integer.parseInt(o.toString());
                i++;}
            
            Collection<posicion> column=lista_tablas[pos].getGuava_table().column(columna).values();
           
            for(posicion obj:column){

              
                if(operador.equals("Eq"))
                		{
                    
                    if(obj.getTipo().equals("cadena")){
                        if(obj.getElemento().toString().equals(argumento))
                        {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                else if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())==Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                    
                else if(obj.getTipo().equals("fecha")){
                    
                SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 = StringtoDate(obj.getElemento().toString());		
						
						
				
				
				fecha2= StringtoDate(argumento);
						
					//	formateador.parse(argumento);
				
            if(fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
            }
     
                }
                		}
                    
                    
                if(operador.equals("Diferente"))
                		{
                    
                        if(obj.getTipo().equals("cadena")){
                        if(!obj.getElemento().toString().equals(argumento))
                        {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                else if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())!=Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                    
                             
                else if(obj.getTipo().equals("fecha")){
                
                     SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                    
                Date fecha1;
                Date fecha2;
                fecha1 =StringtoDate(obj.getElemento().toString()); 
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						
					//	(Date) formateador.parse(argumento);
				
            if(!fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
            }
     
                }
                        
                		}   
                    
                if(operador.equals( "Mayorq"))
                {
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())>=Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                 
                      
                else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 = StringtoDate(obj.getElemento().toString());
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2= StringtoDate(argumento);
						
						//(Date) formateador.parse(argumento);
				
            if(fecha1.after(fecha2)||fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
            }
     
                }
                    
                }
                
                    
                if(operador.equals("Mayor"))
                		{
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())>Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                    
                   else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 = StringtoDate(obj.getElemento().toString());
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						
						//(Date) formateador.parse(argumento);
				
            if(fecha1.after(fecha2)){
				guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
            }
     
                }
                 
                		}
                
                if(operador.equals("Menor"))
                {
                		
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())<Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                    
                   else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 = StringtoDate(obj.getElemento().toString());
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						
						//(Date) formateador.parse(argumento);
				
            if(fecha1.before(fecha2)){
				guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
            }
     
                }
                 
                }
                if(operador.equals("Menorq"))
                {
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())<=Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
                    }
                }
                 
                 else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 = StringtoDate(obj.getElemento().toString());
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						
						//(Date) formateador.parse(argumento);
				
            if(fecha1.before(fecha2)||fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,lista_tablas[pos]);
            }
     
                }
                    
                
            }//fianl de switch
            
            row++;
            }
        
        return temp;
        }
        
    return null;
    }
    
    //Seleccion a partir de una tabla existente, comlumna y argumento
     public tabla sel_caso2Tabla(tabla tabla,String columna,String operador,String argumento) throws ParseException{
    
            tabla temp=new tabla();
            int row=0,i=0;
            
            
            int [] colum_key=new int [  tabla.getGuava_table().rowKeySet().size()];
            for(Object o:tabla.getGuava_table().rowKeySet()){
                colum_key[i]=Integer.parseInt(o.toString());
                i++;}
            
            Collection<posicion> column=tabla.getGuava_table().column(columna).values();
           
            for(posicion obj:column){

                
                if(operador.equals("Eq"))
                		{
                    
                    if(obj.getTipo().equals("cadena")){
                        if(obj.getElemento().toString().equals(argumento))
                        {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                else if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())==Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                    
                    else if(obj.getTipo().equals("fecha")){
                    
                SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 = StringtoDate(obj.getElemento().toString());
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						
						//(Date) formateador.parse(argumento);
				
            if(fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,tabla);
            }
     
                }
                		} 
                    
                if(operador.equals("Diferente"))
                {
                    
                        if(obj.getTipo().equals("cadena")){
                        if(!obj.getElemento().toString().equals(argumento))
                        {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                else if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())!=Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                        
                        else if(obj.getTipo().equals("fecha")){
                
                     SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                    
                Date fecha1;
                Date fecha2;
                fecha1 =StringtoDate(obj.getElemento().toString());
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						//(Date) formateador.parse(argumento);
				
            if(!fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,tabla);
            }
     
                }
                }   
                    
                    
                if(operador.equals("Mayorq"))
                {
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())>=Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                 
                   else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 =StringtoDate(obj.getElemento().toString()) ;
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2= StringtoDate(argumento);
				//(Date) formateador.parse(argumento);
				
            if(fecha1.after(fecha2)||fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,tabla);
            }
     
                }
                 
                }    
                    
                if(operador.equals("Mayor"))
                {
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())>Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                 
                   else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 =StringtoDate(obj.getElemento().toString()); 
						
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						
						//(Date) formateador.parse(argumento);
				
            if(fecha1.after(fecha2)){
				guardar_fila(colum_key[row],columna,temp,tabla);
            }
     
                }
                    
                }   
                if(operador.equals("Menor"))
                {
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())<Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                    
                  else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 =StringtoDate(obj.getElemento().toString()); 
						//(Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
				//(Date) formateador.parse(argumento);
				
            if(fecha1.before(fecha2)){
				guardar_fila(colum_key[row],columna,temp,tabla);
            }
     
                }
                 
                }
                if(operador.equals("Menorq"))
                {
                    
                 if(obj.getTipo().equals("entero")){
                 if(Integer.parseInt(obj.getElemento().toString())<=Integer.parseInt(argumento))
                    {
                            guardar_fila(colum_key[row],columna,temp,tabla);
                    }
                }
                    
                  else if(obj.getTipo().equals("fecha")){
                 SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy"); 
                Date fecha1;
                Date fecha2;
                fecha1 =StringtoDate(obj.getElemento().toString()); 
						
						//Date) formateador.parse(obj.getElemento().toString());
				fecha2=StringtoDate(argumento);
						//(Date) formateador.parse(argumento);
				
            if(fecha1.before(fecha2)||fecha1.equals(fecha2)){
				guardar_fila(colum_key[row],columna,temp,tabla);
            }
     
                }
                 
                 
                }
            row++;
            }
        
        return temp;
        
      
    }

     // Seleccion entre dos tabla
     public tabla sel_caso1(tabla tab1, String tipo, tabla tab2)
     {
    	
    	if(tipo.equals("Or"))
    	{

    		return union_tabla(tab1, tab2);	
    	}
    		    		
    	else if(tipo.equals("And"))
    	{
    		return interseccion(tab1, tab2);
    	}
    			
    	else if(tipo.equals("Not"))
    	{ 
    		return diferencia(tab1, tab2);
    	}	
    		
    	System.out.println("Error no se reconocio el tipo");
    	return null;
    		
    	
    	
     }
    	   
     
     
     public tabla sel_caso3(tabla table, String Hijoi, String op, String HijoD) {
         tabla tablaa = new tabla("comparado");
         int i = 0;
         tabla TablaOrigen = new tabla();
         TablaOrigen=table;

         Collection<posicion> probando = table.getGuava_table().column(Hijoi).values();
         Collection<posicion> probando2 = table.getGuava_table().column(HijoD).values();

         Iterator i1 = probando.iterator();
         Iterator i2 = probando2.iterator();

         while (i1.hasNext()) {
             Object obj = i1.next();
             Object obj2 = i2.next();

             if ((((posicion) obj).getTipo()).equals((((posicion) obj2).getTipo())) && (((posicion) obj).getTipo()).equals("cadena")) {
                 if ((((posicion) obj).getElemento().toString()).equals((((posicion) obj2).getElemento().toString()))) {
                     String column = Hijoi;
                     tablaa.getGuava_table().put(i, column, obj);
                     
                 }
             }
             if ((((posicion) obj).getTipo()).equals((((posicion) obj2).getTipo())) && (((posicion) obj).getTipo()).equals("fecha")) {
                 String fe1 = (((posicion) obj).getElemento()).toString(), fe2 = (((posicion) obj2).getElemento()).toString();
                 Date fecha1, fecha2;
                 SimpleDateFormat ft = new SimpleDateFormat("dd/mm/yyyy");
                 try {
                     fecha1 = (Date) ft.parse(fe1);
                     fecha2 = (Date) ft.parse(fe2);
                     String column = Hijoi;
                     if (op.equals("Eq")) {
                         if (fecha1.equals(fecha2)) {
                             guardar_fila(i, column, tablaa, TablaOrigen);
                             
                         }
                     }
                     if (op.equals("Diferente")) {
                         if (!fecha1.equals(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                             
                         }
                     }
                     

                     if (op.equals("Menor")) {
                         if (fecha1.before(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                             
                         }
                     }
                     if (op.equals("Mayor")) {
                         if (fecha1.after(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                             
                         }
                     }
                     if (op.equals("Mayorq")) {
                         if (fecha1.equals(fecha2) || fecha1.after(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                             
                         }
                     }
                     if (op.equals("Menorq")) {
                         if (fecha1.equals(fecha2) || fecha1.before(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                             
                         }
                     }
                 } catch (ParseException ex) {
                     Logger.getLogger(Metodo_Guava.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             if ((((posicion) obj).getTipo()).equals((((posicion) obj2).getTipo())) && (((posicion) obj).getTipo()).equals("entero")) {
                 
            	 String valor = (((posicion) obj).getElemento()).toString();
                 String valor2 = (((posicion) obj2).getElemento()).toString();
                 String column = Hijoi;
            	 
            	 if (op.equals("Eq")) {
                     
                     if (Integer.parseInt(valor) == Integer.parseInt(valor2)) {
                         guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Diferente")) {
                    
                	 if (Integer.parseInt(valor) != Integer.parseInt(valor2)) {
                		 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Mayor")) {
                     if (Integer.parseInt(valor) > Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Menor")) {
                     if (Integer.parseInt(valor) < Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Mayorq")) {
                     if (Integer.parseInt(valor) > Integer.parseInt(valor2) || Integer.parseInt(valor) == Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Menorq")) {
                     if (Integer.parseInt(valor) < Integer.parseInt(valor2) || Integer.parseInt(valor) == Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                 
                     }
                 }
                 
             }
             i++;
         }
         return tablaa;
     }

     public tabla sel_caso3(String table, String Hijoi, String op, String HijoD) {
         tabla tablaa = new tabla("comparado");
         int i = 0;
         int pos = buscar_num_tabla(table, lista_tablas);
         tabla TablaOrigen = new tabla("Original");

         Collection<posicion> probando = lista_tablas[pos].getGuava_table().column(Hijoi).values();
         Collection<posicion> probando2 = lista_tablas[pos].getGuava_table().column(HijoD).values();
         
         TablaOrigen.getGuava_table().putAll(lista_tablas[pos].getGuava_table());

         Iterator i1 = probando.iterator();
         Iterator i2 = probando2.iterator();

         while (i1.hasNext()) {
             Object obj = i1.next();
             Object obj2 = i2.next();
             
             if ((((posicion) obj).getTipo()).equals((((posicion) obj2).getTipo())) && (((posicion) obj).getTipo()).equals("cadena")) {
                 if ((((posicion) obj).getElemento().toString()).equals((((posicion) obj2).getElemento().toString()))) {
                     String column = Hijoi;
                     tablaa.getGuava_table().put(i, column, obj);
                     
                 }
             }
             if ((((posicion) obj).getTipo()).equals((((posicion) obj2).getTipo())) && (((posicion) obj).getTipo()).equals("fecha")) {
                 String fe1 = (((posicion) obj).getElemento()).toString(), fe2 = (((posicion) obj2).getElemento()).toString();
                 Date fecha1, fecha2;
                 SimpleDateFormat ft = new SimpleDateFormat("dd/mm/yyyy");
                 try {
                     fecha1 = (Date) ft.parse(fe1);
                     fecha2 = (Date) ft.parse(fe2);
                     String column = Hijoi;
                     if (op.equals("Eq")) {
                         if (fecha1.equals(fecha2)) {
                             guardar_fila(i, column, tablaa, TablaOrigen);
                         
                         }
                     }
                     if (op.equals("Diferente")) {
                         if (!fecha1.equals(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                         }
                     }
                     

                     if (op.equals("Menor")) {
                         if (fecha1.before(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                         }
                     }
                     if (op.equals("Mayor")) {
                         if (fecha1.after(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                         }
                     }
                     if (op.equals("Mayorq")) {
                         if (fecha1.equals(fecha2) || fecha1.after(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                         }
                     }
                     if (op.equals("Menorq")) {
                         if (fecha1.equals(fecha2) || fecha1.before(fecha2)) {
                        	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                         }
                     }
                 } catch (ParseException ex) {
                     Logger.getLogger(Metodo_Guava.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             if ((((posicion) obj).getTipo()).equals((((posicion) obj2).getTipo())) && (((posicion) obj).getTipo()).equals("entero")) {
                 
            	 String valor = (((posicion) obj).getElemento()).toString();
                 String valor2 = (((posicion) obj2).getElemento()).toString();
                 String column = Hijoi;
            	 
            	 if (op.equals("Eq")) {
                     
                     if (Integer.parseInt(valor) == Integer.parseInt(valor2)) {
                         guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Diferente")) {
                    
                	 if (Integer.parseInt(valor) != Integer.parseInt(valor2)) {
                		 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Mayor")) {
                     if (Integer.parseInt(valor) > Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Menor")) {
                     if (Integer.parseInt(valor) < Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Mayorq")) {
                     if (Integer.parseInt(valor) > Integer.parseInt(valor2) || Integer.parseInt(valor) == Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 if (op.equals("Menorq")) {
                     if (Integer.parseInt(valor) < Integer.parseInt(valor2) || Integer.parseInt(valor) == Integer.parseInt(valor2)) {
                    	 guardar_fila(i, column, tablaa, TablaOrigen);
                         
                     }
                 }
                 
             }
             i++;
         }
         return tablaa;
     }
    
    
    
    
  //* domingo y yogo*//
    
    
     
     //yogito
     

    public tabla cartesiano(Object tabla1, Object tabla2)
     {

   	  tabla Table_a = tranfor2(tabla1);
   	  tabla Table_b = tranfor2(tabla2);

        tabla tabla_resul = new tabla();

        ////////////////////////////////////// Mejora

        if((Table_a==null && Table_b==null)){
        	System.out.println("Error: tablas inexistentes");
        	return tabla_resul;
        	}
        
        if((Table_a!=null && Table_b!=null)){
        	if((Table_a.getGuava_table().isEmpty() && Table_b.getGuava_table().isEmpty()))
        	{
        	System.out.println("Advertencia: ambas tablas son vacias");
        	return tabla_resul;
        	}
        }
        
         if(Table_a==null || Table_a.getGuava_table().isEmpty()){
        	//si A es nula o vacia
        	if(Table_b!=null && !(Table_b.getGuava_table().isEmpty())){
        		//b no es nula y no esta vacia
        		tabla_resul.getGuava_table().putAll(Table_b.getGuava_table());
        		System.out.println("Advertencia: Tabla 1 vacia o inexistente");
        		//devuelva B
        		return tabla_resul;
        	}
        	
        	
        	
        	else
        	{
        		System.out.println("Error: tablas inexistentes o vacias");
        		return tabla_resul;
        	
        	}
        }
        
         if(Table_b==null || Table_b.getGuava_table().isEmpty()){
        	//si B es nula o vacia
        	if(Table_a!=null && !(Table_a.getGuava_table().isEmpty())){
        		//A no es nula y no esta vacia        		
        		tabla_resul.getGuava_table().putAll(Table_a.getGuava_table());
        		//devuelva A
        		System.out.println("Advertencia: Tabla 2 vacia o inexistente");
        		return tabla_resul;
        	}
        	
        	else
        		return tabla_resul;
        }
        
        ////////////////////////////////////////////// fin mejora
        
        String nombreTA;
        if(Table_a.getNombre() == null||Table_a.getNombre().equals("")){
        nombreTA="Tabla1_";
        }
        else{
        nombreTA=Table_a.getNombre();
        }
            
            
        String nombreTB;
        if(Table_b.getNombre()==null||Table_b.getNombre().equals("")){
        nombreTB="Tabla2_";
        }
        else{
        nombreTB=Table_b.getNombre();
        }

        int i=0;
         for(Object obj_row :Table_a.getGuava_table().rowKeySet()){
             
             int val=Integer.parseInt(obj_row.toString());
             
             Collection<posicion> filas=Table_a.getGuava_table().row(val).values();
        
            producto_c(filas,Table_a.getGuava_table(),nombreTA,Table_b.getGuava_table(),nombreTB,tabla_resul,i);
                i++;
         }

        // System.out.println("sale: "+tabla_resul.getGuava_table().columnKeySet().size()+" "+tabla_resul.getGuava_table().rowKeySet().size());
        return tabla_resul;

    }
     
    private void producto_c(Collection<posicion> filas, Table Table_a, String nombreTA, Table Table_b, String nombreTB, tabla tabla_resul,int indice) {
        
      
        int cant_fil_b=0;
       
        String [] columnas_ta=array_columnas(Table_a);
        String [] columnas_tb=array_columnas(Table_b);
        String [] fil_tb=array_filas(Table_b);
        int f;
        
        if(indice==0)
        f=indice;
        else f=tabla_resul.getGuava_table().rowKeySet().size();
        
        while(cant_fil_b<Table_b.rowKeySet().size()){
         
         int i=0;
         int j=0;
         
            for(posicion p:filas){
        
                if(columna_repetida(columnas_ta[i],columnas_tb)){
                tabla_resul.getGuava_table().put(f,nombreTA+"_"+columnas_ta[i],p);
                }
                else{
                 tabla_resul.getGuava_table().put(f,columnas_ta[i],p);
                }
                
               
                i++;
                
        } 
            
         
        Collection<posicion> filas_tb=Table_b.row(Integer.parseInt(fil_tb[cant_fil_b])).values();
        
          //  System.out.println("Yupiiiiii: "+fil_tb.length);
          for(posicion p:filas_tb){
              
           if(columna_repetida(columnas_tb[j],columnas_ta)){
                String pal=nombreTB+"_"+columnas_tb[j];
                tabla_resul.getGuava_table().put(f,pal,p);
                }
                else{
                 String pal=columnas_tb[j];
                 tabla_resul.getGuava_table().put(f,pal,p);
                }
                
                
                j++;
                
        }
          f++;
        
         cant_fil_b++;
        }
    }
    
    private String[] array_columnas(Table x){
        
    String [] col=new String[x.columnKeySet().size()];
    int i=0;
    
    for(Object p:x.columnKeySet()){
    col[i]=p.toString();
    i++;
    }
        return col;
    };
    
    private String[] array_filas(Table x){
        
    String [] col=new String[x.rowKeySet().size()];
    int i=0;
    
    for(Object p:x.rowKeySet()){
    col[i]=p.toString();
    i++;
    }
        return col;
    };

    private boolean columna_repetida(String string, String[] columnas_tb) {
        for (String columnas_tb1 : columnas_tb) {
            if (columnas_tb1.equals(string)) {
                return true;
            }
        }
        return false;
    }
     
    //yogito
     
    
     public tabla diferencia(Object tabla1, Object  tabla2)
     {
     	
    	 tabla tabla_resul = new tabla();
    	 
    
    	 Table Table_a   = tranfor(tabla1);
         Table Table_b   = tranfor(tabla2);
         
         ///////////////////////////////////////////////////////////////////////////////////////////
         
         if((Table_a==null && Table_b==null)){
        	System.out.println("Error: tablas inexistentes");
         	return tabla_resul;
         	}
         
         if((Table_a!=null && Table_b!=null)){
         	if((Table_a.isEmpty() && Table_b.isEmpty()))
         	{
         	System.out.println("Advertencia: ambas tablas son vacias");
         	
         	return tabla_resul;
         	}
         }
         
          if(Table_a==null || Table_a.isEmpty()){
         	//si A es nula o vacia
         	if(Table_b!=null && !(Table_b.isEmpty())){
         		//b no es nula y no esta vacia
         		tabla_resul.getGuava_table().putAll(Table_b);
            	System.out.println("Advertencia: Tabla 1 vacia o inexistente");
         		//devuelva B
         		return tabla_resul;
         	}
         	
         	else
         	{
         		System.out.println("Error: tablas inexistentes o vacias");
         		return tabla_resul;
         	
         	}
         }
         
          if(Table_b==null || Table_b.isEmpty()){
         	//si B es nula o vacia
         	if(Table_a!=null && !(Table_a.isEmpty())){
         		//A no es nula y no esta vacia        		
         		tabla_resul.getGuava_table().putAll(Table_a);
         		//devuelva A
         		
         		System.out.println("Advertencia: Tabla 2 vacia o inexistente");
         		return tabla_resul;
         	}
         	
         	else
         		return tabla_resul;
         }
         
         
         ///////////////////////////////////////////////////////////////////////////////////////////
         
     	 if(Table_b.isEmpty() && ! Table_a.isEmpty())
        	 {
     		 	tabla result = new tabla();
     			result.getGuava_table().putAll(Table_a);
     			return result;
        	 }
        	 
     	 //ESTAAAAAAAA AQUIII
     if(Iscolumntable_equals(Table_a, Table_b))
     {
    	 ArrayList<String> equals_colums = Search_column_equals (Table_a, Table_b);
      	
      	
      	Iterator iterador = equals_colums.listIterator();
      	
      	while(iterador.hasNext())
      	{
      		
      		
      		
      		String  columna = iterador.next().toString();
      		String tipo_com = new String();
      		
      		
      		Collection<posicion> Comun_A;
      		Collection<posicion> Comun_B;
      		 
      		
      		Comun_A = Table_a.column(columna).values();
      		
      		tipo_com = type_Collection(Comun_A);
      		
      		Set<Object> set_a = ImmutableSet.copyOf(Collection_toArray(Comun_A));
      		
      		Comun_B = Table_b.column(columna).values();
      		
      		Set<Object> set_b = ImmutableSet.copyOf(Collection_toArray(Comun_B));
      		
      		
      		if( isColumn_sametype(Comun_A, Comun_B)  )
      		{
      			Set<Object> result =   Sets.difference(set_a, set_b);
          		
      			
          		for(Object o : result)
          		{
          			
          			
          			posicion obj2 = new posicion(o,tipo_com ); 
          			
          			tabla_resul.setGuava_table(this.Save_row(Table_a, tabla_resul.getGuava_table(), columna, obj2, equals_colums));
          			
          		}
          	
      		
      		}
      		
      		
      		
      		
      		
      		
      		
      	
      	} 
     }
     else
     {
    	 System.out.println("Error las columnasn no son iguales");
     }
         
         
     	
     	
     	
     	
     	return tabla_resul;
     	
     	
     	
     	
     }
     
     public tabla union_tabla(Object tabla1, Object tabla2)
     {
    	 
    	
    	tabla resul = new tabla();
    	 
        Table Table_a = tranfor(tabla1);
        Table Table_b = tranfor(tabla2);

        ///////////////////////////////////////////////////////////////////////////////////////////
        
        if((Table_a==null && Table_b==null)){
        	System.out.println("Error: tablas inexistentes");
        	return resul;
        	}
        
        if((Table_a!=null && Table_b!=null)){
        	if((Table_a.isEmpty() && Table_b.isEmpty()))
        	{
        	System.out.println("Advertencia: ambas tablas son vacias");
        	return resul;
        	}
        }
        
         if(Table_a==null || Table_a.isEmpty()){
        	//si A es nula o vacia
        	if(Table_b!=null && !(Table_b.isEmpty())){
        		//b no es nula y no esta vacia
        		resul.getGuava_table().putAll(Table_b);
        		System.out.println("Advertencia: Tabla 1 vacia o inexistente");
        		//devuelva B
        		return resul;
        	}
        	
        	
        	
        	else
        	{
        		System.out.println("Error: tablas inexistentes o vacias");
        		return resul;
        	
        	}
        }
        
         if(Table_b==null || Table_b.isEmpty()){
        	//si B es nula o vacia
        	if(Table_a!=null && !(Table_a.isEmpty())){
        		//A no es nula y no esta vacia        		
        		resul.getGuava_table().putAll(Table_a);
        		//devuelva A
        		System.out.println("Advertencia: Tabla 2 vacia o inexistente");
        		return resul;
        	}
        	
        	else
        		return resul;
        }
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////
        if(Iscolumntable_equals(Table_a, Table_b))
        {
            ArrayList<String> list = Search_column_equals(Table_a, Table_b);

        	tabla tabC =  diferencia(Table_a, Table_b);
        	
        	tabla tabD =  interseccion(Table_a, Table_b);
        	
        	
        	resul.getGuava_table().putAll(tabC.getGuava_table());
        	
        	int last = 0;
        	for(Object fil : resul.getGuava_table().rowKeySet())
        	{
        		last =  Integer.parseInt( fil.toString());
        	}
        	last++;
        	for(Object fill : tabD.getGuava_table().rowKeySet())
        	{
        		for(Object col : tabD.getGuava_table().columnKeySet())
        		{
        			posicion val = (posicion) tabD.getGuava_table().get(fill, col.toString());
        			
        			
        			resul.getGuava_table().put(last, col, val);
        			
        			
        		}
        		last++;
        	}
        	
        	
        
        	tabC =  diferencia(Table_b, Table_a);
        	
        	
        	
        	for(Object fill : tabC.getGuava_table().rowKeySet())
        	{
        		for(Object col : tabC.getGuava_table().columnKeySet())
        		{
        			posicion val = (posicion) tabC.getGuava_table().get(fill, col.toString());
        			
        			
        			resul.getGuava_table().put(last, col, val);
        			
        			
        		}
        		last++;
        	}
        	
        	
        	
            
        }
        else
        {
        	System.out.println(" ERROR las columnas no son iguales");
            return null;
        }
       
        return resul;

    }     
    
     public Table tranfor(Object obj)
     {
    	 if(obj instanceof String)
    	 {
    		 String nombre = (String) obj;
    		 int pos_1 = buscar_num_tabla(nombre, this.lista_tablas);
    		 
    		 if(pos_1 == -1)
    		 {
    		//	 System.out.println("Error tabla no se consigue");
    			 return null;
    		 }
    		 
    		 return  lista_tablas[pos_1].getGuava_table();
    		 
    		 
    	 }
    	 else if(obj instanceof tabla)
    	 {
    		 return ((tabla)obj).getGuava_table();
    	 }
    	 
    	 else if(obj instanceof Table)
    	 {
    		 return ((Table) obj);
    	 }
    	 else
    	 {
    		// System.out.println("error en trasfor");
    		 return null;
    	 }
     }
     
     
     
     public tabla tranfor2(Object obj)
     {
    	 if(obj instanceof String)
    	 {
    		 String nombre = (String) obj;
    		 int pos_1 = buscar_num_tabla(nombre, this.lista_tablas);
    		 
    		 if(pos_1 == -1)
    		 {
    		//	 System.out.println("Error tabla no se consigue");
    			 return null;
    		 }
    		 
    		 return  lista_tablas[pos_1];
    		 
    		 
    	 }
    	 else if(obj instanceof tabla)
    	 {
    		 
    		 
    		 return ((tabla) obj);
    		 
    	 }
    	 else if(obj instanceof Table)
    	 {
    		 tabla obj2 = new tabla();
    		 obj2.setGuava_table((Table) obj);
    		 return obj2;
    		 
    	 }
    	 else
    	 {
    	//	 System.out.println("error en trasfor");
    		 return null;
    	 }
     }
   
     public boolean Second_e(Table  dest, Table sour, Object fil,Object fil2)
     {
    	 
    	 
    	 for(Object col : sour.columnKeySet())
		 {
			 posicion val1 = (posicion)sour.get(fil2, col.toString());
			 posicion val2 = (posicion)dest.get(fil, col.toString());
			 
			 if(!val1.getElemento().toString().equals(val2.getElemento().toString()))
			 {
				 return false;
			 }
			
			 
		 }
	 
	 return true; 
     }
     
     public boolean equals_D(posicion obj, Table sour, Table dest, Object col,Object fil2)
     {
    	 
    		 for(Object fil : sour.rowKeySet())
    		 {
    			 posicion val = (posicion)sour.get(fil, col.toString());
    			 
    			 if(val.getElemento().toString().equals(obj.getElemento().toString()))
    			 {
    				if( Second_e(sour, dest, fil,fil2))// aquiiii mierda
    					return true;
    			 }
    		 }
    	 
    	 return false;
     }
     
     
     
    public tabla interseccion(Object tab1, Object tab2)
    {
    	ArrayList<String> eq_list = new ArrayList<String>();
    	
    	Table Table_a = tranfor(tab1);
    	Table Table_b = tranfor(tab2);
    	tabla resul = new tabla();
    	
 ///////////////////////////////////////////////////////////////////////////////////////////
        
        if((Table_a==null && Table_b==null)){
        	System.out.println("Error: tablas inexistentes");
        	return resul;
        	}
        
        if((Table_a!=null && Table_b!=null)){
        	if((Table_a.isEmpty() && Table_b.isEmpty()))
        	{
        		System.out.println("Advertencia: ambas tablas son vacias");
        	return resul;
        	}
        }
        
         if(Table_a==null || Table_a.isEmpty()){
        	//si A es nula o vacia
        	if(Table_b!=null && !(Table_b.isEmpty())){
        		//b no es nula y no esta vacia
        		resul.getGuava_table().putAll(Table_b);
        		System.out.println("Advertencia: Tabla 1 vacia o inexistente");
        		//devuelva B
        		return resul;
        	}
        	
        	
        	
        	else
        	{
        		System.out.println("Error: tablas inexistentes o vacias");
        		return resul;
        	
        	}
        }
        
         if(Table_b==null || Table_b.isEmpty()){
        	//si B es nula o vacia
        	if(Table_a!=null && !(Table_a.isEmpty())){
        		//A no es nula y no esta vacia        		
        		resul.getGuava_table().putAll(Table_a);
        		//devuelva A
        		System.out.println("Advertencia: Tabla 2 vacia o inexistente");
        		return resul;
        	}
        	
        	else
        		return resul;
        }
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////
    	
    	eq_list= Search_column_equals(Table_a, Table_b);	
    	
    	if(eq_list.size() !=0)
    	{
    		unid(Table_a, Table_b, 0, resul.getGuava_table(), eq_list);	
    	}
    	else
    	{
    		System.out.println("Error no hay similitud de columnas");
    		return null;
    	}
    
    	
    	
    	
    	return resul;
    	
    }
     
     public Table unid(Table tab1, Table tab2, int last_pos, Table tab,ArrayList<String> Equals_col)
     {
    	 int cant_col = 0;
    	 for (Object col : tab1.columnKeySet() )
    	 {
    		 cant_col++;
    	 }
    	 
    	 for(Object fil:tab1.rowKeySet())
    	 {
    		 boolean flag =true;
    		 
    		 
    		 Object col =Equals_col.get(0);
    		 
    			 posicion p;
                 p = (posicion) tab1.get(Integer.parseInt(fil.toString()), col.toString());

                 
                 if(equals_D(p, tab2,tab1,col,fil) )
                 {
                	 
                	 for(Object col2 : Equals_col)
         			{
         				
         				posicion val;
            			 	val = (posicion) tab1.get(Integer.parseInt(fil.toString()), col2.toString());
         				
         				
         				tab.put(last_pos,col2.toString() , val);
         				
         				
         			}
         			last_pos++;
                 }
                 
                
    			 
    			 
    		 
    		 
    		 
    	 }
    	 
    	 
    	 
    	 return tab;
     }
          
     //DANIEL
     
   
   //luna
   
   
  
     
     
     ///daniel
     
     
     
     
     
     
     
    public Table  Save_row(Table oring, Table dest,String Column , posicion obj,ArrayList<String> Equ_colm )
	{

	
		
		
		int fila = -1;
		tabla temp = null;
		
        for(Object fil:oring.rowKeySet())
        {
            
        	posicion p;
            p = (posicion) oring.get(Integer.parseInt(fil.toString()), Column);
               
            if(p.getElemento().toString().equals(obj.getElemento().toString()))
            {
            	fila=Integer.parseInt(fil.toString());
            	
            	
            	for (String col : Equ_colm)
            	{
            		
            		guardar_fila(fila, col, dest,oring);
            	}
            	
            	
            	
            }
            	
        }
        
        
        return dest;
       
    }
		
	private void almacenar_fila_filtrada(int parseInt, tabla filtrada, tabla tableF) {
        
        for (Object col : tableF.getGuava_table().columnKeySet()) {
            posicion p=(posicion) tableF.getGuava_table().get(parseInt, col.toString());
            filtrada.getGuava_table().put(parseInt, col.toString(),p);
        }
        
    }
	
	public tabla naroow (tabla tableF){

	      tabla filtrada=new tabla();
	        boolean paso=true;
	        
	        for (Object fil : tableF.getGuava_table().rowKeySet()) {

	            for (Object col : tableF.getGuava_table().columnKeySet()) {

	                posicion p;
	                p = (posicion) tableF.getGuava_table().get(Integer.parseInt(fil.toString()), col.toString());
	               
	                if(p==null)paso=false;

	            }
	            
	            if(paso)
	            almacenar_fila_filtrada(Integer.parseInt(fil.toString()),filtrada,tableF);
	            
	        }
	        
	    return filtrada;
	  
    }
    
   public Date StringtoDate(String date)
   {
   	String []tem = date.split("/");
   	
   	Date result = new Date(Integer.parseInt(tem[2]),Integer.parseInt(tem[1]),Integer.parseInt(tem[0]));

   	
   	return result;
   
   
   }
   
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
               BufferedReader reader = new BufferedReader(new FileReader("./src/carpeta/" + lista_tablas[x].getNombre()+".txt"));
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
               lista_tablas[x].init_columns(columnas.length, columnas);
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
               reader = new BufferedReader(new FileReader("./src/carpeta/" + lista_tablas[x].getNombre()+".txt"));
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

			
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }

       }

   }
   
   
 ///daniel
   
   
    private static int buscar_num_tabla(String nombre_tabla, tabla[] lista_tablas) {

        for (int i = 0; i < lista_tablas.length; i++) {

            if (lista_tablas[i].getNombre().equals(nombre_tabla)) {
                return i;
            }

        }

        return -1;
    }
    
    //yogo y domingo  
    
    public void imprimir_tabfinal_string(String nombre_tabla){
        
        int pos=buscar_num_tabla(nombre_tabla, lista_tablas);
        if(pos!=-1){
        tabla tableF=lista_tablas[pos];
        
        int conta_colum = 0, conta_fila = 0;
        int cant_colum = tableF.getGuava_table().columnMap().size(), cant_filas = tableF.getGuava_table().rowMap().size();
        interfaz_tabla interfaz;
        String cAgenda[] = new String[cant_colum];
        Object asd = tableF.getGuava_table().columnKeySet();

        StringTokenizer st = new StringTokenizer(asd.toString(), "[, ]");
        while (st.hasMoreTokens()) {
            cAgenda[conta_colum] = st.nextToken();
            conta_colum++;
        }

        String aAgenda[][] = new String[cant_filas][cant_colum];

        for (Object fil : tableF.getGuava_table().rowKeySet()) {
            conta_colum = 0;
            for (Object col : tableF.getGuava_table().columnKeySet()) {
                posicion p;
                p = (posicion) tableF.getGuava_table().get(Integer.parseInt(fil.toString()), col.toString());
                aAgenda[conta_fila][conta_colum] = p.getElemento().toString();
                conta_colum++;
            }
            conta_fila++;
        }
        interfaz = new interfaz_tabla(aAgenda, cAgenda, conta_fila, tableF.getNombre());
    }
        else System.out.println("Tabla No Existe");
    }
    
    public void imprimir_tabfinal_tabla(Table tableF){
        int conta_colum = 0, conta_fila = 0;
        int cant_colum = tableF.columnMap().size(), cant_filas = tableF.rowMap().size();
        interfaz_tabla interfaz;
        String cAgenda[] = new String[cant_colum];
        Object asd = tableF.columnKeySet();

        StringTokenizer st = new StringTokenizer(asd.toString(), "[, ]");
        while (st.hasMoreTokens()) {
            cAgenda[conta_colum] = st.nextToken();
            conta_colum++;
        }

        String aAgenda[][] = new String[cant_filas][cant_colum];

        for (Object fil : tableF.rowKeySet()) {
            conta_colum = 0;
            for (Object col : tableF.columnKeySet()) {
                posicion p;
                p = (posicion) tableF.get(Integer.parseInt(fil.toString()), col.toString());
                aAgenda[conta_fila][conta_colum] = p.getElemento().toString();
                conta_colum++;
            }
            conta_fila++;
        }
        interfaz = new interfaz_tabla(aAgenda, cAgenda, conta_fila);

    }
    
    public void imprimir_tabfinal_tabla(tabla tableF){

        int conta_colum = 0, conta_fila = 0;
        int cant_colum = tableF.getGuava_table().columnMap().size(), cant_filas = tableF.getGuava_table().rowMap().size();
        interfaz_tabla interfaz;
        String cAgenda[] = new String[cant_colum];
        Object asd = tableF.getGuava_table().columnKeySet();

        StringTokenizer st = new StringTokenizer(asd.toString(), "[, ]");
        while (st.hasMoreTokens()) {
            cAgenda[conta_colum] = st.nextToken();
            conta_colum++;
        }

        String aAgenda[][] = new String[cant_filas][cant_colum];

        for (Object fil : tableF.getGuava_table().rowKeySet()) {
            conta_colum = 0;
            for (Object col : tableF.getGuava_table().columnKeySet()) {
                posicion p;
                p = (posicion) tableF.getGuava_table().get(Integer.parseInt(fil.toString()), col.toString());
                aAgenda[conta_fila][conta_colum] = p.getElemento().toString();
                conta_colum++;
            }
            conta_fila++;
        }
        interfaz = new interfaz_tabla(aAgenda, cAgenda, conta_fila, tableF.getNombre());
    }
    
   private void guardar_fila(int i,String col, tabla temp, tabla tabla) {
        
        for(Object colu: tabla.getGuava_table().columnKeySet()){
        temp.getGuava_table().put(i, colu, tabla.getGuava_table().get(i, colu));
        }
    }
    
    private void guardar_fila(int i,String col, Table temp, Table tabla) {
        
        for(Object colu: tabla.columnKeySet()){
        temp.put(i, colu, tabla.get(i, colu));
        }
    }
    
  //yogo y domingo 
    
    
    
    
    
    //DANIEL
    
    public ArrayList<String> Search_column_equals (Table Table_a, Table Table_b){
    	
    	ArrayList<String> column_a = new ArrayList<String>();
    	ArrayList<String> column_b = new ArrayList<String>();
    	ArrayList<String> equals_colums = new ArrayList<String>();
    	
    	
    	
    	for (Object elem :Table_a.columnKeySet())
    	{
    		column_a.add(elem.toString());
    	}
    	
    	for (Object elem :Table_b.columnKeySet())
    	{
    		column_b.add(elem.toString());
    	}
    	
    	Iterator iterador = column_a.listIterator(); 
    	
    	while( iterador.hasNext() ) {
    			
    		String colum =	iterador.next().toString();
    		
    		if(column_b.contains(colum))
    		{
    			equals_colums.add(colum);	
    		}
    		
    
    	}
    	
    	
    	return equals_colums;
    }
    
    public boolean isColumn_sametype ( Collection<posicion> Co1, Collection<posicion> Co2){
    	
    	String tipo1 = type_Collection(Co1);
    	String tipo2 = type_Collection(Co2);
    	
    	if(tipo1.equals(tipo2))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    
    public ArrayList<Object> Collection_toArray(Collection<posicion> colection) {
		Iterator i = colection.iterator();
		ArrayList<Object> result = new ArrayList<Object>();
		
		
		while(i.hasNext())
		{
			posicion pos = (posicion) i.next();
			
			result.add(pos.getElemento());
		}
		
		return result;
	}
    
    public String type_Collection(Collection<posicion> colection) {
		Iterator i = colection.iterator();
		String result = new String();
		
		
		if(i.hasNext())
		{
			posicion pos = (posicion) i.next();
			
			result = pos.getTipo();
		}
		
		return result;
	}
   
    public boolean Iscolumntable_equals(Table Table_a, Table Table_b) {

        ArrayList<String> column_a = new ArrayList<String>();
        ArrayList<String> column_b = new ArrayList<String>();
        
        
        for (Object elem : Table_a.columnKeySet()) {
            column_a.add(elem.toString());
        }

        for (Object elem : Table_b.columnKeySet()) {
            column_b.add(elem.toString());
        }

        
        Iterator i = column_a.listIterator();
       
        if(column_a.size() == 0 && column_b.size() != 0)
        {
        	return true;
        }
        else if(column_b.size() == 0 && column_a.size() != 0)
        {
        	return true;
        }
        else if(column_a.containsAll(column_b) && column_b.containsAll(column_a))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    
    //DANIEL
    
    
    
    
    
}
