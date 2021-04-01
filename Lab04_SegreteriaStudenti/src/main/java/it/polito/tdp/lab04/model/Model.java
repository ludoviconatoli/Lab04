package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	CorsoDAO cd = new CorsoDAO();
	StudenteDAO sd = new StudenteDAO();
	
	public List<Corso> getTuttiICorsi(){
		ArrayList<Corso> c = new ArrayList<>();
		c.addAll(cd.getTuttiICorsi());
		return c;
	}
	
	public List<String> getTuttiINomiCorsi(){
		ArrayList<String> s = new ArrayList<>();
		for(Corso c: cd.getTuttiICorsi())
		{
			s.add(c.getNome());
		}
		s.add("");
		return s;
	}
	
	public List<Studente> getStudentiIscritti(Corso corso){
		ArrayList<Studente> s = new ArrayList<>();
		s.addAll(cd.getStudentiIscrittiAlCorso(corso));
		return s;
	}
	
	public List<Studente> getTuttiStudenti(){
		ArrayList<Studente> s = new ArrayList<>();
		s.addAll(sd.getTuttiStudenti());
		return s;
	}
	
	public boolean verificaStudente(int matricola) {
		return this.sd.verificaStudente(matricola);
	}
	
	public List<Corso> getCorsiDiStudente(int matricola){
		return this.cd.getCorsiDiStudente(matricola);
	}
	
	public boolean iscriviStudente(int matricola, String codice){
		Studente s = new Studente(matricola, null, null, null);
		Corso c = new Corso(codice, 0, null, 0);
		boolean trovato = this.cd.inscriviStudenteACorso(s, c);
		return trovato;
	}
}
