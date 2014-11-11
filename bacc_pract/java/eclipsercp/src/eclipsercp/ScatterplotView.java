package eclipsercp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;

//http://www.swtchart.org/doc/index.html
//http://www.eclipse.org/articles/viewArticle/ViewArticle2.html
public class ScatterplotView extends ViewPart{
	
	public static final String ID = "eclipsercp.scatterplotView";
	private Chart chart;
	
	public ScatterplotView(){
		super();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		chart = new Chart(parent, SWT.NONE);
	}

	@Override
	public void setFocus() {
		chart.setFocus();		
	}

}
