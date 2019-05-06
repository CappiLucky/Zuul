import java.util.*;

/**
 * Project Zuul
 * Classe for Inventory of player'items
 *
 * @author Manon
 * @version v1
 */
public class ItemList
{
    public HashMap <String, Item> aInventoryHM;

    /**
     * Constructeur par defaut de ItemList
     */
    public ItemList () {
        this.aInventoryHM = new HashMap <String, Item> ();
    } //constructeur par defaut ()

    // ## Accesseurs ##
    /**
     * Accesseur pour avoir l'item
     * 
     * @return Item item quon voulait avoir
     * @param pItem item en question
     */
    public Item getItem (final String pItem) {
        return this.aInventoryHM.get(pItem);
    } //getItem(.)
    
    /**
     * getItemList() : Permet de reccuperer la liste complete
     * @return aItems : Liste complète
     */
    public HashMap<String, Item> getItemList(){
        return this.aInventoryHM;
    }//getItemList
    
    /**
     * Accesseur pour avoir la liste des items dans l'inventaire
     * 
     * @return String liste des items dans la HashMap
     */
    public String getItemsString(){
        if (aInventoryHM.isEmpty()) {
            return "inventory is empty !";
        }
        StringBuilder vList = new StringBuilder("inventory : ");
        Set <String> keys = this.aInventoryHM.keySet();
        for(String vItem : keys){
            vList.append(" " + vItem);
        }
        return vList.toString();
    } //getItemString()

    /**
     * Accesseur set de l'item
     * 
     * @param pName nom de l'item
     * @param pItem type de l'item
     */
    public void setItem (final String pName, final Item pItem){
        this.aInventoryHM.put(pName, pItem);
    } //getItem(..)

    /**
     * Accesseur de la taille total de l'inventaire du joueur
     * 
     * @return int qui correspond au poids actuel de l'inventaire
     */
    public int getTotalWeight(){
        int vWeight = 0;
        Set <String> keys = this.aInventoryHM.keySet();
        for(String item : keys){
            vWeight = vWeight + aInventoryHM.get(item).getWeight();
        }
        return vWeight;
    } //getTotalWeight()

    // ## Methodes ##
    public void removeItem (final String pName, final Item pItem){
        this.aInventoryHM.remove(pName, pItem);
    } //removeItem(.)

    public void addItem (final String pName, final Item pItem) {
        this.aInventoryHM.put(pName, pItem);
    } //addItem(..)
    
    /**
     * Permet de savoir si un item existe
     * @param pName Nom auquel est associé l'item
     * @return boolean true si l'item est dans la liste
     */
    public boolean containsKey (final String pName){
        return this.aInventoryHM.containsKey (pName);
    } //containsKey(.)
    
    public String inventory () {
        return this.getItemsString() + "\n" + "and total weight : " + this.getTotalWeight() + "\n";
    }//inventory()
} // ItemList
