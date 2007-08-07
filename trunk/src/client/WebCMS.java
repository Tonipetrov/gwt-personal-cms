package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:28:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebCMS implements EntryPoint {
    public void onModuleLoad() {
        DBServiceAsync dbService = DBService.App.getInstance();
        ServiceDefTarget service = (ServiceDefTarget) dbService;
        final Label line = new Label("Init");

        AsyncCallback callback = new AsyncCallback() {
            public void onFailure(Throwable caught) {
                line.setText("Failed");
            }

            public void onSuccess(Object result) {
                String text = (String) result;
                line.setText(text);
            }
        };

        dbService.getMenuItems(callback);

        RootPanel.get().add(line);

    }
}
