// Course CS2820 Authentication Tag: 7HQiipr2dNq/iDHKfJkC3MqJsYu9BQklz5DoKTkz9g8u79i8trkpv9sfEsMoZ2jc5MOq9iW1Z24nLPURkvOxiX5HVX8mt2VBroxjG4A76hc=
Channel Analysisfor graph ExampleX created with the following parameters:
Scheduler Name:	Priority
numFaults:	1
Channel/Time Slot 0	1	2	3	4	5	6	7	8	9
0		-	-	-	-	-	-	-	-	-	-	
1		[A]::F0:(A:B)	-	-	[C]::F1:(C:B)	-	-	-	-	-	-	
2		-	-	-	-	-	-	-	-	-	-	
3		-	[B]::F0:(B:C), F0:(A:B)	-	-	-	[A]::F0:(A:B)	-	-	-	-	
4		-	-	[B]::F0:(B:C)	-	-	-	-	-	-	-	
5		-	-	-	-	-	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	
6		-	-	-	-	-	-	-	[B]::F0:(B:C)	-	-	
7		-	-	-	-	[B]::F1:(B:A), F1:(C:B)	-	-	-	-	-	
8		-	-	-	-	-	-	-	-	[B]::F1:(B:A)	-	
9		-	-	-	-	-	-	-	-	-	-	
10		-	-	-	-	-	-	-	-	-	-	
11		-	-	-	-	-	-	-	-	-	-	
12		-	-	-	-	-	-	-	-	-	-	
13		-	-	-	-	-	-	-	-	-	-	
14		-	-	-	-	-	-	-	-	-	-	
15		-	-	-	-	-	-	-	-	-	-	


[if has(F0) push(F0: A -> B, #1), wait(#1), sleep]
[wait(#2), if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2), wait(#2)]
[sleep, if has(F0) push(F0: B -> C, #3), wait(#3)]
[sleep, wait(#1), if has(F1) push(F1: C -> B, #1)]
[wait(#6), if has(F1) push(F1: B -> A, #6) else pull(F1: C -> B, #6), wait(#6)]
[if has(F0) push(F0: A -> B, #3), wait(#3), sleep]
[wait(#4), if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4), wait(#4)]
[sleep, if has(F0) push(F0: B -> C, #5), wait(#5)]
[wait(#7), if has(F1) push(F1: B -> A, #7), sleep]
[sleep, sleep, sleep]
