package com.barchart.udt.util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLOAD {

	static final Logger log = LoggerFactory.getLogger(TestLOAD.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFileConnection() throws Exception {

		String curDir = System.getProperty("user.dir");
		log.info("curDir: {}", curDir);

		String path = "src/test/resources/test-connection.txt";

		File file = new File(path);
		log.info("exists : {}", file.exists());

		log.info("getAbsolutePath : {}", file.getAbsolutePath());

		URLConnection conn = LOAD.fileConnection(file);

		assertTrue(conn.getLastModified() > 0);
		log.info("getLastModified : {}", conn.getLastModified());

		assertTrue(conn.getContentLength() > 0);
		log.info("getContentLength : {}", conn.getContentLength());

	}

	@Test
	public void testExtractResource() throws Exception {

		log.info("java.class.path = {}", System.getProperty("java.class.path"));

		// path inside jar, relative to the root of java.class.path
		String sourcePath = "./lib/bin/test-resource.txt";

		log.info("user.dir = {}", System.getProperty("user.dir"));

		// path outside of jar, in file system, relative to user.dir
		String targetPath = "./lib/bin/test-resource-extracted.txt";

		File targetFile = new File(targetPath);

		targetFile.delete();

		LOAD.extractResource(sourcePath, targetPath);

		assertTrue(targetFile.exists());

		URL sourceURL = TestLOAD.class.getClassLoader().getResource(sourcePath);

		URL targetURL = targetFile.toURI().toURL();

		URLConnection sourceCONN = sourceURL.openConnection();

		URLConnection targetCONN = targetURL.openConnection();

		assertEquals(sourceCONN.getContentLength(),
				targetCONN.getContentLength());
		assertEquals(sourceCONN.getLastModified(), targetCONN.getLastModified());

		targetFile.delete();

		assertEquals(0, targetCONN.getContentLength());
		assertEquals(0, targetCONN.getLastModified());

	}
}
