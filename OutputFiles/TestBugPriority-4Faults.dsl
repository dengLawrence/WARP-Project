// Course CS2820 Authentication Tag: bM0j07HgAhBujV0UCao/VhKox7rpopHxqxa+39RYhGOFdXT7Xcw9eZGe9d5tKnzZLy4Zh6ddFmGvksr1BcvFez8Q5T8QLVx/wLgwiRaB+Kk=
WARP system for graph TestBug created with the following parameters:
Scheduler Name:	Priority
numFaults:	4
Time Slot	A	B	C	D	E	F
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	if has(F1) push(F1: C -> D, #1)	wait(#1)	sleep	sleep
1	if has(F0) push(F0: A -> B, #2)	wait(#2)	wait(#2)	if has(F1) push(F1: D -> E, #2) else pull(F1: C -> D, #2)	wait(#2)	sleep
2	if has(F0) push(F0: A -> B, #3)	wait(#3)	wait(#3)	if has(F1) push(F1: D -> E, #3) else pull(F1: C -> D, #3)	if has(F1) push(F1: E -> F, #1) else wait(#3)	wait(#1)
3	if has(F0) push(F0: A -> B, #4)	wait(#4)	wait(#4)	if has(F1) push(F1: D -> E, #4) else pull(F1: C -> D, #4)	if has(F1) push(F1: E -> F, #2) else wait(#4)	wait(#2)
4	if has(F0) push(F0: A -> B, #5)	wait(#5)	wait(#5)	if has(F1) push(F1: D -> E, #5) else pull(F1: C -> D, #5)	if has(F1) push(F1: E -> F, #3) else wait(#5)	wait(#3)
5	sleep	sleep	sleep	wait(#4)	if has(F1) push(F1: E -> F, #4) else pull(F1: D -> E, #4)	wait(#4)
6	sleep	sleep	sleep	sleep	if has(F1) push(F1: E -> F, #5)	wait(#5)
7	sleep	sleep	sleep	sleep	sleep	sleep
8	sleep	sleep	sleep	sleep	sleep	sleep
9	sleep	sleep	sleep	sleep	sleep	sleep
10	sleep	sleep	sleep	sleep	sleep	sleep
11	sleep	sleep	sleep	sleep	sleep	sleep
// All flows meet their deadlines
