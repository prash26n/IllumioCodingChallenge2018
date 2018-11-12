package code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * 
 * @author Prashant Singh
 * 
 * This class is the actual Firewall. It constructs an object based on the rules defined in the networkrules.csv 
 * 
 *
 */

public class Firewall {

	//A hashmap to store an allowed rule and an Integer to check validity of the rule.
	
		static HashMap<AllowedRules,Integer> allowedRules = new HashMap<AllowedRules,Integer>();

		public Firewall(String filename){

			try(BufferedReader br = new BufferedReader(new FileReader(filename))) {

				String line;
				while((line = br.readLine()) != null) {

					String [] rule = line.split(",");

					/* When rule has range of ports*/
					if (rule[2].contains("-")) { 
						String [] portRanges = rule[2].split("-");
						int portMin = Integer.parseInt(portRanges[0]);
						int portMax = Integer.parseInt(portRanges[1]);
						int portRange = portMax - portMin;

						/* When rule has range in octet of ip address */
						if (rule[3].contains("-")) {
							String [] ipRange = rule[3].split("-");
							long ipAddressMin = Long.parseLong(ipRange[0].replaceAll("\\.", ""));
							long ipAddressMax = Long.parseLong(ipRange[1].replaceAll("\\.", ""));
							long totalIP = ipAddressMax - ipAddressMin;

							//iterate through all possible ports and ips and add them to set
							for (int i = 0; i <= portRange; i++) {
								for (int j = 0; j <= totalIP; j++) {
									AllowedRules currRule = new AllowedRules(rule[0], rule[1], portMin + i, ipAddressMin + j);
									allowedRules.put(currRule,1);
								}
							}

							//iterate through all possible ports and ips and add them to set
							for (int i = 0; i <= portRange; i++) {
								for (int j = 0; j <= totalIP; j++) {
									AllowedRules currRule = new AllowedRules(rule[0], rule[1], portMin + i, ipAddressMin + j);
									allowedRules.put(currRule,1);
								}
							}
						}else{

							//iterate through all possible ports add them to mssetap
							for (int i = 0; i <= portRange; i++) {
								AllowedRules currRule = new AllowedRules(rule[0],rule[1], portMin + i, rule[3]);
								allowedRules.put(currRule,1);
							}
						}
					}  

					else /* When rule has no range of ports*/

					{ 
						/* When rule has  range of ip address*/
						if (rule[3].contains("-")) {
							String [] ipRange = rule[3].split("-");
							long ipAddressMin = Long.parseLong(ipRange[0].replaceAll("\\.", ""));
							long ipAddressMax = Long.parseLong(ipRange[1].replaceAll("\\.", ""));
							long totalIP = ipAddressMax - ipAddressMin;

							//iterate through all possible ips add them to map
							for (int i = 0; i <= totalIP; i++) {
								AllowedRules currRule = new AllowedRules(rule[0],rule[1],rule[2], ipAddressMin + i);
								allowedRules.put(currRule,1);
							}
						}
						else 
							/* When rule has  no range of ip address*/
						{ 
							AllowedRules currRule = new AllowedRules(rule[0],rule[1],rule[2],rule[3]);
							allowedRules.put(currRule,1);
						}

					}

				}
			}
			catch (FileNotFoundException e) {
				System.out.println("Unable to find the specified file "+ filename );
			}
			catch(Exception e) {
				System.out.println("Exeption occured " + e.getMessage());
			}
		}

		/**
		 * This function is used to check the validity of an incoming netowrk configuration.
		*/
		
		public boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
			AllowedRules rule = new AllowedRules(direction, protocol, port, ipAddress);
			if (allowedRules.containsKey(rule)) {
				return true;
			}
			else {
				return false;
			}

		}
}
