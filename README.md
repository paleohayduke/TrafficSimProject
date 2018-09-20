# TrafficSimProject
TAMUCT COSC-3320-110 Programmers Group 1

Tasks:

Priority 1: Determining order of nodes in a road. This is important for drawing the roads correctly on the map.
I am thinking going through list of nodes and then doing distance formula distance=sqrt((x1-x2)^2+(y1-y2)^2)
to find the shortest distances to two nodes from and one node in a road will work to figure their correct order.
There are lots of nodes on curves so that shouldnt cause a problem but some of the roads will be "ends" and only have one node connected to them. 

Priority 1: Building a tree out of the road intersection data that can be traversed for building routes.
Maybe A*

Priority 2: Reimplement the method "private Nd makeND(long ref){ }" inside the "MapReader" as a binary search. 
*important to test the program with a map of the entire city of killeen to fullfil requirements. 

Once we can generate routes we can start testing the cars and everything else.

Check this out to see how to create branches
https://netbeans.org/kb/docs/ide/git.html#branch
