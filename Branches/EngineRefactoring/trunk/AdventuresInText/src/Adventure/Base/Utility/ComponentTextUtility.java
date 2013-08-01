package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import java.util.*;

/**
 * This is the base implementation of the ComponentTextUtility interface.
 */
public class ComponentTextUtility
    implements GameComponentTextUtility
{
    @SuppressWarnings( "compatibility:2433738116956176225" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds the mapping of text string values.
     */
    protected HashMap<String, String> outputText;

    /**
     * This field hold a reference to the component this utility is attached to.
     */
    protected GameComponent component;

    /**
     * This constructor is used to initialize and set up this utility.
     *
     * @param component The component this utility is attached to.
     */
    public ComponentTextUtility( GameComponent component )
    {
        super();
        this.outputText = new HashMap<String, String>();
        this.component = component;
    }

    /**
     * This method is used to retrieve the string of text being stored with the given name.
     *
     * @param textName The name of the string of text to be retrieved.
     * @return The string of text being stored with the given name.
     */
    public String getText( String textName )
    {
        if ( this.outputText.containsKey( textName ) )
        {
            return this.outputText.get( textName );
        }
        return "";
    }

    /**
     * This method is used to determine if a string of text is being stored with the specified name.
     *
     * @param textName This is the name of the text string to check for.
     * @return true if therfe is a text string being stored with the given named, false otherwise.
     */
    public boolean hasText( String textName )
    {
        if ( outputText.containsKey( textName ) )
        {
            if ( outputText.get( textName ) == "" )
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * This method is used to remove a string of text being stored with a given name from the list of stored strings.
     *
     * @param textName The name of the text string to be removed.
     */
    public void removeText( String textName )
    {
        outputText.remove( textName );
    }

    /**
     * This method is used to store a string of text with the given name as its mapped key.
     *
     * @param textName The name to use as the key for this text string.
     * @param newText The string of text to store with the given name.
     */
    public void setText( String textName, String newText )
    {
        this.outputText.put( textName, newText );
    }

    /**
     * This method is used to attach this utility object to a specific GameComponent.
     *
     * @param component The gameComponent object to attach this utility object to.
     */
    public void setComponent( GameComponent component )
    {
        this.component = component;
    }

    /**
     * This method is used to attach this utility object to the GameComponent with the specified name.
     *
     * @param componentName The name of the GameComponent object to attach this utility to.
     */
    public void setComponent( String componentName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component.getName().equalsIgnoreCase( componentName ) )
            {
                this.setComponent( component );
            }
        }
    }
}
