package dev.seanjackson.flappygame.utils;

import java.awt.image.BufferedImage;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.entities.BackgroundTile;

/**=====}-\ Background Class /-{=====*/
/**                                  */
/** @author Sean Jackson             */
/** Purpose: Scrolling backgrounds   */
/**==================================*/

public class Background
{
   // BACKGROUND TILES
   public BackgroundTile t1;
   public BackgroundTile t2;
   
   // MAIN AND ONLY CONSTRUCTOR
   public Background(BufferedImage b, int s)
   {
	  // CREATE TWO BACKGROUND TILES, ONE OFFSET FROM 0
      t1 = new BackgroundTile(0, b);
      t2 = new BackgroundTile(Game.WIDTH, b);
      
      // SET INITIAL SPEED OF PIPES
      setSpeed(s);
   }
   
   // SET THE SPEED OF THE TILES
   public void setSpeed(int s)
   {
	   t1.setSpeed(s);
	   t2.setSpeed(s);
   }
}