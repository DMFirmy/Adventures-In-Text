package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import java.util.*;

/**
 * This is the base implementation of the GameComponentStatusUtility interface.
 */
public class ComponentStatusUtility
    implements GameComponentStatusUtility
{
    @SuppressWarnings( "compatibility:-9112909990122692093" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds the mapping of status values.
     */
    protected HashMap<String, Integer> statusList;

    /**
     * This field hold a reference to the component this utility is attached to.
     */
    protected GameComponent component;

    /**
     * This constructor is used to initialize and set up this utility.
     * 
     * @param component The component this utility is attached to.
     */
    public ComponentStatusUtility( GameComponent component )
    {
        super();
        this.statusList = new HashMap<String, Integer>();
        this.component = component;
    }

    /**
     * This method will retrieve the value stored for the attached component for a given status name.
     *
     * @param statusName The string name of the status to check.
     * @return The integer value stored for the given status or a 0 if not found.
     */
    public int getStatus( String statusName )
    {
        if ( statusList.containsKey( statusName ) )
        {
            return statusList.get( statusName );
        }
        else
        {
            return 0;
        }
    }

    /**
     * This method will check if there is a value greater than 0 for the given status name.
     *
     * @param statusName The string name of the status to check.
     * @return True if the given staus name has a value greater than 0, false otherwise.
     */
    public boolean hasStatus( String statusName )
    {
        if ( statusList.containsKey( statusName ) )
        {
            if ( statusList.get( statusName ) == 0 )
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * This method will take the current value stored for a given status name and increment it by 1. If there is no value
     * stored for the given status name, the value of 1 is set.
     *
     * @param statusName The string name of the status to be incremented.
     */
    public void incrementStatus( String statusName )
    {
        int current = 0;
        if ( statusList.containsKey( statusName ) )
        {
            current = statusList.get( statusName );
        }

        current++;
        statusList.put( statusName, current );
    }

    /**
     * This method will completely remove the given status name from the status list.
     *
     * @param statusName The name of the status to be removed.
     */
    public void removeStatus( String statusName )
    {
        statusList.remove( statusName );
    }

    /**
     * This method is used to directly set the value of a given status name to the given value.
     *
     * @param statusName The name of the status to have its value set.
     * @param newStatus The value top set the status with the given name to.
     */
    public void setStatus( String statusName, int newStatus )
    {
        statusList.put( statusName, newStatus );
    }

    /**
     * This method is used to set a refrence to the GameComponent that is using this utility object.
     *
     * @param component The new GameComponent that is using this utility object.
     */
    public void setStatusComponent( GameComponent component )
    {
        this.component = component;
    }

    /**
     * This method is used to set a refrence to the GameComponent with the given name that is using this utility object.
     *
     * @param componentName The name of the new GameComponent that is using this utility object.
     */
    public void setStatusComponent( String componentName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component.getName().equalsIgnoreCase( componentName ) )
            {
                this.setStatusComponent( component );
            }
        }
    }
}
