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
    private String aDescription; 
    private String aImageName;
    //private Item aItem;

    /**
     * Create a room described by "pDescription".
     * Initially, it has no exits.
     * "pDescription" is something like "a forest"
     * 
     * @param pDescription The description of the room
     */
    public Room (final String pDescription, final String pImage/*, final Item pItem*/) 
    {
        this.aDescription = pDescription;
        aExitHM = new HashMap <String, Room> ();
        aItemHM = new HashMap <String, Item> ();
        this.aImageName = pImage;
        //this.aItem = pItem;
    } //Room(..)

    /**
     * @return aDescription a String description of the room
     * (the one that was defined in the constructor)
     */
    public String getDescription ()
    {
        return this.aDescription;
    }   

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
            return ("\n" + "You are " + this.aDescription + ".\n" + getExitString());
        } else {
            return ("\n" + "You are " + this.aDescription + ".\n" 
                + "Objet in this room is " + this.getItemString() + ".\n" 
                + getExitString() );
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
        return aExitHM.get(pDirection);
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
        Set<String> keys = aExitHM.keySet();
        for (String aExitHM : keys ) 
        {
            vExit += ' ' + aExitHM; 
        }
        return vExit; 
    } //getExitString()

    /**
     * Return a string describing the room's image name
     */
    public String getImageName() 
    {
        return aImageName;
    } //getImageName()

    public String getItemString () {
        String vReturnString = "Objects :";
        Set<String> vKeys = this.aItemHM.keySet();
        for (String vItem : vKeys) {
            vReturnString += "\n" + aItemHM.get(vItem).getItemInformation();
        }
        return vReturnString;
    } //getItemString()

    public void addItem (final String pName, final Item pItem) {
        this.aItemHM.put(pName, pItem);
    }
}// Room
