package views;

import java.awt.Component;

import javax.swing.JPanel;

import model.Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.ui.ApplicationFrame;

import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;

public class ValuechartView extends ApplicationFrame implements Dockable{
	
	DockKey key = new DockKey("valuechart");
	
	public ValuechartView(){
		super("Valuechart");
		setUpView();
	}
	
	private void setUpView(){
		final JFreeChart chart = ChartFactory.createXYBarChart("","X",false,"Y",Data.getDataset(),PlotOrientation.HORIZONTAL,true,true,false);
				
		XYPlot plot = chart.getXYPlot();
		XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
		renderer.setShadowVisible(false);
		renderer.setMargin(0.96);
		
		final ChartPanel panel = new ChartPanel(chart);
		panel.setMinimumDrawHeight(10);
	    panel.setMaximumDrawHeight(2000);
	    panel.setMinimumDrawWidth(10);
	    panel.setMaximumDrawWidth(2000);
	}
	
	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public DockKey getDockKey() {
		return this.key;
	}

}
