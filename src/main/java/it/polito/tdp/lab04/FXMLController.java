/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

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

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Checkbox"
    private CheckBox Checkbox; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorsi"
    private ComboBox<String> txtCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    
    @FXML
    void handleCercaCorsoStudentehandleCercaCorsoStudente(ActionEvent event) {
    	

    	txtRisultato.clear();
    	
    	//1. Controlli
    	
    	//Validità matricola
    	String matricola = txtMatricola.getText();
    	int matricolaNumerica = this.getMatricolaValida(matricola);
    	
    	if(matricolaNumerica == -1) {
    		return;
    	}
    	
    	//Validità corso
    	String corso = txtCorsi.getValue();
    	
    	//Se il sito inserito è null o "" allora segnalare l'utente con un messaggio di errore
    	if(corso == null || corso.equals(""))
    	{
    		System.out.println("ERRORE: nessun corso inserito");
    		txtRisultato.setText("ERRORE: nessun corso inserito");
    		return;
    	}
    	
    	//2. Operazioni
    	
     if(this.isStudenteIscritto(corso, matricolaNumerica))
     {
    	 txtRisultato.setText("Studente iscritto al corso");
    	 return;
     }
     else {
    	 txtRisultato.setText("Studente NON iscritto al corso");
    	 return;
     }
    	
    	

    	
    }
    //Cerca i corsi che segue uno studente
    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String matricola = txtMatricola.getText();
    	int matricolaNumerica = this.getMatricolaValida(matricola);
    	
    	if(matricolaNumerica == -1) {
    		return;
    	}
    	
    	//2. Operazioni 3. Aggiornamento
    	
    	List<Corso> corsiStudente = this.model.getCorsiStudente(matricolaNumerica);
    	
    	if(corsiStudente.size()==0)
    	{
    		txtRisultato.setText("Lo studente non è iscritto a nessun corso");
    	}
    	else for(Corso c: corsiStudente) {
    		txtRisultato.appendText(c + "\n");
    	}
    	return;

    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String corso = txtCorsi.getValue();
    	
    	//1. Controlli
    	
    	//Se il sito inserito è null o "" allora segnalare l'utente con un messaggio di errore
    	if(corso == null || corso.equals(""))
    	{
    		System.out.println("ERRORE: nessun corso inserito");
    		txtRisultato.setText("ERRORE: nessun corso inserito");
    		return;
    	}
    	
    	//2. Operazione 
    	
    	List<Studente> iscritti = this.model.getIscrittiCorso(corso);
    	
    	//3. Aggiornamento
    	
    	for(Studente s: iscritti)
    		txtRisultato.appendText(s +"\n");
    	
    	return;

    }

    @FXML
    void handleIscrivi(ActionEvent event) {


    	txtRisultato.clear();
    	
    	//1. Controlli
    	
    	//Validità matricola
    	String matricola = txtMatricola.getText();
    	int matricolaNumerica = this.getMatricolaValida(matricola);
    	
    	if(matricolaNumerica == -1) {
    		return;
    	}
    	
    	//Validità corso
    	String corso = txtCorsi.getValue();
    	
    	if(corso == null || corso.equals(""))
    	{
    		System.out.println("ERRORE: nessun corso inserito");
    		txtRisultato.setText("ERRORE: nessun corso inserito");
    		return;
    	}
    	
    	//Studente già iscritto?
    	if(isStudenteIscritto(corso, matricolaNumerica)) {
    		System.out.println("ERRORE: Studente già iscritto al corso selezionato");
    		txtRisultato.setText("ERRORE: Studente già iscritto al corso selezionato");
    		return;
    	}
    	
    	//2. Operazioni
    	
    	Map<String, String> corsiCodNome = new HashMap<String, String>();
    	List<Corso> tuttiICorsi = this.model.getTuttiICorsi();
    	for(Corso c: tuttiICorsi)
    		corsiCodNome.put(c.getNome(), c.getCodins());
    	
    	if(this.model.inscriviStudenteACorso(matricolaNumerica, corsiCodNome.get(corso))) {
    		txtRisultato.setText("Studente iscritto al corso selezionato");
    		return;
    	}
    	else {
    		txtRisultato.setText("L'operazione di iscrizione al corso selezionato"+ 
    	" non è andata a buon fine");
    		return;
    	}
    	
    }

    private boolean isStudenteIscritto(String corso, int matricolaNumerica) {
    	
    	Set<Studente> iscrittiCorso = new HashSet<Studente>();
    	List<Studente> iscritti = this.model.getIscrittiCorso(corso);
    	
    	for(Studente s : iscritti) {
    		iscrittiCorso.add(s);
    	}
    	
    	Studente studente = this.model.getStudenteByMatricola(matricolaNumerica);
    	
    	if(iscrittiCorso.contains(studente)) {
    		return true;
    	} else return false;
	}
	@FXML
    void handleReset(ActionEvent event) {
    	
    	txtRisultato.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtCorsi.setItems(null);
    	Checkbox.setSelected(false);
    	return;

    }
    
    @FXML
    void onClickFulfill(ActionEvent event) {
    	
    	if(!Checkbox.isSelected())
    		return;
    	//1. Controlli
    	
    	txtNome.clear();
    	txtCognome.clear();
    	
    	String matricola = txtMatricola.getText();
    	int matricolaNumerica = this.getMatricolaValida(matricola);
    	
    	if(matricolaNumerica == -1) {
    		return;
    	}
    	
    	
    	//2. Operazioni
    	
    	Studente studente = this.model.getStudenteByMatricola(matricolaNumerica);
    	
    	//Fatta per controllare se la matricola è esistente
    	
    	//3. Pulizia e aggiornamento
    	

    	txtNome.setText(studente.getNome());
    	txtCognome.setText(studente.getCognome());
    	
    	return;

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Checkbox != null : "fx:id=\"Checkbox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorsi != null : "fx:id=\"txtCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    
    
    
    
    
    /**
     * Controlla la validità di una matricola inserita dall'utente restituendo il suo valore numerico
     * @param String matricola
     * @return -1 se la matricola è errata, Integer matricola se il valore è corretto
     */
    public Integer getMatricolaValida(String matricola)
    {
    	
    	
    	Integer matricolaNumerica = null;
//    	Il valore inserito è numerico?
    	
    	try {
    		matricolaNumerica = Integer.parseInt(matricola);
    	} catch (NumberFormatException nfe)
    	{
    		System.out.println("Matricola inserita non numerica");
    		nfe.printStackTrace();
    		txtRisultato.setText("ERRORE: Inserisci una matricola numerica");
    		Checkbox.setSelected(false);
    		return -1;
    	}

//    	Il campo è vuoto?
//    	Max 6 numeri
    	
    	if(matricolaNumerica == null || matricolaNumerica>999999 || matricolaNumerica<100000 ) {
    		System.out.println("Matricola inserita nulla o maggiore di 6 numeri");
    		txtRisultato.setText("ERRORE: Inserisci una matricola (max 6 numeri)");
    		Checkbox.setSelected(false);
    		return -1;
    	}
//    	Esiste la matricola?
    	Studente studente = this.model.getStudenteByMatricola(matricolaNumerica);
    	
    	if(studente == null) {
    		System.out.println("Matricola valida ma inesistente");
    		txtRisultato.setText("ERRORE: Matricola valida ma inesistente");
    		Checkbox.setSelected(false);
    		return -1;
    	}
    	
    	return matricolaNumerica;
    }

    public void setModel(Model model) {
    	this.model = model;
    	
    	txtCorsi.getItems().clear();
    	for(Corso c : model.getTuttiICorsi())
    		txtCorsi.getItems().add(c.getNome());
    	txtCorsi.getItems().add("");
    }
    
    
    
    

}
