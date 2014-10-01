package com.joel.GOL.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Screen extends Canvas {

	private static final long serialVersionUID = 1L;

	public JFrame frame;

	public int width, height;
	public static int HEIGHT = 720;
	public static int WIDTH = HEIGHT * 16 / 9;
	public static int SIZE = 6;
	private static int gen = 0;

	public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public static int[] pixelBuffer;

	public Screen() {
		this.width = WIDTH;
		this.height = HEIGHT;

		pixelBuffer = new int[WIDTH  * HEIGHT];

		setupJFrame();
	}

	public void clear() {
		for (int i = 0; i < pixelBuffer.length; i++) {
			pixelBuffer[i] = 0xFFFFFFFF;
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		clear();

		GOL.t.render();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = pixelBuffer[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Verdana", 0, 50));

		// g.drawString("Hello, World!", 100, 100);
		g.drawString("" + gen, 10, 100);

		g.dispose();
		bs.show();
		gen++;
	}

	public static void bufferPixel(int x, int y, boolean isAlive) {
		if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
			return;
		if (isAlive)
			pixelBuffer[x + y * WIDTH] = 0;
		if (!isAlive)
			pixelBuffer[x + y * WIDTH] = 0xFFFFFF;
	}

	public void setupJFrame() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}