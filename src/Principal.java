import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        String opcion;
        Scanner consola = new Scanner(System.in);

        while(true){
            System.out.println("""
                    Escoje de cuál películas quieres ver la información: (Indica solo el número)
                    1. La Amenaza Fantasma.
                    2. El ataque de los clones.
                    3. La venganza de los Sith.
                    4. Una nueva esperanza.
                    5. El imperio contraataca.
                    6. El retorno del Jedi.
                    7. Cerrar programa.""");

            opcion = consola.nextLine();

            if (opcion.equalsIgnoreCase("7")){
                break;
            }

            ConsultaPelicula consulta = new ConsultaPelicula();
            consulta.consultarPeli(opcion);
        }

    }
}
