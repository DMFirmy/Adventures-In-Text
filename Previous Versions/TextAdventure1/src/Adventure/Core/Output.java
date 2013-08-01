package Adventure.Core;

/**
 * This class handles the display and formatting of all output to the console.
 * @author Firmy
 * @version 1.0
 */
public class Output
{
	/**
	 * This field holds the String of text that will be output to the screen.
	 */
	private static String outputBuffer;
	/**
	 * This initializer will set the output buffer to a blank string.
	 */
	static
	{
		outputBuffer = "";
	}

	/**
	 * This constructor is Private so there can not be an instance of the Output
	 * class. All of the methods of this class are static.
	 */
	private Output()
	{
	}

	/**
	 * This method is used to add a string of text to the output buffer, followed
	 * by a line break.
	 * @param text The String of text to be added to the buffer.
	 */
	public static void add(String text)
	{
		outputBuffer += text + "\n";
	}

	/**
	 * This method will display the text in the output buffer to the screen. It calls
	 * the display(boolean) overload and passes in a default value of false.
	 */
	public static void display()
	{
		display(false);
	}

	/**
	 * This method will display the text in the output buffer to the screen. Optionally,
	 * the "What would you like to do?" message can be excluded from the output.
	 * @param cleanOutput True if the message is to be omitted or false if it is to be included.
	 */
	public static void display(boolean cleanOutput)
	{
		clear();
		System.out.print(wordWrap(outputBuffer));
		if (!cleanOutput)
		{
			System.out.println("\nWhat would you like to do?");
			System.out.print(">");
		}
		outputBuffer = "";
	}

	/**
	 * This method will clear out the console window by first adding an 80 character
	 * line of "-" characters, then printing 25 blank lines to the screen. This has
	 * the effect to breaking the output into "pages" that can be scrolled through
	 * and identified easily in the window.
	 */
	public static void clear()
	{
		int i = 0;
		while (i < 80)
		{
			System.out.print("-");
			i++;
		}
		for (i = 0; i < 25; i++)
		{
			System.out.println("\n");
		}
	}

	/**
	 * This method is used to make sure that no string of output text is ever wider
	 * than 80 characters. It will wrap strings so that words never get cut off if
	 * the console window is not long enough to display the string.
	 * @param text The text string to be wrapped.
	 * @return The wrapped text string.
	 */
	public static String wordWrap(String text)
	{
		int width = 80;

		String result = "";
		String[] lines = text.split("\n");

		for (String line : lines)
		{
			int lineLength = 0;
			String[] words = line.split(" ");

			for (String word : words)
			{
				if (word.length() + lineLength >= width - 1)
				{
					result += "\n";
					lineLength = 0;
				}
				result += word + " ";
				lineLength += word.length() + 1;
			}
			result += "\n";
		}

		return result;
	}

	/**
	 * This method will take a string of text and add a line of "-" characters on
	 * the following line that is the same length as the text string.
	 * @param text The string of text to be underlined.
	 * @return The String of "-" characters that will underline the text.
	 */
	public static String underline(String text)
	{
		String underline = "\n";
		for (int i = 0; i < text.length(); i++)
		{
			underline += "-";
		}
		return underline;
	}
}
