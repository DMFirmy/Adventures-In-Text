package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

import java.util.*;

/**
 * This is the base implementation of the GameActor interface.
 */
public abstract class BaseActor
    extends BasePawn
    implements GameActor
{
    @SuppressWarnings( "compatibility:-7525759558064491710" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds the initial dialog string for this component.
     */
    protected GameDialog initialDialog;

    /**
     * This field stores a the GameContainerItemUtility that is utilized by this actor.
     */
    protected GameContainerItemUtility itemUtility;

    /**
     * This constructor is used to initialize and set up this action.
     *
     * @param name The string name of this action.
     * @param description The text that will be shown as the description for this actor.
     */
    public BaseActor( String name, String description )
    {
        super( name, description );

        this.setStatus( "max weight", 12 );
        this.setStatus( "met player", 0 );
        this.setStatus( "hidden", 0 );
        this.itemUtility = new ContainerItemUtility( this, 12.0 );
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
     * This method is used to attach this utility object to the given GameContainer object.
     *
     * @param container The GameContainer object to attach this utility to.
     */
    public void setItemUtility( GameContainerItemUtility newItemUtility )
    {
        this.itemUtility = newItemUtility;
    }
    ///////////////////////Item Utility////////////////////////////

    /**
     * This method is used to get a reference to the initial GameDialog for this component without initiating it.
     *
     * @return The gameDialog object set as the initial dialog for this component.
     */
    public GameDialog getInitialDialog()
    {
        return initialDialog;
    }

    /**
     * This method is used to set the given GameDialog object as the new initial dialog for this component.
     *
     * @param newInitialDialog The new GameDialog object to be set as the initial dialog.
     */
    public void setInitialDialog( GameDialog newInitialDialog )
    {
        initialDialog = newInitialDialog;
    }

    /**
     * This method is used to set the GameDialog object with the given name as the new initial dialog for this component.
     *
     * @param newInitialDialogName The name of the new GameDialog object to be set as the initial dialog.
     */
    public void setInitialDialog( String newInitialDialogName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameDialog && component.getName().equalsIgnoreCase( newInitialDialogName ) )
            {
                setInitialDialog( ( GameDialog ) component );
                return;
            }
        }
    }

    /**
     * This method is used to call the initiate() method of the GameDialog that is being stored as the initial dialog
     * for this component.
     */
    public void initiateDialog()
    {
        this.initiateDialog( false );
    }

    /**
     * This method will cause this actor to to initiate their initial dialog. It includes the option to initiate
     * the dialog even if this actor is marked as hidden.
     *
     * @param speakWhileHidden whether or not to allow the dialog to initiate if this actor is marked as hidden.
     */
    public void initiateDialog( boolean speakWhileHidden )
    {
        if ( speakWhileHidden || !this.hasStatus( "hidden" ) )
        {
            this.incrementStatus( "met player" );
            if ( initialDialog != null )
            {
                initialDialog.initiate();
            }
            else
            {
                IO.addLine( "I have nothing to say right now." );
            }
        }
        else
        {
            IO.addLine( "There is no one here by that name." );
        }
    }
}
