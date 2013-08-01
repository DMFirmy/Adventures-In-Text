package Adventure.Core.Validation;

import Adventure.*;

import Adventure.API.*;

/**
 * This class is responsible for validating the names of every GameComponent object before it registers with
 * the Engine class to make sure it is unique and valid. It makes extensive use of the ValidationCode enum.
 */
public class ComponentValidator
{
    public static final int MAX_COMPONENT_NAME_LENGTH = 20;
    public static final int MAX_HOTKEY_LENGTH = 3;
    
    /**
     * This helper method checks a string for instances of whitespace characters. Whitespace characters are not valid in
     * the names of any GameCommand implementations or in any hotkeys. GameAction aliases are also not allowed to contain
     * whitespace characters since they act as commands. Any GameComponents that are not GameCommand implementations are
     * allowed to contain whitespace characters.
     *
     * @param string The string to be checked.
     * @return True if no white-space characters are found, false if they are.
     */
    private static boolean containsNoWhitespace( String string )
    {
        for ( char character : string.toCharArray() )
        {
            if ( Character.isWhitespace( character ) )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This helper method is used to make sure the given string length is between 1 and a given maximum number
     * of characters long.
     *
     * @param string The string to be checked.
     * @param max The maximum allowed length for the string.
     * @return True if the string is in the given range, false if it is not.
     */
    private static boolean lengthInRange( String string, int max )
    {
        if ( string.length() < 1 || string.length() > max )
        {
            return false;
        }
        return true;
    }

    /**
     * This helper method will take a given string and attempt to match it case-insensitively to the name
     * of a given GameComponent object.
     *
     * @param string the string to be checked.
     * @param component The GameComponent object to compare against.
     * @return True if the string and the component name match, false if they do not.
     */
    private static boolean stringMatchesName( String string, GameComponent component )
    {
        if ( component.getName().equalsIgnoreCase( string ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This helper method will take a given string and attempt to match it case-insensitively to the hotkey
     * assigned to a given GameCommand object.
     *
     * @param string the string to be checked.
     * @param command The GameCommand object to compare against.
     * @return True if the string and the command hotkey match, false if they do not.
     */
    private static boolean stringMatchesHotkey( String string, GameCommand command )
    {
        if ( command.getHotkey().equalsIgnoreCase( string ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This helper method will take a given string and attempt to match it case-insensitively to each
     * alias associated with a given GameAction object.
     * 
     * @param string the string to be checked.
     * @param action The GameAction object to compare against.
     * @return True if the string matches any of the actions aliases, false if it doesn't.
     */
    private static boolean stringMatchesAlias( String string, GameAction action )
    {
        if ( action.aliasList() != null )
        {
            for ( String alias : action.aliasList() )
            {
                if ( alias.equalsIgnoreCase( string ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method validates the length and uniqueness of a component name or an action alias.
     * 
     * @param componentName The string name to be validated.
     * @return The ValidationCode for the validation of the component name.
     */
    public static ValidationCode validateComponentName( String componentName )
    {
        // First we will check the hotkey for a valid length
        if ( !lengthInRange( componentName, MAX_COMPONENT_NAME_LENGTH ) )
        {
            return ValidationCode.INVALID_NAME_LENGTH;
        }

        // Now we loop through the Engine's component list and compare the name
        for ( GameComponent component : Engine.componentList() )
        {
            if ( stringMatchesName( componentName, component ) )
            {
                return ValidationCode.NAME_DUPLICATION;
            }

            // If the component is a GameCommand, we want to check the name against the hotkey too
            if ( component instanceof GameCommand && stringMatchesHotkey( componentName, ( GameCommand ) component ) )
            {
                return ValidationCode.NAME_DUPLICATES_HOTKEY;
            }

            // If the command is a GameAction we need to check the list of aliases
            if ( component instanceof GameAction && stringMatchesAlias( componentName, ( GameAction ) component ) )
            {
                return ValidationCode.NAME_DUPLICATES_ALIAS;
            }
        }
        return ValidationCode.VALIDATION_PASSED;
    }

     /**
      * This method validates the length and uniqueness of a command name, and makes sure it contains no
      * white-space characters.
      * 
      * @param commandName The string name to be validated.
      * @return The ValidationCode for the validation of the command name.
      */
    public static ValidationCode validateCommandName( String commandName )
    {
        // First we will check the hotkey for a valid length
        if ( !lengthInRange( commandName, MAX_COMPONENT_NAME_LENGTH ) )
        {
            return ValidationCode.INVALID_NAME_LENGTH;
        }

        // Command names are not allowed to have any whitepace in them
        if ( !containsNoWhitespace( commandName ) )
        {
            return ValidationCode.INVALID_WHITEPACE;

        }

        // Now we loop through the Engine's component list and compare the name
        for ( GameComponent component : Engine.componentList() )
        {
            if ( stringMatchesName( commandName, component ) )
            {
                return ValidationCode.NAME_DUPLICATION;
            }

            // If the component is a GameCommand, we want to check the name against the hotkey too
            if ( component instanceof GameCommand && stringMatchesHotkey( commandName, ( GameCommand ) component ) )
            {
                return ValidationCode.NAME_DUPLICATES_HOTKEY;
            }

            // If the command is a GameAction we need to check the list of aliases
            if ( component instanceof GameAction && stringMatchesAlias( commandName, ( GameAction ) component ) )
            {
                return ValidationCode.NAME_DUPLICATES_ALIAS;
            }
        }
        return ValidationCode.VALIDATION_PASSED;
    }

     /**
      * This method validates the length and uniqueness of a command hotkey or an action alias, and makes sure it
      * contains no white-space characters.
      * 
      * @param hotkey The string hotkey to be validated.
      * @return The ValidationCode for the validation of the command hotkey.
      */
    public static ValidationCode validateHotkey( String hotkey )
    {
        // First we will check the hotkey for a valid length
        if ( !lengthInRange( hotkey, MAX_HOTKEY_LENGTH ) )
        {
            return ValidationCode.INVALID_NAME_LENGTH;
        }

        // Hotkeys are not allowed to have any whitepace in them
        if ( !containsNoWhitespace( hotkey ) )
        {
            return ValidationCode.INVALID_WHITEPACE;

        }
        // Now we loop through the Engine's component list and compare the name
        for ( GameComponent component : Engine.componentList() )
        {
            if ( stringMatchesName( hotkey, component ) )
            {
                return ValidationCode.HOTKEY_DUPLICATES_NAME;
            }

            // If the component is a GameCommand, we want to check the name against the hotkey too
            if ( component instanceof GameCommand && stringMatchesHotkey( hotkey, ( GameCommand ) component ) )
            {
                return ValidationCode.HOTKEY_DUPLICATION;
            }

            // If the command is a GameAction we need to check the list of aliases
            if ( component instanceof GameAction && stringMatchesAlias( hotkey, ( GameAction ) component ) )
            {
                return ValidationCode.HOTKEY_DUPLICATES_ALIAS;
            }
        }
        // If we have made it this far, we must have a valid hotkey.
        return ValidationCode.VALIDATION_PASSED;
    }
}
