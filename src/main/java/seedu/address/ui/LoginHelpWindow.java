package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import seedu.address.commons.core.LogsCenter;
/**
 * Help window on Login Page
 */
public class LoginHelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_FILE_PATH = "/docs/HelpWindow.html";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    @FXML
    private WebView browser;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public LoginHelpWindow(Stage root) {
        super(FXML, root);
        String userGuideUrl = getClass().getResource(USERGUIDE_FILE_PATH).toString();
        browser.getEngine().load(userGuideUrl);
        registerAsAnEventHandler(this);
    }

    /**
     * Creates a new HelpWindow.
     */
    public LoginHelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help loginWindow.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
    }

    /**
     * Returns true if the help loginWindow is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

}
