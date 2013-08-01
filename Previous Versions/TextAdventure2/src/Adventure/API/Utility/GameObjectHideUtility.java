package Adventure.API.Utility;

import Adventure.API.*;

/**
 * This utility object is utilized by GameObjectss for the funcionality of marking them as hidden from the player.
 */
public interface GameObjectHideUtility
    extends GameUtility
{
     /**
      * This method is used to mark this GameObject component as hidden.
      */
    public abstract void hideObject();

     /**
      * This method is used to determine if this GameObject component is marked as hidden.
      * 
      * @return True if this component is marked as hidden, false otherwise.
      */
    public abstract boolean isHidden();

     /**
      * This method is used to unmark this component as being hidden.
      */
    public abstract void revealObject();

     /**
      * This method is used to attach this utility object to the given GameLocation.
      *
      * @param newObject The GameObject to attach this utility object to.
      */
    public abstract void setObjectComponent( GameObject newObject );

     /**
      * This method is used to attach this utility object to the GameLocation with the given name.
      *
      * @param objectName The name of the GameObject to attach this utility object to.
      */
    public abstract void setObjectComponent( String objectName );
}
