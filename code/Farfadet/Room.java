import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

/**
 * Project Zuul
 * Class for make action on room
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class Room
{
    public HashMap <String, Room> aExitHM; //HashMap reliant les room et leur sortie
    public HashMap <String, Item> aItemHM; //HashMap reliant les room et leur item
    public HashMap <String, Door> aDoorHM; 
    private String aDescription; 
    private String aImageName;

    /**
     * Create a room described by "pDescription".
     * Initially, it has no exits.
     * "pDescription" is something like "a forest"
     * 
     * @param pDescription The description of the room
     * @param pImage the name of the image
     */
    public Room (final String pDescription, final String pImage) 
    {
        this.aDescription = pDescription;
        this.aExitHM = new HashMap <String, Room> ();
        this.aItemHM = new HashMap <String, Item> ();
        this.aDoorHM = new HashMap <String, Door> ();
        this.aImageName = pImage;
    } //Room(..)

    // ## Accesseurs ##
    /**
     * @return aDescription a String description of the room
     * (the one that was defined in the constructor)
     */
    public String getDescription ()
    {
        return this.aDescription;
    } //getDescription()

    /**
     * Return a long description of this room, of the form :
     *      You are in the kitchen.
     *      Exits : North West
     *      
     * @return A description of the room, including exits     
     */
    public String getLongDescription()
    { 
        if (this.aItemHM.isEmpty()) {
            return ("\n" + "You are " + this.aDescription + ".\n" + this.getExitString());
        } else {
            return ("\n" + "You are " + this.aDescription + ".\n" 
                + this.getItemString() + ".\n" 
                + this.getExitString() );
        }
    } //getLongDescription()

    /**
     * Define the exits of the room.
     * Every direction either leads to another room or is null (no exit there)
     * 
     * @param pDirection The direction where do you want to go
     * @param pRoom The room where you go
     */
    public void setExits (final String pDirection, final Room pRoom /* final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit*/)
    {
        this.aExitHM.put(pDirection, pRoom);
    } //setExits(....)

    /**
     * Return the room that is reached if we go from this room in direction "pDesction".
     * If there is no room in that direction, return null.
     * 
     * @param pDirection The direction where do you want to go
     * @return The direction(s) where you can go
     */
    public Room getExit (String pDirection)
    {
        return this.aExitHM.get(pDirection);
    } //getExit(.)

    /**
     * Return a description of the room's exits,
     * for example "exits : North, West"
     * 
     * @return The exit available
     */
    public String getExitString() 
    {
        String vExit = "Exits : ";
        Set<String> keys = this.aExitHM.keySet();
        for (String aExitHM : keys ) 
        {
            vExit += ' ' + aExitHM; 
        }
        return vExit; 
    } //getExitString()

    /**
     * @return a string describing the room's image name
     */
    public String getImageName() 
    {
        return this.aImageName;
    } //getImageName()

    /**
     * @return a String with all object in the inventory of player
     */
    public String getItemString () {
        String vReturnString = "Objects :";
        Set<String> vKeys = this.aItemHM.keySet();
        for (String vItem : vKeys) {
            vReturnString += "\n" + this.aItemHM.get(vItem).getItemInformation();
        }
        return vReturnString;
    } //getItemString()

    /**
     * @param pDir wish direction
     * @param pDoor name of the door
     */
    public void setDoor (final String pDir, final Door pDoor) {
        this.aDoorHM.put(pDir, pDoor);
    } //setDoor(..)
    
    /**
     * @return Door in the direction
     * @param pDir wish' direction 
     */
    public Door getDoor (final String pDir) {
        return this.aDoorHM.get(pDir);
    } //getDoord(.)
    
    // ## Methodes ##
    /**
     * @param pName name of the item
     * @param pItem Item which we want to add
     */
    public void addItem (final String pName, final Item pItem) {
        this.aItemHM.put(pName, pItem);
    } //adItem(..)
    
    /**
     * @param pName name of the item which we want to remove
     */
    public void removeItem (final String pName) {
        this.aItemHM.remove(pName);
    } //removeItem(..)
}// Room
