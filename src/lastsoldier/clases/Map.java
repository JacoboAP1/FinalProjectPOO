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
    public static final int TYPE_ANGEL = 1;
    public static final int TYPE_DEMON = 2;
    public static final int TYPE_FORGOTTEN = 3;
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
        Enemy enemyToRemove = null;
        for (Enemy enemy : enemies) {
            if (enemy.receiveShoot(e)) {
                enemyToRemove = enemy;
                break;
            }
        }
        
        if (enemyToRemove != null) {
            enemies.remove(enemyToRemove);
            world.increaseScore(e);
            respawnEnemy(enemyToRemove.getClass(), world);
        }
    }
    
    /**
    * Reaparece un enemigo cuando se mata uno
    * Usa generics
    * Se verifica que no reaparezca en la misma posición del soldado
    * @param world
    */
    private void respawnEnemy(Class<? extends Enemy> enemyClass, World world) {
        int px, py;
        
        do {
            px = (int) ((width - Angel.WIDTH) * Math.random());
            py = (int) ((height - Angel.HEIGHT) * Math.random());
        } while (isNearCenter(px, py) && 
                (px != world.getSoldier().getX() && py != world.getSoldier().getY()));

        Enemy newEnemy = null;
        Boundable bounds = this;

        if (enemyClass == Angel.class) {
            newEnemy = new Angel(px, py, bounds);
        } else if (enemyClass == Demon.class) {
            newEnemy = new Demon(px, py, bounds);
        } else if (enemyClass == ForgottenSoul.class) {
            newEnemy = new ForgottenSoul(px, py, bounds);
        }

        if (newEnemy != null) {
            enemies.add(newEnemy);
            new Thread(newEnemy).start(); // Iniciar el hilo del nuevo enemigo
        }
    }

    /**
    * Verifica que la posición para reaparecer el enemigo no sea el centro
    * El soldado aparece siempre en el centro al inicio del juego
    * @param x
    * @param  y
    * @return true
    */
    private boolean isNearCenter(int x, int y) {
        int centerX = width / 2;
        int centerY = height / 2;
        int centerRadius = 100; // Ajusta este valor según sea necesario
        return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) < centerRadius;
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
        g.setColor(color);
        g.fillRect(getX(), getY(), width, height);

        // Dibuja cada enemigo en el mapa
        for (Enemy enemy : getEnemies()) {
            enemy.draw(g);
        }
    }
    
}
