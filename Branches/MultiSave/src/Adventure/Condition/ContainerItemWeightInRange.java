package Adventure.Condition;

import Adventure.API.*;

import Adventure.Base.*;

public class ContainerItemWeightInRange
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-5916337449381355563" )
    private static final long serialVersionUID = 1L;

    private GameContainer container;

    private double min;

    private double max;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theContainer The GameComponent to be checked.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     */
    public ContainerItemWeightInRange( String name, GameContainer theContainer,  double theMin, double theMax )
    {
        super( name );

        this.container = theContainer;
        this.min = theMin;
        this.max = theMax;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theContainer The GameComponent to be checked.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public ContainerItemWeightInRange( String name, GameContainer theContainer, double theMin, double theMax, boolean negated )
    {
        super( name, negated );

        this.container = theContainer;
        this.min = theMin;
        this.max = theMax;
    }
    
    /**
     * Checks if the GameComponent has the named status with a value that is equal to or greater than the minimum
     * and less than or equal to the maximum.
     *
     * @return True if the GameComponent has the status within the given range, false if it doesn't.
     */
    public boolean runConditionCheck()
    {
        double theItemValue = this.container.containedItemWeight();

        if ( theItemValue >= this.min && theItemValue <= this.max )
        {
            return true;
        }
        return false;
    }
}
