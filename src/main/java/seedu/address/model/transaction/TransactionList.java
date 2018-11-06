package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.drink.Date;

/**
 * Represents a list of transactions for (currently) eternity, until cleared.
 */
public class TransactionList {

    private List<Transaction> transactions;
    private Date lastUpdateDate;

    public TransactionList() {
        transactions = new ArrayList<>();
    }

    /**
     * Creates a TransactionList using the transactions in the {@code toBeCopied}
     */
    public TransactionList(TransactionList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code TransactionList} with {@code newData}.
     */
    public void resetData(TransactionList newData) {
        requireNonNull(newData);

        setTransactions(newData.getTransactions());
    }

    /**
     * Replaces the contents of the inventory list with {@code drinks}.
     * {@code drinks} must not contain duplicate drinks.
     */
    public void setTransactions(List<Transaction> transactions) {
        requireAllNonNull(transactions);

        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public ObservableList<Transaction> getTransactionsAsObservableList() {
        return FXCollections.observableList(transactions);
    }

    /**
     * Adds {@code transaction} to the list of transactions
     */
    public void addTransaction(Transaction transaction) {
        requireNonNull(transaction);
        transactions.add(transaction);
        updateLastUpdateDate();
    }

    /**
     * Updates the {@code lastUpdateDate} depending on last sale or import.
     */
    private void updateLastUpdateDate() {
        lastUpdateDate = new Date();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        transactions.forEach(builder::append);
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TransactionList // instanceof handles nulls
                && transactions.equals(((TransactionList) other).transactions));
    }


    //    /**
    //     * Calculates the total revenue of all the transactions.
    //     * @return total revenue earned for all transactions
    //     */
    //    public Price calculateTotalRevenue() {
    //        float totalRevenue = 0;
    //        for (Transaction transaction : transactions) {
    //            if (transaction.getTransactionType() == TransactionType.SALE) {
    //                totalRevenue += transaction.getAmountMoney().getValue();
    //            }
    //        }
    //
    //        return new Price(Float.toString(totalRevenue));
    //    }
    //
    //
    //    /**
    //     * Calculates the total profit of all the transactions, using formula: total revenue - total cost
    //     * @return total profit earned for all transactions
    //     */
    //    public Price calculateTotalProfit() {
    //        float totalRevenue = 0;
    //        float totalCost = 0;
    //        for (Transaction transaction : transactions) {
    //            if (transaction.getTransactionType() == TransactionType.SALE) {
    //                totalRevenue += transaction.getAmountMoney().getValue();
    //            } else {
    //                totalCost += transaction.getAmountMoney().getValue();
    //            }
    //        }
    //
    //        float totalProfit = totalRevenue - totalCost;
    //        return new Price(Float.toString(totalProfit));
    //    }
}
