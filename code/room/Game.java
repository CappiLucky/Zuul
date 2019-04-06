/* doit encore :
* - bouton OK --> faire d'autres
*/

/**
 * Project Zuul
 * Class for initialise new game
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class Game 
{
    private UserInterface aGui;
    private GameEngine aEngine;

    /**
     * Begin a new game
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //createRooms();
        //this.aParser = new Parser();
       aEngine = new GameEngine();
       aGui = new UserInterface(aEngine);
       aEngine.setGUI(aGui);
    } //contructeur par defaut : Game()  
} // Game
