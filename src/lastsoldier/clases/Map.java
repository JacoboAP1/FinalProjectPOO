/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import lastsoldier.abstractas.PanelView;
import lastsoldier.enemies.Angel;
import lastsoldier.enemies.Demon;
import lastsoldier.enemies.ForgottenSoul;
import lastsoldier.interfaces.Boundable;

public class Map extends PanelView implements Boundable {

    private ArrayList<Enemy> enemies;
    protected static final int TYPE_ANGEL = 1;
    protected static final int TYPE_DEMON = 2;
    protected static final int TYPE_FORGOTTEN = 3;

    public Map(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        enemies = new ArrayList<>();
    }

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

    @Override
    public void draw(Graphics g) {
        // Pinta el fondo del mapa con el color especificado
        g.setColor(color);
        g.fillRect(x, y, width, height);

        // Dibuja cada enemigo en el mapa
        for (Enemy enemy : getEnemies()) {
            enemy.draw(g);
        }
    }
    
}
