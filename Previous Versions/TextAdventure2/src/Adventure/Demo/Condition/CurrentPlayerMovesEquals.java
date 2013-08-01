package Adventure.Demo.Condition;

import Adventure.*;

import Adventure.Base.*;

/**
 * This condition will test to see of the current player has moved exactly a given number of times.
 */
public class CurrentPlayerMovesEquals
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-564295033067452880" )
    private static final long serialVersionUID = 1L;

    int numberOfMoves;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theNumberOfMoves The number of moves to be checked for.
     */
    public CurrentPlayerMovesEquals( String name, int theNumberOfMoves )
    {
        super( name );
        this.numberOfMoves = theNumberOfMoves;
    }

    /**
     * Checks if the current player has moved the given number of times.
     * 
     * @return True if the current player's move count is equal to the given number, false otherwise.
     */
    public boolean checkCondition()
    {
        if ( Engine.currentPlayer().getStatus("moves") == 0 )
        {
            return true;
        }
        return false;
    }
}
