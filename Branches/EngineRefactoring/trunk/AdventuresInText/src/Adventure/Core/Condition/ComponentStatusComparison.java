package Adventure.Core.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This GameCondition implementation is used to compare the value that a given GameComponent has for the given status
 * name against another given value. The type of comparison that is performed depends on which value from the
 * GameConditionComparisonType enumeration is given to the constructor.
 */
public class ComponentStatusComparison
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-3127983677165162223" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;

    private String statusName;

    private int comparisonValue;

    private GameConditionComparisonType comparisonType;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theComponent The GameComponent to compare the status of.
     * @param theStatusName The name of the status to compare.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     */
    public ComponentStatusComparison( String name, GameComponent theComponent, String theStatusName,
                                      int comparisonValue, GameConditionComparisonType comparisonType )
    {
        super( name );

        this.component = theComponent;
        this.statusName = theStatusName;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theComponent The GameComponent to compare the status of.
     * @param theStatusName The name of the status to compare.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public ComponentStatusComparison( String name, GameComponent theComponent, String theStatusName,
                                      int comparisonValue, GameConditionComparisonType comparisonType, boolean negated )
    {
        super( name, negated );

        this.component = theComponent;
        this.statusName = theStatusName;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * Compares the value for a given GameComponent against another given value. The comparison will be of one of the
     * types defined by the StatusComparison enumeration.
     *
     * @return True if the GameComponent status wins the comparison, false if not.
     */
    public boolean runConditionCheck()
    {
        int theStatusValue = this.component.getStatus( this.statusName );
        boolean result = false;
        switch ( this.comparisonType )
        {
            case GREATER:
            {
               if(theStatusValue > this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case GREATER_OR_EQUAL:
            {
               if(theStatusValue >= this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case LESS:
            {
               if(theStatusValue < this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case LESS_OR_EQUAL:
            {
               if(theStatusValue <= this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case EQUAL:
            {
               if(theStatusValue == this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
        }
        return result;
    }
}
