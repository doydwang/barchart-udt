#/bin/sh

# must provide project name
PROJ="barchart-udt4"

# google code site convention
BASE="$WORKSPACE/$PROJ"
SITE="$WORKSPACE/site/$PROJ"

# google code credentials
CRED=" --username $SVN_USER --password $SVN_PASS "

echo "### BASE=$BASE"
echo "### SITE=$SITE"

### REMOVE

echo "### svn cleanup/update"
svn cleanup  "$SITE"
svn update  "$SITE"

echo "### svn delete"
svn delete --force "$SITE"

echo "### svn commit"
svn commit "$CRED" --message "jenkins: remove site" "$SITE"

### PUBLISH

echo "### svn cleanup/update"
svn cleanup  "$SITE"
svn update  "$SITE"

echo "### mkdir new"
mkdir --parents "$SITE"

echo "### copy new"
cp --verbose --force --recursive "$BASE/target/site/"* "$SITE/"

echo "### svn add new"
svn add --force "$SITE"

echo "### svn propset html"
find "$SITE" -name '*.html' -exec svn propset --force svn:mime-type text/html {} \;

echo "### svn propset css"
find "$SITE" -name '*.css'  -exec svn propset --force svn:mime-type text/css {} \;

echo "### svn cleanup/update"
svn cleanup  "$SITE"
svn update  "$SITE"

echo "### svn commit"
svn commit "$CRED" --message "jenkins: publish site" "$SITE"

###
