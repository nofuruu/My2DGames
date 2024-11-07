/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my2dgames;


import entity.Player;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    // SCREEN SETTINGS
    final int originalTitleSize = 16; // 16X16 SCREEN TITTLE
    final int  scale = 10;
    
    public final int tileSize = originalTitleSize * scale; // 48X48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow;
    
    // FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new  KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    
    
    public GamePanel () {
        
    this.setPreferredSize(new Dimension (screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);  
    this.addKeyListener(keyH);
    this.setFocusable(true);
    
    }    
    public void startGameThread(){ 
      gameThread = new Thread(this);
      gameThread.start();
      
    }
    
    @Override
public void run(){
   
   double drawInterval = 1000000000/FPS;
   double nextDrawTime = System.nanoTime() + drawInterval;
   
   
   while (gameThread != null)  {
   

update(); 


 repaint();  
   

   
       try {
           double remainingTime = nextDrawTime - System.nanoTime();
           remainingTime = remainingTime/1000000;
           
           if (remainingTime < 0){
               remainingTime = 0;
           }
           
           Thread.sleep((long) remainingTime);
           
           nextDrawTime += drawInterval;
           
       } catch (InterruptedException e) {
     e.printStackTrace();
     

       }
   
  }
}
    
    
    public void update() {
        player.update();
    }
      
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);
        
        player.draw(g2);
     
        g2.dispose();
    }
}
