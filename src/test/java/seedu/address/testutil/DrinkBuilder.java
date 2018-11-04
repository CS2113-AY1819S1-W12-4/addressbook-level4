package seedu.address.testutil;

import seedu.address.model.drink.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;
//@@author liu-tianhang
public class DrinkBuilder {
    public static final String DEFAULT_NAME = "Coca cola";
    public static final String DEFAULT_COST_PRICE = "3.90";
    public static final String DEFAULT_RETAIL_PRICE = "5.90";
    public static final String DEFAULT_QUANTITY = "359";


    private Name name;
    private Price costPrice;
    private Price retailPrice;
    private Quantity quantity;
    private Set<Tag> tags;

    public DrinkBuilder(){
        name = new Name (DEFAULT_NAME);
        costPrice = new Price (DEFAULT_COST_PRICE);
        retailPrice = new Price (DEFAULT_RETAIL_PRICE);
        quantity = new Quantity (DEFAULT_QUANTITY);
        tags = new HashSet<> ();

    }
    /**
     * Sets the {@code Name} of the {@code Drink} that we are building.
     */
    public DrinkBuilder withDrinkName(String drinkName) {
        this.name = new Name(drinkName);
        return this;
    }
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Drink} that we are building.
     */
    public DrinkBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }
    /**
     * Sets the {@code costPrice} of the {@code Drink} that we are building.
     */
    public DrinkBuilder withCostPrice(String costPrice) {
        this.costPrice = new Price (costPrice);
        return this;
    }
    /**
     * Sets the {@code retailPrice} of the {@code Drink} that we are building.
     */
    public DrinkBuilder withRetailPrice(String retailPrice) {
        this.retailPrice = new Price (retailPrice);
        return this;
    }
    /**
     * Sets the {@code Quantity} of the {@code Drink} that we are building.
     */
    public DrinkBuilder withQuantity(String quantity) {
        this.quantity = new Quantity (quantity);
        return this;
    }
    public Drink build(){ return new Drink (name, costPrice, retailPrice, quantity,tags);}
}

