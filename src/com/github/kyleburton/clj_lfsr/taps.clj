(ns com.github.kyleburton.clj-lfsr.taps)

;; Derived from:
;;   http://en.wikipedia.org/wiki/LFSR
;;   http://www.physics.otago.ac.nz/px/research/electronics/papers/technical-reports/lfsr_table.pdf
;; See also: $project/util/gen-taps.rb

;; NB: there may be multiple sets of taps for a maximal cycle (2^n - 1) LFSR, these are just one set...
(def *maximal-length-taps* [
  {:lfsr-size 2 :nbits 2 :taps [2 1]}
  {:lfsr-size 2 :nbits 3 :taps [3 2]}
  {:lfsr-size 2 :nbits 4 :taps [4 3]}
  {:lfsr-size 2 :nbits 5 :taps [5 3]}
  {:lfsr-size 4 :nbits 5 :taps [5 4 3 2]}
  {:lfsr-size 2 :nbits 6 :taps [6 5]}
  {:lfsr-size 4 :nbits 6 :taps [6 5 3 2]}
  {:lfsr-size 2 :nbits 7 :taps [7 6]}
  {:lfsr-size 4 :nbits 7 :taps [7 6 5 4]}
  {:lfsr-size 4 :nbits 8 :taps [8 6 5 4]}
  {:lfsr-size 2 :nbits 9 :taps [9 5]}
  {:lfsr-size 4 :nbits 9 :taps [9 8 6 5]}
  {:lfsr-size 2 :nbits 10 :taps [10 7]}
  {:lfsr-size 4 :nbits 10 :taps [10 9 7 6]}
  {:lfsr-size 2 :nbits 11 :taps [11 9]}
  {:lfsr-size 4 :nbits 11 :taps [11 10 9 7]}
  {:lfsr-size 4 :nbits 12 :taps [12 11 8 6]}
  {:lfsr-size 4 :nbits 13 :taps [13 12 10 9]}
  {:lfsr-size 4 :nbits 14 :taps [14 13 11 9]}
  {:lfsr-size 2 :nbits 15 :taps [15 14]}
  {:lfsr-size 4 :nbits 15 :taps [15 14 13 11]}
  {:lfsr-size 4 :nbits 16 :taps [16 14 13 11]}
  {:lfsr-size 2 :nbits 17 :taps [17 14]}
  {:lfsr-size 4 :nbits 17 :taps [17 16 15 14]}
  {:lfsr-size 2 :nbits 18 :taps [18 11]}
  {:lfsr-size 4 :nbits 18 :taps [18 17 16 13]}
  {:lfsr-size 4 :nbits 19 :taps [19 18 17 14]}
  {:lfsr-size 2 :nbits 20 :taps [20 17]}
  {:lfsr-size 4 :nbits 20 :taps [20 19 16 14]}
  {:lfsr-size 2 :nbits 21 :taps [21 19]}
  {:lfsr-size 4 :nbits 21 :taps [21 20 19 16]}
  {:lfsr-size 2 :nbits 22 :taps [22 21]}
  {:lfsr-size 4 :nbits 22 :taps [22 19 18 17]}
  {:lfsr-size 2 :nbits 23 :taps [23 18]}
  {:lfsr-size 4 :nbits 23 :taps [23 22 20 18]}
  {:lfsr-size 4 :nbits 24 :taps [24 23 21 20]}
  {:lfsr-size 2 :nbits 25 :taps [25 22]}
  {:lfsr-size 4 :nbits 25 :taps [25 24 23 22]}
  {:lfsr-size 4 :nbits 26 :taps [26 25 24 20]}
  {:lfsr-size 4 :nbits 27 :taps [27 26 25 22]}
  {:lfsr-size 2 :nbits 28 :taps [28 25]}
  {:lfsr-size 4 :nbits 28 :taps [28 27 24 22]}
  {:lfsr-size 2 :nbits 29 :taps [29 27]}
  {:lfsr-size 4 :nbits 29 :taps [29 28 27 25]}
  {:lfsr-size 4 :nbits 30 :taps [30 29 26 24]}
  {:lfsr-size 2 :nbits 31 :taps [31 28]}
  {:lfsr-size 4 :nbits 31 :taps [31 30 29 28]}
  {:lfsr-size 4 :nbits 32 :taps [32 30 26 25]}
  {:lfsr-size 2 :nbits 33 :taps [33 20]}
  {:lfsr-size 4 :nbits 33 :taps [33 32 29 27]}
  {:lfsr-size 4 :nbits 34 :taps [34 31 30 26]}
  {:lfsr-size 2 :nbits 35 :taps [35 33]}
  {:lfsr-size 4 :nbits 35 :taps [35 34 28 27]}
  {:lfsr-size 2 :nbits 36 :taps [36 25]}
  {:lfsr-size 4 :nbits 36 :taps [36 35 29 28]}
  {:lfsr-size 4 :nbits 37 :taps [37 36 33 31]}
  {:lfsr-size 4 :nbits 38 :taps [38 37 33 32]}
  {:lfsr-size 2 :nbits 39 :taps [39 35]}
  {:lfsr-size 4 :nbits 39 :taps [39 38 35 32]}
  {:lfsr-size 4 :nbits 40 :taps [40 37 36 35]}
  {:lfsr-size 2 :nbits 41 :taps [41 38]}
  {:lfsr-size 4 :nbits 41 :taps [41 40 39 38]}
  {:lfsr-size 4 :nbits 42 :taps [42 40 37 35]}
  {:lfsr-size 4 :nbits 43 :taps [43 42 38 37]}
  {:lfsr-size 4 :nbits 44 :taps [44 42 39 38]}
  {:lfsr-size 4 :nbits 45 :taps [45 44 42 41]}
  {:lfsr-size 4 :nbits 46 :taps [46 40 39 38]}
  {:lfsr-size 2 :nbits 47 :taps [47 42]}
  {:lfsr-size 4 :nbits 47 :taps [47 46 43 42]}
  {:lfsr-size 4 :nbits 48 :taps [48 44 41 39]}
  {:lfsr-size 2 :nbits 49 :taps [49 40]}
  {:lfsr-size 4 :nbits 49 :taps [49 45 44 43]}
  {:lfsr-size 4 :nbits 50 :taps [50 48 47 46]}
  {:lfsr-size 4 :nbits 51 :taps [51 50 48 45]}
  {:lfsr-size 2 :nbits 52 :taps [52 49]}
  {:lfsr-size 4 :nbits 52 :taps [52 51 49 46]}
  {:lfsr-size 4 :nbits 53 :taps [53 52 51 47]}
  {:lfsr-size 4 :nbits 54 :taps [54 51 48 46]}
  {:lfsr-size 2 :nbits 55 :taps [55 31]}
  {:lfsr-size 4 :nbits 55 :taps [55 54 53 49]}
  {:lfsr-size 4 :nbits 56 :taps [56 54 52 49]}
  {:lfsr-size 2 :nbits 57 :taps [57 50]}
  {:lfsr-size 4 :nbits 57 :taps [57 55 54 52]}
  {:lfsr-size 2 :nbits 58 :taps [58 39]}
  {:lfsr-size 4 :nbits 58 :taps [58 57 53 52]}
  {:lfsr-size 4 :nbits 59 :taps [59 57 55 52]}
  {:lfsr-size 2 :nbits 60 :taps [60 59]}
  {:lfsr-size 4 :nbits 60 :taps [60 58 56 55]}
  {:lfsr-size 4 :nbits 61 :taps [61 60 59 56]}
  {:lfsr-size 4 :nbits 62 :taps [62 59 57 56]}
  {:lfsr-size 2 :nbits 63 :taps [63 62]}
  {:lfsr-size 4 :nbits 63 :taps [63 62 59 58]}
  {:lfsr-size 4 :nbits 64 :taps [64 63 61 60]}
  {:lfsr-size 2 :nbits 65 :taps [65 47]}
  {:lfsr-size 4 :nbits 65 :taps [65 64 62 61]}
  {:lfsr-size 4 :nbits 66 :taps [66 60 58 57]}
  {:lfsr-size 4 :nbits 67 :taps [67 66 65 62]}
  {:lfsr-size 2 :nbits 68 :taps [68 59]}
  {:lfsr-size 4 :nbits 68 :taps [68 67 63 61]}
  {:lfsr-size 4 :nbits 69 :taps [69 67 64 63]}
  {:lfsr-size 4 :nbits 70 :taps [70 69 67 65]}
  {:lfsr-size 2 :nbits 71 :taps [71 65]}
  {:lfsr-size 4 :nbits 71 :taps [71 70 68 66]}
  {:lfsr-size 4 :nbits 72 :taps [72 69 63 62]}
  {:lfsr-size 2 :nbits 73 :taps [73 48]}
  {:lfsr-size 4 :nbits 73 :taps [73 71 70 69]}
  {:lfsr-size 4 :nbits 74 :taps [74 71 70 67]}
  {:lfsr-size 4 :nbits 75 :taps [75 74 72 69]}
  {:lfsr-size 4 :nbits 76 :taps [76 74 72 71]}
  {:lfsr-size 4 :nbits 77 :taps [77 75 72 71]}
  {:lfsr-size 4 :nbits 78 :taps [78 77 76 71]}
  {:lfsr-size 2 :nbits 79 :taps [79 70]}
  {:lfsr-size 4 :nbits 79 :taps [79 77 76 75]}
  {:lfsr-size 4 :nbits 80 :taps [80 78 76 71]}
  {:lfsr-size 2 :nbits 81 :taps [81 77]}
  {:lfsr-size 4 :nbits 81 :taps [81 79 78 75]}
  {:lfsr-size 4 :nbits 82 :taps [82 78 76 73]}
  {:lfsr-size 4 :nbits 83 :taps [83 81 79 76]}
  {:lfsr-size 2 :nbits 84 :taps [84 71]}
  {:lfsr-size 4 :nbits 84 :taps [84 83 77 75]}
  {:lfsr-size 4 :nbits 85 :taps [85 84 83 77]}
  {:lfsr-size 4 :nbits 86 :taps [86 84 81 80]}
  {:lfsr-size 2 :nbits 87 :taps [87 74]}
  {:lfsr-size 4 :nbits 87 :taps [87 86 82 80]}
  {:lfsr-size 4 :nbits 88 :taps [88 80 79 77]}
  {:lfsr-size 2 :nbits 89 :taps [89 51]}
  {:lfsr-size 4 :nbits 89 :taps [89 86 84 83]}
  {:lfsr-size 4 :nbits 90 :taps [90 88 87 85]}
  {:lfsr-size 4 :nbits 91 :taps [91 90 86 83]}
  {:lfsr-size 4 :nbits 92 :taps [92 90 87 86]}
  {:lfsr-size 2 :nbits 93 :taps [93 91]}
  {:lfsr-size 4 :nbits 93 :taps [93 91 90 87]}
  {:lfsr-size 2 :nbits 94 :taps [94 73]}
  {:lfsr-size 4 :nbits 94 :taps [94 93 89 88]}
  {:lfsr-size 2 :nbits 95 :taps [95 84]}
  {:lfsr-size 4 :nbits 95 :taps [95 94 90 88]}
  {:lfsr-size 4 :nbits 96 :taps [96 90 87 86]}
  {:lfsr-size 2 :nbits 97 :taps [97 91]}
  {:lfsr-size 4 :nbits 97 :taps [97 95 93 91]}
  {:lfsr-size 2 :nbits 98 :taps [98 87]}
  {:lfsr-size 4 :nbits 98 :taps [98 97 91 90]}
  {:lfsr-size 4 :nbits 99 :taps [99 95 94 92]}
  {:lfsr-size 2 :nbits 100 :taps [100 63]}
  {:lfsr-size 4 :nbits 100 :taps [100 98 93 92]}
  {:lfsr-size 4 :nbits 101 :taps [101 100 95 94]}
  {:lfsr-size 4 :nbits 102 :taps [102 99 97 96]}
  {:lfsr-size 2 :nbits 103 :taps [103 94]}
  {:lfsr-size 4 :nbits 103 :taps [103 102 99 94]}
  {:lfsr-size 4 :nbits 104 :taps [104 103 94 93]}
  {:lfsr-size 2 :nbits 105 :taps [105 89]}
  {:lfsr-size 4 :nbits 105 :taps [105 104 99 98]}
  {:lfsr-size 2 :nbits 106 :taps [106 91]}
  {:lfsr-size 4 :nbits 106 :taps [106 105 101 100]}
  {:lfsr-size 4 :nbits 107 :taps [107 105 99 98]}
  {:lfsr-size 2 :nbits 108 :taps [108 77]}
  {:lfsr-size 4 :nbits 108 :taps [108 103 97 96]}
  {:lfsr-size 4 :nbits 109 :taps [109 107 105 104]}
  {:lfsr-size 4 :nbits 110 :taps [110 109 106 104]}
  {:lfsr-size 2 :nbits 111 :taps [111 101]}
  {:lfsr-size 4 :nbits 111 :taps [111 109 107 104]}
  {:lfsr-size 4 :nbits 112 :taps [112 108 106 101]}
  {:lfsr-size 2 :nbits 113 :taps [113 104]}
  {:lfsr-size 4 :nbits 113 :taps [113 111 110 108]}
  {:lfsr-size 4 :nbits 114 :taps [114 113 112 103]}
  {:lfsr-size 4 :nbits 115 :taps [115 110 108 107]}
  {:lfsr-size 4 :nbits 116 :taps [116 114 111 110]}
  {:lfsr-size 4 :nbits 117 :taps [117 116 115 112]}
  {:lfsr-size 2 :nbits 118 :taps [118 85]}
  {:lfsr-size 4 :nbits 118 :taps [118 116 113 112]}
  {:lfsr-size 2 :nbits 119 :taps [119 111]}
  {:lfsr-size 4 :nbits 119 :taps [119 116 111 110]}
  {:lfsr-size 4 :nbits 120 :taps [120 118 114 111]}
  {:lfsr-size 2 :nbits 121 :taps [121 103]}
  {:lfsr-size 4 :nbits 121 :taps [121 120 116 113]}
  {:lfsr-size 4 :nbits 122 :taps [122 121 120 116]}
  {:lfsr-size 2 :nbits 123 :taps [123 121]}
  {:lfsr-size 4 :nbits 123 :taps [123 122 119 115]}
  {:lfsr-size 2 :nbits 124 :taps [124 87]}
  {:lfsr-size 4 :nbits 124 :taps [124 119 118 117]}
  {:lfsr-size 4 :nbits 125 :taps [125 120 119 118]}
  {:lfsr-size 4 :nbits 126 :taps [126 124 122 119]}
  {:lfsr-size 2 :nbits 127 :taps [127 126]}
  {:lfsr-size 4 :nbits 127 :taps [127 126 124 120]}
  {:lfsr-size 4 :nbits 128 :taps [128 127 126 121]}
  {:lfsr-size 2 :nbits 129 :taps [129 124]}
  {:lfsr-size 4 :nbits 129 :taps [129 128 125 124]}
  {:lfsr-size 2 :nbits 130 :taps [130 127]}
  {:lfsr-size 4 :nbits 130 :taps [130 129 128 125]}
  {:lfsr-size 4 :nbits 131 :taps [131 129 128 123]}
  {:lfsr-size 2 :nbits 132 :taps [132 103]}
  {:lfsr-size 4 :nbits 132 :taps [132 130 127 123]}
  {:lfsr-size 4 :nbits 133 :taps [133 131 125 124]}
  {:lfsr-size 2 :nbits 134 :taps [134 77]}
  {:lfsr-size 4 :nbits 134 :taps [134 133 129 127]}
  {:lfsr-size 2 :nbits 135 :taps [135 124]}
  {:lfsr-size 4 :nbits 135 :taps [135 132 131 129]}
  {:lfsr-size 4 :nbits 136 :taps [136 134 133 128]}
  {:lfsr-size 2 :nbits 137 :taps [137 116]}
  {:lfsr-size 4 :nbits 137 :taps [137 136 133 126]}
  {:lfsr-size 4 :nbits 138 :taps [138 137 131 130]}
  {:lfsr-size 4 :nbits 139 :taps [139 136 134 131]}
  {:lfsr-size 2 :nbits 140 :taps [140 111]}
  {:lfsr-size 4 :nbits 140 :taps [140 139 136 132]}
  {:lfsr-size 4 :nbits 141 :taps [141 140 135 128]}
  {:lfsr-size 2 :nbits 142 :taps [142 121]}
  {:lfsr-size 4 :nbits 142 :taps [142 141 139 132]}
  {:lfsr-size 4 :nbits 143 :taps [143 141 140 138]}
  {:lfsr-size 4 :nbits 144 :taps [144 142 140 137]}
  {:lfsr-size 2 :nbits 145 :taps [145 93]}
  {:lfsr-size 4 :nbits 145 :taps [145 144 140 139]}
  {:lfsr-size 4 :nbits 146 :taps [146 144 143 141]}
  {:lfsr-size 4 :nbits 147 :taps [147 145 143 136]}
  {:lfsr-size 2 :nbits 148 :taps [148 121]}
  {:lfsr-size 4 :nbits 148 :taps [148 145 143 141]}
  {:lfsr-size 4 :nbits 149 :taps [149 142 140 139]}
  {:lfsr-size 2 :nbits 150 :taps [150 97]}
  {:lfsr-size 4 :nbits 150 :taps [150 148 147 142]}
  {:lfsr-size 2 :nbits 151 :taps [151 148]}
  {:lfsr-size 4 :nbits 151 :taps [151 150 149 148]}
  {:lfsr-size 4 :nbits 152 :taps [152 150 149 146]}
  {:lfsr-size 2 :nbits 153 :taps [153 152]}
  {:lfsr-size 4 :nbits 153 :taps [153 149 148 145]}
  {:lfsr-size 4 :nbits 154 :taps [154 153 149 145]}
  {:lfsr-size 4 :nbits 155 :taps [155 151 150 148]}
  {:lfsr-size 4 :nbits 156 :taps [156 153 151 147]}
  {:lfsr-size 4 :nbits 157 :taps [157 155 152 151]}
  {:lfsr-size 4 :nbits 158 :taps [158 153 152 150]}
  {:lfsr-size 2 :nbits 159 :taps [159 128]}
  {:lfsr-size 4 :nbits 159 :taps [159 156 153 148]}
  {:lfsr-size 4 :nbits 160 :taps [160 158 157 155]}
  {:lfsr-size 2 :nbits 161 :taps [161 143]}
  {:lfsr-size 4 :nbits 161 :taps [161 159 158 155]}
  {:lfsr-size 4 :nbits 162 :taps [162 158 155 154]}
  {:lfsr-size 4 :nbits 163 :taps [163 160 157 156]}
  {:lfsr-size 4 :nbits 164 :taps [164 159 158 152]}
  {:lfsr-size 4 :nbits 165 :taps [165 162 157 156]}
  {:lfsr-size 4 :nbits 166 :taps [166 164 163 156]}
  {:lfsr-size 2 :nbits 167 :taps [167 161]}
  {:lfsr-size 4 :nbits 167 :taps [167 165 163 161]}
  {:lfsr-size 4 :nbits 168 :taps [168 162 159 152]}
  {:lfsr-size 2 :nbits 169 :taps [169 135]}
  {:lfsr-size 4 :nbits 169 :taps [169 164 163 161]}
  {:lfsr-size 2 :nbits 170 :taps [170 147]}
  {:lfsr-size 4 :nbits 170 :taps [170 169 166 161]}
  {:lfsr-size 4 :nbits 171 :taps [171 169 166 165]}
  {:lfsr-size 2 :nbits 172 :taps [172 165]}
  {:lfsr-size 4 :nbits 172 :taps [172 169 165 161]}
  {:lfsr-size 4 :nbits 173 :taps [173 171 168 165]}
  {:lfsr-size 2 :nbits 174 :taps [174 161]}
  {:lfsr-size 4 :nbits 174 :taps [174 169 166 165]}
  {:lfsr-size 2 :nbits 175 :taps [175 169]}
  {:lfsr-size 4 :nbits 175 :taps [175 173 171 169]}
  {:lfsr-size 4 :nbits 176 :taps [176 167 165 164]}
  {:lfsr-size 2 :nbits 177 :taps [177 169]}
  {:lfsr-size 4 :nbits 177 :taps [177 175 174 172]}
  {:lfsr-size 2 :nbits 178 :taps [178 91]}
  {:lfsr-size 4 :nbits 178 :taps [178 176 171 170]}
  {:lfsr-size 4 :nbits 179 :taps [179 178 177 175]}
  {:lfsr-size 4 :nbits 180 :taps [180 173 170 168]}
  {:lfsr-size 4 :nbits 181 :taps [181 180 175 174]}
  {:lfsr-size 4 :nbits 182 :taps [182 181 176 174]}
  {:lfsr-size 2 :nbits 183 :taps [183 127]}
  {:lfsr-size 4 :nbits 183 :taps [183 179 176 175]}
  {:lfsr-size 4 :nbits 184 :taps [184 177 176 175]}
  {:lfsr-size 2 :nbits 185 :taps [185 161]}
  {:lfsr-size 4 :nbits 185 :taps [185 184 182 177]}
  {:lfsr-size 4 :nbits 186 :taps [186 180 178 177]}
  {:lfsr-size 4 :nbits 187 :taps [187 182 181 180]}
  {:lfsr-size 4 :nbits 188 :taps [188 186 183 182]}
  {:lfsr-size 4 :nbits 189 :taps [189 187 184 183]}
  {:lfsr-size 4 :nbits 190 :taps [190 188 184 177]}
  {:lfsr-size 2 :nbits 191 :taps [191 182]}
  {:lfsr-size 4 :nbits 191 :taps [191 187 185 184]}
  {:lfsr-size 4 :nbits 192 :taps [192 190 178 177]}
  {:lfsr-size 2 :nbits 193 :taps [193 178]}
  {:lfsr-size 4 :nbits 193 :taps [193 189 186 184]}
  {:lfsr-size 2 :nbits 194 :taps [194 107]}
  {:lfsr-size 4 :nbits 194 :taps [194 192 191 190]}
  {:lfsr-size 4 :nbits 195 :taps [195 193 192 187]}
  {:lfsr-size 4 :nbits 196 :taps [196 194 187 185]}
  {:lfsr-size 4 :nbits 197 :taps [197 195 193 188]}
  {:lfsr-size 2 :nbits 198 :taps [198 133]}
  {:lfsr-size 4 :nbits 198 :taps [198 193 190 183]}
  {:lfsr-size 2 :nbits 199 :taps [199 165]}
  {:lfsr-size 4 :nbits 199 :taps [199 198 195 190]}
  {:lfsr-size 4 :nbits 200 :taps [200 198 197 195]}
  {:lfsr-size 2 :nbits 201 :taps [201 187]}
  {:lfsr-size 4 :nbits 201 :taps [201 199 198 195]}
  {:lfsr-size 2 :nbits 202 :taps [202 147]}
  {:lfsr-size 4 :nbits 202 :taps [202 198 196 195]}
  {:lfsr-size 4 :nbits 203 :taps [203 202 196 195]}
  {:lfsr-size 4 :nbits 204 :taps [204 201 200 194]}
  {:lfsr-size 4 :nbits 205 :taps [205 203 200 196]}
  {:lfsr-size 4 :nbits 206 :taps [206 201 197 196]}
  {:lfsr-size 2 :nbits 207 :taps [207 164]}
  {:lfsr-size 4 :nbits 207 :taps [207 206 201 198]}
  {:lfsr-size 4 :nbits 208 :taps [208 207 205 199]}
  {:lfsr-size 2 :nbits 209 :taps [209 203]}
  {:lfsr-size 4 :nbits 209 :taps [209 207 206 204]}
  {:lfsr-size 4 :nbits 210 :taps [210 207 206 198]}
  {:lfsr-size 4 :nbits 211 :taps [211 203 201 200]}
  {:lfsr-size 2 :nbits 212 :taps [212 107]}
  {:lfsr-size 4 :nbits 212 :taps [212 209 208 205]}
  {:lfsr-size 4 :nbits 213 :taps [213 211 208 207]}
  {:lfsr-size 4 :nbits 214 :taps [214 213 211 209]}
  {:lfsr-size 2 :nbits 215 :taps [215 192]}
  {:lfsr-size 4 :nbits 215 :taps [215 212 210 209]}
  {:lfsr-size 4 :nbits 216 :taps [216 215 213 209]}
  {:lfsr-size 2 :nbits 217 :taps [217 172]}
  {:lfsr-size 4 :nbits 217 :taps [217 213 212 211]}
  {:lfsr-size 2 :nbits 218 :taps [218 207]}
  {:lfsr-size 4 :nbits 218 :taps [218 217 211 210]}
  {:lfsr-size 4 :nbits 219 :taps [219 218 215 211]}
  {:lfsr-size 4 :nbits 220 :taps [220 211 210 208]}
  {:lfsr-size 4 :nbits 221 :taps [221 219 215 213]}
  {:lfsr-size 4 :nbits 222 :taps [222 220 217 214]}
  {:lfsr-size 2 :nbits 223 :taps [223 190]}
  {:lfsr-size 4 :nbits 223 :taps [223 221 219 218]}
  {:lfsr-size 4 :nbits 224 :taps [224 222 217 212]}
  {:lfsr-size 2 :nbits 225 :taps [225 193]}
  {:lfsr-size 4 :nbits 225 :taps [225 224 220 215]}
  {:lfsr-size 4 :nbits 226 :taps [226 223 219 216]}
  {:lfsr-size 4 :nbits 227 :taps [227 223 218 217]}
  {:lfsr-size 4 :nbits 228 :taps [228 226 217 216]}
  {:lfsr-size 4 :nbits 229 :taps [229 228 225 219]}
  {:lfsr-size 4 :nbits 230 :taps [230 224 223 222]}
  {:lfsr-size 2 :nbits 231 :taps [231 205]}
  {:lfsr-size 4 :nbits 231 :taps [231 229 227 224]}
  {:lfsr-size 4 :nbits 232 :taps [232 228 223 221]}
  {:lfsr-size 2 :nbits 233 :taps [233 159]}
  {:lfsr-size 4 :nbits 233 :taps [233 232 229 224]}
  {:lfsr-size 2 :nbits 234 :taps [234 203]}
  {:lfsr-size 4 :nbits 234 :taps [234 232 225 223]}
  {:lfsr-size 4 :nbits 235 :taps [235 234 229 226]}
  {:lfsr-size 2 :nbits 236 :taps [236 231]}
  {:lfsr-size 4 :nbits 236 :taps [236 229 228 226]}
  {:lfsr-size 4 :nbits 237 :taps [237 236 233 230]}
  {:lfsr-size 4 :nbits 238 :taps [238 237 236 233]}
  {:lfsr-size 2 :nbits 239 :taps [239 203]}
  {:lfsr-size 4 :nbits 239 :taps [239 238 232 227]}
  {:lfsr-size 4 :nbits 240 :taps [240 237 235 232]}
  {:lfsr-size 2 :nbits 241 :taps [241 171]}
  {:lfsr-size 4 :nbits 241 :taps [241 237 233 232]}
  {:lfsr-size 4 :nbits 242 :taps [242 241 236 231]}
  {:lfsr-size 4 :nbits 243 :taps [243 242 238 235]}
  {:lfsr-size 4 :nbits 244 :taps [244 243 240 235]}
  {:lfsr-size 4 :nbits 245 :taps [245 244 241 239]}
  {:lfsr-size 4 :nbits 246 :taps [246 245 244 235]}
  {:lfsr-size 2 :nbits 247 :taps [247 165]}
  {:lfsr-size 4 :nbits 247 :taps [247 245 243 238]}
  {:lfsr-size 4 :nbits 248 :taps [248 238 234 233]}
  {:lfsr-size 2 :nbits 249 :taps [249 163]}
  {:lfsr-size 4 :nbits 249 :taps [249 248 245 242]}
  {:lfsr-size 2 :nbits 250 :taps [250 147]}
  {:lfsr-size 4 :nbits 250 :taps [250 247 245 240]}
  {:lfsr-size 4 :nbits 251 :taps [251 249 247 244]}
  {:lfsr-size 2 :nbits 252 :taps [252 185]}
  {:lfsr-size 4 :nbits 252 :taps [252 251 247 241]}
  {:lfsr-size 4 :nbits 253 :taps [253 252 247 246]}
  {:lfsr-size 4 :nbits 254 :taps [254 253 252 247]}
  {:lfsr-size 2 :nbits 255 :taps [255 203]}
  {:lfsr-size 4 :nbits 255 :taps [255 253 252 250]}
  {:lfsr-size 4 :nbits 256 :taps [256 254 251 246]}
  {:lfsr-size 2 :nbits 257 :taps [257 245]}
  {:lfsr-size 4 :nbits 257 :taps [257 255 251 250]}
  {:lfsr-size 2 :nbits 258 :taps [258 175]}
  {:lfsr-size 4 :nbits 258 :taps [258 254 252 249]}
  {:lfsr-size 4 :nbits 259 :taps [259 257 253 249]}
  {:lfsr-size 4 :nbits 260 :taps [260 253 252 250]}
  {:lfsr-size 4 :nbits 261 :taps [261 257 255 254]}
  {:lfsr-size 4 :nbits 262 :taps [262 258 254 253]}
  {:lfsr-size 2 :nbits 263 :taps [263 170]}
  {:lfsr-size 4 :nbits 263 :taps [263 261 258 252]}
  {:lfsr-size 4 :nbits 264 :taps [264 263 255 254]}
  {:lfsr-size 2 :nbits 265 :taps [265 223]}
  {:lfsr-size 4 :nbits 265 :taps [265 263 262 260]}
  {:lfsr-size 2 :nbits 266 :taps [266 219]}
  {:lfsr-size 4 :nbits 266 :taps [266 265 260 259]}
  {:lfsr-size 4 :nbits 267 :taps [267 264 261 259]}
  {:lfsr-size 2 :nbits 268 :taps [268 243]}
  {:lfsr-size 4 :nbits 268 :taps [268 267 264 258]}
  {:lfsr-size 4 :nbits 269 :taps [269 268 263 262]}
  {:lfsr-size 2 :nbits 270 :taps [270 217]}
  {:lfsr-size 4 :nbits 270 :taps [270 267 263 260]}
  {:lfsr-size 2 :nbits 271 :taps [271 213]}
  {:lfsr-size 4 :nbits 271 :taps [271 265 264 260]}
  {:lfsr-size 4 :nbits 272 :taps [272 270 266 263]}
  {:lfsr-size 2 :nbits 273 :taps [273 250]}
  {:lfsr-size 4 :nbits 273 :taps [273 272 271 266]}
  {:lfsr-size 2 :nbits 274 :taps [274 207]}
  {:lfsr-size 4 :nbits 274 :taps [274 272 267 265]}
  {:lfsr-size 4 :nbits 275 :taps [275 266 265 264]}
  {:lfsr-size 4 :nbits 276 :taps [276 275 273 270]}
  {:lfsr-size 4 :nbits 277 :taps [277 274 271 265]}
  {:lfsr-size 2 :nbits 278 :taps [278 273]}
  {:lfsr-size 4 :nbits 278 :taps [278 277 274 273]}
  {:lfsr-size 2 :nbits 279 :taps [279 274]}
  {:lfsr-size 4 :nbits 279 :taps [279 278 275 274]}
  {:lfsr-size 4 :nbits 280 :taps [280 278 275 271]}
  {:lfsr-size 2 :nbits 281 :taps [281 188]}
  {:lfsr-size 4 :nbits 281 :taps [281 280 277 272]}
  {:lfsr-size 2 :nbits 282 :taps [282 247]}
  {:lfsr-size 4 :nbits 282 :taps [282 278 277 272]}
  {:lfsr-size 4 :nbits 283 :taps [283 278 276 271]}
  {:lfsr-size 2 :nbits 284 :taps [284 165]}
  {:lfsr-size 4 :nbits 284 :taps [284 279 278 276]}
  {:lfsr-size 4 :nbits 285 :taps [285 280 278 275]}
  {:lfsr-size 2 :nbits 286 :taps [286 217]}
  {:lfsr-size 4 :nbits 286 :taps [286 285 276 271]}
  {:lfsr-size 2 :nbits 287 :taps [287 216]}
  {:lfsr-size 4 :nbits 287 :taps [287 285 282 281]}
  {:lfsr-size 4 :nbits 288 :taps [288 287 278 277]}
  {:lfsr-size 2 :nbits 289 :taps [289 268]}
  {:lfsr-size 4 :nbits 289 :taps [289 286 285 277]}
  {:lfsr-size 4 :nbits 290 :taps [290 288 287 285]}
  {:lfsr-size 4 :nbits 291 :taps [291 286 280 279]}
  {:lfsr-size 2 :nbits 292 :taps [292 195]}
  {:lfsr-size 4 :nbits 292 :taps [292 291 289 285]}
  {:lfsr-size 4 :nbits 293 :taps [293 292 287 282]}
  {:lfsr-size 2 :nbits 294 :taps [294 233]}
  {:lfsr-size 4 :nbits 294 :taps [294 292 291 285]}
  {:lfsr-size 2 :nbits 295 :taps [295 247]}
  {:lfsr-size 4 :nbits 295 :taps [295 293 291 290]}
  {:lfsr-size 4 :nbits 296 :taps [296 292 287 285]}
  {:lfsr-size 2 :nbits 297 :taps [297 292]}
  {:lfsr-size 4 :nbits 297 :taps [297 296 293 292]}
  {:lfsr-size 4 :nbits 298 :taps [298 294 290 287]}
  {:lfsr-size 4 :nbits 299 :taps [299 295 293 288]}
  {:lfsr-size 2 :nbits 300 :taps [300 293]}
  {:lfsr-size 4 :nbits 300 :taps [300 290 288 287]}
  {:lfsr-size 4 :nbits 301 :taps [301 299 296 292]}
  {:lfsr-size 2 :nbits 302 :taps [302 261]}
  {:lfsr-size 4 :nbits 302 :taps [302 297 293 290]}
  {:lfsr-size 4 :nbits 303 :taps [303 297 291 290]}
  {:lfsr-size 4 :nbits 304 :taps [304 303 302 293]}
  {:lfsr-size 2 :nbits 305 :taps [305 203]}
  {:lfsr-size 4 :nbits 305 :taps [305 303 299 298]}
  {:lfsr-size 4 :nbits 306 :taps [306 305 303 299]}
  {:lfsr-size 4 :nbits 307 :taps [307 305 303 299]}
  {:lfsr-size 4 :nbits 308 :taps [308 306 299 293]}
  {:lfsr-size 4 :nbits 309 :taps [309 307 302 299]}
  {:lfsr-size 4 :nbits 310 :taps [310 309 305 302]}
  {:lfsr-size 4 :nbits 311 :taps [311 308 306 304]}
  {:lfsr-size 4 :nbits 312 :taps [312 307 302 301]}
  {:lfsr-size 2 :nbits 313 :taps [313 234]}
  {:lfsr-size 4 :nbits 313 :taps [313 312 310 306]}
  {:lfsr-size 2 :nbits 314 :taps [314 299]}
  {:lfsr-size 4 :nbits 314 :taps [314 311 305 300]}
  {:lfsr-size 4 :nbits 315 :taps [315 314 306 305]}
  {:lfsr-size 2 :nbits 316 :taps [316 181]}
  {:lfsr-size 4 :nbits 316 :taps [316 309 305 304]}
  {:lfsr-size 4 :nbits 317 :taps [317 315 313 310]}
  {:lfsr-size 4 :nbits 318 :taps [318 313 312 310]}
  {:lfsr-size 2 :nbits 319 :taps [319 283]}
  {:lfsr-size 4 :nbits 319 :taps [319 318 317 308]}
  {:lfsr-size 4 :nbits 320 :taps [320 319 317 316]}
  {:lfsr-size 2 :nbits 321 :taps [321 290]}
  {:lfsr-size 4 :nbits 321 :taps [321 319 316 314]}
  {:lfsr-size 2 :nbits 322 :taps [322 255]}
  {:lfsr-size 4 :nbits 322 :taps [322 321 320 305]}
  {:lfsr-size 4 :nbits 323 :taps [323 322 320 313]}
  {:lfsr-size 4 :nbits 324 :taps [324 321 320 318]}
  {:lfsr-size 4 :nbits 325 :taps [325 323 320 315]}
  {:lfsr-size 4 :nbits 326 :taps [326 325 323 316]}
  {:lfsr-size 2 :nbits 327 :taps [327 293]}
  {:lfsr-size 4 :nbits 327 :taps [327 325 322 319]}
  {:lfsr-size 4 :nbits 328 :taps [328 323 321 319]}
  {:lfsr-size 2 :nbits 329 :taps [329 279]}
  {:lfsr-size 4 :nbits 329 :taps [329 326 323 321]}
  {:lfsr-size 4 :nbits 330 :taps [330 328 323 322]}
  {:lfsr-size 4 :nbits 331 :taps [331 329 325 321]}
  {:lfsr-size 2 :nbits 332 :taps [332 209]}
  {:lfsr-size 4 :nbits 332 :taps [332 325 321 320]}
  {:lfsr-size 2 :nbits 333 :taps [333 331]}
  {:lfsr-size 4 :nbits 333 :taps [333 331 329 325]}
  {:lfsr-size 4 :nbits 334 :taps [334 333 330 327]}
  {:lfsr-size 4 :nbits 335 :taps [335 333 328 325]}
  {:lfsr-size 4 :nbits 336 :taps [336 335 332 329]}
  {:lfsr-size 2 :nbits 337 :taps [337 282]}
  {:lfsr-size 4 :nbits 337 :taps [337 336 331 327]}
  {:lfsr-size 4 :nbits 338 :taps [338 336 335 332]}
  {:lfsr-size 4 :nbits 339 :taps [339 332 329 323]}
  {:lfsr-size 4 :nbits 340 :taps [340 337 336 329]}
  {:lfsr-size 4 :nbits 341 :taps [341 336 330 327]}
  {:lfsr-size 2 :nbits 342 :taps [342 217]}
  {:lfsr-size 4 :nbits 342 :taps [342 341 340 331]}
  {:lfsr-size 2 :nbits 343 :taps [343 268]}
  {:lfsr-size 4 :nbits 343 :taps [343 338 335 333]}
  {:lfsr-size 4 :nbits 344 :taps [344 338 334 333]}
  {:lfsr-size 2 :nbits 345 :taps [345 323]}
  {:lfsr-size 4 :nbits 345 :taps [345 343 341 337]}
  {:lfsr-size 4 :nbits 346 :taps [346 344 339 335]}
  {:lfsr-size 4 :nbits 347 :taps [347 344 337 336]}
  {:lfsr-size 4 :nbits 348 :taps [348 344 341 340]}
  {:lfsr-size 4 :nbits 349 :taps [349 347 344 343]}
  {:lfsr-size 2 :nbits 350 :taps [350 297]}
  {:lfsr-size 4 :nbits 350 :taps [350 340 337 336]}
  {:lfsr-size 2 :nbits 351 :taps [351 317]}
  {:lfsr-size 4 :nbits 351 :taps [351 348 345 343]}
  {:lfsr-size 4 :nbits 352 :taps [352 346 341 339]}
  {:lfsr-size 2 :nbits 353 :taps [353 284]}
  {:lfsr-size 4 :nbits 353 :taps [353 349 346 344]}
  {:lfsr-size 4 :nbits 354 :taps [354 349 341 340]}
  {:lfsr-size 4 :nbits 355 :taps [355 354 350 349]}
  {:lfsr-size 4 :nbits 356 :taps [356 349 347 346]}
  {:lfsr-size 4 :nbits 357 :taps [357 355 347 346]}
  {:lfsr-size 4 :nbits 358 :taps [358 351 350 344]}
  {:lfsr-size 2 :nbits 359 :taps [359 291]}
  {:lfsr-size 4 :nbits 359 :taps [359 358 352 350]}
  {:lfsr-size 4 :nbits 360 :taps [360 359 335 334]}
  {:lfsr-size 4 :nbits 361 :taps [361 360 357 354]}
  {:lfsr-size 2 :nbits 362 :taps [362 299]}
  {:lfsr-size 4 :nbits 362 :taps [362 360 351 344]}
  {:lfsr-size 4 :nbits 363 :taps [363 362 356 355]}
  {:lfsr-size 2 :nbits 364 :taps [364 297]}
  {:lfsr-size 4 :nbits 364 :taps [364 363 359 352]}
  {:lfsr-size 4 :nbits 365 :taps [365 360 359 356]}
  {:lfsr-size 2 :nbits 366 :taps [366 337]}
  {:lfsr-size 4 :nbits 366 :taps [366 362 359 352]}
  {:lfsr-size 2 :nbits 367 :taps [367 346]}
  {:lfsr-size 4 :nbits 367 :taps [367 365 363 358]}
  {:lfsr-size 4 :nbits 368 :taps [368 361 359 351]}
  {:lfsr-size 2 :nbits 369 :taps [369 278]}
  {:lfsr-size 4 :nbits 369 :taps [369 367 359 358]}
  {:lfsr-size 2 :nbits 370 :taps [370 231]}
  {:lfsr-size 4 :nbits 370 :taps [370 368 367 365]}
  {:lfsr-size 4 :nbits 371 :taps [371 369 368 363]}
  {:lfsr-size 4 :nbits 372 :taps [372 369 365 357]}
  {:lfsr-size 4 :nbits 373 :taps [373 371 366 365]}
  {:lfsr-size 4 :nbits 374 :taps [374 369 368 366]}
  {:lfsr-size 2 :nbits 375 :taps [375 359]}
  {:lfsr-size 4 :nbits 375 :taps [375 374 368 367]}
  {:lfsr-size 4 :nbits 376 :taps [376 371 369 368]}
  {:lfsr-size 2 :nbits 377 :taps [377 336]}
  {:lfsr-size 4 :nbits 377 :taps [377 376 374 369]}
  {:lfsr-size 2 :nbits 378 :taps [378 335]}
  {:lfsr-size 4 :nbits 378 :taps [378 374 365 363]}
  {:lfsr-size 4 :nbits 379 :taps [379 375 370 369]}
  {:lfsr-size 2 :nbits 380 :taps [380 333]}
  {:lfsr-size 4 :nbits 380 :taps [380 377 374 366]}
  {:lfsr-size 4 :nbits 381 :taps [381 380 379 376]}
  {:lfsr-size 2 :nbits 382 :taps [382 301]}
  {:lfsr-size 4 :nbits 382 :taps [382 379 375 364]}
  {:lfsr-size 2 :nbits 383 :taps [383 293]}
  {:lfsr-size 4 :nbits 383 :taps [383 382 378 374]}
  {:lfsr-size 4 :nbits 384 :taps [384 378 369 368]}
  {:lfsr-size 2 :nbits 385 :taps [385 379]}
  {:lfsr-size 4 :nbits 385 :taps [385 383 381 379]}
  {:lfsr-size 2 :nbits 386 :taps [386 303]}
  {:lfsr-size 4 :nbits 386 :taps [386 381 380 376]}
  {:lfsr-size 4 :nbits 387 :taps [387 385 379 378]}
  {:lfsr-size 4 :nbits 388 :taps [388 387 385 374]}
  {:lfsr-size 4 :nbits 389 :taps [389 384 380 379]}
  {:lfsr-size 2 :nbits 390 :taps [390 301]}
  {:lfsr-size 4 :nbits 390 :taps [390 388 380 377]}
  {:lfsr-size 2 :nbits 391 :taps [391 363]}
  {:lfsr-size 4 :nbits 391 :taps [391 390 389 385]}
  {:lfsr-size 4 :nbits 392 :taps [392 386 382 379]}
  {:lfsr-size 2 :nbits 393 :taps [393 386]}
  {:lfsr-size 4 :nbits 393 :taps [393 392 391 386]}
  {:lfsr-size 2 :nbits 394 :taps [394 259]}
  {:lfsr-size 4 :nbits 394 :taps [394 392 387 386]}
  {:lfsr-size 4 :nbits 395 :taps [395 390 389 384]}
  {:lfsr-size 2 :nbits 396 :taps [396 371]}
  {:lfsr-size 4 :nbits 396 :taps [396 392 390 389]}
  {:lfsr-size 4 :nbits 397 :taps [397 392 387 385]}
  {:lfsr-size 4 :nbits 398 :taps [398 393 392 384]}
  {:lfsr-size 2 :nbits 399 :taps [399 313]}
  {:lfsr-size 4 :nbits 399 :taps [399 397 390 388]}
  {:lfsr-size 4 :nbits 400 :taps [400 398 397 395]}
  {:lfsr-size 2 :nbits 401 :taps [401 249]}
  {:lfsr-size 4 :nbits 401 :taps [401 399 392 389]}
  {:lfsr-size 4 :nbits 402 :taps [402 399 398 393]}
  {:lfsr-size 4 :nbits 403 :taps [403 398 395 394]}
  {:lfsr-size 2 :nbits 404 :taps [404 215]}
  {:lfsr-size 4 :nbits 404 :taps [404 400 398 397]}
  {:lfsr-size 4 :nbits 405 :taps [405 398 397 388]}
  {:lfsr-size 2 :nbits 406 :taps [406 249]}
  {:lfsr-size 4 :nbits 406 :taps [406 402 397 393]}
  {:lfsr-size 2 :nbits 407 :taps [407 336]}
  {:lfsr-size 4 :nbits 407 :taps [407 402 400 398]}
  {:lfsr-size 4 :nbits 408 :taps [408 407 403 401]}
  {:lfsr-size 2 :nbits 409 :taps [409 322]}
  {:lfsr-size 4 :nbits 409 :taps [409 406 404 402]}
  {:lfsr-size 4 :nbits 410 :taps [410 407 406 400]}
  {:lfsr-size 4 :nbits 411 :taps [411 408 401 399]}
  {:lfsr-size 2 :nbits 412 :taps [412 265]}
  {:lfsr-size 4 :nbits 412 :taps [412 409 404 401]}
  {:lfsr-size 4 :nbits 413 :taps [413 407 406 403]}
  {:lfsr-size 4 :nbits 414 :taps [414 405 401 398]}
  {:lfsr-size 2 :nbits 415 :taps [415 313]}
  {:lfsr-size 4 :nbits 415 :taps [415 413 411 406]}
  {:lfsr-size 4 :nbits 416 :taps [416 414 411 407]}
  {:lfsr-size 2 :nbits 417 :taps [417 310]}
  {:lfsr-size 4 :nbits 417 :taps [417 416 414 407]}
  {:lfsr-size 4 :nbits 418 :taps [418 417 415 403]}
  {:lfsr-size 4 :nbits 419 :taps [419 415 414 404]}
  {:lfsr-size 4 :nbits 420 :taps [420 412 410 407]}
  {:lfsr-size 4 :nbits 421 :taps [421 419 417 416]}
  {:lfsr-size 2 :nbits 422 :taps [422 273]}
  {:lfsr-size 4 :nbits 422 :taps [422 421 416 412]}
  {:lfsr-size 2 :nbits 423 :taps [423 398]}
  {:lfsr-size 4 :nbits 423 :taps [423 420 418 414]}
  {:lfsr-size 4 :nbits 424 :taps [424 422 417 415]}
  {:lfsr-size 2 :nbits 425 :taps [425 413]}
  {:lfsr-size 4 :nbits 425 :taps [425 422 421 418]}
  {:lfsr-size 4 :nbits 426 :taps [426 415 414 412]}
  {:lfsr-size 4 :nbits 427 :taps [427 422 421 416]}
  {:lfsr-size 2 :nbits 428 :taps [428 323]}
  {:lfsr-size 4 :nbits 428 :taps [428 426 425 417]}
  {:lfsr-size 4 :nbits 429 :taps [429 422 421 419]}
  {:lfsr-size 4 :nbits 430 :taps [430 419 417 415]}
  {:lfsr-size 2 :nbits 431 :taps [431 311]}
  {:lfsr-size 4 :nbits 431 :taps [431 430 428 426]}
  {:lfsr-size 4 :nbits 432 :taps [432 429 428 419]}
  {:lfsr-size 2 :nbits 433 :taps [433 400]}
  {:lfsr-size 4 :nbits 433 :taps [433 430 428 422]}
  {:lfsr-size 4 :nbits 434 :taps [434 429 423 422]}
  {:lfsr-size 4 :nbits 435 :taps [435 430 426 423]}
  {:lfsr-size 2 :nbits 436 :taps [436 271]}
  {:lfsr-size 4 :nbits 436 :taps [436 432 431 430]}
  {:lfsr-size 4 :nbits 437 :taps [437 436 435 431]}
  {:lfsr-size 2 :nbits 438 :taps [438 373]}
  {:lfsr-size 4 :nbits 438 :taps [438 436 432 421]}
  {:lfsr-size 2 :nbits 439 :taps [439 390]}
  {:lfsr-size 4 :nbits 439 :taps [439 437 436 431]}
  {:lfsr-size 4 :nbits 440 :taps [440 439 437 436]}
  {:lfsr-size 2 :nbits 441 :taps [441 410]}
  {:lfsr-size 4 :nbits 441 :taps [441 440 433 430]}
  {:lfsr-size 4 :nbits 442 :taps [442 440 437 435]}
  {:lfsr-size 4 :nbits 443 :taps [443 442 437 433]}
  {:lfsr-size 4 :nbits 444 :taps [444 435 432 431]}
  {:lfsr-size 4 :nbits 445 :taps [445 441 439 438]}
  {:lfsr-size 2 :nbits 446 :taps [446 341]}
  {:lfsr-size 4 :nbits 446 :taps [446 442 439 431]}
  {:lfsr-size 2 :nbits 447 :taps [447 374]}
  {:lfsr-size 4 :nbits 447 :taps [447 446 441 438]}
  {:lfsr-size 4 :nbits 448 :taps [448 444 442 437]}
  {:lfsr-size 2 :nbits 449 :taps [449 315]}
  {:lfsr-size 4 :nbits 449 :taps [449 446 440 438]}
  {:lfsr-size 2 :nbits 450 :taps [450 371]}
  {:lfsr-size 4 :nbits 450 :taps [450 443 438 434]}
  {:lfsr-size 4 :nbits 451 :taps [451 450 441 435]}
  {:lfsr-size 4 :nbits 452 :taps [452 448 447 446]}
  {:lfsr-size 4 :nbits 453 :taps [453 449 447 438]}
  {:lfsr-size 4 :nbits 454 :taps [454 449 445 444]}
  {:lfsr-size 2 :nbits 455 :taps [455 417]}
  {:lfsr-size 4 :nbits 455 :taps [455 453 449 444]}
  {:lfsr-size 4 :nbits 456 :taps [456 454 445 433]}
  {:lfsr-size 2 :nbits 457 :taps [457 441]}
  {:lfsr-size 4 :nbits 457 :taps [457 454 449 446]}
  {:lfsr-size 2 :nbits 458 :taps [458 255]}
  {:lfsr-size 4 :nbits 458 :taps [458 453 448 445]}
  {:lfsr-size 4 :nbits 459 :taps [459 457 454 447]}
  {:lfsr-size 2 :nbits 460 :taps [460 399]}
  {:lfsr-size 4 :nbits 460 :taps [460 459 455 451]}
  {:lfsr-size 4 :nbits 461 :taps [461 460 455 454]}
  {:lfsr-size 2 :nbits 462 :taps [462 389]}
  {:lfsr-size 4 :nbits 462 :taps [462 457 451 450]}
  {:lfsr-size 2 :nbits 463 :taps [463 370]}
  {:lfsr-size 4 :nbits 463 :taps [463 456 455 452]}
  {:lfsr-size 4 :nbits 464 :taps [464 460 455 441]}
  {:lfsr-size 2 :nbits 465 :taps [465 406]}
  {:lfsr-size 4 :nbits 465 :taps [465 463 462 457]}
  {:lfsr-size 4 :nbits 466 :taps [466 460 455 452]}
  {:lfsr-size 4 :nbits 467 :taps [467 466 461 456]}
  {:lfsr-size 4 :nbits 468 :taps [468 464 459 453]}
  {:lfsr-size 4 :nbits 469 :taps [469 467 464 460]}
  {:lfsr-size 2 :nbits 470 :taps [470 321]}
  {:lfsr-size 4 :nbits 470 :taps [470 468 462 461]}
  {:lfsr-size 2 :nbits 471 :taps [471 470]}
  {:lfsr-size 4 :nbits 471 :taps [471 469 468 465]}
  {:lfsr-size 4 :nbits 472 :taps [472 470 469 461]}
  {:lfsr-size 4 :nbits 473 :taps [473 470 467 465]}
  {:lfsr-size 2 :nbits 474 :taps [474 283]}
  {:lfsr-size 4 :nbits 474 :taps [474 465 463 456]}
  {:lfsr-size 4 :nbits 475 :taps [475 471 467 466]}
  {:lfsr-size 2 :nbits 476 :taps [476 461]}
  {:lfsr-size 4 :nbits 476 :taps [476 475 468 466]}
  {:lfsr-size 4 :nbits 477 :taps [477 470 462 461]}
  {:lfsr-size 2 :nbits 478 :taps [478 357]}
  {:lfsr-size 4 :nbits 478 :taps [478 477 474 472]}
  {:lfsr-size 2 :nbits 479 :taps [479 375]}
  {:lfsr-size 4 :nbits 479 :taps [479 475 472 470]}
  {:lfsr-size 4 :nbits 480 :taps [480 473 467 464]}
  {:lfsr-size 2 :nbits 481 :taps [481 343]}
  {:lfsr-size 4 :nbits 481 :taps [481 480 472 471]}
  {:lfsr-size 4 :nbits 482 :taps [482 477 476 473]}
  {:lfsr-size 4 :nbits 483 :taps [483 479 477 474]}
  {:lfsr-size 2 :nbits 484 :taps [484 379]}
  {:lfsr-size 4 :nbits 484 :taps [484 483 482 470]}
  {:lfsr-size 4 :nbits 485 :taps [485 479 469 468]}
  {:lfsr-size 4 :nbits 486 :taps [486 481 478 472]}
  {:lfsr-size 2 :nbits 487 :taps [487 393]}
  {:lfsr-size 4 :nbits 487 :taps [487 485 483 478]}
  {:lfsr-size 4 :nbits 488 :taps [488 487 485 484]}
  {:lfsr-size 2 :nbits 489 :taps [489 406]}
  {:lfsr-size 4 :nbits 489 :taps [489 484 483 480]}
  {:lfsr-size 2 :nbits 490 :taps [490 271]}
  {:lfsr-size 4 :nbits 490 :taps [490 485 483 481]}
  {:lfsr-size 4 :nbits 491 :taps [491 488 485 480]}
  {:lfsr-size 4 :nbits 492 :taps [492 491 485 484]}
  {:lfsr-size 4 :nbits 493 :taps [493 490 488 483]}
  {:lfsr-size 2 :nbits 494 :taps [494 357]}
  {:lfsr-size 4 :nbits 494 :taps [494 493 489 481]}
  {:lfsr-size 2 :nbits 495 :taps [495 419]}
  {:lfsr-size 4 :nbits 495 :taps [495 494 486 480]}
  {:lfsr-size 4 :nbits 496 :taps [496 494 491 480]}
  {:lfsr-size 2 :nbits 497 :taps [497 419]}
  {:lfsr-size 4 :nbits 497 :taps [497 493 488 486]}
  {:lfsr-size 4 :nbits 498 :taps [498 495 489 487]}
  {:lfsr-size 4 :nbits 499 :taps [499 494 493 488]}
  {:lfsr-size 4 :nbits 500 :taps [500 499 494 490]}
  {:lfsr-size 4 :nbits 501 :taps [501 499 497 496]}
  {:lfsr-size 4 :nbits 502 :taps [502 498 497 494]}
  {:lfsr-size 2 :nbits 503 :taps [503 500]}
  {:lfsr-size 4 :nbits 503 :taps [503 502 501 500]}
  {:lfsr-size 4 :nbits 504 :taps [504 502 490 483]}
  {:lfsr-size 2 :nbits 505 :taps [505 349]}
  {:lfsr-size 4 :nbits 505 :taps [505 500 497 493]}
  {:lfsr-size 2 :nbits 506 :taps [506 411]}
  {:lfsr-size 4 :nbits 506 :taps [506 501 494 491]}
  {:lfsr-size 4 :nbits 507 :taps [507 504 501 494]}
  {:lfsr-size 2 :nbits 508 :taps [508 399]}
  {:lfsr-size 4 :nbits 508 :taps [508 505 500 495]}
  {:lfsr-size 4 :nbits 509 :taps [509 506 502 501]}
  {:lfsr-size 4 :nbits 510 :taps [510 501 500 498]}
  {:lfsr-size 2 :nbits 511 :taps [511 501]}
  {:lfsr-size 4 :nbits 511 :taps [511 509 503 501]}
  {:lfsr-size 4 :nbits 512 :taps [512 510 507 504]}
  {:lfsr-size 2 :nbits 513 :taps [513 428]}
  {:lfsr-size 4 :nbits 513 :taps [513 505 503 500]}
  {:lfsr-size 4 :nbits 514 :taps [514 511 509 507]}
  {:lfsr-size 4 :nbits 515 :taps [515 511 508 501]}
  {:lfsr-size 4 :nbits 516 :taps [516 514 511 509]}
  {:lfsr-size 4 :nbits 517 :taps [517 515 507 505]}
  {:lfsr-size 2 :nbits 518 :taps [518 485]}
  {:lfsr-size 4 :nbits 518 :taps [518 516 515 507]}
  {:lfsr-size 2 :nbits 519 :taps [519 440]}
  {:lfsr-size 4 :nbits 519 :taps [519 517 511 507]}
  {:lfsr-size 4 :nbits 520 :taps [520 509 507 503]}
  {:lfsr-size 2 :nbits 521 :taps [521 489]}
  {:lfsr-size 4 :nbits 521 :taps [521 519 514 512]}
  {:lfsr-size 4 :nbits 522 :taps [522 518 509 507]}
  {:lfsr-size 4 :nbits 523 :taps [523 521 517 510]}
  {:lfsr-size 2 :nbits 524 :taps [524 357]}
  {:lfsr-size 4 :nbits 524 :taps [524 523 519 515]}
  {:lfsr-size 4 :nbits 525 :taps [525 524 521 519]}
  {:lfsr-size 4 :nbits 526 :taps [526 525 521 517]}
  {:lfsr-size 2 :nbits 527 :taps [527 480]}
  {:lfsr-size 4 :nbits 527 :taps [527 526 520 518]}
  {:lfsr-size 4 :nbits 528 :taps [528 526 522 517]}
  {:lfsr-size 2 :nbits 529 :taps [529 487]}
  {:lfsr-size 4 :nbits 529 :taps [529 528 525 522]}
  {:lfsr-size 4 :nbits 530 :taps [530 527 523 520]}
  {:lfsr-size 4 :nbits 531 :taps [531 529 525 519]}
  {:lfsr-size 2 :nbits 532 :taps [532 531]}
  {:lfsr-size 4 :nbits 532 :taps [532 529 528 522]}
  {:lfsr-size 4 :nbits 533 :taps [533 531 530 529]}
  {:lfsr-size 4 :nbits 534 :taps [534 533 529 527]}
  {:lfsr-size 4 :nbits 535 :taps [535 533 529 527]}
  {:lfsr-size 4 :nbits 536 :taps [536 533 531 529]}
  {:lfsr-size 2 :nbits 537 :taps [537 443]}
  {:lfsr-size 4 :nbits 537 :taps [537 536 535 527]}
  {:lfsr-size 4 :nbits 538 :taps [538 537 536 533]}
  {:lfsr-size 4 :nbits 539 :taps [539 535 534 529]}
  {:lfsr-size 2 :nbits 540 :taps [540 361]}
  {:lfsr-size 4 :nbits 540 :taps [540 537 534 529]}
  {:lfsr-size 4 :nbits 541 :taps [541 537 531 528]}
  {:lfsr-size 4 :nbits 542 :taps [542 540 539 533]}
  {:lfsr-size 2 :nbits 543 :taps [543 527]}
  {:lfsr-size 4 :nbits 543 :taps [543 538 536 532]}
  {:lfsr-size 4 :nbits 544 :taps [544 538 535 531]}
  {:lfsr-size 2 :nbits 545 :taps [545 423]}
  {:lfsr-size 4 :nbits 545 :taps [545 539 537 532]}
  {:lfsr-size 4 :nbits 546 :taps [546 545 544 538]}
  {:lfsr-size 4 :nbits 547 :taps [547 543 540 534]}
  {:lfsr-size 4 :nbits 548 :taps [548 545 543 538]}
  {:lfsr-size 4 :nbits 549 :taps [549 546 545 533]}
  {:lfsr-size 2 :nbits 550 :taps [550 357]}
  {:lfsr-size 4 :nbits 550 :taps [550 546 533 529]}
  {:lfsr-size 2 :nbits 551 :taps [551 416]}
  {:lfsr-size 4 :nbits 551 :taps [551 550 547 542]}
  {:lfsr-size 4 :nbits 552 :taps [552 550 547 532]}
  {:lfsr-size 2 :nbits 553 :taps [553 514]}
  {:lfsr-size 4 :nbits 553 :taps [553 550 549 542]}
  {:lfsr-size 4 :nbits 554 :taps [554 551 546 543]}
  {:lfsr-size 4 :nbits 555 :taps [555 551 546 545]}
  {:lfsr-size 2 :nbits 556 :taps [556 403]}
  {:lfsr-size 4 :nbits 556 :taps [556 549 546 540]}
  {:lfsr-size 4 :nbits 557 :taps [557 552 551 550]}
  {:lfsr-size 4 :nbits 558 :taps [558 553 549 544]}
  {:lfsr-size 2 :nbits 559 :taps [559 525]}
  {:lfsr-size 4 :nbits 559 :taps [559 557 552 550]}
  {:lfsr-size 4 :nbits 560 :taps [560 554 551 549]}
  {:lfsr-size 2 :nbits 561 :taps [561 490]}
  {:lfsr-size 4 :nbits 561 :taps [561 558 552 550]}
  {:lfsr-size 4 :nbits 562 :taps [562 560 558 551]}
  {:lfsr-size 4 :nbits 563 :taps [563 561 554 549]}
  {:lfsr-size 2 :nbits 564 :taps [564 401]}
  {:lfsr-size 4 :nbits 564 :taps [564 563 561 558]}
  {:lfsr-size 4 :nbits 565 :taps [565 564 559 554]}
  {:lfsr-size 2 :nbits 566 :taps [566 413]}
  {:lfsr-size 4 :nbits 566 :taps [566 564 561 560]}
  {:lfsr-size 2 :nbits 567 :taps [567 424]}
  {:lfsr-size 4 :nbits 567 :taps [567 563 557 556]}
  {:lfsr-size 4 :nbits 568 :taps [568 558 557 551]}
  {:lfsr-size 2 :nbits 569 :taps [569 492]}
  {:lfsr-size 4 :nbits 569 :taps [569 568 559 557]}
  {:lfsr-size 2 :nbits 570 :taps [570 503]}
  {:lfsr-size 4 :nbits 570 :taps [570 563 558 552]}
  {:lfsr-size 4 :nbits 571 :taps [571 569 566 561]}
  {:lfsr-size 4 :nbits 572 :taps [572 571 564 560]}
  {:lfsr-size 4 :nbits 573 :taps [573 569 567 563]}
  {:lfsr-size 2 :nbits 574 :taps [574 561]}
  {:lfsr-size 4 :nbits 574 :taps [574 569 565 560]}
  {:lfsr-size 2 :nbits 575 :taps [575 429]}
  {:lfsr-size 4 :nbits 575 :taps [575 572 570 569]}
  {:lfsr-size 4 :nbits 576 :taps [576 573 572 563]}
  {:lfsr-size 2 :nbits 577 :taps [577 552]}
  {:lfsr-size 4 :nbits 577 :taps [577 575 574 569]}
  {:lfsr-size 4 :nbits 578 :taps [578 562 556 555]}
  {:lfsr-size 4 :nbits 579 :taps [579 572 570 567]}
  {:lfsr-size 4 :nbits 580 :taps [580 579 576 574]}
  {:lfsr-size 4 :nbits 581 :taps [581 575 574 568]}
  {:lfsr-size 2 :nbits 582 :taps [582 497]}
  {:lfsr-size 4 :nbits 582 :taps [582 579 576 571]}
  {:lfsr-size 2 :nbits 583 :taps [583 453]}
  {:lfsr-size 4 :nbits 583 :taps [583 581 577 575]}
  {:lfsr-size 4 :nbits 584 :taps [584 581 571 570]}
  {:lfsr-size 2 :nbits 585 :taps [585 464]}
  {:lfsr-size 4 :nbits 585 :taps [585 583 582 577]}
  {:lfsr-size 4 :nbits 586 :taps [586 584 581 579]}
  {:lfsr-size 4 :nbits 587 :taps [587 586 581 576]}
  {:lfsr-size 2 :nbits 588 :taps [588 437]}
  {:lfsr-size 4 :nbits 588 :taps [588 577 572 571]}
  {:lfsr-size 4 :nbits 589 :taps [589 586 585 579]}
  {:lfsr-size 2 :nbits 590 :taps [590 497]}
  {:lfsr-size 4 :nbits 590 :taps [590 588 587 578]}
  {:lfsr-size 4 :nbits 591 :taps [591 587 585 582]}
  {:lfsr-size 4 :nbits 592 :taps [592 591 573 568]}
  {:lfsr-size 2 :nbits 593 :taps [593 507]}
  {:lfsr-size 4 :nbits 593 :taps [593 588 585 584]}
  {:lfsr-size 2 :nbits 594 :taps [594 575]}
  {:lfsr-size 4 :nbits 594 :taps [594 586 584 583]}
  {:lfsr-size 4 :nbits 595 :taps [595 594 593 586]}
  {:lfsr-size 4 :nbits 596 :taps [596 592 591 590]}
  {:lfsr-size 4 :nbits 597 :taps [597 588 585 583]}
  {:lfsr-size 4 :nbits 598 :taps [598 597 592 591]}
  {:lfsr-size 2 :nbits 599 :taps [599 569]}
  {:lfsr-size 4 :nbits 599 :taps [599 593 591 590]}
  {:lfsr-size 4 :nbits 600 :taps [600 599 590 589]}
  {:lfsr-size 2 :nbits 601 :taps [601 400]}
  {:lfsr-size 4 :nbits 601 :taps [601 600 597 589]}
  {:lfsr-size 4 :nbits 602 :taps [602 596 594 591]}
  {:lfsr-size 4 :nbits 603 :taps [603 600 599 597]}
  {:lfsr-size 4 :nbits 604 :taps [604 600 598 589]}
  {:lfsr-size 4 :nbits 605 :taps [605 600 598 595]}
  {:lfsr-size 4 :nbits 606 :taps [606 602 599 591]}
  {:lfsr-size 2 :nbits 607 :taps [607 502]}
  {:lfsr-size 4 :nbits 607 :taps [607 600 598 595]}
  {:lfsr-size 4 :nbits 608 :taps [608 606 602 585]}
  {:lfsr-size 2 :nbits 609 :taps [609 578]}
  {:lfsr-size 4 :nbits 609 :taps [609 601 600 597]}
  {:lfsr-size 2 :nbits 610 :taps [610 483]}
  {:lfsr-size 4 :nbits 610 :taps [610 602 600 599]}
  {:lfsr-size 4 :nbits 611 :taps [611 609 607 601]}
  {:lfsr-size 4 :nbits 612 :taps [612 607 602 598]}
  {:lfsr-size 4 :nbits 613 :taps [613 609 603 594]}
  {:lfsr-size 4 :nbits 614 :taps [614 613 612 607]}
  {:lfsr-size 2 :nbits 615 :taps [615 404]}
  {:lfsr-size 4 :nbits 615 :taps [615 614 609 608]}
  {:lfsr-size 4 :nbits 616 :taps [616 614 602 597]}
  {:lfsr-size 2 :nbits 617 :taps [617 417]}
  {:lfsr-size 4 :nbits 617 :taps [617 612 608 607]}
  {:lfsr-size 4 :nbits 618 :taps [618 615 604 598]}
  {:lfsr-size 4 :nbits 619 :taps [619 614 611 610]}
  {:lfsr-size 4 :nbits 620 :taps [620 619 618 611]}
  {:lfsr-size 4 :nbits 621 :taps [621 616 615 609]}
  {:lfsr-size 2 :nbits 622 :taps [622 325]}
  {:lfsr-size 4 :nbits 622 :taps [622 612 610 605]}
  {:lfsr-size 2 :nbits 623 :taps [623 555]}
  {:lfsr-size 4 :nbits 623 :taps [623 614 613 612]}
  {:lfsr-size 4 :nbits 624 :taps [624 617 615 612]}
  {:lfsr-size 2 :nbits 625 :taps [625 492]}
  {:lfsr-size 4 :nbits 625 :taps [625 620 617 613]}
  {:lfsr-size 4 :nbits 626 :taps [626 623 621 613]}
  {:lfsr-size 4 :nbits 627 :taps [627 622 617 613]}
  {:lfsr-size 2 :nbits 628 :taps [628 405]}
  {:lfsr-size 4 :nbits 628 :taps [628 626 617 616]}
  {:lfsr-size 4 :nbits 629 :taps [629 627 624 623]}
  {:lfsr-size 4 :nbits 630 :taps [630 628 626 623]}
  {:lfsr-size 2 :nbits 631 :taps [631 324]}
  {:lfsr-size 4 :nbits 631 :taps [631 625 623 617]}
  {:lfsr-size 4 :nbits 632 :taps [632 629 619 613]}
  {:lfsr-size 2 :nbits 633 :taps [633 532]}
  {:lfsr-size 4 :nbits 633 :taps [633 632 631 626]}
  {:lfsr-size 2 :nbits 634 :taps [634 319]}
  {:lfsr-size 4 :nbits 634 :taps [634 631 629 627]}
  {:lfsr-size 4 :nbits 635 :taps [635 631 625 621]}
  {:lfsr-size 4 :nbits 636 :taps [636 632 628 623]}
  {:lfsr-size 4 :nbits 637 :taps [637 636 628 623]}
  {:lfsr-size 4 :nbits 638 :taps [638 637 633 632]}
  {:lfsr-size 2 :nbits 639 :taps [639 623]}
  {:lfsr-size 4 :nbits 639 :taps [639 636 635 629]}
  {:lfsr-size 4 :nbits 640 :taps [640 638 637 626]}
  {:lfsr-size 2 :nbits 641 :taps [641 630]}
  {:lfsr-size 4 :nbits 641 :taps [641 640 636 622]}
  {:lfsr-size 2 :nbits 642 :taps [642 523]}
  {:lfsr-size 4 :nbits 642 :taps [642 636 633 632]}
  {:lfsr-size 4 :nbits 643 :taps [643 641 640 632]}
  {:lfsr-size 4 :nbits 644 :taps [644 634 633 632]}
  {:lfsr-size 4 :nbits 645 :taps [645 641 637 634]}
  {:lfsr-size 2 :nbits 646 :taps [646 397]}
  {:lfsr-size 4 :nbits 646 :taps [646 635 634 633]}
  {:lfsr-size 2 :nbits 647 :taps [647 642]}
  {:lfsr-size 4 :nbits 647 :taps [647 646 643 642]}
  {:lfsr-size 4 :nbits 648 :taps [648 647 626 625]}
  {:lfsr-size 2 :nbits 649 :taps [649 612]}
  {:lfsr-size 4 :nbits 649 :taps [649 648 644 638]}
  {:lfsr-size 2 :nbits 650 :taps [650 647]}
  {:lfsr-size 4 :nbits 650 :taps [650 644 635 632]}
  {:lfsr-size 4 :nbits 651 :taps [651 646 638 637]}
  {:lfsr-size 2 :nbits 652 :taps [652 559]}
  {:lfsr-size 4 :nbits 652 :taps [652 647 643 641]}
  {:lfsr-size 4 :nbits 653 :taps [653 646 645 643]}
  {:lfsr-size 4 :nbits 654 :taps [654 649 643 640]}
  {:lfsr-size 2 :nbits 655 :taps [655 567]}
  {:lfsr-size 4 :nbits 655 :taps [655 653 639 638]}
  {:lfsr-size 4 :nbits 656 :taps [656 646 638 637]}
  {:lfsr-size 2 :nbits 657 :taps [657 619]}
  {:lfsr-size 4 :nbits 657 :taps [657 656 650 649]}
  {:lfsr-size 2 :nbits 658 :taps [658 603]}
  {:lfsr-size 4 :nbits 658 :taps [658 651 648 646]}
  {:lfsr-size 4 :nbits 659 :taps [659 657 655 644]}
  {:lfsr-size 4 :nbits 660 :taps [660 657 656 648]}
  {:lfsr-size 4 :nbits 661 :taps [661 657 650 649]}
  {:lfsr-size 2 :nbits 662 :taps [662 365]}
  {:lfsr-size 4 :nbits 662 :taps [662 659 656 650]}
  {:lfsr-size 2 :nbits 663 :taps [663 406]}
  {:lfsr-size 4 :nbits 663 :taps [663 655 652 649]}
  {:lfsr-size 4 :nbits 664 :taps [664 662 660 649]}
  {:lfsr-size 2 :nbits 665 :taps [665 632]}
  {:lfsr-size 4 :nbits 665 :taps [665 661 659 654]}
  {:lfsr-size 4 :nbits 666 :taps [666 664 659 656]}
  {:lfsr-size 4 :nbits 667 :taps [667 664 660 649]}
  {:lfsr-size 4 :nbits 668 :taps [668 658 656 651]}
  {:lfsr-size 4 :nbits 669 :taps [669 667 665 664]}
  {:lfsr-size 2 :nbits 670 :taps [670 517]}
  {:lfsr-size 4 :nbits 670 :taps [670 669 665 664]}
  {:lfsr-size 2 :nbits 671 :taps [671 656]}
  {:lfsr-size 4 :nbits 671 :taps [671 669 665 662]}
  {:lfsr-size 4 :nbits 672 :taps [672 667 666 661]}
  {:lfsr-size 2 :nbits 673 :taps [673 645]}
  {:lfsr-size 4 :nbits 673 :taps [673 666 664 663]}
  {:lfsr-size 4 :nbits 674 :taps [674 671 665 660]}
  {:lfsr-size 4 :nbits 675 :taps [675 674 672 669]}
  {:lfsr-size 2 :nbits 676 :taps [676 435]}
  {:lfsr-size 4 :nbits 676 :taps [676 675 671 664]}
  {:lfsr-size 4 :nbits 677 :taps [677 674 673 669]}
  {:lfsr-size 4 :nbits 678 :taps [678 675 673 663]}
  {:lfsr-size 2 :nbits 679 :taps [679 613]}
  {:lfsr-size 4 :nbits 679 :taps [679 676 667 661]}
  {:lfsr-size 4 :nbits 680 :taps [680 679 650 645]}
  {:lfsr-size 4 :nbits 681 :taps [681 678 672 670]}
  {:lfsr-size 4 :nbits 682 :taps [682 681 679 675]}
  {:lfsr-size 4 :nbits 683 :taps [683 682 677 672]}
  {:lfsr-size 4 :nbits 684 :taps [684 681 671 666]}
  {:lfsr-size 4 :nbits 685 :taps [685 684 682 681]}
  {:lfsr-size 2 :nbits 686 :taps [686 489]}
  {:lfsr-size 4 :nbits 686 :taps [686 684 674 673]}
  {:lfsr-size 2 :nbits 687 :taps [687 674]}
  {:lfsr-size 4 :nbits 687 :taps [687 682 675 673]}
  {:lfsr-size 4 :nbits 688 :taps [688 682 674 669]}
  {:lfsr-size 2 :nbits 689 :taps [689 675]}
  {:lfsr-size 4 :nbits 689 :taps [689 686 683 681]}
  {:lfsr-size 4 :nbits 690 :taps [690 687 683 680]}
  {:lfsr-size 4 :nbits 691 :taps [691 689 685 678]}
  {:lfsr-size 2 :nbits 692 :taps [692 393]}
  {:lfsr-size 4 :nbits 692 :taps [692 687 686 678]}
  {:lfsr-size 4 :nbits 693 :taps [693 691 685 678]}
  {:lfsr-size 4 :nbits 694 :taps [694 691 681 677]}
  {:lfsr-size 2 :nbits 695 :taps [695 483]}
  {:lfsr-size 4 :nbits 695 :taps [695 694 691 686]}
  {:lfsr-size 4 :nbits 696 :taps [696 694 686 673]}
  {:lfsr-size 2 :nbits 697 :taps [697 430]}
  {:lfsr-size 4 :nbits 697 :taps [697 689 685 681]}
  {:lfsr-size 2 :nbits 698 :taps [698 483]}
  {:lfsr-size 4 :nbits 698 :taps [698 690 689 688]}
  {:lfsr-size 4 :nbits 699 :taps [699 698 689 684]}
  {:lfsr-size 4 :nbits 700 :taps [700 698 695 694]}
  {:lfsr-size 4 :nbits 701 :taps [701 699 697 685]}
  {:lfsr-size 2 :nbits 702 :taps [702 665]}
  {:lfsr-size 4 :nbits 702 :taps [702 701 699 695]}
  {:lfsr-size 4 :nbits 703 :taps [703 702 696 691]}
  {:lfsr-size 4 :nbits 704 :taps [704 701 699 692]}
  {:lfsr-size 2 :nbits 705 :taps [705 686]}
  {:lfsr-size 4 :nbits 705 :taps [705 704 698 697]}
  {:lfsr-size 4 :nbits 706 :taps [706 697 695 692]}
  {:lfsr-size 4 :nbits 707 :taps [707 702 699 692]}
  {:lfsr-size 2 :nbits 708 :taps [708 421]}
  {:lfsr-size 4 :nbits 708 :taps [708 706 704 703]}
  {:lfsr-size 4 :nbits 709 :taps [709 708 706 705]}
  {:lfsr-size 4 :nbits 710 :taps [710 709 696 695]}
  {:lfsr-size 2 :nbits 711 :taps [711 619]}
  {:lfsr-size 4 :nbits 711 :taps [711 704 703 700]}
  {:lfsr-size 4 :nbits 712 :taps [712 709 708 707]}
  {:lfsr-size 2 :nbits 713 :taps [713 672]}
  {:lfsr-size 4 :nbits 713 :taps [713 706 703 696]}
  {:lfsr-size 2 :nbits 714 :taps [714 691]}
  {:lfsr-size 4 :nbits 714 :taps [714 709 707 701]}
  {:lfsr-size 4 :nbits 715 :taps [715 714 711 708]}
  {:lfsr-size 2 :nbits 716 :taps [716 533]}
  {:lfsr-size 4 :nbits 716 :taps [716 706 705 704]}
  {:lfsr-size 4 :nbits 717 :taps [717 716 710 701]}
  {:lfsr-size 4 :nbits 718 :taps [718 717 716 713]}
  {:lfsr-size 2 :nbits 719 :taps [719 569]}
  {:lfsr-size 4 :nbits 719 :taps [719 711 710 707]}
  {:lfsr-size 4 :nbits 720 :taps [720 718 712 709]}
  {:lfsr-size 2 :nbits 721 :taps [721 712]}
  {:lfsr-size 4 :nbits 721 :taps [721 720 713 712]}
  {:lfsr-size 2 :nbits 722 :taps [722 491]}
  {:lfsr-size 4 :nbits 722 :taps [722 721 718 707]}
  {:lfsr-size 4 :nbits 723 :taps [723 717 710 707]}
  {:lfsr-size 4 :nbits 724 :taps [724 719 716 711]}
  {:lfsr-size 4 :nbits 725 :taps [725 720 719 716]}
  {:lfsr-size 2 :nbits 726 :taps [726 721]}
  {:lfsr-size 4 :nbits 726 :taps [726 725 722 721]}
  {:lfsr-size 2 :nbits 727 :taps [727 547]}
  {:lfsr-size 4 :nbits 727 :taps [727 721 719 716]}
  {:lfsr-size 4 :nbits 728 :taps [728 726 725 724]}
  {:lfsr-size 2 :nbits 729 :taps [729 671]}
  {:lfsr-size 4 :nbits 729 :taps [729 726 724 718]}
  {:lfsr-size 2 :nbits 730 :taps [730 583]}
  {:lfsr-size 4 :nbits 730 :taps [730 726 715 711]}
  {:lfsr-size 4 :nbits 731 :taps [731 729 725 723]}
  {:lfsr-size 4 :nbits 732 :taps [732 729 728 725]}
  {:lfsr-size 4 :nbits 733 :taps [733 731 726 725]}
  {:lfsr-size 4 :nbits 734 :taps [734 724 721 720]}
  {:lfsr-size 2 :nbits 735 :taps [735 691]}
  {:lfsr-size 4 :nbits 735 :taps [735 733 728 727]}
  {:lfsr-size 4 :nbits 736 :taps [736 730 728 723]}
  {:lfsr-size 2 :nbits 737 :taps [737 732]}
  {:lfsr-size 4 :nbits 737 :taps [737 736 733 732]}
  {:lfsr-size 2 :nbits 738 :taps [738 391]}
  {:lfsr-size 4 :nbits 738 :taps [738 730 729 727]}
  {:lfsr-size 4 :nbits 739 :taps [739 731 723 721]}
  {:lfsr-size 2 :nbits 740 :taps [740 587]}
  {:lfsr-size 4 :nbits 740 :taps [740 737 728 716]}
  {:lfsr-size 4 :nbits 741 :taps [741 738 733 732]}
  {:lfsr-size 4 :nbits 742 :taps [742 741 738 730]}
  {:lfsr-size 2 :nbits 743 :taps [743 653]}
  {:lfsr-size 4 :nbits 743 :taps [743 742 731 730]}
  {:lfsr-size 4 :nbits 744 :taps [744 743 733 731]}
  {:lfsr-size 2 :nbits 745 :taps [745 487]}
  {:lfsr-size 4 :nbits 745 :taps [745 740 738 737]}
  {:lfsr-size 2 :nbits 746 :taps [746 395]}
  {:lfsr-size 4 :nbits 746 :taps [746 738 733 728]}
  {:lfsr-size 4 :nbits 747 :taps [747 743 741 737]}
  {:lfsr-size 4 :nbits 748 :taps [748 744 743 733]}
  {:lfsr-size 4 :nbits 749 :taps [749 748 743 742]}
  {:lfsr-size 4 :nbits 750 :taps [750 746 741 734]}
  {:lfsr-size 2 :nbits 751 :taps [751 733]}
  {:lfsr-size 4 :nbits 751 :taps [751 750 748 740]}
  {:lfsr-size 4 :nbits 752 :taps [752 749 732 731]}
  {:lfsr-size 2 :nbits 753 :taps [753 595]}
  {:lfsr-size 4 :nbits 753 :taps [753 748 745 740]}
  {:lfsr-size 2 :nbits 754 :taps [754 735]}
  {:lfsr-size 4 :nbits 754 :taps [754 742 740 735]}
  {:lfsr-size 4 :nbits 755 :taps [755 754 745 743]}
  {:lfsr-size 2 :nbits 756 :taps [756 407]}
  {:lfsr-size 4 :nbits 756 :taps [756 755 747 740]}
  {:lfsr-size 4 :nbits 757 :taps [757 756 751 750]}
  {:lfsr-size 4 :nbits 758 :taps [758 757 746 741]}
  {:lfsr-size 2 :nbits 759 :taps [759 661]}
  {:lfsr-size 4 :nbits 759 :taps [759 757 756 750]}
  {:lfsr-size 4 :nbits 760 :taps [760 757 747 734]}
  {:lfsr-size 2 :nbits 761 :taps [761 758]}
  {:lfsr-size 4 :nbits 761 :taps [761 760 759 758]}
  {:lfsr-size 2 :nbits 762 :taps [762 679]}
  {:lfsr-size 4 :nbits 762 :taps [762 761 755 745]}
  {:lfsr-size 4 :nbits 763 :taps [763 754 749 747]}
  {:lfsr-size 4 :nbits 764 :taps [764 761 759 758]}
  {:lfsr-size 4 :nbits 765 :taps [765 760 755 754]}
  {:lfsr-size 4 :nbits 766 :taps [766 757 747 744]}
  {:lfsr-size 2 :nbits 767 :taps [767 599]}
  {:lfsr-size 4 :nbits 767 :taps [767 763 760 759]}
  {:lfsr-size 4 :nbits 768 :taps [768 764 751 749]}
  {:lfsr-size 2 :nbits 769 :taps [769 649]}
  {:lfsr-size 4 :nbits 769 :taps [769 763 762 760]}
  {:lfsr-size 4 :nbits 770 :taps [770 768 765 756]}
  {:lfsr-size 4 :nbits 771 :taps [771 765 756 754]}
  {:lfsr-size 2 :nbits 772 :taps [772 765]}
  {:lfsr-size 4 :nbits 772 :taps [772 767 766 764]}
  {:lfsr-size 4 :nbits 773 :taps [773 767 765 763]}
  {:lfsr-size 2 :nbits 774 :taps [774 589]}
  {:lfsr-size 4 :nbits 774 :taps [774 767 760 758]}
  {:lfsr-size 2 :nbits 775 :taps [775 408]}
  {:lfsr-size 4 :nbits 775 :taps [775 771 769 768]}
  {:lfsr-size 4 :nbits 776 :taps [776 773 764 759]}
  {:lfsr-size 2 :nbits 777 :taps [777 748]}
  {:lfsr-size 4 :nbits 777 :taps [777 776 767 761]}
  {:lfsr-size 2 :nbits 778 :taps [778 403]}
  {:lfsr-size 4 :nbits 778 :taps [778 775 762 759]}
  {:lfsr-size 4 :nbits 779 :taps [779 776 771 769]}
  {:lfsr-size 4 :nbits 780 :taps [780 775 772 764]}
  {:lfsr-size 4 :nbits 781 :taps [781 779 765 764]}
  {:lfsr-size 2 :nbits 782 :taps [782 453]}
  {:lfsr-size 4 :nbits 782 :taps [782 780 779 773]}
  {:lfsr-size 2 :nbits 783 :taps [783 715]}
  {:lfsr-size 4 :nbits 783 :taps [783 782 776 773]}
  {:lfsr-size 4 :nbits 784 :taps [784 778 775 771]}
  {:lfsr-size 2 :nbits 785 :taps [785 693]}
  {:lfsr-size 4 :nbits 785 :taps [785 780 776 775]}
  {:lfsr-size 4 :nbits 786 :taps [786 782 780 771]}
  {:lfsr-size 4 :nbits 1024 :taps [1024 1015 1002 1001]}
  {:lfsr-size 4 :nbits 2048 :taps [2048 2035 2034 2029]}
  {:lfsr-size 4 :nbits 4096 :taps [4096 4095 4081 4069]}])


(defn lfsr-for-bit-size [lfsr-size nbits]
  "lfsr-size is the number of taps, nbits is the desired bit width of the LFSR."
  (first (filter #(and (= lfsr-size (:lfsr-size %)) (= nbits (:nbits %))) *maximal-length-taps*)))


(comment
  (require 'clojure.contrib.str-utils)
  (printf "const char *lfsr_specs[] = {\n%s\n};"
          (clojure.contrib.str-utils/str-join
           "\n"
           (map (fn [m]
                  (format "  \"%s %s\","
                          (:nbits m)
                          (:taps m))) *maximal-length-taps*)))


)
