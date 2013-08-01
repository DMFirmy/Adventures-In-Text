package Adventure.Core.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This command is used to initiate a conversation with another speaker.
 */
public class Speak
    extends BaseAction
{
    @SuppressWarnings( "compatibility:-3207890700734152872" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Speak command.
     */
    public Speak()
    {
        super( "Speak", "Begin a conversation.", "p" );
    }

    /**
     * When run, this method will cause the target to initiate their initial dialog.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        String[] commands = operation.getinputArray();
        GamePlayer player = Engine.getCurrentPlayer();
        boolean spoken = false;
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameSpeaker && component.getName().equalsIgnoreCase( commands[ 2 ] ) &&
                 player.getLocation().hasSpeaker( commands[ 2 ], false ) )
            {
                GameSpeaker speaker = ( GameSpeaker ) component;
                speaker.initiateDialog();
                spoken = true;
                break;
            }
        }
        if ( !spoken )
        {
            IO.add( "There is no " + commands[ 2 ] + " to speak to here." );
        }
    }
}
