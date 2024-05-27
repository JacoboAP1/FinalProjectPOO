/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import lastsoldier.abstractas.PanelView;
import lastsoldier.interfaces.Boundable;

/**
* Clase padre de los enemigos
* Realiza acciones en común entre hijos
* @author Jacobo Arroyave
* @version 0.1, 2024/05/26
*/
public class Enemy extends PanelView implements Runnable {
    protected boolean running;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private Boundable bound;
    protected int velocidadX;
    protected int velocidadY;
    protected Image image;
    private boolean collision;
    
    public Enemy(int x, int y, int width, int height, Color color, Boundable bound) {
        super(x, y, width, height, color);
        this.running = false;
        this.bound = bound;
        this.collision = false;
    }

    /**
    * Método que mueve a los enemigos
    * Primero verifica los límites del mapa
    * @see lastsoldier.abstractas.PanelView#getWidth() 
    * @see lastsoldier.abstractas.PanelView#getHeight() 
    */
    public void move(){
        x += velocidadX;
        y += velocidadY;
        
        if (x + WIDTH >= bound.getWidth() || x <= World.CONST_MARGEN_X) {
            velocidadX *= -1;
        }
        if (y + HEIGHT >= bound.getHeight() || y <= World.CONST_MARGEN_Y) {
            velocidadY *= -1;
        }
    }
    
    /**
    * Verifica si el enemigo chocó con el soldado
    * @param soldier
    * @return true
    * @see lastsoldier.clases.Soldier#getX() 
    */
    public boolean collidesWithCharacter(Soldier soldier) {
        return x < soldier.getX() + soldier.getWidth() &&
               x + WIDTH > soldier.getX() &&
               y < soldier.getY() + soldier.getHeight() &&
               y + HEIGHT > soldier.getY();
    }
    /**
    * Quita vidas al soldado
    * Primero verifica que se haya hecho colisión
    * @param soldier
    * @see lastsoldier.clases.Soldier#loseLives() 
    */
    public void decreaseSoldierLives(Soldier soldier){
        if (collidesWithCharacter(soldier) && !collision) {
            
            System.out.println("Soldado perdió vida");
            soldier.loseLives();
            this.collision = true; // Marca al enemigo como colisionado
            velocidadX *= -1; // Invierte la dirección X
            velocidadY *= -1; // Invierte la dirección Y
        }
    }

    public void resetCollision() {
        this.collision = false; // Resetea la colisión después de manejarla
    }

    /**
    * Crea el hilo de los enemigos
    */
    @Override
    public void run() {
        this.running = true;
        while (running) {
            move();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
    
    /**
    * Indica si recibió un diparo
    * Primero verifica si se disparó dentro de sus límites
    * @param e
    * @return true
    */
    public boolean receiveShoot(MouseEvent e) {
        return e.getX() >= x && e.getX() <= (x + width) &&
               e.getY() >= y && e.getY() <= (y + height);
    }

    /**
    * Método sobreescrito para dibujar los enemigos
    * Requiere autorización de World para dibujarse
    * @param g
    * @see lastsoldier.abstractas.PanelView#draw(java.awt.Graphics) 
    * @see lastsoldier.clases.World#draw(java.awt.Graphics)
    */
    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), WIDTH, HEIGHT, null);
        } else {
            g.setColor(color);
            g.fillRect(getX(), getY(), WIDTH, HEIGHT);
        }
    }
}
