#!/bin/bash

EPIC_KEY=$1
EPIC_DIR=$2

MARS_KEY=$3
MARS_DIR=$4

OUT_DIR=$5

mkdir -p $OUT_DIR 2> /dev/null
cp -r $MARS_DIR/* $OUT_DIR/

IFS=$'\n'
for LN in $(cat $EPIC_KEY | cut -f2,6| sort | uniq)
do
	MRN=$(echo $LN| cut -f1)
	EPIC_PATIENT=$(echo $LN| cut -f2)
	MARS_PATIENT=$(grep "$MRN" $MARS_KEY| cut -f6 | head -1)
	
	echo -e "$MRN\t$EPIC_PATIENT\t$MARS_PATIENT"
	
	if [ $MARS_PATIENT ];
	then
		cp $EPIC_DIR/$EPIC_PATIENT/* $OUT_DIR/$MARS_PATIENT
	fi
done



