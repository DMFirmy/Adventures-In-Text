package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition tests that an exit for a given GameDirection is present in the given GameLocation.
 */
public class LocationHasExitInDirection
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-8876774849889236340" )
    private static final long serialVersionUID = 1L;

    private GameLocation location;

    private GameDirection direction;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param theLocation The GameLocation to be checked.
     * @param theDirection The GameDirection to check for an exit.
     */
    public LocationHasExitInDirection( String name, GameLocation theLocation, GameDirection theDirection )
    {
        super( name );
        this.location = theLocation;
        this.direction = theDirection;
    }

    /**
     * Test for the presence of an exit in the given GameDirection in the given GameLocation.
     * 
     * @return True if there is an exit in the given GameDirection, false if there is not one.
     */
    public boolean checkCondition()
    {
        if ( this.location.hasExitDirection( this.direction ) )
        {
            return true;
        }
        return false;
    }
}
