package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.commonMethodUtilities;
import common_method.common_method_patch;
import io.restassured.path.json.JsonPath;
import request_repository.Patch_Request_Repository;

public class PatchTc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "";
		int responseStatuscode = 0;
		String baseUri = Patch_Request_Repository.baseuri();
		String resource = Patch_Request_Repository.resource();
		String requestBody = Patch_Request_Repository.Patch_request_tc1();
		for(int i=0;i<5;i++)
		{
			responseStatuscode = common_method_patch.responsestatuscode_extractor(baseUri, resource, requestBody);
			if(responseStatuscode == 200)
			{
				responseBody = common_method_patch.responsebody_extractor(baseUri, resource, requestBody);
				responseBodyValidator(responseBody);
				break;
			}
			else
			{
				System.out.println("Correct status is not found in the iteration" +i);
			}
		}
		commonMethodUtilities.evidenceFileCreator("PatchTc1",requestBody,responseBody);
		Assert.assertEquals(responseStatuscode, 200);
	}
	public static void responseBodyValidator(String responseBody)
	{
		// Create JsonPath object to extract responsebody parameter
		
		JsonPath jsp = new JsonPath(responseBody);
		
		// extract responsebody parameter
		
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_updatedAt = jsp.getString("updatedAt");
		//System.out.println("Name :"+res_name+ "\nJob :"+res_job+"\nUpdatedAt :"+res_updatedAt);
		
		// Validate responsebody parameter
		
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		
		// extract date from updatedAt parameter
		
		String actual_date = res_updatedAt.substring(0,10);
		String current_date = LocalDate.now().toString();
		Assert.assertEquals(actual_date, current_date);
		//System.out.println("Actual Date :"+actual_date+ "\nCurrent Date :"+current_date);	
	}

}
