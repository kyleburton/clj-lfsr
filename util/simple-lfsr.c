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


const char* long_to_binary_string(uint32_t value) {
  static char buff[1024];
  char *p = buff;
  uint32_t ii;
  bzero(buff,sizeof(buff));

  for(ii = 0u; ii < sizeof(uint32_t)*8; ++ii ) {
    *p++ = (value & (1u << ii)) ? '1' : '0';
    if (ii != 0 && 0 == (ii%4)) {
      *p++ = ' ';
    }
  }
  return buff;
}

uint32_t expected_period_for_bits( uint32_t num_bits ) {
  return (1l << num_bits) - 1;
}

void run_lfsr (uint32_t start, uint32_t num_bits, uint32_t tap_mask) {
  uint32_t period = 0u;
  uint32_t lfsr = 0;
  uint32_t expected_period = expected_period_for_bits(num_bits);
  lfsr = start;
  period = 0;

  dlog("run_lfsr: start=%lu; expected_period=%u tap_mask=%u/0x%X [%s]", (long unsigned)start, expected_period, tap_mask, tap_mask, long_to_binary_string(tap_mask));

  do {
    lfsr = (lfsr >> 1) ^ (unsigned int)((0 - (lfsr & 1u)) & tap_mask);
    dlog("state=%s",long_to_binary_string(lfsr));
    ++period;
  } while(lfsr != start);

  ilog("[%s] re-reached start, period=%u(%u) start=%u tap_mask=%u/0x%X [%s]",
       ( expected_period == period ? "SUCCESS" : "FAILURE" ),
       period,
       expected_period,
       start,
       tap_mask,
       tap_mask,
       long_to_binary_string(tap_mask));
}

uint32_t make_mask(const char* mask_str) {
  uint32_t a=0u, b=0u, c=0u, d=0u;
  uint32_t res = 0u;
  sscanf(mask_str,"[%u %u %u %u]",&a,&b,&c,&d);
  if (a) res |= (1 << (a-1));
  if (b) res |= (1 << (b-1));
  if (c) res |= (1 << (c-1));
  if (d) res |= (1 << (d-1));
  return res;
}

void run_lfsr_spec(const char* spec) {
  ilog("%s => %u : %s",spec, (uint32_t)atol(spec),strchr(spec,'['));
  run_lfsr( 1u, atol(spec), make_mask(strchr(spec,'[')));
}

int main (int argc, char** argv ) {
  log_set_level_str(LVL_INFO_STR);

  run_lfsr_spec("2 [2 1]");
  run_lfsr_spec("3  [3 2]");
  run_lfsr_spec("4  [4 3]");
  run_lfsr_spec("5 [5 3]");
  run_lfsr_spec("5 [5 3]");
  run_lfsr_spec("5 [5 4 3 2]");
  run_lfsr_spec("6 [6 5]");
  run_lfsr_spec("6 [6 5 3 2]");
  run_lfsr_spec("7 [7 6]");
  run_lfsr_spec("7 [7 6 5 4]");
  run_lfsr_spec("8 [8 6 5 4]");
  run_lfsr_spec("9 [9 5]");
  run_lfsr_spec("9 [9 8 6 5]");
  run_lfsr_spec("10 [10 7]");
  run_lfsr_spec("10 [10 9 7 6]");
  run_lfsr_spec("11 [11 9]");
  run_lfsr_spec("11 [11 10 9 7]");
  run_lfsr_spec("12 [12 11 8 6]");
  run_lfsr_spec("13 [13 12 10 9]");
  run_lfsr_spec("14 [14 13 11 9]");
  run_lfsr_spec("15 [15 14]");
  run_lfsr_spec("15 [15 14 13 11]");
  run_lfsr_spec("16 [16 14 13 11]");
  run_lfsr_spec("17 [17 14]");
  run_lfsr_spec("17 [17 16 15 14]");
  run_lfsr_spec("18 [18 11]");
  run_lfsr_spec("18 [18 17 16 13]");
  run_lfsr_spec("19 [19 18 17 14]");
  run_lfsr_spec("20 [20 17]");
  run_lfsr_spec("20 [20 19 16 14]");

  // 32 [32 30 26 25]

  run_lfsr_spec("21 [21 19]");
  run_lfsr_spec("21 [21 20 19 16]");
  run_lfsr_spec("22 [22 21]");
  run_lfsr_spec("22 [22 19 18 17]");
  run_lfsr_spec("23 [23 18]");
  run_lfsr_spec("23 [23 22 20 18]");
  run_lfsr_spec("24 [24 23 21 20]");
  run_lfsr_spec("25 [25 22]");
  run_lfsr_spec("25 [25 24 23 22]");
  run_lfsr_spec("26 [26 25 24 20]");
  run_lfsr_spec("27 [27 26 25 22]");
  run_lfsr_spec("28 [28 25]");
  run_lfsr_spec("28 [28 27 24 22]");
  run_lfsr_spec("29 [29 27]");
  run_lfsr_spec("29 [29 28 27 25]");
  run_lfsr_spec("30 [30 29 26 24]");
  run_lfsr_spec("31 [31 28]");
  run_lfsr_spec("31 [31 30 29 28]");
  run_lfsr_spec("32 [32 30 26 25]");


  return 0;
}

