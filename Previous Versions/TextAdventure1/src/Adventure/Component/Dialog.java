package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Firmy
 */
public class Dialog
	extends BaseObject
	implements GameDialog
{
	/**
	 * This is a HashMap that represents all the possible branches in the conversation
	 * that are available for this dialog. The key for this map is the Dialog
	 * to be branched to, and the value is the text that is displayed to the Player
	 * as a valid response to this dialog to initiate the branching.
	 */
	private HashMap<GameDialog, String> branches;

	/**
	 * This field holds the text to display to the Player when this Dialog is initiated.
	 */
	private String dialogText;

	/**
	 * This field will the status of whether this dialog has been viewed by the Player
	 * already or not,
	 */
	private boolean hasRun;

	/**
	 * This constructor initializes all the fields for this class and adds this Dialog
	 * to the Level.dialogs list.
	 */
	public Dialog()
	{
		branches = new HashMap<GameDialog, String>();
		this.hasRun = false;
		Level.dialogs().add(this);
	}

	/**
	 * This method is used to add a new branch to this dialog.
	 * @param dialog The new Dialog to branch to.
	 * @param optionText The text to display to the Player for this branch.
	 */
	public void addBranch(GameDialog dialog, String optionText)
	{
		this.branches.put(dialog, optionText);
	}

	/**
	 * This method is used to set the main text for this dialog. This is what will be
	 * displayed to the Player when this dialog's initiate() method is called.
	 * @param newDialogText The String of text to set the dialog text to.
	 */
	public void dialogText(String newDialogText)
	{
		dialogText = newDialogText;
	}

	/**
	 * This is a HashMap that represents all the possible branches in the conversation
	 * that are available for this dialog. The key for this map is the Dialog
	 * to be branched to, and the value is the text that is displayed to the Player
	 * as a valid response to this dialog to initiate the branching.
	 * @return The HashMap of dialog branches for this Dialog.
	 */
	public HashMap<GameDialog, String> branches()
	{
		return branches;
	}

	/**
	 * This method will check whether this dialog has been run before.
	 * @return True if the conversation has run, false otherwise.
	 */
	public boolean hasRun()
	{
		return this.hasRun;
	}

	/**
	 *This setter method will set the status for this dialog to determine if it has
	 * been run yet or not.
	 * @param newRunStatus The status to update this Dialogs run status to.
	 */
	public void hasRun(boolean newRunStatus)
	{
		this.hasRun = newRunStatus;
	}

	/**
	 * This method is called whenever this Dialog is invoked as part of a conversation
	 * with the Player. As the conversation is branched from one Dialog to the next,
	 * the initiate method of each branch is called in each subsequent Dialog. This
	 * method handles outputting the dialog text, the choices for conversation branches,
	 * and getting the necessary input to determine which branch (if any) is next to
	 * initiate based on the given input.
	 */
	public void initiate()
	{
		Output.add(dialogText);
		this.hasRun = true;
		if (branches.size() > 0)
		{
			String choice;
			int choiceInt = -1;

			while (choiceInt < 0 || choiceInt > this.branches.size())
			{
				Output.add("\nHow do you respond?\n");
				int i = 0;
				for (Entry<GameDialog, String> branch : branches.entrySet())
				{
					i++;
					Output.add(i + ": " + branch.getValue().toString());
				}
				Output.add("0: End conversation");
				Output.display(true);
				choice = Engine.getUnprocessedInput();
				try
				{
					choiceInt = Integer.parseInt(choice);
				}
				catch (Exception e)
				{
					choiceInt = -1;
					Output.add("I don't understand what you mean.");
          continue;
				}
			}

			if (choiceInt == 0)
			{
				Player.location().describe();
				return;
			}

			int i = 0;
			for (Entry<GameDialog, String> branch : branches.entrySet())
			{
				i++;
				if (i == choiceInt)
				{
					branch.getKey().initiate();
				}
			}
		}
	}
}
