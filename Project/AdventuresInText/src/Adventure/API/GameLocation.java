package Adventure.API;

import Adventure.API.Utility.*;

import java.util.*;

/**
 * This interface represents all of the actual locations that exist within the game world. GameLocations contain
 * all of the pawns, items, exits, dialogs, and other component parts that exist and can be interacted with in the
 * game.
 */
public abstract interface GameLocation
    extends GameObject, GameContainer
{
    ///////////////////////Container Utility////////////////////////////

    /**
     * This method is used to search for a GameContainer to find out if it is present in this location. This search
     * will not locate the container if it is a GameHideable that is marked as hidden.
     *
     * @param container The GameContainer to be searched for.
     * @return True if the GameContainer is found within this location or a component within this location, false otherwise.
     */
    public abstract boolean hasContainer( GameContainer container );

    /**
     * This method is used to search for a GameContainer to find out if it is present in this location. It includes the
     * option to not locate the container even if it is a GameHideable that is marked as hidden.
     *
     * @param container The GameContainer to be searched for.
     * @param includeHidden Whether or not to include a container that is a GameHideable marked as hidden.
     * @return True if the GameContainer is found within this location or a component within this location, false otherwise.
     */
    public abstract boolean hasContainer( GameContainer container, boolean includeHidden );

    /**
     * This method is used to search for a container with a given name to find out if it is present in this location.
     * This search will not locate the container if it is a GameHideable that is marked as hidden.
     *
     * @param containerName The name of the GameContainer to be searched for.
     * @return True if the GameContainer is found within this location or a component within this location, dalse otherwise.
     */
    public abstract boolean hasContainer( String containerName );

    /**
     * This method is used to search for a container with a given name to find out if it is present in this location.
     * It includes the option to not locate the container even if it is a GameHideable that is marked as hidden.
     *
     * @param containerName The name of the GameContainer to be searched for.
     * @param includeHidden Whether or not to include a container that is a GameHideable marked as hidden.
     * @return True if the GameContainer is found within this location or a component within this location, dalse otherwise.
     */
    public abstract boolean hasContainer( String containerName, boolean includeHidden );

    /**
     * This method is used to set a new GameLocationContainerUtility to handle GameContainers for this location.
     * @param newContainerUtility The new GameLocationContainerUtility to utilize for this location.
     */
    public abstract void setContainerUtility( GameLocationContainerUtility newContainerUtility );
    ///////////////////////Container Utility////////////////////////////

    ///////////////////////Exit Utility////////////////////////////

    /**
     * This method is used to add a new GameExit to this location in the given direction that leads to the
     * given location.
     *
     * @param direction the GameDirection to be moved to reach the new location.
     * @param location The GameLocation that moving in the given direction leads to.
     * @return True if the exit was sucessfully added, false if not.
     */
    public abstract boolean addExit( GameDirection direction, GameLocation location );

    /**
     * This method is used to add a new GameExit to this location in the direction with the given name that leads to the
     * location with the given name.
     *
     * @param directionName The name of the GameDirection to be moved to reach the new location.
     * @param locationName The name of the GameLocation that moving in the given direction leads to.
     * @return True if the exit was sucessfully added, false if not.
     */
    public abstract boolean addExit( String directionName, String locationName );

    /**
     * This method adds a GameExit object to the list of exits in this location.
     *
     * @param newExit The new GameExit to add to this location.
     * @return True if the exit was sucessfully added, false if not.
     */
    public abstract boolean addExit( GameExit newExit );

    /**
     * This method adds a GameExit object to the list of exits in this location.
     *
     * @param exitName The name of the new GameExit to add to this location.
     * @return True if the exit was sucessfully added, false if not.
     */
    public abstract boolean addExit( String exitName );

    /**
     * This method is used to get a copy of the list of exits in this location.
     *
     * @return A copy of the list of exits in this location.
     */
    public abstract ArrayList<GameExit> exitList();

    /**
     * This method is used to get a reference to an exit in this location by its name.
     *
     * @param exitName the name of the exit to be retrieved.
     * @return The GameExit with the given name or null if none is found.
     */
    public abstract GameExit getExit( String exitName );

    /**
     * This method is used to get a reference to the exit that you would travel through by moving the direction
     * that has the given name.
     *
     * @param directionName the name of the direction of which you want the exit.
     * @return The gameExit object that is in the specified direction, or null if none is found.
     */
    public abstract GameExit getExitByDirection( String directionName );

    /**
     * This method is used to see if a given Gameexit object is within the list of exits for this location. This search
     * doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param exit The GameExit object to check for.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExit( GameExit exit );

    /**
     * This method is used to see if a given GameExit object is within the list of exits for this location. It also includes
     * the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param exit The GameExit object to check for.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExit( GameExit exit, boolean includeHidden );

    /**
     * This method is used to see if an exit with the given name is within the list of exits for this location. This search
     * doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param exitName The name of the GameExit object to check for.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExit( String exitName );

    /**
     * This method is used to see if an exit with the given name is within the list of exits for this location. It also includes
     * the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param exitName The name of the GameExit object to check for.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExit( String exitName, boolean includeHidden );

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location.  This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param direction The direction to check for an exit.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitDirection( GameDirection direction );

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param direction The GameDirection object to check for an exit.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitDirection( GameDirection direction, boolean includeHidden );

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location. This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param directionName The name of the direction to check for an exit.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitDirection( String directionName );

    /**
     * This method is used to see if an exit in the direction with the given name is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param directionName The name of the direction to check for an exit.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitDirection( String directionName, boolean includeHidden );

    /**
     * This method is used to see if an exit to the given location is within the list of exits for this
     * location. This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param location The Gamelocation object to check for an exit to.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitLocation( GameLocation location );

    /**
     * This method is used to see if an exit to the given location is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param location The Gamelocation object to check for an exit to.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitLocation( GameLocation location, boolean includeHidden );

    /**
     * This method is used to see if an exit to the location with the given name is within the list of exits for this
     * location. This search doesn't include exits that are GameHideable objects marked as hiddden.
     *
     * @param locationName The Gamelocation object to check for an exit to.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitLocation( String locationName );

    /**
     * This method is used to see if an exit to the location with the given name is within the list of exits for this
     * location. It also includes the option to include exits that are GameHideable objects marked as hiddden.
     *
     * @param locationName The Gamelocation object to check for an exit to.
     * @param includeHidden Whether or not to include GameHideable objects marked as hiddden.
     * @return True if the exit is within the list, false otherwise.
     */
    public abstract boolean hasExitLocation( String locationName, boolean includeHidden );

    /**
     * This method is used to get a string of text to list the direction and location being lead to for each exit in this
     * location. This text string doesn't include GameHideable exits marked as hidden.
     *
     * @return A string listing of the exits within this location.
     */
    public abstract String listExits();

    /**
     * This method is used to remove the given GameExit from the list of exits in this location.
     *
     * @param exit The GameExit to be removed from this container.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public abstract boolean removeExit( GameExit exit );

    /**
     * This method is used to remove the GameExit with the given name from the list of exits in this location.
     *
     * @param exitName The GameExit to be removed from this location.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public abstract boolean removeExit( String exitName );

    /**
     * This method is used to remove the GameExit in the given GameDirection from the list of exits in this location.
     *
     * @param direction The direction to remove the GameExit from.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public abstract boolean removeExitDirection( GameDirection direction );

    /**
     * This method is used to remove the exit in the named direction from the list of exits in this location.
     *
     * @param directionName The name of the direction to remove the GameExit from.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public abstract boolean removeExitDirection( String directionName );

    /**
     * This method is used to remove all exits that lead to the given GameLocation from this location.
     *
     * @param location The location to remove all the GameExits leading to.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public abstract boolean removeExitLocation( GameLocation location );

    /**
     * This method is used to remove all exits that lead to the given GameLocation with the given name from this location.
     *
     * @param locationName The location to remove all the GameExits leading to.
     * @return True if the exit is successfully removed, false if it is not.
     */
    public abstract boolean removeExitLocation( String locationName );

    /**
     * This method is used to set a new GameLocationExitUtility object to handle exits for this location.
     *
     * @param newExitUtility The new GameLocationExitUtility to utilize for this location.
     */
    public abstract void setExitUtility( GameLocationExitUtility newExitUtility );
    ///////////////////////Exit Utility////////////////////////////

    ///////////////////////Pawn Utility////////////////////////////

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
    public abstract String listActors();

    /**
     * This method is used to get a string of text to list the names of all the GamePlayers in this
     * location. This text string doesn't include GameHideable actors marked as hidden.
     *
     * @return A string listing of the players in this location.
     */
    public abstract String listPlayers();

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
     * This method is used to set a new GameLocationPawnUtility object to handle pawns for this location.
     *
     * @param newPawnUtility The new GameLocationPawnUtility to utilize for this location.
     */
    public abstract void setPawnUtility( GameLocationPawnUtility newPawnUtility );
    ///////////////////////Pawn Utility////////////////////////////

    ///////////////////////Speaker Utility////////////////////////////

    /**
     * This method is used to search for a GameSpeaker to find out if it is present in this location. This search
     * will not locate the speaker if it is a GameHideable that is marked as hidden.
     *
     * @param speaker The GameSpeaker to be searched for.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public abstract boolean hasSpeaker( GameSpeaker speaker );

    /**
     * This method is used to search for a GameSpeaker to find out if it is present in this location. It includes the
     * option to not locate the speaker even if it is a GameHideable that is marked as hidden.
     *
     * @param speaker The GameSpeaker to be searched for.
     * @param includeHidden Whether or not to include a speaker that is a GameHideable marked as hidden.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public abstract boolean hasSpeaker( GameSpeaker speaker, boolean includeHidden );

    /**
     * This method is used to search for a GameSpeaker with the given name to find out if it is present in this location.
     * This search will not locate the speaker if it is a GameHideable that is marked as hidden.
     *
     * @param speakerName The name of the GameSpeaker to be searched for.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public abstract boolean hasSpeaker( String speakerName );

    /**
     * This method is used to search for a GameSpeaker with the given name to find out if it is present in this location.
     * It includes the option to not locate the speaker even if it is a GameHideable that is marked as hidden.
     *
     * @param speakerName The name of the GameSpeaker to be searched for.
     * @param includeHidden Whether or not to include a speaker that is a GameHideable marked as hidden.
     * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
     */
    public abstract boolean hasSpeaker( String speakerName, boolean includeHidden );

    /**
     * This method is used to set a new GameLocationSpeakerUtility object to handle speakers for this location.
     *
     * @param newSpeakerUtility The new GameLocationSpeakerUtility to utilize for this location.
     */
    public abstract void setSpeakerUtility( GameLocationSpeakerUtility newSpeakerUtility );
    ///////////////////////Speaker Utility////////////////////////////

    /**
     * This method is used to unmark as hidden all components that are currently so marked within this location.
     */
    public abstract void revealHidden();
}
