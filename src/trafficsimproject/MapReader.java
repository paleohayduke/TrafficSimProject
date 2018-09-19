/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsimproject;
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 *
 * @author paleo
 */
public class MapReader {
    
    //
    MapReader(String docString){
        
        Document xmlDoc = getDocument(docString);
        
        String nodeName = xmlDoc.getDocumentElement().getNodeName();
        
        System.out.println("nodeName: " + nodeName);
        
        NodeList listOfWays = xmlDoc.getElementsByTagName("way");
        
        System.out.println("Number of ways " + listOfWays.getLength());
        
        //NodeList listOfNodes = 
        getListOfNd(listOfWays);
        
    }

    int roadCounter = 0;
    private void getListOfNd(NodeList wayList){
        try{
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




                for(int j=0;j<ndList.getLength();j++){
                    Node nd = ndList.item(j);
                    Element ndShowElement =(Element)nd;
                    NodeList refList = ndShowElement.getElementsByTagName("ref");
                    
                    System.out.println(nd.toString());
                    
                    
                    
                    System.out.println(ndShowElement.getAttribute("ref"));
//                    for(int k = 0; k<refList.getLength();k++){
//                        Node ref = refList.item(k);
//                        Element refShowElement = (Element)ref;
//                        System.out.println("ref: "+ ref.toString());
//                    }
//                    
                    
                    
                
                }
                
            }
        }
        catch(Exception ex){
            System.out.println("error; private void getListOfNd");
        }
    }
    
    //Element ndShowElement =(Element)nd;
    //                NodeList refList = ndShowElement.getElementsByTagName("ref");
    //
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
    
    private void getListOfWays(){
        
    }

    
    
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
}
