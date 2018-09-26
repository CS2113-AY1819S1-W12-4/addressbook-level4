package seedu.address.model.ingredient;

import java.util.Objects;

/**
 * Represents an ingredient in the list of ingredients
 * Guarantees: TODO
 */
public abstract class Ingredient {

    // Identity field
    protected final IngredientName name;

    // Data fields
    protected IngredientQuantity quantity;
    // TODO: ingredient cost

    public Ingredient(IngredientName name, IngredientQuantity quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public IngredientName getName() {
        return name;
    }

    public IngredientQuantity getQuantity() {
        return quantity;
    }

    public void setQuantity(IngredientQuantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();
}
