package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

import Adventure.Core.Validation.ComponentValidator;
import Adventure.Core.Validation.ValidationCode;

/**
 * This is the base implementation of the GameComponent interface.
 */
public abstract class BaseComponent
    implements GameComponent
{
    @SuppressWarnings( "compatibility:5551380580628413870" )
    private static final long serialVersionUID = 1L;

    /**
     * This field stores the string name for this component.
     */
    protected String name;

    /**
     * This field stores the GameComponentTextUtility to be utilized by this component.
     */
    protected GameComponentTextUtility textUtility;

    /**
     * This field stores the GameComponentStatusUtility to be utilized by this component.
     */
    protected GameComponentStatusUtility statusUtility;

    /**
     * This constructor is used to initialize and set up this component.
     *
     * @param name The string name of this component.
     *
     * @throws IllegalArgumentException Thrown if the provided name cannot be validated.
     */
    public BaseComponent( String name )
        throws IllegalArgumentException
    {
        this.setName( name );
        this.textUtility = new ComponentTextUtility( this );
        this.statusUtility = new ComponentStatusUtility( this );
        this.register();
    }

    /**
     * This method is used to get the unique component name for this GameComponent object.
     *
     * @return The string name for this GameComponent.
     */
    public String getName()
    {
        if ( this.name == null )
        {
            return "";
        }
        return this.name;
    }

    /**
     * This method is used to set the name for this GameComponent. It should validate the name using the
     * ComponentValidator.validateComponentName() method. GameCommands should be validated using the
     * ComponentValidator.validateCommandName() method instead.
     *
     * @param newName The name to be set for this component.
     *
     * @throws IllegalArgumentException
     */
    public void setName( String newName )
        throws IllegalArgumentException
    {
        ValidationCode validationCode;

        if ( this instanceof GameCommand )
        {
            validationCode = ComponentValidator.validateCommandName( newName );
        }
        else
        {
            validationCode = ComponentValidator.validateComponentName( newName );
        }
        if ( validationCode != ValidationCode.VALIDATION_PASSED )
        {
            switch ( validationCode )
            {
            case INVALID_NAME_LENGTH:
                {
                    throw new IllegalArgumentException( "The name " + name + " needs to be between 1 & " + ComponentValidator.MAX_COMPONENT_NAME_LENGTH + " characters long." );
                }
            case INVALID_WHITEPACE:
                {
                    throw new IllegalArgumentException( "The name " + name +
                                                        " is not allowed to contain whitespace characters." );
                }
            case NAME_DUPLICATION:
                {
                    throw new IllegalArgumentException( "The name " + name +
                                                        " already exists within the master component list." );
                }
            case NAME_DUPLICATES_HOTKEY:
                {
                    throw new IllegalArgumentException( "The name " + name +
                                                        " duplicates an exiting hotkey within the master component list." );
                }
            case NAME_DUPLICATES_ALIAS:
                {
                    throw new IllegalArgumentException( "The name " + name +
                                                        " duplicates an exiting alias within the master component list." );
                }
            }
        }
        this.name = newName;
    }

    /**
     * This method is used to retrieve the value stored for a text string with a given name.
     *
     * @param textName The string name of the text to be retrieved.
     * @return The string of text with the given name.
     */
    public String getText( String textName )
    {
        return this.textUtility.getText( textName );
    }

    /**
     * This method is used to set the status with the given name to the given value.
     *
     * @param textName The name of the text string to be set.
     * @param newText The text string to be stored with the given name.
     */
    public void setText( String textName, String newText )
    {
        this.textUtility.setText( textName, newText );
    }

    /**
     * This method will check for the presence of a text string being stored with a given name.
     *
     * @param textName The string name of the text to search for.
     * @return True if there is a stored text string with the given name, false if not.
     */
    public boolean hasText( String textName )
    {
        return this.textUtility.hasText( textName );
    }

    /**
     * This method will completely remove the given text name from the text list.
     *
     * @param textName The name of the text to be removed.
     */
    public void removeText( String textName )
    {
        this.textUtility.removeText( textName );
    }

    /**
     * This method will check if there is a value greater than 0 for the given status name.
     *
     * @param statusName The string name of the status to check.
     * @return True if the given staus name has a value greater than 0, false otherwise.
     */
    public boolean hasStatus( String statusName )
    {
        return this.statusUtility.hasStatus( statusName );
    }

    /**
     * This method will take the current value stored for a given status name and increment it by 1. If there is no value
     * stored for the given status name, the value of 1 is set.
     *
     * @param statusName The string name of the status to be incremented.
     */
    public void incrementStatus( String statusName )
    {
        this.statusUtility.incrementStatus( statusName );
    }

    /**
     * This method will completely remove the given status name from the status list.
     *
     * @param statusName The name of the status to be removed.
     */
    public void removeStatus( String statusName )
    {
        this.statusUtility.removeStatus( statusName );
    }

    /**
     * This method will retrieve the value stored for this component for a given status name.
     *
     * @param statusName The string name of the status to check.
     * @return The integer value stored for the given status or a 0 if not found.
     */
    public int getStatus( String statusName )
    {
        return this.statusUtility.getStatus( statusName );
    }

    /**
     * This method is used to set the status with the given name to the given value.
     *
     * @param statusName The name of the status to be set.
     * @param newStatus The value to be set for the given status name.
     */
    public void setStatus( String statusName, int newStatus )
    {
        this.statusUtility.setStatus( statusName, newStatus );
    }

    /**
     * This method is used to add this GameComponent to the Engine class master list by calling Engine.addItem( this ).
     */
    public void register()
    {
        Engine.getComponentUtility().addComponent( this );
    }

    /**
     * This method is used to assign a new GameComponentStatusUtility to this GameComponent.
     *
     * @param newStatusUtility The new GameComponentStatusUtility object to assign to this GameComponent.
     */
    public void setStatusUtility( GameComponentStatusUtility newStatusUtility )
    {
        this.statusUtility = newStatusUtility;
    }

    /**
     * This method is used to assign a new GameComponentTextUtility to this GameComponent.
     *
     * @param newTextUtility the new GameComponentTextUtility to assign to this GameComponent.
     */
    public void setTextUtility( GameComponentTextUtility newTextUtility )
    {
        this.textUtility = newTextUtility;
    }

    /**
     * This method is used to get the string representation of this component.
     *
     * @return the String name of this component.
     */
    @Override
    public String toString()
    {
        return this.getName();
    }
}
