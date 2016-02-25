package com.capgemini.resilience.costcenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EurekaApplication.class)
@WebAppConfiguration
public class EurekaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
