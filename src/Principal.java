import com.biogade.challengueconversormonedas.modelos.Divisa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        String opcion;
        Scanner consola = new Scanner(System.in);
        double valor;

        Divisa cambio = new Divisa();

        //Menú de Opciones para el Usuario:
        while(true){
            System.out.println("""
                    ***********************************************************************************
                    Sea bienvenido/a al conversor de moneda. Selecciona una opción:
                    1. Dolar =>> Pesos Colombianos.
                    2. Pesos Colombianos =>> Dolar.
                    3. Dolar =>> Libra Esterlina.
                    4. Libra Esterlina =>> Dolar.
                    5. Euro =>> Real Brasileño.
                    6. Real Brasileño =>> Euro.
                    7. Rupia India =>> Pesos Colombianos.
                    8. Pesos Colombianos =>> Rupia India.
                    9. Salir.
                    ***********************************************************************************""");

            opcion = consola.nextLine();

            //Si la opción es un número entre el 1 al 8 entra en el condicional:
            if (opcion.matches("[1-8]")) {
                System.out.println("Ingrese el valor a cambiar: ");

                /*Se realiza un try/catch para validar que el valor que se ingrese sea un número, ya que en este caso
                  Se obliga a que sea un valor numérico por el "consola.nextDouble()", por lo que si ingresan una letra
                  O un caracter especial arrojará un error.*/
                try {
                    valor = consola.nextDouble();
                    /*El consola.nextLine() se pone para pasar a la siguiente línea, para que luego no hayan problemas en consola debido
                      a la toma de nextDouble.*/
                    consola.nextLine();

                    //El valor debe ser mayor o igual a 0 para poder hacer una conversión:
                    if (valor >= 0){
                        cambio.CambiarDivisa(Integer.valueOf(opcion), valor);
                    }else {
                        System.out.println("El valor debe ser positivo...");
                    }

                }catch (InputMismatchException e){
                    //Como recibió una letra o caracter especial se ejecutará este mensaje.
                    /*El consola.nextLine() se pone para pasar a la siguiente línea, para que luego no hayan problemas en consola debido
                      a la toma de nextDouble.*/
                    System.out.println("Debes ingresar un número...");
                    consola.nextLine();
                }


            } else if(opcion.matches("9")){
                //Si el valor es 9 de todos modos debe ingresar a la función para guardar el historial.
                cambio.CambiarDivisa(Integer.valueOf(opcion), 9);
            }else{
                //Si se ingresa cualquier otro valor lo marcará como erróneo y pedirá un valor permitido entre el 1 al 9.
                System.out.println("Opción inválida, por favor selecciona una opción del 1 al 9.");
            }

            //Si la opción seleccionada fué 9 entonces se ejecuta el "break" para terminar el ciclo While y finalizar el programa.
            if (opcion.equals("9")){
                break;
            }
        }

        consola.close();
    }
}