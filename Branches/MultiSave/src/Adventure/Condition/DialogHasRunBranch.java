package Adventure.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will check a given GameDialog to see if it doesn't have the "has run" status.
 */
public class DialogHasRunBranch
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-6330549393219238696" )
    private static final long serialVersionUID = 1L;

    private String branchName;
    private GameDialog dialog;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theDialog The GameDialog to be checked.
     * @param branchName The name of the branch dialog to check whether it has run or not.
     */
    public DialogHasRunBranch( String name, GameDialog theDialog, String branchName)
    {
        super( name );
        this.dialog = theDialog;
        this.branchName = branchName;
    }

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theDialog The GameDialog to be checked.
     * @param branchName The name of the branch dialog to check whether it has run or not.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public DialogHasRunBranch( String name, GameDialog theDialog, String branchName, boolean negated )
    {
        super( name, negated );
        this.dialog = theDialog;
        this.branchName = branchName;
    }

    /**
     * Checks to see if the given GameDialog has run the branch with the given name yet.
     *
     * @return True if the dialog has run, false if it has not.
     */
    public boolean runConditionCheck()
    {
        if ( this.dialog.hasStatus( branchName ) )
        {
            return true;
        }
        return false;
    }
}
