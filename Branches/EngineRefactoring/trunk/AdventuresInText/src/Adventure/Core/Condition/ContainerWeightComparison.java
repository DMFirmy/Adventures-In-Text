package Adventure.Core.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will compare the total item weight of the given container with the value that is given.The type of comparison
 * that is performed depends on which value from the GameConditionComparisonType enumeration is given to the constructor.
 */
public class ContainerWeightComparison
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:2436646804165485769" )
    private static final long serialVersionUID = 1L;

    private GameContainer container;

    private double comparisonValue;

    private GameConditionComparisonType comparisonType;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theContainer The GameContainer to compare the weight of.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     */
    public ContainerWeightComparison( String name, GameContainer theContainer, int comparisonValue,
                                      GameConditionComparisonType comparisonType )
    {
        super( name );

        this.container = theContainer;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theContainer The GameContainer to compare the weight of.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public ContainerWeightComparison( String name, GameContainer theContainer, int comparisonValue,
                                          GameConditionComparisonType comparisonType, boolean negated )
    {
        super( name, negated );

        this.container = theContainer;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * Compares the value for a given GameComponent against another given value. The comparison will be of one of the
     * types defined by the StatusComparison enumeration.
     *
     * @return True if the GameContainer weight wins the comparison, false if not.
     */
    public boolean runConditionCheck()
    {
        double theContainerWeight = this.container.containedItemWeight();
        boolean result = false;
        switch ( this.comparisonType )
        {
            case GREATER:
            {
               if(theContainerWeight > this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case GREATER_OR_EQUAL:
            {
               if(theContainerWeight >= this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case LESS:
            {
               if(theContainerWeight < this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case LESS_OR_EQUAL:
            {
               if(theContainerWeight <= this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
            case EQUAL:
            {
               if(theContainerWeight == this.comparisonValue)
               {
                   result = true;
               }
               break;
            }
        }
        return result;
    }
}
