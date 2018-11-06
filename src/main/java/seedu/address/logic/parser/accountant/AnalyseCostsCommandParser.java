//@@author liu-tianhang
package seedu.address.logic.parser.accountant;

import java.util.Set;
import java.util.stream.Stream;
import seedu.address.logic.commands.AddDrinkCommand;
import seedu.address.logic.commands.accountant.AnalyseCostsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Name;
import seedu.address.model.drink.Price;
import seedu.address.model.tag.Tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRINK_COST_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRINK_DEFAULT_SELLING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRINK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRINK_TAG;

/**
 * Parses input arguments and creates a new AnalyseCostsCommand object
 */
public class AnalyseCostsCommandParser implements Parser<AnalyseCostsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddDrinkCommand
     * and returns an AddDrinkCommand object for execution.
     *
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform the expected format
     */
    @Override
    public AnalyseCostsCommand parse(String args) throws ParseException {

        if (args.length ()>2){
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnalyseCostsCommand.MESSAGE_USAGE));
        }
        if(args.isEmpty ()){
            
        }

        return new AddDrinkCommand(drink);
    }


}
