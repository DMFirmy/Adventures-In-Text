package Adventure.Core.Objects;

import Adventure.Base.*;

/**
 */
public class Item
    extends BaseItem
{
    @SuppressWarnings( "compatibility:5116832840453418617" )
    private static final long serialVersionUID = 1L;

    /**
     * @param name
     * @param description
     */
    public Item( String name, String description )
    {
        super( name, description );
    }
}
