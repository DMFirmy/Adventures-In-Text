package Adventure.Core.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition checks whether a given GameHideable object is currently not marked as hidden.
 */
public class HideableIsHidden
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-8942528472891242366" )
    private static final long serialVersionUID = 1L;

    private GameHideable gameHideable;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param theHideable The GameHidable object to check for the hidden status.
     */
    public HideableIsHidden( String name, GameHideable theHideable )
    {
        super( name );
        this.gameHideable = theHideable;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theHideable The GameHidable object to check for the hidden status.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public HideableIsHidden( String name, GameHideable theHideable, boolean negated )
    {
        super( name, negated );
        this.gameHideable = theHideable;
    }
    
    /**
     * Checks whether the GameHidable is hidden.
     * 
     * @return True if the hideable is not hidden, false if it is.
     */
    public boolean runConditionCheck()
    {
        if ( this.gameHideable.isHidden() )
        {
            return true;
        }
        return false;
    }
}
