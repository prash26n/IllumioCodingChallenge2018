package code;

/**
 * 
 * @author Prashant Singh
 * 
 * This class contains the different fields for a valid Firewall rule that is allowed in the network. 
 * A rule is equal to the other rule only if all of direction, protocol, port and IP Address match for that rule.
 *
 */

public class AllowedRules {

	 private String direction;
	 private String protocol;
	 private int port;
	 private long ipAddress;


     public AllowedRules(String direction, String protocol, String port, String ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = Integer.parseInt(port);
         this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); 
     }

     public AllowedRules(String direction, String protocol, String port, long ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = Integer.parseInt(port);
         this.ipAddress = ipAddress;
     }

     public AllowedRules(String direction, String protocol, int port, long ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = port;
         this.ipAddress = ipAddress;
     }

     public AllowedRules(String direction, String protocol, int port, String ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = port;
         this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); //convert string ipAddress with periods to just a number
     }
     
     @Override
     public boolean equals(Object object) {
         if (this == object) return true;
         
         if (!(object instanceof AllowedRules)) return false;
         
         AllowedRules networkRule = (AllowedRules) object;
         
         //return true if all conditions are satisfied
         return  direction.equalsIgnoreCase(networkRule.direction) && protocol.equalsIgnoreCase(networkRule.protocol)
        		 && port == networkRule.port && ipAddress == networkRule.ipAddress;
     }

     @Override
     public String toString() {
         return this.direction +  ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.ipAddress);
     }


     public int hashCode() {
         long hash =  99 * (this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode()); //get unique key from all the components
         return Long.valueOf(hash).hashCode();
     }
     
}
