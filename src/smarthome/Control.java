package smarthome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class Control {
       private Process mProcess;

	private String mCommand;

	private String mParameterString;

	private String[] mParameter;

	private String mThumbnail;

	private String mIconBase64;
        
	private String mClientAction = "";

	private String mLogPath;

	private File mLogfile;
        
	public void onLoad() {
		if (mParameterString != null && mParameterString.length() > 0)
			mParameter = mParameterString.split(" ");
		if (mThumbnail != null && mThumbnail.length() > 0) {
			try {
				byte[] bytes = loadFile(new File(mThumbnail));
				mIconBase64 = Base64.getEncoder().encodeToString(bytes);
			} catch (IOException e) {
			
			}
		}
		if (mLogPath != null && mLogPath.length() > 0)
			mLogfile = new File(mLogPath);
	}
        
        private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			is.close();
			throw new IOException("Thumbnail image is too large");
		}
		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		is.close();
		if (offset < bytes.length)
			throw new IOException("Could not completely read file " + file.getName());

		return bytes;
	}
        
        
    public static void main(String[] args) {
        
    }
    
}
