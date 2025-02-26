package br.com.iptv.download;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JProgressBar;

public class Download {

    public String downloadFile(String link, String arquivo) throws IOException {

    	try {
	    	@SuppressWarnings("deprecation")
			URL url = new URL(link);
	        File file = new File(arquivo);
	        FileUtils.copyURLToFile(url, file);
	        return "Download efetuado com sucesso";
    	} catch (IOException ex) {
    		return ex.toString();
    	}

    }

}