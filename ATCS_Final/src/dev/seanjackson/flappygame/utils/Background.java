/* Name: Sean Jackson
*  Date May 12, 2021
*  Filename: Background.java
*/

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
   public BackgroundTile t1;
   public BackgroundTile t2;
   
   public Background(BufferedImage b)
   {
      t1 = new BackgroundTile(0, b);
      t2 = new BackgroundTile(Game.WIDTH, b);
   }
}