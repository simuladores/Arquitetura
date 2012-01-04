package br.unipe.simuladores.arquitetura.telas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TelaWebView extends Tela{

	private String url;
	
	public TelaWebView(String titulo, Color cor, double height, double width, String url) {
		super(titulo, cor, height, width);
		setUrl(url);
		criar();
	}
	
	@Override
	public void criar() {
		
		WebView view = new WebView();
		view.setTranslateX(550);
		final WebEngine eng = view.getEngine();
		eng.load(url);
		final TextField locationField = new TextField(url);
		locationField.setMaxHeight(Double.MAX_VALUE);
		locationField.setMinWidth(500);
		locationField.setTranslateX(200);
		Button goButton = new Button("Ir");
		goButton.setTranslateX(400);
	    goButton.setDefaultButton(true);
		EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				eng.load(locationField.getText().startsWith("http://") ? locationField.getText() :
						"http://" + locationField.getText());
			}
		};
		goButton.setOnAction(goAction);
		locationField.setOnAction(goAction);
		eng.locationProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				locationField.setText(newValue);
			}
		});
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		GridPane.setConstraints(locationField, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
		GridPane.setConstraints(goButton,1,0);
		GridPane.setConstraints(view, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
		grid.getColumnConstraints().addAll(
				new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
				new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
		);
		grid.getChildren().addAll(locationField, goButton, view);
		root.getChildren().add(grid);
		
		view.setMinWidth(stage.getWidth());
		view.setMinHeight(stage.getHeight());
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
