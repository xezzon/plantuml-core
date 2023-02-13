package net.sourceforge.plantuml;

public class ErrorStatus {

	private boolean noData;
	private boolean hasErrors;
	private boolean hasOk;

	private ErrorStatus() {
		this.noData = true;
	}

	public static ErrorStatus init() {
		return new ErrorStatus();
	}

	// public synchronized void goNoData() {
	// this.noData = true;
	// }

	public synchronized void goWithError() {
		this.hasErrors = true;
		this.noData = false;
	}

	public synchronized void goOk() {
		this.hasOk = true;
		this.noData = false;
	}

	public synchronized boolean hasError() {
		return hasErrors;
	}

	public synchronized boolean isNoData() {
		return noData;
	}

	public int getExitCode() {
		if (isNoData()) {
			return 100;
		}
		if (hasErrors) {
			return 200;
		}
		return 0;
	}

}
