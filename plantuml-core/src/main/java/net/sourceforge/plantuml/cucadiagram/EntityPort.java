package net.sourceforge.plantuml.cucadiagram;

import net.sourceforge.plantuml.svek.Ports;

public class EntityPort {

	private final String entityUid;
	private final String portId;

	private EntityPort(String entityUid, String portId) {
		this.entityUid = entityUid;
		this.portId = portId;
	}

	public static EntityPort create(String entityUid, String portName) {
		return new EntityPort(entityUid, portName == null ? null : Ports.encodePortNameToId(portName));
	}

	public static EntityPort forPort(String entityUid) {
		return new EntityPort(entityUid, "P");
	}

	public String getFullString() {
		if (portId != null)
			return entityUid + ":" + portId;

		return entityUid;
	}

	private boolean isShielded() {
		return entityUid.endsWith(":h");
	}

	public String getPrefix() {
		if (isShielded())
			return entityUid.substring(0, entityUid.length() - 2);

		return entityUid;
	}

	public boolean startsWith(String centerId) {
		return entityUid.startsWith(centerId);
	}

	public boolean equalsId(EntityPort other) {
		return this.entityUid.equals(other.entityUid);
	}

}
