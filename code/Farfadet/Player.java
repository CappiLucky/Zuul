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

    private boolean aDPfee;

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
        this.aDPfee = false;
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
        if (this.aCurrentRoom.getImageName() != null)
            this.aGui.showImage(aCurrentRoom.getImageName());
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
            this.aGui.println ("this object do not exist");
        else if (this.aPoidsMax < this.aInventory.getTotalWeight() + vItem.getWeight())
            this.aGui.println ("your inventory is too heavy");
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
            this.aGui.println ("can't drop this object");
        else {
            this.aInventory.removeItem(vDescr, vItem);
            this.aCurrentRoom.aItemHM.put(vDescr, vItem);
        }
        this.aGui.println (this.aInventory.getItemsString());
    } //dropItem(.)     

    /**
     * Allow you to look around you
     */
    public void look () 
    {
        this.printLocationInfo();
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
            this.aPoidsMax = this.aPoidsMax*2;
            this.aGui.println ("You eat the Magic Cookie, you increases inventory'weight");
            this.aInventory.removeItem(pCommand, vItem);
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
            this.aGui.println ("--> You can't go back");        
    } //back()

    /**
     * @param pCommand command tapper par le joueur
     */
    public void talk (final Command pCommand) {
        String vDescr = pCommand.getSecondWord();
        Character vPNG = this.aCurrentRoom.aPngHM.get(vDescr);
        if (vPNG == null)  {
            this.aGui.println ("this character do not exist");
            return;
        }
        if (vPNG == this.aCurrentRoom.getPNG("Fee")) {
            if (! aDPfee) {
                this.aGui.println ("Hello, I found a goldcoin here... Maybe you want this !");
                this.aInventory.addItem ("goldCoin2", vPNG.getItem());
                this.aDPfee = true;
            } else this.aGui.println("I already give you the goldcoin...");
            return; 
        } //fee
        if (vPNG == this.aCurrentRoom.getPNG("Elfe")) {
            if (! this.aInventory.aInventoryHM.containsKey("magicBook")) {
                this.aGui.println ("Hi... I lost my magic book, if you have this object I give you a goldcoin");
                return;
            } else {
                this.aGui.println ("Thanks for the magic book, I give you a goldcoin");
                this.aInventory.addItem ("goldCoin3", vPNG.getItem());
                this.aInventory.removeItem ("magicBook", this.aInventory.getItem("magicBook"));
            }
        }//elfe
        if (vPNG == this.aCurrentRoom.getPNG("Lutin")) {
            if (! this.aInventory.aInventoryHM.containsKey("mushrooms")){
                this.aGui.println ("Give me mushrooms !");
                return;
            } else {
                this.aGui.println ("Great ! Take it, a goldcoin");
                this.aInventory.addItem ("goldCoin4", vPNG.getItem());
                this.aInventory.removeItem ("mushrooms", this.aInventory.getItem("mushrooms"));
            }
        } //lutin
        if (vPNG == this.aCurrentRoom.getPNG("Arbre")) {
            if (! this.aInventory.aInventoryHM.containsKey("water")){
                this.aGui.println ("OHHH please give me some fresh water");
                return;
            } else {
                this.aGui.println ("Thanks you very much, I'm good now ! Take this glodcoin in thanking");
                this.aInventory.addItem ("goldCoin5", vPNG.getItem());
                this.aInventory.removeItem ("water", this.aInventory.getItem("water"));
            }
        } //arbre
        if (vPNG == this.aCurrentRoom.getPNG("Vezonia")) {
            this.aGui.println ("You found the 5 gold coin ! Congratulation, so I give you the key for the claudron");
            this.aGui.println ("\n In reality it was a test. you are named chief of the farfadet !");
            this.aInventory.addItem ("key", vPNG.getItem());
            //fin du jeu
            this.aGui.println("Thank you for playing.  Good bye.");
            this.aGui.enable(false);
        }
    } //talk(.)

    /**
     * To clear the Stack if the player go in trap door
     */
    public void clearStack() {
        this.aAntRoom.clear();
    } //clearStack()

    // ## Affichage ##
    /**
     * When you arrived in a new room, this methode print description and exits available
     */
    public void printLocationInfo ()
    {
        this.aGui.println (this.aCurrentRoom.getLongDescription());
    } //printLocationInfo() 

    /**
     * The welcome text when you start a new game 
     */
    public void printWelcome ()
    {
        this.aGui.println("Welcome to the World of Zuul!");
        this.aGui.println("The game name is : Alerte au pied de l'arc en ciel");
        this.aGui.println("In a magical forest : you, a farfadet, are called urgently at the foot of the rainbow because gold coin from the cauldron have been stolen !");
        this.aGui.println ("Then, you have to cross the forest to try to find the stolen gold coin");

        this.aGui.println("Type 'help' if you need help.");
        this.aGui.println("\n");

        this.aGui.println(this.aCurrentRoom.getLongDescription()); 
        this.aGui.showImage(this.aCurrentRoom.getImageName()); 
    } //printWelcome()
} //Player 