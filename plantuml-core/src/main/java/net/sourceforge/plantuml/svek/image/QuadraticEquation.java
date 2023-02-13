package net.sourceforge.plantuml.svek.image;

public class QuadraticEquation {

	private final double a;
	private final double b;
	private final double c;

	public QuadraticEquation(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double[] solve() {
		final double delta = b * b - 4 * a * c;
		final double x0 = (-b - Math.sqrt(delta)) / 2 / a;
		final double x1 = (-b + Math.sqrt(delta)) / 2 / a;
		return new double[] { x0, x1 };
	}

	public double getV(double x) {
		return a * x * x + b * x + c;
	}

}
