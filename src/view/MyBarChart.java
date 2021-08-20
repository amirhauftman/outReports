package view;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

// this is tailor made class to make a bar chart out of map
public class MyBarChart<X, Y> extends BarChart<X, Y> {

	private final int INITIAL_WIDTH = 1000;

	private XYChart.Series<X, Y> series;

	private String title;
	private String xLabel;
	private String yLabel;

	public MyBarChart(Axis<X> xAxis, Axis<Y> yAxis) {
		super(xAxis, yAxis);

	}

	public void setData(Map<X, Y> map, String title, String xLabel, String yLabel) {
		
		this.xLabel = xLabel;
		this.yLabel = xLabel;
		this.title = title;
		
		
		this.setTitle(title);
		this.getXAxis().setLabel(xLabel);
		this.getYAxis().setLabel(yLabel);

		XYChart.Series<X, Y> series1 = new XYChart.Series<X, Y>();
		for (Entry<X, Y> entry : map.entrySet()) {
			series1.getData().add(new XYChart.Data<X, Y>(entry.getKey(), entry.getValue()));
		}

		this.series = series1;

		if (this.getData() != null)
			this.getData().clear();
		this.getData().addAll(series1);
		setWidth(INITIAL_WIDTH);
		this.setStyle(this.getCssBarFillColor(Util.BLUE) + this.getCssBarRadius(10));

	}
	
	private String getTheTitle() {
		return this.title;
	}
	
	public void setData(Map<X, Y> map) {
		setData(map, title, xLabel, yLabel);

	}
	

	public XYChart.Series<X, Y> getSeries() {
		return series;
	}

	public String getxLabel() {
		return xLabel;
	}

	public String getyLabel() {
		return yLabel;
	}


	public void setWidth(int width) {
		this.setPrefWidth(width);
	}

	public String getCssBarFillColor(String color) {
		return "-fx-bar-fill: " + color + ";";
	}

	public String getCssBarInsets(int[] arr) {
		String arrToString = Arrays.toString(arr);
		return "-fx-background-insets: " + arrToString.substring(1, arrToString.length() - 1) + ";";
	}

	public String getCssBarRadius(int radius) {
		return "-fx-background-radius: " + radius + " " + radius + " 0 0, " + (radius - 2) + " " + (radius - 2)
				+ " 0 0, " + (radius - 4) + " " + (radius - 4) + " 0 0;";
	}

	public String getCssBarRadius(int radius, boolean isTop) {
		if (isTop)
			return getCssBarRadius(radius);
		else
			return "-fx-background-radius: 0 0 " + radius + " " + radius + ", 0 0 " + (radius - 2) + " " + (radius - 2)
					+ ", 0 0 " + (radius - 4) + " " + (radius - 4) + ";";
	}

	public void styleBars(String colorPositive, String colorNegative, int radius) {
		for (XYChart.Data<X, Y> d : series.getData()) {
			if (((Number) d.getYValue()).intValue() > 0)
				d.getNode().setStyle(getCssBarFillColor(colorPositive) + getCssBarInsets(new int[] { 0, 1, 2 })
						+ getCssBarRadius(radius, true));
			else
				d.getNode().setStyle(getCssBarFillColor(colorNegative) + getCssBarInsets(new int[] { 0, 1, 2 })
						+ getCssBarRadius(radius, false));
		}
	}

	public void styleBars(String color, int radius) {
		for (XYChart.Data<X, Y> d : series.getData()) {
			d.getNode().setStyle(
					getCssBarFillColor(color) + getCssBarInsets(new int[] { 0, 1, 2 }) + getCssBarRadius(radius, true));
		}
	}

}
