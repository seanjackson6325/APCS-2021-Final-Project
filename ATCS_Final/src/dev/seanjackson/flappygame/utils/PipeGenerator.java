package dev.seanjackson.flappygame.utils;
import java.util.Random;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.entities.Coin;
import dev.seanjackson.flappygame.entities.Pipe;

public class PipeGenerator
{
   private static Random random = new Random();
   
   public static Pipe p0, p1;
   public static Coin coin;
   
   public static int PIPE_SPEED;
   
   public static void START()
   {
      PIPE_SPEED = -5 - (Game.THE_GAME.GAME_SCORE_COUNTER.getScore() / 4);
      
      p0 = new Pipe(random.nextInt(200) + 50, Pipe.TOP   );
      p1 = new Pipe(Game.HEIGHT - p0.getHeight() - 200, Pipe.BOTTOM);
      
      int coinY = (p0.getY() + p0.getHeight() + p1.getY()) / 2;
      coin = new Coin(p0.getX(), coinY);
   }
   
   public static void STOP_MOVING_PIPES()
   {
      p0.canMove = p1.canMove = false;
   }
   
   public static void START_MOVING_PIPES()
   {
      p0.canMove = p1.canMove = true;
   }
   
   public static void CHECK_PIPE_PAIR()
   {
      if(!p0.active && !p1.active)
      {
         START();
      }
      coin.setX(p0.getX());
   }
}
