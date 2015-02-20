
/**
 * e-Science Central Copyright (C) 2008-2013 School of Computing Science,
 * Newcastle University
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation at: http://www.gnu.org/licenses/gpl-2.0.html
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, 5th Floor, Boston, MA 02110-1301, USA.
 */
import com.connexience.server.workflow.cloud.services.*;
import com.connexience.server.workflow.api.*;

import org.pipeline.core.data.*;
import org.pipeline.core.data.columns.IntegerColumn;
import org.pipeline.core.data.manipulation.ColumnPicker;
import org.pipeline.core.data.manipulation.ColumnSorter;

public class MyService extends CloudDataProcessorService {

    /**
     * This method is call when a service is about to be started. It is called
     * once regardless of whether or not the service is streaming data. Code
     * that is needed to set up information that needs to be preserved over
     * multiple chunks should be executed here.
     */
    public void executionAboutToStart() throws Exception {
    }

    /**
     * This is the main service execution routine. It is called once if the
     * service has not been configured to accept streaming data or once for each
     * chunk of data if the service has been configured to accept data streams
     */
    public void execute() throws Exception {
        // This is the API link that can be used to access data in the
        // Inkspot platform. It is configured using the identity of
        // the user executing the service
      //  APIBroker api = createApiLink();
    	 Data inData = getInputDataSet("input-1");
    	 Data output = new Data();
       Data temp= new Data();
       
         int size = inData.getColumns();
         for(int i=size-1;i>=0;i--)
         {  output.addColumn(inData.column(i).getCopy());}
         Data row; 
        
         size=inData.column(0).getRows();
        for(int i=size-1;i>=0;i--)
         {   
        	 row=output.getRowSubset(0, 0, true);
       	 temp.appendRows(row, false);
         }
         setOutputDataSet("output-1", temp);
    }

    /**
     * All of the data has been passed through the service. Any clean up code
     * should be placed here
     */
    public void allDataProcessed() throws Exception {
    }
    
  
}