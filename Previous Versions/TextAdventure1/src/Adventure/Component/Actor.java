package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

import java.util.ArrayList;

/**
 * This class serves as the core implementation of the GameActor interface, and
 * provides base functionality for any non-player characters within this game.
 * @author Firmy
 * @version 1.0
 */
public class Actor
	extends BaseObject
	implements GameActor
{

	/**
	 * This field will be used to hold this Actor's current location.
	 */
	private GameLocation location;

	/**
	 * This field will be used to hold the location that this Actor was last in.
	 */
	private GameLocation previousLocation;

	/**
	 * This field holds the ArrayList of GameItem that is this Actor's inventory.
	 */
	private ArrayList<GameItem> inventory;

	/**
	 * This field holds an Array List of status tags for this Actor. Any tag in this
	 * list will have a status of true.
	 */
	private ArrayList<String> status;

	/**
	 * This field marks whether this actor has a hidden status.
	 */
	private boolean hidden;

	/**
	 * This field marks whether this Actor has met the player before.
	 */
	private boolean hasMetPlayer;

	/**
	 * This field holds the GameDialog that will first be called when this Actor
	 * has a conversation with the player.
	 */
	private GameDialog initialDialog;

	/**
	 * This constructor will initialize all the relevant fields for this Actor. It
	 * will also add this Actor to the Level.gameObjects array.
	 */
	public Actor()
	{
		super();
		this.inventory = new ArrayList<GameItem>();
		this.hasMetPlayer = false;
		this.status = new ArrayList<String>();
		Level.gameObjects().add(this);
	}

	/**
	 * This method will either set or unset this Actor's status for the given name.
	 * @param statusItem The String name of the status item to update.
	 */
	public void updateStatus(String statusItem)
	{
		if (this.status.contains(statusItem))
		{
			this.status.remove(statusItem);
		}
		else
		{
			this.status.add(statusItem);
		}
	}

	/**
	 * This setter method will update this Actor's location to the new GameLocation
	 * that is specified.
	 * @param newLocation The new GameLocation to set as this Actors current location.
	 */
	public void location(GameLocation newLocation)
	{
		if (this.location != null)
		{
			location.actors().remove(this);
		}
		previousLocation = location;
		location = newLocation;
		location.actors().add(this);
	}

	/**
	 * This method will check if this Actorr has been marked with a given status.
	 * @param statusItem The String name of the status item
	 * @return True if the Player is marked as having the specified status, false otherwise.
	 */
	public boolean checkStatus(String statusItem)
	{
		if (this.status.contains(statusItem))
		{
			return true;
		}
		return false;
	}

	/**
	 * This setter method is used to designate the initial GameDialog that is loaded
	 * when this Actor has a conversation with the Player.
	 * @param newInitialDialog The GameDialog that is set as the initial one for
	 *		conversations.
	 */
	public void initialDialog(GameDialog newInitialDialog)
	{
		initialDialog = newInitialDialog;
	}

	/**
	 * This setter method is used to update the hidden status of this Actor.
	 * @param isHidden This is the boolean value to set the hidden status to.
	 */
	public void hidden(boolean isHidden)
	{
		this.hidden = isHidden;
	}

	/**
	 * This method is used to retrieve a GameItem from this Actor's inventory that
	 * has the name specified name.
	 * @param itemName The String name of the item to retrieve.
	 * @return The GameItem with the name specified or null if it is not found.
	 */
	public GameItem item(String itemName)
	{
		for (GameItem item : inventory)
		{
			if (item.name().equalsIgnoreCase(itemName))
			{
				return item;
			}
		}
		return null;
	}

	/**
	 * This is a getter method that is used to get this Actor's current location.
	 * @return This method will return this Actor's current GameLocation.
	 */
	public GameLocation location()
	{
		return location;
	}

	/**
	 * This is a getter method that is used to get this Actor's last location.
	 * @return This method will return this Actor's most recent GameLocation.
	 */
	public GameLocation previousLocation()
	{
		return previousLocation;
	}

	/**
	 * This getter method is used to retrieve this character's initial dialog for
	 * conversations.
	 * @return The GameDialog that represents this character's initial dialog.
	 */
	public GameDialog initialDialog()
	{
		return initialDialog;
	}

	/**
	 * This method will check whether this GameActor is currently hidden.
	 * @return True if this Actor is hidden, false otherwise.
	 */
	public boolean hidden()
	{
		return this.hidden;
	}

	/**
	 * This method is used to determine if this Actor has a GameItem in their
	 * inventory that has the specified name.
	 * @param itemName The String name of the item to check for.
	 * @return True if the item is in this Actor's inventory, false otherwise.
	 */
	public boolean hasItem(String itemName)
	{
		for (GameItem item : inventory)
		{
			if (item.name().equalsIgnoreCase(itemName))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This will determine if this Actor has had it's status marked as having
	 * previously met the Player. This will be true automatically once the player
	 * has first spoken to this Actor.
	 * @return True if the Actor has met the player, False otherwise.
	 */
	public boolean hasMetPlayer()
	{
		if (this.hasMetPlayer)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method is used to move this Actor through the exit that is in their
	 * current location that is in the direction specified.
	 * @param direction This is a String that represents the direction of the exit to use.
	 */
	public void move(String direction)
	{
		/*
         * We need to make sure that we are looking for a direction by its name even
         * if we are only given the letter key for that direction.
         */
		if (location.hasExit(Processor.directionName(direction)))
		{
			location(location.exit(Processor.directionName(direction)));
		}
		else
		{
			Output.add("You can't exit in that direction.");
		}
	}

	/**
	 * This method will get a GameItem from this Actor's location and add it to
	 * their inventory.
	 * @param itemName The name of the item to pick up.
	 */
	public void pickUpItem(String itemName)
	{
		GameItem item = location.item(itemName);

		if (item != null && item.isMovable())
		{
			location.items().remove(item);
			inventory.add(item);
		}
	}

	/**
	 * This overload of the giveItem() method is used to give a GameItem in this
	 * Actor's inventory to the Player.
	 * @param itemName The String name of the GameItem to give to the Player.
	 */
	public void giveItem(String itemName)
	{
		dropItem(itemName);
		Player.pickUpItem(itemName);
	}

	/**
	 * This overload of the giveItem() method is used to give a GameItem in this
	 * GameActor's inventory to another Actor.
	 * @param itemName The String name of the GameItem to give to the Actor.
	 * @param actor The Actor that the item is given to.
	 */
	public void giveItem(String itemName, GameActor actor)
	{
		dropItem(itemName);
		actor.pickUpItem(itemName);
	}

	/**
	 * This method is used to take a GameItem from this Actor's inventory an place
	 * back into their locations item list.
	 * @param itemName The String name  of the item to drop.
	 */
	public void dropItem(String itemName)
	{
		GameItem item = item(itemName);

		if (item != null)
		{
			inventory.remove(item);
			location.items().add(item);
		}
	}

	/**
	 * This method will set the hasMetPlayer status for this Actor to true.
	 */
	public void meetPlayer()
	{
		this.hasMetPlayer = true;
	}

	/**
	 * This method will set the hasMetPlayer status for this Actor to false.
	 */
	public void forgetMeetingPlayer()
	{
		this.hasMetPlayer = false;
	}

	/**
	 * This method is used to call the initiate method for this Actor's initial GameDialog.
	 */
	public void initiateDialog()
	{
		this.initiateDialog(false);
	}

	/**
	 * This method is used to call the initiate method for this Actor's initial GameDialog.
   * @param speakWhileHidden If set to true, this dialog will initiate even if this actor is hidden.
	 */
	public void initiateDialog(boolean speakWhileHidden)
	{
		if(speakWhileHidden || !this.hidden())
    {
			if (!hasMetPlayer)
			{
				meetPlayer();
			}
			if (initialDialog != null)
			{
				initialDialog.initiate();
			}
			else
			{
				Output.add("I have nothing to say right now.");
			}
    }
		else
		{
			Output.add("There is no one here by that name.");
		}
	}
}
