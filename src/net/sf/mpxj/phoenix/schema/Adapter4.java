//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.28 at 08:59:08 PM GMT 
//


package net.sf.mpxj.phoenix.schema;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import net.sf.mpxj.TimeUnit;

public class Adapter4
    extends XmlAdapter<String, TimeUnit>
{


    public TimeUnit unmarshal(String value) {
        return (net.sf.mpxj.phoenix.DatatypeConverter.parseTimeUnits(value));
    }

    public String marshal(TimeUnit value) {
        return (net.sf.mpxj.phoenix.DatatypeConverter.printTimeUnits(value));
    }

}
