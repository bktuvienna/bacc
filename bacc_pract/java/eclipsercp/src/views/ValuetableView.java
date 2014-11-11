package views;

import model.Data;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class ValuetableView extends ViewPart{
	
	public static final String ID = "views.valuetableView";
	
	public ValuetableView(){
		super();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		Table table = new Table(parent,SWT.CHECK|SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		table.setHeaderVisible(true);
		String[] titles = {"X","Y"};
		
		for(int i=0;i<titles.length;i++){
			TableColumn c = new TableColumn(table, SWT.NULL);
			c.setText(titles[i]);
		}
		
		for(int i=0;i<Data.getDataset()[0].length;i++){
			TableItem item = new TableItem(table,SWT.NULL);
			item.setText(0, String.format("%.5g%n",Data.getDataset()[0][i]));
			item.setText(1, String.format("%.5g%n",Data.getDataset()[1][i]));	
		}
		
		for(int i=0;i<titles.length;i++){
			table.getColumn(i).pack();
		}
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
