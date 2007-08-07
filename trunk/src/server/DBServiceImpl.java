package server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import client.DBService;

import java.util.ArrayList;

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

    // Services to be exposed.
    public String getMenuItems() {
        StringBuffer query = new StringBuffer("SELECT * FROM emp_data");
        return dbManager.select(query.toString());
    }
}