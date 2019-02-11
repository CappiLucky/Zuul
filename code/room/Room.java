
 
public class Room
{
    private String aDescription = "at the foot of the rainbow"; 
    public Room aNorthExit = null;
    public Room aEastExit = null;
    public Room aSouthExit = null;
    public Room aWestExit = null;
    
    public Room (final String pDescription) 
    {
        this.aDescription = pDescription;
    } //Room(.)
    
    public String getDescription ()
    {
        return this.aDescription;
    }   
    
    public void setExits (final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit)
    {
        this.aNorthExit = pNorthExit;
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit;
    } //setExits(....)
    
    public Room getExit (String pDirection)
    {
        if (pDirection.equals("North"))
        { return this.aNorthExit; }
        if (pDirection.equals("East"))
        { return this.aEastExit; }
        if (pDirection.equals("South"))
        { return this.aSouthExit; }
        if (pDirection.equals("West"))
        { return this.aWestExit; }
        return null;
    } //getExit(.)
}// Room
