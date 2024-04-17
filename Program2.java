package kolokvijum;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
 * Prvi kolokvijum
 * ===============
 * 
 * Napisati Java aplikaciju koja pomocu tokova podataka i lambda izraza
 * obradjuje podatke o porudzbinama kolaca i drugih slatkisa.
 * 
 * Data je klasa Porudzbina kojom se prestavljaju porudzbine. Svaka porudzbina
 * ima svoj delovodni broj, porucioca, kao i listu porucenih slatkisa.
 * Delovodni broj se sastoji od kontrolnog dela, odvojenog sifrom godine i
 * meseca u obliku YYMM iza cega sledi redni broj porudzbine u tom mesecu. 
 * 
 * Data je i klasa Slatkis cije instance predstavljaju porucene slatkise, a
 * svaki slatkis ima svoj osnovni tip (kolac, mafin, rolat...), opcioni dodatak
 * u filu (jagode, cokolada, orasi...), kao i opcioni  preliv. Svi ovi podaci
 * su predstavljeni odgovarajucim nabrojivim tipovima.
 * 
 * Takodje je dat ovaj fajl sa kosturom glavnog metoda iz kojeg je potrebno
 * pozivati metode navedene u nastavku teksta zadatka.
 * 
 * Nije dozvoljeno menjati klase van ovog fajla. Sve pomocne metode i dodatne
 * klase smestiti u ovaj fajl i na kraju rada predati samo ovaj fajl.
 * 
 * Prvi deo (10 poena)
 * -------------------
 * 
 * Dat je tok stringova u vidu metoda Porudzbine.stringStream(). U njemu je
 * svaka porudzbina predstavljena jednim stringom. U prvom redu se nalaze
 * podaci o porudzbini a u svakom narednom redu po jedan poruceni slatkis.
 * 
 * Na pocetku linije se nalazi broj koji oznacava koliko komada datog slatkisa
 * je poruceno. Prilikom kreiranja porudzbine, potrebno je u listu dodati
 * ovoliko slatkisa date vrste.
 * 
 * Potom sledi tip slatkisa, pa fil i na kraju preliv u ljudski citljivom
 * formatu. Ako poruceni slatkis ne treba da ima fil ili preliv, na
 * odgovarajucem mestu u liniji se ne nalazi nista.
 * 
 * Za detalje o formatu pokrenuti program i pogledati kako izgleda svaki od
 * stringova.
 * 
 * Pretvoriti dati tok stringova u tok Porudzbina objekata i ispisati ih.
 * 
 * Obratiti paznju da jedan od radnika pise prvo preliv pa onda fil.
 * 
 * Uzeti ovo u obzir i korektno parsirati porudzbine i u slucaju gresaka.
 * 
 * Drugi deo (5 poena)
 * -------------------
 * 
 * Implementirati metod long porucenoRapsodija(), pozvati ga u glavnom programu
 * i ispisati rezultat.
 * 
 * Metod vraca ukupan broj porucenih slatkisa tipa Rapsodija.
 * 
 * Treci deo (5 poena)
 * -------------------
 * 
 * Implementirati metod Set<TipSlatkisa> skoroSviSlatkisi(String mesec),
 * pozvati ga u glavnom programu i ispisati rezultat.
 * 
 * Metod vraca skup svih tipova slatkisa porucenih zadatog meseca, osim
 * Rapsodija i bombica. Mesec se zadaje u istom obliku kao i u delovodnom broju.
 * 
 * Cetvrti deo (10 poena)
 * ----------------------
 * 
 * Za svakog klijenta ispisati koliko je tortica, koliko rolata i koliko
 * slatkisa sve ukupno porucio, u tabelarnom obliku na sledeci nacin:
 * 
 * Klijent                  | Tortica | Rolata  | Ukupno  |
 * -------------------------+---------+---------+---------+
 * 4 konja                  |     256 |     169 |    3043 |
 * Balkan                   |     136 |     257 |    2191 |
 * Barok Hotel & SPA        |     214 |     376 |    3705 |
 * ...                            ...       ...       ... 
 * Zdravko Pantin PR        |     214 |     249 |    2533 |
 * 
 */
public class Program2 {

	public static void main(String[] args) {

		// Za prvi deo zadatka koristiti ovaj tok stringova
		Porudzbine.stringStream(2000)
				.map(s -> s + "\n-----")
				.forEach(System.out::println);

//		// Za ostale delove zadatka koristiti ovaj tok podataka
		Porudzbine.porudzbineStream(2000)
				.forEach(System.out::println);
		System.out.println("Poruceno rapsodija -> " + porucenoRapsodija());
		Set<TipSlatkisa> set = skoroSviSlatkisi("01");
		System.out.println("Tipovi slatkisa prodati u datom mesecu: \n" + set.toString());
		Map<String, Long> data = Porudzbine.porudzbineStream(10)
				.collect(Collectors.groupingBy(Porudzbina::getKlijent, Collectors.counting()));
		System.out.println("\nKlijent       \t\t      | Tortica | Rolata | Ukupno |");
		System.out.println("------------------------------+---------+--------+--------+");
		data.entrySet().stream().map(d -> String.format("%29s | %25d | ", d.getKey(), d.getValue()))
		.forEach(System.out::println);
		

	}
	/*
	 * Prvi deo (10 poena) -------------------
	 * 
	 * Dat je tok stringova u vidu metoda Porudzbine.stringStream(). U njemu je
	 * svaka porudzbina predstavljena jednim stringom. U prvom redu se nalaze podaci
	 * o porudzbini a u svakom narednom redu po jedan poruceni slatkis.
	 * 
	 * Na pocetku linije se nalazi broj koji oznacava koliko komada datog slatkisa
	 * je poruceno. Prilikom kreiranja porudzbine, potrebno je u listu dodati
	 * ovoliko slatkisa date vrste.
	 * 
	 * Potom sledi tip slatkisa, pa fil i na kraju preliv u ljudski citljivom
	 * formatu. Ako poruceni slatkis ne treba da ima fil ili preliv, na
	 * odgovarajucem mestu u liniji se ne nalazi nista.
	 * 
	 * Za detalje o formatu pokrenuti program i pogledati kako izgleda svaki od
	 * stringova.
	 * 
	 * Pretvoriti dati tok stringova u tok Porudzbina objekata i ispisati ih.
	 * 
	 * Obratiti paznju da jedan od radnika pise prvo preliv pa onda fil.
	 * 
	 * Uzeti ovo u obzir i korektno parsirati porudzbine i u slucaju gresaka.
	 */
	private static Porudzbina parse(String file) {
		Porudzbina retPorudzbina = null;
		
		Pattern global = Pattern.compile("\r\n.*");
		Matcher matchGlobal = global.matcher(file);
		
		String narudzbina = null;
		
		if(matchGlobal.find()) {
			narudzbina = matchGlobal.group(1);
		}
		
		List<Slatkis> slatkisi = new ArrayList<Slatkis>();
		
		Pattern kolicina = Pattern.compile("([0-9])\\x");
		Matcher mKol = kolicina.matcher(narudzbina);
		int br = -1;
		if(mKol.find()) {
			br = Integer.parseInt(mKol.group(1));
		}
		Pattern tip = Pattern.compile("(a-zA-Z)?");
		Matcher mTip = tip.matcher(narudzbina);
		
		String [] slTip = {"baklava", "bufla", "bombica", "trougao", "cvet", "kiflica", "knedla",
							"kolac", "mafin", "minjon", "oblatna", "rapsodija", "rolat", "sapica", "tortica"};
		String tipSl;
		TipSlatkisa tsl = null;
		
		if(mTip.find()) {
			tipSl = mTip.group(1);
			for(String t: slTip) {
				if(tipSl.equals(t)) {
					System.out.println(t);
				}
			}
		}
		Pattern ord=Pattern.compile("(\r\n.*)");
		Matcher mORD=ord.matcher(narudzbina);
		String name;
		if(mORD.find()) {
			name=mORD.group(1);
		}
		
		//Slatkis novi=new Slatkis(tsl, null, null);
		return retPorudzbina;
	}
	
	/*
	 * Drugi deo (5 poena) -------------------
	 * 
	 * Implementirati metod long porucenoRapsodija(), pozvati ga u glavnom programu
	 * i ispisati rezultat.
	 * 
	 * Metod vraca ukupan broj porucenih slatkisa tipa Rapsodija.
	 */
	private static long porucenoRapsodija() {
		long br = Porudzbine.porudzbineStream(10)
				.flatMap(p -> p.getSlatkisi().stream())
				.filter(p -> p.getTip().equals(p.getTip().RAPSODIJA))
				.count();
		return br;
	}
	
//	Treci deo (5 poena)
//	 * -------------------
//	 * 
//	 * Implementirati metod Set<TipSlatkisa> skoroSviSlatkisi(String mesec),
//	 * pozvati ga u glavnom programu i ispisati rezultat.
//	 * 
//	 * Metod vraca skup svih tipova slatkisa porucenih zadatog meseca, osim
//	 * Rapsodija i bombica. Mesec se zadaje u istom obliku kao i u delovodnom broju.
	private static Set<TipSlatkisa> skoroSviSlatkisi(String mesec) {
		Set<TipSlatkisa> set = (Set<TipSlatkisa>) Porudzbine.porudzbineStream(10)
				.filter(p -> p.getBroj().contains(mesec))
				.flatMap(p -> p.getSlatkisi().stream())
				.filter(o -> !(o.getTip().equals(o.getTip().RAPSODIJA)) && !(o.getTip().equals(o.getTip().BOMBICA)))
				.map(Slatkis::getTip)
				.collect(Collectors.toSet());
		return set;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
