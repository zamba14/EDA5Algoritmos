package EDA5Algoritmos;

/**
 *
 * @author FabricioGrosso
 */
//For any given word consisting only of simple letters (a-z), we define its "word wave" to be the result of comparing consecutive letter pairs to decide whether the next letter should be drawn at a height that's higher, lower or equal to the previous one.
//
//For example, given the word accb, its corresponding word wave is:
//
// cc 
//a  b
//Explanation:
//
//The first pair, ac, is drawn increasing the height of c, because a comes before c in the English alphabet. That is, a < c.
//The second pair, cc, is drawn without any height change whatsoever, because the letters are equal. That is, c = c.
//The third pair, cb, is drawn decreasing the height of b, because c comes after b in the English alphabet. That is, c > b.
//Given a word s, are you up for the challenge of building its word wave?
//
//Input Format
//
//A single word s.
//
//Constraints
//
//s is guaranteed to:
//
//contain only lowercase English letters (a-z).
//have a length between 1 and 100.
//Output Format
//
//The word wave. See the sample output for more details.
//
//Sample Input 0
//
//abc
//Sample Output 0
//
//  c
// b 
//a  
//Sample Input 1
//
//dcbabcd
//Sample Output 1
//
//d     d
// c   c 
//  b b  
//   a   
//Sample Input 2
//
//ababababababccdaab
//Sample Output 2
//
//              d  b
//            cc aa 
// b b b b b b      
//a a a a a a       
//NOTA: TODOS LOS CASOS DE PRUEBA PROVISTOS COMIENZAN DESDE LA ESQUINA SUPERIOR O INFERIOR,
//DURANTE EL TESTEO DEL CODIGO TAMBIEN SE USAN LOS SIGUIENTES STRING:
//dabdabdab
//allyourbasearebelongtous
//tellmewhatyouwantwhatyoureallyreallywant
//bazyxwvutsrqponmlkjihgfeddefghijklmnopqrstuvwxyzarqponmlkjihgfeddefghijklmnopqrstuvwxyzab
//vbqwofobgwcvomkqdxftwinlwnqiqnoemhdildrvwpfkoqjqikskekwkoybkndwyigytbfizbvwtdiidtpjicgpaclqzznbezxxv
//nwbfytojqenyexbrqujuanjltfqdweseqllklhdfvuvntqlzcosfhtglnieuacgprfvjcgloenuewuzbdqlqrdydwaebofpgznot
// CUYO DIBUJO NO COMIENZA DESDE LA PRIMERA O ULTIMA POSICION DE LA MATRIZ

import java.util.Arrays;
import java.util.Scanner;

class Result4 {

    /*
     * Complete the 'word_wave' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    public static String word_wave(String s) {
        // lo primero que sabemos de la ola es su ancho, que corresponde a la longitud del string
        String[] ancho = new String[s.length()];
        //llenemos el array
        for (int i = 0; i < s.length(); i++) {
            ancho[i] = s.substring(i, i + 1);
        }

        //ahora vamos a llenar el array de movimiento, que me dice si el proximo digito tengo que
        //ponerlo arriba abajo o en la misma linea. 
        int[] movimiento = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            
            //tenemos que usar los ==0 <0 y >0 porque el compareTo de la clase String devuelve
            //la diferencia en Unicode entre los caracteres ("a".compareTo("c")=-3)
            //asi que lo dejamos como 0 1 o -1
            if (ancho[i - 1].compareTo(ancho[i]) == 0) {
                movimiento[i] = 0;
            }
            if (ancho[i - 1].compareTo(ancho[i]) < 0) {
                movimiento[i] = 1;

            }
            if (ancho[i - 1].compareTo(ancho[i]) > 0) {
                movimiento[i] = -1;

            }
            

        }
        //falta saber el alto de la matriz, que vamos a calcularlo sacando el tramo más largo seguido en la misma direccion
        //generamos un contador y variables llamadas cursor para guardar los valores mas alto y bajo que tome
        int contador = 0;
        int cursor_up = 0;
        int cursor_down = 0;

        for (int i = 1; i < movimiento.length; i++) {
            //alto va a ir sumando los valores acumulados del movimiento
            contador += movimiento[i];
//los hitos positivos quedan en cursor_up y los negativos en cursor_down
            if (cursor_up < contador) {

                cursor_up = contador;
            }
            if (cursor_down > contador) {

                cursor_down = contador;
            }

        }

 //el alto de la tabla surge de sumar estos dos hitos (como cursor_down es negativo para sumar lo restamos)
       
        int alto_matriz = cursor_up - cursor_down;

//le agregamos 1 por el indice 0
        String[][] wave = new String[Math.abs(alto_matriz) + 1][ancho.length];
        //y usamos cursor up para encontrar la punta desde la que empieza el dibujo
        int j=cursor_up;

        for (int i = 0; i < ancho.length; i++) {
            //en el arreglo de movimiento tenemos la indicacion de cuanto movernos en las filas
            j -= movimiento[i];
            //y copiamos el valor que corresponde en cada columna
            wave[j][i] = ancho[i];

        }

        //instanciamos un stringbuffer que nos va a permitir convertir el resultado de esta matriz
        //en un solo string para alimentar el valor de retorno
        StringBuffer sb = new StringBuffer();

        //recorremos la matriz por ultima vez con el doble for
        for (int i = 0; i < Math.abs(alto_matriz)+1; i++) {
            for (int k = 0; k < ancho.length; k++) {
                if (wave[i][k] != null) {
                    //si el valor es distinto de nulo, grabamos su letra en el bufferer
                    sb.append(wave[i][k]);
                } else {
                    //sino grabamos un espacio
                    sb.append(" ");
                }

            }
            //salido de cada iteracion del for chiquito grabamos un salto de linea

            sb.append("\n");

        }
        //y regresamos al main el contenido del bufferer
        return sb.toString();
    }
}

public class Solution4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String result = Result4.word_wave(s);
        System.out.println(result);

        sc.close();

    }
}
