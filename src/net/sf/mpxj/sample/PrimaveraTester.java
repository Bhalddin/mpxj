/**
 *
 */

package net.sf.mpxj.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import net.sf.mpxj.Eps;
import net.sf.mpxj.EpsField;
import net.sf.mpxj.MPXJException;
import net.sf.mpxj.primavera.PrimaveraDatabaseReader;

/**
 * @author Bhalddin
 *
 */
public class PrimaveraTester
{

   /**
    * @param args
    * @throws MPXJException
    */
   public static void main(String[] args) throws MPXJException
   {

      //
      // Load the JDBC driver
      //
      String driverClass = "net.sourceforge.jtds.jdbc.Driver";
      try
      {
         Class.forName(driverClass);
      }
      catch (ClassNotFoundException ex)
      {
         // TODO Auto-generated catch block
         ex.printStackTrace();
      }

      //
      // Open a database connection. You will need to change
      // these details to match the name of your server, database, user and password.
      //

      try
      {
         String connectionString = "jdbc:jtds:sqlserver://ae-epso-isd-bsd/PMDB;user=sa;password=45-B4-44-33-7D-B0";
         Connection c;
         c = DriverManager.getConnection(connectionString);
         PrimaveraDatabaseReader reader = new PrimaveraDatabaseReader();
         reader.setConnection(c);

         Map<Integer, Eps> wbs = reader.listEPS();

         for (Map.Entry<Integer, Eps> entry : wbs.entrySet())
         {
            System.out.println(entry.getValue().getCachedValue(EpsField.WBS_NAME));
            System.out.println(entry.getValue().getCachedValue(EpsField.WBS_SHORT_NAME));
            System.out.println(entry.getValue().getCachedValue(EpsField.WBS_ID));
            System.out.println(entry.getValue().getCachedValue(EpsField.PARENT_WBS_ID));
            System.out.println(entry.getValue().getCachedValue(EpsField.START));
            System.out.println(entry.getValue().getCachedValue(EpsField.FINISH));
         }

      }
      catch (SQLException ex)
      {
         // TODO Auto-generated catch block
         ex.printStackTrace();
      }

   }

}
