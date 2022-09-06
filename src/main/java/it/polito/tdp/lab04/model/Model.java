package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(int matricola) {
		return this.studenteDAO.getStudenteByMatricola(matricola);
	}
	
	public List<Studente> getIscrittiCorso(String nomeCorso) {
		return this.studenteDAO.getIscrittiCorso(nomeCorso);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return this.corsoDAO.getCorsiStudente(matricola);
	}
	
	public boolean inscriviStudenteACorso(int matricola, String codins) {
		return this.corsoDAO.inscriviStudenteACorso(matricola, codins);
	}
}
