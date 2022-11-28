// Course CS2820 Authentication Tag: m3f7wvftYoPFqqkHneOsEP+bsF4Kn9SV6+b91MzSZNxzuyJWgqrIXyHEOHmJTdChAknBAZACJ0g0w+qZN5E+M/k9GQhPiD8poYmi9eXeZ4c=
WARP system for graph ExampleX created with the following parameters:
Scheduler Name:	Priority
numFaults:	4
Time Slot	A	B	C
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep
1	wait(#2)	if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)	wait(#2)
2	wait(#3)	if has(F0) push(F0: B -> C, #3) else pull(F0: A -> B, #3)	wait(#3)
3	wait(#4)	if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4)	wait(#4)
4	wait(#5)	if has(F0) push(F0: B -> C, #5) else pull(F0: A -> B, #5)	wait(#5)
5	sleep	if has(F0) push(F0: B -> C, #6)	wait(#6)
6	if has(F0) push(F0: A -> B, #7)	wait(#7)	sleep
7	wait(#8)	if has(F0) push(F0: B -> C, #8) else pull(F0: A -> B, #8)	wait(#8)
8	wait(#9)	if has(F0) push(F0: B -> C, #9) else pull(F0: A -> B, #9)	wait(#9)
9	wait(#10)	if has(F0) push(F0: B -> C, #10) else pull(F0: A -> B, #10)	wait(#10)
10	wait(#11)	if has(F0) push(F0: B -> C, #11) else pull(F0: A -> B, #11)	wait(#11)
11	sleep	if has(F0) push(F0: B -> C, #12)	wait(#12)
12	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)
13	wait(#13)	if has(F1) push(F1: B -> A, #13) else pull(F1: C -> B, #13)	wait(#13)
14	wait(#14)	if has(F1) push(F1: B -> A, #14) else pull(F1: C -> B, #14)	wait(#14)
15	wait(#15)	if has(F1) push(F1: B -> A, #15) else pull(F1: C -> B, #15)	wait(#15)
16	wait(#0)	if has(F1) push(F1: B -> A, #0) else pull(F1: C -> B, #0)	wait(#0)
17	wait(#1)	if has(F1) push(F1: B -> A, #1)	sleep
// WARNING: NOT all flows meet their deadlines. See deadline analysis report.
