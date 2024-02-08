package com.bosonit.formacion.controller;

import com.bosonit.formacion.model.Greeting;
import com.bosonit.formacion.model.Results;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }

    @PostMapping()
    public Greeting addGreeting(@RequestBody Greeting greeting){
        return greeting;
    }

    @GetMapping("/user/{id}")
    public int getId(@PathVariable int id){
        return id;
    }

    @PutMapping("/post")
    public HashMap<?,?> putVars(@RequestParam HashMap<?,?> vars){
        return vars;
    }

    @GetMapping("/header")
    public HashMap<String,String> getHeaders(@RequestHeader HashMap<String,String> headers){
        HashMap<String,String> newHeaders = new HashMap<>();
        if(headers.containsKey("h1"))
            newHeaders.put("h1", headers.get("h1"));
        if(headers.containsKey("h2"))
            newHeaders.put("h2", headers.get("h2"));
        return newHeaders;
    }

    @PostMapping("/all")
    public Results getAll(
            @RequestHeader Map<String,String> headers,
            @RequestParam Map<String,String> params,
            @RequestBody(required = false) String body){
        Results results = new Results();
        results.setHeaders(new ArrayList<>(headers.values()));
        results.setParams(new ArrayList<>(params.values()));
        results.setBody(body);
        return results;
    }
}
