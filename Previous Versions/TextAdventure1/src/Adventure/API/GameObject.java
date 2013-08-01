package Adventure.API;

/**
 * This interface represents any entity that can be manipulated by this game engine.
 * It is at the root of the inheritance structure for everything that is part of
 * the Adventure.API and Adventure.Component classes, and all classes from the
 * Adventure.Core package utilize this interface and it's child interfaces to manage
 * the flow of the game. All components, minimally, must implement this interface
 * if they are to be included as part of the game.
 * @author Firmy
 * @version 1.0
 */
public interface GameObject
{
	/**
	 * This getter method will get the name for this GameObject.
	 * @return The String name for this GameObject.
	 */
	abstract String name();

	/**
	 * This getter method will get the description assigned for this object.
	 * @return The String description for this GameObject.
	 */
	abstract String description();

	/**
	 * This setter method is used to assign a new name to this GameObject.
	 * @param newName The new String name to assign to this GameObject.
	 */
	abstract void name(String newName);

	/**
	 * This method is used to send the description for this GameObject directly to output.
	 */
	abstract void describe();

	/**
	 * This setter method is used to assign a new description to this GameObject.
	 * @param newDescription The new String description to assign to this GameObject.
	 */
	abstract void description(String newDescription);
}
