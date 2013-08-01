package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * When triggered, this event will change the description for a given GameComponent to the given new description string.
 */
public class ChangeComponentDescription
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:3963738657375233250" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;
    
    private String newDescription;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param theComponent The GameComponent to have its description changed.
     * @param theNewDescription The new string description to describe to the GameLocation.
     */
    public ChangeComponentDescription( String name,  GameComponent theComponent,
                                      String theNewDescription )
    {
        super( name );
        this.component = theComponent;
        this.newDescription = theNewDescription;
    }

    /**
     * This event will set the description of the GameComponent to the stored description string.
     * 
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        component.setText( "description", this.newDescription );
        component.incrementStatus( "description changed" );
    }
}
