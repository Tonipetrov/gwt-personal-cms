package client;

import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.core.client.GWT;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:52:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DBService extends RemoteService {
    /**
     * Utility/Convinience class.
     * Use DBService.App.getInstance() to access static instance of DBServiceAsync
     */
    public static class App {
        private static DBServiceAsync ourInstance = null;

        public static synchronized DBServiceAsync getInstance() {
            if (ourInstance == null) {
                ourInstance = (DBServiceAsync) GWT.create(DBService.class);
                ((ServiceDefTarget) ourInstance).setServiceEntryPoint(GWT.getModuleBaseURL() + "WebCMS/DBService");
            }
            return ourInstance;
        }
    }

    // Menu bar APIs
    ArrayList getMenuItems(String type);

    // Content getters
    String getFrontPageContent();

}
