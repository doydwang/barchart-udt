#!/bin/bash

#
# custom macosx hudson build to fix apple's "think different"
#

#
# must provide maven home via
# http://wiki.hudson-ci.org/display/HUDSON/Tool+Environment+Plugin
#

echo "### pwd   = $PWD"

echo "### label = $label"
echo "### jdk   = $jdk"

case "$jdk" in
java32)
    export MAVEN_OPTS="-d32 $MAVEN_OPTS"
    ;;
java64)
    export MAVEN_OPTS="-d64 $MAVEN_OPTS"
    ;;
*)
    echo "invalid jdk"; exit 1
    ;;
esac

echo "### MAVEN_OPTS=$MAVEN_OPTS"

"$APACHE_MAVEN_3_HOME/bin/mvn" $MVN_CMD_UDT

