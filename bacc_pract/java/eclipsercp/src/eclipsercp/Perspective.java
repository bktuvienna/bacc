package eclipsercp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import eclipsercp.ScatterplotView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, (float) 0.25, editorArea);
		left.addView(ValuechartView.ID);
		
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.25, editorArea);
		right.addView(ValuetableView.ID);
		
		
		//layout.setEditorAreaVisible(false);
		//layout.setFixed(true);
		
		//layout.addStandaloneView(ScatterplotView.ID,  false, IPageLayout.LEFT, 0.25f, editorArea);
		//IFolderLayout folder = layout.createFolder("views", arg1, arg2, arg3)
	}

}
