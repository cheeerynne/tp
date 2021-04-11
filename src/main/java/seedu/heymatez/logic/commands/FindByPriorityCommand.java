package seedu.heymatez.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;
import static seedu.heymatez.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;

import seedu.heymatez.model.Model;
import seedu.heymatez.model.task.PriorityContainsKeywordPredicate;

/**
 * Represents a FindByPriorityCommand.
 * It displays tasks that are only of the specified priority value.
 */
public class FindByPriorityCommand extends Command {

    public static final String COMMAND_WORD = "findPriority";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks with the specified priority "
            + "and displays them as a list with index numbers\n "
            + "Parameters: PRIORITY \n"
            + "Example: " + COMMAND_WORD + " high";

    private final PriorityContainsKeywordPredicate predicate;

    public FindByPriorityCommand(PriorityContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);

        if (model.isTaskListEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_TASK_LIST);
        }

        return new CommandResult(
                String.format(MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByPriorityCommand // instanceof handles nulls
                && predicate.equals(((FindByPriorityCommand) other).predicate)); // state check
    }
}
