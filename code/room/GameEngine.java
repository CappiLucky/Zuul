import java.util.HashMap;

/**
 * Project Zuul
 * This class creates all rooms, creates the parser and starts
 * the game.  It also evaluates and executes the commands that 
 * the parser returns.
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class GameEngine
{
    private Room aCurrentRoom;
    private Parser aParser;
    private UserInterface aGui;
    public HashMap <Room, String> aRoomsHM; //HashMap reliant les Rooms et leur nom
    
    
    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
       aParser = new Parser();
       aRoomsHM = new HashMap <Room, String> (); 
       createRooms();
    }
    
    public void setGUI(UserInterface pUserInterface)
    {
        aGui = pUserInterface;
        printWelcome();
    }
    
    /**
     * The welcome text when you start a new game 
     */
    private void printWelcome ()
    {
        aGui.println("Welcome to the World of Zuul!");
        aGui.println("The game name is : Alerte au pied de l'arc en ciel");
        aGui.println("In a magical forest : you, a farfadet, are called urgently at the foot of the rainbow because gold coin from the cauldron have been stolen !");
        aGui.println ("Then, you have to cross the forest to try to find the stolen gold coin");

        aGui.println("Type 'help' if you need help.");
        aGui.println("\n");
        //gui.println(aCurrentRoom.getLongDescription());
        
        aGui.println(aCurrentRoom.getLongDescription()); 
        aGui.showImage(aCurrentRoom.getImageName()); 
    } //printWelcome()
    
    /**
     * Create Room of the game with his descriptions and exits 
     */
    private void createRooms ()
    {
        //declaration des items
        Items vIPiece = new Items ("gold coin", 1);
        Items vIEau = new Items ("water", 1);
        Items vILivre = new Items ("book", 1);
            //a finir
        
        //declaration des variables Room
        Room vChaudron = new Room (" at the foot of the rainbow", "images/chaudron_dor2.jpg", null);
        Room vClairiere = new Room (" in a great clearing", "images/clairiere2.jpg", null);
        Room vFee = new Room (" face to face with a little woods fairy", "images/fee2.jpg", null);
        Room vCascade = new Room (" in front of a big water cascade", "images/cascade_deau2.jpg", vIEau);
        Room vElfe = new Room (" face to face with a elven", "images/elfe2.jpg", null);
        Room vPiece = new Room (" facing a gold coin on the floor", "images/foret_piece_dor2.jpg", null);
        Room vLutin = new Room (" face to face with a pixie", "images/lutin2.jpg", null);
        Room vArbre = new Room (" face to face with a living tree", "images/perso_arbre2.jpg", null);
        Room vLivre = new Room (" facing a magic book", "images/objet_livre2.jpg", vILivre);
        Room vSorciere = new Room (" in a secret room, face to face with a witch", "images/sorciere2.jpg", null);

        //positionner les sorties
        vChaudron.setExits ("East", vSorciere);
        vChaudron.setExits ("West", vClairiere);
        vClairiere.setExits ("North", vFee);
        vClairiere.setExits ("East", vChaudron);
        vClairiere.setExits ("South", vArbre);
        vClairiere.setExits ("West", vPiece);
        vFee.setExits ("North", vCascade);
        vFee.setExits ("South", vClairiere);
        vCascade.setExits ("South", vFee);
        vCascade.setExits ("West", vElfe);
        vElfe.setExits ("North", vCascade);
        vElfe.setExits ("South", vPiece);
        vPiece.setExits ("North", vElfe);
        vPiece.setExits ("East", vClairiere);
        vPiece.setExits ("Up", vLutin);
        vLutin.setExits ("Down", vPiece);
        vArbre.setExits ("North", vClairiere);
        vArbre.setExits ("East", vLivre);
        vLivre.setExits ("West", vArbre);
        vSorciere.setExits ("West", vChaudron);

        //initialiser le lieu de depart
        this.aCurrentRoom = vChaudron;
        
        //initialisation de la aRoomsHM
        aRoomsHM.put(vChaudron, "caldron'room");
        aRoomsHM.put(vClairiere, "clearing'room");
        aRoomsHM.put(vFee, "woods fairy'room");
        aRoomsHM.put(vCascade, "water cascade'room");
        aRoomsHM.put(vElfe, "elven'room");
        aRoomsHM.put(vPiece, "gold coin'room");
        aRoomsHM.put(vLutin, "pixie'room");
        aRoomsHM.put(vArbre, "living tree'room");
        aRoomsHM.put(vLivre, "magic book'room");
        aRoomsHM.put(vSorciere, "witch'room");
    } //createRooms() 
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String pCommandLine) 
    {
        aGui.println(pCommandLine);
        Command command = aParser.getCommand(pCommandLine);

        if(command.isUnknown()) {
            aGui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                aGui.println("Quit what?");
            else
                endGame();
        } 
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("eat"))
            eat();
    }
    
    /**
     * The help text when you write "help"
     */
    private void printHelp ()
    {
        aGui.println("You are lost.");
        aGui.println("You can refer to the map");
        aGui.println("\n");
        aGui.println("Your command words are:");
        aGui.println(aParser.showCommands());
    } //printHelp()
    
    /**
     * Go in the room passed in parameters
     * 
     * @param pCommand The word which has writting
     */
    private void goRoom (final Command pCommand)
    {
        if(!pCommand.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            aGui.println("Go where?");
            return;
        }
        
        String vDirection = pCommand.getSecondWord();
        
        //try to leave current room
        Room vNextRoom = aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null)
        {
            aGui.println ("There is no door !");
        } else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
            if (aCurrentRoom.getImageName() != null)
                aGui.showImage(aCurrentRoom.getImageName());
        }    
    } //procedure goRoom() 
    
    private void endGame()
    {
        aGui.println("Thank you for playing.  Good bye.");
        aGui.enable(false);
    } 
    
    //autres fonctions
    
    /**
     * Allow you to look around you
     */
    private void look () 
    {
        printLocationInfo();
    } //look()
    
    /**
     * Allow you to eat something
     */
    private void eat ()
    {
        aGui.println ("You have eaten now and you are not hungry any more.");
    } //eat() 
    
    
    //non obligatoire (?)
    
    /**
     * When you arrived in a new room, this methode print description and exits available
     */
    private void printLocationInfo ()
    {
        aGui.println (this.aCurrentRoom.getLongDescription());
        aGui.println("\n");
    } //printLocationInfo()  
    
    /**
     * Command to quit game
     * 
     * @param pC The word you written
     * @return A boolean which say if game is finish or not
     */
    private boolean quit (final Command pC)
    {
        boolean vSecondWord = true;
        if(pC.getSecondWord() == null)
        {
            aGui.println("Quit what ?");
            return vSecondWord =false;
        } else 
        {return vSecondWord;
        }
    } //quit() 
    
}
