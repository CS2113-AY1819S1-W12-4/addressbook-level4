package seedu.address.testutil.inventory;

import seedu.address.model.InventoryList;
import seedu.address.model.drink.Drink;

import java.util.List;

import static seedu.address.testutil.drinks.TypicalDrinks.FNN_GRAPE;
import static seedu.address.testutil.drinks.TypicalDrinks.GREEN_TEA;
import static seedu.address.testutil.drinks.TypicalDrinks.PEPSI;

public class TypicalInventoryList {


    public static InventoryList getTypicalInventoryList() {
        InventoryListBuilder inventoryListBuilder = new InventoryListBuilder();
        inventoryListBuilder.withDrink (FNN_GRAPE)
                            .withDrink (PEPSI)
                            .withDrink (GREEN_TEA);
        return inventoryListBuilder.build ();
    }
}
