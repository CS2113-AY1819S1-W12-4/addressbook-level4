package seedu.address.model.user.manager;

import java.util.Set;

import seedu.address.model.LoginInfoManager;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyInventoryList;
import seedu.address.model.UserPrefs;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Price;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.user.AuthenticationLevel;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;

/**
 * Contain api that is usable for manager role
 */
public class ManagerModelManager extends ModelManager implements ManagerModel {

    public ManagerModelManager(ReadOnlyInventoryList inventoryList,
                               UserPrefs userPrefs, LoginInfoManager loginInfoManager,
                               TransactionList transactionList) {
        super(inventoryList, userPrefs, loginInfoManager, transactionList);
    }

    //===============login command ============================//
    @Override
    public void createNewAccount(UserName userName, Password password, AuthenticationLevel authenticationLevel) {
        loginInfoManager.createNewAccount(userName, password, authenticationLevel);
    }

    @Override
    public void deleteAccount(UserName userName) {
        loginInfoManager.deleteAccount(userName);
    }

    //================drink command==============================///
    @Override
    public void deleteDrink(Drink target) {
        inventoryList.removeDrink(target);
        indicateInventoryListChanged();
    }

    @Override
    public void addDrink(Drink drink) {
        inventoryList.addDrink(drink);
        updateFilteredDrinkList(PREDICATE_SHOW_ALL_DRINKS);
        indicateInventoryListChanged();
    }

    // ================ EDIT DRINK DETAILS COMMANDS =========================

    @Override
    public void updateSellingPrice(Drink drinkToEdit, Price newSellingPrice) {
        // TODO: copy from admin
    }

    @Override
    public void updateCostPrice(Drink drinkToEdit, Price newCostPrice) {
        // TODO: copy from admin
    }

    @Override
    public void updateTags(Drink drinkToEdit, Set<Tag> newTags) {
        // TODO: copy from admin
    }
}
