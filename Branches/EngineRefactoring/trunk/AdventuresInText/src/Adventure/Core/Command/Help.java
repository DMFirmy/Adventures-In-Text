package Adventure.Core.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This command is one of the 5 required system commands, and is used to call up the in-game help system.
 */
public class Help
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:-1740843371275032751" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Help command.
     */
    public Help()
    {
        super( "Help", "Display this info.", "?" );
    }

    /**
     * When run, this method will generate the help screen and display it to the player.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        // In future updates, the help system needs to become more robust.
        String output = "";
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameCommand )
            {
                GameCommand command = ( GameCommand ) component;                
                output += String.format( "%-6s %-20s ", "Command:", command.getName() );
                output += String.format( "%-7s %-3s \n", "Hotkey:", command.getHotkey() );
                
                if ( command instanceof GameAction )
                {
                    GameAction action = ( GameAction ) command;
                    if ( action.aliasList().size() > 0 )
                    {
                        output += String.format( "%-8s ", "Aliases:" );

                        int i = 0;
                        for ( String alias : action.aliasList() )
                        {
                            i++;
                            output += String.format( "%s", alias );
                            if ( i < action.aliasList().size() )
                            {
                                output += ", ";
                            }
                            else
                            {
                                output += "\n";
                            }
                        }
                    }
                }

                output += command.getText( "help" ) + "\n\n";
            }
        }
        IO.addLine( output );
    }
}
