package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

import java.util.*;

/**
 * This is the base implementation of the GameItem interface.
 */
public abstract class BaseItem
    extends BaseObject
    implements GameItem
{
    @SuppressWarnings( "compatibility:-2089780142014400739" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds a list of containers that contain 1 or more of this item.
     */
    protected ArrayList<GameContainer> containers;

    /**
     * This field is used to hold the weight of this item.
     */
    protected double itemWeight;

    /**
     * This field is used to hold the GameObjectHideUtility utilized by this component.
     */
    protected GameObjectHideUtility hideUtility;

    /**
     * This constructor is used to initialize and set up this item.
     *
     * @param name The string name of this item.
     * @param description The text that will be shown as the description for this item.
     */
    public BaseItem( String name, String description )
    {
        super( name, description );

        this.containers = new ArrayList<GameContainer>();
        this.hideUtility = new ObjectHideUtility( this );

        this.setText( "pluralName", this.getName() + "s" );
        this.setText( "initialPickup", "" );
        this.setText( "pickup", "" );

        this.setItemWeight( 1.0 );
        this.setStatus( "movable", 1 );
        this.setStatus( "hidden", 0 );
        this.setStatus( "pickedUp", 0 );
        this.setStatus( "stackLimit", 1 );
    }

    /**
     * This constructor is used to initialize and set up this item.
     *
     * @param name The string name of this item.
     * @param description The text that will be shown as the description for this item.
     * @param weight The weight to be set for this item.
     * @param stackLimit The stack limit to be set for this item.
     */
    public BaseItem( String name, String description, double weight, int stackLimit )
    {
        super( name, description );

        this.containers = new ArrayList<GameContainer>();
        this.hideUtility = new ObjectHideUtility( this );

        this.setText( "pluralName", this.getName() + "s" );
        this.setText( "initialPickup", "" );
        this.setText( "pickup", "" );

        this.setItemWeight( weight );
        this.setStatus( "movable", 1 );
        this.setStatus( "hidden", 0 );
        this.setStatus( "pickedUp", 0 );
        this.setStatus( "stackLimit", stackLimit );
    }

    /**
     * This constructor is used to initialize and set up this item.
     *
     * @param name The string name of this item.
     * @param description The text that will be shown as the description for this item.
     * @param weight The weight to be set for this item.
     * @param stackLimit The stack limit to be set for this item.
     * @param hidden Whether or not to mark this item as hidden.
     */
    public BaseItem( String name, String description, double weight, int stackLimit, boolean hidden )
    {
        super( name, description );

        this.containers = new ArrayList<GameContainer>();
        this.hideUtility = new ObjectHideUtility( this );

        this.setText( "pluralName", this.getName() + "s" );
        this.setText( "initialPickup", "" );
        this.setText( "pickup", "" );

        this.setItemWeight( weight );
        this.setStatus( "movable", 1 );
        if ( hidden )
        {
            this.setStatus( "hidden", 1 );
        }
        else
        {
            this.setStatus( "hidden", 0 );
        }
        this.setStatus( "pickedUp", 0 );
        this.setStatus( "stackLimit", stackLimit );
    }

    ///////////////////////Hide Utility////////////////////////////

    /**
     * This method is used to mark this GameObject component as hidden.
     */
    public void hide()
    {
        this.hideUtility.hideObject();
    }

    /**
     * This method is used to determine if this GameObject component is marked as hidden.
     *
     * @return True if this component is marked as hidden, false otherwise.
     */
    public boolean isHidden()
    {
        return this.hideUtility.isHidden();
    }

    /**
     * This method is used to unmark this component as being hidden.
     */
    public void reveal()
    {
        this.hideUtility.revealObject();
    }

    /**
     * This method is used to assign an new GameObjectHideUtility to handle the hidden status of this component.
     *
     * @param newHideUtility The new GameObjectHideUtility to set as the active utility for this component.
     */
    public void setHideUtility( GameObjectHideUtility newHideUtility )
    {
        newHideUtility.setObjectComponent( this );
        this.hideUtility = newHideUtility;
    }
    ///////////////////////Hide Utility////////////////////////////

    /**
     * This method is used to get a reference to the GameContainer that contains this item with the given name.
     *
     * @param containerName The name of the container to be retrieved.
     * @return The GameContainer to be retrieved, or null if none is found.
     */
    public GameContainer getContainer( String containerName )
    {
        for ( GameContainer container : containers )
        {
            if ( ( ( GameObject ) container ).getName().equalsIgnoreCase( containerName ) )
            {
                return container;
            }
        }
        return null;
    }

    /**
     * This method is used to get a copy of the list of all containers that hold 1 or more of this item.
     *
     * @return A list of all containers that hold 1 or more of this item
     */
    public ArrayList<GameContainer> getContainerList()
    {
        ArrayList<GameContainer> containerList = new ArrayList<GameContainer>();
        containerList.addAll( this.containers );
        return containerList;
    }

    /**
     * This method is used to add a container to the list of all containers that contain 1 or more of this item.
     *
     * @param container The GameContainer object that contains this item.
     * @return True if the container is sucessfully added to the list, false otherwise.
     */
    public boolean addContainer( GameContainer container )
    {
        boolean addToList = true;

        if ( containers.contains( container ) )
        {
            addToList = false;
        }
        if ( addToList )
        {
            this.containers.add( container );
            return true;
        }
        return false;
    }

    /**
     * This method is used to add the container with the given name to the list of all containers that contain 1 or
     * more of this item.
     *
     * @param newContainerName The name of the GameContainer object that contains this item.
     * @return True if the container is sucessfully added to the list, false otherwise.
     */
    public boolean addContainer( String newContainerName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameContainer && component.getName().equalsIgnoreCase( newContainerName ) )
            {
                this.addContainer( ( GameContainer ) component );
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to remove a container to the list of all containers that contain 1 or more of this item.
     *
     * @param container The GameContainer object that to be removed from the list.
     * @return True if the container is successfully removed, false otherwise.
     */
    public boolean removeContainer( GameContainer container )
    {
        if ( this.containers.remove( container ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to remove the container with the given name to the list of all containers that contain 1 or
     * more of this item.
     *
     * @param containerName The name of the GameContainer object that to be removed from the list.
     * @return True if the container is successfully removed, false otherwise.
     */
    public boolean removeContainer( String containerName )
    {
        for ( GameContainer container : this.containers )
        {
            if ( ( ( GameObject ) container ).getName().equalsIgnoreCase( containerName ) )
            {
                return this.removeContainer( container );
            }
        }
        return false;
    }

    /**
     * This method is used to check whether the given container has been marked as containing this item.
     *
     * @param container The GameContainer object to check for.
     * @return True if the container is marked as containing this item, false otherwise.
     */
    public boolean hasContainer( GameContainer container )
    {
        if ( this.containers.contains( container ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to check whether there is a container with the given name marked as containing this item.
     *
     * @param containerName The name of the GameContainer object to check for.
     * @return True if the container is marked as containing this item, false otherwise.
     */
    public boolean hasContainer( String containerName )
    {
        for ( GameContainer container : this.containers )
        {
            if ( ( ( GameObject ) container ).getName().equalsIgnoreCase( containerName ) )
            {
                return this.hasContainer( container );
            }
        }
        return false;
    }

    /**
     * This method is used to retrieve the weight for a single quantity of this GameItem.
     *
     * @return The weight for a single quantity of this GameItem.
     */
    public double getItemWeight()
    {
        return this.itemWeight;
    }

    /**
     * This method is used to retrieve the plural version of the name for this item. This is not yet fully implemented,
     * but will allow for the use of different names depending on if you are referring to a singular or multiple of a
     * particular GameItem object. An example might be an item named "potion" with a plural name of "potions".
     *
     * @return The plural name for this item.
     */
    public String getPluralName()
    {
        return this.getText( "pluralName" );
    }

    /**
     * This method is used to retrieve the quantity of this GameItem that are held in the container with the given name.
     *
     * @param containerName The name of the GameContainer to be checked.
     * @return The number of this item found in the given continer.
     */
    public int getItemQuantity( String containerName )
    {
        return this.getContainer( containerName ).getItemQuantity( this.getName() );
    }

    /**
     * This method is used to retrieve the maximum quantity of this GameItem that can exist in a single container.
     *
     * @return The maximum quantity of this GameItem that can exist in a single container
     */
    public int getStackLimit()
    {
        return this.getStatus( "stackLimit" );
    }

    /**
     * Checks to see if there is more than a single copy of this item allowed in a single container.
     *
     * @return True if the stack limit is greater than 1, false otherwise.
     */
    public boolean isStackable()
    {
        if ( this.getStatus( "stackLimit" ) > 1 )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to set a new weight for a single quantity of this item.
     *
     * @param newWeight The new weight value for a single quantity of this item.
     */
    public void setItemWeight( double newWeight )
    {
        this.itemWeight = newWeight;
    }

    /**
     * This method is used to set a new weight for a single quantity of this item to the value indicated by the given
     * string.
     *
     * @param newWeightString the string representation of the double value to set as the new weight.
     */
    public void setItemWeight( String newWeightString )
    {
        this.setItemWeight( Double.parseDouble( newWeightString ) );
    }

    /**
     * This method is used to set a new plural name for this item.
     *
     * @param newPluralName The new plural name to set for this item.
     */
    public void setPluralName( String newPluralName )
    {
        this.setText( "pluralName", newPluralName );
    }

    /**
     * This method is used to set a new stack limit for this item, which is the maximum quantity that can
     * exist within a single GameContainer.
     *
     * @param newStackLimit The new maximum quantity that can of this item that can exist within a single GameContainer.
     */
    public void setStackLimit( int newStackLimit )
    {
        this.setStatus( "stackLimit", 1 );
    }

    /**
     * This method is used to set the stack limit to the value indicated by the given string.
     *
     * @param newStackLimitString A string representation of the integer to set as the new satck limit.
     */
    public void setStackLimit( String newStackLimitString )
    {
        this.setStackLimit( Integer.parseInt( newStackLimitString ) );
    }
}
