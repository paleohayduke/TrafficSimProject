# TrafficSimProject

TAMUCT COSC-3320-110 Programmers Group 1


Announcements:
9/23/2018 There individual nodes can be built into a tree structure that gives
every node a pointer to its connecting nodes along with edge weight data. I'm
working on an "Auto" class that can traverse the nodes while keeping track
of its current position. It takes a set of "directions" that are in the
form of a list of integers. each integer represents the index of the appropriate
connecting node. 

9/22/2018 The map is displaying and is finding all intersections correctly. We've got
everything we need to set up a graph of the roads and then implement a 
graph traversal algorithm.
-ww

TODO:
-Come up with car objects.
 *in progress  class Auto

-Implement a graph search. Watch this video and the one after it.
https://www.youtube.com/watch?v=j1H3jAAGlEA

-Collect intersection type data (stopsign, stoplight, speed limit).

-Get Git set up to keep track of revisions.

-File open/save for _processed_ map data

GUI 
-mouse scroll wheel for zoom, ability to click and drag to move aroudn the map
-buttons... lots of them 
-click on nodes
-test functions 
-load 

BUG 

Map is displaying upside down because origin (0,0) is top left corner for the JFrame.
Need to flip vertically.

COMPLETED 9/23/2018: Building a tree out of the road and intersection data. 

COMPLETED 9/22/2018: Renderer needs to be reworked. I just slapped it together to see if the program was getting
the coordinates of nodes correctly. This needs to be fixed so we can visually see what we are doing. 


COMPLETED 9/21/2018: Determining order of nodes in a road. This is important for drawing the roads correctly on the map.
I am thinking going through list of nodes and then doing distance formula distance=sqrt((x1-x2)^2+(y1-y2)^2)
to find the shortest distances to two nodes from and one node in a road will work to figure their correct order.
There are lots of nodes on curves so that shouldnt cause a problem but some of the roads will be "ends" and only have one node connected to them. 

COMPLETED 9/21/2018: Reimplement the method "private Nd makeND(long ref){ }" inside the "MapReader" as a binary search. 
*important to test the program with a map of the entire city of killeen to fullfil requirements. 
O(n) vs O(log(n)) on test map at 28,000 nodes, that is 28,000/14 = 1000 times faster.  



BREAKDOWN OF CURRENT OUTPUT:

road#: 6 
intercepts: 1   //this is how many intersections the road has with other roads
intersept ref:	149209137
	node ref:	149447096   // each of these is a node of the road
	connections:1   
	node ref:	149447111   
	connections:2
	node ref:	149209137
	connections:2

*NOTE ON CONNECTONS: before the roads get their intersections a 4 node road will
have connections like this: 1, 2, 2, 1. if there the values are greater than this
pattern then there is intersection at the node with the raised value. 
-ww
