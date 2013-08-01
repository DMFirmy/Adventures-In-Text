package Adventure.API;

/**
 * This interface is used to represent any characters within the game that are not playable. GamePlayer's
 * are human controled, but gameActors are handled by the Engine.
 */
public abstract interface GameActor
    extends GamePawn, GameSpeaker, GameContainer, GameHideable
{
    /**
     * This method will cause this actor to to initiate their initial dialog. It includes the option to initiate
     * the dialog even if this actor is marked as hidden.
     * 
     * @param speakWhileHidden whether or not to allow the dialog to initiate if this actor is marked as hidden.
     */
    public abstract void initiateDialog( boolean speakWhileHidden );
}
