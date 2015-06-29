package Guava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class interfaz_tabla extends JFrame {

	public interfaz_tabla(){}
	
    public interfaz_tabla(String[][] da, String[] col, int fil, String nomb) {
    	if(da != null || da.length != 0){
    	JFrame frame = new  JFrame();
    		Object[][] datos = da;
        String[] columnNames = col;
        setTitle(nomb);
        DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        final JTable table = new JTable(dtm);

        //Formato verifica si las dimensiones son dinamicas o estaticas
        if (fil <= 25 && col.length <= 3) {
        	table.setPreferredScrollableViewportSize(new Dimension(350 * col.length, 16 * fil));
        }
        if (fil <= 25 && col.length > 3) {
        	table.setPreferredScrollableViewportSize(new Dimension(150 * 6, 16 * fil));
        }
        if (fil > 25 && col.length <= 3) {
        	table.setPreferredScrollableViewportSize(new Dimension(350 * col.length, 16 * 25));
        }

        if (fil > 25 && col.length > 3) {
        	table.setPreferredScrollableViewportSize(new Dimension(150 * col.length, 16 * 25));
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
       
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame. setVisible(true);
        }else{
    		error("error de nacimiento");
    		
    	}
    }
    
    public interfaz_tabla(String[][] da, String[] col, int fil) {

    	if(da != null || da.length != 0){
    	Object[][] datos = da;
        String[] columnNames = col;
        DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        final JTable table = new JTable(dtm);

        //Formato verifica si las dimensiones son dinamicas o estaticas
        if (fil <= 25 && col.length <= 6) {
        	table.setPreferredScrollableViewportSize(new Dimension(350 * col.length, 16 * fil));
        }
        if (fil <= 25 && col.length > 6) {
        	table.setPreferredScrollableViewportSize(new Dimension(150 * 6, 16 * fil));
        }
        if (fil > 25 && col.length <= 6) {
        	table.setPreferredScrollableViewportSize(new Dimension(350 * col.length, 16 * 25));
        }

        if (fil > 25 && col.length > 3) {
        	table.setPreferredScrollableViewportSize(new Dimension(150 * col.length, 16 * 25));
        }

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
       
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    	}else{
    		error("error de nacimiento");
    		
    	}
    		
    }

    
    
    
    public static void error(String error){
    	JFrame frame = new JFrame();
    	JLabel etiqueta = new JLabel(error); 
    	
    	frame.setBounds(350, 150, 350, 100);
    	frame.setLayout(null);
    	frame.setTitle("Error");
    	frame.setVisible(true);
    	etiqueta.setBounds(40, 15, 600, 30);
    	etiqueta.setLayout(null);
    	etiqueta.setVisible(true);
    	
    	frame.add(etiqueta);
    }
}