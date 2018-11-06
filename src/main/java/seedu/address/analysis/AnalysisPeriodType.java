package seedu.address.analysis;

import java.util.function.Predicate;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Transaction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.HYPHEN_DAY;
import static seedu.address.logic.parser.CliSyntax.HYPHEN_MONTH;
import static seedu.address.logic.parser.CliSyntax.HYPHEN_WEEK;

/**
 * Represents the possible analysis periods to be analysed.
 */
public enum AnalysisPeriodType {
    DAY() {
        @Override
        public Predicate<Transaction> periodFilterPredicate() {
            return new TransactionInDayPredicate();
        }
    },

    WEEK() {
        @Override
        public Predicate<Transaction> periodFilterPredicate() {
            return new TransactionInDayPredicate();
        }
    },

    MONTH {
        @Override
        public Predicate<Transaction> periodFilterPredicate() {
            return new TransactionInDayPredicate();
        }
    };
    public static final String WRONG_PERIOD_MESSAGE = "You have enter an invalid period";
    public abstract Predicate<Transaction> periodFilterPredicate();

    public static AnalysisPeriodType getPeriod(String test) throws ParseException{
        test = test.replaceAll("\\s+","");
        if (test.equals (HYPHEN_DAY)){
            return DAY;
        }
        if (test.equals  (HYPHEN_WEEK)){
            return WEEK;
        }
        if (test.equals  (HYPHEN_MONTH)){
            return MONTH;
        }
        throw new ParseException (String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_PERIOD_MESSAGE));
    }
}
