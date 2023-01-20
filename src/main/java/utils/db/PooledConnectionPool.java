package utils.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

public class PooledConnectionPool {
	private static long expTime = 6000; // 6 sec
	public static HashMap<PooledConnection, Long> available = new HashMap<PooledConnection, Long>();
	public static HashMap<PooledConnection, Long> inUse = new HashMap<PooledConnection, Long>();

	public synchronized static PooledConnection getConnection() throws SQLException {
		long now = System.currentTimeMillis();
		if (!available.isEmpty()) {
			for (Entry<PooledConnection, Long> entry : available.entrySet()) {
				if (now - entry.getValue() > expTime) {
					popElement(available);
				} else {
					PooledConnection pc = popElement(available, entry.getKey());
					push(inUse, pc, now);
					return pc;
				}
			}
		}
		return createPooledConnection(now);
	}

	private synchronized static PooledConnection createPooledConnection(long now) throws SQLException {
		PooledConnection pc = new PooledConnection();
		push(inUse, pc, now);
		return pc;
	}

	private synchronized static void push(HashMap<PooledConnection, Long> map, PooledConnection pc, long now) {
		map.put(pc, now);
	}

	public static void releaseConnection(PooledConnection pc) {
		cleanUp(pc);
		available.put(pc, System.currentTimeMillis());
		inUse.remove(pc);
	}

	private static PooledConnection popElement(HashMap<PooledConnection, Long> map) {
		Entry<PooledConnection, Long> entry = map.entrySet().iterator().next();
		PooledConnection key = entry.getKey();
		// Long value=entry.getValue();
		map.remove(entry.getKey());
		return key;
	}

	private static PooledConnection popElement(HashMap<PooledConnection, Long> map, PooledConnection key) {
		map.remove(key);
		return key;
	}

	public static void cleanUp(PooledConnection pc) {
		pc.setConn(null);
	}
}
