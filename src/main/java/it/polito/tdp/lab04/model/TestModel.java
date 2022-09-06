package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		StudenteDAO studenteDAO = new StudenteDAO();
		CorsoDAO corsoDAO = new CorsoDAO();
		
		/*
		 * 	Write here your test model
		 */

		Studente s = studenteDAO.getStudenteByMatricola(146101);
		List<Corso> corsiStudente = corsoDAO.getCorsiStudente(146101);
		
		System.out.println(s);
		for(Corso c: corsiStudente)
		{
			System.out.println(c);
		}
	}

}
