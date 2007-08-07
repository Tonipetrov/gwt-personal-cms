package client.ui;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Composite;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Aug 7, 2007
 * Time: 2:17:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuBarLeft extends Composite {

    VerticalPanel menuBar = new VerticalPanel();
    Label menuItem = null; 

    public void loadMenuItems(ArrayList menuItems) {
        for(int i = 0; i < menuItems.size(); i++) {
            menuItem = new Label(menuItems.get(i).toString().trim());
            System.out.println("Lable : " + menuItem.getText() +
                    " Width : " + menuItem.getOffsetWidth());
            menuBar.add(menuItem);
        }
    }

    public MenuBarLeft() {
        initWidget(menuBar);
    }

}
