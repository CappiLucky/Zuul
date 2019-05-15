
/**
 * Décrivez votre classe Character ici.
 *
 * @author Manon HERMANN
 * @version v1
 */
public class Character
{
    private String aName;
    private Room aRoom;
    private Item aObject;

    /**
     * Constructeur d'objets de classe Character
     * 
     * @param pName name of the character
     * @param pRoom room of the character
     * @param pObject item of the character
     */
    public Character(final String pName, final Room pRoom, final Item pObject)
    {
        this.aName = pName;
        this.aRoom = pRoom;
        this.aObject = pObject;
    } //constructeur (...)

    /**
     * @return String of the name
     */
    public String getName () {
        return this.aName;
    } //getName()
    
    /**
     * @return Room of the room
     */
    public Room getRoom () {
        return this.aRoom;
    } //getRoom()
    
    /**
     * @return Item of the character
     */
    public Item getItem () {
        return this.aObject;
    } //getItem()
}
