/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;
import java.util.ArrayList;
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 *
 * @author paleo
 */

// reads osm map and makes an ArrayList of the Roads, each Road contains 
// an ArrayList of Nd and second ArrayList of IntersectionNd. 
// each Nd has its node ID, longitude and lattitude. 
// IntersectNd extends Nd and includes a field to tell you what road it
// intersects with (the field is called secondaryRef). 
public class MapReader {

    // these were for testing 
    double minLat =0;
    double maxLat =0;
    double minLon =0;
    double maxLon=0;
    
    
    
//holds the list of  Roads of all the roads on the map   
    public ArrayList<Road> roads = new ArrayList<Road>();
//    int startIndex = 0;
// this holds all the nodes for use in search for coordinates 
// compared to Way refs
    NodeList allNodes;
    //docString is file name
    MapReader(String docString){
        
        
        // this reads the xml document into memory
        Document xmlDoc = getDocument(docString);
        
        // looks like testing stuff, ill clean it up later dont wanna break 
        // it right now
        String nodeName = xmlDoc.getDocumentElement().getNodeName();
        
        System.out.println("nodeName: " + nodeName);
        
        //
        //
        //This is a list of ALL <ways> on the map. the program
        // will refine this list to only the <ways> that are Roads
        NodeList listOfWays = xmlDoc.getElementsByTagName("way");
        
        // Getting the bounds from the file
        NodeList bounds = xmlDoc.getElementsByTagName("bounds");
        //seting bounds of map (max and min long and lat) so the renderer can use it later
        setBounds(bounds);
        
        // This list of nodes is searched against to find longitude and latitude
        // to link to the node IDs in our roads. 
        allNodes = xmlDoc.getElementsByTagName("node");
        
        
        System.out.println("Number of ways " + listOfWays.getLength());
        
        //NodeList listOfNodes =
        //
        // ******** This actually starts the real work. All the real work is in
        // getListOfNd
        // this shit is sloppy.TODO: tidy this up
        getListOfNd(listOfWays);
        
        //tells us how many roads we got. the process to get the number of
        // roads is in getListOfNd
        System.out.println("Number of Roads: " + roads.size()+ "\nRodeCounter: "
        +roadCounter);
        
        setIntersections();
    }

    // this is to set the min and max lats and longs
    private void setBounds(NodeList bounds){
        Element showElement = (Element)bounds.item(0);
        
        
        minLat=Double.parseDouble(showElement.getAttribute("minlat"));
        maxLat=Double.parseDouble(showElement.getAttribute("maxlat"));
        minLon=Double.parseDouble(showElement.getAttribute("minlon"));
        maxLon=Double.parseDouble(showElement.getAttribute("maxlon"));
        
        
        System.out.println("MINLAT:"+showElement.getAttribute("minlat"));
        System.out.println("MAXLAT:"+showElement.getAttribute("maxlat"));
        System.out.println("MINLON:"+showElement.getAttribute("minlon"));
        System.out.println("MAXLON:"+showElement.getAttribute("maxlon"));
    }
    
    // * When u initialize this class, use this function to get the list of Roads
    // to build a tree and do whatever eelse u need. The whole point
    // of this entire class is to give u this ArrayList<Road> roads
    public ArrayList<Road> getRoads(){
        return roads;
    }
    
    
    //roadCounter for debug purposes
    int roadCounter = 0;
    
    //this needs to be revisited
    // this does everything, it needs improvement and to be tidyed up.
    // can modularize this code
    private void getListOfNd(NodeList wayList){
        try{
            
            //This is the start og going through the unfiltered <ways> to
            // build up our Roads
            for(int i = 0; i< wayList.getLength();i++){
                
                // the following pattern repeats as we go through the nodes
                // of the XML file to get all the data we need to
                // build up the list of roads with all their info
                
                // make grab an individual Node from i to wayList.getLength()
                Node way = wayList.item(i);
                
                // turn that Node into an Element because
                // elements have lots of methods that are useful
                Element showElement = (Element)way;
                
                //This is making a node list of all the nodes (nd) that
                // make up the <way> 
                NodeList ndList = showElement.getElementsByTagName("nd");
                System.out.println("way" + way.toString());

                ////////
                ///////// you need a node list of nds from ways
                ////////  that include a tag with attribute of 
                ////////  highway or road 
                /////////

                ///// This checks if the way is a Road. go visit
                // its function to see implementation details. 
                if(!isRoad(showElement)){
                    continue; // if the <way> is not a road, start loop over
                }
                


                System.out.println(++roadCounter);

                // grabbing id of the Road
                int roadID = Integer.parseInt(showElement.getAttribute("id"));
                System.out.println("Road ID: " + roadID);

//////////////// tempRoad is made to build a road that holds all its nodes 
//////////////// with all their data. After it is built, it is added to
//////////////// an ArrayList and then reinitialized here
                Road tempRoad = new Road();
                tempRoad.setID(roadID); 
                
                // This cycles through all the nodes in a Road
                for(int j=0;j<ndList.getLength();j++){
                    Nd tempNd = new Nd();// familiar pattern
                    
                    
                    Node nd = ndList.item(j);
                    Element ndShowElement =(Element)nd;
                    NodeList refList = ndShowElement.getElementsByTagName("ref");
                    
                    
                    
                    
                    
                    
                    System.out.println(nd.toString());
                    
                    // Ref is the ID# of a Nd (a node that makes up a road)
                    long ref = Long.parseLong(ndShowElement.getAttribute("ref"));
                    /////////////////////////////////////////////////////////////                           /WORKING HERE


//////////////////// This grabs the coordinates. This makeNd() function is so
//////////////////// inefficient. it needs to be reimplemented in binary search
//////////////////// before we can process the entire map of killeen. 
//////////////////// it should be lightening fast after its turned to Binary Search. 
                    tempNd=makeNd(ref);
                    tempRoad.addNode(tempNd);
                    
                    
                    System.out.println(ndShowElement.getAttribute("ref"));
//                    for(int k = 0; k<refList.getLength();k++){
//                        Node ref = refList.item(k);
//                        Element refShowElement = (Element)ref;
//                        System.out.println("ref: "+ ref.toString());
//                    }
//                    
                    
                    
                
                }
                
                //
                // add the road we built to the array list, entire point of the class
                //
                roads.add(tempRoad);
                //
                
            }
        }
        catch(Exception ex){
            System.out.println("error; private void getListOfNd");
            System.out.println(ex.getMessage());
        }
    }
    

    // this function is for testing if a <way> is a road vs something else
    private boolean isRoad(Element showElement){
        boolean isRoad = false;
        NodeList list = showElement.getElementsByTagName("tag");
 //       System.out.println("isRoad?" + showElement.toString());

        
        for(int i = 0; i<list.getLength();i++){
            Node tag = list.item(i);
 //           System.out.println(tag.toString());
            
            Element tagShowElement =(Element)tag;
            
            NamedNodeMap test = tag.getAttributes();
            
                    
//            System.out.println(tagShowElement.getAttribute("k"));
            String tagString = tagShowElement.getAttribute("k");
            if(tagString.equalsIgnoreCase("highway")){
                return true;
            }
            //////////// 
            //////// THIS IS BROKEN
        }
        
        return isRoad;
    }
    
    
    //private void getListOfWays(){
    //    
    //}

    
    
    // get the XML file so we can work with it
    private Document getDocument(String docString){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
//            factory.setValidating(true);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            return builder.parse(new InputSource(docString));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    
    
    // TODO: reimplement as binary search
    // Preamble
    // This function currently takes a long ref which identifies a node ID
    // in a road... The nodes linked by the road do not include longitude and 
    // lattitude data... but luckily there are indpendent <node>'s in the file,
    // seperate from the <ways> that do have the location data.. 
    // 
    // After the function finds the node that has longitude and latitude data,
    // it constructs a temporare node (Nd nd) that has all the datafields of a 
    // Node filled out and then returns it.
    //
    // Currently it searches linearly, I'll try to walk through what it does now
    // that corresponds to regular linear search.
    private Nd makeNd(long ref){
        
        Nd nd = new Nd();   // the Nd is one of our data types that holds the
                            // node data: ref, longitude, latitude
        
        // this loop goes through _all_ of the Nodes on the map. 
        for(int i = 0; i<allNodes.getLength();i++){
            Node tempNode = allNodes.item(i);   //pulls an individual node from 
                                                //list of all nodes
            Element showElement = (Element)tempNode;// this convets it to Element
                                                    // to let us get the attributes
//            System.out.println("\tsearching for match... try:"+i);


            // This is where we see if ref IDs match between nodes. 
            // if they do match, then we grab the Longitude and Lattitude
            // data from the Nd nd. 
            if(ref==Long.parseLong(showElement.getAttribute("id"))){
                System.out.println("FOUND A MATCH: "+showElement.getAttribute("id") );
                nd.setRef(ref); // Set reference ID number of the node
                nd.setLat(Double.parseDouble(showElement.getAttribute("lat")));  // seting latitude
                nd.setLong(Double.parseDouble(showElement.getAttribute("lon"))); // setting longitude


                System.out.println("setLat " + nd.getLat());                
                System.out.println("setLon " + nd.getLong());
 //               startIndex=i;
                return nd; // return back a Completed node. 
            }

            
                
        }
        
        
        
        
        
        
        return nd;
    }
    
    
    // 
    // gets all the intersections and adds them to the roads. 
    // This code runs but it has not been tested to see if it
    // is working correctly. 
    void setIntersections(){
        
        
        for(int i = 1; i< roads.size();i++){
            Road road = roads.get(i-1);
            Road road2 = roads.get(i);
            
            ArrayList<IntersectNd> intersects = new ArrayList<IntersectNd>();
            
            for(int j=0;j<road.nodeList.size();j++){
                for(int k=0;k<road2.nodeList.size();k++){
                    if(road.getNodes().get(j).getRef()==road2.getNodes().get(k).getRef()){
                        IntersectNd tempIntersect = new IntersectNd();
                        tempIntersect.setLat(road.getNodes().get(j).getLat());
                        tempIntersect.setLong(road.getNodes().get(j).getLong());
                        tempIntersect.setRef(road.getNodes().get(j).getRef());
                        tempIntersect.setSecondary(road2.getNodes().get(k).getRef());
                        
                        intersects.add(tempIntersect);
                        System.out.println("Intersect: ");
                    }
                }
                
            }
            roads.get(i-1).setIntersections(intersects);
            
        }
        
    }
}
