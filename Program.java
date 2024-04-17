package kolokvijum;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Prvi kolokvijum
 * ===============
 * 
 * Napisati Java aplikaciju koja pomocu tokova podataka i lambda izraza
 * obradjuje podatke o fiskalnim racunima izdatim od strane jedne mlekare.
 * 
 * Data je klasa Racun kojom se prestavljaju fiskalni racuni. Svaki racun ima
 * svoj redni broj, datum i vreme kada je izdat, listu stavki na racunu, kao i
 * koliko je gotovine uplaceno kada je racun placen.
 * 
 * Data je i klasa Stavka cije instance predstavljaju stavke racuna, a svaka
 * stavka se sastoji od imena proizvoda, kolicine tog proizvoda, cene po
 * jedinici mere i poreske stope (predstavljene nabrojivim tipom).
 * 
 * Prvi deo (5 poena)
 * ------------------
 * 
 * Dat je tok stringova u vidu metoda Racuni.stringStream(). U njemu je svaki
 * racun predstavljen jednim stringom u XML formatu. Za detalje o formatu
 * pokrenuti program i pogledati kako izgleda svaki od stringova.
 * 
 * Pretvoriti dati tok stringova u tok Racun objekata i ispisati ih.
 * 
 * Drugi deo (5 poena)
 * -------------------
 * 
 * Implementirati metod long ispodHiljadu(int mesec, int godina),
 * pozvati ga u glavnom programu i ispisati rezultat.
 * 
 * Metod vraca ukupan broj racuna, za zadati mesec zadate godine, za koje je
 * uplaceno strogo manje od hiljadu dinara.
 * 
 * Treci deo (5 poena)
 * -------------------
 * 
 * Implementirati metod List<Racun> sortiraniPoVisini(int dan, int mesec, int godina),
 * pozvati ga u glavnom programu i ispisati rezultat.
 * 
 * Metod vraca listu racuna izadtih zadatog dana, sortiranu rastuce po visini
 * racuna.
 * 
 * Cetvrti deo (5 poena)
 * ---------------------
 * 
 * Za svaki proizvod ispisati koliko je puta prodat (ukupan broj racuna na
 * kojima se nalazi taj proizvod), na sledeci nacin:
 * 
 *      Proizvod | Prodato puta
 * --------------+--------------
 *    Mleko 3,2% |         1245
 *    Mleko 2,8% |        12876
 *  Jogurt 1,5kg |         3762
 *              ...
 * 
 * Peti deo (5 poena)
 * ------------------
 * 
 * Za svaku vrstu tvrdog sira, ispisati koliko je kilograma prodato u svakom
 * mesecu 2019. godine, u tabelarnom obliku na sledeci nacin:
 * 
 * Sir         |  Januar | Februar |    Mart | ...
 * ------------+---------+---------+---------+-----
 * Bjuval RF   |  125.32 |  532.30 |    2.34 |
 * Gauda RF    |   12.43 |  125.44 | 1246.32 |
 * Edamer RF   |     ... |     ... |     ... |
 * Tilsiter RF |     ... |     ... |     ... |
 * Trapist RF  |     ... |     ... |     ... |
 * 
 */
public class Program {

	public static void main(String[] args) {

		Racuni.stringStream(1000)
				.forEach(System.out::println);

		Racuni.racuniStream(1000)
				.forEach(System.out::println);
		System.out.println(String.format("Ukupan broj racuna za zadati mesec i godinu je %d", ispodHiljadu(1, 2018)));
		List<Racun> lista = sortiraniPoVisini(25, 7, 2018);
		System.out.println("Lista izdatih racuna zadatog datuma:\n" + lista.toString());
//		Cetvrti deo (5 poena)
//		 * ---------------------
//		 * 
//		 * Za svaki proizvod ispisati koliko je puta prodat (ukupan broj racuna na
//		 * kojima se nalazi taj proizvod), na sledeci nacin:
//		 * 
//		 *      Proizvod | Prodato puta
//		 * --------------+--------------
//		 *    Mleko 3,2% |         1245
//		 *    Mleko 2,8% |        12876
//		 *  Jogurt 1,5kg |         3762
		ispisiProdajuPoProizvodu();
	}
	
//	Prvi deo (5 poena)
//	 * ------------------
//	 * 
//	 * Dat je tok stringova u vidu metoda Racuni.stringStream(). U njemu je svaki
//	 * racun predstavljen jednim stringom u XML formatu. Za detalje o formatu
//	 * pokrenuti program i pogledati kako izgleda svaki od stringova.
//	 * 
//	 * Pretvoriti dati tok stringova u tok Racun objekata i ispisati ih.
	
	
//	Drugi deo (5 poena)
//	 * -------------------
//	 * 
//	 * Implementirati metod long ispodHiljadu(int mesec, int godina),
//	 * pozvati ga u glavnom programu i ispisati rezultat.
//	 * Metod vraca ukupan broj racuna, za zadati mesec zadate godine, za koje je
//	 * uplaceno strogo manje od hiljadu dinara.
	private static long ispodHiljadu(int mesec, int godina) {
		long br = Racuni.racuniStream(1000)
				.filter(o -> o.getVreme().getMonth().getValue() == mesec)
				.filter(o -> o.getVreme().getYear() == godina)
				.count();
		return br;
	}
//	Treci deo (5 poena)
//	 * -------------------
//	 * 
//	 * Implementirati metod List<Racun> sortiraniPoVisini(int dan, int mesec, int godina),
//	 * pozvati ga u glavnom programu i ispisati rezultat.
//	 * 
//	 * Metod vraca listu racuna izadtih zadatog dana, sortiranu rastuce po visini
//	 * racuna.
	private static List<Racun> sortiraniPoVisini(int dan, int mesec, int godina) {
		List<Racun> retList = Racuni.racuniStream(1000)
				.filter(r -> r.getVreme().getDayOfMonth() == dan)
				.filter(r -> r.getVreme().getMonth().getValue() == mesec)
				.filter(r -> r.getVreme().getYear() == godina)
				.sorted(Comparator.comparing(Racun::getUplaceno))
				.toList();
		return retList;	
	}
	private static void ispisiProdajuPoProizvodu() {
	    // Koristimo tok za obradu svih računa, flatMap za dobivanje svih stavki iz svih računa
	    // i grupiranje po nazivu proizvoda, te prebrojavanje elemenata u grupama
	    Map<Object, Long> prodajaPoProizvodu = Racuni.racuniStream(1000)
	            .flatMap(racun -> racun.getStavke().stream())
	            .collect(Collectors.groupingBy(stavka -> stavka.getProizvod(), Collectors.counting()));

	    // Ispisujemo rezultate
	    System.out.println(" Proizvod       | Prodato puta ");
	    System.out.println("----------------+--------------");
	    prodajaPoProizvodu.forEach((nazivProizvoda, brojProdanih) ->
	            System.out.printf(" %-15s | %12d%n", nazivProizvoda, brojProdanih));
	}

	
}
