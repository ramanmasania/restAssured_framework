package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.commonMethodUtilities;
import common_method.common_method_put;
import io.restassured.path.json.JsonPath;
import request_repository.Put_Request_Repository;

public class PutTc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "";
		int responseStatuscode = 0;
		String baseUri = Put_Request_Repository.baseuri();
		String resource = Put_Request_Repository.resource();
		String requestBody = Put_Request_Repository.Put_request_tc1();
		for (int i=0;i<5;i++)
		{
			responseStatuscode = common_method_put.responsestatuscode_extractor(baseUri, resource, requestBody);
			if(responseStatuscode == 200)
			{
				responseBody = common_method_put.responsebody_extractor(baseUri, resource, requestBody);
				responseBodyValidator(responseBody);
				break;
			}
			else
			{
				System.out.println("Correct status code is not found in the iteration " +i);
			}
			
		}
		commonMethodUtilities.evidenceFileCreator("PutTc1", requestBody, responseBody);
		Assert.assertEquals(responseStatuscode, 200);
	}
	
	public static void responseBodyValidator(String responseBody)
	{
		// create JsonPath object to extract responsebody parameters
		
		JsonPath jsp = new JsonPath(responseBody);
		
		// extract responsebody parameter
		
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String updatedAt = jsp.getString("updatedAt");
		//System.out.println("Name : " +res_name+ "\nJob : " +res_job+ "\nupdatedAt : " +updatedAt);
		
		// validate responsebody parameter
		
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		
		//extract date from updatedAt parameter
		
		String actual_date = updatedAt.substring(0,10);
		String current_date = LocalDate.now().toString();
		Assert.assertEquals(actual_date,current_date );
		//System.out.println("Actual Date : " +actual_date + "\nCurrent Date : " +current_date);
		
	}

}
