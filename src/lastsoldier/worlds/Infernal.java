/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.worlds;

import java.awt.Color;
import lastsoldier.clases.Map;

/**
* Clase que añade el número de enemigos
* Verifica que sus enemigos son tipo Demonio
* @author César David
* @version 0.1, 2024/05/26
*/
public class Infernal extends Map {
    
    public Infernal(int x, int y, int width, int height) {
        super(x, y, width, height, new Color(239, 144, 15));
        for (int i = 0; i < 5; i++) {
            addEnemy(TYPE_DEMON);
        }
    }
    
}