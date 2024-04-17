package kolokvijum.Grupa2;
import java.io.IOException;

/*
 * Prvi kolokvijum
 * ===============
 * 
 * Napisati Java aplikaciju koja pomocu tokova podataka i lambda izraza
 * obradjuje podatke o osobama opisanim u tekstualnom fajlu.
 * 
 * Data je klasa Osoba cijim se instancama prestavljaju osobe. Od bitnih
 * podataka svaka osoba ima ime i prezime, datum rodjenja, broj telefona i
 * registarske tablice automobila koji vozi. Samo su ime i prezime obavezni,
 * dok ostali podaci mogu biti nepoznati.
 * 
 * Takodje su dati i nabrojivi tipovi koji sadrze podatke o mobilnim mrezama i
 * registarskim regionima u Srbiji.
 * 
 * 
 * Prvi deo (10 poena)
 * -------------------
 * 
 * U fajlu se nalazi tekst sa opisom osoba. Celokupan sadrzaj fajla se moze
 * dobiti pozivom metoda Osobe::sadrzajFajla. Tekst se sastoji od vise pasusa,
 * a svaki pasus moze ali i ne mora opisivati neku osobu. Takodje, neki od
 * podataka o osobi mozda nisu navedeni u tekstu.
 * 
 * U fajlu se ime i prezime mogu prepoznati po velikim pocetnim slovima, takodje
 * sva imena se zavrsavaju na "a" a prezimena na "ic" ili "ski", svi datumi su
 * oblika "16. 2. 1947.", registarske tablice "BG 989-TS" sa 3 ili cetiri cifre
 * u sredini, dok se brojevi telefona mogu naci u medjunarodnom (+381691234567)
 * ili u lokalnom (069/123-4567) obliku.
 * 
 * Implementirati metod parse() koji pretvara dati tekst u listu Osoba objekata.
 * Pri pretvaranju konvertovati sve brojeve telefona u medjunarodni oblik.
 * 
 * Studenti koji ne rade ovaj deo zadatka mogu za ostale delove pozivati metod
 * Osobe::tokOsoba i dobiti tok u kojem se nalaze vec pretvorene osobe iz fajla.
 * 
 * 
 * Drugi deo (5 poena)
 * -------------------
 * 
 * Implementirati metod void telekomNoviSad() i pozvati ga u glavnom programu.
 * 
 * Metod ispisuje imena svih Novosadjana pretplatnika mreze Telekom Srbija.
 * 
 * 
 * Treci deo (5 poena)
 * -------------------
 * 
 * Implementirati metod Set<String> bezAuta(), pozvati ga u glavnom programu
 * i ispisati rezultat.
 * 
 * Metod vraca skup imena osoba koje imaju vise od 30 godina i nemaju auto.
 * 
 * 
 * Cetvrti deo (5 poena)
 * ---------------------
 * 
 * Implementirati metod void deoCetiri() koji ispisuje parove ljudi koji voze
 * isti automobil.
 * 
 * Pozvati metod u glavnom programu.
 * 
 * 
 * Peti deo (5 poena)
 * ------------------
 * 
 * Za svaku mobilnu mrezu, ispisati koliko osoba ima auto, koliko nema auto,
 * za koliko se zna datum rodjenja a za koliko se ne zna, u tabelarnom obliku
 * na sledeci nacin:
 * 
 *           Mreza |  ima auto : nema auto
 * ----------------+----------------------
 *             ??? |      2211 : 242      
 *              A1 |       559 : 62       
 *       Globaltel |       196 : 21       
 *  Telekom Srbija |       534 : 73       
 *   Yettel Srbija |       557 : 51       
 * 
 */
public class Grupa2 {

	public static void main(String[] args) throws IOException {

		String tekst = Osobe.sadrzajFajla(5000);
		System.out.println(tekst);

		Osobe.tokOsoba(5000)
				.forEach(System.out::println);
		telekomNoviSad();

	}
//	Drugi deo (5 poena)
//	 * -------------------
//	 * 
//	 * Implementirati metod void telekomNoviSad() i pozvati ga u glavnom programu.
//	 * 
//	 * Metod ispisuje imena svih Novosadjana pretplatnika mreze Telekom Srbija.
//	 * 
	private static void telekomNoviSad() {
		System.out.println("\n==== KORISNICI TELEKOMA IZ NOVOG SADA ====\n");
		Osobe.tokOsoba(5000).filter(o -> o.getTablice() != null && o.getTablice().substring(0, 2).contains("NS"))
							.filter(o -> o.getTelefon() != null)
							.filter(o -> o.getTelefon().substring(4, 6).contains(Mreza.POZIVNI_064.getPozivni()) 
										|| o.getTelefon().substring(4, 6).contains(Mreza.POZIVNI_065.getPozivni())
										|| o.getTelefon().substring(4, 6).contains(Mreza.POZIVNI_066.getPozivni()))
							.filter(o -> o.getTelefon().substring(1, 3).contains(Mreza.POZIVNI_064.getPozivni()) 
									|| o.getTelefon().substring(1, 3).contains(Mreza.POZIVNI_065.getPozivni())
									|| o.getTelefon().substring(1, 3).contains(Mreza.POZIVNI_066.getPozivni()))
							.forEach(System.out::println);
	}
	
	
	
	
	
}
