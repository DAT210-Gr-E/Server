package interfaces;

import java.io.IOException;
import java.io.InputStreamReader;

public interface IPicCountParser {
	public int parse(InputStreamReader reader) throws IOException;
}
