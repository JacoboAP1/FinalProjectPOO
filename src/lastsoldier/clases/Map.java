/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import lastsoldier.abstractas.PanelView;
import lastsoldier.enemies.Angel;
import lastsoldier.enemies.Demon;
import lastsoldier.enemies.ForgottenSoul;
import lastsoldier.interfaces.Boundable;

/**
* Clase padre de los mapas
* Realiza acciones en común entre hijos
* @author Jacobo Arroyave
* @version 0.1, 2024/05/26
*/
public class Map extends PanelView implements Boundable {

    private ArrayList<Enemy> enemies;
    protected static final int TYPE_ANGEL = 1;
    protected static final int TYPE_DEMON = 2;
    protected static final int TYPE_FORGOTTEN = 3;
    protected Image image;

    public Map(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        enemies = new ArrayList<>();
    }

    /**
    * Añade cada tipo de enemigo al arraylist
    * Verifica primero que tipo de enemigo es
    * @param type
    * @see lastsoldier.enemies.Angel#WIDTH
    * @see lastsoldier.enemies.Angel#HEIGHT
    */
    public void addEnemy(int type) {
        int px = (int) ((width - Angel.WIDTH) * Math.random());
        int py = (int) ((height - Angel.HEIGHT) * Math.random());

        Enemy enemy = null;
        Boundable bounds = this;

        switch (type) {
            case TYPE_ANGEL -> enemy = new Angel(px, py, bounds);
            case TYPE_DEMON -> enemy = new Demon(px, py, bounds);
            case TYPE_FORGOTTEN -> enemy = new ForgottenSoul(px, py, bounds);

        }

        if (enemy != null) {
            enemies.add(enemy);
            new Thread(enemy).start(); // Iniciar el hilo del enemigo
        }
    }
    
    /**
    * Método que borra los enemigos del arraylist
    * Primero verifica con cada enemigo si recibió el disparo
    * Incrementa el puntaje llegado a ser el caso
    * @param e
    * @param world
    * @see lastsoldier.clases.World#increaseScore(java.awt.event.MouseEvent) 
    * @see lastsoldier.clases.Enemy#receiveShoot(java.awt.event.MouseEvent) 
    */
    public void removeEnemy(MouseEvent e, World world) {
        for (Enemy enemy : enemies) {
            if (enemy.receiveShoot(e)) {
                enemies.remove(enemy);
                world.increaseScore(e);
                System.out.println("Enemy removed and score increased.");
                break;
            }
        }
    }
    
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    /**
    * Método sobreescrito para dibujar los mapas con sus enemigos
    * Requiere dibujarse con la autorización de World
    * @param g
    * @see lastsoldier.abstractas.PanelView#draw(java.awt.Graphics) 
    * @see lastsoldier.clases.World#draw(java.awt.Graphics)
    */
    @Override
    public void draw(Graphics g) {
        // Pinta el fondo del mapa con el color especificado
        if (image != null) {
            System.out.println("Drawing image...");
            g.drawImage(image, getX(), getY(), width, height, null);
        } else {
            System.out.println("Drawing background color...");
            g.setColor(color);
            g.fillRect(getX(), getY(), width, height);
        }

        // Dibuja cada enemigo en el mapa
        for (Enemy enemy : getEnemies()) {
            enemy.draw(g);
        }
    }
    
}
