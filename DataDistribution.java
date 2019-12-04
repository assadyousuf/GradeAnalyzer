import java.util.ArrayList;
import java.text.DecimalFormat;

public class DataDistribution {

	

	
	public static String displayDistrbution(ArrayList<Double> dataSet) {
		String distribution="";
		DecimalFormat df=new DecimalFormat("###.##");
		
		if(dataSet.size()==0) {
			return null;
		} else {
			double sum=0;
			double total=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 0 <= dataSet.get(i)  && dataSet.get(i)< 10) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			double avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 0->10%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			
			
			sum=0;
			total=0;
			avg=0;
			for(int i=0; i<dataSet.size(); i++) {
				if( 10 <= dataSet.get(i)  && dataSet.get(i)< 20) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 10->20%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 20 <= dataSet.get(i)  && dataSet.get(i)< 30) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 20->30%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 30 <= dataSet.get(i)  && dataSet.get(i)< 40) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 30->40%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 40 <= dataSet.get(i)  && dataSet.get(i)< 50) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 40->50%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 50 <= dataSet.get(i)  && dataSet.get(i)< 60) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 50->60%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 60 <= dataSet.get(i)  && dataSet.get(i)< 70) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 60->70%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 70 <= dataSet.get(i)  && dataSet.get(i)< 80) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 70->80%: ";
			distribution=distribution+ Double.toString(avg) + "%\n";
			
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 80 <= dataSet.get(i)  && dataSet.get(i)< 90) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 80->90%: ";
			distribution=distribution+ Double.toString(avg) + "\n";
			
			
			
			
			
			sum=0;
			total=0;
			avg=0;
			
			for(int i=0; i<dataSet.size(); i++) {
				if( 90 <= dataSet.get(i)  && dataSet.get(i)<= 100) {
					sum=sum+dataSet.get(i);
					total++;
				}
			}
			avg=sum/total;
			distribution=distribution+ "Count: "+total + " ";
			distribution = distribution +" Average in 90->100+%: ";
			distribution=distribution+ Double.toString(avg) + "\n";
			
			
			
		
			
			
			
		
			
			
		
		
		
		return distribution;
	}
	
	
	}
}
