package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition checks whether a given GameHideable object is currently not marked as hidden.
 */
public class GameHideableIsNotHidden
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:6073386400277842309" )
    private static final long serialVersionUID = 1L;

    private GameHideable gameHideable;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param theHideable The GameHidable object to check for the hidden status.
     */
    public GameHideableIsNotHidden( String name, GameHideable theHideable )
    {
        super( name );
        this.gameHideable = theHideable;
    }

    /**
     * Checks whether the GameHidable is hidden.
     * 
     * @return True if the hideable is not hidden, false if it is.
     */
    public boolean checkCondition()
    {
        if ( !this.gameHideable.isHidden() )
        {
            return true;
        }
        return false;
    }
}
