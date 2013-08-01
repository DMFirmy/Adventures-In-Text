package Adventure.API.Utility;

import Adventure.API.*;

/**
 * This interface is used to represent a utility object that implements all of the functionality for a game component
 * to maintain a listing of named strings of text. These text strings can be used as output, to store notes, or
 * for whatever purpose that a content developer might see fit.
 */
public interface GameComponentTextUtility
    extends GameUtility
{
    /**
     * This method is used to retrieve the string of text being stored with the given name.
     * 
     * @param textName The name of the string of text to be retrieved.
     * @return The string of text being stored with the given name.
     */
    public abstract String getText( String textName );

    /**
     * This method is used to determine if a string of text is being stored with the specified name.
     * 
     * @param textName This is the name of the text string to check for.
     * @return true if therfe is a text string being stored with the given named, false otherwise.
     */
    public abstract boolean hasText( String textName );

    /**
     * This method is used to remove a string of text being stored with a given name from the list of stored strings.
     * 
     * @param textName The name of the text string to be removed.
     */
    public abstract void removeText( String textName );

    /**
     * This method is used to attach this utility object to a specific GameComponent.
     * 
     * @param component The gameComponent object to attach this utility object to.
     */
    public abstract void setComponent( GameComponent component );

    /**
     * This method is used to attach this utility object to the GameComponent with the specified name.
     * 
     * @param componentName The name of the GameComponent object to attach this utility to.
     */
    public abstract void setComponent( String componentName );

    /**
     * This method is used to store a string of text with the given name as its mapped key.
     * 
     * @param textName The name to use as the key for this text string.
     * @param newText The string of text to store with the given name.
     */
    public abstract void setText( String textName, String newText );
}
