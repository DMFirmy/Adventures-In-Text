package Adventure;

import Adventure.API.*;

/**
 * This class represents a single operation that can be performed by the game engine. It will take a line of string input
 * from the player and parse it into its component parts, which are "commandName", "quantity", "directObjectName",
 * "preposition", and "indirectObjectName". These pieces of the input string are identified and stored, then the operation
 * object attempts to retrieve the correct game components and stores a refernece to them as well. This is done to allow
 * for some ease of use when writing new commands, as the operation is passed along to each command so that these object
 * references can be used for easy access to the objects that the command will need to manipulate. Even if the components
 * cannot be found, the actual array of strings for each component part of the input string are still preserved.
 */
public class Operation
{
    private String[] inputArray;

    private GameCommand command;

    private String quantity;

    private GameObject directObject;

    private String preposition;

    private GameObject indirectObject;

    /**
     * This method will take a string of input from the player and create an operation object for it.
     *
     * @param lineInput This is the complete line of input that the player entered at the prompt.
     */
    public Operation( String lineInput )
    {
        super();

        inputArray = this.parseCommands( lineInput );

        command = findCommand( inputArray[ 0 ] );

        if ( !inputArray[ 1 ].equals( "" ) )
        {
            this.quantity = this.inputArray[ 1 ];
        }
        if ( !inputArray[ 2 ].equals( "" ) )
        {
            this.directObject = this.findObject( inputArray[ 2 ] );
        }
        if ( !inputArray[ 3 ].equals( "" ) )
        {
            this.preposition = inputArray[ 3 ];
        }
        if ( !inputArray[ 4 ].equals( "" ) )
        {
            this.indirectObject = this.findObject( inputArray[ 4 ] );
        }
    }

    // TODO: This command still nees a bit of work for finding prepositions

    private String[] parseCommands( String lineInput )
    {
        // Here we set up an array to hold the return values.
        // Format: { "commandName", "quantity", "directObjectName", "preposition", "indirectObjectName" }
        String[] commands = new String[]
            { "", "", "", "", "" };
        /*
         * In order to parse the input string into an array of commands, the first thing
         * we need to do is break apart the string into individual words that we can
         * loop through.
         */
        String[] words = lineInput.split( " " );
        // The first word is always the command, so it goes into the array
        commands[ 0 ] = words[ 0 ];
        // If there are no more words, we just return our array.
        if ( words.length == 1 )
        {
            return commands;
        }

        // If there is more than just a command name, we need to load the command object
        Object[] prepositions = new Object[ 0 ];
        if ( this.command instanceof GameAction )
        {
            GameAction action = ( GameAction ) command;
            prepositions = action.prepositionList().toArray();
        }

        int index = 1;
        String temp = words[ 1 ];
        try
        {
            Integer.parseInt( temp );
            commands[ 1 ] = words[ 1 ];
            index = 2;
        }
        catch ( Exception e )
        {
            // TODO: Implement error handling here.
        }

        // Here we initialize an index for keeping track of any prepositions.
        int preposition = -1;

        for ( int i = index; i < words.length; i++ )
        {
            if ( preposition == -1 )
            {
                for ( int j = 0; j < prepositions.length; j++ )
                {
                    if ( prepositions[ j ].equals( words[ i ] ) )
                    {
                        preposition = i;
                        commands[ 3 ] = prepositions[ j ].toString();
                    }
                }
            }
            if ( preposition == -1 || i < preposition )
            {
                commands[ 2 ] += words[ i ] + " ";
            }
            else if ( i > preposition )
            {
                commands[ 4 ] += words[ i ] + " ";
            }
        }
        for ( int j = 0; j < commands.length; j++ )
        {
            commands[ j ] = commands[ j ].trim();
        }
        return commands;
    }

    private GameCommand findCommand( String commandString )
    {
        for ( GameCommand command : Engine.commandList() )
        {
            if ( command.getHotkey().equalsIgnoreCase( commandString ) ||
                 command.getName().equalsIgnoreCase( commandString ) )
            {
                return command;
            }
            if ( command instanceof GameAction )
            {
                GameAction action = ( GameAction ) command;
                for ( String alias : action.aliasList() )
                {
                    if ( alias.equalsIgnoreCase( commandString ) )
                    {
                        return action;
                    }
                }
            }
        }
        return null;
    }

    private GameObject findObject( String objectName )
    {        
        return Engine.getComponentUtility().getObject( objectName );
    }

    /**
     * This method will get the GameCommand object associated with this operation.
     *
     * @return the GameCommand object associated with this operation.
     */
    public GameCommand getCommand()
    {
        return this.command;
    }

    /**
     * If there is a quantity associated with this operation, this method will get it.
     *
     * @return The integer quantity associated with this ooperation.
     */
    public int getQuantity()
    {
        if ( !this.quantity.equals( "" ) || !this.quantity.equals( "0" ) )
        {
            return Integer.parseInt( this.quantity );
        }
        return 0;
    }

    /**
     * This will return the string value that represents the quantity associated with this operation.
     *
     * @return The string representation of the associated quantity.
     */
    public String getQuantityString()
    {
        return this.quantity;
    }

    /**
     * This method will get the GameObject object that represents the direct object for this operation.
     *
     * @return A GameObject object that represents the direct object for this operation.
     */
    public GameObject getDirectObject()
    {
        return this.directObject;
    }

    /**
     * If this operation has an associated preposition, this method can be used to get it.
     *
     * @return the String preposition associated with this operation.
     */
    public String getPreposition()
    {
        return this.preposition;
    }

    /**
     * This method will get the GameObject object that represents the indirect object for this operation.
     *
     * @return A GameObject object that represents the indirect object for this operation.
     */
    public GameObject getIndirectObject()
    {
        return this.indirectObject;
    }

    /**
     * This method will return an array of strings that represent the original line of input after being parsed
     * into its component parts of "commandName", "quantity", "directObjectName", "preposition", and "indirectObjectName".
     *
     * @return An array of String objects with 5 elements that represent each of the component parts of the
     * input string, in the order listed above.
     */
    public String[] getinputArray()
    {
        return this.inputArray;
    }

    /**
     * This is the string that was parsed from the original line of input that was determined to be a reference to a
     * direct object.
     *
     * @return The string name of the direct object from the input string.
     */
    public String getDirectObjectString()
    {
        return this.inputArray[ 2 ];
    }

    /**
     * This is the string that was parsed from the original line of input that was determined to be a reference to a
     * indirect object.
     *
     * @return The string name of the indirect object from the input string.
     */
    public String getInirectObjectString()
    {
        return this.inputArray[ 4 ];
    }

    /**
     * This method is used to determine if there is a quantity associated with this operation.
     *
     * @return True if there is a quantity associated with this operation, false otherwise.
     */
    public boolean hasQuantity()
    {
        if ( !this.quantity.equals( "" ) || !this.quantity.equals( "0" ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to determine if there is a GameObject direct object associated with this operation.
     *
     * @return True if there is a GameObject direct object associated with this operation, false otherwise.
     */
    public boolean hasDirectObject()
    {
        if ( this.directObject != null )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to determine if there is a preposition associated with this operation.
     *
     * @return True if there is a preposition associated with this operation, false otherwise.
     */
    public boolean hasPreposition()
    {
        if ( !this.preposition.equals( "" ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to determine if there is a GameObject indirect object associated with this operation.
     *
     * @return True if there is a GameObject indirect object associated with this operation, false otherwise.
     */
    public boolean hasInirectObject()
    {
        if ( this.indirectObject != null )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to determine if a string is being stroed in the inputArray as a direct object.
     *
     * @return True if inputArray[ 2 ] is not a blank string, false if it is.
     */
    public boolean hasDirectObjectString()
    {
        if ( this.inputArray[ 2 ].equals( "" ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to determine if a string is being stroed in the inputArray as an indirect object.
     *
     * @return True if inputArray[ 4 ] is not a blank string, false if it is.
     */
    public boolean hasInirectObjectString()
    {
        if ( this.inputArray[ 4 ].equals( "" ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will run the command associated with this operation.
     */
    public void run()
    {
        if ( this.command == null )
        {
            IO.addLine( "The command you have entered is not valid.\n" );
        }
        else
        {
            this.command.run( this );
        }
    }
}
