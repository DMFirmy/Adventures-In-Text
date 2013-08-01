package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will assign the given value to the status with the given name for the given GameComponent.
 */
public class AssignStatusToComponent
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:-6716913795570280752" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;
    private String statusName;
    private int statusValue;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param theComponent The GameComponent to det the status upon.
     * @param theStatus The name of the status to be assigned.
     * @param theStatusValue The value of the status to be assigned.
     */
    public AssignStatusToComponent( String name, GameComponent theComponent, String theStatus, int theStatusValue )
    {
        super( name );
        this.component = theComponent;
        this.statusName = theStatus;
        this.statusValue = theStatusValue;
    }

    /**
     * This event will set the given staus to the given value for the GameComponent.
     * 
     * @param operation Not used for this event.
     */
    public void processEvent( Operation operation )
    {
        this.component.setStatus( this.statusName, this.statusValue );
    }
}
