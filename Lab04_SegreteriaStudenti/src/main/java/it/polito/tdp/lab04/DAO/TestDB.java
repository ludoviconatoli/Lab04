package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		//cdao.getTuttiICorsi();
		
		/*StudenteDAO sdao = new StudenteDAO();
		System.out.println(sdao.getTuttiStudenti().size());
		System.out.println(cdao.getStudentiIscrittiAlCorso(new Corso("", 0, "", 0)).size());
		*/
		
	}

}
