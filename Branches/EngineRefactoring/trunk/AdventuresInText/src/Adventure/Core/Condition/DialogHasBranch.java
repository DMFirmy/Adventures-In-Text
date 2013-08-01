package Adventure.Core.Condition;

import Adventure.API.*;

import Adventure.Base.*;

import java.util.*;
import java.util.Map.*;

/**
 * This condition will check a given GameDialog to see if it doesn't have the "has run" status.
 */
public class DialogHasBranch
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:5942799252801852703" )
    private static final long serialVersionUID = 1L;

    private String branchName;

    private GameDialog dialog;

    /**
     * This constructor will build and set up the condition.
     *
     * @param name The GameComponent name to be assigned to this condition.
     * @param theDialog The GameDialog to be checked.
     * @param branchName The name of the branch dialog to check for.
     */
    public DialogHasBranch( String name, GameDialog theDialog, String branchName )
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
     * @param branchName The name of the branch dialog to check for.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public DialogHasBranch( String name, GameDialog theDialog, String branchName, boolean negated )
    {
        super( name, negated );
        this.dialog = theDialog;
        this.branchName = branchName;
    }

    /**
     * Checks to see if the given GameDialog has a branch with a given name.
     *
     * @return True if the dialog has run, false if it has not.
     */
    public boolean runConditionCheck()
    {
        Iterator branches = this.dialog.branchList().entrySet().iterator();

        while ( branches.hasNext() )
        {
            Entry<String, GameDialog> branch = ( Entry<String, GameDialog> ) branches.next();

            if ( branch.getKey().equalsIgnoreCase( this.branchName ) )
            {
                return true;
            }
        }
        return false;
    }
}
