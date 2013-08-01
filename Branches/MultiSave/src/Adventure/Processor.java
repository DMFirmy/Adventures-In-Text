package Adventure;

import Adventure.API.*;

/**
 * This class is responsible for taking a raw line of input from the player and creating the associated operation
 * object for it. It is also responsible for handling all of the event and condition checking.
 */
public class Processor
{
    static boolean enginePostProcessing;

    /**
     * Here we set the static field for enginePostProcessing to false.
     */
    static
    {
        enginePostProcessing = false;
    }

    /**
     * This class is completely static, so we make the constructor private.
     */
    private Processor()
    {
    }

    /**
     * This method will take a line of string input and turn it into an operation that it will then run. It then
     * goes through all of the regestered events and checks if thier conditions have been met, firing each off if it has.
     * 
     * @param lineInput A single line of string input data gathered by the IO class from the player.
     */
    protected static void processCommands( String lineInput )
    {
        Operation operation = new Operation( lineInput );
        operation.run();
        if ( enginePostProcessing )
        {
            enginePostProcessing = false;
        }
        else
        {
            postProcessEvents( operation );
        }
    }
    
    /**
     * This method will loop through each of the registered events, check the conditions for each, and fire
     * off any of them that have conditions that have been met.
     * 
     * @param operation The current operation that caused the events to be fired.
     */
    static void postProcessEvents( Operation operation )
    {
        for ( GameEvent event : Engine.eventList() )
        {
            boolean conditionsPassed = false;
            if ( ( event.hasEventConditions() && event.checkEventConditions() ) )
            {
                conditionsPassed = true;
            }

            if ( conditionsPassed || !event.hasEventConditions() )
            {
                event.processEvent( operation );
            }
        }
    }
}
