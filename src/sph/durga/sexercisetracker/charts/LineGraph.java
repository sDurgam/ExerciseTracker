package sph.durga.sexercisetracker.charts;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.graphics.Color;

public class LineGraph 
{
	public GraphicalView execute(Context context, String label, int mondays[],  int[] xdays, int[] values)
	{ 
		XYSeries pointSeries = new XYSeries("Points");
		int index;
		// Adding data to days and points Series
		for(int i=0; i<mondays.length; i++)
		{ 
			index = GetDayIndex(xdays, mondays[i]);
			if(index != -1)
			{
				pointSeries.add(i,values[index]);
			}
			else
			{
				pointSeries.add(i,0);
			}
		}
		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(pointSeries);       
		// Creating XYSeriesRenderer to customize expenseSeries
		XYSeriesRenderer pointsRenderer = new XYSeriesRenderer();
		pointsRenderer.setColor(Color.rgb(220, 80, 80));
		pointsRenderer.setFillPoints(true);
		pointsRenderer.setLineWidth(2);
		pointsRenderer.setDisplayChartValues(true);        
		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setXLabels(0);
		multiRenderer.setChartTitle("Days vs Points Chart");
		multiRenderer.setXTitle("Month " + label);
		multiRenderer.setYTitle("Points earned");
		multiRenderer.setZoomButtonsVisible(true);    
		for(int i=0; i< mondays.length;i++)
		{
			multiRenderer.addXTextLabel(i, String.valueOf(mondays[i]));          
		}  
		multiRenderer.addSeriesRenderer(pointsRenderer);
		return ChartFactory.getBarChartView(context, dataset, multiRenderer, BarChart.Type.DEFAULT);
	}

	private int GetDayIndex(int[] xdays, int day)
	{
		int index = -1;
		for(int i =0; i < xdays.length; i++)
		{
			if(xdays[i] == day)
			{
				index = i;
				break;
			}
		}
		return index;
	}
}
