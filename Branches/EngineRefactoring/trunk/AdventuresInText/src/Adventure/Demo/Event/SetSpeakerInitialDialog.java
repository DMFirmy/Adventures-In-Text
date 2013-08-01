package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will change the given GameSpeaker's initial dialog to the given GameDialog.
 */
public class SetSpeakerInitialDialog
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:1259765807744114848" )
    private static final long serialVersionUID = 1L;

    private GameSpeaker speaker;
    
    private GameDialog initialDialog;
    
    /**
     * This constructor will build and set up the event.
     *
     * @param name The GameComponent name to be assigned to this event.
     * @param theSpeaker The GameSpeaker who's initial dialog is to be changed.
     * @param theInitialDialog The new GameDialog to assign to the pawn as the initial dialog.
     */
    public SetSpeakerInitialDialog( String name, GameSpeaker theSpeaker, GameDialog theInitialDialog )
    {
        super( name );
        this.speaker = theSpeaker;
        this.initialDialog = theInitialDialog;
    }

    /**
     * This event will change the given pawn's initial dialog to the given GameDialog.
     * 
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        this.speaker.setInitialDialog( this.initialDialog );
        ((GameComponent) this.speaker).incrementStatus( "dialog changed" );
    }
}
