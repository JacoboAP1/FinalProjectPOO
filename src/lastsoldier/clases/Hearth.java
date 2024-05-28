/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import lastsoldier.abstractas.PanelView;
import lastsoldier.interfaces.Boundable;

/**
* Clase que diseña el corazón
* Clase que da vidas al soldado
* @author Sofía Sánchez
* @version 0.1, 2024/05/26
*/
public class Hearth extends PanelView {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Boundable bounds;
    private Image image;
    
    public Hearth(int x, int y, Boundable bounds) {
        super(x, y, WIDTH, HEIGHT, Color.PINK);
        this.bounds = bounds;
        this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\corazon.png").getImage();
    }

    /**
    * Método sobreescrito para dibujar el corazón
    * Requiere autorización de World para dibujarse
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
    * Frase corta descriptiva
    * Descripción del método.
    * @see lastsoldier.interfaces.Boundable#getWidth() 
    */
    public void move() {
        this.x = (int) ((bounds.getWidth() - WIDTH) * Math.random());
        this.y = (int) ((bounds.getHeight() - HEIGHT) * Math.random());
        this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\corazon.png").getImage();
    }
    
}
