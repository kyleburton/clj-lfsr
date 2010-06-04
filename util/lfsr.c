#include <errno.h>
#include <fcntl.h>
#include <math.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

#include "log-util.h"


off_t file_size(const char* file) {
  struct stat statbuf;
  if (-1 == stat(file,&statbuf)) {
    flog("Error invoking fstat on file: '%s' : (%d)%s",
        file, errno, strerror(errno));
    exit(errno);
  }

  return statbuf.st_size;
}

/**
 * The goal of this program is to test out the LFSR to ensure that a
 * given set of taps has a maximal sequence (eg: 2^n - 1).  Since this
 * effectively requires counting the number of times each byte
 * sequence is generated, it also means allocating 2^n bytes of
 * memory, which is prohibitive for large values of n.
 *
 * The technique I'm going to try out is to loop, for 1..n, running
 * through the LFSR sequnde until it exhausts its period (or we hit a
 * maximum of 2^n) only tracking (1>>n)&lfsr.  This will mean we only
 * need to allocated (2^n / n) bytes, which should be feasable for
 * larger values of n.
 *
 * Another idea: use mmap, there will be enough disk space.  Idea from
 * Toby.
 */

void mark_lfsr_seen( int* buff, uint32_t state ) {
  buff[state]++;
}

void run_lfsr (uint32_t tap_mask) {
  size_t num_entries = exp2l(32)/8;
  size_t num_bytes = num_entries * sizeof(uint32_t);
  unsigned int bitnum = 0,
    bitmask = 0;
  uint32_t period = 0u;
  uint32_t start = 1u;
  uint32_t lfsr = 0;
  dlog("num_entries=%ul; num_bytes=%ul",
          (unsigned int)num_entries,
          (unsigned int)num_bytes);

  // TOOD: mmap the file

    lfsr = start;
    seen[lfsr]++;

    period = 0;
    times_masked = 0;
    times_not_masked = 0;

    do {
      lfsr = (lfsr >> 1) ^ (unsigned int)((0 - (lfsr & 1u)) & tap_mask);
      ++period;
      if(0) {
        dlog("lfsr=%lu in bitmask=0x%X; period=%lu",
             (unsigned long)lfsr,
             bitmask,
             (unsigned long)period);
      }
    } while(lfsr != start);

    // TODO: assert that seen is all 1's, except for seen[0] which should be zero.
    // Zero it out while going through it so its ready for the next run...
  }
}

void mmap_test () {
  int ofd = -1;
  static const char* fname = "./lfsr.dat";

  ofd = open(fname, O_RDWR | O_CREAT, 0664);
  if ( -1 == ofd ) {
    flog("%s(%d) Error opening file: '%s' : %s",
            __FILE__,__LINE__,
            fname, strerror(errno));
    return;
  }

  dlog("size of file (%s) = %lld",fname, file_size(fname));

  char *buff = NULL;
  size_t len = 4294967296;
  int prot = PROT_READ | PROT_WRITE,
    flags  = MAP_FILE | MAP_SHARED,
    offset = 0;
  buff = mmap(buff,len,prot,flags,ofd,offset);

  if ( len < file_size(fname) ) {
    elog("Error, file not bing enough for the mmap operation, was:%lld bytes, need at least %lu bytes",
         file_size(fname),len);
    return;
  }

  dlog("sizeof(char*)=%lu sizeof(size_t)=%lu", sizeof(char*), sizeof(size_t));
  dlog("mapping, len=%lu; prot=%d; flags=%d",
      len, prot, flags);
  if (MAP_FAILED == buff) {
    flog("Error mmaping file: '%s' : (%d)%s",
        fname, errno, strerror(errno));
    close(ofd);
    return;
  }

  dlog("buff=%p", buff);
/*   dlog("zeroing out entire file / buffer len=%lu", len); */
/*   bzero(buff,len-1); */
/*   dlog("zeroing out entire file / buffer"); */

  buff[0] = 0;
  dlog("placing 2 at: %lu",len-1);
  buff[len-1] = 0;

  if ( -1 == munmap(buff, len) ) {
    flog("Error unmapping memory: (%d) %s", errno, strerror(errno));
  }
  ilog("unmapped the memory");
  close(ofd);
  ilog("closed the file");
}

int main (int argc, char** argv ) {

  mmap_test();
  return 0;

  run_lfsr(0xd0000001);
  return 0;

  /************************************************************************/


/*   unsigned lfsr = 1u; */
/*   unsigned period = 0u; */
/*   /\* taps: 32 31 29 1; characteristic polynomial: x^32 + x^31 + x^29 + x + 1 *\/ */
/*   // unsigned long mask = 0xd0000001u; */
/*   /\* taps: 32 30 26 25; characteristic polynomial: x^32 + x^31 + x^29 + x + 1 *\/ */
/*   unsigned mask = 2734686209u; */
/*   unsigned total_ids = ~0; */
/*   fprintf(stderr,"sizeof uint32_t=%lu\n", sizeof(uint32_t)); */
/*   fprintf(stderr,"sizeof unsigned=%lu\n", sizeof(unsigned)); */
/*   fprintf(stderr,"total_ids=%lu/%lu\n", */
/*           (unsigned long)total_ids, */
/*           (unsigned long)total_ids * sizeof(unsigned)); */

/*   return 0; */

/*   unsigned *seen = malloc(total_ids*sizeof(unsigned)); */

/*   if ( !seen ) { */
/*     fprintf(stderr,"Error, unable to allocate %lu bytes of memory: %s", (unsigned long)total_ids, strerror(errno)); */
/*     return errno; */
/*   } */

/*   fprintf(stderr, "clearing %lu/%lu bytes of memory\n", */
/*           (unsigned long)total_ids, */
/*           (unsigned long)total_ids * sizeof(unsigned)); */
/*   //memset((void*)seen,0,total_ids); */
/*   bzero(seen,total_ids*sizeof(unsigned)); */
/*   return 0; */

/*   do { */
/*     lfsr = (lfsr >> 1) ^ (unsigned int)((0 - (lfsr & 1u)) & mask); */
/*     ++period; */
/*     if (seen[lfsr]) { */
/*       printf("DUPE: generated period:%lu lfsr:%lu %lu times!\n", */
/*              (unsigned long)period, */
/*              (unsigned long)lfsr, */
/*              (unsigned long)seen[lfsr]); */
/*     } */
/*     seen[lfsr]++; */
/*   } while(lfsr != 1u); */

//  return 0;
}
