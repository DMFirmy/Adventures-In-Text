package Adventure.API;

import java.util.*;

/**
 * This interface is used to represent a single branch in a conversation within the game. It contains all of the
 * functionality for initiating and branching conversations to create a vivid storyline.
 */
public interface GameDialog
    extends GameObject
{
    /**
     * This method will retrieve a copy of the list of all the response strings and GameDialog objects that are branched
     * to by this dialog.
     * 
     * @return A mapping of response strings and the GameDialog objects they branch to.
     */
    HashMap<String, GameDialog> branchList();

    /**
     * This method is used to change the text that is output when this dialog is initiated.
     * 
     * @param newDialogText The new string of text to be displayed when this dialog is initiated.
     */
    abstract void setDialogText( String newDialogText );

    /**
     * This method is used to add a new branch to this dialog. When the dialog is initiated, each branch will display
     * the given string of text to the player as a valid response. When the player chooses their reply, the GameDialog
     * that is mapped to the given response text is initiated.
     * 
     * @param optionText The text that is displayed to the player as their response.
     * @param branchDialog The Gamedialog to be initiated when the given response is selected.
     */
    abstract void addBranch( String optionText, GameDialog branchDialog );

    /**
     * This method is used to create a new GameDialog object and set it as new branch for this dialog.
     * 
     * @param optionText The text that is displayed to the player as their response.
     * @param branchName The string component name for the new gamedialog to be created.
     * @param branchText The string of text to be displayed when the new dialog is initited.
     */
    abstract void addBranch( String optionText, String branchName, String branchText );

    /**
     * This method is used to begin the conversation represented by this GameDialog object. It will display
     * the text for this dialog and the choices for which dialog branch is to be initiated next.
     */
    abstract void initiate();
}
