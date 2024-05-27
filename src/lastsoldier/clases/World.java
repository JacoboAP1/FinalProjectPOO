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

/**
* Clase que tiene el control del juego
* @author Jacobo Arroyave
* @version 0.1, 2024/05/26
*/
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

    /**
    * Método sobreescrito para dibujar el mapa, corazón, puntaje y soldado
    * Autoriza a los anteriores elementos para dibujarse
    * @param g
    * @see lastsoldier.abstractas.PanelView#draw(java.awt.Graphics) 
    */
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

    /**
    * Mueve a cada enemigo de posición
    * Decrementa la vida del soldado por colisión
    * @see lastsoldier.clases.Enemy#move() 
    * @see lastsoldier.clases.Enemy#resetCollision() 
    * @see lastsoldier.clases.Enemy#decreaseSoldierLives(lastsoldier.clases.Soldier) 
    */
    public void moveEnemies() {
        for (Enemy enemy : map.getEnemies()) {
            enemy.move();
            if (soldier != null) {
                enemy.decreaseSoldierLives(soldier);
                if (soldier.die()) {
                    System.out.println("Soldier has died!");
                    saveGameData();
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
    
    /**
    * Checkea la colisión soldado-corazón
    * Reposiciona al corazón
    * @param soldier
    * @return true
    * @see lastsoldier.clases.Hearth#move() 
    */
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
    
    /**
    * Incrementa las vidas del soldado
    * Checkea si hizo colisión con el corazón
    * @see lastsoldier.clases.Soldier#eatHearth() 
    */
    public void increaseSoldierLives(){
        if (soldier != null) {
            if (checkHearthCollision(soldier)) {
                soldier.eatHearth();
            }
        }
    }

    /**
    * Mueve al soldado según la dirección
    * @param code
    * @see lastsoldier.clases.Soldier#move(int) 
    */
    public void keyPressed(int code) {
        if (soldier != null && (code == KeyEvent.VK_UP || code == KeyEvent.VK_RIGHT || 
            code == KeyEvent.VK_LEFT || code == KeyEvent.VK_DOWN)) { // Verifica que el personaje exista antes de moverlo
            
            soldier.move(code);
        }
    }

    /**
    * Incrementa el puntaje 
 Llama el método saveGameData cuando se haya finalizado el juego
    * @param e
    * @see lastsoldier.clases.Score#calculateScore() 
    */
    public void increaseScore(MouseEvent e) {
        score.calculateScore();
        saveGameData();
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
    
    /**
    * Crea el archivo de texto plano
    * Verifica si terminó la partida o si el usuario salió
    * @see lastsoldier.textmanager.FilesCreator#createTextFile() 
    * @see lastsoldier.textmanager.FilesCreator#writeToFile(java.lang.String) 
    */
    public void saveGameData() {
        System.out.println("Saving game data...");

        FilesCreator f = new FilesCreator();
        f.createTextFile();
        
        // Guardar el nombre del mapa elegido, el puntaje obtenido y el tipo de enemigo enfrentado
        String mapData = "MAP: " + map.getClass().getSimpleName();
        String scoreData = "SCORE: " + score.getAccumulator();
        String enemyType = "";

        switch (map.getClass().getSimpleName()) {
            case "Limbo" -> enemyType = "ENEMY TYPE: Forgotten Soul";
            case "Celestial" -> enemyType = "ENEMY TYPE: Angel";
            case "Infernal" -> enemyType = "ENEMY TYPE: Demon";
            default -> {
            }
        }

        f.writeToFile(mapData + "\n" + scoreData + "\n" + enemyType + "\n");
    }
    
}
