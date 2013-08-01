package Adventure.API;

/**
 * This interface is used to represent any non-player characters within the game
 * that can have conversations and inventories of their own. GameActors also have
 * the ability to move from location to location.
 * @author Firmy
 * @version 1.0
 */
public interface GameActor
	extends GameObject
{
	/**
	 * This method is used to move this game actor through the exit that is in their
	 * current location that is in the direction specified.
	 * @param direction This is a String that represents the direction of the exit to use.
	 */
	abstract void move(String direction);

	/**
	 * This method is used to determine if this GameActor has a GameItem in their
	 * inventory that has the specified name.
	 * @param itemName The String name of the item to check for.
	 * @return True if the item is in this GameActor's inventory, false otherwise.
	 */
	abstract boolean hasItem(String itemName);

	/**
	 * This method is used to retrieve a GameItem from this GameActor's inventory
	 * that has the name specified name.
	 * @param itemName The String name of the item to retrieve.
	 * @return The GameItem with the name specified or null if it is not found.
	 */
	abstract GameItem item(String itemName);

	/**
	 * This method will get a GameItem from this GameActor's location and add it to
	 * their inventory.
	 * @param itemName The name of the item to pick up.
	 */
	abstract void pickUpItem(String itemName);

	/**
	 * This method is used to take a GameItem from this GameActor's inventory an place
	 * back into their locations item list.
	 * @param itemName The String name  of the item to drop.
	 */
	abstract void dropItem(String itemName);

	/**
	 * This overload of the giveItem() method is used to give a GameItem in this
	 * GameActor's inventory to the Player.
	 * @param itemName The String name of the GameItem to give to the Player.
	 */
	abstract void giveItem(String itemName);

	/**
	 * This overload of the giveItem() method is used to give a GameItem in this
	 * GameActor's inventory to another GameActor.
	 * @param itemName The String name of the GameItem to give to the GameActor.
	 * @param actor The GameActor that the item is given to.
	 */
	abstract void giveItem(String itemName, GameActor actor);

	/**
	 * This is a getter method that is used to get this GameActor's current location.
	 * @return This method will return this GameActor's current GameLocation.
	 */
	abstract GameLocation location();

	/**
	 * This is a getter method that is used to get this GameActor's last location.
	 * @return This method will return this GameActor's most recent GameLocation.
	 */
	abstract GameLocation previousLocation();

	/**
	 * This setter method will update this GameActor's location to the new GameLocation
	 * that is specified.
	 * @param newLocation The new GameLocation to set as this GameActors current location.
	 */
	abstract void location(GameLocation newLocation);

	/**
	 * This method will check whether this GameActor is currently hidden.
	 * @return True if this GameActor is hidden, false otherwise.
	 */
	abstract boolean hidden();

	/**
	 * This setter method is used to update the hidden status of this GameActor.
	 * @param isHidden This is the boolean value to set the hidden status to.
	 */
	abstract void hidden(boolean isHidden);

	/**
	 * This method will set the hasMetPlayer status for this GameActor to true.
	 */
	abstract void meetPlayer();

	/**
	 * This method will set the hasMetPlayer status for this GameActor to false.
	 */
	abstract void forgetMeetingPlayer();

	/**
	 * This method is used to call the initiate method for this GameActor's initial
	 * GameDialog.
	 */
	abstract void initiateDialog();

	/**
	 * This setter method is used to designate the initial GameDialog that is loaded
	 * when this GameActor has a conversation with the Player.
	 * @param newInitialDialog The GameDialog that is set as the initial one for
	 *		conversations.
	 */
	abstract void initialDialog(GameDialog newInitialDialog);

	/**
	 * This getter method is used to retrieve this character's initial dialog for
	 * conversations.
	 * @return The GameDialog that represents this character's initial dialog.
	 */
	abstract GameDialog initialDialog();

	/**
	 * This will determine if this GameActor has had it's status marked as having
	 * previously met the Player. This will be true automatically once the player
	 * has first spoken to this GameActor.
	 * @return True if the GameActor has met the player, False otherwise.
	 */
	abstract boolean hasMetPlayer();

	/**
	 * This method will either set or unset this GameActor's status for the given name.
	 * @param statusItem The String name of the status item to update.
	 */
	abstract void updateStatus(String statusItem);

	/**
	 * This method will check if this GameActorctor has been marked with a given status.
	 * @param statusItem The String name of the status item
	 * @return True if the Player is marked as having the specified status, false otherwise.
	 */
	abstract boolean checkStatus(String statusItem);
}
