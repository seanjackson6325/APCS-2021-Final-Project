/* Name: Sean Jackson
*  Date May 19, 2021
*  Filename: Sound.java
*/

package dev.seanjackson.flappygame.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundLoader
{  
   public static final String DEFAULT_FOLDER_DIR = "res/sounds/";
   
   public static final String COIN = "Boing.wav";
   public static final String JUMP = "JUMP_SOUND.wav";
   public static final String DIE_CRASH = "DieSound.wav";
   public static final String DIE_FALL = "DieFall.wav";
   
   public static Clip PlaySound(String dir, String name, float vol)
   {
      Clip clip = null;
      try
      {
         clip = AudioSystem.getClip();
         try
         {
            clip.open(AudioSystem.getAudioInputStream(new File(dir + "/" + name)));
            
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * vol) + gainControl.getMinimum();
            gainControl.setValue(gain);
            
            clip.start();
            
         } catch (IOException e)
         {
            e.printStackTrace();
         } catch (UnsupportedAudioFileException e)
         {
            e.printStackTrace();
         }
      } catch (LineUnavailableException e)
      {
         e.printStackTrace();
      }
      return clip;
   }
}
