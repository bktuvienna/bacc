package panels;

import java.awt.Component;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

//http://www.java2s.com/Code/Java/Chart/JFreeChartXYSeriesDemo3.htm
public class ValuesPanel extends ApplicationFrame{
	
	private Component vpanel;
	
	//dataset comes from ScatterplotPanel
	public ValuesPanel(String title,XYSeriesCollection dataset) {
		super(title);
		final JFreeChart chart = ChartFactory.createXYBarChart("","X",false,"Y",dataset,PlotOrientation.HORIZONTAL,true,true,false);
		final ChartPanel panel = new ChartPanel(chart);
		vpanel = panel;
	}
	
	public Component returnBarChartPanel(){
		return vpanel;
	}
}
