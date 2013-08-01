package Adventure;

import Adventure.Core.*;

/**
 * Adventures in Text is a Choose-your-own style text-based game engine. It is not
 * designed to be a game in itself, but is instead an engine on which other games
 * can be run. This version also includes a very simple demo game, which is designed
 * mostly to demonstrate various functionality that the engine provides.
 * @author Firmy
 * @version 1.0
 * @todo Add constructors to Components that provide a default name.
 */
public class Adventure
{
	/**
	 * The game will continue to run as long as this value remains false.
	 */
	protected static boolean quit = false;

	/**
	 * This constructor is Private so there can not be an instance of the Adventure
	 * class. All of the methods of this class are static.
	 */
	private Adventure()
	{
	}

	/**
	 * A call to this function will cause the game to end.
	 */
	public static void quit()
	{
		quit = true;
	}

	/**
	 * This method is the main program entry point, and where the main game loop is.
	 * @param args These are the arguments passed in from the command line, which are not used.
	 */
	public static void main(String[] args)
	{
		Engine.showTitleScreen();

		Level.setupLevel();

		while (!quit)
		{
			Engine.getInput();
		}
	}
}
