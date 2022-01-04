
package Funciones;

import java.util.Arrays;

public class operacionesArrays {
    public static int[] crearArrayEnteros(int n){
        int array[] = new int[n];
        return array;
    }
    public static double[] crearArrayDouble(int n){
        double [] array = new double[n];
        return array;
    }
    public static void cargarArrayTecladoEnteros(int [] array){
        for(int i=0;i<array.length;i++){
            array[i]=leerDatosTeclado.leerEntero("Dame el "+(i+1)+"º elemento");
        }
        operacionesArrays.mostrarArrayPantalla(array);
    }
    public static double[] cargarArrayTecladoDouble(double[]array){
        for(int i=0;i<array.length;i++){
            array[i]=leerDatosTeclado.leerDouble("Dame el "+(i+1)+"º elemento");
        }
        Arrays.sort(array);
        return array;
    }
    public static void mostrarArrayPantalla(int[]array){
        for(int i=0;i<=array.length-1;i++){
            System.out.print("Tu array es: "+array[i]+" ");
        }
    }
    public static double arrayMaximo(double [] array){
        Arrays.sort(array);
        double maximo = array[array.length-1];
        return maximo;
    }
    public static double arrayMinimo(double [] array){
        Arrays.sort(array);
        double minimo = array[0];
        return minimo;
    }
    public static double media(double [] array){
        double suma = 0;
        for(int i=0; i<array.length-1;i++){
            suma += array[i];
        }
        double media = suma/array.length;
        return media;
    }
    public static double mediana(double[]array){
        double mediana;
        if(array.length%2==0){
            mediana = ((array.length/2) + (array.length/2+1))/2;
        }
        else{
            mediana = array.length/2;
        }
        return mediana;
    }
    public static void moda(double[]array){
        int largo = array.length;
        int contador = array.length;
        int añadidas = 0;
        double datosModa[][] = new double[largo][2];
        for(int i=0;i<largo;i++){
            if(i==0){
                datosModa [0][0] = array[0];
            }
            else if(array [i] == array[i-1]){
                datosModa[añadidas][1] += 1;
                contador-=1;
            }
            else{
                añadidas++;
                datosModa[añadidas][0] = array[i];
            }
        }
        double moda[] = new double [contador];
        double mayorRepeticion = 0;
        int contadorModa = contador;
        int añadidasModa = 0;
        for(int i=0;i<contador;i++){
            if(datosModa[i][1]>mayorRepeticion){
                mayorRepeticion = datosModa[i][1];
            }
        }
        for(int i=0;i<contador;i++){
            if(datosModa[i][1] == mayorRepeticion){
                moda[añadidasModa] = datosModa[i][0];
                añadidasModa++;
            }else{
                contadorModa-=1;
            }
        }        
        for(int i=0; i<contadorModa;i++){
            System.out.print(moda[i]);
            System.out.print(" ");
        }
        
    }
    public static double[] mezclaArrays(double array1[], double array2[]){
        int largo = array1.length + array2.length;
        double [] mezclaArrays = new double [largo];
        //Dependiendo de si uno es mayor que otro, se hace un bucle hasta que
        //no queden numeros del pequeño, y se copian los restantes del segundo.
        if(array1.length>array2.length){
            //Si el array1 es más grande
            int cifra = 0;
            int contador1 = 0;
            int contador2 = 0;
            for(int i=0;i<array2.length*2;i++){
                if(i%2==0){
                    mezclaArrays[i] = array1[contador1];
                    cifra++;
                    contador1++;
                }else{
                    mezclaArrays[i] = array2[contador2];
                    cifra++;
                    contador2++;
                }
            }
            for(int i=cifra;i<mezclaArrays.length;i++){
                mezclaArrays[i] = array1[contador1];
                contador1++;
            }
        }else if(array2.length>array1.length){
            //si el array 2 es más grande
            int cifra = 0;
            int contador1 = 0;
            int contador2 = 0;
            for(int i=0;i<array1.length*2;i++){
                if(i%2==0){
                    mezclaArrays[i] = array1[contador1];
                    cifra++;
                    contador1++;
                }else{
                    mezclaArrays[i] = array2[contador2];
                    contador2++;
                    cifra++;
                }
            }
            for(int i=cifra;i<mezclaArrays.length;i++){
                mezclaArrays[i] = array2[contador2];
                contador2++;
            }
        }else{
            //Si son iguales
            int contador1 = 0;
            int contador2 = 0;
            for(int i=0; i<mezclaArrays.length;i++){
                if(i%2==0){
                    mezclaArrays[i] = array1[contador1];
                    contador1++;
                }else{
                    mezclaArrays[i] = array2[contador2];
                    contador2++;
                }
            }
        }
        return mezclaArrays;
    }
    public static double[][] crearMatriz3x3(){
        double[][]matriz = new double[3][3];
        int contador = 1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matriz[i][j] = leerDatosTeclado.leerDouble("Dame tu "+contador+"º elemento");
                if(contador>=3){
                    contador = 0;
                }
                contador++;
            }
        }
        return matriz;
    }
    public static double determinanteMatriz(double[][]matriz){
        double determinante1 = matriz[0][0]*matriz[1][1]*matriz[2][2]+matriz[0][1]*matriz[1][2]*matriz[2][1]+matriz[0][2]*matriz[1][1]*matriz[2][0];
        double determinante2 = matriz[0][2]*matriz[1][1]*matriz[2][0]+matriz[0][1]*matriz[1][2]*matriz[2][1]+matriz[0][2]*matriz[1][1]*matriz[2][2];
        double determinanteFinal = determinante1 - determinante2;
        return determinanteFinal;
    }
    
}
