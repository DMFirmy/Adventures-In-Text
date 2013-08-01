package Adventure.API;

/**
 * This interface is used to define the overall game map in which all GameObjects
 * exist.
 * @author Firmy
 * @version 1.0
 */
public interface GameLevelMap
	extends GameObject
{
	/**
	 * This method is used to set up the entire level map. This is the method that
	 * will be called when the game first loads the level, and it will be used to
	 * instantiate all of the various GameObjects and to set the initial state of
	 * the game.
	 *
	 * NOTE: In the current implementation of this game, there is only a single LevelMap
	 *		and it instantiates the only RuleSet provided.
	 */
	abstract void buildLevel();
}
