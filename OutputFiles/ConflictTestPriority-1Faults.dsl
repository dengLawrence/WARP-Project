// Course CS2820 Authentication Tag: 6/7H5DJEfaEsCLCf60YVexm86dQQHW5djDcLMTLR//AP/LgmF6iJRtWdlqcCk4a2aScHixxuVD7SNzcVQhKQxS0eCH5TrIInviB4kaB33Es=
WARP system for graph ConflictTest created with the following parameters:
Scheduler Name:	Priority
numFaults:	1
Time Slot	A	B	C	D
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	if has(F1) push(F1: C -> D, #1)	wait(#1)
1	if has(F0) push(F0: A -> B, #2)	wait(#2)	if has(F1) push(F1: C -> D, #2)	wait(#2)
2	sleep	sleep	sleep	sleep
3	sleep	sleep	sleep	sleep
4	sleep	sleep	sleep	sleep
5	sleep	sleep	sleep	sleep
6	sleep	sleep	sleep	sleep
7	sleep	sleep	sleep	sleep
8	sleep	sleep	sleep	sleep
9	sleep	sleep	sleep	sleep
// All flows meet their deadlines
