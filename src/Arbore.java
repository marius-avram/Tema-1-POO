
public class Arbore {

	private NodArbore root; 
	
	
	
	/**
	 * Construieste arborele pe baza caruia se vor face codificarile
	 * @param q Coada de prioritate ce contine noduri ale arborelui, intial 
	 * frunze.
	 */
	public Arbore (Coada q) { 
		
		NodArbore n1 = null; 
		NodArbore n2 = null;
		NodArbore nou = new NodArbore();
		while (q.Lungime()>1) {
			
			// Scoatem primele doua noduri din coada 
			// de noduri
			n1 = q.Scoate();
			n2 = q.Scoate();
			
			// Creeam un nou nod pe care il introducem in 
			// coada de noduri.
			nou = new NodArbore(n1,n2);
			q.Adauga(nou);
		
		}
		
		// ultimul nod nou adaugat in lista este nodul root - radacina
		this.root= nou; 
	
	}
	
	/** Parcurgere Radacina-Stanga-Dreapta (RSD)
	 * - functie recursiva. 
	 * Metoda va cauta un anumit caracter in arbore si
	 * in functie de pasii urmati pentru a ajunge la acesta va 
	 * obtine un cod unic (in binar).
	 * @param n Nodul din arbore de la care va porni cautarea. Va fi nodul radacina
	 * @param c Caracterul care trebuie sa il caute.
	 * @return Codul binar asociat caracterului
	 */
	public String Cautare (NodArbore n, char c ) {
		
		String Cod = "";
		if  (n.getValoares()== Character.toString(c) ) { 
			return Cod;
		}
		if ( n.getStanga() != null) {  
			// verifica daca simbolul cautat se afla in subarborele stang
			if ( Sir.Apartine(n.getStanga().getValoares(), c) == true ) { 
				Cod = Cautare (n.getStanga(), c);
				Cod = "0" + Cod;
			
			}
		} 
		if ( n.getDreapta() != null) { 
			// verifica daca simbolul cautat se afla in subarborele drept
			if ( Sir.Apartine(n.getDreapta().getValoares(), c) == true) { 
			Cod = Cautare (n.getDreapta(),c);
			Cod = "1" + Cod;
			}
		} 
		return Cod;
	}
	
	
	/** Metoda primeste ca parametru o coada cu toate caracterele 
	 *  din text, fiecare caracter aflandu-se in coada doar o data.
	 *  Ea va determina codificarea binara pentru fiecare din ele. 
	 *  Codificarea va fi retinuta in campul valoares din Nodarbore
	 * @param q Coada cu toate caracterele din text
	 */
	public void CautareCoduri ( Coada q ) { 
		
		// 
		int lung = q.simboluri.length ; 
		String Cod; 
		for (int i=0; i< lung ; i++)  { 
			Cod = this.Cautare(this.root, q.simboluri[i].getVal());
			q.simboluri[i].setValoares(Cod);
		}	
	}
	
	
	
}
