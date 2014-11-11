package model;

import java.util.Random;

public class Data {

	private static Data instance;
	private static double[][] dataset;
	
	private Data(){
		dataset = new double[2][400];
	}
	
	public static double[][] getDataset(){
		if(Data.instance==null){
			Data.instance=new Data();
			Random rand = new Random();
			for(int i=0;i<2;i++){
				for(int k=0;k<400;k++){			
					double value = rand.nextGaussian();
					dataset[i][k]=value;
				}
			}
		}
		return dataset;
	}
}
