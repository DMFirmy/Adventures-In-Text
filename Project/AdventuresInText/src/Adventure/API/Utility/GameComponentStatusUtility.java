package Adventure.API.Utility;

import Adventure.API.*;

/**
 * This utility object is used to handle all the functionality for handling status effects within a component.
 * Instead of each component needing to maintain seperate code for manipulating status effects, each component
 * need only reference the functionality from its utility object. This way, there is less duplication of code
 * between classes and also there is a basic set of functionality in place for content creators to utilize for
 * any custom content they create.
 */
public interface GameComponentStatusUtility
    extends GameUtility
{
    /**
     * This method will retrieve the value stored for the attached component for a given status name.
     * 
     * @param statusName The string name of the status to check.
     * @return The integer value stored for the given status or a 0 if not found.
     */
    public abstract int getStatus( String statusName );

    /**
     * This method will check if there is a value greater than 0 for the given status name.
     * 
     * @param statusName The string name of the status to check.
     * @return True if the given staus name has a value greater than 0, false otherwise.
     */
    public abstract boolean hasStatus( String statusName );

    /**
     * This method will take the current value stored for a given status name and increment it by 1. If there is no value
     * stored for the given status name, the value of 1 is set.
     * 
     * @param statusName The string name of the status to be incremented.
     */
    public abstract void incrementStatus( String statusName );

    /**
     * This method will completely remove the given status name from the status list.
     * 
     * @param statusName The name of the status to be removed.
     */
    public abstract void removeStatus( String statusName );

    /**
     * This method is used to set a refrence to the GameComponent that is using this utility object.
     * 
     * @param component The new GameComponent that is using this utility object.
     */
    public abstract void setStatusComponent( GameComponent component );

    /**
     * This method is used to set a refrence to the GameComponent with the given name that is using this utility object.
     * 
     * @param componentName The name of the new GameComponent that is using this utility object.
     */
    public abstract void setStatusComponent( String componentName );

    /**
     * This method is used to directly set the value of a given status name to the given value.
     * 
     * @param statusName The name of the status to have its value set.
     * @param newStatus The value top set the status with the given name to.
     */
    public abstract void setStatus( String statusName, int newStatus );
}
