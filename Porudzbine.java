package kolokvijum;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

final class Porudzbina {

	private final String broj;
	private final String klijent;
	private final List<Slatkis> slatkisi;

	public Porudzbina(String broj, String klijent, List<Slatkis> slatkisi) {
		this.broj = broj;
		this.klijent = klijent;
		this.slatkisi = Collections.unmodifiableList(slatkisi);
	}

	public String getBroj() {
		return broj;
	}

	public String getKlijent() {
		return klijent;
	}

	public List<Slatkis> getSlatkisi() {
		return slatkisi;
	}
}

final class Slatkis {

	private final TipSlatkisa tip;
	private final TipDodatka fil;
	private final TipDodatka preliv;

	public Slatkis(TipSlatkisa tip, TipDodatka fil, TipDodatka preliv) {
		this.tip = tip;
		this.fil = fil;
		this.preliv = preliv;
	}

	public TipSlatkisa getTip() {
		return tip;
	}

	public TipDodatka getFil() {
		return fil;
	}

	public TipDodatka getPreliv() {
		return preliv;
	}
}

enum TipSlatkisa {

	BAKLAVA   (20, 90, 5,  0),
	BOMBICA   (35, 35, 5,  5),
	BUFLA     (50, 10, 1,  0),
	CVET      (40, 15, 2,  1),
	KIFLICA   (50, 10, 1,  0),
	KNEDLA    (80, 10, 2,  2),
	KOLAC     (40, 20, 5,  0),
	MAFIN     (50, 25, 5,  0),
	MINJON    (10, 30, 5,  5),
	OBLATNA   (10, 40, 1,  0),
	RAPSODIJA (25, 25, 5, 15),
	ROLAT     (50, 20, 5,  5),
	SAPICA    (50, 10, 1,  0),
	TORTICA   (50, 20, 5,  5),
	TROUGAO   (40, 20, 2,  0);

	private final int[] sastojci;

	private TipSlatkisa(int brasno, int secer, int jaja, int mleko) {
		this.sastojci = new int[] { brasno, secer, jaja, mleko };
	}

	public int getPotrebnoBrasna() {
		return sastojci[0];
	}

	public int getPotrebnoSecera() {
		return sastojci[1];
	}

	public int getPotrebnoJaja() {
		return sastojci[2];
	}

	public int getPotrebnoMleka() {
		return sastojci[3];
	}
}

enum TipDodatka {

	BOROVNICA("borovnicama"),
	VISNJA("visnjama"),
	LIMUN("limunom"),
	NARANDZA("narandzom"),
	JAGODA("jagodama"),
	JABUKA("jabukama"),
	BRESKVA("breskvama"),
	BANANA("bananama"),
	KOKOS("kokosom"),
	KUPINA("kupinama"),
	MALINA("malinama"),
	RIBIZLA("ribizlama"),
	SLJIVA("sljivama"),
	KRUSKA("kruskama"),
	SUMSKO_VOCE("sumskim vocem"),
	PAPRIKA("ljutom paprikom"),
	ORAH("orasima"),
	LESNIK("lesnicima"),
	BADEM("bademima"),
	PISTACI("pistacima"),
	KIKIRIKI("kikirikijem"),
	MAK("makom"),
	ROGAC("rogacem"),
	SUVO_GROZDJE("suvim grozdjem"),
	SUVE_SLJIVE("suvim sljivama"),
	CRNA_COKOLADA("crnom cokoladom"),
	BELA_COKOLADA("belom cokoladom"),
	MLECNA_COKOLADA("mlecnom cokoladom"),
	KARAMELA("karamelom"),
	KREM("kremom"),
	BELI_KREM("belim kremom"),
	RAFAELO_KREM("rafaelo kremom"),
	VANILA("vanila kremom"),
	ZELE("zeleom"),
	PEKMEZ("pekmezom"),
	SLAG("slagom"),
	MED("medom"),
	PUTER("puterom"),
	JOGURT("jogurtom"),
	PUDING("pudingom"),
	RUM("rumom"),
	LIKER("likerom");

	private final String naziv;

	private TipDodatka(String naziv) {
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}
}

final class Porudzbine {

	private static final String[] KLIJENTI = new String[] {
			"hotel Visoki",
			"hotel 4 konja",
			"hotel Ostrvo snova",
			"hotel Hotel & Wellness Diorama",
			"hotel Proplanak ****",
			"hotel Barok Hotel & SPA",
			"hotel Duga ***",
			"hotel Balkan",
			"hotel Kraljevska laguna",
			"hotel Moskva",
			"restoran Retro",
			"restoran Podvodni zamak",
			"restoran Safran",
			"restoran Kula",
			"restoran Mala Atina",
			"restoran Stari ribar",
			"restoran Kod Jove",
			"bar Moj koktel",
			"kafanu Sumrak",
			"kafanu Predah",
			"firmu Mirko i sinovi d.o.o.",
			"firmu Fantastiko d.o.o.",
			"firmu DOO Restorantijum",
			"udruzenje Mladi gastronomi AD",
			"preduzetnika Zdravko Pantin PR",
	};

	public static final Stream<String> stringStream(int n) {
		return porudzbineStream(n).map(p -> toString(p));
	}

	public static final Stream<Porudzbina> porudzbineStream(int n) {
		Stanje stanje = new Stanje();
		return Stream.generate(() -> porudzbina(stanje)).limit(n);
	}

	private static class Stanje {

		public final Random random = new Random(0);

		private int brojac = 1;
		private LocalDateTime vreme = LocalDateTime.of(2020, 1, 1, 8, 0);

		public String broj() {
			int p = 240 - random.nextInt(12) * random.nextInt(12);
			LocalDateTime vreme = this.vreme.plusMinutes(p);
			if (p == 0 || vreme.getHour() > 18) {
				vreme = vreme.plusDays(1).withHour(8).plusMinutes(p);
			}
			if (vreme.getMonthValue() != this.vreme.getMonthValue()) {
				brojac = 1;
			}
			if (vreme.getDayOfWeek() == DayOfWeek.SUNDAY) {
				vreme = vreme.plusDays(1).withHour(8).plusMinutes(p);
			}
			this.vreme = vreme;
			return String.format("%05x-%02d%02d/%d",
					Math.abs(("brojac" + brojac).hashCode()) % 65536,
					vreme.getYear() % 100,
					vreme.getMonthValue(),
					brojac++
			);
		}
	}

	private static final int[] KOLICINE = new int[] {
			50, 40, 30, 20, 10, 5, 2, 1, 25, 15
	};

	private static final Comparator<Slatkis> KOMPARATOR = Comparator
			.comparing(Slatkis::getTip)
			.thenComparing(Slatkis::getFil, Comparator.nullsFirst(Comparator.naturalOrder()))
			.thenComparing(Slatkis::getPreliv, Comparator.nullsFirst(Comparator.naturalOrder()));

	private static final Porudzbina porudzbina(Stanje stanje) {
		String broj = stanje.broj();
		List<Slatkis> slatkisi = new ArrayList<>();
		int k = 1 + stanje.random.nextInt(3) * stanje.random.nextInt(3);
		for (int i = 0; i < k; i++) {
			TipSlatkisa tip = TipSlatkisa.values()[stanje.random.nextInt(TipSlatkisa.values().length)];
			TipDodatka fil = stanje.random.nextInt(10) == 0 ? null : TipDodatka.values()[stanje.random.nextInt(TipDodatka.values().length)];
			TipDodatka preliv = stanje.random.nextInt(10) == 0 ? null : TipDodatka.values()[stanje.random.nextInt(TipDodatka.values().length)];
			int kolicina = KOLICINE[stanje.random.nextInt(KOLICINE.length)];
			Slatkis slatkis = new Slatkis(tip, fil, preliv);
			for (int j = 0; j < kolicina; j++) {
				slatkisi.add(slatkis);
			}
		}
		slatkisi.sort(KOMPARATOR);
		String klijent = KLIJENTI[stanje.random.nextInt(KLIJENTI.length)];
		return new Porudzbina(broj, klijent, slatkisi);
	}

	private static final String toString(Porudzbina porudzbina) {
		return toString1(porudzbina);
	}

	private static final String toString1(Porudzbina porudzbina) {
		boolean poseban = porudzbina.getBroj().hashCode() % 10 == 0;
		StringBuilder b = new StringBuilder();
		b.append("Porudzbina broj ");
		b.append(porudzbina.getBroj());
		b.append(" za ");
		b.append(porudzbina.getKlijent());
		b.append(":");
		List<Slatkis> slatkisi = new ArrayList<>(porudzbina.getSlatkisi());
		slatkisi.add(null);
		int br = 1;
		Slatkis prosli = null;
		for (Slatkis slatkis : slatkisi) {
			if (slatkis != prosli) {
				if (prosli != null) {
					b.append('\n');
					b.append(br);
					b.append("x ");
					b.append(toString(prosli, poseban));
				}
				br = 1;
			} else {
				br++;
			}
			prosli = slatkis;
		}
		return b.toString();
	}

	public static final String toString(Slatkis slatkis, boolean poseban) {
		StringBuilder b = new StringBuilder();
		b.append(slatkis.getTip().name());
		if (slatkis.getFil() != null) {
			b.append(" sa ");
			b.append(slatkis.getFil().getNaziv());
		}
		if (slatkis.getPreliv() != null) {
			b.append(" preliven");
			if (slatkis.getTip().name().endsWith("A")) {
				b.append('a');
			} 
			b.append(" ");
			b.append(slatkis.getPreliv().getNaziv());
		}
		return ocr(b.toString().toLowerCase());
	}
	
	private static final String ocr(String string) {
		Random random = new Random(string.length());
		return string.codePoints()
				.map(c ->
						c == 'o' && random.nextInt(10) == 0 ? '0' :
						c == 'i' && random.nextInt(10) == 0 ? '1' :
						c == 'g' && random.nextInt(10) == 0 ? 'q' :
						c == 'u' && random.nextInt(10) == 0 ? 'v' :
						c == 'v' && random.nextInt(10) == 0 ? 'u' :
						c == 'n' && random.nextInt(10) == 0 ? 'm' :
						c == 'm' && random.nextInt(10) == 0 ? 'n' :
						c
				)
				.collect(
						StringBuilder::new,
						StringBuilder::appendCodePoint,
						StringBuilder::append
				)
				.toString();
	}
}
