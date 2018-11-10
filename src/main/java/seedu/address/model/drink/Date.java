//@@author Lunastryke
package seedu.address.model.drink;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the import date of a batch of drink
 * Guarantees: TODO
 */
public class Date {
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date should be in this format DD/MM/YYYY or D/MM/YYYY or DD/M/YYYY";
    public static final String MESSAGE_NON_EXISTING_DATE =
            "Please enter a date that exists";
    public static final String DATE_VALIDATION_REGEX =
            "^(3[01]|2[0-9]|1[0-9]|0[0-9]|[0-9])[/](1[0-2]|0[1-9]|[1-9])[/]([12]\\d{3})$";
    public static final String DATE_FORMAT = "d/MM/uuuu";
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

    private LocalDate date;

    /**
     * Constructs an {@code Date} with the current real time date.
     */
    public Date() {
        this.date = LocalDate.now();
    }

    /**
     * Constructs an {@code Date}.
     * @param date A valid quantity value expressed as a string.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_DATE_CONSTRAINTS);
        try {
            this.date = LocalDate.parse(date, FORMAT);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(MESSAGE_NON_EXISTING_DATE);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns true if a given string is in a valid date format.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a real existing date
     */
    public static boolean isExistingDate(String test) {
        LocalDate tester;
        try {
            tester = LocalDate.parse(test, FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param other a valid BatchDate object
     * @return Returns positive integer if other holds a date before that of the current object
     *         Returns negative integer if other holds a date after that of the current object
     *         Returns 0 if other holds a date same as the current object
     */
    public int compareTo(Date other) {
        return this.date.compareTo(other.date);
    }

    /**
     * @param other a valid BatchDate object
     * @return Returns true if other holds a date before that of the current object
     */
    public boolean isBefore(Date other) {
        return this.date.isBefore(other.date);
    }

    /**
     * @param other a valid BatchDate object
     * @return Returns true if other holds a date after that of the current object
     */
    public boolean isAfter(Date other) {
        return this.date.isAfter(other.date);
    }


    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }


    public Date getDateSixDaysBefore() {
        setDate(date.minusDays(6));

        return this;
    }

    public Date getDateTwentyNineDaysBefore() {
        setDate(date.minusDays(29));

        return this;
    }

    /**
     * Returns true is this date is between the {@code start} and {@code end} Dates, inclusive
     */
    public boolean between(Date start, Date end) {
        return (this.compareTo(start) >= 0) && (this.compareTo(end) <= 0);
    }

}
