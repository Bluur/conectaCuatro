
package Funciones;

public class conectaCuatro {
    /*-----Funciones Principales para el funcionamiento del programa-----*/
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

    public static boolean volverAJugar() {
        String mensajeFinal = leerDatosTeclado.leerString("¿Quieres volver a jugar?(Y/N)", "y", "n");
        return mensajeFinal.equals("y");
    } 
}
