/**
 * Project Zuul
 * Class for create differents items
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class Item
{
    private String aName;
    private String aDescription;
    private int aWeight;

    /**
     * Constructeur d'objets de classe Items
     * 
     * @param pName nom de l'objet
     * @param pDescr description de l'objet
     * @param pWeight poids de l'objet
     */
    public Item (final String pName, final String pDescr, final int pWeight)
    {
        this.aName = pName;
        this.aDescription = pDescr;
        this.aWeight = pWeight;
    }// construteur par defaut (...)

    // ## Accesseurs ##
    /**
     * accesseur pour le nom de l'objet
     * 
     * @return name of the object
     */
    public String getName()
    {
        return this.aName;
    } //getName()

    /**
     * Return objet'description
     * 
     * @return description de l'objet
     */
    public String getDescription()
    {
        return this.aDescription;
    } //getDescription()

    /**
     * Return objet'weight
     * 
     * @return poids de l'objet
     */
    public int getWeight()
    {
        return this.aWeight;
    } //getWeight()

    /**
     * Return objet'description and objet'weight
     * 
     * @return description and poids 
     */
    public String getItemInformation()
    {
        return this.getName() + " : " + this.aDescription + " whose have the weight of " + this.aWeight;
    } //getItemInformation()
} //Item
