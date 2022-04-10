package it.polito.tdp.lab04.model;

public class Iscrizione {

	private Studente studente;
	private Corso corso;
	
	public Iscrizione(Studente studente, Corso corso) {
		this.studente = studente;
		this.corso = corso;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		result = prime * result + ((studente == null) ? 0 : studente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Iscrizione other = (Iscrizione) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		if (studente == null) {
			if (other.studente != null)
				return false;
		} else if (!studente.equals(other.studente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Iscrizione [studente=" + studente + ", corso=" + corso + "]";
	}
	
	
	
}
