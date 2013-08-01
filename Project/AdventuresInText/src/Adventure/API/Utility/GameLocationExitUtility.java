package Adventure.API.Utility;

import Adventure.API.*;

import java.util.*;

/**
 * This utility object is utilized by GameLocations for the funcionality of managing the various exits within them.
 */
public interface GameLocationExitUtility
    extends GameUtility
{
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
      * This method is used to add a new GameExit to this location in the direction with the given name that leads to the
      * location with the given name.
      *
      * @param directionName The name of the GameDirection to be moved to reach the new location.
      * @param locationName The name of the GameLocation that moving in the given direction leads to.
      * @return True if the exit was sucessfully added, false if not.
      */
    public abstract boolean addExit( String directionName, String locationName );

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
    public abstract boolean removeExitByDirection( GameDirection direction );

     /**
      * This method is used to remove the exit in the named direction from the list of exits in this location.
      *
      * @param directionName The name of the direction to remove the GameExit from.
      * @return True if the exit is successfully removed, false if it is not.
      */
    public abstract boolean removeExitByDirection( String directionName );

     /**
      * This method is used to remove all exits that lead to the given GameLocation from this location.
      *
      * @param location The location to remove all the GameExits leading to.
      * @return True if the exit is successfully removed, false if it is not.
      */
    public abstract boolean removeExitsByLocation( GameLocation location );

     /**
      * This method is used to remove all exits that lead to the given GameLocation with the given name from this location.
      *
      * @param locationName The location to remove all the GameExits leading to.
      * @return True if the exit is successfully removed, false if it is not.
      */
    public abstract boolean removeExitsByLocation( String locationName );

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
