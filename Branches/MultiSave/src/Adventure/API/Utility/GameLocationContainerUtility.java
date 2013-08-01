package Adventure.API.Utility;

import Adventure.API.*;

/**
 * This utility object is utilized by GameLocations for the funcionality of managing the various containers within them.
 */
public interface GameLocationContainerUtility
    extends GameUtility
{
     /**
      * This method is used to search for a GameContainer to find out if it is present in this location. This search
      * will not locate the container if it is a GameHideable that is marked as hidden.
      *
      * @param container The GameContainer to be searched for.
      * @return True if the GameContainer is found within this location or a component within this location, false otherwise.
      */
    public abstract boolean hasContainer( GameContainer container );

     /**
      * This method is used to search for a GameContainer to find out if it is present in this location. It includes the
      * option to not locate the container even if it is a GameHideable that is marked as hidden.
      *
      * @param container The GameContainer to be searched for.
      * @param includeHidden Whether or not to include a container that is a GameHideable marked as hidden.
      * @return True if the GameContainer is found within this location or a component within this location, false otherwise.
      */
    public abstract boolean hasContainer( GameContainer container, boolean includeHidden );

     /**
      * This method is used to search for a container with a given name to find out if it is present in this location.
      * This search will not locate the container if it is a GameHideable that is marked as hidden.
      *
      * @param containerName The name of the GameContainer to be searched for.
      * @return True if the GameContainer is found within this location or a component within this location, dalse otherwise.
      */
    public abstract boolean hasContainer( String containerName );

     /**
      * This method is used to search for a container with a given name to find out if it is present in this location.
      * It includes the option to not locate the container even if it is a GameHideable that is marked as hidden.
      *
      * @param containerName The name of the GameContainer to be searched for.
      * @param includeHidden Whether or not to include a container that is a GameHideable marked as hidden.
      * @return True if the GameContainer is found within this location or a component within this location, dalse otherwise.
      */
    public abstract boolean hasContainer( String containerName, boolean includeHidden );

    /**
     * This method is used to attach this utility object to the given GameLocation.
     * 
     * @param newLocation The GameLocation to attach this utility object to.
     */
    public abstract void setLocationComponent( GameLocation newLocation );

    /**
     * This method is used to attach this utility object to the GameLocation with the given name.
     * 
     * @param locationName The name of the GameLocation to attach this utility object to.
     */
    public abstract void setLocationComponent( String locationName );
}
