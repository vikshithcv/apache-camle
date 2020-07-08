package com.javainuse;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
 
@Component
public class MyRoute extends RouteBuilder {
 
    @Override
    public void configure() throws Exception {
    	from("direct:Test123").routeId("direct:Test123")
    	.log("Test")
    	.to("log:INFO?showBody=true&showHeaders=true")
		.log("===>1 ${body}")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				
				exchange.getOut().setBody(exchange.getIn().getBody());
			}
		});
    	
    	//from("file:C://inputFolder?delete=true").to("file:C://outputFolder");
    }
}
