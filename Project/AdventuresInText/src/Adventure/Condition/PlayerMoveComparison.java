package Adventure.Condition;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will compare the number of moves for the given player, or the current player if none is given,
 * with the value that is given.The type of comparison that is performed depends on which value from the
 * GameConditionComparisonType enumeration is given to the constructor.
 */
public class PlayerMoveComparison
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:7453002572922984932" )
    private static final long serialVersionUID = 1L;

    private GamePlayer player;

    private double comparisonValue;

    private GameConditionComparisonType comparisonType;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     */
    public PlayerMoveComparison( String name, int comparisonValue, GameConditionComparisonType comparisonType )
    {
        super( name );
        this.player = null;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public PlayerMoveComparison( String name, int comparisonValue, GameConditionComparisonType comparisonType,
                                 boolean negated )
    {
        super( name, negated );
        this.player = null;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param player The GamePlayer to compare the moves of.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     */
    public PlayerMoveComparison( String name, GamePlayer player, int comparisonValue,
                                 GameConditionComparisonType comparisonType )
    {
        super( name );
        this.player = player;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param player The GamePlayer to compare the moves of.
     * @param comparisonValue The value to compare the status against.
     * @param comparisonType The type of comparison to be performed.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public PlayerMoveComparison( String name, GamePlayer player, int comparisonValue,
                                 GameConditionComparisonType comparisonType, boolean negated )
    {
        super( name, negated );
        this.player = player;
        this.comparisonValue = comparisonValue;
        this.comparisonType = comparisonType;
    }

    /**
     * Compares the number of moves for the given player (or the current player if none is given) 
     * with the value that is provided. The comparison will be of one of the types defined by the 
     * StatusComparison enumeration.
     *
     * @return True if the GamePlayer's moves wins the comparison, false if not.
     */
    public boolean runConditionCheck()
    {
        // Here we create a temporary variable to store our GamePlayer object.
        GamePlayer thePlayer;
        
        // If no GamePlayer object is being stored, we will store the current player in our temporary variable.
        if ( this.player == null )
        {
            thePlayer = Engine.currentPlayer();
        }
        else
        {
            // If there is a player being stored, we will use it instead.
            thePlayer = this.player;
        }
        
        // Now we initialize a variable to represent our current value for the players moves.
        int moves = 0;
        // We only want to get players moves if they actually have moved, otherwise our value of 0 is fine.
        if(thePlayer.hasStatus( "moves" ))
        {
            moves = thePlayer.getStatus( "moves" );
        }
        
        // Here we initialize a boolean variable to represent the results of our comparison.
        boolean result = false;
        
        // Now we can perform our comparison based on the type of GameConditionComparisonType specified.
        switch ( this.comparisonType )
        {
        case GREATER:
            {
                if ( moves > this.comparisonValue )
                {
                    result = true;
                }
                break;
            }
        case GREATER_OR_EQUAL:
            {
                if ( moves >= this.comparisonValue )
                {
                    result = true;
                }
                break;
            }
        case LESS:
            {
                if ( moves < this.comparisonValue )
                {
                    result = true;
                }
                break;
            }
        case LESS_OR_EQUAL:
            {
                if ( moves <= this.comparisonValue )
                {
                    result = true;
                }
                break;
            }
        case EQUAL:
            {
                if ( moves == this.comparisonValue )
                {
                    result = true;
                }
                break;
            }
        }
        // Finally we can return our result.
        return result;
    }
}
