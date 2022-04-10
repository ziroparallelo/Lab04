package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class IscrizioneDAO {
	
	public boolean iscriviStudente(Studente s, Corso c)	{
	
		String sql = "INSERT INTO iscrizione (`matricola`, `codins`) VALUES "
				+ "(?, ?);";
		int v = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			
			v = st.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("ERRORE: Accesso DB");
			return false;
		}	
		
		if(v == 1)
			return true;
		return false;
		
	}
		
}
				
		
