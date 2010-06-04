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
    if (ii != 0 && 0 == (ii%4)) {
      *p++ = ' ';
    }
    *p++ = (value & (1u << ii)) ? '1' : '0';
  }
  return buff;
}

uint32_t expected_period_for_bits( uint32_t num_bits ) {
  return (1l << num_bits) - 1;
}

void run_lfsr (uint32_t start, uint32_t num_bits, uint32_t tap_mask, int print_sequence) {
  uint32_t period = 0u;
  uint32_t lfsr = 0;
  uint32_t expected_period = expected_period_for_bits(num_bits);
  lfsr = start;
  period = 0;

  dlog("run_lfsr: start=%lu; expected_period=%u tap_mask=%u/0x%X [%s]", (long unsigned)start, expected_period, tap_mask, tap_mask, long_to_binary_string(tap_mask));

  if (print_sequence) {
    printf("[%d] % 8d : %s START\n", num_bits, lfsr, long_to_binary_string(lfsr));
  }

  do {
    lfsr = (lfsr >> 1) ^ (unsigned int)((0 - (lfsr & 1u)) & tap_mask);
    dlog("state=%s",long_to_binary_string(lfsr));
    ++period;

    if (print_sequence) {
      printf("[%d] % 8d : %s\n", num_bits, lfsr, long_to_binary_string(lfsr));
    }

  } while(lfsr != start);

  if (print_sequence) {
    printf("[%d] % 8d : %s END\n", num_bits, lfsr, long_to_binary_string(lfsr));
  }

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

void run_lfsr_spec(const char* spec, int print_sequence) {
  ilog("%s => %u : %s",spec, (uint32_t)atol(spec),strchr(spec,'['));
  run_lfsr( 1u, atol(spec), make_mask(strchr(spec,'[')), print_sequence);
}

int main (int argc, char** argv ) {
  const char *lfsr_specs[] = {
    "2 [2 1]",
    "3 [3 2]",
    "4 [4 3]",
    "5 [5 3]",
    "5 [5 3]",
    "5 [5 4 3 2]",
    "6 [6 5]",
    "6 [6 5 3 2]",
    "7 [7 6]",
    "7 [7 6 5 4]",
    "8 [8 6 5 4]",
    "9 [9 5]",
    "9 [9 8 6 5]",
    "10 [10 7]",
    "10 [10 9 7 6]",
    "11 [11 9]",
    "11 [11 10 9 7]",
    "12 [12 11 8 6]",
    "13 [13 12 10 9]",
    "14 [14 13 11 9]",
    "15 [15 14]",
    "15 [15 14 13 11]",
    "16 [16 14 13 11]",
    "17 [17 14]",
    "17 [17 16 15 14]",
    "18 [18 11]",
    "18 [18 17 16 13]",
    "19 [19 18 17 14]",
    "20 [20 17]",
    "20 [20 19 16 14]",
    "21 [21 19]",
    "21 [21 20 19 16]",
    "22 [22 21]",
    "22 [22 19 18 17]",
    "23 [23 18]",
    "23 [23 22 20 18]",
    "24 [24 23 21 20]",
    "25 [25 22]",
    "25 [25 24 23 22]",
    "26 [26 25 24 20]",
    "27 [27 26 25 22]",
    "28 [28 25]",
    "28 [28 27 24 22]",
    "29 [29 27]",
    "29 [29 28 27 25]",
    "30 [30 29 26 24]",
    "31 [31 28]",
    "31 [31 30 29 28]",
    "32 [32 30 26 25]"
  };

  int num_specs = sizeof(lfsr_specs) / sizeof(char*);
  int print_sequence = 0;
  int ii;

  log_set_level_str(LVL_INFO_STR);

  if (argc == 1) {
    printf("%s print_sequence lfsr_number\n",argv[0]);
    printf("  where print_sequence is 0 (false) or 1 (true)\n");
    printf("  where lfsr_number is one of: \n");
    for(ii = 0; ii < num_specs; ++ii ) {
      printf("    lfsr[%d]: %s\n", ii, lfsr_specs[ii]);
    }
  }

  if (argc > 1) {
    print_sequence = atol(argv[1]);
  }

  if (argc > 2) {
    run_lfsr_spec(lfsr_specs[atol(argv[2])],print_sequence);
    return 0;
  }

  return 0;
}

