package seedu.address.logic.drinkparser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.drinkparser.exceptions.DrinkParseException;
import seedu.address.model.drink.Name;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.Quantity;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.AuthenticationLevel;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;

/**
 * Contains utility methods used for parsing strings in the various *DrinkParser classes.
 */
public class DrinkParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws DrinkParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws DrinkParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new DrinkParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    // =============== User-related parsing ========================
    /**
     * Parses a {@code String userName} into a {@code UserName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code UserName} is invalid.
     */
    public static UserName parseUserName(String userName) throws DrinkParseException {
        requireNonNull(userName);
        String trimmedUserName = userName.trim();
        if (!UserName.isValidUserName(trimmedUserName)) {
            throw new DrinkParseException(UserName.MESSAGE_USER_NAME_CONSTRAINTS);
        }
        return new UserName (trimmedUserName);
    }

    /**
     * Parses a {@code String password} into a {@code Password}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code Password} is invalid.
     */
    public static Password parsePassword(String password) throws DrinkParseException {
        requireNonNull(password);
        String trimmedPassword = password.trim();
        if (!Password.isValidPassword (trimmedPassword)) {
            throw new DrinkParseException(Password.MESSAGE_PASSWORD_CONSTRAINTS);
        }
        return new Password (trimmedPassword);
    }

    /**
     * Parses a {@code String authenticationLevel } into a {@code AuthenticationLevel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code AuthenticationLevel} is invalid.
     */
    public static AuthenticationLevel parseAuthenticationLevel(String authenticationLevel) throws DrinkParseException {
        requireNonNull(authenticationLevel);
        String trimmedAuthenticationLevel = authenticationLevel.trim().toUpperCase ();
        if (!AuthenticationLevel.isAuthenticationLevelValid (trimmedAuthenticationLevel)) {
            throw new DrinkParseException(AuthenticationLevel.MESSAGE_AUTHENTICATIONLEVEL_CONSTRAINTS);
        }
        return new AuthenticationLevel (trimmedAuthenticationLevel);
    }

    // ================== Drink-related parsing ===================
    /**
     * Parses a {@code String itemName} into a {@code String itenName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code name} is invalid.
     */
    public static Name parseDrinkName(String name) throws DrinkParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new DrinkParseException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code price} is invalid.
     */
    public static Price parseDrinkCostPrice (String price) throws DrinkParseException {
        requireNonNull(price);
        String trimmedCostPrice = price.trim();
        if (!Price.isValidPrice(trimmedCostPrice)) {
            throw new DrinkParseException(Price.MESSAGE_PRICE_CONSTRAINTS);
        }
        return new Price(trimmedCostPrice);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code price} is invalid.
     */
    public static Price parseDrinkDefaultSellingPrice (String defaultSellingPrice) throws DrinkParseException {
        requireNonNull(defaultSellingPrice);
        String trimmedDefaultSellingPrice = defaultSellingPrice.trim();
        if (!Price.isValidPrice(trimmedDefaultSellingPrice)) {
            throw new DrinkParseException(Price.MESSAGE_PRICE_CONSTRAINTS);
        }
        return new Price(trimmedDefaultSellingPrice);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws DrinkParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws DrinkParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new DrinkParseException(Tag.MESSAGE_TAG_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws DrinkParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String quantity} into a {@code Quantity}.
     */
    public static Quantity parseQuantity(String quantity) throws DrinkParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new DrinkParseException(Quantity.MESSAGE_QUANTITY_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }



    // ===================== transaction-related parsing ====================
}