/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.enemies;
import java.awt.Color;
import javax.swing.ImageIcon;
import lastsoldier.clases.Enemy;
import lastsoldier.interfaces.Boundable;
/**
 *
 * @author CESAR DAVID ARIAS P
 */
public class ForgottenSoul extends Enemy {
    
    public ForgottenSoul(int x, int y, Boundable bounds) {
        super(x, y, WIDTH, HEIGHT, Color.WHITE, bounds); 
        this.velocidadX = 3;
        this.velocidadY = 3;
        this.image = new ImageIcon("C:\\Users\\arroy\\OneDrive\\Documentos\\NetBeansProjects\\FinalProjectPOO-main\\build\\classes\\lastsoldier\\images\\almaperdida.png").getImage();
    }

}
