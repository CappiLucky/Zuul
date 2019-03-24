/* doit encore :
* - faire une HashMap pour relier les rooms et les images
* - faire un bouton
* - deplacer les images dans un repertoire
* - creer les items disponible dans le jeu
* - ajouter la description des differents items
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
    //private Room aCurrentRoom;
    //private Parser aParser;
    private UserInterface gui;
    private GameEngine engine;
 
    /**
     * Begin a new game
     * Create the game and initialise its internal map.
     */
    public Game() //a laisser
    {
        //createRooms();
        //this.aParser = new Parser();
       engine = new GameEngine();
       gui = new UserInterface(engine);
       engine.setGUI(gui);
    } //contructeur par defaut : Game()  

} // Game
