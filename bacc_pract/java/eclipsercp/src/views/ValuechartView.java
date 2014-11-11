package views;

import model.Data;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;
import org.swtchart.IBarSeries;
import org.swtchart.ILineSeries;
import org.swtchart.LineStyle;
import org.swtchart.ISeries.SeriesType;

public class ValuechartView extends ViewPart{
	
	public static final String ID = "views.valuechartView";
	private Chart chart;
	
	public ValuechartView(){
		super();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		chart = new Chart(parent, SWT.NONE);
		chart.getTitle().setText("Valuechart");
		String[] valueSeries = new String[Data.getDataset()[0].length];
		
		for(int i=0;i<Data.getDataset()[0].length;i++)
			valueSeries[i]="Value "+i;
			
		chart.getAxisSet().getXAxis(0).enableCategory(true);
		chart.getAxisSet().getXAxis(0).setCategorySeries(valueSeries);
		
		IBarSeries barSeries1 = (IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, "bar series 1");
		barSeries1.setYSeries(Data.getDataset()[0]);
		barSeries1.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
		
		IBarSeries barSeries2 = (IBarSeries)chart.getSeriesSet().createSeries(SeriesType.BAR, "bar series 2");
		barSeries2.setYSeries(Data.getDataset()[1]);
		barSeries2.setBarColor(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
		
		chart.setOrientation(SWT.VERTICAL);
		chart.getAxisSet().adjustRange();
		
	}

	@Override
	public void setFocus() {
		chart.setFocus();
	}

}
