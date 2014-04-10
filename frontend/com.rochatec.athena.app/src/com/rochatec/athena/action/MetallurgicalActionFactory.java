package com.rochatec.athena.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.actions.CommandAction;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.util.ICommands;

@SuppressWarnings("restriction")
public abstract class MetallurgicalActionFactory {

	/**
	 * Interface for a workbench action.
	 */
	public interface IWorkbenchAction extends IAction {
		/**
		 * Disposes of this action. Once disposed, this action cannot be used.
		 * This operation has no effect if the action has already been disposed.
		 */
		public void dispose();
	}

	private static class WorkbenchCommandAction extends CommandAction implements
			IWorkbenchAction {
		/**
		 * @param commandIdIn
		 * @param window
		 */
		public WorkbenchCommandAction(String commandIdIn,
				IWorkbenchWindow window) {
			super(window, commandIdIn);
		}
	}

	public static final MetallurgicalActionFactory NEW_JOB = new MetallurgicalActionFactory(
			"new_job",//$NON-NLS-1$ 
			ICommands.JOB_NEW) {

		@Override
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if (window == null) {
                throw new IllegalArgumentException();
            }
			WorkbenchCommandAction action = new WorkbenchCommandAction(getCommandId(), window);
			action.setText(Messages.getMessage("job.new"));
			action.setToolTipText(Messages.getMessage("job.new"));
            action.setId(getId());			
            return action;
			
		}

	};

	/**
	 * Establishes bi-direction connections between the forward and backward
	 * actions of a cycle pair.
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * ActionFactory.IWorkbenchAction nextEditorAction = ActionFactory.NEXT_EDITOR
	 * 		.create(window);
	 * ActionFactory.IWorkbenchAction previousEditorAction = ActionFactory.PREVIOUS_EDITOR
	 * 		.create(window);
	 * ActionFactory.linkCycleActionPair(nextEditorAction, previousEditorAction);
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param next
	 *            the action that moves forward
	 * @param previous
	 *            the action that moves backward
	 */
	public static void linkCycleActionPair(IWorkbenchAction next,
			IWorkbenchAction previous) {
	}

	/**
	 * Id of actions created by this action factory.
	 */
	private final String actionId;

	/**
	 * Optional ID for this action.
	 */
	private final String commandId;

	/**
	 * Creates a new workbench action factory with the given id.
	 * 
	 * @param actionId
	 *            the id of actions created by this action factory
	 */
	protected MetallurgicalActionFactory(String actionId) {
		this(actionId, null);
	}

	/**
	 * Create a new workbench action factory with the given IDs.
	 * 
	 * @param actionId
	 *            the id of actions created by this action factory
	 * @param commandId
	 *            the matching command id
	 * @since 3.5
	 */
	protected MetallurgicalActionFactory(String actionId, String commandId) {
		this.actionId = actionId;
		this.commandId = commandId;
	}

	/**
	 * Creates a new standard action for the given workbench window. The action
	 * has an id as specified by the particular factory.
	 * <p>
	 * Actions automatically register listeners against the workbench window so
	 * that they can keep their enablement state up to date. Ordinarily, the
	 * window's references to these listeners will be dropped automatically when
	 * the window closes. However, if the client needs to get rid of an action
	 * while the window is still open, the client must call
	 * {@link IWorkbenchAction#dispose dispose}to give the action an opportunity
	 * to deregister its listeners and to perform any other cleanup.
	 * </p>
	 * 
	 * @param window
	 *            the workbench window
	 * @return the workbench action
	 */
	public abstract IWorkbenchAction create(IWorkbenchWindow window);

	/**
	 * Returns the id of this action factory.
	 * 
	 * @return the id of actions created by this action factory
	 */
	public String getId() {
		return actionId;
	}

	/**
	 * Return the command id of this action factory.
	 * 
	 * @return the command id of the action created by this action factory. May
	 *         be <code>null</code>.
	 * @since 3.5
	 */
	public String getCommandId() {
		return commandId;
	}

}
