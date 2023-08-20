package dev.seanjackson.flappygame.utils;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/* Name: Sean Jackson
*  Date May 11, 2021
*  Filename: InputManager.java
*/

public class InputManager implements KeyListener, MouseListener, MouseMotionListener
{
   boolean[] keys;
   
   public InputManager() { keys = new boolean[256]; }
   
   public boolean getKey(int key) { return keys[key]; }

   @Override public void keyPressed  (KeyEvent e) { keys[e.getKeyCode()] = true;  } 
   @Override public void keyReleased (KeyEvent e) { keys[e.getKeyCode()] = false; }

   @Override public void keyTyped      (KeyEvent   e) {}
   @Override public void mouseClicked  (MouseEvent e) {}
   @Override public void mousePressed  (MouseEvent e) {}
   @Override public void mouseReleased (MouseEvent e) {}
   @Override public void mouseEntered  (MouseEvent e) {}
   @Override public void mouseExited   (MouseEvent e) {}
   @Override public void mouseDragged  (MouseEvent e) {}
   @Override public void mouseMoved    (MouseEvent e) {}
}
