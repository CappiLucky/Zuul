import java.util.Stack;

/**
 * Projet Zuul
 *
 * @author Manon HERMANN    
 * @version v1
 */
public class Player
{
    private String aName;
    private Room aCurrentRoom;
    private int aPoidsMax = 50;
    private ItemList aInventory;
    private UserInterface aGui; 
    public Stack <Room> aAntRoom; //pile des room precedentes
    
    public Player (final String pNom, final Room pCurrentRoom){
        this.aName = pNom;
        this.aCurrentRoom = pCurrentRoom;
        aAntRoom = new Stack <Room> ();
    } //constructeur par defaut de player
    
     public Room getCurrentRoom (){
        return this.aCurrentRoom;
    }

    public String getName () {
        return this.aName;
    }
    
    public void setCurrentRoom (final Room pRoom){
        this.aCurrentRoom = pRoom;
    }

    public ItemList getInventory (){
        return this.aInventory;
    }
    
    public void setGui (final UserInterface pUserInterface){
        this.aGui = pUserInterface;
    } //setGui(.)
    
    //methodes : 
    public void changeRoom (final Room pRoom){
        this.aCurrentRoom = pRoom;
        this.aGui.println (this.aCurrentRoom.getLongDescription ());
        if (aCurrentRoom.getImageName() != null)
            aGui.showImage(aCurrentRoom.getImageName());
    } //changeRoom(.)
    
    //canBePickedUp boolean : 
    //prendre et deposer (nom d'objet comme deuxieme mot) : drop + take qui verifie la contrainte de poids et retrouver la valeur false si nous ne pouvons pas l'emporter
    /* public void takeItem (final String pNomItem) {
        this.aInventory.setItem(pNomItem,this.aCurrentRoom.getItem(pNomItem));
        this.aCurrentRoom.removeItem(pNomItem);
    }
    
    public void dropItem (final String pNomItem) {
        this.aCurrentRoom.setItem(pNomItem,this.aInventory.getItem(pNomItem));
        this.aInventory.removeItem(pNomItem);
    }*/
    
    //collection pour stocké les objets transporter par le joueur :
    
    //methode que peut utiliser le Player
    /**
     * Allow you to look around you
     */
    public void look () 
    {
        printLocationInfo();
    } //look()
    
    /**
     * Allow you to eat something
     */
    public void eat ()
    {
        this.aGui.println ("You have eaten now and you are not hungry any more.");
    } //eat() 
    
    /**
     * When you arrived in a new room, this methode print description and exits available
     */
    public void printLocationInfo ()
    {
        aGui.println (this.aCurrentRoom.getLongDescription());
    } //printLocationInfo()  
    
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
    
    public Stack <Room> getStackRoom(){
        return this.aAntRoom;
    }
    
    
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
}
