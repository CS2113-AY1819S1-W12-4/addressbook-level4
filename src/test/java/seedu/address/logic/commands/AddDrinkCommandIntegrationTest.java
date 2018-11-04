package seedu.address.logic.commands;

import org.junit.Before;
import seedu.address.logic.CommandHistory;
import seedu.address.model.InventoryList;
import seedu.address.model.LoginInfoManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.user.stocktaker.StockTakerModel;
import seedu.address.model.user.stocktaker.StockTakerModelManager;

import static seedu.address.testutil.TypicalDrinks.getTypicalDrinkList;

public class AddDrinkCommandIntegrationTest {

    private StockTakerModel stockTakerModel;
    private CommandHistory commandHistory = new CommandHistory();

//    @Before
//    public void setUp() {
//        InventoryList inventoryList = new InventoryList ();
//        //inventoryList.setDrinks (getTypicalDrinkList ());
//        stockTakerModel = new StockTakerModelManager (new InventoryList (getTypicalDrinkList()), new UserPrefs (),
//                                                new LoginInfoManager (), new TransactionList());
//
//    }
}
