import java.util.Stack;

/**
 * Projet Zuul
 * Class to controle the player
 *
 * @author Manon HERMANN    
 * @version v1
 */
public class Player
{
    private String aName;
    private Room aCurrentRoom;
    private int aPoidsMax = 19;
    private Item aItem;
    private ItemList aInventory;
    private UserInterface aGui; 
    public Stack <Room> aAntRoom; //pile des room precedentes

    /**
     * Construtor of the player
     * 
     * @param pNom nom du joueur
     * @param pCurrentRoom room ou commence le joueur
     */
    public Player (final String pNom, final Room pCurrentRoom){
        this.aName = pNom;
        this.aCurrentRoom = pCurrentRoom;
        this.aAntRoom = new Stack <Room> ();
        this.aInventory = new ItemList ();
    } //constructeur par defaut de player

    // ## Accesseurs ##
    /**
     * Accesseur of the current room
     * 
     * @return String de la salle
     */
    public Room getCurrentRoom (){
        return this.aCurrentRoom;
    } //getCurrentRoom()

    /**
     * @return int which is the ttale weight of the inventory
     */
    public int getPoidMax () {
        return this.aPoidsMax;
    } //getPoidMax()

    /**
     * Accesseur for the player
     * 
     * @return String du nom
     */
    public String getName () {
        return this.aName;
    } //getName()

    /**
     * Accesseur set de la currentRoom
     * 
     * @param pRoom current room
     */
    public void setCurrentRoom (final Room pRoom){
        this.aCurrentRoom = pRoom;
    } //setCurrentRoom(.)

    /**
     * Accesseur de l'inventaire
     * 
     * @return ItemList liste de l'inventaire du player
     */
    public ItemList getInventory (){
        return this.aInventory;
    } //getInventory()

    /**
     * Accesseur set du gui
     * 
     * @param pUserInterface interface du joueur
     */
    public void setGui (final UserInterface pUserInterface){
        this.aGui = pUserInterface;
    } //setGui(.)

    /**
     * @return Stack of the precedent room
     */
    public Stack <Room> getStackRoom(){
        return this.aAntRoom;
    } //getStackRoom()

    // ## Methodes ##
    /**
     * Methode for change room
     * 
     * @param pRoom pour indiquer la salle suivante
     */
    public void changeRoom (final Room pRoom){
        this.aCurrentRoom = pRoom;
        this.aGui.println (this.aCurrentRoom.getLongDescription ());
        if (aCurrentRoom.getImageName() != null)
            aGui.showImage(aCurrentRoom.getImageName());
    } //changeRoom(.)

    /**
     * Methode for take a item in currentRoom
     * 
     * @param pCommand command of the item which you want to take
     */
    public void takeItem (final Command pCommand) {
        String vDescr = pCommand.getSecondWord();
        Item vItem = this.aCurrentRoom.aItemHM.get(vDescr);
        if (vItem == null) 
            aGui.println ("this object do not exist");
        else if (this.aPoidsMax < this.aInventory.getTotalWeight() + vItem.getWeight())
            aGui.println ("your inventory is too heavy");
        else {
            this.aInventory.addItem(vDescr, vItem);
            this.aCurrentRoom.aItemHM.remove(vDescr, vItem);
            this.aGui.println (this.aInventory.getItemsString());
        }
    } //takeItem(.)

    /**
     * Methode for drop a item in currentRoom
     * 
     * @param pCommand command of the item which you want to drop
     */
    public void dropItem (final Command pCommand) {
        String vDescr = pCommand.getSecondWord();
        Item vItem = this.aInventory.aInventoryHM.get(vDescr);
        if (vItem == null)
            aGui.println ("can't drop this object");
        else {
            aInventory.removeItem(vDescr, vItem);
            this.aCurrentRoom.aItemHM.put(vDescr, vItem);
        }
        this.aGui.println (this.aInventory.getItemsString());
    } //dropItem(.)     

    /**
     * Allow you to look around you
     */
    public void look () 
    {
        printLocationInfo();
    } //look()

    /**
     * Allow you to eat something
     * 
     * @param pCommand what you want to eat
     */
    public void eat (final String pCommand)
    {
        Item vItem = this.aInventory.aInventoryHM.get(pCommand);
        if (pCommand.equals("magicCookie") && this.aInventory.aInventoryHM.containsValue(vItem)) {
            this.aPoidsMax = aPoidsMax*2;
            this.aGui.println ("You eat the Magic Cookie, you increases inventory'weight");
            aInventory.removeItem(pCommand, vItem);
        }
        else this.aGui.println ("You can't eat that");
    } //eat(.) 

    /**
     * Allow you to go back
     */
    public void back () {
        if ( ! this.aAntRoom.empty()) {
            this.aCurrentRoom = this.aAntRoom.pop();
            this.aGui.println (this.aCurrentRoom.getLongDescription());
            if (this.aCurrentRoom.getImageName() != null) 
                this.aGui.showImage (this.aCurrentRoom.getImageName());
        }else
            aGui.println ("--> You can't go back");        
    } //back()

    // ## Affichage ##
    /**
     * When you arrived in a new room, this methode print description and exits available
     */
    public void printLocationInfo ()
    {
        aGui.println (this.aCurrentRoom.getLongDescription());
    } //printLocationInfo() 

    /**
     * The welcome text when you start a new game 
     */
    public void printWelcome ()
    {
        aGui.println("Welcome to the World of Zuul!");
        aGui.println("The game name is : Alerte au pied de l'arc en ciel");
        aGui.println("In a magical forest : you, a farfadet, are called urgently at the foot of the rainbow because gold coin from the cauldron have been stolen !");
        aGui.println ("Then, you have to cross the forest to try to find the stolen gold coin");

        aGui.println("Type 'help' if you need help.");
        aGui.println("\n");

        aGui.println(aCurrentRoom.getLongDescription()); 
        aGui.showImage(aCurrentRoom.getImageName()); 
    } //printWelcome()
} //Player