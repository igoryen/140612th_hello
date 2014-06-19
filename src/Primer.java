import java.math.BigDecimal;
import java.util.*;
import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import primer.*;

public class Primer {
    
    private ObjectFactory of;
    private PurchaseOrderType po;
    
    // v1
    public Primer(){
        of = new ObjectFactory();
        po = of.createPurchaseOrderType();
    }
    
    // v3
    public void make(
            USAddress ship, 
            USAddress bill, 
            String comment,
            /*Items item,*/ 
            XMLGregorianCalendar date){
        
        PurchaseOrderType po = of.createPurchaseOrderType();
        po.setBillTo(bill);
        po.setComment(comment);
        //po.setItems(item);
        po.setShipTo(ship);
        po.setOrderDate(date);
    }
    
    // v1
    public void marshal(){
        try{
            JAXBElement<PurchaseOrderType> pol = of.createPurchaseOrder(po);
            JAXBContext jc = JAXBContext.newInstance( "primer" );
            Marshaller m = jc.createMarshaller();
            m.marshal(pol, System.out);
        } catch (JAXBException jbe){

        }
        
    }
    
    // v4
    public static void main(String args[]){
        Primer theApp = new Primer();
        
        USAddress ship = new USAddress();
        ship.setCity("city");
        ship.setCountry("country");
        ship.setName("name");
        ship.setState("state");
        ship.setStreet("street");
        ship.setZip(new BigDecimal(123));
        
        USAddress bill = new USAddress();
        bill.setCity("city2");
        bill.setCountry("country2");
        bill.setName("name2");
        bill.setState("state2");
        bill.setStreet("street");
        bill.setZip(new BigDecimal(456));
        
        //Items item = new Items();
        
        GregorianCalendar gc = new GregorianCalendar();
        try {
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            theApp.make(ship, bill, "no comment", /*item,*/ xgc);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        //System.out.println(xgcal);        
        
    }
}
