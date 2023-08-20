package dev.seanjackson.flappygame.utils;
/* Name: Sean Jackson
*  Date May 12, 2021
*  Filename: NumUtils.java
*/

public final class Utils
{
   public static final byte GREATER_THAN = -1;
   public static final byte LESS_THAN = 1;
   
   public static boolean IsEqual(float f0, float f1, float thr) {
      return Math.abs(f0) - Math.abs(f1) < thr;
   }
   
   public static int ranInt(int lim)
   {
      return (int)(Math.random() * lim);
   }
   
   public static int numDigits(int val)
   {
	  if(val == 0) return 1;
      int n = 0;
      for(int v = val; v > 0; v /= 10, n++);
      return n;
   }
   
   public static int[] toIntArray(int val)
   {
      int[] digits = new int[numDigits(val)];
      
      for(int i = 0; i < digits.length; i++)
      {
         digits[digits.length - i - 1] = val % 10;
         val /= 10;
      }
      
      return digits;
   }
}
