package Adventure.API;

import java.util.*;

/**
 * This interface is an extension to the basic GameCommand interface that adds the functionality for handling aliases and
 * prepositions. Aliases are used to allow a GameAction to be referred to by multiple names (for example, "Get" could
 * also be "Take"). Prepositions are used so that the direct object of the current operation can operate upon the indirect
 * object. Prepositions are single word strings such as "on" or "with" that will seperate the name of the direct object
 * and the name of the indirect object in the player's input string. An example of this would be a "Use" command, where
 * the player typed "use key on lock" as the input string. The name of the direct object, the key, is seperated by the preposition
 * "on" from the name of indirect object, the lock. Each GameAction maintains its own list of valid prepositions, since
 * some prepositions make sense for some commands but not for others.
 */
public abstract interface GameAction
    extends GameCommand
{
    /**
     * This method is used to add a new alias to the list of aliases for this GameAction. It should validate the name using the
     * ComponentValidator.validateCommandName() method.
     * 
     * @param alias The new string alias to be assigned to this GameAction.
     */
    public abstract void addAlias( String alias );

    /**
     * This method is used to add a new preposition to the list of valid prepositions for this GameAction.
     * 
     * @param preposition The string preposition to be added to the list.
     */
    public abstract void addPreposition( String preposition );

    /**
     * This method is used to retrieve a copy of the list of valid prepositions for this GameAction.
     * 
     * @return A list of valid prepositions for this GameAction.
     */
    public abstract ArrayList<String> aliasList();

    /**
     * This method is used to determine if the given string alias is associated with this GameAction.
     * 
     * @param alias The string alias name to check for.
     * @return True if the alias is in the list for this GameAction, false if it is not.
     */
    public abstract boolean hasAlias( String alias );

    /**
     * This method is used to determine if the given preposition is in the list of valid prepositions for
     * this GameAction.
     * 
     * @param preposition The string preposition to check for.
     * @return True if the preposition is found in the list, false if it is not.
     */
    public abstract boolean hasPreposition( String preposition );

    /**
     * This method will get a copy of the list of valid prepositions for this GameAction.
     * 
     * @return A copy of the list of valid prepositions for this GameAction.
     */
    public abstract ArrayList<String> prepositionList();

    /**
     * This method is used to remove an alias associated with this GameAction.
     * 
     * @param alias The string alias to be removed.
     */
    public abstract void removeAlias( String alias );

    /**
     * This method is used to remove a preposition from the list of known prepositions for this GameAction.
     * 
     * @param preposition The string preposition to be removed from the list.
     */
    public abstract void removePreposition( String preposition );
}
