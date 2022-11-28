// Course CS2820 Authentication Tag: MqRlgNSDnsy+BHGqO7uS9zWYuwQZxXfe67rkGjYCPYWplf86Bx+mgOsWsD8802Ex0DNk/ZyRtg5Z9fWdy2CU1dsM/gHl2Y2/Gqq6GHB2cZY=
WARP system for graph TestBug created with the following parameters:
Scheduler Name:	Priority
numFaults:	1
Time Slot	A	B	C	D	E	F
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	if has(F1) push(F1: C -> D, #1)	wait(#1)	sleep	sleep
1	if has(F0) push(F0: A -> B, #2)	wait(#2)	wait(#2)	if has(F1) push(F1: D -> E, #2) else pull(F1: C -> D, #2)	wait(#2)	sleep
2	sleep	sleep	sleep	wait(#1)	if has(F1) push(F1: E -> F, #1) else pull(F1: D -> E, #1)	wait(#1)
3	sleep	sleep	sleep	sleep	if has(F1) push(F1: E -> F, #2)	wait(#2)
4	sleep	sleep	sleep	sleep	sleep	sleep
5	sleep	sleep	sleep	sleep	sleep	sleep
6	sleep	sleep	sleep	sleep	sleep	sleep
7	sleep	sleep	sleep	sleep	sleep	sleep
8	sleep	sleep	sleep	sleep	sleep	sleep
9	sleep	sleep	sleep	sleep	sleep	sleep
10	sleep	sleep	sleep	sleep	sleep	sleep
11	sleep	sleep	sleep	sleep	sleep	sleep
// All flows meet their deadlines
