
package main;

import Funciones.funcionesConectaCuatro;

public class ConectaCuatro {
    private int[][] tablero;
    
    //Constructor
    public ConectaCuatro(){
        this.tablero = new int[6][7];
    }
    
    //Métodos
    public boolean colocarFicha(int columna, int turnoActual){
        //Si la posición seleccionada arriba tiene un valor diferente de 0 significa que esta lleno
        if(tablero[0][columna] != 0){
            System.out.println("Esa columna está llena, intentalo de nuevo");
            return false;
        }else{
            //Recorremos la columna hasta que demos con una ficha contraria o sea el final
            int posicionFinal = 0;
            for(int i=0; i<6; i++){
                if(this.tablero[i][columna] == 0){
                    posicionFinal = i;
                }
            }
            //Asignamos la ficha a la posición final vacia 
            if(turnoActual == 1){
                this.tablero[posicionFinal][columna] = 1;
            }else if(turnoActual == 2){
                this.tablero[posicionFinal][columna] = 2;
            }
            return true;
        }
    }
    
    public boolean hayGanador(int columna, int turnoActual){
        return funcionesConectaCuatro.hayGanador(this.tablero, columna, turnoActual);
    }
    
    public boolean juegoTerminado(){
        return funcionesConectaCuatro.finJuego(this.tablero);
    }
    
    public void imprimir(){
        funcionesConectaCuatro.mostrarTablero(tablero);
    }
}
