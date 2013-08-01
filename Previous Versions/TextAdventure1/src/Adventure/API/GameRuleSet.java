package Adventure.API;

/**
 * This interface represents a set of game rules that need to be applied each turn.
 * @author Firmy
 * @version 1.0
 */
public interface GameRuleSet
	extends GameObject
{
	/**
	 * This method is used to check various states in the game, and to make any changes
	 * that are required based on current conditions in the game. It is called every
	 * turn as the game progresses.
	 *
	 * NOTE: In the current implementation of this game, all rules are added to a
	 *		single RuleSet, which is instantiated by the only LevelMap available.
	 */
	abstract void applyRules();
}
