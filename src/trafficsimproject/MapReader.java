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

// reads osm map
public class MapReader {

    
    double minLat =0;
    double maxLat =0;
    double minLon =0;
    double maxLon=0;
    
    
    
//holds list of  roads for now    
    public ArrayList<Road> roads = new ArrayList<Road>();
    int startIndex = 0;
// this holds all the nodes for use in search for coordinates 
// compared to Way refs
    NodeList allNodes;
    //docString is file name
    MapReader(String docString){
        
        Document xmlDoc = getDocument(docString);
        
        String nodeName = xmlDoc.getDocumentElement().getNodeName();
        
        System.out.println("nodeName: " + nodeName);
        
        NodeList listOfWays = xmlDoc.getElementsByTagName("way");
        
        
        NodeList bounds = xmlDoc.getElementsByTagName("bounds");
        //
        setBounds(bounds);
        // list of all nodes to get info
        allNodes = xmlDoc.getElementsByTagName("node");
        
        
        System.out.println("Number of ways " + listOfWays.getLength());
        
        //NodeList listOfNodes = 
        getListOfNd(listOfWays);
        
        
        System.out.println("Number of Roads: " + roads.size()+ "\nRodeCounter: "
        +roadCounter);
        
        setIntersections();
    }

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
    
    
    public ArrayList<Road> getRoads(){
        return roads;
    }
    
    
    //roadCounter for debug purposes
    int roadCounter = 0;
    
    //this needs to be revisited
    private void getListOfNd(NodeList wayList){
        try{
            
            
            //START INDEX FOR HEURISTIC
            startIndex = 0;
            for(int i = 0; i< wayList.getLength();i++){
                Node way = wayList.item(i);
                
                Element showElement = (Element)way;
                NodeList ndList = showElement.getElementsByTagName("nd");
                System.out.println("way" + way.toString());
                

                
                
                ////////
                ///////// you need a node list of nds from ways
                ////////  that include a tag with attribute of 
                ////////  highway or road 
                /////////

                if(!isRoad(showElement)){
                    continue;
                }
                


                System.out.println(++roadCounter);


                int roadID = Integer.parseInt(showElement.getAttribute("id"));
                System.out.println("Road ID: " + roadID);
                Road tempRoad = new Road();
                tempRoad.setID(roadID);
                
                
                for(int j=0;j<ndList.getLength();j++){
                    Nd tempNd = new Nd();
                    
                    
                    Node nd = ndList.item(j);
                    Element ndShowElement =(Element)nd;
                    NodeList refList = ndShowElement.getElementsByTagName("ref");
                    
                    
                    
                    
                    
                    
                    System.out.println(nd.toString());
                    
                    long ref = Long.parseLong(ndShowElement.getAttribute("ref"));
                    /////////////////////////////////////////////////////////////                           /WORKING HERE
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
                
                roads.add(tempRoad);
                //
                
            }
        }
        catch(Exception ex){
            System.out.println("error; private void getListOfNd");
            System.out.println(ex.getMessage());
        }
    }
    
    //Element ndShowElement =(Element)nd;
    //                NodeList refList = ndShowElement.getElementsByTagName("ref");
    //
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
    
    
    // this finds the lat and logtitude for Nd elements of a <way> by comparing
    // their <nd> ref vs <node> ID in allNodes. 
    private Nd makeNd(long ref){
        Nd nd = new Nd();
        
        for(int i = 0; i<allNodes.getLength();i++){
            Node tempNode = allNodes.item(i);
            Element showElement = (Element)tempNode;
//            System.out.println("\tsearching for match... try:"+i);
            if(ref==Long.parseLong(showElement.getAttribute("id"))){
                System.out.println("FOUND A MATCH: "+showElement.getAttribute("id") );
                nd.setRef(ref);
                nd.setLat(Double.parseDouble(showElement.getAttribute("lat")));
                nd.setLong(Double.parseDouble(showElement.getAttribute("lon")));
                startIndex=i;
                return nd;
            }

            
                
        }
        
        
        
        
        
        
        return nd;
    }
    
    
    
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
