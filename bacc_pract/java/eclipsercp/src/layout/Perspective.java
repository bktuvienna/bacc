package layout;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import views.ConsoleView;
import views.ScatterplotView;
import views.ValuechartView;
import views.ValuetableView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, (float) 0.25, editorArea);
		left.addView(ValuechartView.ID);
		
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, (float) 0.7, editorArea);
		right.addView(ValuetableView.ID);
		
		IFolderLayout center = layout.createFolder("center", IPageLayout.RIGHT, (float) 0.3, editorArea);
		center.addView(ScatterplotView.ID);
		
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.8,"center");
		bottom.addView(ConsoleView.ID);
	}

}
