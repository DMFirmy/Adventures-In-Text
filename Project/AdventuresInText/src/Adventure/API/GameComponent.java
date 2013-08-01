package Adventure.API;

import Adventure.API.Utility.*;

import java.io.*;

/**
 * This interface represents the base of the entire API inheritance structure. All of the processing the game is capable
 * of performing is designed to manipulate and work with GameComponent implementing objects.
 */
public abstract interface GameComponent
    extends Serializable
{
    /**
     * This method is used to get the unique component name for this GameComponent object.
     * 
     * @return The string name for this GameComponent.
     */
    public abstract String getName();

    /**
     * This method will retrieve the value stored for this component for a given status name.
     * 
     * @param statusName The string name of the status to check.
     * @return The integer value stored for the given status or a 0 if not found.
     */
    public abstract int getStatus( String statusName );

    /**
     * This method is used to retrieve the value stored for a text string with a given name.
     * 
     * @param textName The string name of the text to be retrieved.
     * @return The string of text with the given name.
     */
    public abstract String getText( String textName );

    /**
     * This method will check if there is a value greater than 0 for the given status name.
     * 
     * @param statusName The string name of the status to check.
     * @return True if the given staus name has a value greater than 0, false otherwise.
     */
    public abstract boolean hasStatus( String statusName );

    /**
     * This method will check for the presence of a text string being stored with a given name.
     * 
     * @param textName The string name of the text to search for.
     * @return True if there is a stored text string with the given name, false if not.
     */
    public abstract boolean hasText( String textName );

    /**
     * This method will take the current value stored for a given status name and increment it by 1. If there is no value
     * stored for the given status name, the value of 1 is set.
     * 
     * @param statusName The string name of the status to be incremented.
     */
    public abstract void incrementStatus( String statusName );

    /**
     * This method is used to add this GameComponent to the Engine class master list by calling Engine.addItem( this ).
     */
    public abstract void register();

    /**
     * This method will completely remove the given status name from the status list.
     * 
     * @param statusName The name of the status to be removed.
     */
    public abstract void removeStatus( String statusName );

    /**
     * This method will completely remove the given text name from the text list.
     * 
     * @param textName The name of the text to be removed.
     */
    public abstract void removeText( String textName );

    /**
     * This method is used to set the name for this GameComponent. It should validate the name using the
     * ComponentValidator.validateComponentName() method. GameCommands should be validated using the
     * ComponentValidator.validateCommandName() method instead.
     * 
     * @param newName The name to be set for this component.
     */
    public abstract void setName( String newName );

    /**
     * This method is used to set the status with the given name to the given value.
     * 
     * @param statusName The name of the status to be set.
     * @param newStatus The value to be set for the given status name.
     */
    public abstract void setStatus( String statusName, int newStatus );

    /**
     * This method is used to assign a new GameComponentStatusUtility to this GameComponent.
     * 
     * @param newStatusUtility The new GameComponentStatusUtility object to assign to this GameComponent.
     */
    public abstract void setStatusUtility( GameComponentStatusUtility newStatusUtility );

    /**
     * This method is used to set the status with the given name to the given value.
     * 
     * @param textName The name of the text string to be set.
     * @param newText The text string to be stored with the given name.
     */
    public abstract void setText( String textName, String newText );

    /**
     * This method is used to assign a new GameComponentTextUtility to this GameComponent.
     * 
     * @param newTextUtility the new GameComponentTextUtility to assign to this GameComponent.
     */
    public abstract void setTextUtility( GameComponentTextUtility newTextUtility );
}
