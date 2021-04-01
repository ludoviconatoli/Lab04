package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}

			corsi.add(new Corso("",0, "", 0));
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	
	public void getCorso(Corso corso) {
		
	}*/

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT * "
				+ "FROM studente, iscrizione "
				+ "WHERE studente.matricola = iscrizione.matricola AND codins = ?";
		
		ArrayList<Studente> s = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			if(corso.getCodice().equals(""))
			{
				StudenteDAO sd = new StudenteDAO();
				s.addAll(sd.getTuttiStudenti());
				return s;
			}
			st.setString(1, corso.getCodice());

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente stu = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				s.add(stu);
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return s;
	}

	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		boolean trovato = false;
		String sql ="SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola = i.matricola AND s.matricola = ? "
				+ "AND i.codins = c.codins AND c.codins = ?";
		
		try{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodice());
			
			ResultSet rs = st.executeQuery();
			if(!rs.next()) {
				
				rs.insertRow();
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException sqle) {
			System.out.println("Errore nella query");
		}
		
		return trovato;
	}
	
	
	public List<Corso> getCorsiDiStudente (int matricola){
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola = i.matricola AND s.matricola = ? "
				+ "AND i.codins = c.codins";
		
		List<Corso> corsi = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				corsi.add(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return corsi;
	}

}
