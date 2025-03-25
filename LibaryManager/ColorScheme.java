public class ColorScheme {
    public static final String COMMAND_COLOR = "\033[1;32m";  // Zaļā krāsa (izvēlēties jebkuru krāsu)
    public static final String RESET = "\033[0m";             // Atiestatīt krāsu
    
    
    public static final String ADD_COLOR = "\033[38;5;34m";      // Tumši zaļš
    public static final String REMOVE_COLOR = "\033[38;5;34m";   
    public static final String FIND_COLOR = "\033[38;5;34m\"";     
    public static final String REGISTER_COLOR = "\033[38;5;34m\""; 
  
    
    
    
    public static final String TABLE_HEADER_COLOR = "\033[94m";
    public static final String TABLE_RESET = "\033[0m";
   
  
  
    // Krāsa, lai izceltu kļūdas vai brīdinājumus
    public static final String ERROR_COLOR = "\033[31m"; // Sarkans par kļūdām
    public static final String ERROR_RESET = "\033[0m"; // Atiestatīt krāsu
}
