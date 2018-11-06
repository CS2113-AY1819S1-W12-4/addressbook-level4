package seedu.address.analysis;

import seedu.address.commons.core.ComponentManager;
import seedu.address.model.drink.Price;
import seedu.address.model.transaction.TransactionList;

/**
 * Represents functions to analyse profit, revenue, cost, quantity sold.
 */
public class AnalysisManager extends ComponentManager implements Analysis {
    private TransactionList transactionList;

    public AnalysisManager(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public Price analyseProfit(Analysis'') {
        return transactionList.calculateTotalProfit();
    }

    @Override
    public Price analyseCost() {
        return transactionList.calculateTotalCost();
    }

    @Override
    public Price analyseRevenue() {
        return transactionList.calculateTotalRevenue();
    }


}
