
/**Clasa este o particularizare a unui Sir.
 * Am preferat sa o numesc coada, chiar daca 
 * introducerea elementelor se face in functie de 
 * probabilitate si nu dupa principiul FIFO.
 * 
 */

/**
 * @author Marius Avram
 *
 */
public class Coada extends Sir {
	
	

	/**Constructorul sorteaza elementele in functie de
	 * probabilitate crescator.
	 * 
	 * @param text textul introdus de la tastatura care 
	 * se doreste sa fie codificat
	 */
	public Coada(String text) { 
		// 
		super(text);
		this.SortareProbabilitate();
	}
	
	
	/** Scoate primul element din coada ( cel cu probabilitatea cea mai mica).
	 * 
	 * @return elementul scos
	 */
	public NodArbore Scoate () { 
		
		NodArbore rezultat;
		int lung = this.Lungime() ;
		
		if (lung ==0 ) { 
			return null;
		}
		// In functie de tipul nodului va trebui sa se apele constructori diferiti, 
		// pentru funze si pentru noduri din interior. 
		
		if ( this.simboluri[0].getStanga() == null && this.simboluri[0].getDreapta() == null ) { 
			// Avem frunza
			rezultat = new NodArbore(this.simboluri[0].getVal(), this.simboluri[0].getProb()) ;
		} 
		else { 
			// Avem nod cu descendenti
			rezultat = new NodArbore(this.simboluri[0].getStanga(), this.simboluri[0].getDreapta());
		}
		
		for ( int i = 0 ; i< lung-1 ; i++ ) { 
				this.simboluri[i] =  this.simboluri[i+1];
		}
		
		this.simboluri[lung-1] = new NodArbore(); 
		
		
		return rezultat;
	}
	
	
	/** Aduga un element in coada in functie de probabilitate
	 * 
	 * @param n nodul urmator din coada de prioritati care 
	 * trebuie adaugat
	 */
	public void Adauga( NodArbore n) { 
		//
		int pos;
		int lung = this.Lungime();
		for ( pos=0; pos< lung; pos++ ) { 
			
			if ( n.getProb() < this.simboluri[pos].getProb()) { 
				break;
			}

		}
		
		for (int i= lung; i > pos; i--) { 
			this.simboluri[i] = this.simboluri[i-1];
		}
		this.simboluri[pos] = new NodArbore(n.getStanga(), n.getDreapta()); 
		
	}
	
	
	/** Determina lungimea cozii. Este nevoie de o astfel de functie
	 *  pentru ca am lucrat cu un vector de Noduri si nu cu un ArrayList.
	 *  @return lungimea cozii
	 */
	public int Lungime() {
		// 
		int lung = this.simboluri.length;
		for ( int i = 0; i <  lung ; i++ ) { 
			if (this.simboluri[i].getProb() == 0 ) { 
				return i;
			}
		}
		return lung;
	}
	
	/** 
	 * Afisarea cozii (folosita pentru debuging) 
	 * Spre deosebire de functia de afisare pentru sir
	 * aceasta afiseaza si probabilitatea intre [*]
	 * 
	 * @return Stringul ce se poate afisa cu System.out.println()
	 */
	public String toString() { 
		
		String rezultat=""; 
		int lung = this.simboluri.length;
		 for ( int i =0; i< lung; i++) { 
			 
			rezultat += ((NodArbore)this.simboluri[i]).getValoares() + "[" ; 
			rezultat += ((NodArbore)this.simboluri[i]).getProb() + "] "; 
		
		 }
		return rezultat; 
	}
	
	/** Functie folosita pentru afisare ( va afisa in loc
	 *  de caractere codurile Ascii ) Rol: debugging.
	 * @return String ce se poate afisa cu System.out.println()
	 */
	public String CodificareAscii () { 
		
		String rezultat = ""; 
		for (int i=0; i< this.simboluri.length; i++ ) { 
			rezultat += (int) this.simboluri[i].getVal();
			rezultat += " " + this.simboluri[i].getValoares() + "\n";
		}
		return rezultat;
	}
	
	
	/** Returneaza codul binar pentru un caracter
	 * @param c caracterul
	 * @return codul binar unic, prefix-free
	 */
	public String CodCaracter( char c ) { 
		// 
		for ( int i=0; i< this.simboluri.length; i++) { 
			if ( this.simboluri[i].getVal() == c ) { 
				return this.simboluri[i].getValoares();
			}
		}
		return "";
	}
	
	/**	Codifica textul dat de la tastura. Transforma 
	 * fiecare caracter in codul binar corespunzator.
	 * 
	 * @param text textul dat de la tastatura
	 * @return un String ce contine codificarea intregului text
	 */
	public String Codificare(String text)  { 
	
		int lung = text.length();
		String rezultat= "" ; 
		for (int i =0; i< lung; i++ ) { 
		 rezultat += this.CodCaracter( text.toCharArray()[i] ); 
		}
		
		return rezultat;
		
	}



}
