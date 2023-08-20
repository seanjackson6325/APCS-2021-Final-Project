package dev.seanjackson.flappygame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import dev.seanjackson.flappygame.entities.Bird;
import dev.seanjackson.flappygame.entities.DeathFlash;
import dev.seanjackson.flappygame.entities.DeathScreen;
import dev.seanjackson.flappygame.entities.Entity;
import dev.seanjackson.flappygame.entities.PressSpace;
import dev.seanjackson.flappygame.entities.ScoreCounter;
import dev.seanjackson.flappygame.entities.TitleScreen;
import dev.seanjackson.flappygame.utils.Background;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.InputManager;
import dev.seanjackson.flappygame.utils.PipeGenerator;
import dev.seanjackson.flappygame.utils.SoundLoader;
import dev.seanjackson.flappygame.utils.StateManager;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

//  ######  ##  ##  ######    ######    ##    ##  ##  ######
//     ##    ##  ##  ##        ##      ##  ##  ######  ##
//      ##    ######  ####      ##      ######  ##  ##  ####
//       ##    ##  ##  ##        ##  ##  ##  ##  ##  ##  ##
//        ##    ##  ##  ######    ######  ##  ##  ##  ##  ######

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

/**=====}-\ Game Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Main Runner Class */
/** Last modified: 5/25/21     */
/**============================*/
public final class Game extends Canvas implements Runnable
{
   public static final short HEIGHT = 640;          // WIDTH  OF GAME WINDOW
   public static final short WIDTH = 640;           // HEIGHT OF GAME WINDOW
   
   private Graphics g;                              // GRAPHICS INSTANCE FOR GAME
   private BufferStrategy bs;                       // BUFFER STRAT FOR GAME
   private BufferedImage raster;                    // ALL GAME GRAPHICS DRAWN TO THIS IMAGE
   
   public static int GAME_UPS;                      // CURRENT # OF UPDATES THIS SECOND
   public static int GAME_FPS;                      // CURRENT # OF RENDER CALLS THIS SECOND
   
   private static JFrame frame;                     // JFRAME INSTANCE FOR GAME
   
   public static InputManager GAME_INPUT_MANAGER;   // USED TO KEEP TRACK OF INPUT
   
   public static Bird GAME_BIRD;                    // BIRD INSTANCE (PLAYER)
   public static Background GAME_BACKGROUND;        // BACKGROUND INSTANCE
   public static DeathFlash GAME_DEATH_FLASH;       // SCREEN FLASH WHEN BIRD DIES
   public static DeathScreen GAME_DEATH_SCREEN;		// SCREEN WHEN BIRD DIES
   public static ScoreCounter GAME_SCORE_COUNTER;   // SCORE COUNTER INSTANCE
   public static StateManager GAME_STATE_MANAGER;	// MANAGE GAME STATE (IN MENU, GAMEPLAY, ETC.)
   
   public static TitleScreen GAME_START_MENU;         // STARTMENU ICON
   public static PressSpace GAME_PRESS_SPACE;       // "PRESS SPACE" ICON
   
   private static long TIMER;                       // NUMBER OF SECONDS GAME HAS BEEN RUNNING
   public static long GET_GAME_TIME() { return TIMER; }
   
   
   public static Clip MENU_MUSIC, GAME_MUSIC, DEATH_MUSIC;
   
   public static Game THE_GAME;                     // REFERENCE TO GAME INSTANCE
   
   /**========}-\ Game() /-{========*/
   /** Purpose: Init gfx and input  */
   /**==============================*/
   public Game()
   {
	  // INIT HARDWARE ACCELERATED RENDERING
      System.setProperty("sun.java2d.opengl", "true");
      
      // INIT CANVAS SIZE
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      // INIT FRAME ELEMENTS
      frame = new JFrame("Doge Cool");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(this);
      frame.pack();
      frame.setResizable(true);
      frame.setFocusable(true);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
      // INIT GAME INPUT
      GAME_INPUT_MANAGER = new InputManager();
      this.addKeyListener(GAME_INPUT_MANAGER);
      this.addMouseListener(GAME_INPUT_MANAGER);
      this.addMouseMotionListener(GAME_INPUT_MANAGER);
      
      // IMAGE BUFFER THAT GAME DRAWS TO
      raster = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
      
      // INIT STATE MANAGER
      GAME_STATE_MANAGER = new StateManager(StateManager.MENU_STATE);
   }
   
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
   
   /**====================}-\ RESET() /-{====================*/
   /** Purpose: Init game elements & go back to menu state   */
   /**=======================================================*/
   public static void RESET()
   {
      Entity.ENTITIES.clear();
      GAME_STATE_MANAGER.setState(StateManager.MENU_STATE);
      GAME_BACKGROUND = new Background(ImageLoader.BACKGROUND, 1);
      GAME_BIRD = new Bird(WIDTH / 2, HEIGHT / 2);
      GAME_START_MENU = new TitleScreen();
      GAME_PRESS_SPACE = new PressSpace();
      GAME_DEATH_FLASH = null;
      GAME_SCORE_COUNTER = new ScoreCounter(0);
      PipeGenerator.START();
      Game.MENU_MUSIC = SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR,
    		  								  SoundLoader.MENU_MUSIC, .9f);
   }
   
   /**=========}-\ tick() /-{=========*/
   /** Purpose: Update game elements  */
   /**================================*/
   public void tick()
   {
      if(GAME_STATE_MANAGER.isInState(StateManager.GAME_STATE))
      {
    	 // REMOVE EVERYTHING FROM THE START MENU
    	 // IF NOT ALREADY. THEN SET REFERENCES TO NULL
         if(GAME_START_MENU != null)
         {
            GAME_START_MENU.remove();
            GAME_PRESS_SPACE.remove();
            GAME_START_MENU = null;
            GAME_PRESS_SPACE = null;
         }
         
         // CHECK THE STATUS OF THE OBSTICLES
         PipeGenerator.CHECK_PIPE_PAIR();
         
         // UPDATE ENTITIES IN LIST:
         Entity.TICK_ENTITIES();
      }
      else if(GAME_STATE_MANAGER.isInState(StateManager.MENU_STATE))
      {
    	 // UPDATE ELEMENTS DURING MENU STATE:
         GAME_BACKGROUND.t1.tick();
         GAME_BACKGROUND.t2.tick();
         GAME_START_MENU.tick();
         GAME_PRESS_SPACE.tick();
      }
      else if(GAME_STATE_MANAGER.isInState(StateManager.DEATH_STATE))
      {    	  
    	  // UPDATE ALL ENTITIES IN DEATH STATE
    	  // (MOST ENTITIES HAVE BEEN REMOVED):
    	  Entity.TICK_ENTITIES();
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
      
      g = raster.getGraphics();
      
      /** Draw Here **/
      
      g.setColor(Color.BLACK);
      
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
      
      g = bs.getDrawGraphics();
      
      int draw_w, draw_h;
      int frame_w = frame.getWidth();
      int frame_h = frame.getHeight();
      
      if(frame_w >= frame_h)
      {
    	  draw_h = frame_h;
    	  draw_w = draw_h;
      } else
      {
    	  draw_w = frame_w;
    	  draw_h = draw_w;
      }
      
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, frame_w, frame_h);
      g.drawImage(raster, (frame_w - draw_w) / 2, (frame_h - draw_h) / 2, draw_w, draw_h, null);
      
      /***************/
      
      bs.show();
      g.dispose();
   }
   
   /**====================}-\ run() /-{*====================*/
   /** Purpose: Game clock, makes update and render calls   */
   /**======================================================*/
   public void run()
   {
	  TIMER = 0;
      RESET();
      
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
              TIMER++;
          }
      }
  }

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
   
   /**===============}-\ processBirdDeath /-{*===============*/
   /** Purpose: Init and remove game elements after death    */
   /**=======================================================*/
   public void processBirdDeath()
   {
	   SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.DIE_CRASH, 1f);
       SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.DIE_FALL, 1f);
       PipeGenerator.STOP_MOVING_PIPES();
       Entity.ENTITIES.remove(GAME_BIRD);
       Entity.ENTITIES.add(GAME_BIRD);
       Entity.ENTITIES.remove(GAME_BACKGROUND.t1);
       Entity.ENTITIES.remove(GAME_BACKGROUND.t2);
       GAME_DEATH_FLASH = new DeathFlash();
       GAME_DEATH_SCREEN = new DeathScreen();
   }
   
   public static void main(String[] args)
   {
      THE_GAME = new Game();
      THE_GAME.run();
   }
}
