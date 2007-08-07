package client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:52:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DBServiceAsync {
    // Services to be exposed.
    void getMenuItems(AsyncCallback async);
}
