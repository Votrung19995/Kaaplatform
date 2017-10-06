package com.kaa.platform.services;

import java.io.File;
import java.io.FileInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

import com.kaa.platform.authentications.KaaAuthentication;

public class KaaSendMessage {
	public KaaSendMessage() {
             
	}

	public void sendMessage(String path) {
		KaaAuthentication kaaAuthentication = new KaaAuthentication();
		File file = new File(path);
		HttpClientContext context = (HttpClientContext)kaaAuthentication.getAuthentication("localhost", 8088, "http", "devuser", "devuser123");
		//send message:
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			JSONObject notification = new JSONObject();
			notification.put("applicationId", "229376");
			notification.put("schemaId","589829");
			notification.put("topicId","131072");
			notification.put("type", "USER");
			builder.addTextBody("notification", notification.toJSONString(), ContentType.APPLICATION_JSON);
			// This attaches the file to the POST:
			File f = new File(path);
			builder.addBinaryBody("file", new FileInputStream(f), ContentType.APPLICATION_OCTET_STREAM, f.getName());
			System.out.println("File name:"+f.getName());
			HttpEntity multipart = builder.build();
			HttpPost post = new HttpPost("http://localhost:8088/kaaAdmin/rest/api/sendNotification");
			post.setEntity(multipart);
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpResponse response = httpclient.execute(post,context);
			System.out.println("Status: "+response.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
