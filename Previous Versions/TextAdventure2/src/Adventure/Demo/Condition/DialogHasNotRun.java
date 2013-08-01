package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will check a given GameDialog to see if it has the "has run" status.
 */
public class DialogHasNotRun
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-8455092627758285816" )
    private static final long serialVersionUID = 1L;

    private GameDialog dialog;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition.
     * @param theDialog The GameDialog to be checked.
     */
    public DialogHasNotRun(String name, GameDialog theDialog)
    {
        super( name );
        this.dialog = theDialog;
    }

    /**
     * Checks to see if the given GameDialog has run yet.
     * 
     * @return true if the GameDialog is not run, false if it has.
     */
    public boolean checkCondition()
    {
        if ( !this.dialog.hasStatus( "has run" ) )
        {
            return true;
        }
        return false;
    }
}
