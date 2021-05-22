package com;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {  
    public static void main(String[] args) {  

        
        //CAMBIA LA RUTA POR LA TUYA
        String path = "/home/axel/Escritorio/Prueba/demo/src/main/java/com/Entrada2.xlsx";
        File DirArchivo = new File(path);

        try{
            FileInputStream file = new FileInputStream(DirArchivo);

            XSSFWorkbook libro = new XSSFWorkbook(file);  //leemos el archivo especificado...
            XSSFSheet sheet = libro.getSheetAt(0);  //...en la hoja 0 (principal)
            Iterator<Row> IteradorFila = sheet.iterator(); //obtenemos las filas de la hoja

            ArrayList<String> NombresIncognitas = new ArrayList<String>(); //Creamos el ArrayList donde almacenaremos los nombres de las incognitas
            ArrayList<Variable> listaVariables = new ArrayList<>();
            Row fila; //Una variable de tipo fila en la que iremos almacenando la fila a leer
            Variable xn = null; //Creamos una nueva variable
            
            fila = IteradorFila.next(); //tomamos la fila
            if(fila.getRowNum() == 0){ //Si es la primer fila(la del nombre), tomamos los valores de las celdas y los almacenamos en un ArrayList
                //Obtenemos las celdas de la fila
                Iterator<Cell> BuscandoNombres = fila.cellIterator();
                Cell celda; //variable de tipo celda para almacenar lass celdas de la fila actual
                //Recorremos la fila buscando y almacenando los nombres de las incognitas
                while(BuscandoNombres.hasNext()){
                    celda = BuscandoNombres.next();
                    String Incognita = celda.toString();
                    NombresIncognitas.add(Incognita);
                }
            }

            //Recorremos la hoja una vez por cada columna
            for(int i = 0; i < NombresIncognitas.size(); i++){
                String NombreCol = NombresIncognitas.get(i);
                //Variable xn = new Variable(NombreCol); //Creamos una nueva variable
                xn = new Variable(NombreCol); //Creamos una nueva variable
                IteradorFila = sheet.iterator(); //Reiniciar conteno de filas en el iterador
                fila = IteradorFila.next(); //Omitimos la primer fila

                while(IteradorFila.hasNext()){
                    fila = IteradorFila.next(); //Saltamos de fila
                    Iterator<Cell> cellIterator = fila.cellIterator(); //Tomamos las celdas de dicha fila
                    Cell celda;

                    while(cellIterator.hasNext()){ //...Recorremos las celdas de esa fila
                        celda = cellIterator.next();
                        if(celda.getColumnIndex() == NombresIncognitas.indexOf(NombreCol)){ //Si la casilla pertenece a la columna buscada...
                            xn.setValor(celda.toString());   //...la agregamos a los valores de la variable
                        }
                    }                    
                }
                listaVariables.add(xn); //Agregamos la variable a la lista de variables
            }
            Convertir(listaVariables, xn);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }  

// Esto es lo que mando Isaac------------------------------------
    public static void Convertir(ArrayList<Variable> listaVariables, Variable xn){
        String ecuacion = "";
        for(int j = 0; j < xn.getTamanoLista(); j++)
        {
            ecuacion = "2*x_1^2 - x_2 + x_3";
            System.out.println("Ecuacion a evaluar: " + ecuacion + "\n");
            for(Variable variable : listaVariables)
            {
                System.out.println("Vuelta: " + j);
                System.out.println(variable.toString(j));
                ecuacion = ecuacion.replaceAll(variable.getVariable(), variable.getValor(j));
            }
        
            System.out.println("\nEcuacion Formateada: " + ecuacion);
            System.out.println("\n--------------------------\n");
        }
    }

}  