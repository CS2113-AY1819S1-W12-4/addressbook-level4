package seedu.address.model.user.admin;

import java.util.Set;

import seedu.address.analysis.Analysis;
import seedu.address.analysis.AnalysisManager;
import seedu.address.analysis.PurchaseTransactionPredicate;
import seedu.address.analysis.SaleTransactionPredicate;
import seedu.address.analysis.TransactionPeriodPredicate;
import seedu.address.commons.core.LoginInfo;
import seedu.address.commons.events.model.DrinkAttributeChangedEvent;
import seedu.address.model.LoginInfoModel;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyInventoryList;
import seedu.address.model.UserPrefs;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.exceptions.InsufficientQuantityException;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.ReadOnlyTransactionList;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.user.UserName;

/**
 * This is the API model for Admin command
 */
public class AdminModelManager extends ModelManager implements AdminModel {
    private final Analysis analysis = new AnalysisManager(transactionList, filteredTransactions);

    public AdminModelManager(ReadOnlyInventoryList inventoryList, UserPrefs userPrefs,
                             LoginInfoModel loginInfoModel, ReadOnlyTransactionList transactionList) {
        super(inventoryList, userPrefs, loginInfoModel, transactionList);
    }

    /**
     * Raises an event to indicate the model has changed
     */
    public void indicateDrinkAttributesChanged(Drink drink) {
        raise(new DrinkAttributeChangedEvent(drink));
    }

    //===============manager command====================//
    @Override
    public void deleteDrink(Drink target) {
        inventoryList.removeDrink(target);
        indicateInventoryListChanged();
        indicateDrinkAttributesChanged(target);
    }

    @Override
    public void addDrink(Drink drink) {
        inventoryList.addDrink(drink);
        updateFilteredDrinkList(PREDICATE_SHOW_ALL_DRINKS);
        indicateInventoryListChanged();
        indicateDrinkAttributesChanged(drink);
    }

    //=====================Stock taker commands====================
    @Override
    public void sellDrink(Transaction transaction) throws InsufficientQuantityException {
        Price defaultSalePrice = inventoryList.getDefaultSellingPrice(transaction.getDrinkTransacted());
        inventoryList.decreaseQuantity(transaction.getDrinkTransacted(), transaction.getQuantityTransacted());
        Price defaultAmountTransacted = new Price(Float.toString(defaultSalePrice.getValue()
                * transaction.getQuantityTransacted().getValue()));
        transaction.setAmountMoney(defaultAmountTransacted);
        recordTransaction(transaction);

        indicateInventoryListChanged();
        updateFilteredDrinkList(PREDICATE_SHOW_ALL_DRINKS);

        updateFilteredTransactionListToShowAll();

        indicateDrinkAttributesChanged(transaction.getDrinkTransacted());
        indicateTransactionListChanged();
    }

    @Override
    public void buyDrink(Transaction transaction) {
        inventoryList.increaseDrinkQuantity(transaction.getDrinkTransacted(), transaction.getQuantityTransacted());

        Price defaultCostPrice = inventoryList.getDefaultCostPrice(transaction.getDrinkTransacted());
        Price defaultAmountTransacted = new Price(Float.toString(defaultCostPrice.getValue()
                * transaction.getQuantityTransacted().getValue()));
        transaction.setAmountMoney(defaultAmountTransacted);
        recordTransaction(transaction);

        indicateInventoryListChanged();
        updateFilteredDrinkList(PREDICATE_SHOW_ALL_DRINKS);

        updateFilteredTransactionListToShowAll();

        indicateDrinkAttributesChanged(transaction.getDrinkTransacted());
        indicateTransactionListChanged();
    }

    private void recordTransaction(Transaction transaction) {
        transactionList.addTransaction(transaction);
    }


    //=====================Manager command=========================
    @Override
    public void createNewAccount(LoginInfo loginInfo) {
        loginInfoModel.createNewAccount(loginInfo);
    }

    @Override
    public void deleteAccount(UserName userName) {
        loginInfoModel.deleteAccount(userName);
    }

    //===================== Accountant commands ======================
    @Override
    public Price analyseCosts(TransactionPeriodPredicate period) {
        updateFilteredTransactionListToShowPurchases(period);
        return analysis.analyseCost();
    }

    @Override
    public Price analyseRevenue(TransactionPeriodPredicate period) {
        updateFilteredTransactionListToShowSales(period);
        return analysis.analyseRevenue();
    }

    @Override
    public Price analyseProfit(TransactionPeriodPredicate period) {
        updateFilteredTransactionListToShowProfitPeriod(period);
        return analysis.analyseProfit();
    }

    /**
     * Updates the {@code filteredTransactions} with Purchase predicate and {@code period} predicate.
     */
    private void updateFilteredTransactionListToShowPurchases(TransactionPeriodPredicate period) {
        updateFilteredTransactionList(period.and(new PurchaseTransactionPredicate()));
        indicateTransactionListChanged();
    }

    /**
     * Updates the {@code filteredTransactions} with Sale predicate and {@code period} predicate.
     */
    private void updateFilteredTransactionListToShowSales(TransactionPeriodPredicate period) {
        updateFilteredTransactionList(period.and(new SaleTransactionPredicate()));
        indicateTransactionListChanged();
    }

    /**
     * Updates the {@code filteredTransactions} with {@code period} predicate.
     * For use by Profit analysis.
     */
    private void updateFilteredTransactionListToShowProfitPeriod(TransactionPeriodPredicate period) {
        updateFilteredTransactionList(period);
        indicateTransactionListChanged();
    }

    private void updateFilteredTransactionListToShowAll() {
        updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        indicateTransactionListChanged();
    }


    // ================ EDIT DRINK DETAILS COMMANDS =========================
    @Override
    public void updateSellingPrice(Drink drinkToEdit, Price newSellingPrice) {
        inventoryList.updateSellingPrice(drinkToEdit, newSellingPrice);
        indicateDrinkAttributesChanged(drinkToEdit);
    }

    @Override
    public void updateCostPrice(Drink drinkToEdit, Price newCostPrice) {
        inventoryList.updateCostPrice(drinkToEdit, newCostPrice);
        indicateDrinkAttributesChanged(drinkToEdit);
    }

    @Override
    public void updateTags(Drink drinkToEdit, Set<Tag> newTags) {
        inventoryList.updateTags(drinkToEdit, newTags);
        indicateDrinkAttributesChanged(drinkToEdit);
    }

}
