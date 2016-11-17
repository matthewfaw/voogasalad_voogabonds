package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{

	private MainInitalizer controller;
	
	@Override
	public void start (Stage s) throws IOException {
		controller = new MainInitalizer(s);
		s.setTitle(controller.getTitle());
	}
	
	public static void main (String[] args) {
		launch(args);
	}
	
}
