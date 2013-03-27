#/bin/sh

if [ ! $# -eq 1 ]
then 
	echo usage: `basename $0` 'hashtag' 1>&2
	exit 1
fi

url='http://search.twitter.com/search.json?q='$1'&rpp=100&include_entities=true&result_type=recent'
wget $url -O a 2>/dev/null

replace='},"from_user":"'
regExp='^(.{1,25})",.*'

cat a | sed -n -E "s/$replace/\n/pg" >b
cat b | sed -n -E "s/$regExp/\1/pg" >c

regExp='^.*,"id_str":"([0-9]{18})","iso_language_code":".*'

cat b | sed -n -E "s/$regExp/\1/pg" >d

curRow=1
line=`cat c | wc -l`
line=`expr $line + 1`

while [ $curRow -le $line ]
do
	for name in `awk 'NR=='$curRow' {print $0}' c`
	do
		for status in `awk 'NR=='$curRow' {print $0}' d`
		do
			url='https://twitter.com/#!/'$name'/status/'$status
			echo 'Tweets'$curRow'    '$url
		done
	done
	((curRow++))
done

rm a b c d
