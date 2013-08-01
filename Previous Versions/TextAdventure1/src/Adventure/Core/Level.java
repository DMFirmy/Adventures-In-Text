package Adventure.Core;

import Adventure.API.*;

import Adventure.Component.*;

import java.util.ArrayList;

/**
 *
 * @author Firmy
 */
public class Level
{

	/**
	 * This ArrayList holds all of the GameActor, GameItem, and GameLocation objects
	 * that were created by the current GameLevelMap.
	 */
	private static ArrayList<GameObject> gameObjects;

	/**
	 * This ArrayList holds all of the GameDiallog objects that that were created by the
	 * current GameLevelMap.
	 */
	private static ArrayList<GameDialog> dialogs;

	/**
	 * This ArrayList holds all of the GameRuleSet objects that were created by the
	 * current GameLevelMap.
	 */
	private static ArrayList<GameRuleSet> rules;

	/**
	 * This is the currently loaded GameLevelMap object.
	 */
	private static GameLevelMap levelMap;

	/**
	 * This initializer is used to initialize all the ArrayList objects utilized by
	 * this class.
	 */
	static
	{
		gameObjects = new ArrayList<GameObject>();
		dialogs = new ArrayList<GameDialog>();
		rules = new ArrayList<GameRuleSet>();
	}

	/**
	 * This constructor is Private so there can not be an instance of the Level
	 * class. All of the methods of this class are static.
	 */
	private Level()
	{
	}

	/**
	 * This getter method is used to retrieve the list of GameObjects for the level.
	 * @return The ArrayList of GameObjects for the currently loaded level.
	 */
	public static ArrayList<GameObject> gameObjects()
	{
		return gameObjects;
	}

	/**
	 * This getter method is used to retrieve the list of GameRuleSets for the level.
	 * @return The ArrayList of GameRuleSets for the currently loaded level.
	 */
	public static ArrayList<GameRuleSet> rules()
	{
		return rules;
	}

	/**
	 * This method is used to retrieve the current level map.
	 * @return The currently loaded GameLevelMap object.
	 */
	public static GameLevelMap levelMap()
	{
		return levelMap;
	}

	/**
	 * This getter method is used to retrieve a single GameLocation from the game objects
	 * list that has the name specified.
	 * @param locationName The String name of the location to retrieve.
	 * @return The GameLocation object that has the specified name, or null if none is found.
	 */
	public static GameLocation location(String locationName)
	{
		for (GameObject location : gameObjects)
		{
			if (location instanceof GameLocation &&
				location.name().equalsIgnoreCase(locationName))
			{
				return (GameLocation)location;
			}
		}
		return null;
	}

	/**
	 * This getter method is used to retrieve a single GameActor from the game objects
	 * list that has the name specified.
	 * @param actorName The String name of the location to retrieve.
	 * @return The GameActor object that has the specified name, or null if none is found.
	 */
	public static GameActor actor(String actorName)
	{
		for (GameObject actor : gameObjects)
		{
			if (actor instanceof GameActor &&
				actor.name().equalsIgnoreCase(actorName))
			{
				return (GameActor)actor;
			}
		}
		return null;
	}

	/**
	 * This getter method is used to retrieve a single GameDialog from the dialogs
	 * list that has the name specified.
	 * @param dialogName The String name of the location to retrieve.
	 * @return The GameDialog object that has the specified name, or null if none is found.
	 */
	public static GameDialog dialog(String dialogName)
	{
		for (GameDialog dialog : dialogs)
		{
			if (dialog.name().equalsIgnoreCase(dialogName))
			{
				return dialog;
			}
		}
		return null;
	}

	/**
	 * This getter method is used to retrieve a single GameItem from the gameObjejcts
	 * list that has the name specified.
	 * @param itemName The String name of the location to retrieve.
	 * @return The GameItem object that has the specified name, or null if none is found.
	 */
	public static GameItem item(String itemName)
	{
		for (GameObject item : gameObjects)
		{
			if (item instanceof GameItem &&
				item.name().equalsIgnoreCase(itemName))
			{
				return (GameItem)item;
			}
		}
		return null;
	}

	/**
	 * This getter method is used to retrieve the list of GameLocations for the level.
	 * @return The ArrayList of GameLocations for the currently loaded level.
	 */
	public static ArrayList<GameLocation> locations()
	{
		ArrayList<GameLocation> locations = new ArrayList<GameLocation>();
		for (GameObject location : gameObjects)
		{
			if (location instanceof GameLocation)
			{
				locations.add((GameLocation)location);
			}
		}
		return locations;
	}

	/**
	 * This getter method is used to retrieve the list of GameItem for the level.
	 * @return The ArrayList of GameItem for the currently loaded level.
	 */
	public static ArrayList<GameItem> items()
	{
		ArrayList<GameItem> items = new ArrayList<GameItem>();
		for (GameObject item : gameObjects)
		{
			if (item instanceof GameItem)
			{
				items.add((GameItem)item);
			}
		}
		return items;
	}

	/**
	 * This getter method is used to retrieve the list of GameDialogs for the level.
	 * @return The ArrayList of GameDialogs for the currently loaded level.
	 */
	public static ArrayList<GameDialog> dialogs()
	{
		return dialogs;
	}

	/**
	 * This getter method is used to retrieve the list of GameActors for the level.
	 * @return The ArrayList of GameActors for the currently loaded level.
	 */
	public static ArrayList<GameActor> actors()
	{
		ArrayList<GameActor> actors = new ArrayList<GameActor>();
		for (GameObject actor : gameObjects)
		{
			if (actor instanceof GameActor)
			{
				actors.add((GameActor)actor);
			}
		}
		return actors;
	}

	/**
	 * This method is used to create a GameLevelMap to be loaded and to display the
	 * initial output for it.
	 */
	public static void setupLevel()
	{
		// This method will serve as the public level setup call in version 2.
		levelMap = new LevelMap();
		levelMap.buildLevel();
		Output.display();
	}

	/**
	 * This method is used to reset the the Level back to an unloaded state. It will
	 * present the user the option to reload it or to quit the game.
	 */
	public static void reset()
	{
		gameObjects.clear();
		rules.clear();
		levelMap = null;

		Output.add("Would you like to play again? (y/n)");
		Output.display(true);
		String answer = "";
		while (!answer.trim().equalsIgnoreCase("y") ||
			   !answer.trim().equalsIgnoreCase("n"))
		{
			answer = Engine.getUnprocessedInput();
			if (answer.trim().equalsIgnoreCase("y"))
			{
				Level.setupLevel();
				return;
			}
			else if (answer.trim().equalsIgnoreCase("n"))
			{
				Engine.endGame("Thank you for playing Adventures in Text...\n");
				return;
			}
		}
	}
}
