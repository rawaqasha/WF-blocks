
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
import java.io.File;

import com.connexience.server.workflow.cloud.services.*;
import com.connexience.server.workflow.engine.datatypes.FileWrapper;
//import com.connexience.server.workflow.api.*;
//import com.connexience.server.workflow.util.ZipUtils;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import org.pipeline.core.data.*;

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
        // APIBroker api = createApiLink();

        // The following method is used to get a set of input data
        // and should be configured using the name of the input
        // data to fetch
    	 FileWrapper inputFile = (FileWrapper)getInputData("input-1");
         if (inputFile.getFileCount() != 1) {
             throw new Exception("ReadObject only operates on a single input file.");
         }
         File workingDir1 = getWorkingDirectory();
         File sourceFile = inputFile.getFile(0);
         FileOutputStream fos = new FileOutputStream(workingDir1.toString()+"/"+"zipfile.zip");
         ZipOutputStream zos = new ZipOutputStream(fos);
         //add a new Zip Entry to the ZipOutputStream
         ZipEntry ze = new ZipEntry(sourceFile.getName());
         zos.putNextEntry(ze);
         //read the file and write to ZipOutputStream
         FileInputStream fis = new FileInputStream(sourceFile);
         byte[] buffer = new byte[1024];
         int len;
         while ((len = fis.read(buffer)) > 0) {
             zos.write(buffer, 0, len);
         }
          
         //Close the zip entry to write to zip file
         zos.closeEntry();
         //Close resources
        zos.close();
         fis.close();
         fos.close();
         //FileOutputStream output= new FileOutputStream(zos.toString());
         File output=new File("zipfile.zip");
         //File zipout= new File(output);
         File workingDir = getWorkingDirectory();
        FileWrapper FW=new FileWrapper(workingDir);
        FW.addFile(output.toString());
         setOutputData("output-1", FW);
       
    }

   
	/**
     * All of the data has been passed through the service. Any clean up code
     * should be placed here
     */
    public void allDataProcessed() throws Exception {
    }
}