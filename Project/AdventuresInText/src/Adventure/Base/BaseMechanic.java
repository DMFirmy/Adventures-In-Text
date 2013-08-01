package Adventure.Base;

import Adventure.API.*;

/**
 * This is the base implementation of the GameMechanic interface.
 */
public abstract class BaseMechanic
    extends BaseComponent
    implements GameMechanic
{
    @SuppressWarnings( "compatibility:3106072096320371920" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor is used to initialize and set up this mechanic.
     *
     * @param name The string name of this mechanic.
     */
    public BaseMechanic( String name )
    {
        super( name );
    }
}
