package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will initiate a given GameDialog. This dialog doesn't need to be associated
 * with any GameSpeaker objects.
 */
public class InitiateDialog
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:-1292525536710553650" )
    private static final long serialVersionUID = 1L;

    private GameDialog dialog;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param theDialog The GameDialog that is to be initiated.
     */
    public InitiateDialog( String name, GameDialog theDialog )
    {
        super( name );
        this.dialog = theDialog;
    }

    /**
     * This event calls the initiate() method for the Gamedialog.
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        dialog.initiate();
    }
}
