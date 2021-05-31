package org.sam.mines.examples.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface RequestHandler {
    String handle(String request);

    default RequestHandler append(RequestHandler requestHandler){
        return request -> handle(request) +"," + requestHandler.handle(request);
    }
}

public class ChainOfResponsibilityTest {

    @Test
    void shouldChainRequest(){
        RequestHandler requestHandler =
                ((RequestHandler) request1 -> "First " + request1)
                .append(request -> "Second " + request)
                .append(request -> "Third " + request)
                .append(request -> {
                    // The request can be handled conditionally
                    if (request.equals("request")){
                        return "Fourth " + request;
                    }else {
                        return "";
                    }
                });

        assertEquals("First request,Second request,Third request,Fourth request", requestHandler.handle("request"));
        assertEquals("First otherRequest,Second otherRequest,Third otherRequest,", requestHandler.handle("otherRequest"));
    }
}
