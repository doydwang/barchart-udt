package com.barchart.udt.lib;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.udt.util.HelperUtils;

public class MainRES {

	private static Logger log = LoggerFactory.getLogger(MainRES.class);

	public static void main(String[] args) {

		log.info("started");

		HelperUtils.logOsArch();
		HelperUtils.logClassPath();

		String name;
		URL url;

		name = "/lib/i386-Linux-g++/jni/libbarchart-udt4-1.0.2-SNAPSHOT.so";
		url = MainRES.class.getResource(name);
		log.info("url={}", url);

		name = "/META-INF/MANIFEST.MF";
		url = org.slf4j.Logger.class.getResource(name);
		log.info("url={}", url);

		name = "/META-INF/MANIFEST.MF";
		url = org.apache.log4j.Appender.class.getResource(name);
		log.info("url={}", url);

	}

}
