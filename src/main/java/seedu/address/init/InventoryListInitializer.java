package seedu.address.init;

import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_ACCOUNTANT;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_ADMIN;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_MANAGER;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_STOCK_TAKER;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.MainApp;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.CurrentUser;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.events.model.ChangeModelEvent;
import seedu.address.commons.events.model.InitInventoryListEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.InventoryList;
import seedu.address.model.LoginInfoManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.user.accountant.AccountantModelManager;
import seedu.address.model.user.admin.AdminModelManager;
import seedu.address.model.user.manager.ManagerModelManager;
import seedu.address.model.user.stocktaker.StockTakerModelManager;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.Storage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Contain item that has to be init after login
 */
public class InventoryListInitializer {
    public static final Version VERSION = new Version(0, 6, 0, true);
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);


    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;
    protected UserPrefs userPrefs;
    private LoginInfoManager loginInfoList;
    private TransactionList transactionList;


    public InventoryListInitializer (Config config, Storage storage,
                                     UserPrefs userPrefs, LoginInfoManager loginInfoList) {
        this.storage = storage;
        this.userPrefs = userPrefs;
        this.loginInfoList = loginInfoList;
        this.config = config;
        EventsCenter.getInstance().registerHandler(this);
    }

    /**
     * init Drink I/O after login
     */
    public void initAfterLogin() {
        model = initModelManager(storage, userPrefs, loginInfoList);
        logic = new LogicManager (model);
        ui = new UiManager (logic, config, userPrefs);

    }

    @Subscribe
    public void handleinitInventoryListEvent(InitInventoryListEvent event) {
        initAfterLogin ();
    }
    @Subscribe
    public void handleChangeModelEvent(ChangeModelEvent event) {
        model = initModelManager(storage, userPrefs, loginInfoList);
        logic.changeModelAfterReLogin (model);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, UserPrefs userPrefs, LoginInfoManager loginInfoManager) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook ();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }
        if (CurrentUser.getAuthenticationLevel ().equals (AUTH_ADMIN)) {
            return new AdminModelManager (new InventoryList (), userPrefs, loginInfoManager, new TransactionList());
        }
        if (CurrentUser.getAuthenticationLevel ().equals (AUTH_MANAGER)) {
            return new ManagerModelManager (new InventoryList (), userPrefs, loginInfoManager, new TransactionList());
        }
        if (CurrentUser.getAuthenticationLevel ().equals (AUTH_STOCK_TAKER)) {
            return new StockTakerModelManager (new InventoryList () , userPrefs, loginInfoManager,
                    new TransactionList());
        }

        if (CurrentUser.getAuthenticationLevel ().equals (AUTH_ACCOUNTANT)) {
            return new AccountantModelManager (new InventoryList (), userPrefs, loginInfoManager,
                    new TransactionList());
        }

        //TODO: need change inventoryList () to storage when storage is done
        return new ModelManager (new InventoryList () , userPrefs, loginInfoManager, new TransactionList());
    }


}
