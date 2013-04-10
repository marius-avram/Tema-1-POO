import java.util.Scanner;

public class Main {
	
	/**
	 * Programul principal. Pentru mai multe informatii urmariti comentariile 
	 * din codul sursa sau cele din README.
	 * @param args argumentul dat din consola. Poate fi c sau d, pentru 
	 * codificare sau decodificare.
	 */
	public static void main ( String[] args )  {
		String text="" , text_linie ;
		String separator = System.getProperty("line.separator");
		// Intrebam sistem care este sepratorul de linie pentru ca 
		// difera intre Unix si Windows
		boolean ok = true ;
		Scanner citire = new Scanner(System.in);
		
		if ( args[0].charAt(0) == 'c' ) { 
			// Sectiunea de codificare 
			
			// Citim textul de la tastatura 
			 while ( ok == true ) { 
				 text_linie= citire.nextLine() ;
				 if ( text_linie.compareTo(".") == 0  ) { 
					 ok = false ;
				 } else { 
					 if (text.compareTo("") == 0 )  { 
						text += text_linie;
					 } else { 
						text += separator + text_linie; 
					 }
					 
				 }
			 } // terminare citire 
			
			
			// Creeam o coada ce contine simbolurile din text. 
			// Fiecare simbol se regaseste o singura data in coada. 
			// Creeam si o copie a cozii pentru ca prima va suferi 
			// transformari (operatii de scoatere) atunci cand vom 
			// construi arborele.
			
			// Coada este deja ordonata crescator atunci cand o creeam 
			Coada v = new Coada (text);
			Coada v_copy = new Coada(text) ; 
			
			
			// Construim Arborele
			Arbore arb = new Arbore(v);
			
			// Cautam codul corespondent fiecarui simbol/caracter 
			// din coada de caractere. Nu e nevoie sa le salvam 
			// nicaieri. Acestea vor fi salvate de catre metoda 
			// in campul valoares  din obiectul v_copy. 
			arb.CautareCoduri(v_copy);
			
			// Ordonam Coada descrescator pentru ca simbolurile cu 
			// frecventa mai mare sa fie la inceput si pentru a fi 
			// gasite mai usor. 
			v_copy.SortareProbabilitateDESC();
			
			// Afisam Codificarea pentru fiecare simbol. Simbolurile/ 
			// /caracterele sunt reprezentate in cod ASCII
			System.out.print(v_copy.CodificareAscii());
			
			System.out.println(".");
			
			// Codificare finala a textului
			System.out.print(v_copy.Codificare(text));
		} 
		else if ( args[0].charAt(0) == 'd' ) {
			
			int ascii; 
			String cod; 
			Sir decod = new Sir();
			ok = true;
			
			// Citire codificari pentru fiecare caracter
			 while ( ok == true ) { 
				 
				 text_linie = citire.nextLine();
				 if ( text_linie.compareTo(".") == 0  ) { 
					 ok = false ;
				 } else { 
					 int pos = text_linie.indexOf(' '); 
					 ascii = Integer.parseInt(text_linie.substring(0, pos)); 
					 cod = text_linie.substring(pos+1);
					 decod.AdaugaInSir((char)ascii, cod);
					 
				 }
			 } // terminare citire codificari
			 
			 // citim si secventa codificata 
			 text = citire.nextLine();
			 // facem decodificarea si la fiecare pas afisam 
			 // caracterul corespunzator 
			 String secv = "";
			 for ( int i = 0 ; i < text.length(); i++ )  { 
				 secv += text.toCharArray()[i]; 
				 ascii = decod.CautaInSir(secv);
				 if ( ascii != -1 ) { 
					 System.out.print((char)ascii); 
					 secv = "";
				 }
			 }
			 System.out.println();
			 System.out.print( ".");
			
		} 
		else { 
			System.out.println("Argumentul introdus nu este valid");
		}
	}
}
