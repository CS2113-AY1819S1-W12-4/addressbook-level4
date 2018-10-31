package seedu.address.commons.events.Logic;

import seedu.address.commons.events.BaseEvent;
import seedu.address.logic.Logic;

/**
 * Change in Logic for logic related component
 */
public class LogicChangedEvent extends BaseEvent {

    public final Logic logic;

    public LogicChangedEvent(Logic logic){
        this.logic = logic;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
