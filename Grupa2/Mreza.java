package kolokvijum.Grupa2;
public enum Mreza {

	POZIVNI_060("60", "A1"),
	POZIVNI_061("61", "A1"),
	POZIVNI_062("62", "Yettel Srbija"),
	POZIVNI_063("63", "Yettel Srbija"),
	POZIVNI_064("64", "Telekom Srbija"),
	POZIVNI_065("65", "Telekom Srbija"),
	POZIVNI_066("66", "Telekom Srbija"),
	POZIVNI_067("67", "Globaltel"),
	POZIVNI_068("68", "A1"),
	POZIVNI_069("69", "Yettel Srbija"),
	POZIVNI_NEPOZNAT("???", "???");
	
	private final String ime;
	private final String pozivni;

	private Mreza(String pozivni, String ime) {
		this.ime = ime;
		this.pozivni = pozivni;
	}

	public String getIme() {
		return ime;
	}

	public String getPozivni() {
		return pozivni;
	}

	public static Mreza find(String pozivni) {
		for (Mreza m : values()) {
			if (m.pozivni.equals(pozivni)) {
				return m;
			}
		}
		return POZIVNI_NEPOZNAT;
	}
}
