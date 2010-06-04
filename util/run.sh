set -x
set -e

if [ ! -f lfsr.dat ]; then
  echo "Creating mmap file (4g of zeros).."
  dd if=/dev/zero of=lfsr.dat bs=1048576 count=4096
  echo "done"
fi

gcc -Wall -o lfsr lfsr.c

./lfsr "$@"
