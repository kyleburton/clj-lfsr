set -e
set -x
gcc -Wall -o lfsr lfsr.c
./lfsr
#./lfsr | sort -z | uniq -d
