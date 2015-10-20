import java.util.BitSet;

// This class provides all the data type and data handling modules that allow easy data manipulation in other classes  
public class Conversion {
	
	/**
	 * This method Converts 4 bytes into an integer  signed integer value.
	 * @param b Array which holds the byte
	 * @param offset The location in the array where the Byte begins 
	 * @return int 4 Byte Integer Value
	 */
/*	public static int byteToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }*///christine version:need change name
	
// This is used to convert 4 bytes in sequence into an integer number
public static int byte4tointeger(byte[] bytes, int index) {
	int a = 0;

	a= (bytes[index+0]<<24)&0xff000000|
	       (bytes[index+1]<<16)&0x00ff0000|
	       (bytes[index+2]<< 8)&0x0000ff00|
	       (bytes[index+3]<< 0)&0x000000ff;
		  
	return a;
	} 
		
	
	/**
	 * This method returns a bitset for the bits of the byte.
	 * @param b Byte to convert. 
	 * @return BitSet An array of booleans (index 0 coresponds to least significant bit)
	 */
/*    public static BitSet byteToBitset(byte b) {  
	    BitSet bits = new BitSet(8);  
	    for (int i = 0; i < 8; i++){  
	        bits.set(i, (b & 1) == 1);  
	        b >>>= 1;  
	    }  
	    return bits;  
	}  	*/    // christine: internet: static BitSet valueOf(byte[] bytes) 
	// Returns a new bit set containing all the bits in the given byte array.

// This allows to convert a single byte to a 8bits bitset
public static BitSet byteToBitset(byte b) {  
	BitSet set = BitSet.valueOf(new byte[] { b });
	return set;  
	}  	    
    
    
	/**
	 * This method returns a 2byte unsigned integer.
	 * @param b Byte array
	 * @param offset Location in array where the 2 bytes begin. 
	 * @return int 2byte unsigned integer.
	 */
/*    public static int byteToShort(byte[] b, int offset){
        short retVal;

        if (b.length == 1){
            retVal = b[0];
        }else{
            //retVal = (short)(b[offset] << 8 | b[offset+1]);
            retVal = (short)(b[offset] << 8 | b[offset+1]);
        }
        
        if(retVal < 0) return 256+retVal;
        return retVal;
    }
    *///christine need change name
// This allows to convert 2 bytes in sequence into a single integer number
public static int byte2tointeger(byte[] bytes, int offset){
	
    int a= (bytes[offset+0]<< 8)&0x0000ff00|
    (bytes[offset+1]<< 0)&0x000000ff;
	  
	   return a;
}
    
  
	/**
	 * This method returns a byte from a bitset.
	 * @param bits A bitset(array of bools) 
	 * @return byte A byte forged from the elements of the bitset ONE BYTE TO RULE ALL OTHER BYTES .
	 */    
/*    public static byte bitsetToByte(BitSet bits) {
        byte b = 0; //= new byte[bits.length()/8+1];
        
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                b |= 1<<(i%8);
            }
        }
        return b;
    }*/
   /* public static byte bitsetToByte(BitSet bits) {
        byte b = 0; //= new byte[bits.length()/8+1];
        
        for (int i=0; i<bits.length(); i++) {
            if (bits.get(i)) {
                //b |= 1<<(i%8);// b = b or ( a byte which is 00000001 shift left to correct position depend on get(i))christine  erase the % 8 after check all call s o f this function in this program 
            	b |= 1<<(i);
            }
        }
        return b;
    }
	
    */// christine:
// This allows to convert a single bitset (8 bits) into a single byte
public static byte bitstobyte(BitSet bits) {
	byte[] a = new byte[1];
    	
    for(int i = 0; i<bits.length(); i++)
    	{
        if(bits.get(i) == true)
            a[0] |= (1 <<i) ;            
    	}
    return a[0];
    }

	/**
	 * This method returns a 1 byte String
	 * @param b Byte to convert 
	 * @return String One Byte String
	 */  
/*    public static String byteToString(byte b){
    	short retVal = b;
    	
    	if(retVal < 0) return Short.toString((short)(256+retVal));
    
    	return Short.toString(b);
    }
	*/
    
// This allows to convert one single byte into an integer number 
public static int onebytetointeger(byte b) {
	return (int) b & 0xFF;// christine: cuz when byte to int: always think it is signed, so need to make it positive
	}
	/**
	 * This function will remove all pre-appended values which count label characters into domain separators '.'
	 * @param in String data
	 * @return String Outputs String with '.' separators
	 */  
	/*public static String convStops(String in){
		byte[] out = new byte[in.length()];
				
		for(int i=0;i<in.length();i++){
			//Checks if its a label element
			if((Character.isDigit(in.charAt(i))) || 
			(Character.isLetter(in.charAt(i)))||
			in.charAt(i)=='?'  || 
			in.charAt(i)=='-'||
			in.charAt(i)=='@'){
				out[i] = (byte)(in.charAt(i));
			} else { //Is label separator
				out[i] = (byte)'.';
			}
		}
	
		String outsecond = new String(out).substring(1);// create a string by decoding byte and then just take the whole without the leftmost string
	return outsecond;
	/// 3www3qwe2ca meme length vs .www.que.ca then substring(1) is to print from .
				
	}*///christine:

// This allows to take a string that presents a domain name as digit/letters/digit/letters...format and convert into a string in letter/dots/letter/dots...format
public static String addrseqtodots(String nameWithFrontSeqNum){
	byte[] out = new byte[nameWithFrontSeqNum.length()];
  				
	for(int i=0;i<nameWithFrontSeqNum.length();i++){
  		//if it is everything that can be present in a domain name (other than dots)
		if((Character.isDigit(nameWithFrontSeqNum.charAt(i))) || 
  			(Character.isLetter(nameWithFrontSeqNum.charAt(i)))||
  			nameWithFrontSeqNum.charAt(i)=='?'  || 
  			nameWithFrontSeqNum.charAt(i)=='-'||
  			nameWithFrontSeqNum.charAt(i)=='@'){
  				out[i] = (byte)(nameWithFrontSeqNum.charAt(i));
  			} 
		//if it is a dot
		else { 
  				out[i] = (byte)'.';
  			}
  		}
  	// Print out the converted format but start by skipping the first substring because it is an extra and useless dot
	String shortenString = new String(out).substring(1);// create a string by decoding byte and then just take the whole without the leftmost string
  	return shortenString;
  	}
    		
    		//christine string to bytes
    		/*public static String StringToBytes(String SearchDomainName){
    	  		byte[] out = new byte[SearchDomainName.length()];
    	  				
    	  		for(int i=0;i<SearchDomainName.length();i++){
    	  			//Checks if its a label element
    	  			if((Character.isDigit(SearchDomainName.charAt(i))) || 
    	  			(Character.isLetter(nameWithFrontSeqNum.charAt(i)))||
    	  			nameWithFrontSeqNum.charAt(i)=='?'  || 
    	  			nameWithFrontSeqNum.charAt(i)=='-'||
    	  			nameWithFrontSeqNum.charAt(i)=='@'){
    	  				out[i] = (byte)(nameWithFrontSeqNum.charAt(i));
    	  			} else { //Is label separator
    	  				out[i] = (byte)'.';
    	  			}
    	  		}
    	  	
    	  		String shortenString = new String(out).substring(1);// create a string by decoding byte and then just take the whole without the leftmost string
    	  	return shortenString;
    	  	}*/
	
	/**
	 * This method will return a String output of a 4 byte IP
	 * @param recData A byte array which holds 4 bytes. It will only read the first 4 bytes starting from 0
	 * @return String Returns String IP
	 */  
// This allows to convert an array of data into a single string that indicates an IP address with dots format
public static String bytesTowholeIPString (byte[] byteofpureIPdigits){
	String wholeIPwithdots = "";
		
	int number0 =  onebytetointeger(byteofpureIPdigits[0]);
	int number1 =  onebytetointeger(byteofpureIPdigits[1]);
	int number2 =  onebytetointeger(byteofpureIPdigits[2]);
	int number3 =  onebytetointeger(byteofpureIPdigits[3]);

	String string0 = String.valueOf(number0);
	String string1 = String.valueOf(number1);
	String string2 = String.valueOf(number2);
	String string3 = String.valueOf(number3);
    	
	wholeIPwithdots = string0 + "."+string1+"."+string2+ "."+ string3;
	
	return wholeIPwithdots;
	}
	
	/**
	 * Returns weather a specific record name is a root name. IE no label.
	 * @param b The first byte of the record name. 
	 * @return boolean True if name has no label. (Root Name)
	 */  
	/*public static boolean rrNameIsRoot(byte b){
		if (b==0) return true;
		
		return false;
	}*/
	
}