package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will check whether the given GameComponent has a status value for a given string status name
 * that falls within the range of a given minimum and maximum value, inclusively.
 */
public class ComponentStatusInRange
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:5933439810301558973" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;

    private String statusName;

    private int min;

    private int max;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theComponent The GameComponent to be checked.
     * @param theStatusName The name of the status to check for.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     */
    public ComponentStatusInRange( String name, GameComponent theComponent, String theStatusName, int theMin,
                                   int theMax )
    {
        super( name );

        this.component = theComponent;
        this.statusName = theStatusName;
        this.min = theMin;
        this.max = theMax;
    }

    /**
     * Checks if the GameComponent has the named status with a value that is equal to or greater than the minimum
     * and less than or equal to the maximum.
     *
     * @return True if the GameComponent has the status within the given range, false if it doesn't.
     */
    public boolean checkCondition()
    {
        int theStatusValue = this.component.getStatus( this.statusName );

        if ( theStatusValue >= this.min && theStatusValue <= this.max )
        {
            return true;
        }
        return false;
    }
}
