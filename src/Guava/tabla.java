package Guava;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class tabla {
	
	private String  columnas[];
    private String nombre;
    private Table <Integer,String,posicion> guava_table;
    private boolean exist = true;
    
    

    public tabla(String nombre, Table guava_table) {
        this.nombre = nombre;
        this.guava_table = guava_table;
    }
    
    public tabla(String nombre) {
        this.nombre = nombre;
        this.guava_table=HashBasedTable.create();
    }

    public tabla() {
        guava_table=HashBasedTable.create();
    }

    
    public boolean Get_exist()
    {
    	return exist;
    }
    
    public tabla Set_exist()
    {
    	this.exist=false;
    	return this;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Table getGuava_table() {
        return guava_table;
    }

    public void setGuava_table(Table guava_table) {
        this.guava_table = guava_table;
    }
    
    public void put_table (int R,String C,posicion V){
    
        guava_table.put(R, C, V);
    
    }
    
    public void setColumnas(String [] columnas)
    {
    	this.columnas = columnas;
    }
    
    public String[] getColumnas()
    {
    	return columnas;
    }
    

    public void init_columns(int x,String[] val){
        this.columnas=new String[x];
            for (int i = 0; i < val.length; i++) {
                this.columnas[i]=val[i];
            }
        }

    
    
    public int colum_size()
    {
        return this.columnas.length;
    }
    
    

}

