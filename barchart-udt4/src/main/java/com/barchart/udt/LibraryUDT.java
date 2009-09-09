/**
 * =================================================================================
 *
 * BSD LICENCE (http://en.wikipedia.org/wiki/BSD_licenses)
 *
 * ARTIFACT='barchart-udt4'.VERSION='1.0.0-SNAPSHOT'.TIMESTAMP='2009-09-09_16-17-24'
 *
 * Copyright (C) 2009, Barchart, Inc. (http://www.barchart.com/)
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *     * Neither the name of the Barchart, Inc. nor the names of its contributors
 *     may be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Developers: Andrei Pozolotin;
 *
 * =================================================================================
 */
package com.barchart.udt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CONTRACT:
 * 
 * 1) expecting to find native libraries under these names in the root of class
 * path or jar file which contains this class
 * 
 * 2) first name array element is extracted, copied AND loaded
 * 
 * 3) remaining array elements are extracted and copied ONLY
 */
public enum LibraryUDT {

	UNKNOWN(new String[] { "UNKNOWN" }), //

	WINDOWS_32(new String[] { "SocketUDT-windows-x86-32.dll",
			"LICENCE_BARCHART.txt" }), // 

	WINDOWS_64(new String[] { "SocketUDT-windows-x86-64.dll",
			"LICENCE_BARCHART.txt" }), //

	LINUX_32(new String[] { "libSocketUDT-linux-x86-32.so",
			"LICENCE_BARCHART.txt" }), // 

	LINUX_64(new String[] { "libSocketUDT-linux-x86-64.so",
			"LICENCE_BARCHART.txt" }), //

	;

	public final String[] fileNameArray;

	private LibraryUDT(String[] fileNameArray) {
		this.fileNameArray = fileNameArray;
	}

	private final static Logger log = LoggerFactory.getLogger(LibraryUDT.class);

	public final static String OS_NAME = System.getProperty("os.name")
			.toLowerCase();

	public final static String OS_ARCH = System.getProperty("os.arch")
			.toLowerCase();

	public static LibraryUDT detect() {
		if (OS_NAME.contains("windows")) {
			if (OS_ARCH.contains("x86")) {
				LibraryUDT library = WINDOWS_32;
				log.debug("detected: library={}", library);
				return library;
			}
			if (OS_ARCH.contains("amd64")) {
				LibraryUDT library = WINDOWS_64;
				log.debug("detected: library={}", library);
				return library;
			}
		}
		if (OS_NAME.contains("linux")) {
			if (OS_ARCH.contains("i386") || OS_ARCH.contains("i586")
					|| OS_ARCH.contains("i686")) {
				LibraryUDT library = LINUX_32;
				log.debug("detected: library={}", library);
				return library;
			}
			if (OS_ARCH.contains("amd64") || OS_ARCH.contains("x86_64")) {
				LibraryUDT library = LINUX_64;
				log.debug("detected: library={}", library);
				return library;
			}
		}
		log.error("unsupported OS_NAME={} OS_ARCH={}", OS_NAME, OS_ARCH);
		return UNKNOWN;
	}

	public final static String DEFAULT_FOLDER_NAME = "./lib";

	/**
	 * can specify optional libraries unpack location folder
	 */
	public static void load(String folderName) throws Exception {

		if (folderName == null || folderName.length() == 0) {
			folderName = DEFAULT_FOLDER_NAME;
			log.warn("using default folderName={}", folderName);
		}

		//

		File targetFolder = new File(folderName);

		if (targetFolder.exists()) {
			if (targetFolder.isDirectory()) {
				log.debug("found folder={}", targetFolder);
			} else {
				log.error("not a directory; folder={}", targetFolder);
				throw new IllegalArgumentException(
						"destination exists, but as file and not a folder");
			}
		} else {
			targetFolder.mkdirs();
			log.debug("made folder={}", targetFolder);
		}

		//

		LibraryUDT library = detect();

		if (library == UNKNOWN) {
			throw new UnsupportedOperationException(
					"this platform is not supported");
		}

		if (library.fileNameArray.length == 0) {
			log
					.error("invalid library file name array for library={}",
							library);
			throw new IllegalArgumentException("invalid name array");
		}

		// extract all

		for (String fileName : library.fileNameArray) {

			log.debug("using: targetFolder={} fileName={}", //
					targetFolder, fileName);

			extract(targetFolder, fileName);

		}

		// load first only

		String libraryName = library.fileNameArray[0];

		String absolutePath = //
		targetFolder.getAbsolutePath() + File.separator + libraryName;

		try {
			System.load(absolutePath);
		} catch (Exception e) {
			log.error("load failed : path={} message={}", //
					absolutePath, e.getMessage());
			throw new UnsupportedOperationException(
					"native library load failed");
		}

	}

	private final static int EOF = -1;

	// note: expecting resources in the the root of class path / jar file
	private static void extract(File folder, String fileName) throws Exception {

		ClassLoader classLoader = LibraryUDT.class.getClassLoader();

		if (classLoader == null) {
			log.error("resource classLoader not available: {}", fileName);
			throw new IllegalArgumentException("resource not found");
		}

		// no root "/" prefix needed for this call
		URL urlIN = classLoader.getResource(fileName);

		if (urlIN == null) {
			log.error("resource url not found: {}", fileName);
			throw new IllegalArgumentException("resource not found");
		}

		log.debug("urlIN={} ", urlIN);

		URLConnection connIN = urlIN.openConnection();

		if (connIN == null) {
			log.error("resource connection not available: {}", fileName);
			throw new IllegalArgumentException("resource not found");
		}

		File fileOUT = new File(folder, fileName);
		log.debug("fileOUT={} ", fileOUT.getAbsolutePath());

		if (isSameFile(connIN, fileOUT)) {
			log.debug("already extracted");
			return;
		} else {
			log.debug("making new destination resource for extraction");
			fileOUT.delete();
			fileOUT.createNewFile();
			// continue
		}

		final long timeStamp = timeStamp(connIN);

		InputStream streamIN = new BufferedInputStream(//
				classLoader.getResourceAsStream(fileName));

		OutputStream streamOUT = new BufferedOutputStream(//
				new FileOutputStream(fileOUT));

		byte[] array = new byte[64 * 1024];

		int readCount = 0;

		while ((readCount = streamIN.read(array)) != EOF) {
			streamOUT.write(array, 0, readCount);
		}

		streamIN.close();

		streamOUT.close();

		// synchronize target time stamp with source to avoid repeated copy
		fileOUT.setLastModified(timeStamp);

		log.debug("resource extracted OK");

	}

	private static long timeStamp(URLConnection connIN) {
		return connIN.getLastModified();
	}

	private static boolean isSameFile(URLConnection connIN, File fileOUT)
			throws Exception {

		long stampIN = connIN.getLastModified();
		long lengthIN = connIN.getContentLength();

		long stampOut = fileOUT.lastModified();
		long lengthOut = fileOUT.length();

		return lengthIN == lengthOut && stampIN == stampOut;

	}

}
