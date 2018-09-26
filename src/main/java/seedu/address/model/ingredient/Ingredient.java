package seedu.address.model.ingredient;

import java.util.Objects;

/**
 * Represents an ingredient in the list of ingredients
 * Guarantees: TODO
 */
public class Ingredient {

    // Identity field
    private final IngredientName name;

    // Data fields
    private IngredientQuantity quantity;
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
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return otherIngredient.getName().equals(getName())
                && otherIngredient.getQuantity() == getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }
}
