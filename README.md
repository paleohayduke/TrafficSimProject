# TrafficSimProject
TAMUCT COSC-3320-110 Programmers Group 1
======================================================================================================================
Tasks:

Priority 1: Building a tree out of the road and intersection data. 
-Dependent task: Implementing a graph transversal algorithm for route selection.

Priority 1: Renderer needs to be reworked. I just slapped it together to see if the program was getting
the coordinates of nodes correctly. This needs to be fixed so we can visually see what we are doing. 

Priority 2: Get Get set up to keep track of revisions.
https://netbeans.org/kb/docs/ide/git.html#branch

=======================================================================================================================
9/21/2018 COMPLETED: Determining order of nodes in a road. This is important for drawing the roads correctly on the map.
I am thinking going through list of nodes and then doing distance formula distance=sqrt((x1-x2)^2+(y1-y2)^2)
to find the shortest distances to two nodes from and one node in a road will work to figure their correct order.
There are lots of nodes on curves so that shouldnt cause a problem but some of the roads will be "ends" and only have one node connected to them. 

9/21/2018 COMPLETED: Reimplement the method "private Nd makeND(long ref){ }" inside the "MapReader" as a binary search. 
*important to test the program with a map of the entire city of killeen to fullfil requirements. 




