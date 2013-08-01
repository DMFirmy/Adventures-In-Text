package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

/**
 */
public class LocationSpeakerUtility
    implements GameLocationSpeakerUtility
{
    @SuppressWarnings( "compatibility:7011596913195561081" )
    private static final long serialVersionUID = 1L;

    /**
     */
    protected GameLocation location;

    /**
     * @param location
     */
    public LocationSpeakerUtility( GameLocation location )
    {
        super();
        this.location = location;
    }

    /**
     * @param speaker
     * @return
     */
    public boolean hasSpeaker( GameSpeaker speaker )
    {
        return this.hasSpeaker( speaker, false );
    }

    /**
     * @param speaker
     * @param includeHidden
     * @return
     */
    public boolean hasSpeaker( GameSpeaker speaker, boolean includeHidden )
    {
        if ( location.pawnList().contains( speaker ) )
        {
            GameObject containerObject = ( GameObject ) speaker;
            if ( includeHidden || !containerObject.hasStatus( "hidden" ) )
            {
                return true;
            }
        }
        if ( location.exitList().contains( speaker ) )
        {
            GameObject containerObject = ( GameObject ) speaker;
            if ( includeHidden || !containerObject.hasStatus( "hidden" ) )
            {
                return true;
            }
        }
        if ( location.itemList().contains( speaker ) )
        {
            GameObject containerObject = ( GameObject ) speaker;
            if ( includeHidden || !containerObject.hasStatus( "hidden" ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param speakerName
     * @return
     */
    public boolean hasSpeaker( String speakerName )
    {
        return this.hasSpeaker( speakerName, false );
    }

    /**
     * @param speakerName
     * @param includeHidden
     * @return
     */
    public boolean hasSpeaker( String speakerName, boolean includeHidden )
    {
        for ( GamePawn pawn : location.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( speakerName ) && pawn instanceof GameSpeaker )
            {
                return this.hasSpeaker( ( GameSpeaker ) pawn, includeHidden );
            }
        }
        for ( GameItem item : location.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( speakerName ) && item instanceof GameSpeaker )
            {
                if ( includeHidden || !item.hasStatus( "hidden" ) )
                {
                    return this.hasSpeaker( ( GameSpeaker ) item, includeHidden );
                }
            }
        }
        for ( GameExit exit : location.exitList() )
        {
            if ( exit.getName().equalsIgnoreCase( speakerName ) && exit instanceof GameSpeaker )
            {
                if ( includeHidden || !exit.hasStatus( "hidden" ) )
                {
                    return this.hasSpeaker( ( GameSpeaker ) exit, includeHidden );
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
