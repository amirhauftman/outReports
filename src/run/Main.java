package run;

import java.io.File;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import view.ReportsView;

public class Main extends Application {

	public static final String FILEPATH = "data.dat"; 
	
	@Override
	public void start(Stage stage) throws Exception {

		boolean isFileExists = (new File(FILEPATH).exists());
		
		Model model = new Model();
		if ( isFileExists ) {
			model.load(FILEPATH);
		}
		ReportsView view=  new ReportsView(stage);
		
		Controller controller = new Controller(model, view);
		if ( isFileExists ){
			controller.initFromFile();
		}
		
	}
	public static void main(String[] args) {
		
		launch(args);
	}

}
