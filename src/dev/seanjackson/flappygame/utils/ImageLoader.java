package dev.seanjackson.flappygame.utils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**=====}-\ ImageLoader Class /-{=====*/
/** @author Sean Jackson              */
/** Purpose: Load bufferedImages      */
/** Last modified: 5/25/21            */
/**===================================*/

public class ImageLoader
{
   /**============}-\ start() /-{============*/
   /** Purpose: Safely load BufferedImage    */
   /**=======================================*/
   public static BufferedImage LoadBufferedImage(String name)
   {
      BufferedImage b = null;
      
      try { b = ImageIO.read(new File(name)); }
      catch (IOException e) { e.printStackTrace(); }
      
      return b;
   }
   
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

   //    ####    ######  ######  ##  ##    ##    ######  ######
   //     ##  ##    ##      ##    ######  ##  ##  ##  ##  ##      
   //      ####      ##      ##    ##  ##  ######  ######  ######  
   //       ##  ##    ##      ##    ##  ##  ##  ##  ##          ##  
   //        ####    ######    ##    ##  ##  ##  ##  ##      ######  
   
   private static final String folderPath = "res/bitmaps/";
   
   public static BufferedImage  BACKGROUND = LoadBufferedImage(folderPath + "SpaceBackground.jpg");
   public static BufferedImage       TITLE = LoadBufferedImage(folderPath + "Logo.png"               );
   public static BufferedImage        BIRD = LoadBufferedImage(folderPath + "DogeCool.png"            );
   public static BufferedImage       SMOKE = LoadBufferedImage(folderPath + "Smoke.png"               );
   public static BufferedImage PRESS_SPACE = LoadBufferedImage(folderPath + "Press_Space.png"         );
   public static BufferedImage   PIPE_TUBE = LoadBufferedImage(folderPath + "Pipe.png"                );
   public static BufferedImage    PIPE_TOP = LoadBufferedImage(folderPath + "Pipe_Top.png"            );
   public static BufferedImage        COIN = LoadBufferedImage(folderPath + "Coin.png"                );
   public static BufferedImage   GAME_OVER = LoadBufferedImage(folderPath + "GAME_OVER.png"           );
   
   private static BufferedImage nums = LoadBufferedImage(folderPath + "Numbers.png");
   public static final int numW = nums.getWidth()/10, numH = nums.getHeight();
   public static BufferedImage[] NUMBERS =
   {
         nums.getSubimage(numW*9, 0, numW, numH),
         nums.getSubimage(numW*0, 0, numW, numH),
         nums.getSubimage(numW*1, 0, numW, numH),
         nums.getSubimage(numW*2, 0, numW, numH),
         nums.getSubimage(numW*3, 0, numW, numH),
         nums.getSubimage(numW*4, 0, numW, numH),
         nums.getSubimage(numW*5, 0, numW, numH),
         nums.getSubimage(numW*6, 0, numW, numH),
         nums.getSubimage(numW*7, 0, numW, numH),
         nums.getSubimage(numW*8, 0, numW, numH),
   };
   
   private static BufferedImage high_score = LoadBufferedImage(folderPath + "High_Score.png");
   public static BufferedImage[] HIGH_SCORE = 
   {
		 high_score.getSubimage(0, 0, high_score.getWidth() / 2, high_score.getHeight()),
		 high_score.getSubimage(high_score.getWidth() / 2, 0, high_score.getWidth() / 2, high_score.getHeight())
   };
}
