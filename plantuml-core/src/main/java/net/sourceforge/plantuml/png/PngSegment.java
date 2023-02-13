package net.sourceforge.plantuml.png;

class PngSegment {

	private final int totalSize;
	private final int nbPiece;

	public PngSegment(int totalSize, int nbPiece) {
		this.nbPiece = nbPiece;
		this.totalSize = totalSize;
	}

	public int getStart(int idx) {
		if (idx < 0 || idx > nbPiece - 1) {
			throw new IllegalArgumentException();
		}
		return (int) (1.0 * totalSize / nbPiece * idx);
	}

	public int getLen(int idx) {
		if (idx < 0 || idx > nbPiece - 1) {
			throw new IllegalArgumentException();
		}
		return (int) (1.0 * totalSize / nbPiece);
	}

}
