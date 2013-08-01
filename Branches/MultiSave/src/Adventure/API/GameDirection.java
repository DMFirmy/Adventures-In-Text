package Adventure.API;

/**
 * This interface represents a direction that can be traveled within the game world. Directions are represented in the game
 * as GameCommands so that a player can simmply type in the name or hotkey for a given direction to move that way, or they
 * can use the "Move" command along with the name of the direction to move in. Directions are completely arbitrary, and
 * though familiar names like "North" or "West" are known to the engine by default, you could have directions named anything
 * that you might choose.
 */
public interface GameDirection
    extends GameCommand
{
}
