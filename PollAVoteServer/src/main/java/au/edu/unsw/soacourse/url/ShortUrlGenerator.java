package au.edu.unsw.soacourse.url;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShortUrlGenerator {
	   /**
	     * @param args
	 * @throws NoSuchAlgorithmException 
	    */
	    public static void main(String[] args) throws NoSuchAlgorithmException {
		String sLongUrl = "http://localhost:8070/FoundITClient/PollAndVote/123456789" ; 
	       // 3BD768E58042156E54626860E241E999
		String aResult = shortUrl (sLongUrl);
	     
		System.out.println("short url is: " +"http://localhost:8070/FoundITClient/PollAndVote/"+aResult);
		
	     }
	 
		  
	    public static String shortUrl(String url) throws NoSuchAlgorithmException {
		 String key = "horn305" ;
		String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
		   "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
		   "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
		   "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
		   "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
		   "U" , "V" , "W" , "X" , "Y" , "Z"
		  };
		 
		  // encrypt with MD5 
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 md.update((key + url).getBytes());
		 String hex = new BigInteger(1, md.digest()).toString(16);
		 String resUrl = new String();
	          String sTempSubString = hex.substring(8, 8 + 8);
		  long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
		  String outChars = "" ;
		  for ( int j = 0; j < 6; j++) {
		     long index = 0x0000003D & lHexLong;
		    outChars += chars[( int ) index];
		    lHexLong = lHexLong >> 5;
		  }
		 
		    resUrl = outChars;
		  return resUrl;
	   }
		 
	}