package Adventure.API.Utility;

import java.io.*;

/**
 * This interface serves as a basis for all of the utility objects that the game engine is capable of utilizing to
 * handle various aspects of game processing. These utility objects can be swapped out so that the under-the-hood
 * processing for various aspects of the way the game handles GameComponents. For example, every GameComponent is capable
 * of storing a mapping of string names to an associated set of strings of text. By placing this functionality into
 * an external utility object, not only are we enabeling content developers the ability to replace only portions of the
 * mechanisms that Gamecomponent implementing classes use to do its processing, but it also gives them some foundational'
 * tools that they can use in the creation of their oen custom designs. Please look at the various classes in the
 * Adventure.Base package to see how this is ipmplemented in practice.
 */
public interface GameUtility
    extends Serializable
{
}
