/* doit encore :
* - bouton OK --> faire d'autres
* - creer un fichier de commande pour le parcours ideal pour gagner (7.28.2)
* - Player()
* 
* J'en suis a faire le 7.29
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
       aEngine = new GameEngine();
       aGui = new UserInterface(aEngine);
       aEngine.setGUI(aGui);
    } //contructeur par defaut : Game()  
} // Game
