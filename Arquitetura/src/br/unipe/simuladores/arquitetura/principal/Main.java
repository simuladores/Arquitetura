package br.unipe.simuladores.arquitetura.principal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unipe.simuladores.arquitetura.componentes.circulos.CaixaFormulario;
import br.unipe.simuladores.arquitetura.componentes.circulos.Computador;
import br.unipe.simuladores.arquitetura.componentes.internos.Variavel;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;
import br.unipe.simuladores.arquitetura.excecoes.DadosInvalidosException;
import br.unipe.simuladores.arquitetura.excecoes.VariavelExistenteException;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main (String args[]) {

		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		 //Ajusta a cena para ocupar toda a tela do monitor
        Rectangle2D tela = Screen.getPrimary().getVisualBounds();
		
		TelaPrincipal telaPrincipal = new TelaPrincipal(stage, 
				"SOAC - Simulador de Organização e Arquitetura de Computadores", 
				Color.WHITE, tela.getHeight(), tela.getWidth());
		telaPrincipal.exibir();
		
	}
	

}
