package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.ingredient.IceCream;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.Topping;


/**
 * Represents the list of ingredients in the store
 */
public class IngredientManager implements IngredientModel {
    private List<Topping> toppings;
    private List<IceCream> iceCreams;

    public IngredientManager() {
        // TODO: for now, always create new lists (i.e. doesn't save data yet)
        toppings = new ArrayList<>();
        iceCreams = new ArrayList<>();
    }


    @Override
    public void resetData() {
        toppings.clear();
        iceCreams.clear();
    }

    @Override
    public ReadOnlyIngredientList getIngredientList() {
        // TODO: return an immutable list in the future
    }

    @Override
    public void addIngredient(Ingredient ingredient) {

    }

    @Override
    public void updateIngredientQuantity(Ingredient ingredient, IngredientQuantity quantity) {
        ingredient.setQuantity(quantity);
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {

    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        return false;
    }

    private Ingredient findIngredientByName(Ingredient ingredient) {
        ingredients.
    }
}
