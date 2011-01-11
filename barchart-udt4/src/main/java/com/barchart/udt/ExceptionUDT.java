/**
 * =================================================================================
 *
 * BSD LICENCE (http://en.wikipedia.org/wiki/BSD_licenses)
 *
 * ARTIFACT='barchart-udt4';VERSION='1.0.2-SNAPSHOT';TIMESTAMP='2011-01-11_09-30-59';
 *
 * Copyright (C) 2009-2011, Barchart, Inc. (http://www.barchart.com/)
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

import java.net.SocketException;

/**
 * The Class ExceptionUDT. Wraps all native UDT exceptions and more.
 */
// used by JNI; do not change any signatures;
public class ExceptionUDT extends SocketException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6353519522691064012L;

	/**
	 * The error udt. Keeps error description for this exception. Use this enum
	 * in switch/case to fine tune exception processing.
	 */
	public final ErrorUDT errorUDT;

	/**
	 * The socket id. Keeps socketID of the socket that produced this exception.
	 * Can possibly contain '0' when particular method can not determine
	 * {@link #socketID} that produced the exception.
	 */
	public final int socketID;

	/**
	 * Instantiates a new exception udt for native UDT::Exception. This
	 * exception is generated in the underlying UDT method.
	 * 
	 * @param socketID
	 *            the socket id
	 * @param errorCode
	 *            the error code
	 * @param comment
	 *            the comment
	 */
	protected ExceptionUDT(int socketID, int errorCode, String comment) {
		super(ErrorUDT.descriptionFrom(socketID, errorCode, comment));
		errorUDT = ErrorUDT.of(errorCode);
		this.socketID = socketID;
	}

	// used by SocketUDT
	/**
	 * Instantiates a new exception udt for synthetic JNI wrapper exception.
	 * This exception is generated in the JNI glue code itself.
	 * 
	 * @param socketID
	 *            the socket id
	 * @param error
	 *            the error
	 * @param comment
	 *            the comment
	 */
	protected ExceptionUDT(int socketID, ErrorUDT error, String comment) {
		super(ErrorUDT.descriptionFrom(socketID, error.code, comment));
		errorUDT = error;
		this.socketID = socketID;
	}

}
