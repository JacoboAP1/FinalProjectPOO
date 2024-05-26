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
    
    public boolean collidesWithCharacter(Soldier soldier) {
        return x < soldier.getX() + soldier.getWidth() &&
               x + WIDTH > soldier.getX() &&
               y < soldier.getY() + soldier.getHeight() &&
               y + HEIGHT > soldier.getY();
    }
    
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
    
    public boolean receiveShoot(MouseEvent e) {
        return e.getX() >= x && e.getX() <= (x + width) &&
               e.getY() >= y && e.getY() <= (y + height);
    }

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
