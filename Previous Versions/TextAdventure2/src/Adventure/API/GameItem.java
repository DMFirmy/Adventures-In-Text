package Adventure.API;

import java.util.*;

/**
 * This interface is used to represent any physical items within the game world.
 */
public abstract interface GameItem
    extends GameObject, GameHideable
{
    /**
     * This method is used to retrieve the maximum quantity of this GameItem that can exist in a single container.
     * 
     * @return The maximum quantity of this GameItem that can exist in a single container
     */
    public abstract int getStackLimit();

    /**
     * This method is used to retrieve the weight for a single quantity of this GameItem.
     * 
     * @return The weight for a single quantity of this GameItem.
     */
    public abstract double getItemWeight();

    /**
     * This method is used to retrieve the quantity of this GameItem that are held in the container with the given name.
     * 
     * @param containerName The name of the GameContainer to be checked.
     * @return The number of this item found in the given continer.
     */
    public abstract int getItemQuantity( String containerName );

    /**
     * This method is used to retrieve the plural version of the name for this item. This is not yet fully implemented,
     * but will allow for the use of different names depending on if you are referring to a singular or multiple of a 
     * particular GameItem object. An example might be an item named "potion" with a plural name of "potions".
     * 
     * @return The plural name for this item.
     */
    public abstract String getPluralName();

    /**
     * Checks to see if there is more than a single copy of this item allowed in a single container.
     * 
     * @return True if the stack limit is greater than 1, false otherwise.
     */
    public abstract boolean isStackable();

    /**
     * This method is used to add a container to the list of all containers that contain 1 or more of this item.
     * 
     * @param container The GameContainer object that contains this item.
     * @return True if the container is sucessfully added to the list, false otherwise.
     */
    public abstract boolean addContainer( GameContainer container );

    /**
     * This method is used to add the container with the given name to the list of all containers that contain 1 or 
     * more of this item.
     * 
     * @param containerName The name of the GameContainer object that contains this item.
     * @return True if the container is sucessfully added to the list, false otherwise.
     */
    public abstract boolean addContainer( String containerName );

    /**
     * This method is used to remove a container to the list of all containers that contain 1 or more of this item.
     * 
     * @param container The GameContainer object that to be removed from the list.
     * @return True if the container is successfully removed, false otherwise.
     */
    public abstract boolean removeContainer( GameContainer container );

    /**
     * This method is used to remove the container with the given name to the list of all containers that contain 1 or 
     * more of this item.
     * 
     * @param containerName The name of the GameContainer object that to be removed from the list.
     * @return True if the container is successfully removed, false otherwise.
     */
    public abstract boolean removeContainer( String containerName );

    /**
     * This method is used to get a reference to the gamecontainer that contains this item with the given name.
     * 
     * @param containerName The name of the container to be retrieved.
     * @return The GameContainer to be retrieved, or null if none is found.
     */
    public abstract GameContainer getContainer( String containerName );

    /**
     * This method is used to get a copy of the list of all containers that hold 1 or more of this item.
     * 
     * @return A list of all containers that hold 1 or more of this item
     */
    public abstract ArrayList<GameContainer> getContainerList();

    /**
     * This method is used to check whether the given container has been marked as containing this item.
     * 
     * @param container The GameContainer object to check for.
     * @return True if the container is marked as containing this item, false otherwise.
     */
    public abstract boolean hasContainer( GameContainer container );

    /**
     * This method is used to check whether there is a container with the given name marked as containing this item.
     * 
     * @param containerName The name of the GameContainer object to check for.
     * @return True if the container is marked as containing this item, false otherwise.
     */
    public abstract boolean hasContainer( String containerName );

    /**
     * This method is used to set a new plural name for this item.
     * 
     * @param newPluralName The new plural name to set for this item.
     */
    public abstract void setPluralName( String newPluralName );

    /**
     * This method is used to set a new stack limit for this item, which is the maximum quantity that can
     * exist within a single GameContainer.
     * 
     * @param newStackLimit The new maximum quantity that can of this item that can exist within a single GameContainer.
     */
    public abstract void setStackLimit( int newStackLimit );

    /**
     * This method is used to set the stack limit to the value indicated by the given string.
     * 
     * @param newStackLimitString A string representation of the integer to set as the new satck limit. 
     */
    public abstract void setStackLimit( String newStackLimitString );

    /**
     * This method is used to set a new weight for a single quantity of this item.
     * 
     * @param newWeight The new weight value for a single quantity of this item.
     */
    public abstract void setItemWeight( double newWeight );

    /**
     * This method is used to set a new weight for a single quantity of this item to the value indicated by the given
     * string.
     * 
     * @param newWeightString the string representation of the double value to set as the new weight.
     */
    public abstract void setItemWeight( String newWeightString );
}
