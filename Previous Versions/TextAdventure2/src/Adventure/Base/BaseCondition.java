package Adventure.Base;

import Adventure.API.*;

/**
 * This is the base implementation of the GameCondition interface.
 */
public abstract class BaseCondition
    extends BaseMechanic
    implements GameCondition
{
    @SuppressWarnings( "compatibility:8099907301261096194" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor is used to initialize and set up this condition.
     * 
     * @param name The string name of this command.
     */
    public BaseCondition( String name )
    {
        super( name );
    }

    /**
     * This method will run the associated check to test this GameCondition.
     *
     * @return True if the given condition is detemined to be true, false otherwise.
     */
    public boolean checkCondition()
    {
        return false;
    }
}
