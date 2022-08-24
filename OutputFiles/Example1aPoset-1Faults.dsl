// Course CS2820 Authentication Tag: nP0n3GdYUOs2pKRqyXcmki9vW+6/TRsc3h8QTJnBXWqDBxcBleE1JISR36VQCTZOOh2J0NU3BELij3rrPYAe95j0CxFivhmqSqHpj5t2W7Y=
WARP system for graph Example1A created with the following parameters:
Scheduler Name:	Poset
numFaults:	1
Time Slot	A	B	C
0	wait(#0)	if !has(F0: A -> B) pull(F0: A -> B, #0)	sleep
1	wait(#0)	if !has(F0: A -> B) pull(F0: A -> B, #0) else if has(F0: B -> C) push(F0: B -> C, #0)	wait(#0)
2	sleep	if has(F0: B -> C) push(F0: B -> C, #0)	wait(#0)
3	sleep	if !has(F1: C -> B) pull(F1: C -> B, #0)	wait(#0)
4	wait(#0)	if !has(F1: C -> B) pull(F1: C -> B, #0) else if has(F1: B -> A) push(F1: B -> A, #0)	wait(#0)
5	wait(#0)	if has(F1: B -> A) push(F1: B -> A, #0)	sleep
6	sleep	sleep	sleep
7	sleep	sleep	sleep
8	sleep	sleep	sleep
9	sleep	sleep	sleep
10	wait(#0)	if !has(F0: A -> B) pull(F0: A -> B, #0)	sleep
11	wait(#0)	if !has(F0: A -> B) pull(F0: A -> B, #0) else if has(F0: B -> C) push(F0: B -> C, #0)	wait(#0)
12	sleep	if has(F0: B -> C) push(F0: B -> C, #0)	wait(#0)
13	sleep	sleep	sleep
14	sleep	sleep	sleep
15	sleep	sleep	sleep
16	sleep	sleep	sleep
17	sleep	sleep	sleep
18	sleep	sleep	sleep
19	sleep	sleep	sleep
// All flows meet their deadlines
