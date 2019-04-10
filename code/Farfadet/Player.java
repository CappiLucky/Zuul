
/**
 * Projet Zuul
 *
 * @author Manon HERMANN    
 * @version v1
 */
public class Player
{
    //attributs : nom, piece courante, item, poids maxi
    private String aNom;
    private Room aCurrentRoom;
    private int aPoidsMax;
    private Item aItem; //jsp si obligatoire
    private UserInterface aGui; 
    
    public Player (final String pNom, final Room pCurrentRoom, final int pPoidsMax){
        this.aNom = pNom;
        this.aCurrentRoom = pCurrentRoom;
        this.aPoidsMax = aPoidsMax;
    } //constructeur par defaut de player
    
    public void setGui (final UserInterface pUserInterface){
        this.aGui = pUserInterface;
    } //setGui(.)
    
    public void changeRoom (final Room pRoom){
        this.aCurrentRoom = pRoom;
    } //changeRoom(.)
    
    //canBePickedUp boolean : 
    //prendre et deposer (nom d'objet comme deuxieme mot) : drop + take qui verifie la contrainte de poids et retrouver la valeur false si nous ne pouvons pas l'emporter
    public void takeItem (final String pNomItem) {
        //if (){
            //this.aItem = aItemHM.get(pNomItem); 
        //}
    }
    
    public void dropItem (final String pNomItem) {
        //rajouter des choses
        this.aItem = null;
    }
    
    //collection pour stocké les objets transporter par le joueur :
    
    //deplacer ici : eat, look, ...
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
        aGui.println ("You have eaten now and you are not hungry any more.");
    } //eat() 
    
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
}
