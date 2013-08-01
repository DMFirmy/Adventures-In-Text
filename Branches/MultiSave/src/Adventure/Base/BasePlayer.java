package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

import java.util.*;

/**
 * This is the base implementation of the GamePlayer interface.
 */
public abstract class BasePlayer
    extends BasePawn
    implements GamePlayer
{
    @SuppressWarnings( "compatibility:-5255143076366869451" )
    private static final long serialVersionUID = 1L;

    /**
     * This field stores a the GameContainerItemUtility that is utilized by this actor.
     */
    protected GameContainerItemUtility itemUtility;

    /**
     * This constructor is used to initialize and set up this player.
     *
     * @param name The string name of this action.
     * @param description The text that will be shown as the description for this player.
     */
    public BasePlayer( String name, String description )
    {
        super( name, description );

        this.setStatus( "moves", 0 );
        this.itemUtility = new ContainerItemUtility( this, 12.0 );
        this.hideUtility = new ObjectHideUtility( this );
    }

    /**
     * This method is used to set the current location of this pawn to the given GameLocation object. It includes'
     * an option to also add the description for the given location to the output.
     *
     * @param newLocation The new GameLocation object to set this player in.
     * @param describeNewLocation Whether or not to add a description of the new location to the output.
     */
    public void setLocation( GameLocation newLocation, boolean describeNewLocation )
    {
        this.setLocation( newLocation );
        newLocation.addPawn( this );

        if ( describeNewLocation )
        {
            this.location.describe();
        }
    }

    ///////////////////////Item Utility////////////////////////////

    /**
     * This method is used to remove a single GameItem object from its current container and to add
     * it to the quantity being maintained by this container.
     *
     * @param newItem The GameItem object to add a single quantity of to this container.
     * @param currentContainer The GameContainer object that the given GameItem should be removed from.
     * @return True if successful, false if not.
     */
    public boolean addItem( GameItem newItem, GameContainer currentContainer )
    {
        return this.itemUtility.addItem( newItem, currentContainer );
    }

    /**
     * This method is used to remove a given quantity of a GameItem object from its current container and to add
     * it to the quantity being maintained by this container.
     *
     * @param newItem The GameItem object to add the quantity to for this container.
     * @param quantity The quantity of the GameItem to be added to this container.
     * @param currentContainer The GameContainer object that the given GameItem should be removed from.
     * @return True if successful, false if not.
     */
    public boolean addItem( GameItem newItem, int quantity, GameContainer currentContainer )
    {
        return this.itemUtility.addItem( newItem, quantity, currentContainer );
    }

    /**
     * This method is used to remove a single GameItem object with the given name from the GameContainer object
     * with the given name and to add it to the quantity being maintained by this container.
     *
     * @param itemName The string name of the GameItem object to add a single quantity of to this container.
     * @param currentContainerName The string name of the GameContainer object that the item should be removed from.
     * @return True if successful, false if not.
     */
    public boolean addItem( String itemName, String currentContainerName )
    {
        return this.itemUtility.addItem( itemName, currentContainerName );
    }

    /**
     * This method is used to remove a given quantity of the GameItem object with the given name from the GameContainer
     * object with the given name and to add it to the quantity being maintained by this container.
     *
     * @param itemName The string name of the GameItem object to add to this container.
     * @param quantity The quantity of the item to be added to this container.
     * @param currentContainerName The string name of the GameContainer object that the item should be removed from.
     * @return True if successful, false if not.
     */
    public boolean addItem( String itemName, int quantity, String currentContainerName )
    {
        return this.itemUtility.addItem( itemName, quantity, currentContainerName );
    }

    /**
     * This method is used to calculate the weight of the items that this container is currently holding.
     *
     * @return The value of the combined weight of all of the items within this container.
     */
    public double containedItemWeight()
    {
        return this.itemUtility.containedItemWeight();
    }

    /**
     * This method is used to decrease the quantity of a given GameItem being maintained by this container
     * by the given amount.
     *
     * @param item The GameItem object to decrease the quantity of.
     * @param decreaseByAmount The amount to decrease the quantity within this container by.
     * @return True if there were enough GameItems and they were successfully removed, false otherwise.
     */
    public boolean decreaseItemQuantity( GameItem item, int decreaseByAmount )
    {
        return this.itemUtility.decreaseItemQuantity( item, decreaseByAmount );
    }

    /**
     * This method is used to decrease the quantity of the GameItem with the given name being maintained by this
     * container by the given amount.
     *
     * @param itemName The name of the GameItem object to decrease the quantity of.
     * @param decreaseByAmount The amount to decrease the quantity within this container by.
     * @return True if there were enough items and they were successfully removed, false otherwise.
     */
    public boolean decreaseItemQuantity( String itemName, int decreaseByAmount )
    {
        return this.itemUtility.decreaseItemQuantity( itemName, decreaseByAmount );
    }

    /**
     * This method is used to get a reference to the GameItem object with the given name from this container.
     *
     * @param itemName The name of the GameItem object to be retrieved.
     * @return The GameItem object with the given name if found, null if it is not.
     */
    public GameItem getItem( String itemName )
    {
        return this.itemUtility.getItem( itemName );
    }

    /**
     * This method is used to retrieve the quantity of a given gameItem object that are held in this container.
     *
     * @param item The GameItem object to retrieve the quantity for.
     * @return The quantity of the given gameItem that exist within this container.
     */
    public int getItemQuantity( GameItem item )
    {
        return this.itemUtility.getItemQuantity( item );
    }

    /**
     * This method is used to retrieve the quantity of the item with the given name that is being held within
     * this container.
     *
     * @param itemName The string name of the item to retrieve the quantity for.
     * @return The quantity of the item that is held within this container.
     */
    public int getItemQuantity( String itemName )
    {
        return this.itemUtility.getItemQuantity( itemName );
    }

    /**
     * This method is used to retrieve the maximum combined weight for the items that it can contain.
     *
     * @return The maximum combined weight of the items this container can hold.
     */
    public double getMaxWeight()
    {
        return this.itemUtility.getMaxWeight();
    }

    /**
     * This method is used to determine if the given gameItem is being held within this container.
     * GameHideable items marked hidden are not included in the check.
     *
     * @param item The GameItem object to be checked for.
     * @return True if there are 1 or more of the given item in this container, false otherwise.
     */
    public boolean hasItem( GameItem item )
    {
        return this.itemUtility.hasItem( item );
    }

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being
     * held within this container. GameHideable items marked hidden are not included in the check.
     *
     * @param item The GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @return True if there are the given quantity or more of the item in this container, false otherwise.
     */
    public boolean hasItem( GameItem item, int quantity )
    {
        return this.itemUtility.hasItem( item, quantity );
    }

    /**
     * This method is used to determine if there is 1 or more of the given GameItem object being held within this
     * container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param item The GameItem object to be checked for.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found, false otherwise.
     */
    public boolean hasItem( GameItem item, boolean includeHidden )
    {
        return this.itemUtility.hasItem( item, includeHidden );
    }

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being held
     *  within this container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param item The GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found with the given quantity or greater, false otherwise.
     */
    public boolean hasItem( GameItem item, int quantity, boolean includeHidden )
    {
        return this.itemUtility.hasItem( item, quantity, includeHidden );
    }

    /**
     * This method is used to determine if the item with the given name is being held within this container.
     * GameHideable items marked hidden are not included in the check.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @return True if there are 1 or more of the given item in this container, false otherwise.
     */
    public boolean hasItem( String itemName )
    {
        return this.itemUtility.hasItem( itemName );
    }

    /**
     * This method is used to determine if there is 1 or more of the GameItem object with the given name being held
     * within this container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found, false otherwise.
     */
    public boolean hasItem( String itemName, boolean includeHidden )
    {
        return this.itemUtility.hasItem( itemName, includeHidden );
    }

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being
     * held within this container. GameHideable items marked hidden are not included in the check.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @return True if there are the given quantity or more of the item in this container, false otherwise.
     */
    public boolean hasItem( String itemName, int quantity )
    {
        return this.itemUtility.hasItem( itemName, quantity );
    }

    /**
     * This method is used to determine if there is a given quantity or more of the given GameItem object being held
     * within this container. This method also allows for the inclusion of GameHideable items marked hidden.
     *
     * @param itemName The name of the GameItem object to be checked for.
     * @param quantity The quantity to be checked for of the given item.
     * @param includeHidden Whether or not to include GameHidable objects marked hidden or not.
     * @return True if the given item is found, false otherwise.
     */
    public boolean hasItem( String itemName, int quantity, boolean includeHidden )
    {
        return this.itemUtility.hasItem( itemName, quantity, includeHidden );
    }

    /**
     * This method is used to increase the quantity of a given GameItem being maintained by this container
     * by the given amount.
     *
     * @param item The GameItem object to increase the quantity of.
     * @param increaseByAmount The amount to increase the quantity within this container by.
     * @return True if the item quantity was successfully increased, false otherwise.
     */
    public boolean increaseItemQuantity( GameItem item, int increaseByAmount )
    {
        return this.itemUtility.increaseItemQuantity( item, increaseByAmount );
    }

    /**
     * This method is used to increase the quantity of the GameItem with a given name being maintained by this container
     * by the given amount.
     *
     * @param itemName The name of the item to increase the quantity of.
     * @param increaseByAmount The amount to increase the quantity within this container by.
     * @return True if the item quantity was successfully increased, false otherwise.
     */
    public boolean increaseItemQuantity( String itemName, int increaseByAmount )
    {
        return this.itemUtility.increaseItemQuantity( itemName, increaseByAmount );
    }

    /**
     * This method is used to get a copy of the list of items that are being held within this container. It doesn't
     * include any information about the quantities of each item, just the references to the items themself.
     *
     * @return A list of the gameItems that are being held within this container.
     */
    public ArrayList<GameItem> itemList()
    {
        return this.itemUtility.itemList();
    }

    /**
     * This method is used to get a string of text to list the contents of this container that will be added to
     * the game output. It doesn't utilize the utility object for this method because it needs to always include
     * hidden items in the player's inventory list.
     *
     * @return A string listing of the items held within this container.
     */
    public String listItems()
    {
        String list = this.itemUtility.listItems();
        list += String.format( ": %-30s: %-2s :", "Total Weight", this.containedItemWeight() );
        return list;
    }

    /**
     * This method is used to remove a quantity of 1 of the given GameItem from the list of items being held in this container.
     *
     * @param item The GameItem to be removed from this container.
     * @return True if the item is successfully removed, false if it is not.
     */
    public boolean removeSingleItem( GameItem item )
    {
        return this.itemUtility.removeSingleItem( item );
    }

    /**
     * This method is used to remove a quantity of 1 of the GameItem object with the given name from the list of items
     * being held in this container.
     *
     * @param item The GameItem to be removed from this container.
     * @param quantityToRemove The quantity to be removed from the container.
     * @return True if the item quantity is successfully removed, false if it is not.
     */
    public boolean removeItemQuantity( GameItem item, int quantityToRemove )
    {
        return this.itemUtility.removeItemQuantity( item, quantityToRemove );
    }

    /**
     * This method is used to remove a quantity of 1 of the GameItem object with the given name from the list of items
     * being held in this container.
     *
     * @param itemName The GameItem to be removed from this container.
     * @return True if the item is successfully removed, false if it is not.
     */
    public boolean removeSingleItem( String itemName )
    {
        return this.itemUtility.removeSingleItem( itemName );
    }

    /**
     * This method is used to remove a quantity of 1 of the GameItem object with the given name from the list of items
     * being held in this container.
     *
     * @param itemName The GameItem to be removed from this container.
     * @param quantityToRemove The quantity to be removed from the container.
     * @return True if the item quantity is successfully removed, false if it is not.
     */
    public boolean removeItemQuantity( String itemName, int quantityToRemove )
    {
        return this.itemUtility.removeItemQuantity( itemName, quantityToRemove );
    }

    /**
     * This method is used to attach this utility object to the component with the given name.
     *
     * @param containerName The name of the GameContainer object to attach this utility to.
     */
    public void setMaxWeight( double newMaxWeight )
    {
        this.itemUtility.setMaxWeight( newMaxWeight );
    }

     /**
      * This method is used to set a new GameContainerItemUtility to handle GameItem management.
      * 
      * @param newItemUtility The new GameContainerItemUtility to set as the active utility for this component.
      */
    public void setItemUtility( GameContainerItemUtility newItemUtility )
    {
        this.itemUtility = newItemUtility;
    }
    ///////////////////////Item Utility////////////////////////////

    /**
     * This method is used to set the current location of this pawn to the location with the given name. It includes'
     * an option to also add the description for the given location to the output.
     *
     * @param locationName The name of the new GameLocation object to set this pawn in.
     * @param describeNewLocation Whether or not to add a description of the new location to the output.
     */
    public void setLocation( String locationName, boolean describeNewLocation )
    {
        GameComponent component = Engine.getComponent( locationName );
        if ( component instanceof GameLocation )
        {
            this.location = (GameLocation) component;
        }
    }
}
