package dev.seanjackson.flappygame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import dev.seanjackson.flappygame.entities.Bird;
import dev.seanjackson.flappygame.entities.DeathFlash;
import dev.seanjackson.flappygame.entities.Entity;
import dev.seanjackson.flappygame.entities.PressSpace;
import dev.seanjackson.flappygame.entities.ScoreCounter;
import dev.seanjackson.flappygame.entities.StartMenu;
import dev.seanjackson.flappygame.utils.Background;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.InputManager;
import dev.seanjackson.flappygame.utils.PipeGenerator;
import dev.seanjackson.flappygame.utils.SoundLoader;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

/**=====}-\ Game Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Main Runner Class */
/**============================*/
public class Game extends Canvas implements Runnable
{
   public static final short HEIGHT = 640;          // WIDTH  OF GAME WINDOW
   public static final short WIDTH = 640;           // HEIGHT OF GAME WINDOW
   
   private Graphics g;                              // GRAPHICS INSTANCE FOR GAME
   private BufferStrategy bs;                       // BUFFER STRAT FOR GAME
   
   public static int GAME_UPS;                      // CURRENT # OF UPDATES THIS SECOND
   public static int GAME_FPS;                      // CURRENT # OF RENDER CALLS THIS SECOND
   
   private JFrame frame;                            // JFRAME INSTANCE FOR GAME
   
   public static InputManager inputManager;         // USED TO KEEP TRACK OF INPUT
   
   public static Bird GAME_BIRD;                    // BIRD INSTANCE (PLAYER)
   public static Background GAME_BACKGROUND;        // BACKGROUND INSTANCE
   public ScoreCounter GAME_SCORE_COUNTER;          // SCORE COUNTER INSTANCE   
   public static DeathFlash GAME_DEATH_FLASH;       // SCREEN FLASH WHEN BIRD DIES
   
   private static StartMenu GAME_START_MENU;        // STARTMENU ICON
   private static PressSpace GAME_PRESS_SPACE;      // "PRESS SPACE" ICON
   
   public static boolean IS_STARTED = false;        // HAS THE PLAYER GOTTEN PAST THE MAIN MENU?
   
   public static Game THE_GAME;                     // REFERENCE TO GAME INSTANCE
   
   
   private static final Font GAME_FONT = new Font("C64 Pro", Font.BOLD, 30);
   
   /**========}-\ Game() /-{========*/
   /** Purpose: Init gfx and input  */
   /**==============================*/
   public Game()
   {
      System.setProperty("sun.java2d.opengl", "true");
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      frame = new JFrame("Doge Cool");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(this);
      frame.pack();
      frame.setResizable(false);
      frame.setFocusable(true);
      frame.setVisible(true);
      
      inputManager = new InputManager();
      this.addKeyListener(inputManager);
      this.addMouseListener(inputManager);
      this.addMouseMotionListener(inputManager);
   }
   
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
   
   /**========}-\ start() /-{========*/
   /** Purpose: Init game elements   */
   /**===============================*/
   public void start()
   {
      GAME_BACKGROUND = new Background(ImageLoader.BACKGROUND);
      GAME_BIRD = new Bird(WIDTH / 2, HEIGHT / 2);
      GAME_START_MENU = new StartMenu();
      GAME_PRESS_SPACE = new PressSpace();
      GAME_DEATH_FLASH = null;
      GAME_SCORE_COUNTER = new ScoreCounter();
      PipeGenerator.START();
   }
   
   public void reset()
   {
      Entity.ENTITIES.clear();
      start();
      IS_STARTED = false;
   }
   
   /**=========}-\ tick() /-{=========*/
   /** Purpose: Update game elements  */
   /**================================*/
   public void tick()
   {
      if(inputManager.getKey(KeyEvent.VK_SPACE))
      {
         IS_STARTED = true;
      }
      
      if(IS_STARTED)
      {
         if(GAME_START_MENU != null)
         {
            GAME_START_MENU.remove();
            GAME_PRESS_SPACE.remove();
            GAME_START_MENU = null;
            GAME_PRESS_SPACE = null;
         }
         PipeGenerator.CHECK_PIPE_PAIR();
         Entity.TICK_ENTITIES();
         
         if(GAME_BIRD.isDead)
         {
            if(!GAME_BIRD.inDeathAnimation)
            {
               SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.DIE_CRASH, .75f);
               SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.DIE_FALL, 1f);
               PipeGenerator.STOP_MOVING_PIPES();
               Entity.ENTITIES.remove(GAME_BIRD);
               Entity.ENTITIES.add(GAME_BIRD);
               Entity.ENTITIES.remove(GAME_BACKGROUND.t1);
               Entity.ENTITIES.remove(GAME_BACKGROUND.t2);
               Entity.ENTITIES.remove(GAME_SCORE_COUNTER);
               GAME_SCORE_COUNTER = null;
               GAME_DEATH_FLASH = new DeathFlash();
            }
         }
         
      }
      else
      {
         GAME_BACKGROUND.t1.tick();
         GAME_BACKGROUND.t2.tick();
         GAME_START_MENU.tick();
         GAME_PRESS_SPACE.tick();
         
         if(GAME_DEATH_FLASH != null)
            GAME_DEATH_FLASH.tick();
      }
   }
   
   /**=========}-\ render() /-{=========*/
   /** Purpose: Render game elements    */
   /**==================================*/
   public void render()
   {
      bs = getBufferStrategy();
      
      if(bs == null)
      {
         createBufferStrategy(3);
         return;
      }
      
      g = bs.getDrawGraphics();
      g.setFont(GAME_FONT);
      
      /** Draw Here **/
      
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, WIDTH, HEIGHT);
      
      if(GAME_SCORE_COUNTER != null)
      {
         Entity.ENTITIES.remove(GAME_SCORE_COUNTER);
         Entity.ENTITIES.add(GAME_SCORE_COUNTER);
      }
      
      if(GAME_START_MENU != null)
      {
         Entity.ENTITIES.remove(GAME_START_MENU);
         Entity.ENTITIES.add(GAME_START_MENU);
      }
      
      Entity.RENDER_ENTITIES(g);
      
      /***************/
      
      bs.show();
      g.dispose();
   }
   
   /**====================}-\ run() /-{*====================*/
   /** Purpose: Game clock, makes update and render calls   */
   /**======================================================*/
   public void run()
   {
      start();
      
      long now, last = System.nanoTime();
      double unprocessed = 0;
      double nsPerTick = 1000000000.0 / 60;
      GAME_FPS = 0;
      GAME_UPS = 0;
      long lastTimer1 = System.currentTimeMillis();
      
      boolean running = true;

      while (running)
      {
          now = System.nanoTime();
          unprocessed += (now - last) / nsPerTick;
          last = now;
          
          while (unprocessed >= 1)
          {
              GAME_UPS++;
              tick();
              
              GAME_FPS++;
              render();
              
              unprocessed -= 1;
          }
          
          

          if (System.currentTimeMillis() - lastTimer1 > 1000)
          {
              lastTimer1 += 1000;
              frame.setTitle("Doge Cool:  " + GAME_UPS + " UPS, " + GAME_FPS + " FPS");
              GAME_FPS = 0;
              GAME_UPS = 0;
          }
      }
  }

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
   
   public static void main(String[] args)
   {
      THE_GAME = new Game();
      //Sound.MUSIC.start();
      THE_GAME.run();
   }
}
