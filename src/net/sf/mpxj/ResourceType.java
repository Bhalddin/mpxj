/*
 * file:       ResourceType.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2002-2003
 * date:       25/11/2004
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package net.sf.mpxj;

import java.util.EnumSet;

import net.sf.mpxj.utility.MpxjEnum;
import net.sf.mpxj.utility.NumberUtility;

/**
 * Instances of this class represent enumerated resource type values.
 */
public enum ResourceType implements MpxjEnum
{
   MATERIAL(0, "Material"),
   WORK(1, "Work");

   /**
    * Private constructor.
    * 
    * @param type int version of the enum
    * @param name enum name
    */
   private ResourceType(int type, String name)
   {
      m_value = type;
      m_name = name;
   }

   /**
    * Retrieve an instance of the enum based on its int value.
    *
    * @param type int type
    * @return enum instance
    */
   public static ResourceType getInstance(int type)
   {
      if (type < 0 || type >= TYPE_VALUES.length)
      {
         type = WORK.getValue();
      }
      return (TYPE_VALUES[type]);
   }

   /**
    * Retrieve an instance of the enum based on its int value.
    *
    * @param type int type
    * @return enum instance
    */
   public static ResourceType getInstance(Number type)
   {
      int value;
      if (type == null)
      {
         value = -1;
      }
      else
      {
         value = NumberUtility.getInt(type);
      }
      return (getInstance(value));
   }

   /**
    * Accessor method used to retrieve the numeric representation of the enum. 
    *
    * @return int representation of the enum
    */
   public int getValue()
   {
      return (m_value);
   }

   /**
    * {@inheritDoc}
    */
   @Override public String toString()
   {
      return (m_name);
   }

   /**
    * Array mapping int types to enums.
    */
   private static final ResourceType[] TYPE_VALUES = new ResourceType[2];
   static
   {
      for (ResourceType e : EnumSet.range(ResourceType.MATERIAL, ResourceType.WORK))
      {
         TYPE_VALUES[e.getValue()] = e;
      }
   }

   /**
    * Internal representation of the enum int type.
    */
   private int m_value;
   private String m_name;
}
