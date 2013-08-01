package Adventure.API;

import java.util.HashMap;

/**
 * This interface is used to represent a single spoken dialog between a GameActor
 * and the Player. If the player is able to respond to this dialog, this interface
 * also provides the functionality for branching between dialogs based upon the
 * response that is provided by the Player.
 * @author Firmy
 * @version 1.0
 */
public interface GameDialog
	extends GameObject
{
	/**
	 * This is a HashMap that represents all the possible branches in the conversation
	 * that are available for this dialog. The key for this map is the GameDialog
	 * to be branched to, and the value is the text that is displayed to the Player
	 * as a valid response to this dialog to initiate the branching.
	 * @return The HashMap of dialog branches for this GameDialog.
	 */
	HashMap<GameDialog, String> branches();

	/**
	 * This method is used to set the main text for this dialog. This is what will be
	 * displayed to the Player when this dialog's initiate() method is called.
	 * @param newDialogText The String of text to set the dialog text to.
	 */
	abstract void dialogText(String newDialogText);

	/**
	 * This method is used to add a new branch to this dialog.
	 * @param dialog The new GameDialog to branch to.
	 * @param optionText The text to display to the Player for this branch.
	 */
	abstract void addBranch(GameDialog dialog, String optionText);

	/**
	 * This method is called whenever this dialog is invoked as part of a conversation
	 * with the Player. As the conversation is branched from one dialog to the next,
	 * the initiate method of each branch is called in each subsequent dialog. This
	 * method handles outputting the dialog text, the choices for conversation branches,
	 * and getting the necessary input to determine which branch (if any) is next to
	 * initiate based on the given input.
	 */
	abstract void initiate();

	/**
	 * This method will check whether this dialog has been run before.
	 * @return True if the conversation has run, false otherwise.
	 */
	abstract boolean hasRun();

	/**
	 *This setter method will set the status for this dialog to determine if it has
	 * been run yet or not.
	 * @param newRunStatus The status to update this GameDialogs run status to.
	 */
	abstract void hasRun(boolean newRunStatus);
}
