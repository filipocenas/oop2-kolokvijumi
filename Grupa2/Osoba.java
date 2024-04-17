package kolokvijum.Grupa2;
import java.time.LocalDate;

public final class Osoba {

	private final String imePrezime;
	private final LocalDate datum;
	private final String telefon;
	private final String tablice;

	public Osoba(String imePrezime, LocalDate datum, String telefon, String tablice) {
		if (imePrezime == null) {
			throw new IllegalArgumentException();
		}
		this.imePrezime = imePrezime;
		this.datum = datum;
		this.telefon = telefon;
		this.tablice = tablice;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getTablice() {
		return tablice;
	}

	@Override
	public String toString() {
		return String.format("%08X ", System.identityHashCode(this)) + imePrezime;
	}
}
