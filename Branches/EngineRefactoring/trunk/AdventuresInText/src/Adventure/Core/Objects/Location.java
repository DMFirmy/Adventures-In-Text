package Adventure.Core.Objects;

import Adventure.API.Utility.*;

import Adventure.Base.*;

/**
 */
public class Location
    extends BaseLocation
{
    @SuppressWarnings( "compatibility:-5008920031094477481" )
    private static final long serialVersionUID = 1L;

    /**
     * @param name
     * @param description
     */
    public Location( String name, String description )
    {
        super( name, description );
    }

    /**
     * @param newItemUtility
     */
    public void setItemUtility( GameContainerItemUtility newItemUtility )
    {
    }
}
