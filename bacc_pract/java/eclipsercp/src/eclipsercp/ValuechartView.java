package eclipsercp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;

public class ValuechartView extends ViewPart{
	
	public static final String ID = "eclipsercp.valuechartView";
	//private Chart chart;
	
	public ValuechartView(){
		super();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		Chart chart = new Chart(parent, SWT.NONE);
		
	}

	@Override
	public void setFocus() {
		//chart.setFocus();
	}

}
