package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{

	private StageController controller;
	
	@Override
	public void start (Stage s) throws IOException {
		controller = new StageController(s);
		s.setTitle(controller.getTitle());
	}
	
	public static void main (String[] args) {
		launch(args);
	}
	
}
