package Adventure.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This command is used to get a description of something and to display it to the screen. by default, it describes
 * the player's current location. when a target isn't specified.
 */
public class Look
    extends BaseAction
{
    @SuppressWarnings( "compatibility:-6236385582778700779" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Look command.
     */
    public Look()
    {
        super( "Look", "Describe your current location.", "k" );
        this.addPreposition( "at" );
    }

    /**
     * When run, this method will display the description for the target, which by default it the players current location.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        String[] commands = operation.getinputArray();
        GamePlayer player = Engine.currentPlayer();
        boolean described = false;
        if ( commands[ 2 ].equals( "" ) )
        {
            player.getLocation().revealHidden();
            player.getLocation().describe();
            described = true;
        }
        else
        {
            for ( GameComponent component : Engine.componentList() )
            {
                if ( component instanceof GamePawn && component.getName().equalsIgnoreCase( commands[ 2 ] ) &&
                     player.getLocation().hasPawn( commands[ 2 ], false ) )
                {
                    GameObject object = ( GameObject ) component;
                    object.describe();
                    described = true;
                    break;
                }
                if ( component instanceof GameContainer && player.getLocation().hasContainer( commands[ 2 ] ) )
                {
                    GameContainer container = ( GameContainer ) component;
                    if ( container.hasItem( commands[ 2 ] ) )
                    {
                        container.getItem( commands[ 2 ] ).describe();
                        described = true;
                        break;
                    }
                }
            }
        }

        if ( !described )
        {
            IO.addLine( "There is no " + commands[ 2 ] + " to examine." );
        }
    }
}
