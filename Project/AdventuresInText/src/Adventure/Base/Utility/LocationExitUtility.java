package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Objects.*;

import java.util.*;

/**
 */
public class LocationExitUtility
    implements GameLocationExitUtility
{
    @SuppressWarnings( "compatibility:8270400961333027799" )
    private static final long serialVersionUID = 1L;

    /**
     */
    protected ArrayList<GameExit> exitList;

    /**
     */
    protected GameLocation location;

    /**
     * @param location
     */
    public LocationExitUtility( GameLocation location )
    {
        super();
        this.location = location;
        this.exitList = new ArrayList<GameExit>();
    }

    /**
     * @return
     */
    public ArrayList<GameExit> exitList()
    {
        return ( ArrayList<GameExit> ) this.exitList.clone();
    }

    /**
     * @param direction
     * @param location
     * @return
     */
    public boolean addExit( GameDirection direction, GameLocation location )
    {
        if ( this.hasExitDirection( direction ) )
        {
            return false;
        }
        this.exitList.add( new Exit( direction, location ) );
        return true;
    }

    /**
     * @param newExit
     * @return
     */
    public boolean addExit( GameExit newExit )
    {
        if ( this.hasExitDirection( newExit.getExitDirection() ) )
        {
            return false;
        }
        this.exitList.add( newExit );
        return true;
    }

    /**
     * @param exitName
     * @return
     */
    public boolean addExit( String exitName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameExit && component.getName().equalsIgnoreCase( exitName ) )
            {
                return this.addExit( ( GameExit ) component );
            }
        }
        return false;
    }

    /**
     * @param directionName
     * @param locationName
     * @return
     */
    public boolean addExit( String directionName, String locationName )
    {
        if ( this.hasExitDirection( directionName, true ) )
        {
            return false;
        }

        GameDirection direction = null;
        GameLocation location = null;

        for ( GameComponent component : Engine.componentList() )
        {

            if ( component.getName().equalsIgnoreCase( directionName ) ||
                 component.getName().equalsIgnoreCase( locationName ) )
            {
                if ( component instanceof GameDirection && direction == null )
                {
                    direction = ( GameDirection ) component;
                }
                else if ( component instanceof GameLocation && location == null )
                {
                    location = ( GameLocation ) component;
                }
            }
        }
        if ( direction == null || location == null )
        {
            return false;
        }
        return this.addExit( direction, location );
    }

    /**
     * @param exitName
     * @return
     */
    public GameExit getExit( String exitName )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getName().equalsIgnoreCase( exitName ) )
            {
                return exit;
            }
        }
        return null;
    }

    /**
     * @param directionName
     * @return
     */
    public GameExit getExitByDirection( String directionName )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getExitDirection().getName().equalsIgnoreCase( directionName ) )
            {
                return exit;
            }
        }
        return null;
    }

    /**
     * @param exitName
     * @return
     */
    public boolean hasExit( String exitName )
    {
        return this.hasExit( exitName, false );
    }

    /**
     * @param exitName
     * @param includeHidden
     * @return
     */
    public boolean hasExit( String exitName, boolean includeHidden )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getName().equalsIgnoreCase( exitName ) )
            {
                if ( includeHidden || !exit.isHidden() )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param exit
     * @return
     */
    public boolean hasExit( GameExit exit )
    {
        return this.hasExit( exit.getName(), false );
    }

    /**
     * @param exit
     * @param includeHidden
     * @return
     */
    public boolean hasExit( GameExit exit, boolean includeHidden )
    {
        return this.hasExit( exit.getName(), includeHidden );
    }

    /**
     * @param direction
     * @return
     */
    public boolean hasExitDirection( GameDirection direction )
    {
        return hasExitDirection( direction.getName(), false );
    }

    /**
     * @param direction
     * @param includeHidden
     * @return
     */
    public boolean hasExitDirection( GameDirection direction, boolean includeHidden )
    {
        return hasExitDirection( direction.getName(), includeHidden );
    }

    /**
     * @param directionName
     * @return
     */
    public boolean hasExitDirection( String directionName )
    {
        return hasExitDirection( directionName, false );
    }

    /**
     * @param directionName
     * @param includeHidden
     * @return
     */
    public boolean hasExitDirection( String directionName, boolean includeHidden )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getExitDirection().getName().equalsIgnoreCase( directionName ) )
            {
                if ( includeHidden || !exit.hasStatus( "hidden" ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param location
     * @return
     */
    public boolean hasExitLocation( GameLocation location )
    {
        return hasExitLocation( location, false );
    }

    /**
     * @param location
     * @param includeHidden
     * @return
     */
    public boolean hasExitLocation( GameLocation location, boolean includeHidden )
    {
        return hasExitLocation( location, includeHidden );
    }

    /**
     * @param locationName
     * @return
     */
    public boolean hasExitLocation( String locationName )
    {
        return hasExitLocation( locationName, false );
    }

    /**
     * @param locationName
     * @param includeHidden
     * @return
     */
    public boolean hasExitLocation( String locationName, boolean includeHidden )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getExitLocation().getName().equalsIgnoreCase( locationName ) )
            {
                if ( includeHidden || !exit.hasStatus( "hidden" ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return
     */
    public String listExits()
    {
        String output = "";

        if ( this.exitList.size() > 0 )
        {
            output = String.format( "%-20s %-20s\n", "Direction:", "Location:" );
            for ( GameExit exit : this.exitList() )
            {
                if ( !exit.isHidden() )
                {
                    output +=
                            String.format( "%-20s %-20s\n", exit.getExitDirection().getName(), exit.getExitLocation().getName() );
                }
            }
        }

        return output;
    }

    /**
     * @param exit
     * @return
     */
    public boolean removeExit( GameExit exit )
    {
        return removeExitByDirection( exit.getName() );
    }

    /**
     * @param exitName
     * @return
     */
    public boolean removeExit( String exitName )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getName().equalsIgnoreCase( exitName ) )
            {
                this.exitList.remove( exit );
                return true;
            }
        }
        return false;
    }

    /**
     * @param direction
     * @return
     */
    public boolean removeExitByDirection( GameDirection direction )
    {
        return removeExitByDirection( direction.getName() );
    }

    /**
     * @param directionName
     * @return
     */
    public boolean removeExitByDirection( String directionName )
    {
        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getExitDirection().getName().equalsIgnoreCase( directionName ) )
            {
                this.exitList.remove( exit );
                return true;
            }
        }
        return false;
    }

    /**
     * @param location
     * @return
     */
    public boolean removeExitsByLocation( GameLocation location )
    {
        return removeExitsByLocation( location.getName() );
    }

    /**
     * @param locationName
     * @return
     */
    public boolean removeExitsByLocation( String locationName )
    {
        boolean removed = false;

        for ( GameExit exit : this.exitList() )
        {
            if ( exit.getExitLocation().getName().equalsIgnoreCase( locationName ) )
            {
                this.exitList.remove( exit );
                removed = true;
            }
        }

        return removed;
    }

    /**
     * @param location
     */
    public void setLocationComponent( GameLocation location )
    {
        this.location = location;
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
