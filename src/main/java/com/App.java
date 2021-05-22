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
        String path = "/home/axel/Escritorio/Prueba/demo/src/main/java/com/Entrada.xlsx";
        File DirArchivo = new File(path);

        try{
            FileInputStream file = new FileInputStream(DirArchivo);

            XSSFWorkbook libro = new XSSFWorkbook(file);  //leemos el archivo especificado...
            XSSFSheet sheet = libro.getSheetAt(0);  //...en la hoja 0 (principal)
            Iterator<Row> IteradorFila = sheet.iterator(); //obtenemos las filas de la hoja

            ArrayList<String> NombresIncognitas = new ArrayList<String>(); //Creamos el ArrayList donde almacenaremos los nombres de las incognitas
            Row fila; //Una variable de tipo fila en la que almacenaremos cada una de las filas

            //Recorremos cada fila de la hoja 
            while(IteradorFila.hasNext()){
                ArrayList<String> valores = new ArrayList<String>(); //Creamos el ArrayList donde almacenaremos los valores extraidos

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
                    
                    System.out.println("Incognitas = "+NombresIncognitas); // imprime los nombres de las incognitas

                    fila = IteradorFila.next(); //Saltamos a la siguiente fila
                }


                //Mismo procedimiento de arriba pero ahora para obtener los valores
                Iterator<Cell> cellIterator = fila.cellIterator();
                Cell celda;

                while(cellIterator.hasNext()){ //...Recorremos las celdas de esa fila
                    celda = cellIterator.next();


                    /*
                    Por ejemplo, tenemos la ecuacion: (12 + x_1) - x_5
                    
                    Esta clase podria recibir un array con los nombres de esas incognitas, y en los ifs de abajo buscar esos nombres
                    */

                    //AQUI ESTAMOS RECIBIENDO SOLO LOS VALORES DE X_1 Y X_5
                    if(celda.getColumnIndex() == NombresIncognitas.indexOf("x_5")){ //Aqui podriamos recibir el nombre de la incognita a buscar
                        valores.add(celda.toString());   //Agregamos la celda al ArrayList
                    } 
                    else if (celda.getColumnIndex() == NombresIncognitas.indexOf("x_1")){ //...aca igual
                        valores.add(celda.toString());   //Agregamos la celda al ArrayList
                    }
                }
                System.out.println("\t  "+valores); // imprime los valores
            }
        }
        catch(Exception e){
            e.getMessage();
        }
    }  
}  