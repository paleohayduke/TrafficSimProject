# TrafficSimProject

TAMUCT COSC-3320-110 Programmers Group 1

Announcements:
The map is displaying and is finding all intersections correctly. We've got
everything we need to set up a graph of the roads and then implement a 
graph traversal algorithm.

This is an informative video on constructing an algorithm to find the shortest
path between two points on a graph. 
https://youtu.be/gGQ-vAmdAOI

Tasks:

Priority 1: Building a tree out of the road and intersection data. 
-Dependent task: Implementing a graph transversal algorithm for route selection.

Priority 1: Come up with car objects that can travel across the map.

Priority 2: Get Git set up to keep track of revisions.

Priority 3: Map is displaying upside down because origin (0,0) is top left corner for the JFrame.

COMPLETED 9/22/2018: Renderer needs to be reworked. I just slapped it together to see if the program was getting
the coordinates of nodes correctly. This needs to be fixed so we can visually see what we are doing. 


COMPLETED 9/21/2018: Determining order of nodes in a road. This is important for drawing the roads correctly on the map.
I am thinking going through list of nodes and then doing distance formula distance=sqrt((x1-x2)^2+(y1-y2)^2)
to find the shortest distances to two nodes from and one node in a road will work to figure their correct order.
There are lots of nodes on curves so that shouldnt cause a problem but some of the roads will be "ends" and only have one node connected to them. 

COMPLETED 9/21/2018: Reimplement the method "private Nd makeND(long ref){ }" inside the "MapReader" as a binary search. 
*important to test the program with a map of the entire city of killeen to fullfil requirements. 




