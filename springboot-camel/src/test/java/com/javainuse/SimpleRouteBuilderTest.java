package com.javainuse;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ai.grakn.redismock.RedisServer;

@RunWith(SpringRunner.class)
@SpringBootApplication
@SpringBootTest(classes = SimpleRouteBuilderTest.class)
public class SimpleRouteBuilderTest  extends Assert {

	/*
	 * @EndpointInject(uri = "mock:Test123") MockEndpoint mock;
	 */

    @Autowired
    ProducerTemplate producerTemplate;
    
    private static RedisServer server = null;

    @Before
    public void before() throws IOException {
      server = RedisServer.newRedisServer();  // bind to a random port
      server.start();
    }

	@Test
	public void testPayloadIsTransformed() 
			throws InterruptedException {
		
		String msg = "<name>test1</name>";
       // mock.expectedBodiesReceived(msg);
        
        Map<String, Object> headersMap = new HashMap<String, Object>();
        
        //producerTemplate.sendBody("direct:Test123", msg);
        String str = (String) producerTemplate.sendBodyAndHeaders("direct:Test123",
				ExchangePattern.InOut, msg, headersMap);
        
        System.out.println("====>>>"+str);
        assertEquals(msg, str);
        // Then
        //mock.assertIsSatisfied();
	}
	
	@After
	public void after() {
	  server.stop();
	  server = null;
	}

}
