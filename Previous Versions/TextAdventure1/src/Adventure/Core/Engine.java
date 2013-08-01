package Adventure.Core;

import Adventure.API.GameRuleSet;

import Adventure.Adventure;

import java.util.Scanner;

/**
 * The Engine class is the foundation of the Adventure.Core package. It is responsible
 * for setting up the game, gathering user input, and making sure applying game rules.
 * @author Firmy
 * @version 1.0
 */
public class Engine
{
	/**
	 * This field is used to mark whether the game has begun yet.
	 */
	private static boolean begin;

	/**
	 * This field is used to hold a Scanner object that will retrieve all user inputs.
	 */
	private static Scanner scanner;
	/**
	 * This initializer is used to create the Scanner object needed to gather input.
	 */
	static
	{
		begin = false;
		scanner = new Scanner(System.in);
	}

	/**
	 * This constructor is Private so there can not be an instance of the Engine
	 * class. All of the methods of this class are static.
	 */
	private Engine()
	{
	}

	/**
	 * This method is used to get user input from the keyboard and to send it to the
	 * Processor to be processed.
	 */
	public static void getInput()
	{
		if (!begin)
		{
			scanner.nextLine();
		}
		else
		{
			Processor.processCommands(scanner.nextLine());
		}
	}

	/**
	 * This method is used to get user input, but instead of sending it to the Processor,
	 * the input is returned to the caller.
	 * @return The String of user input.
	 */
	public static String getUnprocessedInput()
	{
		return scanner.nextLine();
	}

	/**
	 * This method will display the game Title Screen to the player when the game
	 * is first loaded. It will then wait for the user to press the Enter key before
	 * continuing.
	 */
	public static void showTitleScreen()
	{
		Output.add("*** Welcome to Adventures in Text ***\n");
		Output.add("Type \"help\" to get a list of commands or \"exit\" to quit.\n");
		Output.add("Press enter to continue...");
		Output.display(true);

		while (!begin)
		{
			Engine.getInput();
			begin = true;
		}
	}

	/**
	 * This method will cause the game to end, outputting whatever text is passed
	 * in to it before ending in an infinite loop, which forces the user to close
	 * the console window. This ensures that a user can't accidently close the window
	 * before having the chance to read the ending text.
	 * @param endingText The text to display as the game ending.
	 */
	public static void endGame(String endingText)
	{
		Adventure.quit();

		Output.add(endingText);
		Output.add("You may now close this window...");
		Output.display(true);

		while (true)
		{
			scanner.nextLine();
		}
	}

	/**
	 * This method will loop through all GameRuleSets for the Level and call their
	 * applyRules() methods. It is called every game turn.
	 */
	public static void applyRules()
	{
		for (GameRuleSet ruleSet : Level.rules())
		{
			ruleSet.applyRules();
		}
	}
}
