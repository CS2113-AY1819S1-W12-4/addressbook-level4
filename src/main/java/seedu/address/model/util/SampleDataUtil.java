package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InventoryList;
import seedu.address.model.ReadOnlyInventoryList;
import seedu.address.model.drink.Batch;
import seedu.address.model.drink.Date;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Name;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.Quantity;
import seedu.address.model.drink.UniqueBatchList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.ReadOnlyTransactionList;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.transaction.TransactionType;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Initialising sample drinks for use
     */
    // Coke
    public static final Batch COKE1 = new SampleBatchBuilder().withId("0001").withQuantity("10")
            .withDate("01/10/2018").build();
    public static final Batch COKE2 = new SampleBatchBuilder().withId("0002").withQuantity("20")
            .withDate("19/10/2018").build();
    public static final Batch COKE3 = new SampleBatchBuilder().withId("0003").withQuantity("30")
            .withDate("11/11/2018").build();
    //Green Tea
    public static final Batch GT1 = new SampleBatchBuilder().withId("0004").withQuantity("10")
            .withDate("01/11/2018").build();
    public static final Batch GT2 = new SampleBatchBuilder().withId("0005").withQuantity("20")
            .withDate("02/11/2018").build();
    public static final Batch GT3 = new SampleBatchBuilder().withId("0006").withQuantity("40")
            .withDate("20/11/2018").build();
    //Sprite
    public static final Batch SPRITE1 = new SampleBatchBuilder().withId("0007").withQuantity("30")
            .withDate("01/11/2018").build();
    public static final Batch SPRITE2 = new SampleBatchBuilder().withId("0008").withQuantity("20")
            .withDate("02/11/2018").build();
    public static final Batch SPRITE3 = new SampleBatchBuilder().withId("0009").withQuantity("40")
            .withDate("20/11/2018").build();
    //Milk Coffee
    public static final Batch MC1 = new SampleBatchBuilder().withId("0010").withQuantity("10")
            .withDate("01/11/2018").build();
    public static final Batch MC2 = new SampleBatchBuilder().withId("0011").withQuantity("30")
            .withDate("02/11/2018").build();
    public static final Batch MC3 = new SampleBatchBuilder().withId("0012").withQuantity("40")
            .withDate("20/11/2018").build();
    //Milk Tea
    public static final Batch MT1 = new SampleBatchBuilder().withId("0013").withQuantity("60")
            .withDate("01/11/2018").build();
    public static final Batch MT2 = new SampleBatchBuilder().withId("0014").withQuantity("20")
            .withDate("02/11/2018").build();
    public static final Batch MT3 = new SampleBatchBuilder().withId("0015").withQuantity("40")
            .withDate("20/11/2018").build();

    private static final UniqueBatchList cokeBatches = new SampleBatchListBuilder().buildBatchList(COKE1, COKE2, COKE3);
    private static final UniqueBatchList greenTeaBatches = new SampleBatchListBuilder().buildBatchList(GT1, GT2, GT3);
    private static final UniqueBatchList spriteBatches =
            new SampleBatchListBuilder().buildBatchList(SPRITE1, SPRITE2, SPRITE3);
    private static final UniqueBatchList milkCoffeeBatches = new SampleBatchListBuilder().buildBatchList(MC1, MC2, MC3);
    private static final UniqueBatchList milkTeaBatches = new SampleBatchListBuilder().buildBatchList(MT1, MT2, MT3);

    public static Drink[] getSampleDrinks() {
        return new Drink[] {
                new Drink(new Name("Coke"), new Price("19.2"), new Price("36"), cokeBatches,
                        getTagSet("Popular")),
                new Drink(new Name("Green Tea"), new Price("16.8"), new Price("36"), greenTeaBatches ,
                        getTagSet("Popular")),
                new Drink(new Name("Sprite"), new Price("16.8"), new Price("36"), spriteBatches ,
                        getTagSet("Popular")),
                new Drink(new Name("Milk Coffee"), new Price("24"), new Price("43.2"), milkCoffeeBatches,
                        getTagSet()),
                new Drink(new Name("Milk Tea"), new Price("24"), new Price("43.2"), milkTeaBatches, getTagSet())
        };
    }

    public static ReadOnlyInventoryList getSampleInventoryList() {
        InventoryList sampleIl = new InventoryList();
        for (Drink sampleDrink : getSampleDrinks()) {
            sampleIl.addDrink(sampleDrink);
        }
        return sampleIl;
    }

    /**
     * Initialising sample transactions for use
     */
    // Coke
    //Green Tea
    //Sprite
    //Milk Coffee
    //Milk Tea

    // TransactionType transactionType, Date transactionDate, Drink drinkTransacted,
    // Quantity quantityTransacted, Price amountMoney, long id
    public static final Transaction BUY_COKE_1 = new Transaction(TransactionType.PURCHASE, new Date("01/10/2018"),
            new Drink(new Name("COKE")), new Quantity("10"), new Price("")

    /*
    public static final Batch COKE1 = new SampleBatchBuilder().withId("0001").withQuantity("10")
            .withDate("01/10/2018").build();
    public static final Batch COKE2 = new SampleBatchBuilder().withId("0002").withQuantity("20")
            .withDate("19/10/2018").build();
    public static final Batch COKE3 = new SampleBatchBuilder().withId("0003").withQuantity("30")
            .withDate("11/11/2018").build();

    */











    public static Transaction[] getSampleTransactions() {

    }


    public static ReadOnlyTransactionList getSampleTransactionList() {
        TransactionList sampleTl = new TransactionList();


        return null;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
