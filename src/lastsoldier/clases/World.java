/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lastsoldier.clases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import lastsoldier.abstractas.PanelView;
import lastsoldier.interfaces.Boundable;
import lastsoldier.textmanager.FilesCreator;

public class World extends PanelView implements Boundable {
    
    public static final int CONST_MARGEN_X = 8;
    public static final int CONST_MARGEN_Y = 30;
    private Map map;
    private Soldier soldier;
    private Score score;
    private Hearth hearth;

    public World(int x, int y, int width, int height, Map map) {
        super(x, y, width, height, null);
        this.map = map;

        int px = (width - Soldier.WIDTH) / 2;
        int py = (height - Soldier.HEIGHT) / 2;

        this.soldier = new Soldier(px, py, this);
        this.score = new Score(0);
        this.hearth = new Hearth(px, px, this);
    }

    @Override
    public void draw(Graphics g) {
        map.draw(g);
        hearth.draw(g);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("SCORE: " + getScore().getAccumulator(), 650, 60);
        
        if (soldier != null) {  // Verificar si el personaje existe antes de dibujar
            soldier.draw(g);
            g.drawString("LIVES: " + soldier.getLives(), 15, 60);
        }
       
    }

    public void moveEnemies() {
        for (Enemy enemy : map.getEnemies()) {
            enemy.move();
            if (soldier != null) {
                enemy.decreaseSoldierLives(soldier);
                if (soldier.die()) {
                    System.out.println("Soldier has died!");
                    soldier = null;
                    break;
                }
            }
        }

        // Restablecer la bandera de colisión después de procesar todas las colisiones
        for (Enemy enemy : map.getEnemies()) {
            enemy.resetCollision();
        }
    }
    
    public boolean checkHearthCollision(Soldier soldier) {
        if (soldier.getX() < hearth.getX() + Hearth.WIDTH &&
            soldier.getX() + Soldier.WIDTH > hearth.getX() &&
            soldier.getY() < hearth.getY() + Hearth.HEIGHT &&
            soldier.getY() + Soldier.HEIGHT > hearth.getY()) {
            
            hearth.move();  // Reposicionar el corazón
            return true;
        }
        return false;
    }
    
    public void increaseSoldierLives(){
        if (soldier != null) {
            if (checkHearthCollision(soldier)) {
                soldier.eatHearth();
            }
        }
    }

    public void keyPressed(int code) {
        if (soldier != null && (code == KeyEvent.VK_UP || code == KeyEvent.VK_RIGHT || 
            code == KeyEvent.VK_LEFT || code == KeyEvent.VK_DOWN)) { // Verifica que el personaje exista antes de moverlo
            
            soldier.move(code);
        }
    }

    public void increaseScore(MouseEvent e) {
        score.calculateScore();
        System.out.println("Score increased: " + score.getAccumulator());
    }

    public Soldier getSoldier() {
        return soldier;
    }
    
    /**
     * @return the score
     */
    public Score getScore() {
        return score;
    }
    
    public void saveGameData(KeyEvent e) {
        FilesCreator fileCreator = new FilesCreator();
        fileCreator.createTextFile();

        // Guardar el nombre del mapa elegido, el puntaje obtenido y el tipo de enemigo enfrentado
        String mapData = "MAP: " + map.getClass().getSimpleName();
        String scoreData = "SCORE: " + score.getAccumulator();
        String enemyType = "";

        switch (map.getClass().getSimpleName()) {
            case "Limbo" -> enemyType = "KILLED ENEMY TYPE: Forgotten Soul";
            case "Celestial" -> enemyType = "KILLED ENEMY TYPE: Angel";
            case "Infernal" -> enemyType = "KILLED ENEMY TYPE: Demon";
            default -> {
            }
        }

        if (map.getEnemies().isEmpty() || soldier.die() || e.getKeyCode() == KeyEvent.VK_Q) {
            fileCreator.writeToFile(mapData + "\n" + scoreData
                                + "\n" + enemyType + "\n");
        }
    }
}
