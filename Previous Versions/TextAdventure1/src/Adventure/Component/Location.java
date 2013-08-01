package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

// TODO: Add method such as linkExit() that will add connecting exits in 2 locations.

/**
 * This class is used to represent any location within the game world that the
 * Player can explore, and is the primary implementation of the GamrLocation interface.
 * @author Firmy
 * @version 1.0
 */
public class Location
	extends BaseObject
	implements GameLocation
{
	/**
	 * This field holds an ArrayList of all the items in this location.
	 */
	private ArrayList<GameItem> items;

	/**
	 * This field holds a HashMap that represents the exits in this location. The
	 * key is the String direction the exit is in and the value is the GameLocation
	 * that the exit leads to.
	 */
	private HashMap<String, GameLocation> exits;

	/**
	 * This field holds a HashMap that represents all the hidden exits in this
	 * location. The key is the String direction the exit is in and the value is
	 * the GameLocation that the exit leads to.
	 */
	private HashMap<String, GameLocation> hiddenExits;

	/**
	 * This field holds an ArrayList of of all the actors in this location.
	 */
	private ArrayList<GameActor> actors;

	/**
	 * This constructor initializes all of the fields in this class and adds this
	 * location to the level locations list.
	 */
	public Location()
	{
		super();
		actors = new ArrayList<GameActor>();
		items = new ArrayList<GameItem>();
		exits = new HashMap<String, GameLocation>();
		hiddenExits = new HashMap<String, GameLocation>();
		Level.gameObjects().add(this);
	}

	/**
	 * This setter method is used to add a new exit to this location. This overload
	 * will call the addExit(String,GameLocation,boolean) overload with a default of
	 * false so that this exit is not hidden.
	 * @param direction The string direction that this exit is located.
	 * @param exitTo The GameLocation that this exit leads to.
	 */
	public void addExit(String direction, GameLocation exitTo)
	{
		this.addExit(direction, exitTo, false);
	}

	/**
	 * This setter method is used to add a new exit to this location. You can also
	 * mark the exit as hidden or not hidden as well.
	 * @param direction The string direction that this exit is located.
	 * @param exitTo The GameLocation that this exit leads to.
	 * @param hideExit Whether to mark this exit as hidden or not.
	 */
	public void addExit(String direction, GameLocation exitTo,
						boolean hideExit)
	{
		direction = Processor.directionName(direction);

		Boolean exitExists = false;

		for (Entry<String, GameLocation> exit : this.exits.entrySet())
		{
			if (exit.getKey().toString().equals(direction))
			{
				exitExists = true;
				break;
			}
		}

		for (Entry<String, GameLocation> exit : this.hiddenExits.entrySet())
		{
			if (exit.getKey().toString().equals(direction))
			{
				exitExists = true;
				break;
			}
		}

		if (!exitExists)
		{
			if (hideExit)
			{
				this.hiddenExits.put(direction, exitTo);
			}
			else
			{
				this.exits.put(direction, exitTo);
			}
		}
	}

	/**
	 * This getter method is used to retrieve a single GameActor by name from this
	 * locations list of actors.
	 * @param actorName The String name of the item to retrieve.
	 * @return The GameActor with the specified name or null if it is not found.
	 */
	public GameActor actor(String actorName)
	{
		for (GameActor actor : actors)
		{
			if (actor.name().equalsIgnoreCase(actorName.trim()))
			{
				return actor;
			}
		}
		return null;
	}

	/**
	 * This getter method is used to get the location that this location exits to
	 * in the direction that is specified.
	 * @param direction The String direction to get the exit location for.
	 * @return The GameLocation that is the exit for the specified direction, or
	 *      null if none is found.
	 */
	public GameLocation exit(String direction)
	{
		for (Entry<String, GameLocation> exit : this.exits.entrySet())
		{
			if (exit.getKey().toString().equalsIgnoreCase(direction))
			{
				return exit.getValue();
			}
		}

		for (Entry<String, GameLocation> exit : this.hiddenExits.entrySet())
		{
			if (exit.getKey().toString().equalsIgnoreCase(direction))
			{
				return exit.getValue();
			}
		}
		return null;
	}

	/**
	 * This getter method is used to retrieve a single GameItem by name from this
	 * locations list of items.
	 * @param itemName The String name of the item to retrieve.
	 * @return The GameItem with the specified name or null if it is not found.
	 */
	public GameItem item(String itemName)
	{
		for (GameItem item : items)
		{
			if (item.name().equalsIgnoreCase(itemName.trim()))
			{
				return item;
			}
		}
		return null;
	}

	/**
	 * This getter method is used to get the list of items that are in this location.
	 * @return The ArrayList of GameItems in this GameLocation.
	 */
	public ArrayList<GameItem> items()
	{
		return items;
	}

	/**
	 * This getter method is used to get the list of actors that are in this location.
	 * @return The ArrayList of GameActors in this GameLocation.
	 */

	public ArrayList<GameActor> actors()
	{
		return actors;
	}

	@Override
	public void describe()
	{
		Output.add(this.description() + "\n");
		Output.add(this.listActors());
		Output.add(this.listItems());
		Output.add(this.listExits());
	}

	/**
	 * This method is used to output to the screen the name of this Location.
	 */
	public void showName()
	{
		Output.add(this.name());
	}

	/**
	 * This method is used to determine if the GameItem with the specified name is
	 * present in this locations item list.
	 * @param itemName The String name of the item to look for.
	 * @return True if the item is present, false otherwise.
	 */
	public boolean hasItem(String itemName)
	{
		for (GameItem item : this.items)
		{
			if (item.name().toLowerCase().equals(itemName.toLowerCase()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is used to remove the exit that is in the specified direction from
	 * this location.
	 * @param direction The String direction to remove the exit from.
	 */
	public void removeExit(String direction)
	{
		direction = Processor.directionName(direction);

		if (this.exits.containsKey(direction))
		{
			this.exits.remove(direction);
			return;
		}
		else if (this.hiddenExits.containsKey(direction))
		{
			this.hiddenExits.remove(direction);
			return;
		}

	}

	/**
	 * This method will mark all exits in this location that are hidden as not hidden.
	 */
	public void revealExits()
	{
		for (Entry<String, GameLocation> exit : this.hiddenExits.entrySet())
		{
			this.hiddenExits.remove(exit.getKey().toString());
			this.exits.put(exit.getKey(), exit.getValue());
		}
	}

	/**
	 * This method is used to check whether there is an exit in the specified direction.
	 * This overload will call the hasExit(String, boolean) overload with a default
	 * value of false so that hidden exits are not included.
	 * @param direction The String direction to check for an exit.
	 * @return True if an exit is found in the specified direction, false otherwise.
	 */
	public boolean hasExit(String direction)
	{
		return hasExit(direction, false);
	}

	/**
	 * This method is used to check whether there is an exit in the specified direction.
	 * Optionally, hidden exits can also be included in the check.
	 * @param direction The String direction to check for an exit.
	 * @param includeHidden Whether you wish to include hidden exits in the check.
	 * @return True if an exit is found in the specified direction, false otherwise.
	 */
	public boolean hasExit(String direction, boolean includeHidden)
	{
		for (Entry<String, GameLocation> exit : this.exits.entrySet())
		{
			if (exit.getKey().toString().equals(direction))
			{
				return true;
			}
		}
		if (includeHidden)
		{
			for (Entry<String, GameLocation> exit :
				 this.hiddenExits.entrySet())
			{
				if (exit.getKey().toString().equals(direction))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method is used to create a String listing of GameActors in this location
	 * to the be output.
	 * @return The String list of GameActors
	 */
	public String listActors()
	{
		String output = "Characters In Room:";
		output += Output.underline(output) + "\n";

		if (actors().size() > 0)
		{
			String actorString = "";
			for (GameActor actor : actors())
			{
				if (!actor.hidden())
				{
					actorString += "<" + actor.name() + ">\n";
				}
			}
			if (actorString.equals(""))
			{
				output += "<none>\n";
			}
			else
			{
				output += actorString;
			}
		}
		else
		{
			output += "<none>\n";
		}

		return output;
	}

	/**
	 * This method is used to create a String listing of GameItems in this location
	 * to the be output.
	 * @return The String list of GameItems
	 */
	public String listItems()
	{
		String output = "Items in this location:";
		output += Output.underline(output) + "\n";

		if (this.items.size() > 0)
		{
			String items = "";

			for (GameItem item : this.items)
			{
				if (!item.isHidden())
				{
					items += "<" + item.name() + ">\n";
				}
			}

			if (items.equals(""))
			{
				output += "<none>\n";
			}
			else
			{
				output += items;
			}
		}
		else
		{
			output += "<none>\n";
		}

		return output;
	}

	/**
	 * This method is used to create a String listing of exits in this location to
	 * the be output.
	 * @return The String list of exits.
	 */
	public String listExits()
	{
		GameLocation thisExit;
		String output = "Possible Directions:";
		output += Output.underline(output) + "\n";

		if (this.exits.size() > 0)
		{
			for (Entry<String, GameLocation> exit : this.exits.entrySet())
			{
				thisExit = exit.getValue();
				output +=
		"<" + exit.getKey().toString() + ": " + thisExit.name() + ">\n";
			}
		}
		else
		{
			output += "<none>\n";
		}

		return output;
	}
}
