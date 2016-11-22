package gamePlayerView;

import engine.IObservable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public abstract class InfoBox {
	public HBox myDisplay= new HBox();
	Label myLabel=new Label();
	TextArea myOutput= new TextArea();
	
	public InfoBox(){
		//
	}

	public HBox makeDisplay(String text){
		HBox h=new HBox();
		myLabel= makeLabel(text);
		myOutput = makeTextArea();
		h.getChildren().addAll(myLabel,myOutput);
		return h;
	}
	
	public TextArea makeTextArea() {
		TextArea t= new TextArea();
		t.setPrefSize(5, 5);
		return t;
	}

	public Label makeLabel(String text){
		Label l= new Label(text);
		l.setFont(new Font("Cambria",14));
		return l;
	}
	
	public Node getView(){
		return myDisplay;
	}

	public abstract void giveObject(IObservable aCash);

	public abstract void update(Object aChangedObject);
}
