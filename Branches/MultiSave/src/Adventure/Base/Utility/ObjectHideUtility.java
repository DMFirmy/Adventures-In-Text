package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

/**
 */
public class ObjectHideUtility
    implements GameObjectHideUtility
{
    @SuppressWarnings( "compatibility:1679062619019339693" )
    private static final long serialVersionUID = 1L;

    /**
     */
    protected GameObject object;

    /**
     * @param object
     */
    public ObjectHideUtility( GameObject object )
    {
        super();
        this.object = object;
    }

    /**
     */
    public void hideObject()
    {
        this.object.setStatus( "hidden", 1 );
    }

    /**
     * @return
     */
    public boolean isHidden()
    {
        return this.object.hasStatus( "hidden" );
    }

    /**
     */
    public void revealObject()
    {
        this.object.removeStatus( "hidden" );
    }

    /**
     * @param newObject
     */
    public void setObjectComponent( GameObject newObject )
    {
        this.object = newObject;
    }

    /**
     * @param objectName
     */
    public void setObjectComponent( String objectName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameContainer && component.getName().equalsIgnoreCase( objectName ) )
            {
                setObjectComponent( ( GameObject ) component );
            }
        }
    }
}
