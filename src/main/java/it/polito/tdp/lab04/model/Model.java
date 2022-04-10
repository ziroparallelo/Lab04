package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrizioneDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	private IscrizioneDAO iscrizioneDAO;
	
	public Model()
	{
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
		iscrizioneDAO = new IscrizioneDAO();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String nome)
	{
		return this.studenteDAO.getStudentiIscrittiAlCorso(nome);
	}
	
	public Studente getStudentePerMatricola(int matricola)
	{
		return this.studenteDAO.getStudentePerMatricola(matricola);
	}
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<Corso> getCorsiPerMatricola(int matricola)
	{
		return this.corsoDAO.getCorsiPerMatricola(matricola);
	}
	
	public boolean iscriviStudente(Studente s, Corso c) 
	{
		return this.iscrizioneDAO.iscriviStudente(s, c);
	}
}
