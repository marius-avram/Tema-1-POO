
public class NodArbore {
	
	private char val ; 
	private int prob; 
	private NodArbore stanga; 
	private NodArbore dreapta;
	
	 /*Campul 'valoares' il vom folosi pentru a sti 
	 care sunt simbolurile din subarbore. 
	 Cand ajungem la o frunza valoarea subarborelui 
	 valoares egala cu val*/
	
	
	private String valoares; 

	
	/** 
	 * Constructor pentru noduri care nu exista.
	 * Deoarece am lucrat cu un vector si nu cu un ArrayList 
	 * am decis sa codific elementele din coada care nu exista
	 * cu valoarea ' ' si probabilitatea 0 
	 */
	public NodArbore () { 
		this.setVal(' '); 
		this.setProb(0);
	}
	
	
	
	
	/**
	 * Constructorul folosit in cazul in care aveam frunza.
	 * Nu exista stanga si dreapta, de aceea nu le initializam
	 *  
	 * @param val un caracter din textul dat de la tastatura
	 * @param prob frecventa cu care acesta se intalneste in text
	 * 
	 * 
	 */
	public NodArbore (char val, int prob) { 
		this.setVal(val);
		this.setProb(prob);
		this.setValoares(Character.toString(val));
	}
	
	
	/**
	 * Constructorul folosit in cazul in care avem descendenti
	 * nodurile primite ca parametru se dau pe baza cozii de prioritati
	 * 
	 * @param stanga Nodul din stanga 
	 * @param dreapta Nodul din dreapta
	 * 
	 */
	public NodArbore ( NodArbore stanga , NodArbore dreapta )   { 
		this.stanga = stanga;
		this.dreapta = dreapta;
		this.setProb(stanga.getProb()+ dreapta.getProb());
		this.setVal('$');
		this.valoares = stanga.getValoares() + ""  + dreapta.getValoares();
	}

	
	/**
	 * Constructor folosit pentru partea de decodificare
	 * din program.
	 * 
	 * @param val caracterul 
	 * @param cod codificatia binara a acestuia
	 * 
	 */
	public NodArbore (char val, String cod) { 
		this.setVal(val);
		this.setValoares(cod.trim());
	}
	
	
	
	

	
	/*Setteri si Getteri*/
	public void setVal ( char val ) { 
		this.val = val;
	}
	
	public char getVal () { 
		return this.val; 
	}
	
	public void setProb ( int prob )  { 
		this.prob = prob ; 
	}
	
	public int getProb () { 
		return this.prob;
	}


	public NodArbore getStanga() {
		return this.stanga;
	}


	public void setStanga(NodArbore stanga) {
		this.stanga = new NodArbore();
	}


	public NodArbore getDreapta() {
		return this.dreapta;
	}


	public void setDreapta(NodArbore dreapta) {
		this.dreapta = new NodArbore();
	}


	public String getValoares() {
		return valoares;
	}


	public void setValoares(String valoares) {
		this.valoares = valoares;
	}
	

	
	
}
