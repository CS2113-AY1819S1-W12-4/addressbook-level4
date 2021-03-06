package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LoginInfo;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.InventoryListChangedEvent;
import seedu.address.commons.events.model.TransactionListChangedEvent;
import seedu.address.commons.events.ui.RestartUiEvent;
import seedu.address.model.drink.Drink;
import seedu.address.model.transaction.ReadOnlyTransactionList;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    protected LoginInfoModel loginInfoModel;
    protected final FilteredList<Drink> filteredDrinks;
    protected final InventoryList inventoryList;
    protected final FilteredList<Transaction> filteredTransactions;
    protected final TransactionList transactionList;

    /**
     * Initializes a ModelManager with the given inventoryList, userPrefs and transactionList
     */
    public ModelManager(ReadOnlyInventoryList readOnlyInventoryList, UserPrefs userPrefs,
                        LoginInfoModel loginInfoModel , ReadOnlyTransactionList transactionList) {

        super();
        requireAllNonNull(readOnlyInventoryList, userPrefs);
        logger.fine("Initializing with inventory list: " + readOnlyInventoryList + " and user prefs " + userPrefs);

        inventoryList = new InventoryList(readOnlyInventoryList);
        filteredDrinks = new FilteredList<>(inventoryList.getDrinkList());
        this.loginInfoModel = loginInfoModel;
        this.transactionList = new TransactionList(transactionList);
        filteredTransactions = new FilteredList<>(this.transactionList.getTransactionList());

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
        indicateInventoryListChanged();
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

    // ========== common commands ====================================
    @Override
    public ReadOnlyTransactionList getTransactionList() {
        return transactionList;
    }

    // ========== transactions  =================================================
    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return FXCollections.unmodifiableObservableList(filteredTransactions);
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredTransactions.setPredicate(predicate);
    }

    /**
     * Raises an event to indicate the transactions have changed
     */
    protected void indicateTransactionListChanged() {
        raise(new TransactionListChangedEvent(transactionList));
    }


    //=========== Login feature command ==============================================

    @Override
    public void changePassword(UserName userName, Password newHashedPassword) {
        loginInfoModel.changePassword(userName, newHashedPassword);
    }

    @Override
    public LoginInfo getLoginInfo(UserName userName) {
        return loginInfoModel.getLoginInfo(userName);
    }

    @Override
    public boolean isUserNameExist(UserName userName) {
        return loginInfoModel.isUserNameExist(userName);
    }

    @Subscribe
    private void handleRestartUiEvent(RestartUiEvent event) {
        updateFilteredDrinkList(PREDICATE_SHOW_ALL_DRINKS);
        updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
    }
}
