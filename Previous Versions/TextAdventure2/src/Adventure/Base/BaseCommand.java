package Adventure.Base;

import Adventure.API.*;

import Adventure.Validation.*;

/**
 * This is the base implementation of the GameCommand interface.
 */
public abstract class BaseCommand
    extends BaseComponent
    implements GameCommand
{
    @SuppressWarnings( "compatibility:5608877955396807488" )
    private static final long serialVersionUID = 1L;

    /**
     * This filed stores the string hotkey for this command.
     */
    protected String hotkey;

    /**
     * This constructor is used to initialize and set up this command.
     * 
     * @param name The string name of this command.
     * @param helpText The text that will be shown on the help screen for this command.
     * @param hotkey The string hotkey for this command.
     * 
     * @throws IllegalArgumentException Thrown if the provided name or hotkey cannot be validated
     */
    public BaseCommand( String name, String helpText, String hotkey )
        throws IllegalArgumentException

    {
        super( name );
        this.setHotkey( hotkey );
        this.setText( "help", helpText );
    }

    /**
     * This method is used to retrieve the hotkey assigned to this GameCommand.
     *
     * @return The string hotkey for this GameCommand.
     */
    public String getHotkey()
    {
        if ( this.hotkey == null )
        {
            return "";
        }
        return hotkey;
    }

    /**
     * This method is used to assign a new hotkey to this GameCommand. It should validate the name using the
     * ComponentValidator.validateHotkey() method.
     *
     * @param newHotkey The string to be assigned as the new hotkey for this GameCommand.
     */
    public void setHotkey( String newHotkey )
    {
        ValidationCode validationCode = ComponentValidator.validateHotkey( newHotkey );
        if ( validationCode != ValidationCode.VALIDATION_PASSED )
        {
            switch ( validationCode )
            {
            case INVALID_NAME_LENGTH:
                {
                    throw new IllegalArgumentException( "The hotkey " + hotkey +
                                                        " needs to be between 1 & 3 characters long." );

                }
            case INVALID_WHITEPACE:
                {
                    throw new IllegalArgumentException( "The hotkey " + hotkey +
                                                        " cannot contain whitespace characters." );

                }
            case HOTKEY_DUPLICATES_NAME:
                {
                    throw new IllegalArgumentException( "The hotkey " + hotkey +
                                                        " already exists within the master component list." );

                }
            case HOTKEY_DUPLICATION:
                {
                    throw new IllegalArgumentException( "The hotkey " + hotkey +
                                                        " duplicates an exiting hotkey within the master component list." );

                }
            case HOTKEY_DUPLICATES_ALIAS:
                {
                    throw new IllegalArgumentException( "The hotkey " + hotkey +
                                                        " duplicates an exiting alias within the master component list." );

                }
            }
        }
        hotkey = newHotkey;
    }
}
