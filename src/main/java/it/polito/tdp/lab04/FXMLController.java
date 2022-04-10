package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.ConnectDB;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
    private ComboBox<String> cmbCorso;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private CheckBox txtOkButton;

    @FXML
    private TextArea txtOutput;


    @FXML
    void doInserimentoAutomatico(ActionEvent event) 
    {
    	if(txtOkButton.isSelected())
    	{
        	String txt = txtMatricola.getText();
        	int matricola = -1;
        	try {
            	matricola = Integer.parseInt(txt);
        	}
        	catch (NumberFormatException nfe)
        	{
        		txtOutput.clear();
            	txtOutput.setText("Errore formato Stringa");
            	return;
        	}
        	
        	Studente s = model.getStudentePerMatricola(matricola);
        	
        	if(s == null)
        	{
        		txtOutput.setText("Errore: matricola inesistente");
        		txtCognome.clear();
            	txtNome.clear();;
        		return;
        	}
        	txtCognome.setText(s.getCognome());
        	txtNome.setText(s.getNome());
    	}
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) 
    {
    	String txt = txtMatricola.getText();
    	int matricola = -1;
    	try {
        	matricola = Integer.parseInt(txt);
    	}
    	catch (NumberFormatException nfe)
    	{
    		txtOutput.clear();
        	txtOutput.setText("Errore formato matricola");
        	return;
    	}
    	
    	Studente s = model.getStudentePerMatricola(matricola);
    	
    	if(s == null)
    	{
    		txtOutput.setText("Errore: matricola inesistente");
    		return;
    	}
    	
    	List<Corso> corsi = this.model.getCorsiPerMatricola(matricola);
    	txtOutput.clear();
    	for(Corso c: corsi)
    	{
    		txtOutput.appendText(c +"\n");
    	}
    	
    	
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	String nome = cmbCorso.getValue();
    	
    	if(nome == null || nome.compareTo("")==0)
    	{
    		txtOutput.clear();
    		txtOutput.setText("Errore: inserire corso");
    	}
    	
    	List<Studente> studenti = model.getStudentiIscrittiAlCorso(nome);
    	txtOutput.clear();
    	
    	if(studenti.size() == 0)
    	{
    		txtOutput.setText("Non ci sono iscritti");
    	}
    	for(Studente s: studenti)
    		txtOutput.appendText(s.toString()+"\n");

    }
    
    @FXML
    void doCercaStudente(ActionEvent event) {
    	
    	//MATRICOLA
    	
    	//CONTROLLI
    	
    	//Formato matricola corretto?
    	String txt = txtMatricola.getText();
    	int matricola = -1;
    	try {
        	matricola = Integer.parseInt(txt);
    	}
    	catch (NumberFormatException nfe)
    	{
    		txtOutput.clear();
        	txtOutput.setText("Errore formato matricola");
        	return;
    	}
    	//Studente esistente?
    	Studente s = model.getStudentePerMatricola(matricola);
    	if(s == null)
    	{
    		txtOutput.setText("Errore: matricola inesistente");
    		return;
    	}
    	
    	//COMBO BOX
    	
    	//CONTROLLI
    	String nome = cmbCorso.getValue();
    	
    	if(nome == null || nome.compareTo("")==0)
    	{
    		txtOutput.clear();
    		txtOutput.setText("Errore: inserire corso");
    	}
    	
    	//CERCO TUTTI GLI ISCRITTI PER QUEL CORSO
    	
    	List<Studente> studenti = model.getStudentiIscrittiAlCorso(nome);
    	
    	//ORA VEDO SE IN QUESTA LISTA LO STUDENTE E' PRESENTE 
    	//(DEFINITO IL METODO EQUALS PER MATRICOLA)
    	
    	if(studenti.contains(s))
    	{
    		txtOutput.clear();
    		txtOutput.setText("Studente già iscritto al corso");
    	}
    	else
    	{
    		txtOutput.clear();
    		txtOutput.setText("Studente non iscritto al corso");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	//MATRICOLA
    	
    	//CONTROLLI
    	
    	//Formato matricola corretto?
    	String txt = txtMatricola.getText();
    	boolean flag = false;
    	Corso c = null;
    	int matricola = -1;
    	try {
        	matricola = Integer.parseInt(txt);
    	}
    	catch (NumberFormatException nfe)
    	{
    		txtOutput.clear();
        	txtOutput.setText("Errore formato matricola");
        	return;
    	}
    	//Studente esistente?
    	Studente s = model.getStudentePerMatricola(matricola);
    	if(s == null)
    	{
    		txtOutput.setText("Errore: matricola inesistente");
    		return;
    	}
    	
    	//COMBO BOX
    	
    	//CONTROLLI
    	String nome = cmbCorso.getValue();
    	
    	if(nome == null || nome.compareTo("")==0)
    	{
    		txtOutput.clear();
    		txtOutput.setText("Errore: inserire corso");
    	}
    	
    	//CERCO IL CORSO CON QUEL NOME
    	
    	//CERCO TUTTI GLI ISCRITTI PER QUEL CORSO
    	
    	List<Studente> studenti = model.getStudentiIscrittiAlCorso(nome);
    	
    	//ORA VEDO SE IN QUESTA LISTA LO STUDENTE E' PRESENTE 
    	//(DEFINITO IL METODO EQUALS PER MATRICOLA)
    	
    	if(studenti.contains(s))
    	{
    		txtOutput.clear();
    		txtOutput.setText("Studente già iscritto al corso");
    	}
    	else
    	{
    		for(Corso cc: model.getTuttiICorsi())
    			if(cc.getNome().compareTo(nome)==0)
    			{
    				c = cc;
    				break;
    			}
    		flag = this.model.iscriviStudente(s, c);
    		
    		if(flag)
    		{
    			txtOutput.clear();
        		txtOutput.setText("Studente iscritto al corso!");
    		}
    		else {
    			txtOutput.clear();
        		txtOutput.setText("Qualcosa è andato storto... prova ad impiccarti");
    		}
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtOutput.clear();
    	txtOkButton.setSelected(false);
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	cmbCorso.setValue(null);

    }
    
    void setModel(Model model)
    {
    	this.model = model;
    	
    	List<Corso> corsi = model.getTuttiICorsi();
    	for(Corso c : corsi)
    	{
    		cmbCorso.getItems().add(c.getNome());
    	}
    	cmbCorso.getItems().add(null);
    	
    }

    @FXML
    void initialize() {
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOkButton != null : "fx:id=\"txtOkButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";

    }





    


}
