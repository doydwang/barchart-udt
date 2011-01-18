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
package com.barchart.udt.nio;

import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class ConnectorThreadPoolUDT {

	final ConnectorThreadFactoryUDT factory;

	final Queue<SelectionKeyUDT> readyQueue = //
	new ConcurrentLinkedQueue<SelectionKeyUDT>();

	final ConcurrentMap<SelectionKeyUDT, Runnable> taskMap = //
	new ConcurrentHashMap<SelectionKeyUDT, Runnable>();

	final ExecutorService service;

	final String THREAD_PREFIX = "UDT Connector #";

	final int THREAD_PRIORITY = Thread.NORM_PRIORITY;

	final int THREAD_TIME_KEEP = 3 * 1000;
	final TimeUnit THREAD_TIME_UNIT = TimeUnit.MILLISECONDS;

	ConnectorThreadPoolUDT(int maximumPoolSize) {

		factory = new ConnectorThreadFactoryUDT(THREAD_PREFIX, THREAD_PRIORITY);

		// service = Executors.newCachedThreadPool();

		service = ConnectorExecutorsUDT.newDynamicThreadPool(//
				maximumPoolSize, //
				THREAD_TIME_KEEP, //
				THREAD_TIME_UNIT, //
				factory);

	}

	void submitRequest(SelectionKeyUDT keyUDT, InetSocketAddress remote) {

		if (taskMap.containsKey(keyUDT)) {
			return;
		}

		Runnable task = new ConnectorTaskUDT(//
				keyUDT, taskMap, readyQueue, remote);

		Runnable result = taskMap.putIfAbsent(keyUDT, task);

		if (result == null) {
			service.submit(task);
		}

	}

}
