
/**
 * Project Zuul
 * Class for capture words
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    public Command (final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } //Command(..) 
    
    public String getCommandWord ()
    {
        return this.aCommandWord;
    }   
    
    public String getSecondWord ()
    {
        return this.aSecondWord; 
    }  
    
    public boolean hasSecondWord ()
    {
        boolean vHasSecondWord = true;
        if (this.aSecondWord == null)  
        {
            vHasSecondWord = false;
        }   
        return vHasSecondWord; 
    } //hasSecondWord()
    
    public boolean isUnknown () 
    {
        boolean vIsUnknown = false;
        if (this.aCommandWord == null) 
        {
            vIsUnknown = true;
        }    
        return vIsUnknown;
    } //isUnkown()  
} // Command 