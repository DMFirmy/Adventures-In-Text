package Adventure.Core.Condition;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will compare the number of moves for the given player, or the current player if none is given,
 * to see if it falls within the range of a given minimum and maximum value, inclusively.
 */
public class PlayerMovesInRange
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-8297730886323837370" )
    private static final long serialVersionUID = 1L;

    private GamePlayer player;

    private int min;

    private int max;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     */
    public PlayerMovesInRange( String name, int theMin, int theMax )
    {
        super( name );

        this.player = null;
        this.min = theMin;
        this.max = theMax;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public PlayerMovesInRange( String name, int theMin, int theMax, boolean negated )
    {
        super( name, negated );

        this.player = null;
        this.min = theMin;
        this.max = theMax;
    }
    
    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param thePlayer The GameComponent to be checked.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     */
    public PlayerMovesInRange( String name, GamePlayer thePlayer, int theMin, int theMax )
    {
        super( name );

        this.player = thePlayer;
        this.min = theMin;
        this.max = theMax;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param thePlayer The GameComponent to be checked.
     * @param theMin The minimum range value to check for.
     * @param theMax The maximum range value to check for.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public PlayerMovesInRange( String name, GamePlayer thePlayer, int theMin, int theMax, boolean negated )
    {
        super( name, negated );

        this.player = thePlayer;
        this.min = theMin;
        this.max = theMax;
    }
    
    /**
     * Compares the number of moves for the given player (or the current player if none is given) with the given
     * value to determine if it is equal to or greater than the minimum and less than or equal to the maximum.
     *
     * @return True if the GameComponent has the status within the given range, false if it doesn't.
     */
    public boolean runConditionCheck()
    {
        // Here we create a temporary variable to store our GamePlayer object.
        GamePlayer thePlayer;
        
        // If no GamePlayer object is being stored, we will store the current player in our temporary variable.
        if ( this.player == null )
        {
            thePlayer = Engine.getCurrentPlayer();
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
        
        // Now we can check if the number of moves is within the given range.
        if ( moves >= this.min && moves <= this.max )
        {
            return true;
        }
        return false;
    }
}
