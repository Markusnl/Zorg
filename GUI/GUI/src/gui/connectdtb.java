package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class connectdtb 
{
        //String url = "http://markvandegiessen.com/zorg/";
        String url = "https://145.24.222.230";
        
      	
	/**
	 * print alle clienten met de bijbehorende gegeven naam, ook de optie iedereen uit te printen mits de parameter all te gebruiken
	 * @param naam De op te zoeken client (kan ook 'all' zijn voor iedereen)
	 * @throws Exception kan meerdere exeptions werpen
	 */
	// all = iedereen -> naam == specifiek
	protected String getClienten(String naam) throws Exception 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
                
		boolean found = false;
                String returnclient="";
	
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "get_clienten"));
			nvps.add(new BasicNameValuePair("clientnaam", naam));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine =in.readLine()) != null)
				{
                                    found=true;
                                    returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                    return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                } 
	}
        
        protected String getmedicijnHoeveelheid(String naam) throws Exception 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action","get_medicatie"));
			nvps.add(new BasicNameValuePair("clientnaam", naam));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine =in.readLine()) != null)
				{
					found=true;
                                        returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                } 
	}
        
        
        protected String loginCheck(String naam, String password) throws Exception 
        {    
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url); //markvandegiessen.com/zorg/
			List<NameValuePair> nvps = new ArrayList<NameValuePair>(); //http://145.24.222.230
                        nvps.add(new BasicNameValuePair("action", "validate_user"));
			nvps.add(new BasicNameValuePair("username",naam));
                        nvps.add(new BasicNameValuePair("password",password));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
				}
                                
                                //System.out.println(inputLine);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                } 
	}
        
        protected String addUser(String naam, String password, String email, String rechten) throws Exception 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "create_user"));
			nvps.add(new BasicNameValuePair("username", naam));
                        nvps.add(new BasicNameValuePair("password", password));
                        nvps.add(new BasicNameValuePair("email", email));
                        nvps.add(new BasicNameValuePair("rechten", rechten));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
                                        //returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                }
	}
        
        protected String removeUser(String naam) throws Exception 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "delete_user"));
			nvps.add(new BasicNameValuePair("username", naam));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
                                        //returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                }
	}
        
        protected String addDispenser(String naam) throws Exception 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "add_dispenser"));
			nvps.add(new BasicNameValuePair("clientnaam", naam));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
                                        //returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                }
	}
        
        protected String removeDispenser(String naam) throws Exception 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "delete_dispenser"));
			nvps.add(new BasicNameValuePair("clientnaam", naam));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
                                        //returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                }
	}
        
        protected String addClient(String kamerID, String naam, String geboortedatum, String geboorteplaats, String aantalmedicijnen) throws Exception //return error of good
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "add_client"));
			nvps.add(new BasicNameValuePair("kamerid", kamerID));
                        nvps.add(new BasicNameValuePair("clientnaam", naam));
                        nvps.add(new BasicNameValuePair("geboortedatum", geboortedatum));//jaar, maand, dag voorbeeld: 1992-08-02
                        nvps.add(new BasicNameValuePair("geboorteplaats", geboorteplaats));
                        nvps.add(new BasicNameValuePair("aantalmedicijnen", aantalmedicijnen));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
                                        //returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                }
	}
        
        protected String removeClient(String naam) throws Exception {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "delete_client"));
			nvps.add(new BasicNameValuePair("clientnaam", naam));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine=in.readLine()) != null)
				{
					found=true;
                                        returnclient=inputLine;
                                        //returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                        return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                }
	}
        
        protected String getStatus(String Naam) throws Exception //good, error, 
        {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		boolean found = false;
                String returnclient="";

		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("action", "get_dispenserstate"));
			nvps.add(new BasicNameValuePair("clientnaam", Naam));
                        //nvps.add(new BasicNameValuePair("state", Status));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try 
                        {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response2.getEntity().getContent()));
				String inputLine;
				while ((inputLine =in.readLine()) != null)
				{
                                    found=true;
                                    returnclient+=inputLine;
				}
                                
                                //System.out.println(returnclient);
				in.close();
                                    
				if (!found) 
                                {
                                    return "No data found";
				}
                                
                                else
                                {
                                    return returnclient;
                                }
			} 
                        finally 
                        {
				response2.close();
                        }
		} 
                finally 
                {
			httpclient.close();
                } 
	}
}
