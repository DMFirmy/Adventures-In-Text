package Adventure.Core;

import Adventure.API.GameActor;
import Adventure.API.GameItem;
import Adventure.API.GameLocation;

import java.util.ArrayList;

/**
 * This class is used to represent the user within the game.
 * @author Firmy
 */
public class Player
{
	/**
	 * This field holds the Player's current location.
	 */
	private static GameLocation location;

	/**
	 * This field holds the location that the Player was in before the current location.
	 */
	private static GameLocation previousLocation;

	/**
	 * This field holds an ArrayList of all GAmeItems in the carried by the Player.
	 */
	private static ArrayList<GameItem> inventory;

	/**
	 * This field holds the number of moves between locations the Player has made.
	 */
	private static int moves;

	/**
	 * This field holds the value for the maximum combined weight of items in the
	 * Players inventory.
	 */
	private static int maxWeight;

	/**
	 * This field holds an Array List of status tags for the Player. Any tag in this
	 * list will have a status of true.
	 */
	private static ArrayList<String> status;
	static
	{
		inventory = new ArrayList<GameItem>();
		moves = 0;
		maxWeight = 12;
		status = new ArrayList<String>();
	}

	/**
	 * This constructor is Private so there can not be an instance of the Player
	 * class. All of the methods of this class are static.
	 */
	private Player()
	{
	}

	/**
	 * This setter method will update the Player's current location, set the previous
	 * location to the current location, and increment the number of moves for the Player.
	 * @param newLocation The GameLocation to set as the current location.
	 */
	public static void location(GameLocation newLocation)
	{
		location(newLocation, true);
	}

	/**
	 * This setter method will update the Player's current location, set the previous
	 * location to the current location, and increment the number of moves for the Player.
	 * @param newLocation The GameLocation to set as the current location.
   * @param describeNewLocation This is whether or not to output the new location's description.
	 */
	public static void location(GameLocation newLocation, boolean describeNewLocation)
	{
		moves++;
		previousLocation = location;
		location = newLocation;
    
    if(describeNewLocation)
    {
      location().describe();
    }
	}

	/**
	 * This setter method is used to assign a new weight maximum for the Player.
	 * @param newMaxWeight The new maximum weight value to assign.
	 */
	public static void maxWeight(int newMaxWeight)
	{
		maxWeight = newMaxWeight;
	}

	/**
	 * This method will either set or unset the Player's status for the given name.
	 * @param statusItem The String name of the status item to update.
	 */
	public static void updateStatus(String statusItem)
	{
		if (status.contains(statusItem))
		{
			status.remove(statusItem);
		}
		else
		{
			status.add(statusItem);
		}
	}

	/**
	 * This getter method will get the number of moves taken by the Player.
	 * @return The number of moves made by the Player.
	 */
	public static int moves()
	{
		return moves;
	}

	/**
	 * This getter method gets the current maximum weight for the Players inventory.
	 * @return The maximum inventory weight value.
	 */
	public static int maxWeight()
	{
		return maxWeight;
	}

	/**
	 * This getter method will calculate the combined weight of all the items in
	 * the Players inventory.
	 * @return The combined weight of all the GameItems carried by the Player.
	 */
	public static int inventoryWeight()
	{
		int weight = 0;
		for (GameItem item : inventory)
		{
			weight += item.weight();
		}
		return weight;
	}

	/**
	 * This getter method will get the Player's current location.
	 * @return The GameLocation that the Player is currently in.
	 */
	public static GameLocation location()
	{
		return location;
	}

	/**
	 * This getter method will get the Player's previous location.
	 * @return The GameLocation that the Player is was in last.
	 */
	public static GameLocation previousLocation()
	{
		return previousLocation;
	}

	/**
	 * This getter method is used to retrieve a single item from the Player's inventory
	 * by name.
	 * @param itemName The String name of the item to retrieve.
	 * @return The GameItem object that has the specified name, or null if none is found.
	 */
	public static GameItem item(String itemName)
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
	 * This method is used to determine if the item with the given name is currently
	 * in the Player's inventory.
	 * @param itemName The String name of the item to look for.
	 * @return True if the Player is carrying the item, false otherwise.
	 */
	public static boolean hasItem(String itemName)
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
	 * This method will check if the player has been marked with a given status.
	 * @param statusItem The String name of the status item
	 * @return True if the Player is marked as having the specified status, false otherwise.
	 */
	public static boolean checkStatus(String statusItem)
	{
		if (status.contains(statusItem))
		{
			return true;
		}
		return false;
	}

	/**
	 * This method will move the Player to the location that is in the specified direction.
	 * @param direction The String direction to move in.
	 */
	public static void move(String direction)
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
	 * This method will take an item from the Player's current location and place
	 * it into their inventory.
	 * @param itemName The String name of the item to pick up.
	 */
	public static void pickUpItem(String itemName)
	{
		GameLocation location = location();
		GameItem item = location.item(itemName);

		if (item != null)
		{
			if (item.isMovable())
			{
				if (inventoryWeight() + item.weight() > maxWeight())
				{
					Output.add("You are carrying too much weight.");
					return;
				}

				location.items().remove(item);
				inventory.add(item);
				Output.add(item.pickUpText());
			}
			else
			{
				Output.add("You cannot move the " + itemName + ".");
			}
		}
		else
		{
			Output.add("There is no " + itemName + " in this location.");
		}
	}

	/**
	 * This method will take an item from the Player's inventory and place
	 * it into their current locations items list.
	 * @param itemName The String name of the item to drop.
	 */
	public static void dropItem(String itemName)
	{
		GameItem item = item(itemName);

		if (item != null)
		{
			inventory.remove(item);
			location.items().add(item);
		}
		else
		{
			Output.add("There is no " + itemName + " in your inventory.");
		}
	}

	/**
	 * This method will add the Player's inventory to the output.
	 */
	public static void displayInventory()
	{
		String output = "Your Inventory:";
		output += Output.underline(output);

		if (inventory.size() > 0)
		{
			for (GameItem item : inventory)
			{
				output += "\n<" + item.name() + ">";
			}
		}
		else
		{
			output += "\n<none>";
		}

		output +=
  "\n\nTotal Weight: " + inventoryWeight() + " / " + maxWeight();
		Output.add(output);
	}

	/**
	 * This method will begin a conversation with the actor in the Player's current
	 * location who has the specified name.
	 * @param actorName The String name of the GameActor to speak to.
	 */
	public static void speakTo(String actorName)
	{
		for (GameActor actor : location.actors())
		{
			if (actor.name().equalsIgnoreCase(actorName.trim()))
			{
				actor.initiateDialog();
			}
		}
	}

	/**
	 * This method is used to add the description for the specified GameObject to
	 * the output.
	 * @param whatToExamine
	 */
	public static void examine(String whatToExamine)
	{
		if (whatToExamine == null || whatToExamine.equals(""))
		{
			whatToExamine = location.name();
		}

		for (GameItem item : inventory)
		{
			if (item.name().equalsIgnoreCase(whatToExamine))
			{
				item.describe();
				return;
			}
		}

		for (GameItem item : location.items())
		{
			if (item.name().equalsIgnoreCase(whatToExamine))
			{
				item.describe();
				return;
			}
		}

		for (GameActor actor : location.actors())
		{
			if (actor.name().equalsIgnoreCase(whatToExamine))
			{
				actor.describe();
				return;
			}
		}

		if (location.name().equalsIgnoreCase(whatToExamine))
		{
			for (GameItem item : location.items())
			{
				if (item.isHidden())
				{
					item.hidden(false);
				}
			}
			for (GameActor actor : location.actors())
			{
				if (actor.hidden())
				{
					actor.hidden(false);
				}
			}

			location.revealExits();

			location.describe();
			return;
		}
	}

	/**
	 * This method will increment the Player's moves without doing anything else.
	 */
	public static void skipTurn()
	{
		Output.add("You wait a few minutes, but nothing happens.");
		moves++;
	}

	/**
	 * This method will remove a specified item from the Player's inventory and give it to
	 * the specified actor.
	 * @param itemName The String name of the item to give.
	 * @param actor The GameActor to give the item to.
	 */
	public static void giveItem(String itemName, GameActor actor)
	{
		dropItem(itemName);
		actor.pickUpItem(itemName);
	}
}
