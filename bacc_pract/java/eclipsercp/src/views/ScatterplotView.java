package views;

import model.Data;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.LineStyle;

//http://www.swtchart.org/doc/index.html
//http://www.eclipse.org/articles/viewArticle/ViewArticle2.html
public class ScatterplotView extends ViewPart{
	
	public static final String ID = "views.scatterplotView";
	private Chart chart;
	
	public ScatterplotView(){
		super();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		chart = new Chart(parent, SWT.NONE);
		chart.getTitle().setText("Scatterplot");
		
		ILineSeries scatterSeries = (ILineSeries)chart.getSeriesSet().createSeries(SeriesType.LINE, "scatter series");
		scatterSeries.setLineStyle(LineStyle.NONE);
		scatterSeries.setXSeries(Data.getDataset()[0]);
		scatterSeries.setYSeries(Data.getDataset()[1]);
		
		chart.getAxisSet().adjustRange();
	}

	@Override
	public void setFocus() {
		chart.setFocus();		
	}

}
