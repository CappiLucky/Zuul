<<<<<<< HEAD
=======
/* doit encore :
* - faire une HashMap pour relier les rooms et les images
* - faire un bouton
* - deplacer les images dans un repertoire
* - creer les items disponible dans le jeu
* - ajouter la description des differents items
*/

>>>>>>> d2fa9cff1f6cbb177c2592c93280fa56657a9fde
/**
 * Project Zuul
 * Class for initialise new game
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class Game 
{
<<<<<<< HEAD
    private Room aCurrentRoom;
    private Parser aParser;

    /**
     * Create Room of the game with his descriptions and exits 
     */
    private void createRooms ()
    {
        //declaration des variables Room
        Room vChaudron = new Room (" at the foot of the rainbow");
        Room vClairiere = new Room (" in a great clearing");
        Room vFee = new Room (" face to face with a little woods fairy");
        Room vCascade = new Room (" in front of a big water cascade");
        Room vElfe = new Room (" face to face with a elven");
        Room vPiece = new Room (" facing a gold coin on the floor");
        Room vLutin = new Room (" face to face with a pixie" );
        Room vArbre = new Room (" face to face with a living tree");
        Room vLivre = new Room (" facing a magic book");
        Room vSorciere = new Room (" in a secret room, face to face with a witch");

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
        vLutin.setExits ("East", vPiece);
        vArbre.setExits ("North", vClairiere);
        vArbre.setExits ("East", vLivre);
        vLivre.setExits ("West", vArbre);
        vSorciere.setExits ("West", vChaudron);

        //initialiser le lieu de depart
        this.aCurrentRoom = vChaudron;
    } //createRooms()   

    /**
     * Begin a new game
=======
    //private Room aCurrentRoom;
    //private Parser aParser;
    private UserInterface gui;
    private GameEngine engine;
 
    /**
     * Begin a new game
     * Create the game and initialise its internal map.
>>>>>>> d2fa9cff1f6cbb177c2592c93280fa56657a9fde
     */
    public Game() //a laisser
    {
        //createRooms();
        //this.aParser = new Parser();
       engine = new GameEngine();
       gui = new UserInterface(engine);
       engine.setGUI(gui);
    } //contructeur par defaut : Game()  

<<<<<<< HEAD
    /**
     * Go in the room passed in parameters
     * 
     * @param pCommand The word which has writting
     */
    private void goRoom (final Command pCommand)
    {
        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = null;
        vNextRoom = aCurrentRoom.getExit(vDirection);

        if (vNextRoom == null)
        {
            System.out.println ("There is no door !");
        } else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
        }    
    } //procedure goRoom() 

    /**
     * The welcome text when you start a new game 
     */
    private void printWelcome ()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("In a magical forest : you, a farfadet, are called urgently at the foot of the rainbow because gold coin from the cauldron have been stolen !");
        System.out.println ("Then, you have to cross the forest to try to find the stolen gold coin");

        System.out.println("Type 'help' if you need help.");
        System.out.println('\n');
            
        System.out.println(aCurrentRoom.getLongDescription());
    } //printWelcome()

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

    /**
     * Methode which reconize the word written on the keyboard
     * Available command "go" "help" "quit"
     * 
     * @param pCommand The word you written
     * @return The result of the word 
     */
    private boolean processCommand (final Command pCommand)
    {
        if (pCommand.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        } 
        String commandWord = pCommand.getCommandWord();
        if (commandWord.equals("go"))
        {
            goRoom(pCommand);
            return false;
        } 
        else if (commandWord.equals("help"))
        {
            printHelp();
            return false;
        } 
        else if (commandWord.equals("quit")) 
        {
            return quit(pCommand);
        } 
        else if (commandWord.equals("look"))
        {
            look();
            return false;
        }
        else if (commandWord.equals("eat"))
        {
            eat();
            return false;
        }
        else return false;
    } //processCommand(.)

    /**
     * The game engine
     */
    public void play()
    {
        Command vCommand;
        printWelcome();
        boolean vFinished = false;
        while (vFinished == false)
        {
            vCommand = this.aParser.getCommand();
            vFinished = this.processCommand(vCommand);
        } 
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * When you arrived in a new room, this methode print description and exits available
     */
    private void printLocationInfo ()
    {
        System.out.println (this.aCurrentRoom.getLongDescription());
        System.out.println();
    } //printLocationInfo()   

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
=======
>>>>>>> d2fa9cff1f6cbb177c2592c93280fa56657a9fde
} // Game
