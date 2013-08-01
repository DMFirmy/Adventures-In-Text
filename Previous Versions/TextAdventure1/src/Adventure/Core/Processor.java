package Adventure.Core;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * This class is used to process input from the Player and determine which actions
 * are to be taken based upon the input it has received.
 * @author Firmy
 * @version 1.0
 */
public class Processor
{
	/**
	 * This TreeMap holds all of the valid commands that this game can recognize.
	 */
	private static final TreeMap<String, String> verbs;

	/**
	 * This TreeMap holds all of the valid directions that this game can recognize.
	 */
	private static final TreeMap<String, String> directions;

	/**
	 * This array holds a prepositions that will be recognized by the parser as separating
	 * a direct object and an indirect object from an input string.
	 */
	private static String[] prepositions;
	/**
	 * This initializer sets up the TreeMap objects for verbs and directions and
	 * the array of prepositions.
	 */
	static
	{
		verbs = new TreeMap<String, String>();
		verbs.put("?", "help"); //0
		verbs.put("a", "wait"); //1
		verbs.put("c", "close"); //2
		verbs.put("exit", "quit"); //3
		verbs.put("g", "get"); //4
		verbs.put("k", "speak"); //5
		verbs.put("l", "look"); //6
		verbs.put("p", "open"); //7
		verbs.put("r", "drop"); //8
		verbs.put("u", "whereami"); //9
		verbs.put("v", "inventory"); //10
		verbs.put("x", "examine"); //11

		directions = new TreeMap<String, String>();
		directions.put("d", "down"); //0
		directions.put("e", "east"); //1
		directions.put("i", "in"); //2
		directions.put("n", "north"); //3
		directions.put("ne", "northeast"); //4
		directions.put("nw", "northwest"); //5
		directions.put("o", "out"); //6
		directions.put("s", "south"); //7
		directions.put("se", "southeast"); //8
		directions.put("sw", "southwest"); //9
		directions.put("u", "up"); //10
		directions.put("w", "west"); //11

		prepositions = new String[] { "on", "to", "from", "with" };
	}

	/**
	 * This constructor is Private so there can not be an instance of the Processor
	 * class. All of the methods of this class are static.
	 */
	private Processor()
	{
	}

	/**
	 * This method is used to ensure that regardless of whether the key or value for
	 * any valid direction is passed in, that only the value will be used. This will
	 * allow the Processor to treat "n" and "north" the same (or whatever the case
	 * might be).
	 * @param direction The String direction to check.
	 * @return The appropriate value from the directions TreeMap
	 */
	public static String directionName(String direction)
	{
		for (Entry<String, String> d : directions.entrySet())
		{
			if (d.getKey().toString().equalsIgnoreCase(direction) ||
				d.getValue().toString().equalsIgnoreCase(direction))
			{
				return d.getValue().toString();
			}
		}
		return "";
	}

	/**
	 * This method will take a given direction and return it's index in the directions
	 * TreeMap.
	 * @param direction The String direction to search for.
	 * @return The index in the directions TreeMap for the specified direction.
	 */
	private static int directionIndex(String direction)
	{
		int directionCount = 0;
		for (Entry<String, String> d : directions.entrySet())
		{
			if (d.getKey().equals(direction) || d.getValue().equals(direction))
			{
				return directionCount;
			}
			directionCount++;
		}
		return -1;
	}

	/**
	 * This method will take a given verb and return it's index in the verbs TreeMap.
	 * @param verb The String verb to search for.
	 * @return The index in the verbs TreeMap for the specified direction.
	 */
	private static int verbIndex(String verb)
	{
		int verbCount = 0;
		for (Entry<String, String> v : verbs.entrySet())
		{
			if (v.getKey().equals(verb) || v.getValue().equals(verb))
			{
				return verbCount;
			}
			verbCount++;
		}
		return -1;
	}

	/**
	 * This method is responsible for a majority of the grunt work in converting a
	 * string of user input into commands that can be executed. Based upon the command
	 * that is entered, this method will also call whatever method is appropriate.
	 * @param lineInput
	 */
	public static void processCommands(String lineInput)
	{
		/*
		 * commands[0] == Either a verb or a direction will | Always a single word
		 * commands[1] == The name of the direct object | Every character until a preposition
		 * commands[2] == Whatever preposition is used, if one is.
		 * commands[3] == The name of the indirect object, if there is one. | The end of the string
		 */
		String[] commands = extractCommands(lineInput.toLowerCase().trim());
		// This variable will store the switch value if this commands[0] is a verb.
		int verbSwitch = -1;
		// This variable will store the switch value if this commands[0] is a direction.
		int directionSwitch = -1;

		/*
		 * This loop will check commands[0] to see if it is a recognized verb. If it
		 * is, verbSwitch is set to the index of the correct verb.
		 */
		verbSwitch = verbIndex(commands[0]);

		/*
		 * If commands[0] is not a verb, it must be a direction instead. If it is not,
		 * then the command can not be recognized, and a default message should be displayed.
		 */
		if (verbSwitch == -1)
		{
			directionSwitch = directionIndex(commands[0]);
		}

		/*
		 * Now that we have figured out if commands[0] can be recognized as either a
		 * verb or a direction, we can call the correct methods based on the command.
		 */
		if (verbSwitch == -1 && directionSwitch == -1)
		{
			/*
			 * Since commands[0] is neither a verb nor a direction that we know, we output
			 * a default message that we can't understand the command.
			 */

			Output.add("I do not understand that command.");
		}
		else if (verbSwitch > -1)
		{
			/*
			 * Here we have a recognized verb, so we can use a switch statement to call
			 * whatever methods we need to to perform the command we have been given.
			 */
			switch (verbSwitch)
			{
				case 6: // look
					Player.location().describe();
					break;
				case 11: // examine
					Player.examine(commands[1]);
					break;
				case 5: // speak
					Player.speakTo(commands[1]);
					break;
				case 9: // whereami
					Player.location().showName();
					break;
				case 1: // wait
					Player.skipTurn();
					break;
				case 4: // get
					Player.pickUpItem(commands[1]);
					break;
				case 8: // drop
					Player.dropItem(commands[1]);
					break;
				case 7: // open
				case 2: // close
				case 10: // inventory
					Player.displayInventory();
					break;
				case 0: // help
					showHelp();
					break;
				case 3: // quit
					Level.reset();
					return;
				default:
					Output.add("I do not understand that command.");
					break;
			}
		}
		else if (directionSwitch > -1)
		{
			/*
			 * Here we have a recognized direction, wo we can use a switch statement to
			 * move the player in the correct direction.
			 */
			switch (directionSwitch)
			{
				case 3: // north
					Player.move("n");
					break;
				case 4: // northeast
					Player.move("ne");
					break;
				case 1: // east
					Player.move("e");
					break;
				case 8: // southeast
					Player.move("se");
					break;
				case 7: // south
					Player.move("s");
					break;
				case 9: // southwests
					Player.move("sw");
					break;
				case 11: // west
					Player.move("w");
					break;
				case 5: // northwest
					Player.move("nw");
					break;
				case 10: // up
					Player.move("u");
					break;
				case 0: // down
					Player.move("d");
					break;
				case 2: // in
					Player.move("i");
					break;
				case 6: // out
					Player.move("o");
					break;
				default:
					Output.add("I do not understand that command.");
					break;
			}
		}

		// Now that our commands have been run, we need to apply the game rules.
		Engine.applyRules();

		// Finally, we can display the output for the command results.
		Output.display();
	}

	/**
	 * This method will output a help message to the console.
	 */
	public static void showHelp()
	{
		String output = "Command <Hotkey> : Description";
		output += Output.underline(output) + "\n";
		output += "whereami <u> : Displays the name of the location you are at.\n";
    output += "look <l> : This command will repeat the room description.\n";
		output += "examine <x> : Get details about your location, a specified item or character.\n";
		output += "inventory <i> : Displays a list of what you are carrying.\n";
	  output += "get <g> : Picks up an item and adds it to your inventory.\n";
		output += "drop <r> : Places an item in your inventory on the ground.\n";
		output += "speak <k> : Speaks to another character.\n";
	  output += "help <?> : Display this information.\n";
		output += "exit <quit> : Quits this game.\n";
    Output.add(output);
	}

	/**
	 * This method will take a line of input and convert it into an array of 4 strings
	 * that each contain information parsed from the input. The array items will contain:
	 * array[0] == Either a verb or a direction will | Always a single word.
	 * array[1] == The name of the direct object | Every character until a preposition.
	 * array[2] == Whatever preposition is used, if one is.
	 * array[3] == The name of the indirect object, if there is one. | The end of the string.
	 * @param lineInput The String of input to be parsed.
	 * @return The array of parsed Strings.
	 */
	private static String[] extractCommands(String lineInput)
	{
		/*
		 * In order to parse the input string into an array of commands, the first thing
		 * we need to do is break apart the string into individual words that we can
		 * loop through.
		 */
		String[] words = lineInput.split(" ");

		// Here we set up an array to hold the return values.
		String[] commands = new String[] { "", "", "", "" };

		// Here we initialize an index for keeping track of any prepositions.
		int preposition = -1;

		commands[0] = words[0];

		for (int i = 1; i < words.length; i++)
		{
			if (preposition == -1)
			{
				for (int j = 0; j < prepositions.length; j++)
				{
					if (prepositions[j].equals(words[i]))
					{
						preposition = i;
						commands[2] = prepositions[j];
					}
				}
			}

			if (preposition == -1 || i < preposition)
			{
				commands[1] += words[i] + " ";
			}
			else if (i > preposition)
			{
				commands[3] += words[i] + " ";
			}
		}

		for (int j = 0; j < commands.length; j++)
		{
			commands[j] = commands[j].trim();
		}

		return commands;
	}
}
