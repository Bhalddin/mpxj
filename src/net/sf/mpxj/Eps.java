/*
 * file:       PrimaveraReader.java
 * author:     BahaaEldin A. Elsayed <Bhalddin@hotmail.com>
 * copyright:  (c) Lujytec Computer Systems 2016
 * date:       22/03/2010
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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.sf.mpxj.common.NumberHelper;
import net.sf.mpxj.listener.FieldListener;
import net.sf.mpxj.primavera.ChildEpsContainer;

/**
 * @author Bhalddin
 *
 */
public class Eps implements ChildEpsContainer, FieldContainer
{
   /**
    * Default constructor
    *
    *
    */
   public Eps(Eps parent)
   {
      m_parent = parent;
   }

   /**
    * @return Parent EPS
    */
   public Eps getParent()
   {
      return (m_parent);
   }

   /**
    * @return List of Children
    */
   public List<Eps> getChildren()
   {
      return (m_childern);
   }

   /**
    * This method is used to associate a child Eps with the current
    * Eps instance. It has been designed to
    * allow the hierarchical outline structure of Eps' in the Database
    * to be updated once all of the Eps data has been read.
    *
    * @param child child Eps
    */

   public void addChildEps(Eps child)
   {
      child.m_parent = this;
      m_children.add(child);
      setSummary(true);
      child.setOutlineLevel(Integer.valueOf(NumberHelper.getInt(getOutlineLevel()) + 1));
   }

   /**
    * This method is used to associate a child Eps with the current
    * Eps instance. It has package access, and has been designed to
    * allow the hierarchical outline structure of Eps in the database
    * to be constructed as the data is read in.
    *
    * @param child Child Eps.
    * @param childOutlineLevel Outline level of the child Eps.
    */
   public void addChildEps(Eps child, int childOutlineLevel)
   {
      int outlineLevel = NumberHelper.getInt(getOutlineLevel());

      if ((outlineLevel + 1) == childOutlineLevel)
      {
         m_children.add(child);
         setSummary(true);
      }
      else
      {
         if (m_children.isEmpty() == false)
         {
            (m_children.get(m_children.size() - 1)).addChildEps(child, childOutlineLevel);
         }
      }
   }

   /**
    * Inserts a child Eps prior to a given sibling Eps.
    *
    * @param child new child Eps
    * @param previousSibling sibling Eps
    */
   public void addChildEpsBefore(Eps child, Eps previousSibling)
   {
      int index = m_children.indexOf(previousSibling);
      if (index == -1)
      {
         m_children.add(child);
      }
      else
      {
         m_children.add(index, child);
      }

      child.m_parent = this;
      setSummary(true);
      child.setOutlineLevel(Integer.valueOf(NumberHelper.getInt(getOutlineLevel()) + 1));
   }

   /**
    * Removes a child Eps.
    *
    * @param child child Eps instance
    */
   public void removeChildEps(Eps child)
   {
      if (m_children.remove(child))
      {
         child.m_parent = null;
      }
      setSummary(!m_children.isEmpty());
   }

   /**
    * This method allows the list of child Eps' to be cleared in preparation
    * for the hierarchical Eps structure to be built.
    */
   public void clearChildEps()
   {
      m_children.clear();
      setSummary(false);
   }

   /**
    * The Outline Level field contains the number that indicates the level
    * of the Eps in the project outline hierarchy.
    *
    * @return int
    */
   public Integer getOutlineLevel()
   {
      return ((Integer) getCachedValue(EpsField.OUTLINE_LEVEL));
   }

   /**
    * The Outline Level field contains the number that indicates the level of
    * the Eps in the project outline hierarchy.
    *
    * @param val - int
    */
   public void setOutlineLevel(Integer val)
   {
      set(EpsField.OUTLINE_LEVEL, val);
   }

   /**
    * The Summary field indicates whether a Eps is a summary Eps.
    *
    * @param val - boolean
    */
   public void setSummary(boolean val)
   {
      set(EpsField.SUMMARY, val);
   }

   /**
    * Sets Name field value.
    *
    * @param val value
    */
   public void setName(String val)
   {
      set(EpsField.WBS_NAME, val);
   }

   /**
    * Gets Eps Name field value.
    *
    * @return value
    */
   public String getName()
   {
      return ((String) getCachedValue(EpsField.WBS_NAME));
   }

   public Date getStartDate()
   {
      return (Date) getCachedValue(EpsField.START);
   }

   public Date getFinishDate()
   {
      return (Date) getCachedValue(EpsField.FINISH);
   }

   /* (non-Javadoc)
    * @see net.sf.mpxj.primavera.ChildEpsContainer#getCildEPSs()
    */
   @Override public List<Eps> getChildEPSs()
   {
      return (m_children);
   }
   private List<Eps> m_children = new LinkedList<Eps>();

   /**
    * This method inserts a name value pair into internal storage.
    *
    * @param field task field
    * @param value attribute value
    */
   private void set(FieldType field, boolean value)
   {
      set(field, (value ? Boolean.TRUE : Boolean.FALSE));
   }

   /*
    * @see net.sf.mpxj.FieldContainer#set(net.sf.mpxj.FieldType, java.lang.Object)
    */
   @Override public void set(FieldType field, Object value)
   {
      if (field != null)
      {
         int index = field.getValue();
         if (m_eventsEnabled)
         {
            fireFieldChangeEvent((EpsField) field, m_array[index], value);
         }
         m_array[index] = value;
      }
   }

   /**
    * Handle the change in a field value. Reset any cached calculated
    * values affected by this change, pass on the event to any external
    * listeners.
    *
    * @param field field changed
    * @param oldValue old field value
    * @param newValue new field value
    */
   private void fireFieldChangeEvent(EpsField field, Object oldValue, Object newValue)
   {
      //
      // Internal event handling
      //
      // TODO: add internal event hanling for Eps
      //
      // External event handling
      //
      if (m_listeners != null)
      {
         for (FieldListener listener : m_listeners)
         {
            listener.fieldChange(this, field, oldValue, newValue);
         }
      }
   }

   /*
    * {@inheritDoc}
    */
   @Override public Object getCachedValue(FieldType field)
   {
      return (field == null ? null : m_array[field.getValue()]);
   }

   /*
    * {@inheritDoc}
    */
   @Override public Object getCurrentValue(FieldType field)
   {
      Object result = null;
      if (field != null)
      {
         result = m_array[field.getValue()];
      }
      return result;
   }

   /*
    * {@inheritDoc}
    */
   @Override public void addFieldListener(FieldListener listener)
   {
      if (m_listeners == null)
      {
         m_listeners = new LinkedList<FieldListener>();
      }
      m_listeners.add(listener);
   }

   /*
    * {@inheritDoc}
    */
   @Override public void removeFieldListener(FieldListener listener)
   {
      if (m_listeners != null)
      {
         m_listeners.remove(listener);
      }
   }

   /**
    * This method is used to set the value of a field in the eps.
    *
    * @param field field to be set
    * @param value new value for field.
    */
   //   private void set(FieldType field, boolean value)
   //   {
   //      set(field, (value ? Boolean.TRUE : Boolean.FALSE));
   //   }

   /**
    * Disable events firing when fields are updated.
    */
   public void disableEvents()
   {
      m_eventsEnabled = false;
   }

   /**
    * Enable events firing when fields are updated. This is the default state.
    */
   public void enableEvents()
   {
      m_eventsEnabled = true;
   }

   /**
    * {@inheritDoc}
    */
   @Override public String toString()
   {
      return ("[EPS id=" + getCachedValue(EpsField.WBS_ID) + " name=" + getName() + "]");
   }

   private Object[] m_array = new Object[EpsField.MAX_VALUE];

   private Eps m_parent;

   private List<Eps> m_childern = new LinkedList<Eps>();

   private boolean m_eventsEnabled = true;

   private List<FieldListener> m_listeners;
}
