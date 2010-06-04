#include <stdio.h>
#include <stdint.h>

/* gcc write-test.c && (./a.out | hexdump ) */

int main (int argc, char** argv) {
  int i = 127;
  int j;
  for (j=0;j<=16;j++) {
    write(fileno(stdout),(void*)&j,sizeof(int));
    write(fileno(stdout),0,1);
  }
  return 0;
}
