package seedu.address.logic;

import javax.swing.table.TableRowSorter;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.drink.Drink;
import seedu.address.model.transaction.Transaction;

/**
 * API of the logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the filtered list of drinks */
    ObservableList<Drink> getFilteredDrinkList ();

    /** Returns the list of input entered by the user, encapsulated in a {@code ListElementPointer} object */
    ListElementPointer getHistorySnapshot();

    void changeModelAfterReLogin(Model model);

    /** Returns an unmodifiable view of the transactions */
    ObservableList<Transaction> getFilteredTransactionList();
}
