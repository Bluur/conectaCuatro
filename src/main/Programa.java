package main;

import Funciones.leerDatosTeclado;

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
            Programa.mostrarTablero(tablero);
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
                    colocar = Programa.colocarFicha(tablero, columna, turnoActual);
                }
                
                //Muestra del tablero tras colocar la ficha
                Programa.mostrarTablero(tablero);
                if(turno >= 7){
                    ganador = Programa.hayGanador(tablero, columna, turnoActual);
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
                finJuego = Programa.finJuego(tablero);
                if(finJuego){
                    System.out.println("El tablero está lleno, hay un empate");
                }
                
                //Pide un mensaje para seguir jugando o no
                if(ganador){
                    seguirJugando = volverAJugar();
                }
            }while(ganador != true && finJuego != true);
        }while(seguirJugando);    
    }
    public static void mostrarTablero(int tablero[][]){
        //Recorre el tablero y saca valores diferentes en caso de ser 1, 2 o 0
        for(int i=0; i<6; i++){
            for(int j=0;j<7;j++){
                switch (tablero[i][j]) {
                    case 0 -> {
                        System.out.print("| |");
                    }
                    case 1 -> {
                        System.out.print("|X|");
                    }
                    default -> {
                        System.out.print("|O|");
                    }
                }
            }
            System.out.print("\n");
        }
    }
    public static boolean colocarFicha(int tablero[][], int columna,int turnoActual){
        //Si la posición seleccionada arriba tiene un valor diferente de 0 significa que esta lleno
        if(tablero[0][columna] != 0){
            System.out.println("Esa columna está llena, intentalo de nuevo");
            return false;
        }else{
            //Recorremos la columna hasta que demos con una ficha contraria o sea el final
            int posicionFinal = 0;
            for(int i=0; i<6; i++){
                if(tablero[i][columna] == 0){
                    posicionFinal = i;
                }
            }
            //Asignamos la ficha a la posición final vacia 
            if(turnoActual == 1){
                tablero[posicionFinal][columna] = 1;
            }else if(turnoActual == 2){
                tablero[posicionFinal][columna] = 2;
            }
            return true;
        }
    }
    public static boolean hayGanador(int tablero[][], int columna, int turnoActual){
        int fila = 0;
        boolean comprobacion = true;
        
        //vamos a calcular la posición en la que la ficha cayó
        for(int i=0;i<6 && comprobacion;i++){
            if(tablero[i][columna] == turnoActual){
                fila = i;
                comprobacion = false;
            }
        }
        //Variables para comprobar
        int repeticionHorizontal = 1;
        int repeticionVertical = 1;
        int repeticionDiagDesc = 1;
        int repeticionDiagAsc = 1;
        boolean continuar = true;
        
        
        //Comprobación horizontal
        if(((columna-1 >= 0) && tablero[fila][columna-1] == turnoActual) || ((columna+1 < 7) && tablero[fila][columna+1] == turnoActual)){
            //izquierda
            int contador = 1;
            while(continuar){
                if(columna-contador >= 0 && tablero[fila][columna-contador] == turnoActual){
                    repeticionHorizontal ++;
                    contador++;
                }else{
                    continuar = false;
                }
            }
            continuar = true;
            //Derecha
            contador = 1;
            while(continuar){
                if(columna+contador < 7 && tablero[fila][columna+contador] == turnoActual){
                    repeticionHorizontal ++;
                    contador++;
                }else{
                    continuar = false;
                }
            }
            //Mensaje con la combinación ganadora y devuelve boolean
            if(repeticionHorizontal >= 4){
                System.out.println("Horizontal");
                return repeticionHorizontal >= 4;
            }
            
        }
        //Comprobación vertical, solo hacia abajo porque se tiene en cuenta la última posición
        if(fila+1 < 6 && tablero[fila+1][columna] == turnoActual){
            //abajo
            int contador = 1;
            while(continuar){
                if(fila+contador < 6 && tablero[fila+contador][columna] == turnoActual){
                    repeticionVertical++;
                    contador++;
                }else{
                    continuar = false;
                }
            }
            //Mensaje con la combinación detectada y devuelve boolean
            if(repeticionVertical >= 4){
                System.out.println("Vertical");
                return repeticionVertical >= 4;
            }
            
        }
        //Comprobaci�n Diagonal Descendente
        if(fila-1 >= 0 && columna-1 >= 0  && tablero[fila-1][columna-1] == turnoActual ||fila+1 < 6 && columna+1 < 7 && tablero[fila+1][columna+1]==turnoActual){
            //izquierda arriba
            int contador = 1;
            while(continuar){
                if(fila-contador >= 0 && columna-contador >= 0 && tablero[fila-contador][columna-contador] == turnoActual){
                    repeticionDiagDesc += 1;
                    contador += 1;
                }else{
                    continuar = false;
                }
            }
            //derecha abajo
            continuar = true;
            contador = 1;
            while(continuar){
                if(fila+contador < 6 && columna+contador < 7 && tablero[fila+contador][columna+contador] == turnoActual){
                    repeticionDiagDesc += 1;
                    contador += 1;
                }else{
                    continuar = false;
                }
            }
            //Mensaje con la cobinación ganadora y devuelve el boolean
            if(repeticionDiagDesc >= 4){
                System.out.println("Diagonal Descencente");
                return repeticionDiagDesc >= 4;
            }
            
        }
        //Comprobaci�n Diagonal Ascendente
        if(((fila-1 >= 0 && columna+1 < 7) && tablero[fila-1][columna+1] == turnoActual )||((fila+1 < 6 && columna-1 >= 0) && tablero[fila+1][columna-1] == turnoActual)){
            //derecha arriba
            int contador = 1;
            continuar = true;
            while(continuar){
                if(fila-contador >= 0 && columna+contador < 7 && tablero[fila-contador][columna+contador] == turnoActual){
                    repeticionDiagAsc += 1;
                    contador +=1;
                }else{
                    continuar = false;
                }
            }
            //izquierda abajo
            continuar = true;
            contador = 1;
            while(continuar){
                if(fila+contador < 6 && columna-contador >= 0 && tablero[fila+contador][columna-contador] == turnoActual){
                    repeticionDiagAsc += 1;
                    contador += 1;
                }else{
                    continuar = false;
                }
            }
            //Mensaje sacando el tipo de conbinación detectada y devuelve el boolean
            if(repeticionDiagAsc >= 4){
                System.out.println("Diagonal Ascendente");
                return repeticionDiagAsc >= 4;
            }
            
        }
        return false;
    }
    
    public static boolean finJuego(int tablero[][]){
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                if(tablero[i][j] == 0){
                    return false;
                }
            }
        }
        return false;
    }

    private static boolean volverAJugar() {
        String mensajeFinal = leerDatosTeclado.leerString("¿Quieres volver a jugar?(Y/N)", "y", "n");
        return mensajeFinal.equals("y");
    }
}

