import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * Project Zuul
 * Class for make action on room
 * 
 * @autor Manon HERMANN
 * @version v1
 */
public class Room
{
    /* public Room aNorthExit = null;
    public Room aEastExit = null;
    public Room aSouthExit = null;
    public Room aWestExit = null; */
    public HashMap <String, Room> aExitHM;
    private String aDescription = "at the foot of the rainbow"; 

    /**
     * Create a room described by "pDescription".
     * Initially, it has no exits.
     * "pDescription" is something like "a forest"
     * 
     * @param pDescription The description of the room
     * @return Nothing
     */
    public Room (final String pDescription) 
    {
        this.aDescription = pDescription;
        aExitHM = new HashMap <String, Room> ();
    } //Room(.)

    /**
     * Return a String description of the room
     * (the one that was defined in the constructor)
     * 
     * @param Nothing No parameters required
     * @return Nothing
     */
    public String getDescription ()
    {
        return this.aDescription;
    }   

    /**
     * Define the exits of the room.
     * Every direction either leads to another room or is null (no exit there)
     * 
     * @param pDirection The direction where do you want to go
     * @param pRoom The room where you go
     * @return Nothing
     */
    public void setExits (final String pDirection, final Room pRoom /* final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit*/)
    {
        aExitHM.put(pDirection, pRoom); 
        /* this.aNorthExit = pNorthExit;
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit; */ 
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
        /*if (pDirection.equals("North"))
        { return this.aNorthExit; }
        if (pDirection.equals("East"))
        { return this.aEastExit; }
        if (pDirection.equals("South"))
        { return this.aSouthExit; }
        if (pDirection.equals("West"))
        { return this.aWestExit; }
        return null; */
    } //getExit(.)

    /**
     * Return a description of the room's exits,
     * for example "exits : North, West"
     * 
     * @param Nothing No parameters required
     * @return The exit available
     */
    public String getExitString() 
    {
        // String vExits = "";
        String vExit = "Exits : ";
        Set<String> keys = aExitHM.keySet();
        for (String aExitHM : keys ) 
        {
            vExit += ' ' + aExitHM; 
        }
        return vExit; 
        /* if (this.aNorthExit != null) vExits += " North";
        if (this.aEastExit != null) vExits += " East";
        if (this.aSouthExit != null) vExits += " South";
        if (this.aWestExit != null) vExits += " West"; 
        return vExits; */
    } //getExitString()
    
    //exo 7.11
    /**
     * Return a long description of this room, of the form :
     *      You are in the kitchen.
     *      Exits : North West
     *      
     * @return A description of the room, including exits     
     */
    public String getLongDescription()
    {
        return ("You are " + this.aDescription + ".\n" + getExitString());
    } //getLongDescription()
}// Room
