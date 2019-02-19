
/**
 * Project Zuul
 * Class for initialise new game
 * 
 * @autor Manon HERMANN
 * @version v1
 */
public class Game 
{
    private Room aCurrentRoom;
    private Parser aParser;

    /**
     * Create Room of the game with his descriptions and exits 
     * 
     * @param Nothing No parameters required
     * @return Nothing
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
        // avant : vChaudron.setExits( null, vSorciere, null, vClairiere)
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
     * 
     * @param Nothing No parameters required
     * @return Nothing
     */
    public Game() 
    {
        createRooms();
        this.aParser = new Parser();
    } //contructeur par defaut : Game()  

    /**
     * Go in the room passed in parameters
     * 
     * @param pCommand The word which has writting
     * @return Nothing
     */
    private void goRoom (final Command pCommand)
    {
        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = null;

        vNextRoom = aCurrentRoom.getExit(vDirection);

        /* if (vDirection == null)
        {
        System.out.println("Go where ?");
        return;
        } else {   
        if (vDirection.equals("North"))
        {
        vNextRoom = this.aCurrentRoom.getExit("North");
        //return this.aNorthExit;
        } 
        else if (vDirection.equals("East"))
        {
        vNextRoom = this.aCurrentRoom.getExit("East");
        }
        else if (vDirection.equals("South"))
        {
        vNextRoom = this.aCurrentRoom.getExit("South");
        }
        else if (vDirection.equals("West"))
        {
        vNextRoom = this.aCurrentRoom.getExit("West");
        } else {
        System.out.println ("Unknown direction !");
        return;
        }    
        } */

        if (vNextRoom == null)
        {
            System.out.println ("There is no door !");
        } else {
            this.aCurrentRoom = vNextRoom;
            printLocationInfo();
            /* System.out.println("You are "+vNextRoom.getDescription());
            System.out.print("Exits : ");
            if (this.aCurrentRoom.aNorthExit != null)
            {
            System.out.print ("North ");
            }    
            if (this.aCurrentRoom.aEastExit != null)
            {
            System.out.print ("East ");
            } 
            if (this.aCurrentRoom.aSouthExit != null)
            {
            System.out.print ("South ");
            } 
            if (this.aCurrentRoom.aWestExit != null)
            {
            System.out.print ("West ");
            } 
             */
        }    
    } //procedure goRoom()  

    // ## debut tp3.2##

    /**
     * The welcome text when you start a new game 
     * 
     * @param Nothing No parameters required
     * @return Nothing
     */
    private void printWelcome ()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("In a magical forest : you, a farfadet, are called urgently at the foot of the rainbow because gold coin from the cauldron have been stolen !");
        System.out.println ("Then, you have to cross the forest to try to find the stolen gold coin");

        System.out.println("Type 'help' if you need help.");
        System.out.println('\n');

        printLocationInfo();
        /* System.out.println("You are " + this.aCurrentRoom.getDescription());
        System.out.print("Exits: ");
        if (this.aCurrentRoom.aNorthExit != null)
        {
        System.out.print ("North ");
        }    
        if (this.aCurrentRoom.aEastExit != null)
        {
        System.out.print ("East ");
        } 
        if (this.aCurrentRoom.aSouthExit != null)
        {
        System.out.print ("South ");
        } 
        if (this.aCurrentRoom.aWestExit != null)
        {
        System.out.print ("West ");
        } 
         */ 
    } //printWelcome()

    /**
     * The help text when you write "help"
     * 
     * @param Nothing No parameters required
     * @return Nothing
     */
    private void printHelp ()
    {
        System.out.println("You are lost.");
        System.out.println("You can refer to the map");
        System.out.println('\n');
        System.out.println("Your command words are:");
        aParser.showCommands();
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
     * 
     * @param Nothing No parameters required
     * @return Nothing
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
     * 
     * @param Nothing No parameters required
     * @return Nothing
     */
    private void printLocationInfo ()
    {
        System.out.println (this.aCurrentRoom.getLongDescription());
        //System.out.println("You are" + aCurrentRoom.getDescription());
        // System.out.print("Exits : ");
        /*if (aCurrentRoom.aNorthExit != null) 
        { System.out.print("North");}
        if (aCurrentRoom.aEastExit != null) 
        { System.out.print("East");}
        if (aCurrentRoom.aSouthExit != null) 
        { System.out.print("South");}
        if (aCurrentRoom.aWestExit != null) 
        { System.out.print("West");} */
        // System.out.println(aCurrentRoom.getExitString());
        System.out.println();
    } //printLocationInfo()   

    private void look () 
    {
        System.out.println (aCurrentRoom.getLongDescription());
    } //look()
    
    private void eat ()
    {
        System.out.println ("You have eaten now and you are not hungry any more.");
    } //eat()
} // Game
