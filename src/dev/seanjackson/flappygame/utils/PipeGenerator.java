package dev.seanjackson.flappygame.utils;
import java.util.Random;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.entities.Coin;
import dev.seanjackson.flappygame.entities.Pipe;

/**=====}-\ Game Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Spawn pipes       */
/** Last modified: 5/31/21     */
/**============================*/

public class PipeGenerator
{
   // RANDOM OBJECT USED THROUGH LIFETIME OF GAME
   private static Random random = new Random();
   
   // REFERENCES TO TWO PIPES (TOP AND BOTTOM)
   public static Pipe p0, p1;
   
   // REFERENCE TO THE COIN THAT'S USED IN GAME
   public static Coin coin;
   
   // THE SPEED FOR THE PIPES IN THE GAME
   public static int PIPE_SPEED;
   
   // SPAWN PIPES
   public static void START()
   {
	  // CALCULATE PIPE SPEED BASED ON NUMBER OF POINTS SCORED
      PIPE_SPEED = -5 - (Game.GAME_SCORE_COUNTER.getScore() / 4);
      
      // CREATE NEW PIPES
      p0 = new Pipe(random.nextInt(200) + 50, Pipe.TOP   );
      p1 = new Pipe(Game.HEIGHT - p0.getHeight() - 200, Pipe.BOTTOM);
      
      // ADJUST THE BACKGROUND SPEED TO REFLECT SPEED OF PIPES
      Game.GAME_BACKGROUND.setSpeed(PIPE_SPEED >> 4);
      
      // CREATE A NEW COIN
      int coinY = p0.getHeight() + (p1.getY() - p0.getHeight()) / 2 - Coin.SIZE / 2;
      coin = new Coin(p0.getX(), coinY);
   }
   
   // STOP MOVING PIPES
   public static void STOP_MOVING_PIPES()
   {
      p0.canMove = p1.canMove = false;
   }
   
   // START MOVING PIPES
   public static void START_MOVING_PIPES()
   {
      p0.canMove = p1.canMove = true;
   }
   
   // CHECK IF NEW PIPES MUST BE SPAWNED
   public static void CHECK_PIPE_PAIR()
   {
      if(!p0.active && !p1.active)
      {
         START();
      }
      coin.setX(p0.getX());
   }
}
