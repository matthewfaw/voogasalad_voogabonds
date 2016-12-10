package main;

import java.io.IOException;



import javafx.application.Application;
import javafx.stage.Stage;
import engine.controller.MockGameDataConstructor;
import engine.exceptions.SerializationException;

public class Main extends Application{

	private MainInitializer controller;
	
	@Override
	public void start (Stage s) throws IOException {
		controller = new MainInitializer(s);
		s.setTitle(controller.getTitle());
	}
	
	public static void main (String[] args) {
		launch(args);
	}
	
}
