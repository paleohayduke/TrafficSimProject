# TrafficSimProject

TAMUCT COSC-3320-110 Programmers Group 1

Announcements:

10/1/2018

There are a few buttons, pause, play, fast forward. 
Fast forward is button clicks^2. 
 
Mouse position is now converted to correct long and lat. Red square dropped where
the engine thinks you are in lon&lat. Zoom has been fixed. 

9/30/2018 Stop signs are working. Time for stop lights. 

Cars now keep distance between each other. 

A* now takes into account traffic levels at time of route generation!

Pause and play functionality.
Placeholder Frames to hold data and select options-still empty.

9/29/2019 Consolidated intersections into one node. Next task is making stoplights
*Nope... that broke a lot of intersections. set it back to old technique. 

One way roads are now implemented. This causes an issue if u set the cars
to generate new routes from their destination waypoint if they just got to the
end of a oneway road that dead ends at the end of the map and a few other
circumstances. To deal with this i set up cars to spawn at a random new starting
location when they finish their route. 

Pathfinding now takes distance traveled into account (A*). Rebuilt GraphBuilder
to consolidate intersections into single nodes. Next step is stops. 

9/28/2019 Figured out the methods of JComponent a little better and was able to 
fix the frame rate issue in the Renderer. Works a lot better now! 

List of nodes added to Nd that link to all cars who hold the node as a waypoint.
Might carry previous node to help figure orientation for handling stop signs.
Stop lights should be much simpler. Idea: build stop lights or stop signs
with mouse... map building tools.

New bug, sometimes the cars will cycle through more than one node when
updating waypoint. 
-Check on the... d/D, im thinking its when D gets near 0 or something. 

Pathfinding causes hiccups in frame rate on the full size Killeen map when
Random() picks two points that do not connect, leaving the algorithm to 
(sometimes) search the _entire_ map. 

I was thinking about signal time length for stop lights and started to
wonder, "should we add pedestrians?". 
https://nacto.org/publication/urban-street-design-guide/intersection-design-elements/traffic-signals/signal-cycle-lengths/
A lot of the decisions on timing schemes depends on vehicle traffic
AND pedestrian traffic. 


9/27/2018 The automobile can now update its position based on time and
velocity, this means it now moves smoothly between nodes.   

You can now spawn variable amount of cars at once, setCars(int numOfCars). 
Look at main() in TrafficSimProject for more details. 

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

-Node Info Window

-Stoplights
https://nacto.org/publication/urban-street-design-guide/intersection-design-elements/traffic-signals/signal-cycle-lengths/

-Speed limits

-Test play and pause button functionality.

-Test mouse interaction.

-Function to find a node based on its longitude and latitude. 

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

COMPLETED 10/1/2018: zoom broken since redesigning renderer

COMPLETED 9/30/2018: pause/play functionality

COMPLETED 9/30/2018: Stop signs.

COMPLETED 9/29/2018: Link to Road from nodes. 

COMPLETED 9/29/2018: Change intersection node set up.. currently every intersection is made up of
two nodes... need to change to ONE node at each intersection... this will greatly
simplify stop signs and stop lights. 

COMPLETED 9/29/2018: Alter localRoadBuilder to implement unidirectional node connections for
one-way roads. 

COMPLETED 9/28/2018: Sometimes the cars will cycle through more than one node when
updating their waypoint. Might be a problem with node connections. 
*problem was with updating the waypoint and posNode of auto. 

COMPLETED 9/28/2018: Fixed frame rate issue and the minimize-maximize bug
Renderer needs to rebuilt. Its doing a lot of unnecessary work.

COMPLETED  9/27/2019: Implement update of car position between nodes. Solve for x and y from
distance formula, Pythagorean theorem, vectors. 

COMPLETED 9/26/2018: Implement a graph search. 

COMPLETED 9/26/2018 BUG FIXED: findRoute() sort of works. Need to check if the problem is in the algorithm (likely)
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