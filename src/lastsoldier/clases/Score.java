/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

/**
 *
 * @author Jacobo
 */
public class Score {

    private int accumulator;

    public Score(int acumulador) {
        this.accumulator = acumulador;
    }
    
    public void calculateScore(){
        accumulator ++;
    }
    
    public void resetScore(){
        accumulator = 0;
    }

    /**
     * @return the accumulator
     */
    public int getAccumulator() {
        return accumulator;
    }
    
    @Override
    public String toString() {
        return String.valueOf(accumulator);
    }
    
}
