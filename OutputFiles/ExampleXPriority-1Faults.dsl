// Course CS2820 Authentication Tag: Wl5e37fMZ3O42KY5VktVlp2eghVPBQp01eXoZmAg2Fs58tsBlsCwgk8qofpkn6eA9Vt5AOB9DOvU1XmZi3iK5sWHe1FPNUvJhoMGfcBBTMo=
WARP system for graph ExampleX created with the following parameters:
Scheduler Name:	Priority
numFaults:	1
Time Slot	A	B	C
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep
1	wait(#2)	if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)	wait(#2)
2	sleep	if has(F0) push(F0: B -> C, #3)	wait(#3)
3	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)
4	wait(#6)	if has(F1) push(F1: B -> A, #6) else pull(F1: C -> B, #6)	wait(#6)
5	if has(F0) push(F0: A -> B, #3)	wait(#3)	sleep
6	wait(#4)	if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4)	wait(#4)
7	sleep	if has(F0) push(F0: B -> C, #5)	wait(#5)
8	wait(#7)	if has(F1) push(F1: B -> A, #7)	sleep
9	sleep	sleep	sleep
// All flows meet their deadlines
