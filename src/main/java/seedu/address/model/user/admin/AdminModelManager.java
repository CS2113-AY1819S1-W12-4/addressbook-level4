package seedu.address.model.user.admin;

import seedu.address.analysis.AnalysisPeriodType;
import seedu.address.commons.events.model.DrinkAttributeChangedEvent;
import seedu.address.model.LoginInfoManager;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyInventoryList;
import seedu.address.model.UserPrefs;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.exceptions.InsufficientQuantityException;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.user.AuthenticationLevel;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;
import seedu.address.model.user.accountant.AccountantModel;
import seedu.address.model.user.accountant.AccountantModelManager;
import seedu.address.model.user.manager.ManagerModel;
import seedu.address.model.user.manager.ManagerModelManager;
import seedu.address.model.user.stocktaker.StockTakerModel;
import seedu.address.model.user.stocktaker.StockTakerModelManager;

/**
 * This is the API model for Admin command
 */
public class AdminModelManager extends ModelManager implements AdminModel {
    ManagerModel managerModel;
    StockTakerModel stockTakerModel;
    AccountantModel accountantModel;
    public AdminModelManager(ReadOnlyInventoryList inventoryList, UserPrefs userPrefs,
                             LoginInfoManager loginInfoManager, TransactionList transactionList) {

        super(inventoryList, userPrefs, loginInfoManager, transactionList);
        managerModel = new ManagerModelManager (inventoryList, userPrefs, loginInfoManager, transactionList);
        stockTakerModel = new StockTakerModelManager (inventoryList, userPrefs, loginInfoManager, transactionList);
        accountantModel = new AccountantModelManager (inventoryList, userPrefs, loginInfoManager, transactionList);
    }



    /**
     * Raises an event to indicate the model has changed
     */
    protected void indicateDrinkAttributesChanged(Drink drink) {
        raise(new DrinkAttributeChangedEvent(drink));
    }
    //===============manager command====================//
    @Override
    public void deleteDrink(Drink target) {
        managerModel.deleteDrink (target);
    }

    @Override
    public void addDrink(Drink drink) {
        managerModel.addDrink (drink);
    }
    //=====================Manager login command=========================
    @Override
    public void createNewAccount (UserName userName, Password password, AuthenticationLevel authenticationLevel) {
        managerModel.createNewAccount (userName, password, authenticationLevel);
    }

    @Override
    public void deleteAccount (UserName userName) {
        managerModel.deleteAccount (userName);
    }

    //=====================Stock taker commands====================
    @Override
    public void sellDrink(Transaction transaction) throws InsufficientQuantityException {
        stockTakerModel.sellDrink (transaction);
    }

    @Override
    public void buyDrink(Transaction transaction) {
        stockTakerModel.buyDrink (transaction);
    }




    //=====================Accountant command======================
    @Override
    public Price analyseCosts(AnalysisPeriodType period) {
        return analysis.analyseCost(period);
    }
    /*
        @Override
        public Price analyseRevenue() {
            return analysis.analyseRevenue();
        }
        @Override
        public Price analyseProfit() {
            return analysis.analyseProfit();
        }
    */
}
