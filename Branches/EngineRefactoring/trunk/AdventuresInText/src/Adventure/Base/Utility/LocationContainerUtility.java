package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

/**
 */
public class LocationContainerUtility
    implements GameLocationContainerUtility
{
    @SuppressWarnings( "compatibility:-5501862799058704277" )
    private static final long serialVersionUID = 1L;

    /**
     */
    protected GameLocation location;

    /**
     * @param location
     */
    public LocationContainerUtility( GameLocation location )
    {
        super();
        this.location = location;
    }

    /**
     * @param container
     * @return
     */
    public boolean hasContainer( GameContainer container )
    {
        return this.hasContainer( container, false );
    }

    /**
     * @param container
     * @param includeHidden
     * @return
     */
    public boolean hasContainer( GameContainer container, boolean includeHidden )
    {
        if ( location.itemList().contains( container ) )
        {
            GameObject containerObject = ( GameObject ) container;
            if ( includeHidden || !containerObject.hasStatus( "hidden" ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param containerName
     * @return
     */
    public boolean hasContainer( String containerName )
    {
        return this.hasContainer( containerName, false );
    }

    /**
     * @param containerName
     * @param includeHidden
     * @return
     */
    public boolean hasContainer( String containerName, boolean includeHidden )
    {
        for ( GamePawn pawn : location.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( containerName ) && pawn instanceof GameContainer )
            {
                return this.hasContainer( (GameContainer) pawn, includeHidden );
            }
        }
        for ( GameItem item : location.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( containerName ) && item instanceof GameContainer )
            {
                if ( includeHidden || !item.hasStatus( "hidden" ) )
                {
                    return this.hasContainer( ( GameContainer ) item, includeHidden );
                }
            }
        }
        for ( GameExit exit : location.exitList() )
        {
            if ( exit.getName().equalsIgnoreCase( containerName ) && exit instanceof GameContainer )
            {
                if ( includeHidden || !exit.hasStatus( "hidden" ) )
                {
                    return this.hasContainer( ( GameContainer ) exit, includeHidden );
                }
            }
        }
        return false;
    }

    /**
     * @param newLocation
     */
    public void setLocationComponent( GameLocation newLocation )
    {
        this.location = newLocation;
    }

    /**
     * @param locationName
     */
    public void setLocationComponent( String locationName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameLocation && component.getName().equalsIgnoreCase( locationName ) )
            {
                setLocationComponent( ( GameLocation ) component );
            }
        }
    }
}
