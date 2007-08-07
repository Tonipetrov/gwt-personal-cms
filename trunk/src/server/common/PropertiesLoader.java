package server.common;

import java.util.Properties;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Siddharth
 * Date: Jul 16, 2007
 * Time: 4:58:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesLoader {

    private static PropertiesLoader propsLoader = null;

    private void Properties() { }

    public static PropertiesLoader getInstance() {

        if (propsLoader == null) {
            propsLoader = new PropertiesLoader();
        }

        return propsLoader;
    }

    public Properties loadPropertyFile(String filePath) {
        try {
            Properties props = new Properties();
            InputStream instr = this.getClass().getResourceAsStream(filePath);
            props.load(instr);
            return props;
        } catch (FileNotFoundException e) {
            // TODO Log the error here
            return null;
        } catch (IOException e) {
            // TODO Log the error here
            return null;
        }
    }

}
