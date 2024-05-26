/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.enemies;
import java.awt.Color;
import javax.swing.ImageIcon;
import lastsoldier.clases.Enemy;
import lastsoldier.interfaces.Boundable;

public class Angel extends Enemy {
    
   public Angel(int x, int y, Boundable bounds) {
        super(x, y, WIDTH, HEIGHT, Color.GRAY, bounds); 
        this.velocidadX = 2;
        this.velocidadY = 2;
        this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\angel.png").getImage();
    }

}
