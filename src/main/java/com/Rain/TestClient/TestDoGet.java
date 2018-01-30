package com.Rain.TestClient;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.databene.benerator.anno.Source;
import org.databene.feed4testng.FeedTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
//http://192.168.52.131:8080/Test/TestServlet?a=1&b=2
public class TestDoGet extends FeedTest{
	@Test(dataProvider="feeder")
	@Source("./data/add.csv")

	public void doGet(String a,String b,String c) throws Exception{
		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("192.168.52.131")
				.setPort(8080)
				.setPath("/Test/TestServlet")
				.setParameter("a",a)
				.setParameter("b",b)
				.build();
		HttpGet request = new HttpGet(uri);
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity);
		Assert.assertEquals(str, c);
	}
}
