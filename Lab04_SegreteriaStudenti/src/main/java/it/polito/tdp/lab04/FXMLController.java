/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> tendinaCorsi;

    @FXML
    private Button btCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btVerifica;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btCercaCorsi;

    @FXML
    private Button btIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	Corso c = this.tendinaCorsi.getValue();
    	this.txtResult.setText("");
    	if(c == null)
    	{
    		this.txtResult.setText("Devi selezionare un corso");
    		return;
    	}
    	
    	ArrayList<Studente> stu = (ArrayList<Studente>) this.model.getStudentiIscritti(c);
    	for(Studente stud: stu)
    		this.txtResult.appendText(stud.toString() +"\n");
    	
    	return;
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.tendinaCorsi.setValue(null);
    	this.txtResult.clear();
    	this.txtMatricola.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	return;
    }

    @FXML
    void doVerifica(ActionEvent event) {
    	String s = this.txtMatricola.getText();
    	if(s == "") {
    		this.txtResult.setText("Devi inserire un numero di matricola");
    		return;
    	}
    	
    	int m = Integer.parseInt(s);
    	ArrayList<Studente> stu = (ArrayList<Studente>)this.model.getTuttiStudenti();
    	for(Studente st: stu)
    	{	
    		if(st.getMatricola() == m)
    		{
    			this.txtCognome.setText(st.getCognome());
    			this.txtNome.setText(st.getNome());
    			return;
    		}
    	}
    	this.txtResult.setText("La matricola inserita non è corretta");
    	return;
    }

    @FXML
    void initialize() {
        assert tendinaCorsi != null : "fx:id=\"tendinaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btCercaIscrittiCorso != null : "fx:id=\"btCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btVerifica != null : "fx:id=\"btVerifica\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btCercaCorsi != null : "fx:id=\"btCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btIscrivi != null : "fx:id=\"btIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btReset != null : "fx:id=\"btReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model = m;
       	this.tendinaCorsi.getItems().addAll(m.getTuttiICorsi());
    }
}
