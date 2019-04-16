import java.util.HashMap;
import java.util.*;

/**
 * Décrivez votre classe ItemList ici.
 *
 * @author Manon
 * @version v1
 */
public class ItemList
{
    public HashMap <String, Item> aInventoryHM;

    public ItemList () {
        this.aInventoryHM = new HashMap <String, Item> ();
    }

    public Item getItem (final String pItem) {
        return this.aInventoryHM.get(pItem);
    } //getPlayerItem()

    public String getItemsString(){
        String vList = "";
        Set <String> keys = this.aInventoryHM.keySet();
        for(String vItem : keys){
            vList += vItem+" ";
        }
        return vList;
    } //getItemString()

    public void setItem(final String pName, final Item pItem){
        this.aInventoryHM.put(pName, pItem);
    }

    public void removeItem(final String pName){
        this.aInventoryHM.remove(pName);
    }

    public int getTotalWeight(){
        int vWeight = 0;
        Set <String> keys = this.aInventoryHM.keySet();
        for(String item : keys){
            vWeight = vWeight+aInventoryHM.get(item).getWeight();
        }
        return vWeight;
    }

    /**
     * Permet de savoir si un item existe
     * @param pName Nom auquel est associé l'item
     * @return boolean true si l'item est dans la liste
     */
    public boolean containsKey (final String pName){
        return this.aInventoryHM.containsKey (pName);
    }

    /**
     * getItemList() : Permet de recuperer la liste complete
     * @return aItems : Liste complète
     */
    public HashMap<String, Item> getItemList(){
        return this.aInventoryHM;
    }//getItemList
}
