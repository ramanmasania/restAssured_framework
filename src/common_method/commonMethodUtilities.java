package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class commonMethodUtilities {
	
	public static void evidenceFileCreator(String fileName,String request,String response) throws IOException
	{
		File newTextFile = new File("E:\\restAssuredEvidence\\" + fileName + ".txt");
		if(newTextFile.createNewFile())
		{
		FileWriter dataWriter = new FileWriter(newTextFile);
		dataWriter.write("Requestbody is : \n " +request+ "\n\n");
		dataWriter.write("\nResponsebody is :\n" +response);
		dataWriter.close();
		System.out.println("Requestbody and Responsebody data stored in : " +newTextFile.getName());
		}
		
	else
	{
		System.out.println(newTextFile.getName()+ " Already exist take a backup of it ! ");
	}
	}

}
