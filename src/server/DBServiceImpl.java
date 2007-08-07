package server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import client.DBService;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.common.DBQueryConsts;
import server.common.ServerAPIConsts;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:52:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBServiceImpl extends RemoteServiceServlet implements DBService {

    // One Time Service Class Instances and other variables
    DBManager dbManager = null;

    public DBServiceImpl() {
        dbManager = DBManager.getInstance();
    }

    // Menu bar APIs
    public ArrayList getMenuItems(String type) {
        ArrayList menuList = new ArrayList();
        ResultSet rs = null;

        if(ServerAPIConsts.ACTIVE_MENUS.equals(type)) {
            rs = dbManager.select(DBQueryConsts.activeMenuItemsQuery);
        } else if(ServerAPIConsts.INACTIVE_MENUS.equals(type)) {
            rs = dbManager.select(DBQueryConsts.inactiveMenuItemsQuery);
        } else {
            rs = dbManager.select(DBQueryConsts.allMenuItemsQuery);
        }
                
        try {
            while(rs.next()) {
                menuList.add(rs.getString(DBQueryConsts.menuBarNameName).trim());
            }
        } catch (SQLException e) {
            // TODO Exception handling
            return null;
        }

        dbManager.closeResultSet();

        return menuList;
    }

    // Content getters
    public String getFrontPageContent() {
        String content = null;
        ResultSet rs = null;

        rs = dbManager.select(DBQueryConsts.getFrontPageContent);

        try {
            if(rs.next()) {
                content = rs.getString(DBQueryConsts.frontPageContentColumnName).trim();
                System.out.println(content);
            }
        } catch (SQLException e) {
            // TODO Handle exception
            return null;
        }

        dbManager.closeResultSet();

        return content;
    }

}