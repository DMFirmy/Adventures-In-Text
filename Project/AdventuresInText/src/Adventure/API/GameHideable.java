package Adventure.API;

import Adventure.API.Utility.*;

import java.io.*;

/**
 * This interface is not in itself a GameComponent, but instead should only be added to a class that implements
 * the GameLocation, GameExit, GameItem, or GamePawn interfaces. This interface is used to represent a physical
 *  GameObject component such as an item or character that can be hidden from detection by the player.
 */
public interface GameHideable
    extends Serializable
{
    /**
     * This method is used to mark this GameObject component as hidden.
     */
    public abstract void hide();

    /**
     * This method is used to determine if this GameObject component is marked as hidden.
     * 
     * @return True if this component is marked as hidden, false otherwise.
     */
    public abstract boolean isHidden();

    /**
     * This method is used to unmark this component as being hidden.
     */
    public abstract void reveal();

    /**
     * This method is used to assign an new GameObjectHideUtility to handle the hidden status of this component.
     * 
     * @param newHideUtility The new GameObjectHideUtility to set as the active utility for this component.
     */
    public abstract void setHideUtility( GameObjectHideUtility newHideUtility );
}
