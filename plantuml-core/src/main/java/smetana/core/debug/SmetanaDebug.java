// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.

package smetana.core.debug;

import java.util.LinkedHashMap;
import java.util.Map;

public final class SmetanaDebug {
	static private final Map<String, String> methods = new LinkedHashMap<String, String>();

	static public void LOG(String s) {

	}

	static public void ENTERING(String signature, String methodName) {
//		if (methods.containsKey(methodName) == false)
//			methods.put(methodName, methodName);
	}

	static public void LIST_METHODS() {
		int i = 0;
		for (String s : methods.keySet()) {
			System.err.println("i=" + i + " " + s);
			i++;
		}
	}

	static public void LEAVING(String signature, String methodName) {
	}

	public static void reset() {
	}

	public static void printMe() {
	}

}
