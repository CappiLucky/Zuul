
/**
 * Project Zuul
 * Class for create differents items
 * 
 * @author Manon HERMANN
 * @version v1
 */
public class Item
{
    private String aDescription;
    private int aWeight;

    /**
     * Constructeur d'objets de classe Items
     * @param pName nom de l'objet
     * @param pDescr description de l'objet
     * @param pWeight poids de l'objet
     */
    public Item (final String pDescr, final int pWeight)
    {
        this.aDescription = pDescr;
        this.aWeight = pWeight;
    }// construteur par defaut (...)

    /**
     * Return objet'description
     * @return description de l'objet
     */
    public String getDescription()
    {
        return this.aDescription;
    }

    /**
     * Return objet'weight
     * @return poids de l'objet
     */
    public int getWeight()
    {
        return this.aWeight;

    }

    /**
     * Return objet'description and objet'weight
     * @return description & poids
     */
    public String getItemInformation()
    {
        return this.aDescription + " whose have the weight of " + this.aWeight;

    }
}
