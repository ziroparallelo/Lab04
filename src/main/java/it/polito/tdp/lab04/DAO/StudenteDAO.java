package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/**
	 * Cerca uno studente per matricola
	 * @param matricola
	 * @return Ogetto "Studente" se trova la matricola, null altrimenti
	 */
	public Studente getStudentePerMatricola(int matricola)
	{
		String sql = "Select * "
				+ "from studente "
				+ "where matricola = ?";
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, matricola);
			ResultSet sr = ps.executeQuery();
			Studente studente = null;
			
			while(sr.next())
			{
				studente = new Studente(sr.getInt("matricola"), 
						sr.getString("cognome"), sr.getString("nome"), sr.getString("CDS"));
			}
			
			sr.close();
			ps.close();
			conn.close();
			
			return studente;
			
		}
		catch (SQLException e)
		{
			System.out.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String nome) {
		
		String sql = "Select matricola "
				+ "from iscrizione i, corso c "
				+ "where c.`codins` = i.`codins` && nome = ?";
		
		List<Studente> studenti= new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, nome);
			ResultSet rs = st.executeQuery();
			StudenteDAO DAO = new StudenteDAO();

			while (rs.next()) {

				studenti.add(DAO.getStudentePerMatricola(rs.getInt("matricola")));
				
				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	

}
