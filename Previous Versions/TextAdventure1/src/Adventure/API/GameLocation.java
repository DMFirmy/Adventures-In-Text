package Adventure.API;

import java.util.ArrayList;

/**
 * This interface is used to represent any location within the game world that the
 * Player can explore.
 * @author Firmy
 * @version 1.0
 */
public interface GameLocation
	extends GameObject
{
	/**
	 * This method is used to output to the screen the name of this GameLocation.
	 */
	abstract void showName();

	/**
	 * This getter method is used to get the list of items that are in this location.
	 * @return The ArrayList of GameItems in this GameLocation.
	 */
	abstract ArrayList<GameItem> items();

	/**
	 * This getter method is used to retrieve a single GameItem by name from this
	 * locations list of items.
	 * @param itemName The String name of the item to retrieve.
	 * @return The GameItem with the specified name or null if it is not found.
	 */
	abstract GameItem item(String itemName);

	/**
	 * This method is used to determine if the GameItem with the specified name is
	 * present in this locations item list.
	 * @param itemName The String name of the item to look for.
	 * @return True if the item is present, false otherwise.
	 */
	abstract boolean hasItem(String itemName);

	/**
	 * This getter method is used to get the list of actors that are in this location.
	 * @return The ArrayList of GameActors in this GameLocation.
	 */
	abstract ArrayList<GameActor> actors();

	/**
	 * This getter method is used to retrieve a single GameActor by name from this
	 * locations list of actors.
	 * @param actorName The String name of the item to retrieve.
	 * @return The GameActor with the specified name or null if it is not found.
	 */
	abstract GameActor actor(String actorName);

	/**
	 * This getter method is used to get the location that this location exits to
	 * in the direction that is specified.
	 * @param direction The String direction to get the exit location for.
	 * @return The GameLocation that is the exit for the specified direction, or
	 *		null if none is found.
	 */
	abstract GameLocation exit(String direction);

	/**
	 * This method is used to check whether there is an exit in the specified direction.
	 * This overload will call the hasExit(String, boolean) overload with a default
	 * value of false so that hidden exits are not included.
	 * @param direction The String direction to check for an exit.
	 * @return True if an exit is found in the specified direction, false otherwise.
	 */
	abstract boolean hasExit(String direction);

	/**
	 * This method is used to check whether there is an exit in the specified direction.
	 * Optionally, hidden exits can also be included in the check.
	 * @param direction The String direction to check for an exit.
	 * @param includeHidden Whether you wish to include hidden exits in the check.
	 * @return True if an exit is found in the specified direction, false otherwise.
	 */
	abstract boolean hasExit(String direction, boolean includeHidden);

	/**
	 * This setter method is used to add a new exit to this location. This overload
	 * will call the addExit(String,GameLocation,boolean) overload with a default of
	 * false so that this exit is not hidden
	 * @param direction The string direction that this exit is located.
	 * @param exitTo The GameLocation that this exit leads to.
	 */
	abstract void addExit(String direction, GameLocation exitTo);

	/**
	 * This setter method is used to add a new exit to this location. You can also
	 * mark the exit as hidden or not hidden as well.
	 * @param direction The string direction that this exit is located.
	 * @param exitTo The GameLocation that this exit leads to.
	 * @param hideExit Whether to mark this exit as hidden or not.
	 */
	abstract void addExit(String direction, GameLocation exitTo,
						  boolean hideExit);

	/**
	 * This method is used to remove the exit that is in the specified direction from
	 * this location.
	 * @param direction The String direction to remove the exit from.
	 */
	abstract void removeExit(String direction);

	/**
	 * This method will mark all exits in this location that are hidden as not hidden.
	 */
	abstract void revealExits();

	/**
	 * This method is used to create a String listing of GameActors in this location
	 * to the be output.
	 * @return The String list of GameActors
	 */
	abstract String listActors();

	/**
	 * This method is used to create a String listing of exits in this location to
	 * the be output.
	 * @return The String list of exits.
	 */
	abstract String listExits();

	/**
	 * This method is used to create a String listing of GameItems in this location
	 * to the be output.
	 * @return The String list of GameItems
	 */
	abstract String listItems();
}
