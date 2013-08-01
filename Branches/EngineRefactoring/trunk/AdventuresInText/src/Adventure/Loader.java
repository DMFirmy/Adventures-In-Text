package Adventure;

import Adventure.API.*;
import Adventure.API.Utility.*;

import java.io.*;

import java.net.*;

import java.util.*;
import java.util.jar.*;

/**
 * This class is responsible for loading in GameMap objects from .jar files stored in the "Maps" directory. Though this is
 * working, it should be noted that this feature is not complete, nor is it completely tested. In future updates to the
 * Adventures in Text game, this class will be completely revamped, but for now it is here on an experimental only basis.
 */
public class Loader
{
    private static File gameDirectory;

    private static File customContentDirectory;

    static
    {
        gameDirectory = new File( System.getProperty( "user.dir" ) );

        /**
         * TODO: There needs to be a check here for the existance of any folders that will be used by the game engine for
         *       user content. This includes a folder called "Maps" to store any map files that have been added to the game,
         *       another folder called "Saves" to store saved game files, and any other folders that might become required
         *       for loading in custom content. If these folders are found to exist, the game engine needs to check to see
         *       whether it has appropriate read/write permissions for the folders. If they do not exist, the engine should
         *       attempt to create them. If they do not exist and cannot be created, or if the appropriate permissions are
         *       not available for the game engine, there needs to be some appropriate action taken.
         */

        customContentDirectory = new File( getPathForFile( "Custom", true ) );
    }

    /**
     * This class is completely static, so we make the constructor private.
     */
    private Loader()
    {
    }

    public static ArrayList<GameIOUtility> loadIOUtilities()
    {
        ArrayList<GameIOUtility> utilityList = new ArrayList<GameIOUtility>();
        ArrayList<File> contentFiles = getCustomContentFiles();

        for ( File file : contentFiles )
        {
            ArrayList contentItems = loadComponentsFromJar( file.getAbsolutePath(), CustomContentType.CUSTOM_GUI );

            if ( contentItems != null && !contentItems.isEmpty() )
            {
                for ( Object contentItem : contentItems )
                {
                    if ( contentItem instanceof GameIOUtility )
                    {
                        utilityList.add( ( GameIOUtility ) contentItem );
                    }
                }
            }
        }
        return utilityList;
    }

    /**
     * This method will return a list of all the GameMap objects it was able to locate in the .jar files it finds in the
     * "Maps" directory.
     *
     * @return A list of GameMap objects that can be loaded.
     */
    public static ArrayList<GameMap> loadMaps()
    {
        ArrayList<GameMap> mapList = new ArrayList<GameMap>();
        ArrayList<File> contentFiles = getCustomContentFiles();

        for ( File file : contentFiles )
        {
            ArrayList contentItems = loadComponentsFromJar( file.getAbsolutePath(), CustomContentType.CUSTOM_MAP );

            if ( contentItems != null && !contentItems.isEmpty() )
            {
                for ( Object contentItem : contentItems )
                {
                    if ( contentItem instanceof GameMap )
                    {
                        mapList.add( ( GameMap ) contentItem );
                    }
                }
            }
        }
        return mapList;
    }
    
    public static ArrayList<File> getCustomContentFiles()
    {
        File files[] = customContentDirectory.listFiles();
        ArrayList<File> jarFileList = new ArrayList<File>();

        //Iterate over files in the custom content directory
        if ( files != null )
        {
            for ( File file : files )
            {
                // TODO: Add the ability to also load .class files.
                if ( file.isFile() && file.getName().endsWith( ".jar" ) )
                {
                    jarFileList.add( file );
                }
                else
                {
                    //skip folders & non-jar files
                    continue;
                }
            }
        }
        return jarFileList;
    }

    /**
     * This method will scan through a .jar file looking for classes to load. If they are GameMap classes, they are loaded
     * and added to a list to be returned.
     *
     * @param pathToJarFile This is the absolute path to the .jar file to bve scanned
     * @return A list of GameMap objects that were found durring the scan.
     */
    public static ArrayList loadComponentsFromJar( String pathToJarFile, CustomContentType contentType )
    {
        ArrayList myComponents = new ArrayList();

        JarInputStream jarStream = null;
        JarEntry jarEntry = null;
        Class c = null;

        try
        {
            jarStream = new JarInputStream( new FileInputStream( pathToJarFile ) );
            jarEntry = jarStream.getNextJarEntry();

            while ( jarEntry != null )
            {
                c = getClassForJarEntry( jarEntry, pathToJarFile );
                if ( c != null )
                {
                    try
                    {
                        switch ( contentType )
                        {
                        case CUSTOM_MAP:
                            {
                                if ( GameMap.class.isAssignableFrom( c ) )
                                {
                                    GameMap myMap = ( GameMap ) c.newInstance();

                                    if ( myMap != null )
                                    {
                                        myMap.setMapPath( pathToJarFile );
                                        myComponents.add( myMap );
                                    }
                                }
                                break;
                            }
                        case CUSTOM_GUI:
                            {
                                if ( GameIOUtility.class.isAssignableFrom( c ) )
                                {
                                    GameIOUtility myIOUtility = ( GameIOUtility ) c.newInstance();

                                    if ( myIOUtility != null )
                                    {
                                        myComponents.add( myIOUtility );
                                    }
                                }
                            }
                        }
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                }
                jarEntry = jarStream.getNextJarEntry();
            }
            jarStream.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return myComponents;
    }

    private static Class getClassForJarEntry( JarEntry jarEntry, String pathToJarFile )
    {
        URL url = null;
        URLClassLoader ucl = null;
        Class c = null;

        try
        {
            if ( jarEntry.getName().endsWith( ".class" ) )
            {
                String classname = jarEntry.getName().replaceAll( "/", "\\." );

                classname = classname.substring( 0, classname.length() - 6 );

                if ( classname.contains( "$" ) == false )
                {
                    url = new URL( "jar:file:" + pathToJarFile + "!/" );

                    ucl = new URLClassLoader( new URL[]
                                { url } );

                    c = ucl.loadClass( classname );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * This method is a helper for loading GameMap objects from a .jar file.
     *
     * @param c This is the Class object name for the class to be loaded.
     * @return This will return a GameMap object if one can be loaded, or null if not.
     */
    private static GameMap instantiateMapEntry( Class c )
    {
        try
        {
            // This if statement demonstrates how to figure out if a loaded file implements an interface
            if ( GameMap.class.isAssignableFrom( c ) )
            {
                GameMap myMap = ( GameMap ) c.newInstance();
                return myMap;
            }
        }
        catch ( Exception e )
        {
            IO.addLine( "There was an error while loading a map from a .jar file. " );
        }
        return null;
    }

    private static GameIOUtility instantiateIOUtilityEntry( Class c )
    {
        try
        {
            // This if statement demonstrates how to figure out if a loaded file implements an interface
            if ( GameIOUtility.class.isAssignableFrom( c ) )
            {
                GameIOUtility myIOUtility = ( GameIOUtility ) c.newInstance();
                return myIOUtility;
            }
        }
        catch ( Exception e )
        {
            IO.addLine( "There was an error while loading a utility from a .jar file. " );
        }
        return null;
    }

    /**
     * This method will take a String filename and get the absolute path for it. It will not work for directories.
     *
     * @param fileName The String name of the file to be located.
     * @return The String absolute path to the file.
     */
    public static String getPathForFile( String fileName )
    {
        return getPathForFile( fileName, false );
    }

    /**
     * This method will take a String filename and get the absolute path for it.
     *
     * @param fileName The String name of the file to be located.
     * @param includeDirectories Whether or not to also scan directories.
     * @return The String absolute path to the file.
     */
    public static String getPathForFile( String fileName, boolean includeDirectories )
    {
        File foundFile = getFileByName( fileName, null, includeDirectories );

        if ( foundFile != null )
        {
            return foundFile.getAbsolutePath();
        }
        else
        {
            return "";
        }
    }

    /**
     * This is a helper method that will get the File object for a file with a given name. It looks recursively in all of
     * the subfolders of the working directory.
     *
     * @param fileName The String name of the file to be found.
     * @param workingDirectory The File object for the directory to be scanned.
     * @param includeDirectories True if you also want to locate directores with the given name, false if you only want to search files.
     * @return The File object for the file with the given name if it is found, or null otherwise.
     */
    private static File getFileByName( String fileName, File workingDirectory, boolean includeDirectories )
    {
        if ( workingDirectory == null )
        {
            workingDirectory = gameDirectory;
        }

        File foundFile = null;
        File[] fileListing = workingDirectory.listFiles();

        if ( fileListing != null )
        {
            for ( File file : fileListing )
            {
                if ( file.isFile() )
                {
                    if ( file.getName().equalsIgnoreCase( fileName ) )
                    {
                        foundFile = file;
                        break;
                    }
                }
                else if ( file.isDirectory() )
                {
                    File temp = null;
                    if ( includeDirectories && file.getName().equalsIgnoreCase( fileName ) )
                    {
                        temp = file;
                    }
                    else
                    {
                        temp = getFileByName( fileName, file, includeDirectories );
                    }

                    if ( temp != null )
                    {
                        foundFile = temp;
                        break;
                    }
                }
            }
        }
        return foundFile;
    }

    /**
     * This method will retrieve the string absolute path for the "Maps" directory.
     *
     * @return The String absolute path for the "Maps" directory.
     */
    public static String getCustomContentDirectoryPath()
    {
        return customContentDirectory.getAbsolutePath();
    }
}
