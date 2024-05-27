/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.abstractas;

import java.awt.Color;
import java.awt.Graphics;

/**
* Clase abstracta
* Posee los atributos para dibujar
* @author César David
* @version 0.1, 2024/05/26
*/
public abstract class PanelView { 
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;

    public PanelView(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    /**
    * Método abstracto que brinda a las clases hijas poder dibujarse
    * @param g
    */
    public abstract void draw(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
    
}
