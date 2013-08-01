package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

/**
 * This class is used to represent any item within the game world that the Player
 * or a GameActor can interact with, and is the primary implementation of the GameItem
 * Interface.
 * @author Firmy
 * @version 1.0
 */
public class Item
	extends BaseObject
	implements GameItem
{

	/**
	 * This field holds the text that will be displayed to the Player when this
	 * Item is is picked up.
	 */
	private String pickUpText;

	/**
	 * This field holds the text that will be displayed to the Player when this
	 * Item is is picked up for the first time. Afterwards, the pickup text is used instead.
	 */
	private String initialPickUpText;

	/**
	 * This field holds the value for this Item's weight.
	 */
	private int weight;

	/**
	 * This field holds whether this Item has been marked as previously picked
	 * up by the Player.
	 */
	private boolean pickedUp;

	/**
	 * This field holds whether this Item has been marked as movable or not.
	 */
	private boolean movable;

	/**
	 * This field holds whether this Item has been marked as hidden or not.
	 */
	private boolean hidden;

	/**
	 * This constructor initializes all the fields for this class and adds this Item
	 * to the Level.gameObjects list.
	 */
	public Item()
	{
		super();
		pickUpText = "";
		initialPickUpText = "";
		pickedUp = false;
		weight = 1;
		movable = true;
		hidden = false;
		Level.gameObjects().add(this);
	}

	/**
	 * This setter method is used to set the text to be displayed when this GameItem
	 * is added to the player's inventory.
	 * @param newPickUpText The text to set as the pickup text for this GameItem.
	 */
	public void pickUpText(String newPickUpText)
	{

		pickUpText = newPickUpText;

	}

	/**
	 * This setter method is used to set the text to be displayed when this GameItem
	 * is added to the player's inventory for the first time. It is usually a bit
	 * longer and more descriptive than the standard pickup text.
	 * @param newPickUpText The text to set as the initial pickup text for this GameItem.
	 */
	public void initialPickUpText(String newPickUpText)
	{

		initialPickUpText = newPickUpText;

	}

	/**
	 * This setter method is used to set whether this GameItem is marked as hidden or not.
	 * @param isHidden The boolean value to set this GameItem's hidden status to.
	 */
	public void hidden(boolean isHidden)
	{
		this.hidden = isHidden;
	}

	/**
	 * This setter method is used to assign a new weight to this GameItem.
	 * @param newWeight The new weight to be assigned.
	 */
	public void weight(int newWeight)
	{
		weight = newWeight;
	}

	/**
	 * This setter method is used to set whether this GameItem is marked as movable or not.
	 * @param movability The boolean value to set this GameItem's movability to.
	 */
	public void movable(boolean movability)
	{
		movable = movability;
	}

	/**
	 * This getter method gets the text that will be displayed to the Player when
	 * this GameItem is added to their inventory. If this GameItem is not marked as having
	 * been picked up previously by the Player, the initial pickup text is used.
	 * @return The String of text to be displayed.
	 */
	public String pickUpText()
	{
		if (!pickedUp)
		{
			pickedUp = true;
			if (initialPickUpText.equals(""))
			{
				return pickUpText;
			}
			else
			{
				return initialPickUpText;
			}
		}
		else
		{
			return pickUpText;
		}
	}

	/**
	 * This getter method gets how much this GameItem weighs. This is used to figure
	 * out how many GameItems the Player is allowed to carry.
	 * @return The weight assigned to this GameItem.
	 */
	public int weight()
	{
		return weight;
	}

	/**
	 * This is used to check if this GameItem is marked as hidden.
	 * @return True if this GameItem is marked as hidden, false otherwise.
	 */
	public boolean isHidden()
	{
		return this.hidden;
	}

	/**
	 * This is used to check if this GameItem is marked as movable. Items that are
	 * marked as not movable can not be picked up or moved by the Player.
	 * @return True if this item is movable, false otherwise.
	 */
	public boolean isMovable()
	{
		if (movable)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method is used to check whether this GameItem is marked as hidden or not.
	 * @return True if this GameItem is marked as hidden, false otherwise.
	 */
	public boolean hidden()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
