package seedu.address.testutil;
//@@author liu-tianhang
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.drink.Drink;
import seedu.address.model.drink.UniqueDrinkList;


/**
 * A utility class containing a list of {@code Drink} objects to be used in tests.
 */
public class TypicalDrinks {
    public static final Drink COKE = new DrinkBuilder ().withDrinkName ("Coca cola")
            .withCostPrice ("3.5").withRetailPrice ("5.76").withQuantity ("90")
            .withTags ("soft Drink").build ();
    public static final Drink PEPSI = new DrinkBuilder ().withDrinkName ("Pepsi")
            .withCostPrice ("3.6").withRetailPrice ("56.76").withQuantity ("76")
            .withTags ("soft Drink").build ();
    public static final Drink SPRITE = new DrinkBuilder ().withDrinkName ("Sprite")
            .withCostPrice ("3.7").withRetailPrice ("78.76").withQuantity ("34")
            .withTags ("soft Drink").build ();
    public static final Drink FANTA = new DrinkBuilder ().withDrinkName ("Fanta")
            .withCostPrice ("3.8").withRetailPrice ("56").withQuantity ("67")
            .withTags ("soft Drink").build ();

    private TypicalDrinks() {} // prevents instantiation
    /**
     * Returns an {@code UniqueDrinkList} with all the typical drinks.
     */
    public static UniqueDrinkList getTypicalDrinkList() {
        UniqueDrinkList drinkList = new UniqueDrinkList();
        for (Drink drink : getTypicalDrinks()) {
            drinkList.add (drink);
        }
        return drinkList;
    }

    public static List<Drink> getTypicalDrinks() {
        return new ArrayList<> (Arrays.asList(COKE, PEPSI, SPRITE, FANTA));
    }
}
