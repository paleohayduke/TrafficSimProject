# TrafficSimProject

TAMUCT COSC-3320-110 Programmers Group 1

Announcements:

Edit this text document with anything you do while working on the project. We can
turn this in as an appendix on reports. 

9/27/2018 5:04 AM the automobile can now update its position based on time and
velocity, this means it now moves smoothly between nodes.   

9/26/2018 It looks like routeFind() is fixed. You can enter a start node and
end node and it will automatically generate a path and animate the car taking
the nodes between both points. A new bug has come up, when you first run the
program, if you use the medium map or larger, the window will not update the
animation until after you MINIMIZE the window and then open it back up. 
The renderer needs to be rebuilt and a controller/handler for setting up cars 
needs to be written. The car controller needs to be able to generate a 
defined amount of cars and give all of them random start and end nodes. The
controller needs to hold the cars in a list and update them all iteratively, 
every "step". After they are all updated a list of all the cars positions needs
to be passed to the renderer to update the next frame. 

9/26/2018 The demo now animates in a loop. I added a debug() method to the
Auto class. call the debug method after your car already has a waypoint assigned 
to its waypointNode and you can manually traverse the nodes. waypointNodes 
represent the next node the car is supposed to take on a route. 
Debugging of the node system revealed that the map nodes are all correctly
linked. Since the node system is fine, the bug is most probably in 
Direction routeFind(). *see BUGS  

9/24/2018 DEMO:
Once the demo loads, you can press a key into the console and hit enter to cause
a car to take a "step". Each step will traverse one node towards the car's
destination. The code is in the Simulation class under demo().


9/24/2018 There is a new Directions class. It has some stuff to build lists of
directions for the car to follow. Pathfinding algorithm needs to generate a
list of choices that the car will make at each node. The automobile can take the
direction list and it will travel from a starting node through the route with every
call of the .step() function. The renderer must be called separately to display
the car on the map. When we generate many cars we will update the cars position 
in the geographic system and then form a list of coordinates (and possibly orientations) 
along with type to send to the renderer to display. 
There is a findRoute() in Directions but it is very buggy, see BUGS. 
-ww
Cleaned up main and made a Simulation class to make things more manageable. 
Need to fix protected vs private vs public on a lot of stuff. 

9/23/2018 There individual nodes can be built into a tree structure that gives
every node a pointer to its connecting nodes along with edge weight data. I'm
working on an "Auto" class that can traverse the nodes while keeping track
of its current position. It takes a set of "directions" that are in the
form of a list of integers. each integer represents the index of the appropriate
connecting node. 
-ww

9/22/2018 The map is displaying and is finding all intersections correctly. We've got
everything we need to set up a graph of the roads and then implement a 
graph traversal algorithm.
-ww

TODO:

-Renderer needs to rebuilt. Its doing a lot of unnecessary work.

-Implement update of car position between nodes. Solve for x and y from
distance formula, Pythagorean theorem, vectors. 

-Collect intersection type data (stopsign, stoplight, speed limit).
Reevaluate MapReader-write some functions that take tags as parameter to
easily search the OSM file. Need to be able to display any queried data in
the renderer! 

-Get Git set up to keep track of revisions.

-File open/save for _processed_ map data

GUI 
-mouse scroll wheel for zoom, ability to click and drag to move aroudn the map
-buttons... lots of them 
-click on nodes
-test functions 
-load 

BUGS

Map is displaying upside down because origin (0,0) is top left corner for the JFrame.
Need to flip vertically.

COMPLETED TASKS

COMPLETED 9/26/2018: Implement a graph search. 

COMPLETED 9/26/2018 BUG FIXED findRoute() sort of works. Need to check if the problem is in the algorithm (likely)
or something with the linking of nodes crossing roads (in some test cases a path
will be generated that involves changing roads at intersections, but sometimes
the algorithm gets stuck in an infinite loop, need to review the code but im too
tired atm). 

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
