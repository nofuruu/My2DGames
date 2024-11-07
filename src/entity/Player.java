/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import my2dgames.GamePanel;
import my2dgames.KeyHandler;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;



/**
 *
 * @author RENDI
 */
public class Player  extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp = gp;
        this.keyH = keyH;
        
            setDefaultValues();
            getPlayerImage();

  
    }
    public void setDefaultValues(){
        
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        
    }
    public void getPlayerImage(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/Player/negusup1.png"));
               up2= ImageIO.read(getClass().getResourceAsStream("/Player/negusup2.png"));
                  down1= ImageIO.read(getClass().getResourceAsStream("/Player/momodown1.png"));
                     down2= ImageIO.read(getClass().getResourceAsStream("/Player/momodown2.png"));
                        left1= ImageIO.read(getClass().getResourceAsStream("/Player/negusleft1.png"));
                           left2= ImageIO.read(getClass().getResourceAsStream("/Player/negusleft2.png"));
                              right1= ImageIO.read(getClass().getResourceAsStream("/Player/negusright1.png"));
                                 right2= ImageIO.read(getClass().getResourceAsStream("/Player/negusright2.png"));
                                   
                     
         
           
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
        
              if (keyH.upPressed == true){
          direction = "up";
            y -= speed;
}
else if (keyH.downPressed == true){
    direction = "down";
    y += speed;
}
else if (keyH.leftPressed == true){
    direction = "left";
    x -= speed;
}
else if (keyH.rightPressed == true){
    direction = "right";
    x += speed;
        }
      spriteCounter++;
      if(spriteCounter > 12){
          if(spriteNum == 1){
              spriteNum = 2;
          }
          else if(spriteNum == 2){
              spriteNum = 1;
          }
           spriteCounter = 0;
      }
    
    }

}
    public void draw(Graphics2D g2){
//           g2.setColor(Color.white);
//        
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        
        switch(direction){
            
        case "up": 
            if(spriteNum ==  1 ){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
        break;
        
        case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            break;
            
        case "left":
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            break;
            
        case "right":
            if(spriteNum == 1){
            image = right1;
        }
            if(spriteNum == 2){
                image = right2;
            }
         break;
            
        
        
        }
       g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);


    }
}
