package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteByMatricola(int matricola) {
		
		
		final String sql = "SELECT * "
				+ "from studente "
				+ "where matricola = ?";

		Studente studente;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			if(rs.first()) {
				
			studente = new Studente(rs.getInt("matricola"), rs.getString("cognome")
						,rs.getString("nome"), rs.getString("CDS"));

			rs.close();
			st.close();
			conn.close();
			
			return studente;
			}
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return null;
	}
	
	public List<Studente> getIscrittiCorso(String nomeCorso){
		
		final String sql ="Select s.matricola, s.cognome, s.nome, s.CDS "
				+ "From corso c, iscrizione i, studente s "
				+ "where s.matricola = i.matricola and c.codins = i.codins "
				+ "and c.nome = ? "
				+ "group by s.matricola, s.cognome, s.nome, s.CDS";
		
		
		List<Studente> iscritti = new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, nomeCorso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				iscritti.add(new Studente(rs.getInt("matricola"), 
						rs.getString("cognome"), rs.getString("nome")
						, rs.getString("CDS")));
			}

			rs.close();
			st.close();
			conn.close();
			
			return iscritti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
