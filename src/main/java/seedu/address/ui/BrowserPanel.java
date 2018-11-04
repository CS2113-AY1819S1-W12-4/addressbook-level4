package seedu.address.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.InventoryPanelSelectionChangedEvent;
import seedu.address.model.drink.Drink;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String SEARCH_PAGE_URL =
            "https://cs2113-ay1819s1-w12-4.github.io/main/DummySearchPage.html";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    /**
     * Loads page and populates it with variables from the drink object
     * @param drink a valid drink object
     */
    private void loadDrinkPage(Drink drink) {
        String name = "?name=" + drink.getName().toString();
        String sellingPrice = "sellingPrice=" + drink.getRetailPrice().toString();
        String costPrice = "costPrice=" + drink.getCostPrice().toString();
        String stock = "stock=" + drink.getQuantity().toString();
        String earliestBatchDate;
        if(drink.getEarliestBatchDate() == null) {
            earliestBatchDate = "earliestBatchDate=" + "Unavailable";
        } else {
            earliestBatchDate = drink.getEarliestBatchDate().toString();
        }
        loadPage(SEARCH_PAGE_URL + name + "&" + sellingPrice + "&" + costPrice + "&" + stock + "&" + earliestBatchDate);
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handleInventoryPanelSelectionChangedEvent(InventoryPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadDrinkPage(event.getNewSelection());
    }

}
