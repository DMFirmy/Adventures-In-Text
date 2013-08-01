package Adventure.Core.Condition;

import Adventure.Base.*;

/**
 * This GameCondition implementation is used to facilitate randomly ocurring events. It will return true each turn the
 * given percentage of the time.
 */
public class PercentageChance
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-8225322891210270836" )
    private static final long serialVersionUID = 1L;

    private int percentChance;

    /**
     * This constructor will build and set up the condition.
     *
     * @param percentChance The percentage chance that this event will return true.
     */
    public PercentageChance( int percentChance )
    {
        super( percentChance + "% chance" );
        this.percentChance = percentChance;
    }

    /**
     * Generates a random percentage and checks whether it is lower than a given percent chance.
     * 
     * @return True if the randomly generated is less or equal to the given percent chance, false otherwise.
     */
    public boolean runConditionCheck()
    {
        int rand = ( int ) ( Math.random() * 100 );
        if ( rand <= this.percentChance )
        {
            return true;
        }
        return false;
    }
}
