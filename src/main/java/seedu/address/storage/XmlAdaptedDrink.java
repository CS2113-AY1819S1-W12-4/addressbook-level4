package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Name;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.Stock;
import seedu.address.model.tag.Tag;


/**
 * JAXB-friendly version of the Drink.
 */
public class XmlAdaptedDrink {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Drink's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String costPrice;
    @XmlElement(required = true)
    private String retailPrice;
    @XmlElement(required = true)
    private String stock;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedDrink() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given person details.
     */
    public XmlAdaptedPerson(String name, String costPrice, String retailPrice, String stock, List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.stock = stock;
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(Drink source) {
        name = source.getName().name;
        costPrice = source.getCostPrice().toString();
        retailPrice = source.getRetailPrice().toString();
        stock = source.getStock().toString();
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this JAXB-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Drink toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (costPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(costPrice)) {
            throw new IllegalValueException(Price.MESSAGE_PRICE_CONSTRAINTS);
        }
        final Price modelCostPrice = new Price(costPrice);

        if (retailPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(retailPrice)) {
            throw new IllegalValueException(Price.MESSAGE_PRICE_CONSTRAINTS);
        }
        final Price modelRetailPrice = new Price(retailPrice);

        if (stock == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Stock.class.getSimpleName()));
        }
        if (!Stock.isValidStock(stock)) {
            throw new IllegalValueException(Stock.MESSAGE_QUANTITY_CONSTRAINTS);
        }
        final Stock modelStock = new Stock(stock);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Drink(modelName, modelCostPrice, modelRetailPrice, modelStock, modelTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedPerson)) {
            return false;
        }

        XmlAdaptedDrink otherDrink = (XmlAdaptedDrink) other;
        return Objects.equals(name, otherDrink.name)
                && Objects.equals(costPrice, otherDrink.costPrice)
                && Objects.equals(retailPrice, otherDrink.retailPrice)
                && Objects.equals(stock, otherDrink.stock)
                && tagged.equals(otherDrink.tagged);
    }
}
