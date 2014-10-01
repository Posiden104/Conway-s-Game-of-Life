package com.joel.GOL.main;

import java.util.Random;

public class Table {

	public Cell[][] board;
	public Cell[][] nextBoard;

	public int r, c;
	public int size = 10;

	public Table(int r, int c, boolean random) {
		this.r = r;
		this.c = c;
		this.board = new Cell[r][c];
		this.nextBoard = new Cell[r][c];
		init(board);
		init(nextBoard);
		if (random) {
			random();
		} else {
			first();
		}
	}

	public Table(int r, int c, int seed) {
		this.r = r;
		this.c = c;
		this.board = new Cell[r][c];
		this.nextBoard = new Cell[r][c];
		init(board);
		init(nextBoard);
		random(seed);
	}

	public void update() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				nextBoard[i][j].setAlive(board[i][j].update());
			}
		}
		nextGen();
	}

	public void nextGen() {
		board = nextBoard;
		nextBoard = new Cell[r][c];
		init(nextBoard);
	}

	public void init(Cell[][] b) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				b[r][c] = new Cell(r, c);
			}
		}
	}

	public void random() {
		Random ran = new Random();
		long seed = ran.nextLong();
		ran.setSeed(seed);
		System.out.println("This seed is: " + seed);
		for (int r = board.length / 2 - size; r < board.length / 2 + size; r++) {
			for (int c = board[r].length / 2 - size; c < board[r].length / 2 + size; c++) {
				board[r][c].setAlive(ran.nextBoolean());
			}
		}
	}

	public void random(int seed) {
		Random ran = new Random(seed);
		System.out.println("This seed is: " + seed);
		for (int r = board.length / 2 - size; r < board.length / 2 + size; r++) {
			for (int c = board[r].length / 2 - size; c < board[r].length / 2 + size; c++) {
				board[r][c].setAlive(ran.nextBoolean());
			}
		}
	}

	public void first() {
		board[1][25].setAlive(true);

		board[2][23].setAlive(true);
		board[2][25].setAlive(true);

		board[3][13].setAlive(true);
		board[3][14].setAlive(true);
		board[3][21].setAlive(true);
		board[3][22].setAlive(true);
		board[3][35].setAlive(true);
		board[3][36].setAlive(true);

		board[4][12].setAlive(true);
		board[4][16].setAlive(true);
		board[4][21].setAlive(true);
		board[4][22].setAlive(true);
		board[4][35].setAlive(true);
		board[4][36].setAlive(true);

		board[5][1].setAlive(true);
		board[5][2].setAlive(true);
		board[5][11].setAlive(true);
		board[5][17].setAlive(true);
		board[5][21].setAlive(true);
		board[5][22].setAlive(true);

		board[6][1].setAlive(true);
		board[6][2].setAlive(true);
		board[6][11].setAlive(true);
		board[6][15].setAlive(true);
		board[6][17].setAlive(true);
		board[6][18].setAlive(true);
		board[6][23].setAlive(true);
		board[6][25].setAlive(true);

		board[7][11].setAlive(true);
		board[7][17].setAlive(true);
		board[7][25].setAlive(true);

		board[8][12].setAlive(true);
		board[8][16].setAlive(true);

		board[9][13].setAlive(true);
		board[9][14].setAlive(true);
	}

	public void render() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].render();
			}
		}
	}
}
