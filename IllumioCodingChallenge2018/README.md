### Testing
I tested the solution using JUnit 5 based testcases. All testcases passed the configuration. I did not write testcases for invalid network rule configuration (like integer/long ranges, invalid IP addresses) as it was mentioned that the configuration settings are valid. 

### Design Decisions
Interestingly, the design i assumed better to use was to have a HashMap because as per the instructions the runtime calculation only matters after the constructor has successfully returned. Therefore, without the constraint of performance on how we create the allowed rules object, i focussed on the lookup performance. HashMap having constant O(1) lookup for keys seemed the best option. 
Also, using a HashMap<AllowedRules,Integer> configuration is good considering a future design, where the integer flag can be used to distinguish between "Allow" or "Blocked" rules.

If i had more time, i would have tried to write code in a way that the constructor is also efficient. Also to improve the space occupied by all the network rules, stored individually, i would have tried to re-form the datastructure so that the range values could be stored key:value pair with constant lookup for range. Without actually having to iterate through the range(s) of port and the IP address.

### Order of preference for teams

> Platform team - My 5 years of experience with back-end based frameworks like Spring in Java language would be great for me to use and improve my skills with this time.

> Policy team

> Data team
