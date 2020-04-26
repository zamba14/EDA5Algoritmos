package EDA5Algoritmos;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author FabricioGrosso
 */

//CONSIGNA
//Draw * according to certain size
//
//Input Format
//
//1
//
//2
//
//3
//
//4
//
//Output Format
//
//Sample Input 0
//
//0
//Sample Output 0
//
//*
//Explanation 0
//
//The tiniest possible star, made of a single asterisk (*).
//
//Sample Input 1
//
//1
//Sample Output 1
//
//***
//***
//***
//Explanation 1
//
//Extra asteriks are drawn around the center in each of the eight possible directions.
//
//Sample Input 2
//
//2
//Sample Output 2
//
//* * *
// ***
//*****
// ***
//* * *
//Explanation 2
//
//Two extra asteriks are drawn around the center in each of the eight possible directions.

class  Result1 {

    /*
     * Complete the 'stars' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER star_size as parameter.
     */

    public static String stars(int star_size) {
        //dado que recibimos la cantidad de estrellas que tiene que haber hacia cada lado, partiendo desde el centro
        //vamos a hacer una matriz de tamaño star_size*2+1
    Integer matriz_size = star_size*2+1;
        String [][] matriz = new String [matriz_size][matriz_size];
        //instanciamos un stringbuffer que nos va a permitir convertir el resultado de esta matriz
        //en un solo string para alimentar el valor de retorno
        StringBuffer sb = new StringBuffer();
        
        //un doble for para recorrer la matriz
    for (int i=0;i<matriz_size;i++){
        for(int j=0;j<matriz_size;j++){
            //¿cuando dibujo "*"? cuando este en la fila o columna que corresponda al centro, (i==star_size||j==star_size)
            //en la diagonal principal, i==j o en la diagonal transpuesta  i+j==matriz_size-1
            if (i==star_size||j==star_size||i==j||i+j==matriz_size-1){
                matriz[i][j]="*";
            } else {
                matriz [i][j]=" ";
            }
            //luego, el valor de cada celda de la matriz lo coloco en el string bufferer
            sb.append(matriz[i][j]);
            if(j==matriz_size-1){
                //y si estoy en la ultima celda de cada fila, le agrego un salto de linea
                sb.append("\n");
            }
        }
    }
        
    return sb.toString();

    }

}

public class Solution1 {
    public static void main(String[] args) {
        //el codigo que venia de base usaba inputbufferer y buffererreader
        //los reemplaze por scanner y sout
        Scanner sc = new Scanner(System.in);
        int star_size = sc.nextInt();

        String result = Result1.stars(star_size);
        
        System.out.println(result);


        sc.close();
        
    }
}
//EJEMPLO INPUT 5
//5
//*    *    *
// *   *   * 
//  *  *  *  
//   * * *   
//    ***    
//***********
//    ***    
//   * * *   
//  *  *  *  
// *   *   * 
//*    *    *


