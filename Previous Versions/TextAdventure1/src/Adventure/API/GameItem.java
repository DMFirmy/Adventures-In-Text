package Adventure.API;

/**
 * This interface is used to represent any item within the game world that the Player
 * or a GameActor can interact with.
 * @author Firmy
 * @version 1.0
 */
public interface GameItem
	extends GameObject
{
	/**
	 * This getter method gets how much this GameItem weighs. This is used to figure
	 * out how many GameItems the Player is allowed to carry.
	 * @return The weight assigned to this GameItem.
	 */
	abstract int weight();

	/**
	 * This is used to check if this GameItem is marked as hidden.
	 * @return True if this GameItem is marked as hidden, false otherwise.
	 */
	abstract boolean isHidden();

	/**
	 * This is used to check if this GameItem is marked as movable. Items that are
	 * marked as not movable can not be picked up or moved by the Player.
	 * @return True if this item is movable, false otherwise.
	 */
	abstract boolean isMovable();

	/**
	 * This getter method gets the text that will be displayed to the Player when
	 * this GameItem is added to their inventory. If this GameItem is not marked as having
	 * been picked up previously by the Player, the initial pickup text is used.
	 * @return The String of text to be displayed.
	 */
	abstract String pickUpText();

	/**
	 * This setter method is used to set the text to be displayed when this GameItem
	 * is added to the player's inventory.
	 * @param newPickUpText The text to set as the pickup text for this GameItem.
	 */
	abstract void pickUpText(String newPickUpText);

	/**
	 * This setter method is used to set the text to be displayed when this GameItem
	 * is added to the player's inventory for the first time. It is usually a bit
	 * longer and more descriptive than the standard pickup text.
	 * @param newPickUpText The text to set as the initial pickup text for this GameItem.
	 */
	abstract void initialPickUpText(String newPickUpText);

	/**
	 * This setter method is used to assign a new weight to this GameItem.
	 * @param newWeight The new weight to be assigned.
	 */
	abstract void weight(int newWeight);

	/**
	 * This method is used to check whether this GameItem is marked as hidden or not.
	 * @return True if this GameItem is marked as hidden, false otherwise.
	 */
	abstract boolean hidden();

	/**
	 * This setter method is used to set whether this GameItem is marked as hidden or not.
	 * @param isHidden The boolean value to set this GameItem's hidden status to.
	 */
	abstract void hidden(boolean isHidden);

	/**
	 * This setter method is used to set whether this GameItem is marked as movable or not.
	 * @param movability The boolean value to set this GameItem's movability to.
	 */
	abstract void movable(boolean movability);
}
