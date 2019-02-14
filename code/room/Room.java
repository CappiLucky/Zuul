import java.util.HashMap;
 
public class Room
{
    public HashMap <String, Room> aExitHM;
    private String aDescription = "at the foot of the rainbow"; 
    /* public Room aNorthExit = null;
    public Room aEastExit = null;
    public Room aSouthExit = null;
    public Room aWestExit = null; */
    
    public Room (final String pDescription) 
    {
        this.aDescription = pDescription;
        aExitHM = new HashMap <String, Room> ();
    } //Room(.)
    
    public String getDescription ()
    {
        return this.aDescription;
    }   
    
    public void setExits (final String pDirection, final Room pRoom /* final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit*/)
    {
        aExitHM.put(pDirection, pRoom); 
        /* this.aNorthExit = pNorthExit;
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit; */ 
    } //setExits(....)
    
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
    
    public String getExitString() 
    {
        String vExits = "";
        if (this.aNorthExit != null) vExits += " North";
        if (this.aEastExit != null) vExits += " East";
        if (this.aSouthExit != null) vExits += " South";
        if (this.aWestExit != null) vExits += " West";
        return vExits; 
    } //getExitString()
}// Room
