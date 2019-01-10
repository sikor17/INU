package application;

public enum MessageBoxResult {
	Abort("Przerwij"), Retry("Pon�w"), Ignore("Ignoruj"), OK("OK"), Cancel("Anuluj"), Yes("Tak"), No("Nie");

	private String[] text = new String[3];
	private int count;

	MessageBoxResult(String... msg) {
		for (int i = 0; i < msg.length; ++i)
			text[i] = msg[i];
		count = msg.length;
	}
	
	@Override
	public String toString() {
		return text[0];
	}
	
	public String getText(int i) {
		return text[i];
	}
	
	public int getCount() {
		return count;
	}
}
