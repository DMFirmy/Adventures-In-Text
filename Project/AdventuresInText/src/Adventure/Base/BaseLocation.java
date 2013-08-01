package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

import java.util.*;

/**
 * This is the base implementation of the GameLocation interface.
 */
public abstract class BaseLocation
    extends BaseObject
    implements GameLocation
{
    @SuppressWarnings( "compatibility:-1345567411522747430" )
    private static final long serialVersionUID = 1L;

    /**
     * This field is used to hold the GameLocationContainerUtility utilized by this component.
     */
    protected GameLocationContainerUtility containerUtility;

    /**
     * This field is used to hold the GameLocationExitUtility utilized by this component.
     */
    protected GameLocationExitUtility exitUtility;

    /**
     * This field is used to hold the GameContainerItemUtility utilized by this component.
     */
    protected GameContainerItemUtility itemUtility;

    /**
     * This field is used to hold the GameLocationPawnUtility utilized by this component.
     */
    protected GameLocationPawnUtility pawnUtility;

    /**
     * This field is used to hold the GameLocationSpeakerUtility utilized by this component.
     */
    protected GameLocationSpeakerUtility speakerUtility;

    /**
     * This constructor is used to initialize and set up this pawn.
     *
     * @param name The string name of this action.
     * @param description The text that will be shown as the description for this pawn.
     */
    public BaseLocation( String name, String description )
    {
        super( name, description );
        this.containerUtility = new LocationContainerUtility( this );
        this.exitUtility = new LocationExitUtility( this );
        this.itemUtility = new ContainerItemUtility( this );
        this.pawnUtility = new LocationPawnUtility( this );
        this.speakerUtility = new LocationSpeakerUtility( this );
    }

    ///////////////////////Container Utility////////////////////////////

    /**
     * This method is used to search for a GameContainer to find out if it is present in this location. This search
     * will not locate the container if it is a GameHideable that is marked as hidden.
     *
     * @param container The GameContainer to be searched for.
     * @return True if the GameContainer is found within this location or a component within this location, false otherwise.
     */
    public boolean hasContainer( GameContainer container )
    {
        return this.containerUtility.hasContainer( container );
    }

    /**
     * This method is used to search for a GameContainer to find out if it is present in this location. It includes the
     * option to not locate the container even if it is a GameHideable that is marked as hidden.
     *
     * @param container The GameContainer to be searched for.
     * @param includeHidden Whether or not to include a container that is a GameHideable marked as hidden.
     * @return True if the GameContainer is found within this location or a component within this location, false otherwise.
     */
    public boolean hasContainer( GameContainer container, boolean includeHidden )
    {
        return this.containerUtility.hasContainer( container, includeHidden );
    }

    /**
     * This method is used to search for a container with a given name to find out if it is present in this location.
     * This search will not locate the container if it is a GameHideable that is marked as hidden.
     *
     * @param containerName The name of the GameContainer to be searched for.
     * @return True if the GameContainer is found within this location or a component within this location, dalse otherwise.
     */
    public boolean hasContainer( String containerName )
    {
        return this.containerUtility.hasContainer( containerName );
    }

    /**
     * This method is used to search for a container with a given name to find out if it is present in this location.
     * It includes the option to not locate the container even if it is a GameHideable that is marked as hidden.
     *
     * @param containerName The name of the GameContainer to be searched for.
     * @param includeHidden Whether or not to include a container that is a GameHideable marked as hidden.
     * @return True if the GameContainer is found within this location or a component within this location, dalse otherwise.
     */
    public boolean hasContainer( String containerName, boolean includeHidden )
    {
        return this.containerUtility.hasContainer( containerName, includeHidden );
    }

    /**
     * This method is used to set a new GameLocationContainerUtility to handle GameContainers for this location.
     * @param newContainerUtility The new GameLocationContainerUtility to utilize for this location.
     */
    public void setContainerUtility( GameLocationContainerUtility newContainerUtility )
    {
        newContainerUtility.setLocationComponent( this );
        this.containerUtility = newContainerUtility;
    }
    ///////////////////////Container Utility////////////////////////////

    ///////////////////////Exit Utility////////////////////////////

    /**
     * This method is used to add a new GameExit to this location in the given direction that leads to the
     * given location.
     *
     * @param direction the GameDirection to be moved to reach the new location.
     * @param location The GameLocation that moving in the given direction leads to.
     * @return True if the exit was sucessfully added, false if not.
     */
    public boolean addExit( GameDirection direction, GameLocation location )
    {
        return this.exitUtility.addExit( direction, location );
    }

    /**
     * This method is used to add a new GameExit to this location in the direction with the given name that leads to the
     * location with the given name.
     *
     * @param directionName The name of the GameDirection to be moved to reach the new location.
     * @param locationName The name of the GameLocation that moving in the given direction leads to.
     * @return True if the exit was sucessfully added, false if not.
     */
    public boolean addExit( String directionName, String locationName )
    {
        return this.exitUtility.addExit( directionName, locationName );
    }

    /**
     * This method adds a GameExit object to the list of exits in this location.
     *
     * @param newExit The new GameExit to add to this location.
     * @return True if the exit was sucessfully added, false if not.
     */
    public boolean addExit( GameExit newExit )
    {
        return this.exitUtility.addExit( newExit );
    }

    /**
     * This method adds a GameExit object to the list of exits in this location.
     *
     * @param exitName The name of the new GameExit to add to this location.
     * @return True if the exit was sucessfully added, false if not.
     */
    public boolean addExit( String exitName )
    {
        return this.exitUtility.addExit( exitName );
    }

    /**
     * This method is used to get a copy of the list of exits in this location.
     *
     * @return A copy of the list of exits in this location.
     */
    public ArrayList<GameExit> exitList()
    {
        return this.exitUtility.exitList();
    }

    /**
     * This method is used to get a reference to an exit in this location by its name.
     *
     * @param exitName the name of the exit to be retrieved.
     * @return The GameExit with the given name or null if none is found.
     */
    public GameExit getExit( String exitName )
    {
        return this.exitUtility.getExit( exitName );
    }

    /**
     * This method is used to get a reference to the exit that you would travel through by moving the direction
     * that has the given name.
     *
     * @param directionName the name of the direction of which you want the exit.
     * @return The gameExit object that is in the specified direction, or null if none is found.
     */
    public GameExit getExitByDirection( String directionName )
    {
        return this.exitUtility.getExitByDirection( directionName );
    }

    /**
     * This method is used to see if a given Gameexit object is within the list of exits for this location. This search
     * doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param exit The GameExit object to check for.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExit( GameExit exit )
    {
        return this.exitUtility.hasExit( exit );
    }

    /**
     * This method is used to see if a given GameExit object is within the list of exits for this location. It also includes
     * the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param exit The GameExit object to check for.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExit( GameExit exit, boolean includeHidden )
    {
        return this.exitUtility.hasExit( exit, includeHidden );
    }

    /**
     * This method is used to see if an exit with the given name is within the list of exits for this location. This search
     * doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param exitName The name of the GameExit object to check for.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExit( String exitName )
    {
        return this.exitUtility.hasExit( exitName );
    }

    /**
     * This method is used to see if an exit with the given name is within the list of exits for this location. It also includes
     * the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param exitName The name of the GameExit object to check for.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExit( String exitName, boolean includeHidden )
    {
        return this.exitUtility.hasExit( exitName, includeHidden );
    }

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location.  This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param direction The direction to check for an exit.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitDirection( GameDirection direction )
    {
        return this.exitUtility.hasExitDirection( direction );
    }

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param direction The GameDirection object to check for an exit.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitDirection( GameDirection direction, boolean includeHidden )
    {
        return this.exitUtility.hasExitDirection( direction, includeHidden );
    }

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location. This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param directionName The name of the direction to check for an exit.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitDirection( String directionName )
    {
        return this.exitUtility.hasExitDirection( directionName );
    }

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param directionName The name of the direction to check for an exit.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitDirection( String directionName, boolean includeHidden )
    {
        return this.exitUtility.hasExitDirection( directionName, includeHidden );
    }

    /**
     * This method is used to see if an exit to the given location is within the list of exits for this
     * location. This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param location The Gamelocation object to check for an exit to.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitLocation( GameLocation location )
    {
        return this.exitUtility.hasExitLocation( location );
    }

    /**
     * This method is used to see if an exit to the given location is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param location The Gamelocation object to check for an exit to.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitLocation( GameLocation location, boolean includeHidden )
    {
        return this.exitUtility.hasExitLocation( location, includeHidden );
    }

    /**
     * This method is used to see if an exit to the location with the given name is within the list of exits for this
     * location. This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param locationName The Gamelocation object to check for an exit to.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitLocation( String locationName )
    {
        return this.exitUtility.hasExitLocation( locationName );
    }

    /**
     * This method is used to see if an exit to the location with the given name is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param locationName The Gamelocation object to check for an exit to.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public boolean hasExitLocation( String locationName, boolean includeHidden )
    {
        return this.exitUtility.hasExitLocation( locationName, includeHidden );
    }

    /**
     * This method is used to get a string of text to list the direction and location being lead to for each exit in this
     * location. This text string doesn't include GameHideable exits marked as hidden.
     *
     * @return A string listing of the exits within this location.
     */
    public String listExits()
    {
        return this.exitUtility.listExits();
    }

    /**
     * This method is used to remove the given GameExit from the list of exits in this location.
     *
     * @param exit The GameExit to be removed from this container.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public boolean removeExit( GameExit exit )
    {
        return this.exitUtility.removeExit( exit );
    }

    /**
     * This method is used to remove the GameExit with the given name from the list of exits in this location.
     *
     * @param exitName The GameExit to be removed from this location.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public boolean removeExit( String exitName )
    {
        return this.exitUtility.removeExit( exitName );
    }

    /**
     * This method is used to remove the GameExit in the given GameDirection from the list of exits in this location.
     *
     * @param direction The direction to remove the GameExit from.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public boolean removeExitDirection( GameDirection direction )
    {
        return this.exitUtility.removeExitByDirection( direction );
    }

    /**
     * This method is used to remove the exit in the named direction from the list of exits in this location.
     *
     * @param directionName The name of the direction to remove the GameExit from.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public boolean removeExitDirection( String directionName )
    {
        return this.exitUtility.removeExitByDirection( directionName );
    }

    /**
     * This method is used to remove all exits that lead to the given GameLocation from this location.
     *
     * @param location The location to remove all the GameExits leading to.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public boolean removeExitLocation( GameLocation location )
    {
        return this.exitUtility.removeExitsByLocation( location );
    }

    /**
     * This method is used to remove all exits that lead to the given GameLocation with the given name from this location.
     *
     * @param locationName The location to remove all the GameExits leading to.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public boolean removeExitLocation( String locationName )
    {
        return this.exitUtility.removeExitsByLocation( locationName );
    }

    /**
     * This method is used to set a new GameLocationExitUtility object to handle exits for this location.
     *
     * @param newExitUtility The new GameLocationExitUtility to utilize for this location.
     */
    public void setExitUtility( GameLocationExitUtility newExitUtility )
    {
        newExitUtility.setLocationComponent( this );
        this.exitUtility = newExitUtility;
    }
    ///////////////////////Exit Utility////////////////////////////

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
     * the game output. This text string doesn't include GameHideable objects marked as hidden.
     *
     * @return A string listing of the items held within this container.
     */
    public String listItems()
    {
        return this.itemUtility.listItems();
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
     * This method is used to remove the given quantity of the given GameItem from this container.
     *
     * @param item The GameItem to be removed from this container.
     * @param quantity The quantity to be removed from the container.
     * @return True if the given quantity is successfully removed, false otherwise.
     */
    public boolean removeItemQuantity( GameItem item, int quantity )
    {
        return this.itemUtility.removeItemQuantity( item, quantity );
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
     * This method is used to assign a new maximum combined item weight to this container.
     *
     * @param newMaxWeight The new maximum combined item weight.
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
        newItemUtility.setContainerComponent( this );
        this.itemUtility = newItemUtility;
    }
    ///////////////////////Item Utility////////////////////////////

    ///////////////////////Pawn Utility////////////////////////////

    /**
     * This method is used to get a copy of the complete list of GameActors in this location.
     *
     * @return A copy of the list of GameActors in this location.
     */
    public ArrayList<GameActor> actorList()
    {
        return this.pawnUtility.actorList();
    }

    /**
     * This method is used to add the given GamePawn to this location.
     *
     * @param newPawn the GamePawn to be added to the list of pawns in this location.
     * @return True if successfully added, false otherwise.
     */
    public boolean addPawn( GamePawn newPawn )
    {
        return this.pawnUtility.addPawn( newPawn );
    }

    /**
     * This method is used to add the GamePawn with the given name to this location.
     *
     * @param pawnName The name of the GamePawn to be added to the list of pawns in this location.
     * @return True if successfully added, false otherwise.
     */
    public boolean addPawn( String pawnName )
    {
        return this.pawnUtility.addPawn( pawnName );
    }

    /**
     * This method is used to retrieve a reference to the GameActor in this location with a given name.
     *
     * @param actorName The name of the GameActor to be retrieved.
     * @return A reference to the GameActor in this location with a given name.
     */
    public GameActor getActor( String actorName )
    {
        return this.pawnUtility.getActor( actorName );
    }

    /**
     * This method is used to retrieve a reference to the GamePawn in this location with a given name.
     *
     * @param pawnName The name of the GamePawn to be retrieved.
     * @return A reference to the GamePawn in this location with a given name.
     */
    public GamePawn getPawn( String pawnName )
    {
        return this.pawnUtility.getPawn( pawnName );
    }

    /**
     * This method is used to retrieve a reference to the GamePlayer in this location with a given name.
     *
     * @param playerName The name of the GamePlayer to be retrieved.
     * @return A reference to the GamePlayer in this location with a given name.
     */
    public GamePlayer getPlayer( String playerName )
    {
        return this.pawnUtility.getPlayer( playerName );
    }

    /**
     * This method is used to determine if the given GameActor is in this location. This search will not
     * include GameHideable actors marked as hidden.
     *
     * @param actor The GameActor to look for.
     * @return True if the actor is in this location, false otherwise.
     */
    public boolean hasActor( GameActor actor )
    {
        return this.pawnUtility.hasActor( actor );
    }

    /**
     * This method is used to determine if the given GameActor is in this location. It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param actor The GameActor to look for.
     * @param includeHidden whether or not to include GameHideable actors marked as hidden.
     * @return True if the actor is in this location, false otherwise.
     */
    public boolean hasActor( GameActor actor, boolean includeHidden )
    {
        return this.pawnUtility.hasActor( actor, includeHidden );
    }

    /**
     * This method is used to determine if a GameActor with the given name is in this location. This search will not
     * include GameHideable actors marked as hidden.
     *
     * @param actorName The name of the GameActor to look for.
     * @return True if the actor is in this location, false otherwise.
     */
    public boolean hasActor( String actorName )
    {
        return this.pawnUtility.hasActor( actorName );
    }

    /**
     * This method is used to determine if a GameActor with the given name is in this location. It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param actorName The name of the GameActor to look for.
     * @param includeHidden whether or not to include GameHideable actors marked as hidden.
     * @return True if the actor is in this location, false otherwise.
     */
    public boolean hasActor( String actorName, boolean includeHidden )
    {
        return this.pawnUtility.hasActor( actorName, includeHidden );
    }

    /**
     * This method is used to determine if the given GamePawn is in this location. This search will not
     * include GameHideable pawns marked as hidden.
     *
     * @param pawn The GamePawn to look for.
     * @return True if the pawn is in this location, false otherwise.
     */
    public boolean hasPawn( GamePawn pawn )
    {
        return this.pawnUtility.hasPawn( pawn );
    }

    /**
     * This method is used to determine if the given GamePawn is in this location. It has the option to also
     * include GameHideable pawns marked as hidden.
     *
     * @param pawn The GamePawn to look for.
     * @param includeHidden whether or not to include GameHideable pawns marked as hidden.
     * @return True if the pawn is in this location, false otherwise.
     */
    public boolean hasPawn( GamePawn pawn, boolean includeHidden )
    {
        return this.pawnUtility.hasPawn( pawn, includeHidden );
    }

    /**
     * This method is used to determine if a GamePawn with the given name is in this location. This search will not
     * include GameHideable pawns marked as hidden.
     *
     * @param pawnName The name of the GamePawn to look for.
     * @return True if the pawn is in this location, false otherwise.
     */
    public boolean hasPawn( String pawnName )
    {
        return this.pawnUtility.hasPawn( pawnName );
    }

    /**
     * This method is used to determine if a GamePawn with the given name is in this location. It has the option to also
     * include GameHideable pawns marked as hidden.
     *
     * @param pawnName The name of the GamePawn to look for.
     * @param includeHidden whether or not to include GameHideable actors marked as hidden.
     * @return True if the pawn is in this location, false otherwise.
     */
    public boolean hasPawn( String pawnName, boolean includeHidden )
    {
        return this.pawnUtility.hasPawn( pawnName, includeHidden );
    }

    /**
     * This method is used to determine if the given GamePlayer is in this location. This search will not
     * include GameHideable pawns marked as hidden.
     *
     * @param player The GamePlayer to look for.
     * @return True if the player is in this location, false otherwise.
     */
    public boolean hasPlayer( GamePlayer player )
    {
        return this.pawnUtility.hasPlayer( player );
    }

    /**
     * This method is used to determine if the given GamePlayer is in this location. It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param player The GamePlayer to look for.
     * @param includeHidden whether or not to include GameHideable players marked as hidden.
     * @return True if the player is in this location, false otherwise.
     */
    public boolean hasPlayer( GamePlayer player, boolean includeHidden )
    {
        return this.pawnUtility.hasPlayer( player, includeHidden );
    }

    /**
     * This method is used to determine if a GamePlayer with the given name is in this location. This search will not
     * include GameHideable player marked as hidden.
     *
     * @param playerName The name of the GamePlayer to look for.
     * @return True if the player is in this location, false otherwise.
     */
    public boolean hasPlayer( String playerName )
    {
        return this.pawnUtility.hasPlayer( playerName );
    }

    /**
     * This method is used to determine if a GamePlayer with the given name is in this location.It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param playerName The name of the GamePlayer to look for.
     * @param includeHidden whether or not to include GameHideable players marked as hidden.
     * @return True if the player is in this location, false otherwise.
     */
    public boolean hasPlayer( String playerName, boolean includeHidden )
    {
        return this.pawnUtility.hasPlayer( playerName, includeHidden );
    }

    /**
     * This method is used to get a string of text to list the names of all the GameActors in this
     * location. This text string doesn't include GameHideable actors marked as hidden.
     *
     * @return A string listing of the actors in this location.
     */
    public String listActors()
    {
        return this.pawnUtility.listActors( "Characters in this location:" );
    }

    /**
     * This method is used to get a string of text to list the names of all the GamePlayers in this
     * location. This text string doesn't include GameHideable actors marked as hidden.
     *
     * @return A string listing of the players in this location.
     */
    public String listPlayers()
    {
        return this.pawnUtility.listPlayers( "Players in this location:" );
    }

    /**
     * This method is used to get a copy of the list of GamePawn objects in this location.
     *
     * @return A copy of the list of GamePawn objects in this location.
     */
    public ArrayList<GamePawn> pawnList()
    {
        return this.pawnUtility.pawnList();
    }

    /**
     * This method is used to get a copy of the list of GamePlayer objects in this location.
     *
     * @return A copy of the list of GamePlayer objects in this location.
     */
    public ArrayList<GamePlayer> playerList()
    {
        return this.pawnUtility.playerList();
    }

    /**
     * This method is used to remove the given GamePawn from the list of pawns in this location.
     *
     * @param pawn The GamePawn to be removed from this location.
     * @return True if the pawn is successfully removed, false if it is not.
     */
    public boolean removePawn( GamePawn pawn )
    {
        return this.pawnUtility.removePawn( pawn );
    }

    /**
     * This method is used to remove the GamePawn with the given name from the list of pawns in this location.
     *
     * @param pawnName The name of the GamePawn to be removed from this location.
     * @return True if the pawn is successfully removed, false if it is not.
     */
    public boolean removePawn( String pawnName )
    {
        return this.pawnUtility.removePawn( pawnName );
    }

    /**
     * This method is used to assign an new GameLocationPawnUtility to handle the hidden status of this component.
     *
     * @param newPawnUtility The new GameLocationPawnUtility to set as the active utility for this component.
     */
    public void setPawnUtility( GameLocationPawnUtility newPawnUtility )
    {
        newPawnUtility.setLocationComponent( this );
        this.pawnUtility = newPawnUtility;
    }
    ///////////////////////Pawn Utility////////////////////////////

    ///////////////////////Speaker Utility////////////////////////////

    /**
     * This method is used to search for a GameSpeaker to find out if it is present in this location. This search
     * will not locate the speaker if it is a GameHideable that is marked as hidden.
     *
     * @param speaker The GameSpeaker to be searched for.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public boolean hasSpeaker( GameSpeaker speaker )
    {
        return this.speakerUtility.hasSpeaker( speaker );
    }

    /**
     * This method is used to search for a GameSpeaker to find out if it is present in this location. It includes the
     * option to not locate the speaker even if it is a GameHideable that is marked as hidden.
     *
     * @param speaker The GameSpeaker to be searched for.
     * @param includeHidden Whether or not to include a speaker that is a GameHideable marked as hidden.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public boolean hasSpeaker( GameSpeaker speaker, boolean includeHidden )
    {
        return this.speakerUtility.hasSpeaker( speaker, includeHidden );
    }

    /**
     * This method is used to search for a GameSpeaker with the given name to find out if it is present in this location.
     * This search will not locate the speaker if it is a GameHideable that is marked as hidden.
     *
     * @param speakerName The name of the GameSpeaker to be searched for.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public boolean hasSpeaker( String speakerName )
    {
        return this.speakerUtility.hasSpeaker( speakerName );
    }

    /**
     * This method is used to search for a GameSpeaker with the given name to find out if it is present in this location.
     * It includes the option to not locate the speaker even if it is a GameHideable that is marked as hidden.
     *
     * @param speakerName The name of the GameSpeaker to be searched for.
     * @param includeHidden Whether or not to include a speaker that is a GameHideable marked as hidden.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public boolean hasSpeaker( String speakerName, boolean includeHidden )
    {
        return this.speakerUtility.hasSpeaker( speakerName, includeHidden );
    }

    /**
     * This method is used to assign an new GameLocationSpeakerUtility to handle the hidden status of this component.
     *
     * @param newSpeakerUtility The new GameLocationSpeakerUtility to set as the active utility for this component.
     */
    public void setSpeakerUtility( GameLocationSpeakerUtility newSpeakerUtility )
    {
        newSpeakerUtility.setLocationComponent( this );
        this.speakerUtility = newSpeakerUtility;
    }
    ///////////////////////Speaker Utility////////////////////////////

    /**
     * This method will add a description for this GameObject component to the output. It also includes
     * a list of actors, players, items, and exits within this location.
     */
    @Override
    public void describe()
    {
        IO.addLine( this.getText( "description" ) + "\n" );

        if ( this.playerList().size() > 1 )
        {
            IO.addLine( this.listPlayers() );
        }
        if ( !this.actorList().isEmpty() )
        {
            IO.addLine( this.listActors() );
        }
        if ( !this.itemList().isEmpty() )
        {
            IO.addLine( this.listItems() );
        }
        IO.addLine( this.listExits() );
    }

    /**
     * This method is used to unmark as hidden all components that are currently so marked within this location.
     */
    public void revealHidden()
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.hasStatus( "hidden" ) )
            {
                pawn.removeStatus( "hidden" );
            }
        }
        for ( GameItem item : this.itemList() )
        {
            if ( item.hasStatus( "hidden" ) )
            {
                item.removeStatus( "hidden" );
            }
        }
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.hasStatus( "hidden" ) )
            {
                exit.removeStatus( "hidden" );
            }
        }
    }
}
