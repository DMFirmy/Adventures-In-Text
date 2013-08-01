package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will check a given GameDialog to see if it doesn't have the "has run" status.
 */
public class DialogHasRun
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-1944839651813882362" )
    private static final long serialVersionUID = 1L;

    private GameDialog dialog;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param theDialog The GameDialog to be checked.
     */
    public DialogHasRun(String name, GameDialog theDialog)
    {
        super( name );
        this.dialog = theDialog;
    }

    /**
     * Checks to see if the given GameDialog has run yet.
     * 
     * @return True if the dialog has run, false if it has not.
     */
    public boolean checkCondition()
    {
        if ( this.dialog.hasStatus( "has run" ) )
        {
            return true;
        }
        return false;
    }
}
