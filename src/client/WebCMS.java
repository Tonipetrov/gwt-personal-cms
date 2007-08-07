package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.Window;

import java.util.ArrayList;

import client.ui.MenuBarLeft;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:28:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebCMS implements EntryPoint {
    // UI Components
    DockPanel mainPanel = new DockPanel();
    MenuBarLeft menuBar = new MenuBarLeft();
    HTML frontPageContent = new HTML();
    VerticalPanel frontPageContentPanel = new VerticalPanel();
    ScrollPanel indexPagePanel = new ScrollPanel(frontPageContentPanel);   

    public void onModuleLoad() {
        DBServiceAsync dbService = DBService.App.getInstance();
        ServiceDefTarget service = (ServiceDefTarget) dbService;        

        AsyncCallback callback = new AsyncCallback() {
            public void onFailure(Throwable caught) {
        
            }

            public void onSuccess(Object result) {
                menuBar.loadMenuItems((ArrayList)result);
            }
        };

        AsyncCallback frontPageContentCallback = new AsyncCallback() {
            public void onFailure(Throwable caught) {
                Window.alert("Failed!");
            }

            public void onSuccess(Object result) {
                frontPageContent.setHTML(result.toString().trim());
            }
        };
        
        RootPanel.get().add(mainPanel);
        mainPanel.setWidth("100%");
        mainPanel.setBorderWidth(3);               

        mainPanel.add(menuBar, DockPanel.WEST);
        menuBar.setWidth("30%");

        frontPageContentPanel.add(frontPageContent);
        frontPageContentPanel.setBorderWidth(1);
        mainPanel.add(indexPagePanel, DockPanel.CENTER);
        indexPagePanel.setAlwaysShowScrollBars(true);
        
        dbService.getMenuItems("ACTIVE", callback);
        dbService.getFrontPageContent(frontPageContentCallback);
    }
}
