
public class Sir {
	 
	public NodArbore[] simboluri; 
	public int dim;
	
	public Sir () { 
		simboluri = new NodArbore[5];
		this.dim = 0;
	}
	
	
	/**Folosim constructorul pentru a determina caracterele unice
	 *  si frecventele lor. 
	 * @param text Textul dat de la tastaura.
	 */
	public Sir (String text) {
		
		
		int lung = text.length();
		int dim = 0; 
		// Vom lucra cu un vector temporar de noduri pentru ca nu 
		// stim care este dimensiunea finala a acestuia.
		NodArbore[] temp = new NodArbore[lung];
		
		// Vom transforma stringul intr-un sir de caractere 
		// pentru a putea detecta care sunt caracterele din el 
		char[] text_c = new char[lung]; 
		text_c = text.toCharArray(); 
		

		// In loc sa folosim o valoare boolean care sa memoreze daca 
		// un anumit element se gaseste in sirul de caractere unice si 
		// un int care sa memoreze indicele, vom folosi doar indicele. 
		// Cand gasim pentru prima data simbolul index = -1, valoare ce 
		// nu poate fi folosita in mod util de catre un array, dar ne 
		// va spune ca aceasta este prima data cand se intalneste simbolul. 
		
		int index; 
		for ( int i = 0; i< lung ; i++ ) { 
			index = -1;
			for ( int j=0; j<dim ; j++ ) { 
				if ( text_c[i] == temp[j].getVal() ) {
					index = j ; 
				}
			}
			// Daca index este >= 0 (real) va incrementa probabilitatea 
			// acelui caracter
			if (index > -1) {
				temp[index].setProb(temp[index].getProb() + 1); 
			} else { 
			// Daca index este == -1 se va creea un nou NodArbore 
			// in sir cu probabilitatea 1
				temp[dim] = new NodArbore(text_c[i], 1 );
				dim++;
			}
				
		}
		
		// Vom copia din arrayul temporar in cel cu simboluri.
		// Aceasta va avea dimensiunea == numarul de elemente unice. 
		
		this.simboluri = new NodArbore[dim]; 
		System.arraycopy(temp, 0, this.simboluri, 0, dim);
		
		
		
	}
	
	
	/**
	 * Dubleaza dimensiunea sirului. Este nevoie de aceasta functie 
	 * pentru ca am lucrat cu un vector normal.
	 */
	public void DubleazaDim() { 
		NodArbore[] temp = new NodArbore[this.dim];
		System.arraycopy(this.simboluri, 0 , temp , 0 , dim);
		this.simboluri = new NodArbore[this.dim * 2]; 
		System.arraycopy(temp, 0, this.simboluri, 0, dim);
	}
	  
	/** Adauga un element in sir. Functia se foloseste pentru a creea sirul 
	 * necesar in cazul decodificari.
	 * @param c caracterul ce se doreste adaugat in sir
	 * @param codificare codificarea binara a elementului respectiv
	 */
	public void AdaugaInSir( char c, String codificare ) { 
		if ( this.simboluri.length == this.dim )  { 
			this.DubleazaDim();
		} 
			this.simboluri[this.dim] = new NodArbore(c, codificare);
			this.dim++;
		
	}
	

	
	/** Cauta in sir un caracter/simbol dupa un cod binar dat 
	 *  In cazul in care nu se gaseste va returna -1. 
	 *  In caz de succes returneaza codul ASCII. Functie folosita la 
	 *  decodificare.
	 * @param cod codul binar
	 * @return codul ASCII corespunzator codului binar 
	 */
	public int CautaInSir ( String cod ) { 
		for ( int i =0 ; i< this.dim ; i++) {
			if ( this.simboluri[i].getValoares().compareTo(cod) == 0) { 
				return (int)this.simboluri[i].getVal();
			}
		}
		return -1;
	}
	
	

	/**
	 *  Sorteaza crescator dupa probabilitate
	 *  - metoda bulelor 
	 */
	public void SortareProbabilitate ( ) {
		
		NodArbore aux = new NodArbore();
		for ( int i = 0; i < this.simboluri.length -1 ; i++ ) { 
			for ( int j = i+1 ; j< this.simboluri.length; j++ ) { 
				if (this.simboluri[i].getProb() > this.simboluri[j].getProb()) { 
					
					aux = this.simboluri[i];
					this.simboluri[i]=this.simboluri[j];
					this.simboluri[j]= aux;
					
				}
			}
		}
	}
	

	/**
	 * Sorteaza descrescator dupa probabilitate
	 * - metoda bulelor
	 */
	public void SortareProbabilitateDESC() { 
		NodArbore aux = new NodArbore();
		for ( int i = 0; i < this.simboluri.length -1 ; i++ ) { 
			for ( int j = i+1 ; j< this.simboluri.length; j++ ) { 
				if (this.simboluri[i].getProb() < this.simboluri[j].getProb()) { 
					
					aux = this.simboluri[i];
					this.simboluri[i]=this.simboluri[j];
					this.simboluri[j]= aux;
				}
			}
		}
		
	}
	
	/** Metoda verifica daca un caracter c, apartine unui text 
	 * Se foloseste in functia recursiva "Cautare" din Arbore. 
	 * Ea verifica daca un caracter se gaseste pe un subarbore
	 * 
	 * 
	 * @param text Campul valoares din NodArbore (contine toate nodurile 
	 * din subarbore sub forma de String)
	 * @param c caracterul cautat
	 * @return true/false daca apartine sau nu
	 */
	public static boolean Apartine( String text, char c ) {
		// 
		char[] text_c = new char[text.length()];
		text_c = text.toCharArray();
		for ( int i =0; i< text.length(); i++ ) { 
			if ( text_c[i]== c ) { 
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Functie de afisare ( a fost folosita pentru debugging ) 
	 * @return Stringul ce poate fi afisat cu System.out.println();
	 */
	public String toString () { 
		String rezultat= ""; 
		for ( int i=0; i<this.simboluri.length; i++ )   { 
		  rezultat += this.simboluri[i].getVal() + " ["; 
		  rezultat += this.simboluri[i].getProb() + "] ";
		}
		return rezultat;
	}
	
	/** Functie de afisare folosita pentru partea de decodificare 
	 * (de asemenea folosita pentru debugging). Pentru toate 
	 * caracterele afiseaza caracterul si in [] codul binar al 
	 * acestuia.
	 * 
	 * @return Stringul ce poate fi afisat cu System.out.println();
	 */
	public String AfisareDecod() { 
		
	
		String rezultat= ""; 
		for ( int i=0; i<this.dim; i++ )   { 
		  rezultat += this.simboluri[i].getVal() + " ["; 
		  rezultat += this.simboluri[i].getValoares() + "] ";
		}
		return rezultat;
		
	}
	
	

}
