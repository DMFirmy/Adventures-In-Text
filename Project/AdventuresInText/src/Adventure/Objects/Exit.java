package Adventure.Objects;

import Adventure.API.*;

import Adventure.Base.*;

/**
 */
public class Exit
    extends BaseExit
{
    @SuppressWarnings( "compatibility:-4788873551431283699" )
    private static final long serialVersionUID = 1L;

    /**
     * @param direction
     * @param location
     */
    public Exit( GameDirection direction, GameLocation location )
    {
        super( direction, location );
    }
}
