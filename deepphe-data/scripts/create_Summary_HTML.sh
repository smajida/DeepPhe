#!/bin/bash


echo "<html><header><title>WTF</title></header><body><table><tr>"

for x in $@
do
	z=$(basename $x)
	y=$(cat $x)
	echo "<td valign=top align=left><h1>$z</h1><pre>$y</pre></td>"
done


echo "</tr></table></body></html>"





