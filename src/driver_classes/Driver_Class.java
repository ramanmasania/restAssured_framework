package driver_classes;

import java.io.IOException;

import test_class.GetTc1;

import test_class.PatchTc1;
import test_class.PostTc1;
import test_class.PutTc1;

public class Driver_Class {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PostTc1.orchestrator();
		PutTc1.orchestrator();
		PatchTc1.orchestrator();
		GetTc1.orchestrator();
		
	
	}
}
