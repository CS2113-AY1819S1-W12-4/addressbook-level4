package seedu.address.model;

import java.util.List;

import seedu.address.model.ingredient.Ingredient;

/**
 * Unmodifiable view of the Ingredient Manager
 */
public interface ReadOnlyIngredientList {

    /**
     * TODO: supposed to be "Returns an unmodifiable view of the ingredients list"
     * (for now) Returns the ingredients list
     */
    List<Ingredient> getIngredientList();

}
