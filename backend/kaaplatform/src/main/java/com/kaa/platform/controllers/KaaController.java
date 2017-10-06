package com.kaa.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaa.platform.authentications.*;
import com.kaa.platform.services.CreateJSON;
import com.kaa.platform.services.KaaSendMessage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping(value = "/test/api")
public class KaaController {
   private Logger log = LogManager.getLogger(KaaController.class);
   private static String bodyLog;
   
   //Get log value from kaa:
   @RequestMapping(value = "/kaademo", method = RequestMethod.POST)
   public void getLog(@RequestBody String body){
	   System.out.println("Co tac dong!!!!");
	   bodyLog = body;
	   System.out.println("Body------>>>"+body);
	   String[] array = {"Device thay doi 2","Device thay doi 1","Device thay doi 3"};
	   Random rand = new Random();
	   CreateJSON createJSON = new CreateJSON();
	   KaaSendMessage kaaSendMessage = new KaaSendMessage();
	   //
	   if(body!=null){
		   Timer timer = new Timer();
		   timer.schedule(new TimerTask() {
		   @Override
		   public void run() {
				// TODO Auto-generated method stub
			    int radom = rand.nextInt(3);
			    createJSON.createJsonFile(array[radom]);
			    kaaSendMessage.sendMessage(createJSON.JSON_PATH);
			}
		},1500);
	   }
    }
   //Test examples:
   @RequestMapping(value = "/test", method = RequestMethod.GET)
   @ResponseBody
   public String test() throws ClientProtocolException, IOException, ParseException{
       KaaAuthentication kaaAuthentication = new KaaAuthentication();
       String body = kaaAuthentication.kaaAuthen("http://localhost:8088/kaaAdmin/rest/api/users", "admin", "admin123");
       JSONParser parser = new JSONParser();
       JSONArray array = (JSONArray) parser.parse(body);
       Object[] jsonObjects = array.toArray();
       JSONObject jsonObject = (JSONObject)jsonObjects[0];
	   return jsonObject.get("id").toString();
   }
   
   
}
