#!/bin/bash

PATIENT=$1

if [ -z $PATIENT ]; then
	echo "Usage: $0 <patient sub directory>"
	exit 1;
fi


# rename files
cd $PATIENT
for x in *.txt
do 
	# get previous person
	NM=$(grep  "Patient Name\.\.\." $x | awk -F '.' '{print $NF}')
	PNM=${PATIENT^}
	
	# rename the file to new patient
	y=`echo $x | cut -c 10-`
	y="$PATIENT$y" 

	# move old content to tmp	
	mv $x tmp	
		
	# remove non-ascii characters && replace old patient name with new name
	# rename the file to new patient
	cat tmp | tr -cd '\000-\177' | sed -e "s/$NM/$PNM/g" > $y
	
	rm tmp
done
