package EDA5Algoritmos;
/**
 *
 * @author FabricioGrosso
 */
//An old GPS has information about time and speed when you move, and they have asked us for a module to calculate the distance since their original programmers disappeared.
//
//Input Format
//
//A single string time_speed_data.
//
//Constraints
//
//The format of time_speed_data will be:
//
//<time_value><time_unit>@<speed_value><speed_unit>
//Where:
//
//time_value is an integer between 1 and 100.
//time_unit is a string and one of the following:
//"h" (hours)
//"m" (minutes)
//speed_value is an integer between 0 and 100.
//speed_unit is a string and one of the following:
//"km/h" (kilometers per hour)
//"m/h" (meters per hour)
//Output Format
//
//The calculated distance, in meters and as an integer.
//
//If the answer were to have a fractional part, you should ignore it and remove it.
//
//Sample Input 0
//
//60m@1km/h
//Sample Output 0
//
//1000
//Explanation 0
//
//If for 60 minutes you move at 1 kilometer per hour, then you will do 1000 meters.
//
//Sample Input 1
//
//30m@1km/h
//Sample Output 1
//
//500
//Explanation 1
//
//If for 30 minutes you move at 1 kilometer per hour, then you will do 500 meters.
//
//Sample Input 2
//
//60m@1m/h
//Sample Output 2
//
//1
//Explanation 2
//
//If for 60 minutes you move at 1 meter per hour, then you will do 1 meter.
//
//Sample Input 3
//
//1m@1m/h
//Sample Output 3
//
//0
//Explanation 3
//
//If for 1 minute you move at 1 meter per hour, then you will do almost nothing.
//
//Sample Input 4
//
//1h@1km/h
//Sample Output 4
//
//1000
//Explanation 4
//
//If for 1 hour you move at 1 kilometer per hour, then you will do 1000 meters.
//
//Sample Input 5
//
//20h@100km/h
//Sample Output 5
//
//2000000
//Explanation 5
//
//If for 20 hours you move at 100 kilometers per hour, then you will do 2000000 meters.

import java.io.*;
import java.util.Scanner;
class Result2 {

    /*
     * Complete the 'distance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING time_speed_data as parameter.
     */
    public static int distance(String time_speed_data) {
        //lo primero que sabemos es que el caracter '@' nos separa el string,
        //dejando por un lado el tiempo y su unidad y por otro la velocidad y su unidad.
        //no sabemos en qué posición del string va a estar el '@', porque eso depende de cuantas cifras
        //tenga la cantidad de tiempo.. así que lo averiguamos con el metodo indexof
        int arroba = time_speed_data.indexOf('@');
        //como la unidad de tiempo sí o sí es un solo caracter 'h' o 'm', ya podemos asumir que en la posicion anterior
        // al '@' esta la unidad de tiempo. Así que cortamos en tres: lo que esta entre 0 y arroba-1 es la cantidad de tiempo
        //el caracter de la posicion arroba-1 la unidad; y todo lo que quede despues del arroba tiene que ver con la velocidad
        Integer time_value = Integer.parseInt(time_speed_data.substring(0, arroba - 1));
        String time_unit = time_speed_data.substring(arroba - 1, arroba);
        String speed_value_aux = time_speed_data.substring(arroba + 1);
        //el problema que se presenta aca es el siguiente: lo que hagamos tiene que poder 
        //interpretar inputs tan diversos como 100km/h 1km/h 1m/h 10km/h.. donde la cantidad puede ser de 1,2 o 3 digitos
        //y la unidad de medida de 3 o 4 caracteres; asi que contar desde una punta o la otra del string no sirve.
        //Así que necesitamos una forma de AVERIGUAR cuantos caracteres PUEDEN ser convertidos a números

        int index = 0;
        //generamos un contador, y metemos ese contador y el string con la parte de velocidad en un metodo nuevo (ir al metodo)
        while (canParseInt(speed_value_aux, index)) {
            index++;
        }
        //entonces en index vamos a tener la cantidad de caracteres que se pueden parsear, así que es nuestro argumento
        //para separar la parte numérica
        Integer speed_value = Integer.parseInt(speed_value_aux.substring(0, index));
        //y todo lo que queda es la unidad de medida
        String speed_unit = speed_value_aux.substring(index);

        //Ahora a hacer los cálculos: arrancamos con el numero que nos quedó en time_value
        //por ahora trabajamos en double para hacer mas facil algunas operaciones
        double resultado = time_value;

        //que pasa con la unidad? puede ser 1 minuto o 1 hora. decido trabajar en HORAS xq es la constante
        //en la unidad de velocidad (km/h o m/h); así que si estoy en minutos divido el res por 60
        if (time_unit.equals("m")) {
            resultado /= 60;
        }
        //recuperamos la velocidad a la que se traslado y la pasamos a double
        double velocidad = speed_value;
        //como el resultado final tiene que estar en metros, me comprometo con esta unidad de medida
        //así que si el dato viene en km/h (empieza con k), voy a multiplicarlo por 1000 para convertirlo a m/h
        if (speed_unit.substring(0, 1).equals("k")) {
            velocidad *= 1000;
        }
        //ya tengo por un lado la cantidad de tiempo en horas que se traslado
        //y por el otro a que velocidad en metros por hora. 
        //solo necesito la parte entera de la operación, así que lo hago con Math.Floor

        return (int) Math.floor(resultado * velocidad);

    }

    public static boolean canParseInt(String speed_value, int index) {
        //Sabemos que el método Integer.parseInt('1'), recibe el String '1' y lo convierte en el Integer 1;
        //y si lo probamos con Integer.parseInt('k') genera un NumberFormatException pq no tiene idea de a que numero
        //corresponde la letra K. Entonces podemos hacer lo siguiente:
        //INTENTAR convertir de a un caracter por vez a Integer. Si se puede convertir, el carácter era un número,
        //si da una Excepción (que atrapamos para que no explote el programita) no se podía.
        //así que este método que regresa T o F va a ser llamado dentro de un bucle con un contador que va creciendo,
        //para saber cuántos carácteres puedo parsear a integer

        try {
            Integer.parseInt(speed_value.substring(0, index + 1));

            return true;
        } catch (NumberFormatException e) {

            return false;
        }

    }

}

public class Solution2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //leemos el string con el formato <time_value><time_unit>@<speed_value><speed_unit>
        //ejemplos de input
        //30m@1km/h 60m@1m/h 1m@1m/h 20h@100km/h
        String time_speed_data = sc.nextLine();

        //ese string viaja al metodo distance de la clase result y regresa un int
        int result = Result2.distance(time_speed_data);
        //que se imprime por pantalla.
        System.out.println(result);

        sc.close();

    }
}
