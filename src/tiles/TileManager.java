package tiles;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import my2dgames.GamePanel;
import tiles.Tile;
import java.awt.image.BufferedImage;

public class TileManager {

   GamePanel gp;
   Tile[] tile;
   BufferedImage spriteSheet; // Sprite sheet
   
   public TileManager(GamePanel gp){
       this.gp = gp;
       
       tile = new Tile[10]; // Misalnya ada 10 tipe tile
       
       loadSpriteSheet();
       getTileImage();
   }

   // Load the sprite sheet
   public void loadSpriteSheet() {
       try {
           System.out.println(getClass().getResource("../Tile/water.jpg")); // Jalur file lowercase
           spriteSheet = ImageIO.read(getClass().getResourceAsStream("../Tile/water.jpg")); // Sesuaikan dengan jalur file
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   // Get tile images from sprite sheet
   public void getTileImage(){
       int tileSize = gp.tileSize;  // Gunakan ukuran tile yang ditentukan di GamePanel
       
       // Cek apakah ukuran sprite sheet cukup besar untuk memuat tile
       int spriteSheetWidth = spriteSheet.getWidth();
       int spriteSheetHeight = spriteSheet.getHeight();
       System.out.println("Sprite Sheet Size: " + spriteSheetWidth + "x" + spriteSheetHeight);
       
       if (tileSize <= spriteSheetWidth && tileSize <= spriteSheetHeight) {
           // Ambil tile dari sprite sheet (sesuaikan koordinat dengan sprite sheet)
           tile[0] = new Tile();
           tile[0].image = spriteSheet.getSubimage(0, 0, tileSize, tileSize);  // Tile pertama

           tile[1] = new Tile();
           tile[1].image = spriteSheet.getSubimage(tileSize, 0, tileSize, tileSize);  // Tile kedua

           tile[2] = new Tile();
           tile[2].image = spriteSheet.getSubimage(2 * tileSize, 0, tileSize, tileSize);  // Tile ketiga

           // Tambahkan tile lainnya dengan subimage yang sesuai jika diperlukan
       } else {
           System.out.println("Tile size exceeds sprite sheet dimensions.");
       }
   }

   public void draw(Graphics2D g2){
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;
       
       while(col < gp.maxScreenCol && row < gp.maxScreenRow){
           g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);  // Gunakan tile[0] untuk semua tiles saat ini
           col++;
           x += gp.tileSize;
           
           if(col == gp.maxScreenCol){
               col = 0;
               x = 0;
               row++;
               y += gp.tileSize;
           }
       }
   }
}
