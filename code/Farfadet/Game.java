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
       this.aEngine = new GameEngine();
       this.aGui = new UserInterface(aEngine);
       this.aEngine.setGUI(aGui);
    } //contructeur par defaut : Game()  
} // Game
