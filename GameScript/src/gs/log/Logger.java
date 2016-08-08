package gs.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Logger {
	
	private final SimpleDateFormat sdf;
	private final LinkedList<String> logs = new LinkedList<>();
	private final boolean logToStd;
	
	public Logger() {
		this(false);
	}

	public Logger(boolean logToStd) {
		this.logToStd = logToStd;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public void log(String sender, String message) {
		String m = sdf.format(new Date()) + " - " + sender + " - " + message; 
		logs.add(m);
		if (logToStd) {
			System.out.println(m);
		}
	}
}
