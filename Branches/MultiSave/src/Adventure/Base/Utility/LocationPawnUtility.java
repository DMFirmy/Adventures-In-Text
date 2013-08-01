package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import java.util.*;

/**
 */
public class LocationPawnUtility
    implements GameLocationPawnUtility
{
    @SuppressWarnings( "compatibility:7648579766907617318" )
    private static final long serialVersionUID = 1L;

    /**
     */
    protected ArrayList<GamePawn> pawnList;

    /**
     */
    protected GameLocation location;

    /**
     * @param location
     */
    public LocationPawnUtility( GameLocation location )
    {
        super();
        this.location = location;
        this.pawnList = new ArrayList<GamePawn>();
    }

    /**
     * @return
     */
    public ArrayList<GameActor> actorList()
    {
        ArrayList<GameActor> actors = new ArrayList<GameActor>();
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn instanceof GameActor )
            {
                actors.add( ( GameActor ) pawn );
            }
        }
        return actors;
    }

    /**
     * @param newPawn
     * @return
     */
    public boolean addPawn( GamePawn newPawn )
    {
        if ( !this.pawnList().contains( newPawn ) )
        {
            if ( newPawn.getLocation() != null )
            {
                newPawn.getLocation().removePawn( newPawn );
            }
            this.pawnList.add( newPawn );

            if ( newPawn.getLocation() != this.location )
            {
                newPawn.setLocation( this.location );
            }
            return true;
        }
        return false;
    }

    /**
     * @param pawnName
     * @return
     */
    public boolean addPawn( String pawnName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GamePawn && component.getName().equalsIgnoreCase( pawnName ) )
            {
                if ( !this.pawnList().contains( component ) )
                {
                    return this.addPawn( ( GamePawn ) component );
                }
            }
        }
        return false;
    }

    /**
     * @param actorName
     * @return
     */
    public GameActor getActor( String actorName )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( actorName ) && pawn instanceof GameActor )
            {
                return ( GameActor ) pawn;
            }
        }
        return null;
    }

    /**
     * @param pawnName
     * @return
     */
    public GamePawn getPawn( String pawnName )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( pawnName ) )
            {
                return pawn;
            }
        }
        return null;
    }

    /**
     * @param playerName
     * @return
     */
    public GamePlayer getPlayer( String playerName )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( playerName ) && pawn instanceof GamePlayer )
            {
                return ( GamePlayer ) pawn;
            }
        }
        return null;
    }

    /**
     * @param actor
     * @return
     */
    public boolean hasActor( GameActor actor )
    {
        return this.hasActor( actor.getName(), false );
    }

    /**
     * @param actor
     * @param includeHidden
     * @return
     */
    public boolean hasActor( GameActor actor, boolean includeHidden )
    {
        return this.hasActor( actor.getName(), includeHidden );
    }

    /**
     * @param actorName
     * @return
     */
    public boolean hasActor( String actorName )
    {
        return this.hasActor( actorName, false );
    }

    /**
     * @param actorName
     * @param includeHidden
     * @return
     */
    public boolean hasActor( String actorName, boolean includeHidden )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( actorName ) && pawn instanceof GameActor )
            {
                if ( includeHidden || !pawn.hasStatus( "hidden" ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param pawn
     * @return
     */
    public boolean hasPawn( GamePawn pawn )
    {
        return this.hasPawn( pawn, false );
    }

    /**
     * @param pawn
     * @param includeHidden
     * @return
     */
    public boolean hasPawn( GamePawn pawn, boolean includeHidden )
    {
        return this.hasPawn( pawn, includeHidden );
    }

    /**
     * @param pawnName
     * @return
     */
    public boolean hasPawn( String pawnName )
    {
        return this.hasPawn( pawnName, false );
    }

    /**
     * @param pawnName
     * @param includeHidden
     * @return
     */
    public boolean hasPawn( String pawnName, boolean includeHidden )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( pawnName ) )
            {
                if ( includeHidden || !pawn.hasStatus( "hidden" ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param player
     * @return
     */
    public boolean hasPlayer( GamePlayer player )
    {
        return this.hasPlayer( player.getName(), false );
    }

    /**
     * @param player
     * @param includeHidden
     * @return
     */
    public boolean hasPlayer( GamePlayer player, boolean includeHidden )
    {
        return this.hasPlayer( player.getName(), includeHidden );
    }

    /**
     * @param playerName
     * @return
     */
    public boolean hasPlayer( String playerName )
    {
        return this.hasPlayer( playerName, false );
    }

    /**
     * @param playerName
     * @param includeHidden
     * @return
     */
    public boolean hasPlayer( String playerName, boolean includeHidden )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( playerName ) && pawn instanceof GamePlayer )
            {
                if ( includeHidden || !pawn.hasStatus( "hidden" ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param initialOutputString
     * @return
     */
    public String listActors( String initialOutputString )
    {
        String output = "";

        if ( actorList().size() > 0 )
        {
            
            String actorList = "";
            for ( GameActor actor : actorList() )
            {
                if ( !actor.isHidden() )
                {
                    actorList += actor.getName() + "\n";
                }
            }
            if(actorList != "")
            {
                output = initialOutputString + "\n";
                output += actorList + "\n";
            }
        }

        return output;
    }

    /**
     * @param initialOutputString
     * @return
     */
    public String listPlayers( String initialOutputString )
    {
        String output = "";

        if ( playerList().size() > 1 )
        {
            output = initialOutputString + "\n";
            for ( GamePlayer player : playerList() )
            {
                if ( !player.hasStatus( "hidden" ) && !player.equals( Engine.currentPlayer() ) )
                {
                    output += player.getName() + "\n";
                }
            }
        }

        return output;
    }

    /**
     * @return
     */
    public ArrayList<GamePawn> pawnList()
    {
        return ( ArrayList<GamePawn> ) this.pawnList.clone();
    }

    /**
     * @return
     */
    public ArrayList<GamePlayer> playerList()
    {
        ArrayList<GamePlayer> players = new ArrayList<GamePlayer>();
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn instanceof GamePlayer )
            {
                players.add( ( GamePlayer ) pawn );
            }
        }
        return players;
    }

    /**
     * @param pawn
     * @return
     */
    public boolean removePawn( GamePawn pawn )
    {
        if ( this.pawnList.contains( pawn ) )
        {
            this.pawnList.remove( pawn );
            return true;
        }
        return false;
    }

    /**
     * @param pawnName
     * @return
     */
    public boolean removePawn( String pawnName )
    {
        for ( GamePawn pawn : this.pawnList() )
        {
            if ( pawn.getName().equalsIgnoreCase( pawnName ) )
            {
                return this.removePawn( pawn );
            }
        }
        return false;
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
