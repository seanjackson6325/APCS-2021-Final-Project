/* Name: Sean Jackson
*  Date May 18, 2021
*  Filename: ScoreCounter.java
*/

package dev.seanjackson.flappygame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.NumUtils;

public class ScoreCounter extends Entity
{
   private int score;
   
   private static final int WIDTH = 70;     // RENDER  WIDTH OF SINGLE NUM
   private static final int HEIGHT = 110;   // RENDER HEIGHT OF SINGLE NUM
   
   private static final int ELLIPSE_W = 100;
   private static final int ELLIPSE_H = 40;
   
   public ScoreCounter()
   {
      super(Game.WIDTH/2 - WIDTH/2, 32, WIDTH, HEIGHT, null);
      score = -1;    increment();
      
      bounds.height += ELLIPSE_H;
   }

   public void increment()
   {
      score++;
      
      int[] text = NumUtils.toIntArray(score);
      
      if(text.length > 0)
      {
         image = new BufferedImage(ImageLoader.numW * text.length + ELLIPSE_W,
                                   ImageLoader.numH + ELLIPSE_H,
                                   BufferedImage.TYPE_INT_ARGB);
         
         Graphics2D g = (Graphics2D)image.getGraphics();
         
         g.setColor(new Color(0, 0, 0, 0));
         g.fillRect(0, 0, image.getWidth(), image.getHeight());
         
         g.setColor(new Color(255, 255, 255, 50));
         g.fillOval(0, 0, image.getWidth(), image.getHeight());
               
         for(int i = 0; i < text.length; i++)
         {
            g.drawImage(ImageLoader.NUMBERS[text[i]],
                        i * ImageLoader.numW + ELLIPSE_W/2, ELLIPSE_H/2,
                        ImageLoader.numW, ImageLoader.numH, null);
         }
      }
      bounds.width = WIDTH * text.length + 100;
      bounds.x = Game.WIDTH/2 - bounds.width/2;
   }
   
   public int getScore()
   {
      return score;
   }
   
   @Override
   public void tick()
   {
      
   }

   @Override
   public void render(Graphics g)
   {
      drawImage(g);
      drawDebug(g);
   }
}
