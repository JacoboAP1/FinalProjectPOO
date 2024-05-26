/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lastsoldier.window;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JOptionPane;
import lastsoldier.clases.Map;
import lastsoldier.clases.World;
import lastsoldier.worlds.Celestial;
import lastsoldier.worlds.Infernal;
import lastsoldier.worlds.Limbo;

/**
 *
 * @author Jacobo
 */
public class MainWindow extends javax.swing.JFrame {
    
    private BufferStrategy bufferStrategy;
    private World world;
    private Map map;

    /**
     * Creates new form MainWindow
     * @param map1
     */
    public MainWindow(Map map1) {
        initComponents();   
        this.map = map1;
    }
    
    public void setWorld(World world){
        this.world = world;
        startHearthAndEnemyThreads();
    }
    
    private void startHearthAndEnemyThreads() {
        Thread thread = new Thread(() -> {
            while (true) {
                world.increaseSoldierLives();
                world.moveEnemies();
                repaint();
                
                if (world.getSoldier() == null) {
                    System.exit(0);
                }
                
                try {
                    Thread.sleep(40); // Mover los enemigos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        createBufferStrategy(2);  // Doble búfer
        bufferStrategy = getBufferStrategy();
    }
    
    @Override
    public void paint(Graphics g){
        if (bufferStrategy == null) {
            super.paint(g);
            return;
        }

        do {
            do {
                g = bufferStrategy.getDrawGraphics();
                g.clearRect(0, 0, getWidth(), getHeight()); // Limpiar la pantalla
                
                world.draw(g);
                
                g.dispose();
            } while (bufferStrategy.contentsRestored());

            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
        if (evt.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT | evt.getKeyCode() == KeyEvent.VK_LEFT
            | evt.getKeyCode() == KeyEvent.VK_UP | evt.getKeyCode() == KeyEvent.VK_DOWN) {

            world.keyPressed(evt.getKeyCode());
            System.out.println("Se movió");
            repaint();
        }
    }//GEN-LAST:event_formKeyPressed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        
        if (evt.getButton() == MouseEvent.BUTTON1) {
            try {
                if (world.getSoldier().getLives() != 0) {
                    map.removeEnemy(evt, world);
                    repaint();
                }
                if (map.getEnemies().isEmpty()) {
                    System.exit(0);
                }
            }
            catch (NullPointerException e){
                JOptionPane.showMessageDialog(this, "You can´t kill, you´re dead");
            }
        }
    }//GEN-LAST:event_formMousePressed

    /**
     * Save game data to file
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        MainWindow ventana = null; 
        String option;

        try {
            option = JOptionPane.showInputDialog("¿What world would you like to play?");
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "The user has cancelled the action");
            option = ""; // Para que entre a los condicionales porque sino lo toma como null
        }
                
        if ("celestial".equalsIgnoreCase(option)) {
            ventana = new MainWindow(new Celestial(0, 0, 800, 600));
        } else if ("infernal".equalsIgnoreCase(option)) {
            ventana = new MainWindow(new Infernal(0, 0, 800, 600));
        } else if ("limbo".equalsIgnoreCase(option)) {
            ventana = new MainWindow(new Limbo(0, 0, 800, 600));
        }
        
        if (ventana != null) {
            World w = new World(0, 0, 800, 600, ventana.map);
            ventana.setWorld(w);

            ventana.setSize(800, 600);
            ventana.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "The user has entered an incorrect option");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}