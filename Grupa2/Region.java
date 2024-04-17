package kolokvijum.Grupa2;
public enum Region {

	ALEKSINAC("Aleksinac", "AL"),
	ARANDJELOVAC("Aranđelovac", "AR"),
	ALEKSANDROVAC("Aleksandrovac", "AC"),
	BAJINA_BASXTA("Bajina Bašta", "BB"),
	BEOGRAD("Beograd", "BG"),
	BOR("Bor", "BO"),
	BACXKA_PALANKA("Bačka Palanka", "BP"),
	BACXKA_TOPOLA("Bačka Topola", "BT"),
	BOGATIĆ("Bogatić", "BĆ"),
	BUJANOVAC("Bujanovac", "BU"),
	BECXEJ("Bečej", "BČ"),
	VALJEVO("Valjevo", "VA"),
	VRNJACXKA_BANJA("Vrnjačka Banja", "VB"),
	VLASOTINCE("Vlasotince", "VL"),
	VELIKA_PLANA("Velika Plana", "VP"),
	VRANJE("Vranje", "VR"),
	VRBAS("Vrbas", "VS"),
	VRSXAC("Vršac", "VŠ"),
	GNJILANE("Gnjilane", "GL"),
	GORNJI_MILANOVAC("Gornji Milanovac", "GM"),
	DESPOTOVAC("Despotovac", "DE"),
	DJAKOVICA("Đakovica", "ĐA"),
	ZAJECXAR("Zaječar", "ZA"),
	ZRENJANIN("Zrenjanin", "ZR"),
	INDJIJA("Inđija", "IN"),
	IVANJICA("Ivanjica", "IC"),
	JAGODINA("Jagodina", "JA"),
	KANJIZXA("Kanjiža", "KA"),
	KRALJEVO("Kraljevo", "KV"),
	KRAGUJEVAC("Kragujevac", "KG"),
	KNJAZXEVAC("Knjaževac", "KŽ"),
	KIKINDA("Kikinda", "KI"),
	KLADOVO("Kladovo", "KL"),
	KOSOVSKA_MITROVICA("Kosovska Mitrovica", "KM"),
	KOVIN("Kovin", "KO"),
	KOCELJEVA("Koceljeva", "KC"),
	KRUSXEVAC("Kruševac", "KŠ"),
	LEBANE("Lebane", "LB"),
	LESKOVAC("Leskovac", "LE"),
	LOZNICA("Loznica", "LO"),
	LUCXANI("Lučani", "LU"),
	NOVA_VAROSX("Nova Varoš", "NV"),
	NEGOTIN("Negotin", "NG"),
	NISX("Niš", "NI"),
	NOVI_PAZAR("Novi Pazar", "NP"),
	NOVI_SAD("Novi Sad", "NS"),
	PANCXEVO("Pančevo", "PA"),
	PRIBOJ("Priboj", "PB"),
	PECY("Peć", "PE"),
	POZXEGA("Požega", "PŽ"),
	PRIZREN("Prizren", "PZ"),
	PIROT("Pirot", "PI"),
	PROKUPLJE("Prokuplje", "PK"),
	PARACYIN("Paraćin", "PN"),
	POZXAREVAC("Požarevac", "PO"),
	PRIJEPOLJE("Prijepolje", "PP"),
	PRISXTINA("Priština", "PR"),
	PETROVAC("Petrovac", "PT"),
	RASXKA("Raška", "RA"),
	RUMA("Ruma", "RU"),
	SENTA("Senta", "SA"),
	SURDULICA("Surdulica", "SC"),
	SVIJALNAC("Svijalnac", "SV"),
	SMEDEREVO("Smederevo", "SD"),
	SJENICA("Sjenica", "SJ"),
	SREMSKA_MITROVICA("Sremska Mitrovica", "SM"),
	SOMBOR("Sombor", "SO"),
	SMEDEREVSKA_PALANKA("Smederevska Palanka", "SP"),
	STARA_PAZOVA("Stara Pazova", "ST"),
	SUBOTICA("Subotica", "SU"),
	TOPOLA("Topola", "TO"),
	TRSTENIK("Trstenik", "TS"),
	TUTIN("Tutin", "TT"),
	CYUPRIJA("Ćuprija", "ĆU"),
	UB("Ub", "UB"),
	UZXICE("Užice", "UE"),
	UROSXEVAC("Uroševac", "UR"),
	CXACXAK("Čačak", "ČA"),
	SXABAC("Šabac", "ŠA"),
	SXID("Šid", "ŠI"),
	NEPOZNAT("???", "--");

	private final String ime;
	private final String oznaka;

	private Region(String ime, String oznaka) {
		this.ime = ime;
		this.oznaka = oznaka;
	}

	public String getIme() {
		return ime;
	}

	public String getOznaka() {
		return oznaka;
	}

	public static Region find(String oznaka) {
		for (Region r : values()) {
			if (r.oznaka.equalsIgnoreCase(oznaka)) {
				return r;
			}
		}
		return NEPOZNAT;
	}
}
