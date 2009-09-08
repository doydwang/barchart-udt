#!/bin/bash

# used by eclipse cdt interactive builder

SCRIPT=`basename $0`
log() {
	echo "$SCRIPT: $1"
}
toLower() {
  echo $1 | tr "[:upper:]" "[:lower:]" 
} 
toUpper() {
  echo $1 | tr "[:lower:]" "[:upper:]" 
} 

KIND=$1

LIB_NAME="SocketUDT"
LIB_FOLDER="$PWD/../target/classes"

ARTIFACT=""
ARTIFACT_MAP=""

LIB_FILE=""
LIB_EXTENSION=""

OS=`uname -s`
OS=`toLower $OS`
MACH=`uname -m`
MACH=`toLower $MACH`

makeExtension(){
	case $OS in
	linux)
		LIB_EXTENSION="so"
	;;
	cygwin*)
		LIB_EXTENSION="dll"
	;;
	*)
		log "error: unsupported OS=$OS"
		exit 1
	;;
	esac
	log "detected LIB_EXTENSION=$LIB_EXTENSION"
}
findArtifact(){
	COUNT=`ls -1 *.$LIB_EXTENSION | wc -l`
	if [ $COUNT = 1 ] ; then
		ARTIFACT=`ls -1 *.$LIB_EXTENSION`
	else
		log "error; can not find artifact; COUNT=$COUNT"
		exit 1
	fi 		
}
findArtifactMap(){
	COUNT=`ls -1 *.map | wc -l`
	if [ $COUNT = 1 ] ; then
		ARTIFACT_MAP=`ls -1 *.map`
	else
		log "error; can not find artifact map; COUNT=$COUNT"
		exit 1
	fi 		
}
makeLibraryName() {
	case $OS in
			linux)
			case $MACH in
				i*86)
					LIB_FILE="lib$LIB_NAME-linux-x86-32.so"
				;;
				amd64 | x86_64)
					LIB_FILE="lib$LIB_NAME-linux-x86-64.so"
				;;
				*)
					log "error; not supported MACH=$MACH"
					exit 1
				;;
			esac		
		;;
		cygwin*)
			case $MACH in
				i*86)
					LIB_FILE="$LIB_NAME-windows-x86-32.dll"
				;;
				amd64 | x86_64)
					LIB_FILE="$LIB_NAME-windows-x86-64.dll"
				;;
				*)
					log "error; not supported MACH=$MACH"
					exit 1
				;;
			esac		
		;;
		*)
			log "error: not supported OS=$OS"
			exit 1
		;;
	esac
}
checkSupported(){
	case $OS in
		linux | cygwin*)
			log "detected OS=$OS"
		;;
		*)
			log "error: unsupported OS=$OS"
			exit 1
		;;
	esac
	case $MACH in
		i*86 | amd64 | x86_64 )
			log "detected MACH=$MACH"
		;;
		*)
			log "error: unsupported MACH=$MACH"
			exit 1
		;;
	esac
}

#####################################################

checkSupported
makeExtension

case $KIND in
	start)
		log "pre-build task;"
		log "done"
	;;
	finish)
		#
		log "post-build task;"
		findArtifact
		findArtifactMap
		makeLibraryName
		#
		MAP_FILE="$LIB_FILE.map"
		TARGET="$LIB_FOLDER/$LIB_FILE"
		TARGET_MAP="$LIB_FOLDER/$MAP_FILE"
		#
		log "ARTIFACT     : $ARTIFACT"
		log "ARTIFACT_MAP : $ARTIFACT_MAP"
		log "TARGET     : $TARGET"
		log "TARGET_MAP : $TARGET_MAP"
		#
		cp --force --update "$ARTIFACT" "$TARGET" 
		cp --force --update "$ARTIFACT_MAP" "$TARGET_MAP" 
		#
		ldd -r "$TARGET"
		#
		ls -l "$LIB_FOLDER"
		log "done"
	;;
	*)
		log "error; unecpected KIND=$KIND"
		exit 1
	;;
esac

exit 0

#####################################################
