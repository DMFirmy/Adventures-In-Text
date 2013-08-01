package Adventure.Addon;

import Adventure.Base.Utility.*;

import Adventure.Objects.*;

/**
 * This is a demonstartion of a custom GamePlayer implementation. It has an increased max weight.
 */
public class StrongPlayer
    extends Player
{
    @SuppressWarnings( "compatibility:6641832349146444771" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor will initialize a new StrongPlayer object.
     * @param name The GameComponent name to assign to this StrongPlayer object.
     * @param description The text bdescription for this StrongPlayer object.
     */
    public StrongPlayer( String name, String description )
    {
        super( name, description );
        this.setStatus( "moves", 0 );
        this.itemUtility = new ContainerItemUtility( this, 50.0 );
        this.hideUtility = new ObjectHideUtility( this );
    }
}
