package com.joel.GOL.main;

public class GOL {

	public static final int GEN = 1000;
	public static int passed = 0;
	private static long timer = 10L;

	public static Table t;

	public static boolean running = true;

	public static void main(String[] args) {
		Screen s = new Screen();
		t = new Table(Screen.WIDTH / Screen.SIZE, Screen.HEIGHT / Screen.SIZE, false);

		while (running) {
			while (passed <= GEN) {
				long begin = System.currentTimeMillis();
				t.update();
				passed++;
				while (System.currentTimeMillis() - timer < begin) {
					s.render();
				}
			}
			s.render();
		}
	}

}
