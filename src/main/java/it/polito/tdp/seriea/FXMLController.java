package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Team> boxSquadra;

    @FXML
    private ChoiceBox<Season> boxStagione;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaTifosi;

    @FXML
    private Button btnAnalizzaSquadre;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaSquadre(ActionEvent event) {
    	String msg = model.doCreaGrafo();
    	txtResult.appendText(msg);
    	boxSquadra.getItems().clear();
    	boxSquadra.getItems().addAll(model.getVertex());
    }

    @FXML
    void doCalcolaConnessioniSquadra(ActionEvent event) {
    	if(boxSquadra.getValue()==null) {
    		txtResult.appendText("Seleziona una squadra");
    		return;
    	}
    	String msg = model.doConnessioniSquadra(boxSquadra.getValue());
    	txtResult.appendText(msg);
    }

    @FXML
    void doSimulaTifosi(ActionEvent event) {
    	if(boxStagione.getValue()==null) {
    		txtResult.appendText("Seleziona una squadra");
    		return;
    	}
    	String msg = model.doSimulazione(boxStagione.getValue());
    	txtResult.appendText(msg);
    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxStagione.getItems().clear();
		boxStagione.getItems().addAll(model.getSeason());
	}
}