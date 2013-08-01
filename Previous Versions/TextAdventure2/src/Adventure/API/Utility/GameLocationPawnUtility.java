package Adventure.API.Utility;

import Adventure.API.*;

import java.util.*;

/**
 * This utility object is utilized by GameLocations for the funcionality of managing the various pawns within them.
 */
public interface GameLocationPawnUtility
    extends GameUtility
{
    /**
     * This method is used to get a copy of the complete list of GameActors in this location.
     *
     * @return A copy of the list of GameActors in this location.
     */
    public abstract ArrayList<GameActor> actorList();

    /**
     * This method is used to add the given GamePawn to this location.
     *
     * @param newPawn the GamePawn to be added to the list of pawns in this location.
     * @return True if successfully added, false otherwise.
     */
    public abstract boolean addPawn( GamePawn newPawn );

    /**
     * This method is used to add the GamePawn with the given name to this location.
     *
     * @param pawnName The name of the GamePawn to be added to the list of pawns in this location.
     * @return True if successfully added, false otherwise.
     */
    public abstract boolean addPawn( String pawnName );

    /**
     * This method is used to retrieve a reference to the GameActor in this location with a given name.
     *
     * @param actorName The name of the GameActor to be retrieved.
     * @return A reference to the GameActor in this location with a given name.
     */
    public abstract GameActor getActor( String actorName );

    /**
     * This method is used to retrieve a reference to the GamePawn in this location with a given name.
     *
     * @param pawnName The name of the GamePawn to be retrieved.
     * @return A reference to the GamePawn in this location with a given name.
     */
    public abstract GamePawn getPawn( String pawnName );

    /**
     * This method is used to retrieve a reference to the GamePlayer in this location with a given name.
     *
     * @param playerName The name of the GamePlayer to be retrieved.
     * @return A reference to the GamePlayer in this location with a given name.
     */
    public abstract GamePlayer getPlayer( String playerName );

    /**
     * This method is used to determine if the given GameActor is in this location. This search will not
     * include GameHideable actors marked as hidden.
     *
     * @param actor The GameActor to look for.
     * @return True if the actor is in this location, false otherwise.
     */
    public abstract boolean hasActor( GameActor actor );

    /**
     * This method is used to determine if the given GameActor is in this location. It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param actor The GameActor to look for.
     * @param includeHidden whether or not to include GameHideable actors marked as hidden.
     * @return True if the actor is in this location, false otherwise.
     */
    public abstract boolean hasActor( GameActor actor, boolean includeHidden );

    /**
     * This method is used to determine if a GameActor with the given name is in this location. This search will not
     * include GameHideable actors marked as hidden.
     *
     * @param actorName The name of the GameActor to look for.
     * @return True if the actor is in this location, false otherwise.
     */
    public abstract boolean hasActor( String actorName );

    /**
     * This method is used to determine if a GameActor with the given name is in this location. It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param actorName The name of the GameActor to look for.
     * @param includeHidden whether or not to include GameHideable actors marked as hidden.
     * @return True if the actor is in this location, false otherwise.
     */
    public abstract boolean hasActor( String actorName, boolean includeHidden );

    /**
     * This method is used to determine if the given GamePawn is in this location. This search will not
     * include GameHideable pawns marked as hidden.
     *
     * @param pawn The GamePawn to look for.
     * @return True if the pawn is in this location, false otherwise.
     */
    public abstract boolean hasPawn( GamePawn pawn );

    /**
     * This method is used to determine if the given GamePawn is in this location. It has the option to also
     * include GameHideable pawns marked as hidden.
     *
     * @param pawn The GamePawn to look for.
     * @param includeHidden whether or not to include GameHideable pawns marked as hidden.
     * @return True if the pawn is in this location, false otherwise.
     */
    public abstract boolean hasPawn( GamePawn pawn, boolean includeHidden );

    /**
     * This method is used to determine if a GamePawn with the given name is in this location. This search will not
     * include GameHideable pawns marked as hidden.
     *
     * @param pawnName The name of the GamePawn to look for.
     * @return True if the pawn is in this location, false otherwise.
     */
    public abstract boolean hasPawn( String pawnName );

    /**
     * This method is used to determine if a GamePawn with the given name is in this location. It has the option to also
     * include GameHideable pawns marked as hidden.
     *
     * @param pawnName The name of the GamePawn to look for.
     * @param includeHidden whether or not to include GameHideable actors marked as hidden.
     * @return True if the pawn is in this location, false otherwise.
     */
    public abstract boolean hasPawn( String pawnName, boolean includeHidden );

    /**
     * This method is used to determine if the given GamePlayer is in this location. This search will not
     * include GameHideable pawns marked as hidden.
     *
     * @param player The GamePlayer to look for.
     * @return True if the player is in this location, false otherwise.
     */
    public abstract boolean hasPlayer( GamePlayer player );

    /**
     * This method is used to determine if the given GamePlayer is in this location. It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param player The GamePlayer to look for.
     * @param includeHidden whether or not to include GameHideable players marked as hidden.
     * @return True if the player is in this location, false otherwise.
     */
    public abstract boolean hasPlayer( GamePlayer player, boolean includeHidden );

    /**
     * This method is used to determine if a GamePlayer with the given name is in this location. This search will not
     * include GameHideable player marked as hidden.
     *
     * @param playerName The name of the GamePlayer to look for.
     * @return True if the player is in this location, false otherwise.
     */
    public abstract boolean hasPlayer( String playerName );

    /**
     * This method is used to determine if a GamePlayer with the given name is in this location.It has the option to also
     * include GameHideable actors marked as hidden.
     *
     * @param playerName The name of the GamePlayer to look for.
     * @param includeHidden whether or not to include GameHideable players marked as hidden.
     * @return True if the player is in this location, false otherwise.
     */
    public abstract boolean hasPlayer( String playerName, boolean includeHidden );

    /**
     * This method is used to get a string of text to list the names of all the GameActors in this
     * location. This text string doesn't include GameHideable actors marked as hidden.
     *
     * @return A string listing of the actors in this location.
     */
    public abstract String listActors( String initialOutputString );

    /**
     * This method is used to get a string of text to list the names of all the GamePlayers in this
     * location. This text string doesn't include GameHideable actors marked as hidden.
     *
     * @return A string listing of the players in this location.
     */
    public abstract String listPlayers( String initialOutputString );

    /**
     * This method is used to get a copy of the list of GamePawn objects in this location.
     *
     * @return A copy of the list of GamePawn objects in this location.
     */
    public abstract ArrayList<GamePawn> pawnList();

    /**
     * This method is used to get a copy of the list of GamePlayer objects in this location.
     *
     * @return A copy of the list of GamePlayer objects in this location.
     */
    public abstract ArrayList<GamePlayer> playerList();

    /**
     * This method is used to remove the given GamePawn from the list of pawns in this location.
     *
     * @param pawn The GamePawn to be removed from this location.
     * @return True if the pawn is successfully removed, false if it is not.
     */
    public abstract boolean removePawn( GamePawn pawn );

    /**
     * This method is used to remove the GamePawn with the given name from the list of pawns in this location.
     *
     * @param pawnName The name of the GamePawn to be removed from this location.
     * @return True if the pawn is successfully removed, false if it is not.
     */
    public abstract boolean removePawn( String pawnName );

    /**
     * This method is used to attach this utility object to the given GameLocation.
     *
     * @param newLocation The GameLocation to attach this utility object to.
     */
    public abstract void setLocationComponent( GameLocation newLocation );

    /**
     * This method is used to attach this utility object to the GameLocation with the given name.
     *
     * @param locationName The name of the GameLocation to attach this utility object to.
     */
    public abstract void setLocationComponent( String locationName );
}
