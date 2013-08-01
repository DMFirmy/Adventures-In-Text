package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will move a given GamePawn to a given GameLocation.
 */
public class MovePawnToLocation
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:4481197120237464944" )
    private static final long serialVersionUID = 1L;

    private GamePawn pawn;

    private GameLocation location;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param thePawn The GamePawn to be moved.
     * @param theLocation the new GameLocation to move the GamePawn to.
     */
    public MovePawnToLocation( String name, GamePawn thePawn, GameLocation theLocation )
    {
        super( name );
        this.pawn = thePawn;
        this.location = theLocation;
    }

    /**
     * This event will set the GamePawn's location to the given GameLocation.
     * 
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        pawn.setLocation( location );
    }
}
