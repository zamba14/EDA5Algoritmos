package EDA5Algoritmos;

/**
 *
 * @author FabricioGrosso
 */
//A poor Roman family raises goats. Their neighbors sometimes lend them some, and sometimes they also reproduce.
//
//That is why they have asked us for a program to calculate how many goats they have after each loan or birth.
//
//Input Format
//
//A single string my_goats_operator_my_other_goats.
//
//Constraints
//
//my_goats_operator_my_other_goats has a form of:
//
//<Roman number from 1 to 5><Operator + or *><Roman number from 1 to 5>
//For example:
//
//II+I
//V*I
//IV+III
//Output Format
//
//The Roman number that results from evaluating the given expression.
//
//Sample Input 0
//
//I+I
//Sample Output 0
//
//II
//Sample Input 1
//
//II+I
//Sample Output 1
//
//III
//Sample Input 2
//
//III+I
//Sample Output 2
//
//IV
//Sample Input 3
//
//I*I
//Sample Output 3
//
//I
//Sample Input 4
//
//II*I
//Sample Output 4
//
//II
//Sample Input 5
//
//III*I
//Sample Output 5
//
//III
//Sample Input 6
//
//IV*IV
//Sample Output 6
//
//XVI
//Sample Input 7
//
//V*IV
//Sample Output 7
//
//XX
import java.util.Scanner;

class Result3 {

    /*
     * Complete the 'growingLittleRomans' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING my_goats_operator_my_other_goats as parameter.
     */
    public static String growingLittleRomans(String my_goats_operator_my_other_goats) {
        // Lo primero que sabemos es que tenemos un operador, que va a ser + o *; así que usemos ese operador
        //para separar los dos números romanos. Como sí o sí va a contener uno de los dos, lo resolvemos con un if-else
        int operador_indice;
        if (my_goats_operator_my_other_goats.contains("+")) {
            operador_indice = my_goats_operator_my_other_goats.indexOf("+");
        } else {
            operador_indice = my_goats_operator_my_other_goats.indexOf("*");
        }
        //Ya podemos separar el string que entra como parametro en tres: El operador y ambos numeros romanos
        String operador = my_goats_operator_my_other_goats.substring(operador_indice, operador_indice + 1);
        String my_goats = my_goats_operator_my_other_goats.substring(0, operador_indice);
        String my_other_goats = my_goats_operator_my_other_goats.substring(operador_indice + 1);
        //en el metodo romanCalculator vamos a convertir cada string en el nro que le corresponde; y en un switch
        //vamos a realizar la operacion que el operador nos indique
        int resultado_aritmetico;
        switch (operador) {
            case "+":
                resultado_aritmetico = romanCalculator(my_goats) + romanCalculator(my_other_goats);
                break;
            case "*":
                resultado_aritmetico = romanCalculator(my_goats) * romanCalculator(my_other_goats);
                break;
            default:
                resultado_aritmetico = 0;
        }
        
        //con ese numero en la mano, lo pasamos por el metodo romanConversor y lo devolvemos al main   

        return romanConversor(resultado_aritmetico);

    }

    public static int romanCalculator(String roman) {
        //metodo para saber qué numero arabigo es cada romano. recibe un string y a través de un for
        //va a ir leyendo cada una de las cifras, usando un switch para establecer el valor numerico de cada una
        char romano;
        int arabigo;

        //nota: el input de las cabritas es del 1 al 5; no hace falta contemplar los casos de X-L-C-D o M, pero dado
        //que pienso reciclar este codigo para la segunda parte (pasar el resultado aritmetico a romanos), lo hago entero
        //y listo;
        //vamos a ir guardando el valor de cada cifra en un arreglo de tamaño = a cantidad de letras en el nro romano
        int[] valor = new int[roman.length()];
        for (int i = 0; i < roman.length(); i++) {
            romano = roman.charAt(i);
            switch (romano) {
                case 'I':
                    arabigo = 1;
                    break;
                case 'V':
                    arabigo = 5;
                    break;
                case 'X':
                    arabigo = 10;
                    break;
                case 'L':
                    arabigo = 50;
                    break;
                case 'C':
                    arabigo = 100;
                    break;
                case 'D':
                    arabigo = 500;
                    break;
                case 'M':
                    arabigo = 1000;
                    break;
                default:
                    arabigo = 0;
            }
            //hemos recuperado cuanto vale cada digito y lo guardamos en el correspondiente casillero del arreglo
            valor[i] = arabigo;

        }
 //ya tenemos los digitos en orden, solo nos queda saber si hay que sumar o restar...
        //cada digito le va a preguntar al siguiente si es mayor que el mismo, si eso es true el digito se va a convertir
        // en negativo. el ultimo digito del arreglo siempre suma. reciclo la variable arabigo y almaceno la suma ahi
        arabigo = 0;
        for (int i = 0; i < valor.length - 1; i++) {
            //valor.lengt-1 para que no se me vaya outofarrayindex el bucle, total al ultimo digito no hay que preguntarle nada
            if (valor[i] < valor[i + 1]) {
                //si el siguiente digito es mayor, el digito preguntado se convierte en su opuesto 
                valor[i] = -valor[i];
            }
//vamos sumando todos los valores del arreglo en la variable arabigo
            arabigo += valor[i];
        }
//y le agregamos el ultimo digito que siempre suma (y que nos quedo sin recorrer en el bucle)
        arabigo += valor[valor.length - 1];

        return arabigo;
    }

    public static String romanConversor(int arabigo) {
//ahora tenemos que hacer el proceso inverso, pasar de arabigo a romano. primero vamos a comprobar cuantas cifras tiene
//para eso le preguntamos al numero cuantas veces se puede dividir por 10 sin dar un nro menor a 1 y guardamos el resultado en un contador
//para no perder el nro original, guardamos en un aux su valor actual
        int contador = 0;
        int arabigo_aux = arabigo;
        while (arabigo_aux / 10 >= 1) {
            arabigo_aux /= 10;
            contador++;
        }
       
//el contador nos da la potencia de 10 que corresponde a la posicion del primer digito
        //almacenemos cada digito por separado en un arreglo reseteamos el aux y copiamos el contador
        arabigo_aux = arabigo;
        int contador_aux = contador;
        int[] arreglo = new int[contador_aux + 1];
        //llenamos el arreglo
        for (int i = 0; i <= contador_aux; i++) {
            //si el auxiliar es mayor que diez, vamos a extraer el valor entero de cada digito y multiplicarlo por la 
            //potencia de diez que le corresponda
            if (arabigo_aux > 10) {
                arreglo[i] = (int) ((int) Math.floor((arabigo_aux / Math.pow(10, contador))));
                arabigo_aux -= arreglo[i] * Math.pow(10, contador);
                contador--;

            } else {
                //si no es mayor que diez, estamos en la unidad así que sólo guardamos ese valor
                arreglo[i] = arabigo_aux;
            }

        }
        //hecho esto nos queda un arreglo con el valor entero de cada posicion, por ejemplo 
        // INPUT L*L = 2500, el arreglo dice [2, 5, 0, 0]
        //usamos un stringbuilder para ir concatenando el resultado

        StringBuilder sb = new StringBuilder();
        
        //y por cada valor del arreglo, usamos el metodo llamarReverse con su dígito y su potencia de 10
        for (int i = 0; i < arreglo.length; i++) {
            sb.append(llamarReverse(arreglo[i],arreglo.length-i));
        }
        return sb.toString();
    }

    public static char reverse(int arabigo) {
        //este metodo recibe un entero que corresponda a un romano y devuelve su caracter correspondiente
        char romano;
       
        switch (arabigo) {
            case 1:
                romano = 'I';

                break;
            case 5:
                romano = 'V';

                break;
            case 10:
                romano = 'X';

                break;
            case 50:
                romano = 'L';

                break;
            case 100:
                romano = 'C';

                break;
            case 500:
                romano = 'D';

                break;
            case 1000:
                romano = 'M';

                break;
            default:
                //la E es de ERROR, es de esperarse que nunca se ejecute el default...
                romano = 'E';
        }
        return romano;

    }

    public static String llamarReverse(int digito, int posicion) {
        //este metodo recibe el valor del digito y si corresponde a unidad decena centena u.de mil etc
        StringBuilder sb = new StringBuilder();
        switch (digito) {
            case 1:
            case 2:
            case 3:
                for (int i = 0; i < digito; i++) {
                    sb.append(reverse((int) Math.pow(10, posicion-1)));
                  //dato de color, el metodo funciona para las unidades porque 10^0=1   
                  
                }
                //casos 1,2 y 3, llama al caracter que corresponda a la unidad 1, 2 o 3 veces
                break;
            case 4:
                sb.append(reverse((int) Math.pow(10, posicion-1)));
                //caso 4, llama al caracter de la unidad una vez y luego al del 5 (por eso aca no tengo break)
            case 5:
                sb.append(reverse(5*(int) Math.pow(10, posicion-1)));
                //caso 5, llama al de la unidad multiplicado por 5
                break;
            case 6:
            case 7:
            case 8:
                //casos 6,7y8 llama primero al de 5 y luego hace lo mismo que en 1,2,3 pero restando 5
                sb.append(reverse(5*(int) Math.pow(10, posicion-1)));
                for (int i = 0; i < digito-5; i++) {
                   sb.append(reverse((int) Math.pow(10, posicion-1)));
                }
            break;
            case 9:
                //caso 9, llama al de la unidad y luego al de la unidad*10
                sb.append(reverse((int) Math.pow(10, posicion-1)));
                sb.append(reverse((int) Math.pow(10, posicion)));
                break;
           
            default:
                if (digito>1 && posicion==1){
                    sb.append(reverse((int) Math.pow(10, posicion)));
                }
                
                

        }
        return sb.toString();
    }

}

public class Solution3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();

        String result = Result3.growingLittleRomans(n);
        System.out.println(result);
        sc.close();
    }
}
