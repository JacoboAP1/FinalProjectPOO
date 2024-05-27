/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import lastsoldier.abstractas.PanelView;
import lastsoldier.interfaces.Boundable;

/**
* Clase Soldado
* @author Sofía Sánchez
* @version 0.1, 2024/05/26
*/
public class Soldier extends PanelView {
    private Boundable bounds;
    private Image image;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int STEP = 10;
    private int lives;

    public Soldier(int soldierX, int soldierY, Boundable bounds) {
        super(soldierX, soldierY, WIDTH, HEIGHT, Color.GREEN);
        this.bounds = bounds;
        this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\quieto.png").getImage();
        this.lives = 1;
    }

    /**
    * Método sobreescrito para dibujar el soldado
    * Requiere dibujarse con la autorización de World
    * @param g
    * @see lastsoldier.abstractas.PanelView#draw(java.awt.Graphics) 
    * @see lastsoldier.clases.World#draw(java.awt.Graphics) 
    */
    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.setColor(color);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    /**
    * Mueve al soldado 
    * Verifica la dirección y los límites
    * @param code
    * @return true
    * @see lastsoldier.interfaces.Boundable#getHeight() 
    * @see lastsoldier.abstractas.PanelView#getY() 
    */
    public boolean move(int code) {
        int nx = getX();
        int ny = getY();
        
        switch (code) {
            case KeyEvent.VK_UP -> {
                this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\arriba.png").getImage();
                ny -= STEP;
            }
            case KeyEvent.VK_DOWN -> {
                this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\abajo.png").getImage();
                ny += STEP;
            }
            case KeyEvent.VK_LEFT -> {
                this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\izquierda.png").getImage();
                nx -= STEP;
            }
            case KeyEvent.VK_RIGHT -> {
                this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\derecha.png").getImage(); 
                nx += STEP;
            }
            default -> {
            }
        }

        if (nx < 0 || nx > bounds.getWidth() - getWidth() || ny < 0 || ny > bounds.getHeight() - getHeight()) {
            return false;
        }

        this.x = nx;
        this.y = ny;

        return true;
    }
    
    /**
    * Método para aumentar vidas del soldado
    * Se verifica con las colisiones del corazón
    * @see lastsoldier.clases.World#increaseSoldierLives() 
    */
    public void eatHearth(){
        lives ++;
    }
    
    /**
    * Método para disminuir vidas del soldado
    * Se verifica con las colisiones del enemigo
    * @see lastsoldier.clases.Enemy#decreaseSoldierLives(lastsoldier.clases.Soldier) 
    */
    public void loseLives(){
        lives --;
    }
    
    /**
    * Método que indica si se murió el soldado
    * Se retorna cuando no tiene vidas
    * @return true
    */
    public boolean die(){
        return getLives() == 0;
    }

    /**
     * @return the lives
     */
    public int getLives() {
        return lives;
    }
    
}