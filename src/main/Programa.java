package main;

import Funciones.leerDatosTeclado;
import Funciones.conectaCuatro;

public class Programa {

    //He conseguido que funcione, el único problema es que cuando en horizontal o diagonal es igual a 0 o 6 no funciona bien la operación lógica
    public static void main(String[] args) {
        //Creación de variables y tablero, asignación al azar de un jugador inicial.
        boolean seguirJugando = false;
        
        do{
            int tablero[][] = new int[6][7];
            final int j1 = 1;
            final int j2 = 2;
            int jugadorInicial = (int) Math.floor(Math.random()*(2-1+1)+1);

            //Textos introductorios
            System.out.println("Bienvenidos al juego de Conecta 4");        

            //Asignación de nombres a los jugadores
            String jugador1 = leerDatosTeclado.leerString("Dame el nombre de el jugador 1 ");
            String jugador2 = leerDatosTeclado.leerString("Dame el nombre del jugador 2 ");
        
        
            //Impresión de información sobre que jugador comienza primero
            if(jugadorInicial == j1){
                System.out.println("El jugador que comienza es "+jugador1);
            }else if(jugadorInicial == j2){
                System.out.println("El jugador que comienza es "+jugador2);
            }

            System.out.println("Este es vuestro tablero ");
            conectaCuatro.mostrarTablero(tablero);
            System.out.println("Que comience el juego");

            //Comienzo del juego, Estructura un do/while, que se ejecutara mientras ganador y finJuego sean Falsos
            //Asignación de variables de control de flujo
            boolean ganador = false;
            boolean finJuego;
            boolean colocar = false;
            int turnoActual = jugadorInicial;
            int columna = 0;
            int turno = 1;
            
            //Bucle que hace que se ejecute si el usuario quiere volver a jugar
            do{
                //Colocar ficha, primero pedimos posiciones y adaptamos y despues llamamos a la función
                while(colocar != true){
                    //Iteración 2, introducir una ia básica, que ramdomice casillas pero que sea capaz de tapar al contrincante y ganar alguna que 
                    //otra partida, colocar un if aqui debajo que en caso de que el turno sea de la ia, llame a la función correspondiente
                    
                    columna = Funciones.leerDatosTeclado.LeerEntero("Dame la posición en la que quieres colocar tu ficha (1-7): ", 1, 7);
                    columna -= 1;
                    
                    //En vez de que la función colocar calcule la casilla en la que se coloca, haré otra función con la que calcularlo ara así guardar la última posición
                    colocar = conectaCuatro.colocarFicha(tablero, columna, turnoActual);
                }
                
                //Muestra del tablero tras colocar la ficha
                conectaCuatro.mostrarTablero(tablero);
                if(turno >= 7){
                    ganador = conectaCuatro.hayGanador(tablero, columna, turnoActual);
                }
                colocar = false;
                
                //Ganador, en caso de haberlo, mensaje dependiendo de el turno que toque
                if(!ganador){
                    if(turnoActual == 1){
                        turnoActual = 2;
                        System.out.println("Es el turno de "+jugador2);
                    }else{
                        turnoActual = 1;
                        System.out.println("Es el turno de "+jugador1);
                    }
                }
                
                //Suma un turno para ejecutar ganador solo a partir de la 7º ronda
                turno++;
                
                //Mensaje de ganador
                if(ganador && turnoActual == 2){
                    System.out.println("Ha ganado la partida "+jugador2);
                }else if(ganador && turnoActual == 1){
                    System.out.println("Ha ganado la partida "+jugador1);
                }
                
                //Comprueba que halla casillas vacias
                finJuego = conectaCuatro.finJuego(tablero);
                if(finJuego){
                    System.out.println("El tablero está lleno, hay un empate");
                }
                
                //Pide un mensaje para seguir jugando o no
                if(ganador){
                    seguirJugando = conectaCuatro.volverAJugar();
                }
            }while(ganador != true && finJuego != true);
        }while(seguirJugando);    
    }
}

