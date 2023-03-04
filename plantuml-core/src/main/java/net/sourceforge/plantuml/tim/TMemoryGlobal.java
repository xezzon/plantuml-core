// THIS FILE HAS BEEN GENERATED BY A PREPROCESSOR.
package net.sourceforge.plantuml.tim;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.sourceforge.plantuml.tim.expression.TValue;
import net.sourceforge.plantuml.utils.Log;

public class TMemoryGlobal extends ExecutionContexts implements TMemory {

	private final Map<String, TValue> globalVariables = new HashMap<String, TValue>();
	private final TrieImpl variables = new TrieImpl();

	public TValue getVariable(String varname) {
		return this.globalVariables.get(varname);
	}

	public void dumpDebug(String message) {
		Log.error("[MemGlobal] Start of memory_dump " + message);
		dumpMemoryInternal();
		Log.error("[MemGlobal] End of memory_dump");
	}

	void dumpMemoryInternal() {
		Log.error("[MemGlobal] Number of variable(s) : " + globalVariables.size());
		for (Entry<String, TValue> ent : new TreeMap<String, TValue>(globalVariables).entrySet()) {
			final String name = ent.getKey();
			final TValue value = ent.getValue();
			Log.error("[MemGlobal] " + name + " = " + value);
		}
	}

	public void putVariable(String varname, TValue value, TVariableScope scope) throws EaterException {
		Log.info("[MemGlobal] Setting " + varname);
		if (scope == TVariableScope.LOCAL) {
			throw EaterException.unlocated("Cannot use local variable here");
		}
		this.globalVariables.put(varname, value);
		this.variables.add(varname);
	}

	public void removeVariable(String varname) {
		this.globalVariables.remove(varname);
		this.variables.remove(varname);
	}

	public boolean isEmpty() {
		return globalVariables.isEmpty();
	}

	public Set<String> variablesNames() {
		return Collections.unmodifiableSet(globalVariables.keySet());
	}

	public Trie variablesNames3() {
		return variables;
	}

	public TMemory forkFromGlobal(Map<String, TValue> input) {
		return new TMemoryLocal(this, input);
	}

}
