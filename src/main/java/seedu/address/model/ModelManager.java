package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.analysis.Analysis;
import seedu.address.analysis.AnalysisManager;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LoginInfo;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.InventoryListChangedEvent;
import seedu.address.model.drink.Drink;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    protected LoginInfoManager loginInfoManager;
    protected final FilteredList<Drink> filteredDrinks;
    protected final InventoryList inventoryList;
    protected final TransactionList transactionList;
    protected final Analysis analysis;

    public ModelManager(ReadOnlyInventoryList readOnlyInventoryList, UserPrefs userPrefs) {
        super();
        requireAllNonNull(readOnlyInventoryList, userPrefs);

        logger.fine("Initializing with inventory list: " + readOnlyInventoryList + " and user prefs " + userPrefs);
        System.out.println(readOnlyInventoryList.getDrinkList().toString());
        inventoryList = new InventoryList(readOnlyInventoryList);
        filteredDrinks = new FilteredList<>(inventoryList.getDrinkList());
        this.loginInfoManager = new LoginInfoManager();
        this.transactionList = new TransactionList();
        analysis = new AnalysisManager(transactionList);
        // TODO: transaction manager, facade for transactions
    }

    /**
     * Initializes a ModelManager with the given inventoryList, userPrefs and transactionList
     */
    public ModelManager(ReadOnlyInventoryList readOnlyInventoryList, UserPrefs userPrefs,
                        LoginInfoManager loginInfoManager, TransactionList transactionList) {

        super();
        requireAllNonNull(readOnlyInventoryList, userPrefs);
        logger.fine("Initializing with inventory list: " + readOnlyInventoryList + " and user prefs " + userPrefs);

        inventoryList = new InventoryList(readOnlyInventoryList);
        filteredDrinks = new FilteredList<>(inventoryList.getDrinkList());
        this.loginInfoManager = loginInfoManager;
        this.transactionList = transactionList;
        analysis = new AnalysisManager(transactionList);
        resetData(readOnlyInventoryList);
        // TODO: transaction manager, facade for transactions
    }



    public ModelManager() {
        this(new InventoryList(), new UserPrefs(), new LoginInfoManager(), new TransactionList());
    }

    @Override
    public void resetData(ReadOnlyInventoryList newData) {
        inventoryList.resetData(newData);
        indicateInventoryListChanged();
    }

    @Override
    public ReadOnlyInventoryList getInventoryList() {
        return inventoryList;
    }

    /**
     * Raises an event to indicate the model has changed
     */
    protected void indicateInventoryListChanged() {
        raise(new InventoryListChangedEvent(inventoryList));
    }

    @Override
    public boolean hasDrink(Drink drink) {
        requireNonNull(drink);
        return inventoryList.hasDrink(drink);
    }


    /*
    @Override
    public void updateDrink(Drink target, Drink editedDrink) {
        requireAllNonNull(target, editedDrink);

        inventoryList.updateDrink(target, editedDrink);
        indicateInventoryListChanged();
    }
    */


    //=========== Filtered Drink List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Drink} backed by the internal list of
     * {@code inventoryList}
     */
    @Override
    public ObservableList<Drink> getFilteredDrinkList() {
        return FXCollections.unmodifiableObservableList(filteredDrinks);
    }

    @Override
    public void updateFilteredDrinkList(Predicate<Drink> predicate) {
        requireNonNull(predicate);
        filteredDrinks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return inventoryList.equals(other.inventoryList)
                && inventoryList.equals(other.inventoryList);
    }

    // ========== stockTaker commands ====================================


    // ========== Accountant commands =================================================


    //=========== Login feature command ==============================================

    @Override
    public void changePassword(UserName userName, Password newHashedPassword) {
        loginInfoManager.changePassword(userName, newHashedPassword);
    }

    @Override
    public LoginInfo getLoginInfo(UserName userName) {
        return loginInfoManager.getLoginInfo(userName);
    }

    @Override
    public boolean isUserNameExist(UserName userName) {
        return loginInfoManager.isUserNameExist(userName);
    }
}
