//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.03.24 at 12:10:52 AM GMT 
//

package net.sf.mpxj.mspdi.schema;

import java.math.BigDecimal;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@SuppressWarnings("all") public class Adapter4 extends XmlAdapter<String, BigDecimal>
{

   public BigDecimal unmarshal(String value)
   {
      return (javax.xml.bind.DatatypeConverter.parseDecimal(value));
   }

   public String marshal(BigDecimal value)
   {
      if (value == null)
      {
         return null;
      }
      return (javax.xml.bind.DatatypeConverter.printDecimal(value));
   }

}
