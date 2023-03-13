package com.mahindra.annualsurvey.utils;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mahindra.annualsurvey.dto.EmailEntity;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;

public class AzureTableStorage {

	// Define the connection-string with your values.
	public static final String storageConnectionString =
	    "DefaultEndpointsProtocol=http;" +
	    "AccountName=mcaresdevstor;" +
	    "AccountKey=O83v+oOC41Ukr33DOqo1W0ilpgFbV3aGJHFbdYQw7KqlJaRXc8r1LtZURgU7Zd3uXlClm/5GEX0xKiiVi6pryw==";


    public AzureTableStorage() {
    }
    static final Logger log = Logger.getLogger(AzureTableStorage.class);
  //  static Logger log = Logger.getLogger(Logger.class);

   // public static void main(String argsp[]) {
    	public static void createEmailLogs(EmployeeDTO employee,String rowKey,String emailId) {
    	
        //log.debug("Here is some DEBUG");
        //log.info("Here is some INFO");
        //log.warn("Here is some WARN");
        //log.error("Here is some ERROR");
        //log.fatal("Here is some FATAL");
    	log.info("AzureTableStorage Started.");
    	
    	
    	// Retrieve storage account from connection-string.
    	/*String storageConnectionString =
    	    RoleEnvironment.getConfigurationSettings().get("StorageConnectionString");
    	*/
    	
    	
    	
    	try
    	{
    	    // Retrieve storage account from connection-string.
    	    CloudStorageAccount storageAccount =
    	    CloudStorageAccount.parse(storageConnectionString);

    	    // Create the table client.
    	    CloudTableClient tableClient = storageAccount.createCloudTableClient();

    	    
    	    
    	    // Create a cloud table object for the table.
    	    CloudTable cloudTable = tableClient.getTableReference("AnnualSurveyEmail");
    	    String primaryKey=employee.getEmpId();
    	    // Create a new customer entity.
    	    
    	    
    	    EmailEntity customer1 = new EmailEntity(primaryKey,rowKey);
    	    customer1.setEmailId(emailId);
    	    customer1.setTime(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
    	    customer1.setUserId(employee.getEmpId());
    	 //   customer1.setSector(employee.getSector());
           
    	    // Create an operation to add the new customer to the people table.
    	    TableOperation insertCustomer1 = TableOperation.insertOrMerge(customer1);

    	    // Submit the operation to the table service.
    	    cloudTable.execute(insertCustomer1);
    	}
    	catch (Exception e)
    	{
    	    // Output the stack trace.
    	    e.printStackTrace();
    	}

    	log.info("AzureTableStorage ended.");
    	
    }
}
