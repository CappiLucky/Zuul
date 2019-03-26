
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
    private UserInterface gui;
    
    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
       aParser = new Parser();
       createRooms();
    }
    
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }
    
    /**
     * The welcome text when you start a new game 
     */
    private void printWelcome ()
    {
        gui.println("Welcome to the World of Zuul!");
        gui.println("The game name is : Alerte au pied de l'arc en ciel");
        gui.println("In a magical forest : you, a farfadet, are called urgently at the foot of the rainbow because gold coin from the cauldron have been stolen !");
        gui.println ("Then, you have to cross the forest to try to find the stolen gold coin");

        gui.println("Type 'help' if you need help.");
        gui.println("\n");
        //System.out.println(aCurrentRoom.getLongDescription());
        
        gui.println(aCurrentRoom.getLongDescription());
        gui.showImage(aCurrentRoom.getImageName());
    } //printWelcome()
    
    /**
     * Create Room of the game with his descriptions and exits 
     */
    private void createRooms ()
    {
        //declaration des variables Room
        Room vChaudron = new Room (" at the foot of the rainbow", "chaudron_dor2.jpg");
        Room vClairiere = new Room (" in a great clearing", "clairiere2.jpg");
        Room vFee = new Room (" face to face with a little woods fairy", "fee2.jpg");
        Room vCascade = new Room (" in front of a big water cascade", "cascade_deau2.jpg");
        Room vElfe = new Room (" face to face with a elven", "elfe2.jpg");
        Room vPiece = new Room (" facing a gold coin on the floor", "foret_piece_dor2.jpg");
        Room vLutin = new Room (" face to face with a pixie", "lutin2.jpg");
        Room vArbre = new Room (" face to face with a living tree", "perso_arbre2.jpg");
        Room vLivre = new Room (" facing a magic book", "objet_livre2.jpg");
        Room vSorciere = new Room (" in a secret room, face to face with a witch", "sorciere2.jpg");

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
    } //createRooms() 
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = aParser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
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
        System.out.println("You are lost.");
        System.out.println("You can refer to the map");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println( aParser.showCommands());
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
            gui.println("Go where?");
            return;
        }
        
        String vDirection = pCommand.getSecondWord();
        
        //try to leave current room
        Room vNextRoom = aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null)
        {
            gui.println ("There is no door !");
        } else {
            this.aCurrentRoom = vNextRoom;
            gui.println(aCurrentRoom.getLongDescription());
            if (aCurrentRoom.getImageName() != null)
                gui.showImage(aCurrentRoom.getImageName());
        }    
    } //procedure goRoom() 
    
    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    } 
    
    //autres fonctions
    
    /**
     * Allow you to look around you
     */
    private void look () 
    {
        System.out.println (aCurrentRoom.getLongDescription());
    } //look()
    
    /**
     * Allow you to eat something
     */
    private void eat ()
    {
        System.out.println ("You have eaten now and you are not hungry any more.");
    } //eat() 
    
    
    //non obligatoire (?)
    
    /**
     * When you arrived in a new room, this methode print description and exits available
     */
    private void printLocationInfo ()
    {
        System.out.println (this.aCurrentRoom.getLongDescription());
        System.out.println();
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
            System.out.println("Quit what ?");
            return vSecondWord =false;
        } else 
        {return vSecondWord;
        }
    } //quit() 
}
