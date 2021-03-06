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
     * 
     * @param pCommandWord 1er mot ecrit
     * @param pSecondWord 2eme mot ecrit
     */
    public Command (final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } //Command(..) 
    
    // ## Accesseurs ##
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * 
     * @return string contains commande word of this command
     */
    public String getCommandWord ()
    {
        return this.aCommandWord;
    }   

    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     * 
     * @return string contains second word of this command
     */
    public String getSecondWord ()
    {
        return this.aSecondWord; 
    }  

    // ## Methodes ##
    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord ()
    {
        return (this.aSecondWord != null);
    } //hasSecondWord()

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown () 
    {
        return (this.aCommandWord == null);
    } //isUnkown()  
} // Command 
