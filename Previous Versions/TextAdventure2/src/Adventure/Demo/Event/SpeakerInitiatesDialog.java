package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will cause a stored GameSpeaker object to initiate thier initial dialog.
 */
public class SpeakerInitiatesDialog
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:-3154736862307431253" )
    private static final long serialVersionUID = 1L;

    private GameSpeaker speaker;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param theSpeaker The gameSpeaker object who should speak.
     */
    public SpeakerInitiatesDialog( String name, GameSpeaker theSpeaker )
    {
        super( name );
        this.speaker = theSpeaker;
    }

    /**
     * This will call the GameSpeaker's initiateDialog() method.
     * 
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        speaker.initiateDialog();
    }
}
