//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.09.11 at 10:06:48 PM BST 
//

package net.sf.mpxj.planner.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "", propOrder =
{
   "allocation"
}) @XmlRootElement(name = "allocations") public class Allocations
{

   protected List<Allocation> allocation;

   /**
    * Gets the value of the allocation property.
    * 
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the allocation property.
    * 
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getAllocation().add(newItem);
    * </pre>
    * 
    * 
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link Allocation }
    * 
    * 
    */
   public List<Allocation> getAllocation()
   {
      if (allocation == null)
      {
         allocation = new ArrayList<Allocation>();
      }
      return this.allocation;
   }

}
