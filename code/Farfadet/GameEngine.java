import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;
import java.io.InputStream;

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
    private Player aPlayer;
    //stoker une collection de joueur

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        aParser = new Parser();
        aRoomsHM = new HashMap <Room, String> (); 
        
        createRooms();
    }

    /**
     * Create interface of the user
     * 
     * @param pUserInterface interface of the user
     */
    public void setGUI(UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        this.aPlayer.setGui(aGui); //lui dire qu'une Interface du joueur existe
        aPlayer.printWelcome();
    }

    //printwelcome dans player

    /**
     * Create Room of the game with his descriptions and exits 
     */
    private void createRooms ()
    {
        //declaration des Items 
        Item vIPiece1 = new Item ("gold coin", "gold coin number 1", 1);
        Item vIPiece2 = new Item ("gold coin", "gold coin number 2", 1);
        Item vIPiece3 = new Item ("gold coin", "gold coin number 3", 1);
        Item vIPiece4 = new Item ("gold coin", "gold coin number 4", 1);
        Item vIPiece5 = new Item ("gold coin", "gold coin number 5", 1);
        Item vIEau = new Item ("cup of water", "cup of water which going to the big water cascade", 1);
        Item vILivre = new Item ("magik book", "magik and ancient book with many secrets", 1);
        Item vIChampi = new Item ("mushrooms", "good mushrooms for the great clearing", 1);
        Item vIClef = new Item ("key", "claudron'key", 1);
        Item vIChaudron = new Item ("claudron", "contains the gold coin", 20);
        Item vIFleur = new Item ("flower", "flower, is nothing else", 1);
        Item vMagikCookie = new Item ("magik cookie", "upgrade your storage", 0);

        //declaration des variables Room
        Room vChaudron = new Room (" at the foot of the rainbow", "images/chaudron_dor2.jpg"); //endroit ou il faut ramener les 5pieces
        Room vClairiere = new Room (" in a great clearing", "images/clairiere2.jpg");
        Room vFee = new Room (" face to face with a little woods fairy", "images/fee2.jpg"); //donne une piece d'or 2
        Room vCascade = new Room (" in front of a big water cascade", "images/cascade_deau2.jpg");
        Room vElfe = new Room (" face to face with a elven", "images/elfe2.jpg"); //donne une piece d'or 3
        Room vPiece = new Room (" facing a gold coin on the floor", "images/foret_piece_dor2.jpg");
        Room vLutin = new Room (" face to face with a pixie", "images/lutin2.jpg"); //donne une piece d'or 4
        Room vArbre = new Room (" face to face with a living tree", "images/perso_arbre2.jpg"); //donne une piece d'or 5
        Room vLivre = new Room (" facing a magic book", "images/objet_livre2.jpg");
        Room vSorciere = new Room (" in a secret room, face to face with a witch", "images/sorciere2.jpg"); //donne la clé

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

        //ajout des item dans les rooms
        vChaudron.addItem ("claudron", vIChaudron);
        vClairiere.addItem ("mushrooms", vIChampi);
        vCascade.addItem ("water", vIEau);
        vPiece.addItem ("gold coin number 1", vIPiece1);
        vLivre.addItem ("magik book", vILivre);
        vLivre.addItem ("flower", vIFleur);

        //initialiser le lieu de depart
        //this.aCurrentRoom = vChaudron;
        this.aPlayer = new Player ("Farfadet", vChaudron);

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
     * 
     * @param pCommandLine text which was wirten by the user
     */
    public void interpretCommand(String pCommandLine) 
    {
        aGui.println ("*****************************************");
        aGui.println("Your command is : " + pCommandLine);
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
            aPlayer.look();
        else if (commandWord.equals("eat"))
            aPlayer.eat();
        else if (commandWord.equals("back"))
            aPlayer.back();
        else if (commandWord.equals("test")) {
            if (command.hasSecondWord())
                test(command.getSecondWord());
        }
        else if (commandWord.equals("take"))
            if (!command.hasSecondWord()) 
                this.aGui.println ("Take what ? \n");
            else 
                aPlayer.takeItem(command);
        else if (commandWord.equals("drop"))
            aPlayer.dropItem(command);  
        else if (commandWord.equals("inventory"))
            this.aGui.println (this.aPlayer.getInventory().inventory());
    }
    
    /**
     * The help text when you write "help"
     */
    private void printHelp ()
    {
        aGui.println("You are lost.");
        aGui.println("You can refer to the map");
        aGui.println("Your maximal weight is " + this.aPlayer.getPoidMax());
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
        Room vNextRoom = aPlayer.getCurrentRoom().getExit(vDirection);

        if (vNextRoom == null)
        {
            aGui.println ("There is no door !");
        } else {
            aPlayer.getStackRoom().push (aPlayer.getCurrentRoom());
            aPlayer.changeRoom(vNextRoom);
        }    
    } //procedure goRoom() 

    /**
     * Ending game
     */
    private void endGame()
    {
        aGui.println("Thank you for playing.  Good bye.");
        aGui.enable(false);
    } 

    //autres fonctions
    //eat et look deplacer dans player

    
    //printlocationinfo dans player

    //non obligatoire (?)
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

    private void test (final String pNom) {
        Scanner vS;
        try {
            String vNom =pNom;
            if (! vNom.endsWith(".txt")) vNom += ".txt";
            InputStream vIPS = getClass().getResourceAsStream(vNom);
            vS = new Scanner (vIPS);
            while ( vS.hasNextLine() ) {
                String vLine = vS.nextLine();
                this.interpretCommand(vLine);
            }
        }
        catch (final Exception pErr) {
            this.aGui.println ("Erreur : " + pErr.getMessage() );
        }
    }
}
