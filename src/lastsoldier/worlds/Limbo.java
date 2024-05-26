/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.worlds;

import java.awt.Color;
import lastsoldier.clases.Map;

/**
 *
 * @author Jacobo
 */
public class Limbo extends Map{
    public Limbo(int x, int y, int width, int height) {
        super(x, y, width, height, Color.GRAY);
        for (int i = 0; i < 5; i++) {
            addEnemy(TYPE_FORGOTTEN);
        }
    }

}