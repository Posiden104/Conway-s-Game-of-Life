package com.joel.GOL.main;

public class Cell {

	private boolean alive;
	private int r, c;
	private int[] dir = { -1, 0, 1 };

	public Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public boolean update() {
		int neighbors = 0;
		for (int i = 0; i < dir.length; i++) {
			for (int j = 0; j < dir.length; j++) {
				if ((r == 0 && dir[i] < 0) || (r == GOL.t.board.length - 1 && dir[i] > 0)) { // row
					continue;
				} else if ((c == 0 && dir[j] < 0) || (c == GOL.t.board[i].length - 1 && dir[j] > 0)) { // column
					continue;
				} else if (dir[i] == 0 && dir[j] == 0) { // skip self
															// examination
					continue;
				} else {
					if (GOL.t.board[r + dir[i]][c + dir[j]].isAlive()) {
						neighbors++;
					}
				}

			}
		}
		return resolve(neighbors);
	}

	@SuppressWarnings("null")
	public boolean resolve(int neighbors) {
		if (neighbors == 0 || neighbors >= 4 || !isAlive() && neighbors == 2 || neighbors == 1) {
			return false;
		} else if (neighbors == 3 || isAlive() && neighbors == 3 || isAlive() && neighbors == 2) {
			return true;
		}
		System.out.println(isAlive());
		System.out.println(neighbors);
		return (Boolean) null;
	}

	public void render() {
		for (int y = 0; y < Screen.SIZE; y++) {
			for (int x = 0; x < Screen.SIZE; x++) {
				Screen.bufferPixel(y + c * Screen.SIZE, x + r * Screen.SIZE, isAlive());
			}
		}
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}
}
