/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import excepciones.DemasiadosIntentosExcepcion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author andyloz
 */
public class Carton {
    private Casilla[][] gridCasillas;
    
    private final static class ConstructorCarton {

        private Casilla[][] gridCasillas;

        private boolean[][] gridCombs;
        private ArrayList<Integer> filsDisp;
        private ArrayList<Integer> colsDisp;

        private boolean primerasCasillas;

        private int ciclosDeMas;
        private boolean combTerminado;

        private int[] numCasPorCol;
        private ArrayList<Integer>[] nums = new ArrayList[9];

        private ConstructorCarton() {
            this.gridCasillas = new Casilla[3][9];

            do {
                // Esta variable marca las casillas a las que se les va a asignar un
                // número
                this.gridCombs = new boolean[3][9];

                // Variable de filas disponibles (véase this.comprobarFilsDisp(int))
                this.filsDisp = new ArrayList<>(3);
                this.filsDisp.add(0);
                this.filsDisp.add(1);
                this.filsDisp.add(2);

                // Variable de columnas disponibles (véase this.comprobarColsDisp(int))
                this.colsDisp = new ArrayList<>(9);
                this.colsDisp.add(0); this.colsDisp.add(1); this.colsDisp.add(2);
                this.colsDisp.add(3); this.colsDisp.add(4); this.colsDisp.add(5);
                this.colsDisp.add(6); this.colsDisp.add(7); this.colsDisp.add(8);

                // Estas ayudan a comprobar que se ha caído en una combinación imposible
                this.ciclosDeMas = 0;
                this.combTerminado = true;

                // Primero se asigna una casilla para cada columna en una casilla aleatoria
                this.primerasCasillas();
                
                // Esta variable almacenará el número de casillas asignadas por columna.
                // Tras pasar las primeras casillas, todas las columnas tienen una 
                // sola casilla asignada
                this.numCasPorCol = new int[]{1,1,1,1,1,1,1,1,1};

                // Luego se asignan casillas al azar
                try {
                    this.casillasAleatorias();
                } catch (DemasiadosIntentosExcepcion ex) {
                    // Si se ha caído en una combinación imposible, se empieza de nuevo
                    this.combTerminado = false;
                    // Esto suele ocurrir el ~12% de los casos
                }
            } while (!this.combTerminado);

            // Variable para guardar los números generados
            this.nums = new ArrayList[9];
            this.generarNumeros();
            
            // Colocación de los números generados en gridCasillas
            this.colocarNumeros();
        }

        private void primerasCasillas() {
            Random rnd = new Random();
            // Recorremos las columnas...
            for (int cols = 0; cols < 9; cols++) {
                // Fila candidata generada
                int fil = this.filsDisp.get(rnd.nextInt(this.filsDisp.size()));

                // A partir de la 3ra iteración comprobará que la casilla sea asignable
                if (cols > 1) {
                    if (!this.casillaAsignable(fil, cols)) {
                        // Si no es asignable se generará una fila diferente
                        cols--;
                        continue;
                    }
                }
                this.asignarCasilla(fil, cols);

                // A partir de la 5ta iteración, es necesario comprobar qué filas
                // están llenas
                if (cols > 3) {
                    this.comprobarFilsDisp(fil);
                }
            }
            // Se marca en el programa que se ha terminado de generar las primeras casillas
            this.primerasCasillas = true;
        }

        private void casillasAleatorias() throws DemasiadosIntentosExcepcion {
            Random rnd = new Random();
            // Mientras queden posibilidades en las dos listas
            while (!this.filsDisp.isEmpty() && !this.colsDisp.isEmpty()) {
                // Generar coordenadas
                int fil = filsDisp.get(rnd.nextInt(filsDisp.size()));
                int col = colsDisp.get(rnd.nextInt(colsDisp.size()));

                // Si la casilla no ha sido asignada y es asignable
                if (!this.gridCombs[fil][col] && casillaAsignable(fil, col)) {
                    this.asignarCasilla(fil, col);

                    this.comprobarFilsDisp(fil);
                    this.comprobarColsDisp(col);
                } else {
                    this.ciclosDeMas++;
                    if (this.ciclosDeMas > 75) {
                        throw new DemasiadosIntentosExcepcion("En casillas aleatorias");
                    }
                }
            }
        }

        // Marca un true en la posición de la casilla en el grid de Combinación del
        // cartón para su posterior rellenado
        private void asignarCasilla(int fil, int col) {
            this.gridCombs[fil][col] = true;
        }
        
        private boolean casillaAsignable(int fil, int col) {
            // Comprobar anteriores...
            boolean anterior = false;
            // ...si hay espacio atrás
            if (col > 0) {
                // Comprobar anterior
                anterior = this.gridCombs[fil][col - 1];
            }
            // Si las dos anteriores están asignadas devolver false...
            if (col > 1 && anterior && this.gridCombs[fil][col - 2]) {
                return false;
            }

            // Si se han terminado las primeras casillas, se comprueban las
            // casillas posteriores...
            if (primerasCasillas) {
                boolean posterior = false;
                // ...si hay espacio después
                if (col < 8) {
                    // Comprobar posterior
                    posterior = this.gridCombs[fil][col + 1];
                }
                // Si las dos posteriores están asignadas, devolver false
                if (col < 7 && posterior && this.gridCombs[fil][col + 2]) {
                    return false;
                }
                // Si al final las dos casillas contiguas están asignadas, devolver
                // false. Si no, devolver true.
                return anterior && posterior ? false : true;
            }

            // ...si no, devolver true
            return true;
        }
        
        // Comprueba que no se vayan a asignar más casillas de las que se pueden
        // asignar en una fila (5)
        private void comprobarFilsDisp(int fil) {
            int contCasillas = 0;
            // Recorremos las casillas para esa fila
            for (int cols = 0; cols < 9; cols++) {
                // Si la casilla ha sido asginada
                if (gridCombs[fil][cols]) {
                    // .. la cuenta
                    contCasillas++;
                }
            }
            if (contCasillas > 4) {
                // Si la fila llega a tener 5 casillas asignadas, la retira de las 
                // filas disponibles para rellenar
                this.filsDisp.remove((Integer) fil);
            }
        }

        // Comprueba que no se vayan a asginar más casillas de las que se pueden
        // asginar en una columna (2)
        private void comprobarColsDisp(int col) {
            int contCasillas = 0;
            // Recorremos las casillas para esa columna
            for (int fils = 0; fils < 3; fils++) {
                // Si la casilla ha sido asignada
                if (gridCombs[fils][col]) {
                    // .. la cuenta
                    contCasillas++;
                }
            }
            if (contCasillas > 1) {
                // Si la columna llega a tener 2 casillas asignadas, la retira de las 
                // filas disponibles para rellenar
                this.colsDisp.remove((Integer) col);
            }
            // Aprovechamos el método para contar las casillas que hay en esa columna
            this.numCasPorCol[col] = contCasillas;
        }

        // Se generan los números por columnas
        private void generarNumeros() {
            // Recorremos las columnas
            for (int cols = 0; cols < 9; cols++) {
                nums[cols] = new ArrayList<>(2);
                // Generaremos tantos números como hayamos indicado en numCasPorCol
                for (int fils = 0; fils < this.numCasPorCol[cols]; fils++) {
                    // Generamos...
                    switch (cols) {
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                            this.nums[cols].add(Casilla.generarNumero(cols));
                            break;
                        case 8:
                            this.nums[cols].add(Casilla.generarNumero(80, 90));
                            break;
                    }
                }
                // Ya generados los números de la columna comprobaremos que no
                // se repitan si hay más de uno
                if (nums[cols].size() == 2 && nums[cols].get(0).equals(1)) {
                    // Si los números coinciden, vaciaremos la lista y retrocedemos
                    // una iteración para generarla de nuevo
                    nums[cols].clear();
                    cols--;
                } else {
                    // Si no hay problemas, ordenaremos los números
                    Collections.sort(nums[cols]);
                }
            }
        }

        // Se colocan los números generados en gridCasillas
        private void colocarNumeros() {
            // Recorremos las columnas...
            for (int cols = 0; cols < 9; cols++) {
                // y comprobamos si la casilla de esa columna...
                for (int fils = 0; fils < 3; fils++) {
                    // ...está asignada
                    if (this.gridCombs[fils][cols]) {
                        // Si está asignada, le colocamos el número almacenado en nums
                        this.gridCasillas[fils][cols] = new Casilla(nums[cols].remove(0));
                    }
                }
            }
        }
    }

    public boolean comprobarLinea(int fil) {
        for (int cols = 0; cols < 9; cols++) {
            // Si es null...
            if (this.gridCasillas[fil][cols] == null) {
                return false;
            }
            // Si no está tachada...
            if (!this.gridCasillas[fil][cols].isTachado()) {
                return false;
            }
        }
        // Si todas están tachadas...
        return true;
    }

    public boolean tacharCasilla(int num) {
        int col = Carton.colNum(num);
        // Recorre las casillas de esa columna
        for (int fils = 0; fils < 3; fils++) {
            if (this.gridCasillas[fils][col] != null
                    && this.gridCasillas[fils][col].comprobarNumero(num)) {
                // Hasta encontrar el número
                return true;
            }
        }
        // Si no lo encuentra, devuelve false
        return false;
    }
    
    // Selecciona la columna a la que pertenece el número tomando sus décimas
    private static int colNum(int num) {
        return num < 80
                ? Integer.parseInt(String.format("%02d", num).substring(0, 1))
                : 8;
    }

    @Override
    public String toString() {
        // Primera línea
        String str = "╔════╦════╦════╦════╦════╦════╦════╦════╦════╗";
        for (int fils = 0; fils < this.gridCasillas.length; fils++) {
            str += "\n║";
            for (int cols = 0; cols < this.gridCasillas[fils].length; cols++) {
                if (this.gridCasillas[fils][cols] == null) {
                    str += "    ║";
                    continue;
                }
                if (this.gridCasillas[fils][cols].isTachado()) {
                    str += String.format("=%2s=║", this.gridCasillas[fils][cols].getNum());
                } else {
                    str += String.format(" %2s ║", this.gridCasillas[fils][cols].getNum());
                }
            }
            switch (fils) {
                case 0:
                case 1:
                    // Líneas intermedias
                    str += "\n╠════╬════╬════╬════╬════╬════╬════╬════╬════╣";
                    break;
                case 2:
                    // Última línea
                    str += "\n╚════╩════╩════╩════╩════╩════╩════╩════╩════╝";
                    break;
            }
        }
        return str;
    }

    public Casilla[][] getCasillas() {
        return gridCasillas;
    }
}
