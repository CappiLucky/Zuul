
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
    
    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognised by this game.
     */
    public Command (final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } //Command(..) 
    
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     */
    public String getCommandWord ()
    {
        return this.aCommandWord;
    }   
    
    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord ()
    {
        return this.aSecondWord; 
    }  
    
    /**
     * Return true if the command has a second word.
     */
    public boolean hasSecondWord ()
    {
        /*boolean vHasSecondWord = true;
        if (this.aSecondWord == null)  
        {
            vHasSecondWord = false;
        }   
        return vHasSecondWord; */
        return (aSecondWord != null);
    } //hasSecondWord()
    
    /**
     * Return true if this command was not understood.
     */
    public boolean isUnknown () 
    {
        /*boolean vIsUnknown = false;
        if (this.aCommandWord == null) 
        {
            vIsUnknown = true;
        }    
        return vIsUnknown;*/
        return (aCommandWord == null);
    } //isUnkown()  
} // Command 