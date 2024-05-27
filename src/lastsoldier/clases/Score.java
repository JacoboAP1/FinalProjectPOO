/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

/**
* Clase Puntaje
* Controla el puntaje
* @author César David
* @version 0.1, 2024/05/26
*/
public class Score {

    private int accumulator;

    public Score(int acumulador) {
        this.accumulator = acumulador;
    }
    
    /**
    * Aumenta el acumulador del puntaje de 1 en 1
    */
    public void calculateScore(){
        accumulator ++;
    }
    
    /**
    * Reinicia el puntaje
    * En caso de querer jugar de nuevo
    */
    public void resetScore(){
        accumulator = 0;
    }

    /**
     * @return the accumulator
     */
    public int getAccumulator() {
        return accumulator;
    }
    
    /**
    * Método que convierte el valor entero a String
    * Para pasarlo al archivo txt
    * @return acummulator
    */
    @Override
    public String toString() {
        return String.valueOf(accumulator);
    }
    
}
