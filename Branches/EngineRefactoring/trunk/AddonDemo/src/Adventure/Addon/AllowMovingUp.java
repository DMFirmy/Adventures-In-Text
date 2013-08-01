package Adventure.Addon;

import Adventure.*;

import Adventure.Base.*;

/**
 * This is an example of a custom event that is run each turn. If the player is attempting to move in
 * the "up" direction it will return them to the location they came from and reset the output so the description
 * of the one in the location in the "up" direction is not displayed. This event doesn't utilize conditions to
 * prevent it from running, it simply makes the checks itself every turn.
 */
public class AllowMovingUp
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:-921559772307737414" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor will set up the AllowMovingUp event object.
     *
     * @param name The GameComponent name for this event object.
     */
    public AllowMovingUp( String name )
    {
        super( name );
    }

    /**
     * This event will check the current operation and if the command is the direction "up" it will return them
     * to their original location unless they have the "flying" status.
     * 
     * @param operation the current operation that the Processor is working with.
     */
    public void processEvent( Operation operation )
    {
        if ( operation != null )
        {
            if ( operation.getCommand().getName().equalsIgnoreCase( "up" ) )
            {
                if ( !Engine.getCurrentPlayer().hasStatus( "flying" ) )
                {
                    IO.clearBuffer();
                    Engine.getCurrentPlayer().setLocation( Engine.getCurrentPlayer().getPreviousLocation(), false );
                    IO.addLine( "Try as you might, you can't get up there without flying." );
                    Engine.getCurrentPlayer().getLocation().describe();
                }
            }
        }
    }
}
