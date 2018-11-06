package seedu.address.analysis;

import java.util.List;

import seedu.address.commons.core.ComponentManager;
import seedu.address.model.drink.Price;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;
import seedu.address.model.transaction.TransactionType;

/**
 * Represents functions to analyse profit, revenue, cost, quantity sold.
 */
public class AnalysisManager extends ComponentManager implements Analysis {
    private TransactionList transactionList;

    public AnalysisManager(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public Price analyseProfit() {
        List<Transaction> transactions = transactionList.getTransactions();
        return null;

    }

    @Override
    public Price analyseCost() {
        List<Transaction> transactions = transactionList.getTransactions();
        float totalCost = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.PURCHASE) {
                totalCost += transaction.getAmountMoney().getValue();
            }
        }

        return new Price(Float.toString(totalCost));
    }

    @Override
    public Price analyseRevenue() {
        List<Transaction> transactions = transactionList.getTransactions();
        float totalRevenue = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.SALE) {
                totalRevenue += transaction.getAmountMoney().getValue();
            }
        }

        return new Price(Float.toString(totalRevenue));
    }





}
