package aziendaenergia.entities;

public abstract class StatoAbs {
	    String stato;
	    
	    StatoAbs(String stato2) {
	        this.stato = stato2;
	    }

	    public abstract String tipo();
	}
