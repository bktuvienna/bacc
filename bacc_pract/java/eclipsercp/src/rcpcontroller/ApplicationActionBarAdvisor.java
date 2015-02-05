package rcpcontroller;

//adapted source from http://www.subshell.com/en/subshell/blog/article-Eclipse-RCP-Change-Your-Perspective100.html
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.
	private IWorkbenchAction openPerspectiveAction;
	private IWorkbenchAction savePerspectiveAsAction;
	private IWorkbenchAction resetPerspectiveAction;
	     
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
        openPerspectiveAction = ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window);
        register(openPerspectiveAction);
        savePerspectiveAsAction = ActionFactory.SAVE_PERSPECTIVE.create(window);
        register(savePerspectiveAsAction);
        resetPerspectiveAction = ActionFactory.RESET_PERSPECTIVE.create(window);
        register(resetPerspectiveAction);
	}
	     
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager windowMenu = new MenuManager("&Window");
        menuBar.add(windowMenu);
        windowMenu.add(openPerspectiveAction);
        windowMenu.add(savePerspectiveAsAction);
        windowMenu.add(resetPerspectiveAction);
	}
}
