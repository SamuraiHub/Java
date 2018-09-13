/*
 * CountryInfromation.java
 */

package countries;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.xml.sax.SAXException;

/**
 * The main class of the application.
 */
public class CountryInformation extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */

    public CountryInformation(){};


    @Override protected void startup() {
        try {
            show(new CountryInformationView(this));
        } catch (SAXException ex) {
            Logger.getLogger(CountryInformation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CountryInformation.class.getName()).log(Level.SEVERE, null, ex);
        }

        }


    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of CountryInfromation
     */
    public static CountryInformation getApplication() {
        return Application.getInstance(CountryInformation.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(CountryInformation.class, args);
    }
}

