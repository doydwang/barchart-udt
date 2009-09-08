/**
 * =================================================================================
 *
 * BSD LICENCE (http://en.wikipedia.org/wiki/BSD_licenses)
 *
 * ARTIFACT='barchart-udt4'.VERSION='1.0.0-SNAPSHOT'.TIMESTAMP='2009-09-07_23-05-40'
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
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_barchart_udt_SocketUDT */

#ifndef _Included_com_barchart_udt_SocketUDT
#define _Included_com_barchart_udt_SocketUDT
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: log */
#undef com_barchart_udt_SocketUDT_INFINITE_TTL
#define com_barchart_udt_SocketUDT_INFINITE_TTL -1L
#undef com_barchart_udt_SocketUDT_INFINITE_TIMEOUT
#define com_barchart_udt_SocketUDT_INFINITE_TIMEOUT -1L
#undef com_barchart_udt_SocketUDT_UNLIMITED_BW
#define com_barchart_udt_SocketUDT_UNLIMITED_BW -1LL
#undef com_barchart_udt_SocketUDT_DEFAULT_ACCEPT_QUEUE_SIZE
#define com_barchart_udt_SocketUDT_DEFAULT_ACCEPT_QUEUE_SIZE 256L
#undef com_barchart_udt_SocketUDT_DEFAULT_MAX_SELECTOR_SIZE
#define com_barchart_udt_SocketUDT_DEFAULT_MAX_SELECTOR_SIZE 1024L
#undef com_barchart_udt_SocketUDT_DEFAULT_CONNECTOR_POOL_SIZE
#define com_barchart_udt_SocketUDT_DEFAULT_CONNECTOR_POOL_SIZE 16L
#undef com_barchart_udt_SocketUDT_DEFAULT_MIN_SELECTOR_TIMEOUT
#define com_barchart_udt_SocketUDT_DEFAULT_MIN_SELECTOR_TIMEOUT 10L
#undef com_barchart_udt_SocketUDT_UDT_EXCEPT_INDEX
#define com_barchart_udt_SocketUDT_UDT_EXCEPT_INDEX 2L
#undef com_barchart_udt_SocketUDT_UDT_WRITE_INDEX
#define com_barchart_udt_SocketUDT_UDT_WRITE_INDEX 1L
#undef com_barchart_udt_SocketUDT_UDT_READ_INDEX
#define com_barchart_udt_SocketUDT_UDT_READ_INDEX 0L
#undef com_barchart_udt_SocketUDT_UDT_SIZE_COUNT
#define com_barchart_udt_SocketUDT_UDT_SIZE_COUNT 3L
/* Inaccessible static: LINGER_ZERO */
/* Inaccessible static: _00024assertionsDisabled */
/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    initClass0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_initClass0
  (JNIEnv *, jclass);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    stopClass0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_stopClass0
  (JNIEnv *, jclass);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    initInstance0
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_barchart_udt_SocketUDT_initInstance0
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    initInstance1
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_barchart_udt_SocketUDT_initInstance1
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    accept0
 * Signature: ()Lcom/barchart/udt/SocketUDT;
 */
JNIEXPORT jobject JNICALL Java_com_barchart_udt_SocketUDT_accept0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    bind0
 * Signature: (Ljava/net/InetSocketAddress;)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_bind0
  (JNIEnv *, jobject, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    close0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_close0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    connect0
 * Signature: (Ljava/net/InetSocketAddress;)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_connect0
  (JNIEnv *, jobject, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    hasLoadedRemoteSocketAddress
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_barchart_udt_SocketUDT_hasLoadedRemoteSocketAddress
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    hasLoadedLocalSocketAddress
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_barchart_udt_SocketUDT_hasLoadedLocalSocketAddress
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    getOption0
 * Signature: (ILjava/lang/Class;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_com_barchart_udt_SocketUDT_getOption0
  (JNIEnv *, jobject, jint, jclass);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    setOption0
 * Signature: (ILjava/lang/Class;Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_setOption0
  (JNIEnv *, jobject, jint, jclass, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    listen0
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_listen0
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    receive0
 * Signature: (II[B)I
 */
JNIEXPORT jint JNICALL Java_com_barchart_udt_SocketUDT_receive0
  (JNIEnv *, jobject, jint, jint, jbyteArray);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    select0
 * Signature: ([I[I[I[IJ)I
 */
JNIEXPORT jint JNICALL Java_com_barchart_udt_SocketUDT_select0
  (JNIEnv *, jclass, jintArray, jintArray, jintArray, jintArray, jlong);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    selectEx0
 * Signature: ([I[I[I[IJ)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_selectEx0
  (JNIEnv *, jclass, jintArray, jintArray, jintArray, jintArray, jlong);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    send0
 * Signature: (IIIZ[B)I
 */
JNIEXPORT jint JNICALL Java_com_barchart_udt_SocketUDT_send0
  (JNIEnv *, jobject, jint, jint, jint, jboolean, jbyteArray);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    updateMonitor0
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_updateMonitor0
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    getErrorCode0
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_barchart_udt_SocketUDT_getErrorCode0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    getErrorMessage0
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_barchart_udt_SocketUDT_getErrorMessage0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    clearError0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_clearError0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    isOpen0
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_barchart_udt_SocketUDT_isOpen0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    testEmptyCall0
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_testEmptyCall0
  (JNIEnv *, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    testIterateArray0
 * Signature: ([Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_testIterateArray0
  (JNIEnv *, jobject, jobjectArray);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    testIterateSet0
 * Signature: (Ljava/util/Set;)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_testIterateSet0
  (JNIEnv *, jobject, jobject);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    testMakeArray0
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_barchart_udt_SocketUDT_testMakeArray0
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_barchart_udt_SocketUDT
 * Method:    testGetSetArray0
 * Signature: ([IZ)V
 */
JNIEXPORT void JNICALL Java_com_barchart_udt_SocketUDT_testGetSetArray0
  (JNIEnv *, jobject, jintArray, jboolean);

#ifdef __cplusplus
}
#endif
#endif
