package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.drink.Date;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.Quantity;

/**
 * Represents a transaction in the company.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {
    private TransactionType transactionType;
    private Date transactionDate;
    private Drink drinkTransacted;
    private Quantity quantityTransacted;
    private Price amountMoney;
    private long id;

    public Transaction() {}

    public Transaction(TransactionType transactionType, Drink drinkTransacted,
                       Quantity quantityTransacted, Price amountMoney) {
        requireAllNonNull(transactionType, drinkTransacted, quantityTransacted, amountMoney);
        this.transactionType = transactionType;
        this.drinkTransacted = drinkTransacted;
        this.quantityTransacted = quantityTransacted;
        this.amountMoney = amountMoney;
        transactionDate = new Date();
        id = new java.util.Date().getTime();
    }

    public Transaction(TransactionType transactionType, Drink drinkTransacted,
                       Quantity quantityTransacted) {
        requireAllNonNull(transactionType, drinkTransacted, quantityTransacted);
        this.transactionType = transactionType;
        this.drinkTransacted = drinkTransacted;
        this.quantityTransacted = quantityTransacted;
        amountMoney = new Price("0");
        transactionDate = new Date();
        id = new java.util.Date().getTime();
    }

    public Transaction(TransactionType transactionType, Drink drinkTransacted,
                       Quantity quantityTransacted, Price amountMoney, Date transactionDate, long id) {
        requireAllNonNull(transactionType, drinkTransacted, quantityTransacted, transactionDate, id);
        this.transactionType = transactionType;
        this.drinkTransacted = drinkTransacted;
        this.quantityTransacted = quantityTransacted;
        this.amountMoney = amountMoney;
        this.transactionDate = transactionDate;
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Drink getDrinkTransacted() {
        return drinkTransacted;
    }

    public Price getAmountMoney() {
        return amountMoney;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Quantity getQuantityTransacted() {
        return quantityTransacted;
    }

    private long getId() {
        return id;
    }

    public void setAmountMoney(Price amountMoney) {
        this.amountMoney = amountMoney;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Transaction that = (Transaction) other;
        return getTransactionType() == that.getTransactionType()
                && getTransactionDate().equals(that.getTransactionDate())
                && getDrinkTransacted().isSameDrink(that.getDrinkTransacted())
                && getQuantityTransacted().equals(that.getQuantityTransacted())
                && getAmountMoney().equals(that.getAmountMoney())
                && getId() == (that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionType(), getTransactionDate(), getDrinkTransacted(),
                getQuantityTransacted(), getAmountMoney(), getId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(getTransactionDate())
                .append(", Type: ")
                .append(getTransactionType())
                .append(", Drink: ")
                .append(getDrinkTransacted())
                .append(", Quantity: ")
                .append(getQuantityTransacted())
                .append(", Amount transacted: $")
                .append(getAmountMoney())
                .append(", ID: ")
                .append(getId());

        return builder.toString();
    }
}
