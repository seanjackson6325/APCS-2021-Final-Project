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
import dev.seanjackson.flappygame.utils.StateManager;
import dev.seanjackson.flappygame.utils.Utils;

public class ScoreCounter extends Entity
{
   private int score;
   
   private static final int WIDTH = 70;         // RENDER  WIDTH OF SINGLE NUM
   private static final int HEIGHT = 110;       // RENDER HEIGHT OF SINGLE NUM
   
   private static final int ELLIPSE_W = 100;    // RENDER WIDTH OF ELLIPSE
   private static final int ELLIPSE_H = 40;     // RENDER HEIGHT OF ELLIPSE
   
   // MAIN AND ONLY CONSTRUCTOR
   public ScoreCounter(int s)
   {
      super(Game.WIDTH/2 - WIDTH/2, 32, WIDTH, HEIGHT, null);
      score = s - 1;    increment();
      
      bounds.height += ELLIPSE_H;
   }

   // INCREMENT SCORE AND UPDATE IMAGE & TEXT
   public void increment()
   {
	  // INCREMENT SCORE
      score++;
      
      // CONVERT SCORE INTO INT ARRAY
      int[] text = Utils.toIntArray(score);
      
      // CACHE THE TEXT SO THIS OPERATION NOT NEED BE PERFORMED EVERY GAME UPDATE
      image = new BufferedImage(ImageLoader.numW * text.length + ELLIPSE_W,
              ImageLoader.numH + ELLIPSE_H,
              BufferedImage.TYPE_INT_ARGB);

      // GET GRAPHICS CONTEXT FROM IMAGE
	  Graphics2D g = (Graphics2D)image.getGraphics();
	
	  // CLEAR GRAPHICS
	  g.setColor(new Color(0, 0, 0, 0));
	  g.fillRect(0, 0, image.getWidth(), image.getHeight());
	
	  // DRAW OVAL
	  g.setColor(new Color(255, 255, 255, 50));
	  g.fillOval(0, 0, image.getWidth(), image.getHeight());
	
	  // DRAW TEXT
	  for(int i = 0; i < text.length; i++)
	  {
		 g.drawImage(ImageLoader.NUMBERS[text[i]],
	     i * ImageLoader.numW + ELLIPSE_W/2, ELLIPSE_H/2,
	     ImageLoader.numW, ImageLoader.numH, null);
	  }
	  
	  // UPDATE POSITION
      bounds.width = WIDTH * text.length + 100;
      bounds.x = Game.WIDTH/2 - bounds.width/2;
   }
   
   /** @return THE SCORE */
   public int getScore()
   {
      return score;
   }
   
   @Override
   public void tick() {}

   @Override
   public void render(Graphics g)
   {
	  // THIS ISN'T THE BEST SOLUTION AS IT REQUIRES A CHECK EACH FRAME
	  // HOPEFULLY I CAN FIND A SOLUTION BEFORE THIS IS DUE :P
	  // TODO: FIX THIS SHIT
	  if(!Game.GAME_STATE_MANAGER.isInState(StateManager.MENU_STATE))
	  {
		  drawImage(g);
	      drawDebug(g);
	  }
   }
}
