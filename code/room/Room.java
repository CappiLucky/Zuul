 //a completer selon l'histoire
 
 
public class Room
{
    private String aDescription = "au pied du chaudron d'or"; 
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
    
}// Room
