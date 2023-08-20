package dev.seanjackson.flappygame.utils;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**=====}-\ Game Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Handle input      */
/** Last modified: 5/31/21     */
/**============================*/

public class InputManager implements KeyListener, MouseListener, MouseMotionListener
{

   boolean[] keys;    // STATE OF ALL KEYS ON KEYBOARD
   
   public InputManager() { keys = new boolean[256]; }
   
   /**===============}-\ getKey /-{*===============*/
   /** Purpose: return state of key                */
   /**=============================================*/
   public boolean getKey(int key) { return keys[key]; }

   @Override public void keyPressed  (KeyEvent e) { keys[e.getKeyCode()] = true;  } 
   @Override public void keyReleased (KeyEvent e) { keys[e.getKeyCode()] = false; }

   @Override public void mousePressed  (MouseEvent e) { keys[KeyEvent.VK_SPACE] = true; }
   @Override public void mouseReleased (MouseEvent e) { keys[KeyEvent.VK_SPACE] = false; }
   
   @Override public void keyTyped      (KeyEvent   e) {}
   @Override public void mouseClicked  (MouseEvent e) {}
   @Override public void mouseEntered  (MouseEvent e) {}
   @Override public void mouseExited   (MouseEvent e) {}
   @Override public void mouseDragged  (MouseEvent e) {}
   @Override public void mouseMoved    (MouseEvent e) {}
}
