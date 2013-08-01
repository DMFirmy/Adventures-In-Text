package Adventure.Base;

import Adventure.*;

import Adventure.API.*;

import Adventure.Objects.*;

import java.util.*;
import java.util.Map.*;

/**
 * This is the base implementation of the GameDialog interface.
 */
public abstract class BaseDialog
    extends BaseObject
    implements GameDialog
{

    @SuppressWarnings( "compatibility:-8360997691141370561" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds the mapping of dialog branches.
     */
    protected HashMap<String, GameDialog> branches;

    /**
     * This constructor is used to initialize and set up this dialog.
     *
     * @param name The string name of this dialog.
     * @param dialogText This is the text that will be displayed to the player when this dialog is intitiated.
     */
    public BaseDialog( String name, String dialogText )
    {
        super( name );
        this.setText( "dialog text", dialogText );
        this.setStatus( "has run", 0 );
        this.branches = new HashMap<String, GameDialog>();
    }

    /**
     * This constructor is used to initialize and set up this dialog.
     *
     * @param name The string name of this dialog.
     * @param description The text that will be shown as the description for this dialog.
     * @param dialogText This is the text that will be displayed to the player when this dialog is intitiated.
     */
    public BaseDialog( String name, String description, String dialogText )
    {
        super( name, description );
        this.setText( "dialog text", dialogText );
        this.setStatus( "has run", 0 );
        this.branches = new HashMap<String, GameDialog>();
    }

    /**
     * This method is used to add a new branch to this dialog. When the dialog is initiated, each branch will display
     * the given string of text to the player as a valid response. When the player chooses their reply, the GameDialog
     * that is mapped to the given response text is initiated.
     *
     * @param optionText The text that is displayed to the player as their response.
     * @param branchDialog The Gamedialog to be initiated when the given response is selected.
     */
    public void addBranch( String optionText, GameDialog branchDialog )
    {
        this.branches.put( optionText, branchDialog );
    }

    /**
     * This method is used to create a new GameDialog object and set it as new branch for this dialog.
     *
     * @param optionText The text that is displayed to the player as their response.
     * @param branchName The string component name for the new gamedialog to be created.
     * @param branchText The string of text to be displayed when the new dialog is initited.
     */
    public void addBranch( String optionText, String branchName, String branchText )
    {
        this.branches.put( optionText, new Dialog( branchName, branchText ) );
    }

    /**
     * This method is used to change the text that is output when this dialog is initiated.
     *
     * @param newDialogText The new string of text to be displayed when this dialog is initiated.
     */
    public void setDialogText( String newDialogText )
    {
        this.setText( "dialog text", newDialogText );
    }

    /**
     * This method will retrieve a copy of the list of all the response strings and GameDialog objects that are branched
     * to by this dialog.
     *
     * @return A mapping of response strings and the GameDialog objects they branch to.
     */
    public HashMap<String, GameDialog> branchList()
    {
        return this.branches;
    }

    /**
     * This method is used to begin the conversation represented by this GameDialog object. It will display
     * the text for this dialog and the choices for which dialog branch is to be initiated next.
     */
    public void initiate()
    {
        IO.addLine( this.getText( "dialog text" ) );
        this.setStatus( "has run", 1 );
        if ( branches.size() > 0 )
        {
            int choiceInt = -1;

            while ( choiceInt < 0 || choiceInt > this.branches.size() )
            {
                IO.addLine( "\nHow do you respond?\n" );
                int i = 0;
                for ( Entry<String, GameDialog> branch : this.branches.entrySet() )
                {
                    i++;
                    IO.addLine( i + ": " + branch.getKey().toString() );
                }
                IO.addLine( "0: End conversation" );
                IO.display();
                try
                {
                    choiceInt = IO.getChoice();
                }
                catch ( Exception e )
                {
                    choiceInt = -1;
                    IO.addLine( "I don't understand what you mean." );
                    continue;
                }
            }

            if ( choiceInt == 0 )
            {
                return;
            }

            int i = 0;
            for ( Entry<String, GameDialog> branch : this.branches.entrySet() )
            {
                i++;
                if ( i == choiceInt )
                {
                    branch.getValue().initiate();
                }
            }
        }
    }
}
