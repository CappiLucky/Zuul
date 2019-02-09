
public class Game 
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    private void createRooms ()
    {
        //declaration des variables Room
        Room vChaudron = new Room ("at the foot of the rainbow");
        Room vClairiere = new Room ("in a great clearing");
        Room vFee = new Room ("face to face with a little fairy of the woods");
        Room vCascade = new Room ("in front of a big water cascade");
        Room vElfe = new Room ("face to face with a elven");
        Room vPiece = new Room ("facing a gold coin on the floor");
        Room vLutin = new Room ("face to face with a pixie" );
        Room vArbre = new Room ("face to face with a living tree");
        Room vLivre = new Room ("facing a magic book");
        Room vSorciere = new Room ("in a secret room, face to face with a witch");
        
        //positionner les sorties
        vChaudron.setExits( null, vSorciere, null, vClairiere);
        vClairiere.setExits( vFee, vChaudron, vArbre, vPiece);
        vFee.setExits( vCascade, null, vClairiere, null);
        vCascade.setExits( null, null, vFee, vElfe);
        vElfe.setExits( vCascade, null, vPiece, null);
        vPiece.setExits( vElfe, vClairiere, null, vLutin);
        vLutin.setExits( null, vPiece, null, null);
        vArbre.setExits( vClairiere, vLivre, null, null);
        vLivre.setExits( null, null, null, vArbre);
        vSorciere.setExits( null, null, null, vChaudron);
         
        //initialiser le lieu de depart
        this.aCurrentRoom = vChaudron;
    } //createRooms()   
    
    public Game() 
    {
        createRooms();
        this.aParser = new Parser();
    } //contructeur par defaut : Game()  
    
    private void goRoom (final Command pCommand)
    {
        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = null;
        
        if (vDirection == null)
        {
            System.out.println("Go where ?");
            return;
        } else {   
            if (vDirection.equals("North"))
            {
                vNextRoom = this.aCurrentRoom.aNorthExit;
                //return this.aNorthExit;
            } 
            else if (vDirection.equals("East"))
            {
                vNextRoom = this.aCurrentRoom.aEastExit;
            }
            else if (vDirection.equals("South"))
            {
                vNextRoom = this.aCurrentRoom.aSouthExit;
            }
            else if (vDirection.equals("West"))
            {
                vNextRoom = this.aCurrentRoom.aWestExit;
            } else {
                System.out.println ("Unknown direction !");
                return;
            }    
        } 
        
        if (vNextRoom == null)
        {
            System.out.println ("There is no door !");
        } else {
            this.aCurrentRoom = vNextRoom;
            System.out.println("You are "+vNextRoom.getDescription());
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
        }    
    } //procedure goRoom()  
    
    // ## debut tp3.2##
    
    private void printWelcome ()
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("Dans une foret magique : vous, un farfadet, etes appelé d'urgence au pied d'un arc en ciel car des pieces d'or du chaudron ont été volées !");
        System.out.println ("Vous devez alors traverser la foret pour tenter de retrouver les 5 pièces d'or perdu.");
        
        System.out.println("Type 'help' if you need help.");
        System.out.println('\n');
        System.out.println("You are " + this.aCurrentRoom.getDescription());
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
            
    } //printWelcome()
    
    private void printHelp ()
    {
        System.out.println("You are lost.You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println('\n');
        System.out.println("Your command words are:");
        System.out.println("  go quit help");
    } //printHelp()
    
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
       else return false;
    } //processCommand(.)
    
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
} // Game
