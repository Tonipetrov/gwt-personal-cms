package server.common;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Aug 7, 2007
 * Time: 2:41:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBQueryConsts {
    // Table Names
    private static final String menuBarTableName = "menu_items";
    private static final String frontPageContentTableName = "front_page";

    // Column names
    private static final String menuBarStatusName = "isDisabled";
    public static final String menuBarNameName = "MenuName";
    public static final String frontPageContentColumnName = "Content";

    // Query Constants
    private static final String SELECT_QUERY_PRE = "SELECT * FROM ";

    // Query Names
    public static final String allMenuItemsQuery = SELECT_QUERY_PRE + menuBarTableName;
    public static final String activeMenuItemsQuery = allMenuItemsQuery + " WHERE " +
            menuBarStatusName + "=false";
    public static final String inactiveMenuItemsQuery = allMenuItemsQuery + " WHERE " +
            menuBarStatusName + "=true";

    public static final String getFrontPageContent = SELECT_QUERY_PRE + frontPageContentTableName;
    
}
