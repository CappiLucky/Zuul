/**
 * Project Zuul
 * Class for create a specialy door
 *
 * @author Manon HERMANN
 * @version v1
 */
public class Door
{
    private boolean aTrap;
    private boolean aLock;
    private boolean aGoodDir;

    /**
     * Constructor of the door
     * 
     * @param pTrap say if it's a trap
     * @param pLock say if it's lock
     * @param pGoodDir say if it's the good direction
     */
    public Door (final boolean pTrap, final boolean pLock, final boolean pGoodDir)
    {
        this.aTrap = pTrap;
        this.aLock = pLock;
        this.aGoodDir = pGoodDir;
    } //constructeur par defaut
    
    // ## Accesseurs ##
    /**
     * @return boolean to say if it's a trap door
     */
    public boolean isTrapDoor () {
        return this.aTrap;
    } //isTrapDoor()
    
    /**
     * @return boolean to say if it's a lock door
     */
    public boolean isLockDoor () {
        return this.aLock;
    } //isLockDoor()
    
    /**
     * @return boolean to say if it's a good direction
     */
    public boolean canGo () {
        return this.aGoodDir;
    } //canGo()
    
    /**
     * @param pLock change the locked door
     */
    public void setLock (final boolean pLock) {
        this.aLock = pLock;
    } //setLock(.)
    
    /**
     * @param pGoodDir change the good direction
     */
    public void setCanGo (final boolean pGoodDir) {
        this.aGoodDir = pGoodDir;
    } //setCanGo(.)
}
