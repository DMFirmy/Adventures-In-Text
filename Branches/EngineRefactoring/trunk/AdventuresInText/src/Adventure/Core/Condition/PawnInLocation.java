package Adventure.Core.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will test whether the given GamePawn is in the given GameLocation.
 */
public class PawnInLocation
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-4618134776176887378" )
    private static final long serialVersionUID = 1L;

    private GamePawn pawn;

    private GameLocation location;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param thePawn The GamePawn object to check for.
     * @param theLocation The gameLocation object to check.
     */
    public PawnInLocation( String name, GamePawn thePawn, GameLocation theLocation )
    {
        // There is a chance of name length errors here
        super( name );
        this.pawn = thePawn;
        this.location = theLocation;
    }

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param thePawn The GamePawn object to check for.
     * @param theLocation The gameLocation object to check.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public PawnInLocation( String name, GamePawn thePawn, GameLocation theLocation, boolean negated )
    {
        // There is a chance of name length errors here
        super( name, negated );
        this.pawn = thePawn;
        this.location = theLocation;
    }
    
    /**
     * Tests whether the given GamePawn is in the given GameLocation.
     * 
     * @return True if the GamePawn is in the given GameLocation, false otherwise.s
     */
    public boolean runConditionCheck()
    {
        if ( this.pawn.getLocation().equals( location ) )
        {
            return true;
        }
        return false;
    }
}
