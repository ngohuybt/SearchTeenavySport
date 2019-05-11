package searchTeenavySport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CSVUtils {
	private static final Log log = LogFactory.getLog(CSVUtils.class);
	private static final char DEFAULT_SEPARATOR = ',';

	public static void writeLine(Writer w, List<String> values) throws IOException {
		writeLine(w, values, DEFAULT_SEPARATOR, ' ');
	}

	public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
		writeLine(w, values, separators, ' ');
	}

	// https://tools.ietf.org/html/rfc4180
	private static String followCVSformat(String value) {

		String result = value;
		if (result.contains("\"")) {
			result = result.replace("\"", "\"\"");
		}
		return result;

	}

	public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

		boolean first = true;

		// default customQuote is empty

		if (separators == ' ') {
			separators = DEFAULT_SEPARATOR;
		}

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(separators);
			}
			if (customQuote == ' ') {
				if (value==null) {
					sb.append(followCVSformat(""));
				} else {
					sb.append(followCVSformat(value));
				}
			} else {
				if (value==null) {
					sb.append(customQuote).append(followCVSformat("")).append(customQuote);
				} else {
					sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
				}
			}

			first = false;
		}
		sb.append("\n");
		w.append(sb.toString());

	}

}