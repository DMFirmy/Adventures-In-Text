package Adventure.API;

import Adventure.*;

/**
 * This interface is used as the base of any executable commands within the game. Whenever the player types a string of
 * input at the prompt, the Engine will loop through its internal list of GameCommand objects to identify which of them
 * should be run. GameCommand objects should be created for each level within the associated GameMap's
 * buildMapCommands() method.
 */
public abstract interface GameCommand
    extends GameComponent
{
    /**
     * This method is used to retrieve the hotkey assigned to this GameCommand.
     * 
     * @return The string hotkey for this GameCommand.
     */
    public abstract String getHotkey();

    /**
     * This method will be called by the Engine whenever this GameCommand is to be run.
     * 
     * @param operation This is the Operation object created by the processor for the current input.
     */
    public abstract void run( Operation operation );

    /**
     * This method is used to assign a new hotkey to this GameCommand. It should validate the name using the
     * ComponentValidator.validateHotkey() method.
     * 
     * @param newHotkey The string to be assigned as the new hotkey for this GameCommand.
     */
    public abstract void setHotkey( String newHotkey );
}
