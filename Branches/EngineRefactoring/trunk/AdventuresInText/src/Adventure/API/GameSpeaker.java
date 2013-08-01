package Adventure.API;

import java.io.*;

/**
 * This interface is not in itself a GameComponent, but instead should only be added to a class that implements
 * the GameLocation, GameExit, GameItem, or GamePawn interfaces. This interface represents any component objects that
 * are able to maintain conversations with the player, and includes all of the functionality for managing the
 * initial GameDialog for this component and for managing the choices and branches used to connect dialogs together.
 */
public abstract interface GameSpeaker
    extends Serializable
{
    /**
     * This method is used to call the initiate() method of the GameDialog that is being stored as the initial dialog
     * for this component.
     */
    public abstract void initiateDialog();

    /**
     * This method is used to get a reference to the initial GameDialog for this component without initiating it.
     * 
     * @return The gameDialog object set as the initial dialog for this component.
     */
    public abstract GameDialog getInitialDialog();

    /**
     * This method is used to set the given GameDialog object as the new initial dialog for this component.
     * 
     * @param newInitialDialog The new GameDialog object to be set as the initial dialog.
     */
    public abstract void setInitialDialog( GameDialog newInitialDialog );

    /**
     * This method is used to set the GameDialog object with the given name as the new initial dialog for this component.
     * 
     * @param newInitialDialogName The name of the new GameDialog object to be set as the initial dialog.
     */
    public abstract void setInitialDialog( String newInitialDialogName );

}
