package Adventure.Demo.Event;

import Adventure.*;

import Adventure.Base.*;

/**
 * This event, when triggered, will end the game and display a stored text string.
 */
public class TriggerEndOfGame
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:299468261852536232" )
    private static final long serialVersionUID = 1L;

    private String endText;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param endGameText The end of game output text to be displayed when this event is fired.
     */
    public TriggerEndOfGame(String name, String endGameText)
    {
        super(name);
        this.endText = endGameText;
    }

    /**
     * This event will run the end game method and display the stored ending text.
     * 
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        Engine.endGame( this.endText );
    }
}
