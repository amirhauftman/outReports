package view;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class CenterVBox extends VBox {

	public static final int PADDING = 30;
	
	public CenterVBox() {
		super();
		this.setPadding(new Insets(PADDING));
	}
	
}
