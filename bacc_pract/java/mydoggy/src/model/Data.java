package model;

import java.util.Random;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Data {

	private static Data instance;
	private static XYSeriesCollection dataset;
	
	private Data(){
		dataset = new XYSeriesCollection();
	}
	
	public static XYSeriesCollection getDataset(){
		if(Data.instance==null){
			Data.instance=new Data();
			XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		    XYSeries series = new XYSeries("Scatterplot Values");
		    Random rand = new Random();
			for(int i=0;i<20;i++){
				for(int k=0;k<20;k++){			
					double valuex = rand.nextGaussian();
					double valuey = rand.nextGaussian();
					series.add(valuex,valuey);
				}
			}
			xySeriesCollection.addSeries(series);
			dataset = xySeriesCollection;
		}
		return dataset;
	}
}
