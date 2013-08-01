package Adventure.API.Utility;

import Adventure.API.*;

/**
 * This utility object is utilized by GameLocations for the funcionality of managing the speakers within them.
 */
public interface GameLocationSpeakerUtility
    extends GameUtility
{
     /**
      * This method is used to search for a GameSpeaker to find out if it is present in this location. This search
      * will not locate the speaker if it is a GameHideable that is marked as hidden.
      *
      * @param speaker The GameSpeaker to be searched for.
      * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
      */
    public abstract boolean hasSpeaker( GameSpeaker speaker );

     /**
      * This method is used to search for a GameSpeaker to find out if it is present in this location. It includes the
      * option to not locate the speaker even if it is a GameHideable that is marked as hidden.
      *
      * @param speaker The GameSpeaker to be searched for.
      * @param includeHidden Whether or not to include a speaker that is a GameHideable marked as hidden.
      * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
      */
    public abstract boolean hasSpeaker( GameSpeaker speaker, boolean includeHidden );

     /**
      * This method is used to search for a GameSpeaker with the given name to find out if it is present in this location.
      * This search will not locate the speaker if it is a GameHideable that is marked as hidden.
      *
      * @param speakerName The name of the GameSpeaker to be searched for.
      * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
      */
    public abstract boolean hasSpeaker( String speakerName );

     /**
      * This method is used to search for a GameSpeaker with the given name to find out if it is present in this location.
      * It includes the option to not locate the speaker even if it is a GameHideable that is marked as hidden.
      *
      * @param speakerName The name of the GameSpeaker to be searched for.
      * @param includeHidden Whether or not to include a speaker that is a GameHideable marked as hidden.
      * @return True if the GameSpeaker is found within this location or a component within this location, false otherwise.
      */
    public abstract boolean hasSpeaker( String speakerName, boolean includeHidden );

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
