/**
 *
 */

package net.sf.mpxj;

import java.util.EnumSet;
import java.util.Locale;

/**
 * @author Bhalddin
 *
 */
public enum EpsField implements FieldType
{
   START(DataType.DATE),
   WBS_ID(DataType.INTEGER),
   PROJ_ID(DataType.INTEGER),
   OBS_ID(DataType.INTEGER),
   SEQ_NUM(DataType.INTEGER),
   PHASE_ID(DataType.INTEGER),
   PARENT_WBS_ID(DataType.INTEGER),
   EV_USER_PCT(DataType.INTEGER),
   RESP_TEAM_ID(DataType.INTEGER),
   ITERATION_ID(DataType.INTEGER),
   STATUS_REVIEWER(DataType.INTEGER),
   DELETE_SESSION_ID(DataType.INTEGER),
   ANTICIP_START_DATE(DataType.DATE),
   ANTICIP_END_DATE(DataType.DATE),
   UPDATE_DATE(DataType.DATE),
   CREATE_DATE(DataType.DATE),
   DELETE_DATE(DataType.DATE),
   EST_WT(DataType.NUMERIC),
   EV_ETC_USER_VALUE(DataType.NUMERIC),
   ORIG_COST(DataType.NUMERIC),
   INDEP_REMAIN_TOTAL_COST(DataType.NUMERIC),
   ANN_DSCNT_RATE_PCT(DataType.NUMERIC),
   INDEP_REMAIN_WORK_QTY(DataType.NUMERIC),
   ORIGINAL_QTY(DataType.NUMERIC),
   RQMT_REM_QTY(DataType.NUMERIC),
   PROJ_NODE_FLAG(DataType.STRING),
   SUM_DATA_FLAG(DataType.STRING),
   STATUS_CODE(DataType.STRING),
   WBS_SHORT_NAME(DataType.STRING),
   WBS_NAME(DataType.STRING),
   DSCNT_PERIOD_TYPE(DataType.STRING),
   CREATE_USER(DataType.STRING),
   EV_COMPUTE_TYPE(DataType.STRING),
   EV_ETC_COMPUTE_TYPE(DataType.STRING),
   GUID(DataType.STRING),
   TMPL_GUID(DataType.STRING),
   INTG_TYPE(DataType.STRING),
   UPDATE_USER(DataType.STRING),
   SUMMARY(DataType.BOOLEAN),
   OUTLINE_LEVEL(DataType.SHORT),
   LAST_RECALC_DATE(DataType.DATE),
   FINISH(DataType.DATE);

   /**
    * Constructor.
    *
    * @param dataType field data type
    * @param unitsType units type
    */
   private EpsField(DataType dataType, FieldType unitsType)
   {
      m_dataType = dataType;
      m_unitsType = unitsType;
   }

   private EpsField(DataType dataType)
   {
      this(dataType, null);
   }

   /*
    *  {@inheritDoc}
    */
   @Override public int getValue()
   {
      return (m_value);
   }

   /*
    *  {@inheritDoc}
    */
   @Override public FieldTypeClass getFieldTypeClass()
   {
      return FieldTypeClass.EPS;
   }

   /*
    *  {@inheritDoc}
    */
   @Override public String getName()
   {

      return (getName(Locale.ENGLISH));
   }

   /*
    *  {@inheritDoc}
    */
   @Override public String getName(Locale locale)
   {
      String[] titles = LocaleData.getStringArray(locale, LocaleData.EPS_COLUMNS);
      String result = null;
      if (m_value >= 0 && m_value < titles.length)
      {
         result = titles[m_value];
      }
      return result;
   }

   /*
    * {@inheritDoc}
    */
   @Override public DataType getDataType()
   {
      return (m_dataType);
   }

   /*
    *  {@inheritDoc}
    */
   @Override public FieldType getUnitsType()
   {
      return (m_unitsType);
   }

   /**
    * Retrieves the string representation of this instance.
    *
    * @return string representation
    */
   @Override public String toString()
   {
      return (getName());
   }

   /**
    * This method takes the integer enumeration of a resource field
    * and returns an appropriate class instance.
    *
    * @param type integer resource field enumeration
    * @return ResourceField instance
    */
   public static EpsField getInstance(int type)
   {
      EpsField result = null;
      if (type >= 0 && type < MAX_VALUE)
      {
         result = TYPE_VALUES[type];
      }
      return (result);
   }
   public static final int MAX_VALUE = EnumSet.allOf(EpsField.class).size();
   private static final EpsField[] TYPE_VALUES = new EpsField[MAX_VALUE];
   static
   {
      int value = 0;
      for (EpsField e : EnumSet.allOf(EpsField.class))
      {
         e.m_value = value++;
         TYPE_VALUES[e.getValue()] = e;
      }
   }
   private int m_value;
   private DataType m_dataType;
   private FieldType m_unitsType;
}
