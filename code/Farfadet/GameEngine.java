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
    private int aTimer = 30; 

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aRoomsHM = new HashMap <Room, String> (); 
        this.createRooms();
    }

    // ## Accesseurs ##
    /**
     * Create interface of the user
     * 
     * @param pUserInterface interface of the user
     */
    public void setGUI(UserInterface pUserInterface)
    {
        this.aGui = pUserInterface;
        this.aPlayer.setGui(aGui); //dire qu'une Interface du joueur existe
        this.aPlayer.printWelcome();
    }

    // ## Methodes ##
    /**
     * Create Room of the game with his descriptions and exits 
     */
    private void createRooms ()
    {
        //declaration des Items 
        Item vIPiece1 = new Item ("goldCoin1", "gold coin number 1", 1);
        Item vIPiece2 = new Item ("goldCoin2", "gold coin number 2", 1);
        Item vIPiece3 = new Item ("goldCoin2", "gold coin number 3", 1);
        Item vIPiece4 = new Item ("goldCoin2", "gold coin number 4", 1);
        Item vIPiece5 = new Item ("goldCoin2", "gold coin number 5", 1);
        Item vIEau = new Item ("water", "cup of water which going to the big water cascade", 1);
        Item vILivre = new Item ("magicBook", "magic and ancient book with many secrets", 1);
        Item vIChampi = new Item ("mushrooms", "good mushrooms for the great clearing", 1);
        Item vIClef = new Item ("key", "claudron'key", 1);
        Item vIChaudron = new Item ("claudron", "contains the gold coin", 20);
        Item vIFleur = new Item ("flower", "flower, is nothing else", 1);
        Item vMagikCookie = new Item ("magicCookie", "upgrade your storage", 0);

        //declaration des Room
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
        vFee.setExits ("South", vClairiere); //--> trap door
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

        //declaration des TrapDoor et LockDoor
        Door vSecretDoor = new Door (false, true, true);
        Door vFCdoor = new Door (true, false, false);
        Door vCFdoor = new Door (true, false, true);
        //lien entre les salles et les Doors
        vChaudron.setDoor ("East", vSecretDoor);
        vFee.setDoor ("North", vFCdoor);
        vCascade.setDoor ("South", vCFdoor);

        //ajout des item dans les rooms
        vChaudron.addItem ("claudron", vIChaudron);
        vClairiere.addItem ("mushrooms", vIChampi);
        vCascade.addItem ("water", vIEau);
        vPiece.addItem ("goldCoin1", vIPiece1);
        vLivre.addItem ("magicBook", vILivre);
        vLivre.addItem ("flower", vIFleur);
        vLivre.addItem ("magicCookie", vMagikCookie);
        
        //declaration des persos
        Character vPFee = new Character ("Fee", vFee, vIPiece2);
        Character vPElfe = new Character ("Elfe", vElfe, vIPiece3);
        Character vPLutin = new Character ("Lutin", vLutin, vIPiece4);
        Character vPArbre = new Character ("Arbre", vArbre, vIPiece5);
        Character vPSorciere = new Character ("Vezonia", vSorciere, vIClef);
        //ajout des persos dans les rooms
        vFee.setPNG ("Fee", vPFee);
        vElfe.setPNG ("Elfe", vPElfe);
        vLutin.setPNG ("Lutin", vPLutin);
        vArbre.setPNG ("Arbre", vPArbre);
        vSorciere.setPNG ("Vezonia", vPSorciere);

        //initialiser le lieu de depart
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
        this.aGui.println ("*****************************************");
        this.aGui.println("Your command is : " + pCommandLine);
        Command command = this.aParser.getCommand(pCommandLine);
        if(command.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            this.printHelp();
        else if (commandWord.equals("go"))
            this.goRoom(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                this.aGui.println("Quit what?");
            else
                this.endGame();
        } 
        else if (commandWord.equals("look"))
            this.aPlayer.look();
        else if (commandWord.equals("eat"))
            if(!command.hasSecondWord())
                this.aGui.println("Eat what?");
            else this.aPlayer.eat(command.getSecondWord()); 
        else if (commandWord.equals("back"))
            this.aPlayer.back();
        else if (commandWord.equals("test")) {
            if (command.hasSecondWord())
                this.test(command.getSecondWord());
        }
        else if (commandWord.equals("take"))
            if (!command.hasSecondWord()) 
                this.aGui.println ("Take what ? \n");
            else 
                this.aPlayer.takeItem(command);
        else if (commandWord.equals("drop"))
            this.aPlayer.dropItem(command);  
        else if (commandWord.equals("inventory"))
            this.aGui.println (this.aPlayer.getInventory().inventory());
        else if (commandWord.equals("talk")) {
            if (!command.hasSecondWord()) 
                this.aGui.println ("Talk with who ? \n");
            else 
                this.aPlayer.talk(command);
        }
    }

    /**
     * The help text when you write "help"
     */
    private void printHelp ()
    {
        this.aGui.println("You are lost.");
        this.aGui.println("You can refer to the map");
        this.aGui.println("Your maximal weight is " + this.aPlayer.getPoidMax());
        this.aGui.println("\n");
        this.aGui.println("Your command words are:");
        this.aGui.println(this.aParser.showCommands());
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
            this.aGui.println("Go where?");
            return;
        }
        String vDirection = pCommand.getSecondWord();
        //try to leave current room
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
        if (vNextRoom == null)
        {
            this.aGui.println ("There is no door !");
            return;
        }
        this.aPlayer.getStackRoom().push (this.aPlayer.getCurrentRoom());
        
        //cas du trapDoor et lockDoor
        Door vNextDoor = this.aPlayer.getCurrentRoom().getDoor(vDirection);
        if (vNextDoor != null) {
            if (this.aPlayer.getCurrentRoom().getDoor(vDirection).isTrapDoor()) {
                if (! vNextDoor.canGo()) {
                    this.aGui.println ("this door is a trap. You can't go in this direction");
                    return;
                } 
                this.aPlayer.clearStack();
            } else if (this.aPlayer.getCurrentRoom().getDoor(vDirection).isLockDoor()) {
                HashMap vInvtHM = this.aPlayer.getInventory().aInventoryHM;
                if (vInvtHM.containsKey("goldCoin1") && vInvtHM.containsKey("goldCoin2") && vInvtHM.containsKey("goldCoin3") && vInvtHM.containsKey("goldCoin4") && vInvtHM.containsKey("goldCoin5")) {
                    vNextDoor.setLock(false);
                    this.aGui.println ("Amazing ! You found a secret room...");
                } else {
                    this.aGui.println ("You can't open the door ! You have to take the 5 gold coin");
                    return;
                }
            }
        }

        this.aPlayer.changeRoom(vNextRoom);
        if (aTimer == 0) {
            this.aGui.println("!! time is over !!");
            this.endGame();
        }
        else {
            this.aTimer -= 1;
            this.aGui.println("You have " + this.aTimer + "moves even");
        }
    } //procedure goRoom() 

    /**
     * Ending game
     */
    private void endGame()
    {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    } //endGame()

    //autres fonctions
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
            this.aGui.println("Quit what ?");
            return vSecondWord =false;
        } else 
        {return vSecondWord;
        }
    } //quit() 

    /**
     * @param pNom name of the text
     */
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
    } //test(.)
} //GameEngine
