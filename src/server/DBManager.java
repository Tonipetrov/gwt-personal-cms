package server;

import server.common.PropertiesLoader;
import server.common.ServerConstants;

import java.sql.ResultSet;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:52:59 PM
 * To change this template use File | Settings | File Templates.
 * Operations required here :
 * 1. Insert
 * 2. SelectAll
 * 3. SelectPaginated
 * 4. Update
 */
public class DBManager {

    // Commonly required util/service classes
    PropertiesLoader propsLoader = null;
    Properties dbProps = null;

    // DB Connection parameters
    private Connection conn = null;

    private static DBManager dbManager = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public static DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
            dbManager.initializeDbManager();
        }

        return dbManager;
    }

    private void initializeDbManager() {
        propsLoader = PropertiesLoader.getInstance();
        dbProps = propsLoader.loadPropertyFile(ServerConstants.DBProps_File);

        if (dbProps == null || dbProps.size() < 1) {
            // TODO Handle Error Situation
            System.exit(-1);
        }

        ServerConstants.dbServAddr = dbProps.getProperty("db.serverAddr");
        ServerConstants.dbServPort = dbProps.getProperty("db.serverPort");
        ServerConstants.dbName = dbProps.getProperty("db.name");
        ServerConstants.dbUserName = dbProps.getProperty("db.user");
        ServerConstants.dbUserPass = dbProps.getProperty("db.pass");

        // DB Driver initialization start.
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Cannot find driver!");
            System.exit(-1);
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + ServerConstants.dbServAddr + ":" + ServerConstants.dbServPort +
                    "/" + ServerConstants.dbName, ServerConstants.dbUserName, ServerConstants.dbUserPass);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);
        }
    }

    public void insert(String query) {

    }

    public ResultSet select(String query) {
        StringBuffer list = new StringBuffer();

        list.append(dbProps.getProperty("db.serverAddr"));
        list.append(dbProps.getProperty("db.serverPort"));
        list.append(dbProps.getProperty("db.name"));
        list.append(dbProps.getProperty("db.user"));
        list.append(dbProps.getProperty("db.pass"));
        System.out.println(list);

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            // TODO handle error
            System.out.println("Cannot create statement!");
            closeResultSet();
            stmt = null;
            return null;
        }

        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            // TODO handle error
            System.out.println("Cannot execute : " + query);
            closeResultSet();
            stmt = null;
            rs = null;
            return null;
        }

        return rs;
    }

    public ResultSet selectPaginated(String query, int startIndex, int endIndex) {
        return null;
    }

    public void update(String query) {

    }

    public boolean closeResultSet() {
        boolean errFlag = false;

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { // ignore }
                errFlag = true;
                rs = null;
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { // ignore }
                rs = null;
                stmt = null;
                errFlag = true;
            }
        }

        return !errFlag;
    }
    
}

