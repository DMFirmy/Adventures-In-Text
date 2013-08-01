package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

/**
 * This class represents the core of the class inheritance structure, and is the
 * primary implementation of the GameObject interface. It represents any entity
 * that can be manipulated by this game engine.
 * @author Firmy
 * @version 1.0
 */
abstract class BaseObject
	implements GameObject
{
	/**
	 * This field holds the name for this object.
	 */
	private String name;

	/**
	 * This field holds the description for this object.
	 */
	private String description;

	/**
	 * This constructor initializes all of the fields for this object.
	 */
	public BaseObject()
	{
		name = "";
		description = "";
	}

	/**
	 * This setter method is used to assign a new name to this GameObject.
	 * @param newName The new String name to assign to this GameObject.
	 */
	public void name(String newName)
	{
		name = newName;
	}

	/**
	 * This setter method is used to assign a new description to this GameObject.
	 * @param newDescription The new String description to assign to this GameObject.
	 */
	public void description(String newDescription)
	{
		description = newDescription;
	}

	/**
	 * This getter method will get the name for this object.
	 * @return The String name for this object.
	 */
	public String name()
	{
		return name;
	}

	/**
	 * This getter method will get the description assigned for this object.
	 * @return The String description for this object.
	 */
	public String description()
	{
		return description;
	}

	/**
	 * This method is used to send the description for this GameObject directly to output.
	 */
	public void describe()
	{
		if (!this.description.isEmpty())
		{
			Output.add(this.description + "\n");
		}
		else
		{
			Output.add("It seems like an ordanary " + this.name() + ".\n");
		}
	}
}
