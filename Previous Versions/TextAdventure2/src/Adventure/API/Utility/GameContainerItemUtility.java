package Adventure.API.Utility;

import Adventure.API.*;

import java.util.*;

/**
 * This utility object is utilized by GameContainers for the funcionality of managing the items contained within them.
 */
public interface GameContainerItemUtility
    extends GameUtility
{
    /**
     * This method is used to remove a single GameItem object from its current container and to add
     * it to the quantity being maintained by this container.
     *
     * @param newItem The GameItem object to add a single quantity of to this container.
     * @param currentContainer The GameContainer object that the given GameItem should be removed from.
     * @return True if successful, false if not.
     */
    public abstract boolean addItem( GameItem newItem, GameContainer currentContainer );

    /**
     * This method is used to remove a given quantity of a GameItem object from its current container and to add
     * it to the quantity being maintained by this container.
     *
     * @param newItem The GameItem object to add the quantity to for this container.
     * @param quantity The quantity of the GameItem to be added to this container.
     * @param currentContainer The GameContainer object that the given GameItem should be removed from.
     * @return True if successful, false if not.
     */
    public abstract boolean addItem( GameItem newItem, int quantity, GameContainer currentContainer );

    /**
     * This method is used to remove a single GameItem object with the given name from the GameContainer object
     * with the given name and to add it to the quantity being maintained by this container.
     *
     * @param itemName The string name of the GameItem object to add a single quantity of to this container.
     * @param currentContainerName The string name of the GameContainer object that the item should be removed from.
     * @return True if successful, false if not.
     */
    public abstract boolean addItem( String itemName, String currentContainerName );

    /**
     * This method is used to remove a given quantity of the GameItem object with the given name from the GameContainer
     * object with the given name and to add it to the quantity being maintained by this container.
     *
     * @param itemName The string name of the GameItem object to add to this container.
     * @param quantity The quantity of the item to be added to this container.
     * @param currentContainerName The string name of the GameContainer object that the item should be removed from.
     * @return True if successful, false if not.
     */
    public abstract boolean addItem( String itemName, int quantity, String currentContainerName );

    /**
     * This method is used to calculate the weight of the items that this container is currently holding.
     *
     * @return The value of the combined weight of all of the items within this container.
     */
    public abstract double containedItemWeight();

    /**
     * This method is used to decrease the quantity of a given GameItem being maintained by this container
     * by the given amount.
     *
     * @param item The GameItem object to decrease the quantity of.
     * @param decreaseByAmount The amount to decrease the quantity within this container by.
     * @return True if there were enough GameItems and they were successfully removed, false otherwise.
     */
    public abstract boolean decreaseItemQuantity( GameItem item, int decreaseByAmount );

    /**
     * This method is used to decrease the quantity of the GameItem with the given name being maintained by this
     * container by the given amount.
     *
     * @param itemName The name of the GameItem object to decrease the quantity of.
     * @param decreaseByAmount The amount to decrease the quantity within this container by.
     * @return True if there were enough items and they were successfully removed, false otherwise.
     */
    public abstract boolean decreaseItemQuantity( String itemName, int decreaseByAmount );

    /**
     * This method is used to get a reference to the GameItem object with the given name from this container.
     *
     * @param itemName The name of the GameItem object to be retrieved.
     * @return The GameItem object with the given name if found, null if it is not.
     */
    public abstract GameItem getItem( String itemName );

    /**
     * This method is used to retrieve the quantity of a given gameItem object that are held in this container.
     *
     * @param item The GameItem object to retrieve the quantity for.
     * @return The quantity of the given gameItem that exist within this container.
     */
    public abstract int getItemQuantity( GameItem item );

    /**
     * This method is used to retrieve the quantity of the item with the given name that is being held within
     * this container.
     *
     * @param itemName The string name of the item to retrieve the quantity for.
     * @return The quantity of the item that is held within this container.
     */
    public abstract int getItemQuantity( String itemName );

    /**
     * This method is used to retrieve the maximum combined weight for the items that it can contain.
     *
     * @return The maximum combined weight of the items this container can hold.
     */
    public abstract double getMaxWeight();

    /**
     * This method is used to determine if the given gameItem is being held within this container.
     * GameHideable items marked hidden are not included in the check.
     *
     * @param item The GameItem object to be checked for.
     * @return True if there are 1 or more of the given item in this container, false otherwise.
     */
    public abstract boolean hasItem( GameItem item );

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being
     * held within this container. GameHideable items marked hidden are not included in the check.
     *
     * @param item The GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @return True if there are the given quantity or more of the item in this container, false otherwise.
     */
    public abstract boolean hasItem( GameItem item, int quantity );

    /**
     * This method is used to determine if there is 1 or more of the given GameItem object being held within this
     * container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param item The GameItem object to be checked for.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found, false otherwise.
     */
    public abstract boolean hasItem( GameItem item, boolean includeHidden );

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being held
     *  within this container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param item The GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found with the given quantity or greater, false otherwise.
     */
    public abstract boolean hasItem( GameItem item, int quantity, boolean includeHidden );

    /**
     * This method is used to determine if the item with the given name is being held within this container.
     * GameHideable items marked hidden are not included in the check.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @return True if there are 1 or more of the given item in this container, false otherwise.
     */
    public abstract boolean hasItem( String itemName );

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being
     * held within this container. GameHideable items marked hidden are not included in the check.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @return True if there are the given quantity or more of the item in this container, false otherwise.
     */
    public abstract boolean hasItem( String itemName, int quantity );

    /**
     * This method is used to determine if there is 1 or more of the GameItem object with the given name being held
     * within this container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found, false otherwise.
     */
    public abstract boolean hasItem( String itemName, boolean includeHidden );

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being held
     * within this container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found, false otherwise.
     */
    public abstract boolean hasItem( String itemName, int quantity, boolean includeHidden );

    /**
     * This method is used to increase the quantity of a given GameItem being maintained by this container
     * by the given amount.
     *
     * @param item The GameItem object to increase the quantity of.
     * @param increaseByAmount The amount to increase the quantity within this container by.
     * @return True if the item quantity was successfully increased, false otherwise.
     */
    public abstract boolean increaseItemQuantity( GameItem item, int increaseByAmount );

    /**
     * This method is used to increase the quantity of the GameItem with a given name being maintained by this container
     * by the given amount.
     *
     * @param itemName The name of the item to increase the quantity of.
     * @param increaseByAmount The amount to increase the quantity within this container by.
     * @return True if the item quantity was successfully increased, false otherwise.
     */
    public abstract boolean increaseItemQuantity( String itemName, int increaseByAmount );

    /**
     * This method is used to get a copy of the list of items that are being held within this container. It doesn't
     * include any information about the quantities of each item, just the references to the items themself.
     *
     * @return A list of the gameItems that are being held within this container.
     */
    public abstract ArrayList<GameItem> itemList();

    /**
     * This method is used to get a string of text to list the contents of this container that will be added to
     * the game output. This text string doesn't include GameHideable objects marked as hidden.
     *
     * @return A string listing of the items held within this container.
     */
    public abstract String listItems();

    /**
     * This method is used to remove a quantity of 1 of the given GameItem from the list of items being held in this container.
     *
     * @param item The GameItem to be removed from this container.
     * @return True if the item is successfully removed, false if it is not.
     */
    public abstract boolean removeSingleItem( GameItem item );

    /**
     * This method is used to remove a quantity of 1 of the GameItem object with the given name from the list of items
     * being held in this container.
     *
     * @param item The GameItem to be removed from this container.
     * @param quantityToRemove The quantity to be removed from the container.
     * @return True if the item quantity is successfully removed, false if it is not.
     */
    public abstract boolean removeItemQuantity( GameItem item, int quantityToRemove );

    /**
     * This method is used to remove a quantity of 1 of the GameItem object with the given name from the list of items
     * being held in this container.
     *
     * @param itemName The GameItem to be removed from this container.
     * @return True if the item is successfully removed, false if it is not.
     */
    public abstract boolean removeSingleItem( String itemName );

    /**
     * This method is used to remove a quantity of 1 of the GameItem object with the given name from the list of items
     * being held in this container.
     *
     * @param itemName The GameItem to be removed from this container.
     * @param quantityToRemove The quantity to be removed from the container.
     * @return True if the item quantity is successfully removed, false if it is not.
     */
    public abstract boolean removeItemQuantity( String itemName, int quantityToRemove );

    /** 
     * This method is used to attach this utility object to the given GameContainer object.
     * 
     * @param container The GameContainer object to attach this utility to.
     */
    public abstract void setContainerComponent( GameContainer container );

    /**
     * This method is used to attach this utility object to the component with the given name.
     * 
     * @param containerName The name of the GameContainer object to attach this utility to.
     */
    public abstract void setContainerComponent( String containerName );

     /**
      * This method is used to assign a new maximum combined item weight to this container.
      * 
      * @param newMaxWeight The new maximum combined item weight.
      */
    public abstract void setMaxWeight( double newMaxWeight );
}
