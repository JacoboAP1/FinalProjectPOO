/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.worlds;

import java.awt.Color;
import lastsoldier.clases.Map;

/**
 * Clase que representa el mundo celestial donde aparecen los ángeles.
 * 
 * @author Jacobo
 */
public class Celestial extends Map {
    public Celestial(int x, int y, int width, int height) {
        super(x, y, width, height, Color.WHITE);
        for (int i = 0; i < 5; i++) {
            addEnemy(TYPE_ANGEL);
        }
    }

}