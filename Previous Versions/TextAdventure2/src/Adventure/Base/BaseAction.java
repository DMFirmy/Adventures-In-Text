package Adventure.Base;

import Adventure.API.*;

import Adventure.Validation.*;

import java.util.*;

/**
 * This is the base implementation of the GameAction interface.
 */
public abstract class BaseAction
    extends BaseCommand
    implements GameAction
{
    @SuppressWarnings( "compatibility:8445259966529884290" )
    private static final long serialVersionUID = 1L;

    /**
     * This is a list of aliases attached to this action.
     */
    protected ArrayList<String> aliases;

    /**
     * This is a list of prepositions attached to this action.
     */
    protected ArrayList<String> prepositions;

    /**
     * This constructor is used to initialize and set up this action.
     * 
     * @param name The string name of this action.
     * @param helpText The text that will be shown on the help screen for this action.
     * @param hotkey The string hotkey for this action.
     */
    public BaseAction( String name, String helpText, String hotkey )
    {
        super( name, helpText, hotkey );

        this.aliases = new ArrayList<String>();
        this.prepositions = new ArrayList<String>();
    }

     /**
      * This method is used to add a new alias to the list of aliases for this GameAction. It should validate the name using the
      * ComponentValidator.validateCommandName() method.
      * 
      * @param alias The new string alias to be assigned to this GameAction.
      */
    public void addAlias( String alias )
        throws IllegalArgumentException
    {
        ValidationCode validationCode = ComponentValidator.validateComponentName( alias );
        if ( validationCode != ValidationCode.VALIDATION_PASSED )
        {
            switch ( validationCode )
            {
            case INVALID_NAME_LENGTH:
                {
                    throw new IllegalArgumentException( "The alias " + alias +
                                                        " needs to be between 1 & 20 characters long." );
                }
            case INVALID_WHITEPACE:
                {
                    throw new IllegalArgumentException( "The alias " + alias +
                                                        " cannot contain whitespace characters." );

                }
            case ALIAS_DUPLICATES_NAME:
                {
                    throw new IllegalArgumentException( "The alias " + alias +
                                                        " duplicates an exiting name within the master component list." );
                }
            case NAME_DUPLICATES_HOTKEY:
                {
                    throw new IllegalArgumentException( "The alias " + alias +
                                                        " duplicates an exiting hotkey within the master component list." );
                }
            case NAME_DUPLICATES_ALIAS:
                {
                    throw new IllegalArgumentException( "The alias " + alias +
                                                        " already exists within the master component list." );
                }
            }
        }
        this.aliases.add( alias );
    }

     /**
      * This method is used to add a new preposition to the list of valid prepositions for this GameAction.
      * 
      * @param preposition The string preposition to be added to the list.
      */
    public void addPreposition( String preposition )
    {
        this.prepositions.add( preposition );
    }

     /**
      * This method is used to retrieve a copy of the list of valid prepositions for this GameAction.
      * 
      * @return A list of valid prepositions for this GameAction.
      */
    public ArrayList<String> aliasList()
    {
        return aliases;
    }

     /**
      * This method is used to determine if the given string alias is associated with this GameAction.
      * 
      * @param alias The string alias name to check for.
      * @return True if the alias is in the list for this GameAction, false if it is not.
      */
    public boolean hasAlias( String alias )
    {
        for ( String listAlias : this.aliasList() )
        {
            if ( listAlias.equalsIgnoreCase( alias ) )
            {
                return true;
            }
        }
        return false;
    }

     /**
      * This method is used to determine if the given preposition is in the list of valid prepositions for
      * this GameAction.
      * 
      * @param preposition The string preposition to check for.
      * @return True if the preposition is found in the list, false if it is not.
      */
    public boolean hasPreposition( String preposition )
    {
        for ( String listPreposition : this.prepositionList() )
        {
            if ( listPreposition.equalsIgnoreCase( preposition ) )
            {
                return true;
            }
        }
        return false;
    }

     /**
      * This method will get a copy of the list of valid prepositions for this GameAction.
      * 
      * @return A copy of the list of valid prepositions for this GameAction.
      */
    public ArrayList<String> prepositionList()
    {
        return prepositions;
    }

     /**
      * This method is used to remove an alias associated with this GameAction.
      * 
      * @param alias The string alias to be removed.
      */
    public void removeAlias( String alias )
    {
        for ( String listAlias : this.aliasList() )
        {
            if ( listAlias.equalsIgnoreCase( alias ) )
            {
                this.aliases.remove( alias );
                return;
            }
        }
    }

     /**
      * This method is used to remove a preposition from the list of known prepositions for this GameAction.
      * 
      * @param preposition The string preposition to be removed from the list.
      */
    public void removePreposition( String preposition )
    {
        for ( String listPreposition : this.prepositionList() )
        {
            if ( listPreposition.equalsIgnoreCase( preposition ) )
            {
                this.prepositions.remove( preposition );
                return;
            }
        }
    }
}
