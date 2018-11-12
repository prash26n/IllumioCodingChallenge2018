package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import code.Firewall;
/**
 * 
 * @author Prashant Singh
 * 
 * Junit testcases for checking the Firewall class. - init
 *
 */

public class Testcases {

	static Firewall f = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		f = new Firewall("networkrules.csv");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAcceptPacket() {
		boolean acceptPacket = f.acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
		assertEquals("This will be true rule 2 of config", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket1() {
		boolean acceptPacket = f.acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
		assertEquals("This will be true rule 2 of config", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket3() {
		boolean acceptPacket = f.acceptPacket("inbound", "tcp",80,"192.168.1.2");
		assertEquals("This will be true rule 1 of config", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket4() {
		boolean acceptPacket = f.acceptPacket("inbound", "udp",53,"192.168.2.1");
		assertEquals("This will be true rule 3 of config", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket5() {
		boolean acceptPacket = f.acceptPacket("outbound", "tcp",10234,"192.168.10.11");
		assertEquals("This will be true rule 2 of config", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket6() {
		boolean acceptPacket = f.acceptPacket("inbound", "tcp",81,"192.168.1.2");
		assertEquals("This will be false - no match", false, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket7() {
		boolean acceptPacket = f.acceptPacket("inbound", "udp",24,"52.12.48.92");
		assertEquals("This will be false - no match", false, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket8() {
		boolean acceptPacket = f.acceptPacket("inbound", "tcp",80,"192.168.1.322");
		assertEquals("This will be false - no match", false, acceptPacket);
	}
	
	
	@Test
	public void testAcceptPacket9() {
		boolean acceptPacket = f.acceptPacket("inbound", "udp",43,"12.53.6.25");
		assertEquals("This will be false - no match", false, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket10() {
		boolean acceptPacket = f.acceptPacket("inbound", "tcp",673,"123.45.56.83");
		assertEquals("This will be true - rule 5 matches", true, acceptPacket);
	}
	
	
}
