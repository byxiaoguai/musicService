package org.system.vip.tools;

import lombok.Data;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.util.Arrays;

/**
 * @author lz
 * @Date : 2019/7/23
 */
@Data
@Component("httpClientFactoryBean")
public class HttpClientFactoryBean implements FactoryBean<CloseableHttpClient>, InitializingBean, DisposableBean {
    private CloseableHttpClient httpClient;
    private int maxConnTotal = 1000;
    private int maxConnPerRoute = 300;

    @Override
    public void destroy() throws Exception {
        if (this.httpClient != null) {
            try {
                this.httpClient.close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public CloseableHttpClient getObject() throws Exception {
        return this.httpClient;
    }

    @Override
    public Class<?> getObjectType() {
        return CloseableHttpClient.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.httpClient == null) {
            try {
                SSLContext sslcontext = SSLContexts.createDefault();
                //证书
                sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, null);
                SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);
                RequestConfig defaultRequestConfig = RequestConfig.custom().
                        setSocketTimeout(300000).
                        setConnectTimeout(350000).
                        setConnectionRequestTimeout(320000).
                        setCookieSpec(CookieSpecs.STANDARD_STRICT).
                        setExpectContinueEnabled(true).
                        setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", ssf).build();
                PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

                this.httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(defaultRequestConfig).setMaxConnTotal(this.maxConnTotal).setMaxConnPerRoute(this.maxConnPerRoute).build();
            } catch (KeyManagementException e) {
                throw new IllegalArgumentException("can't create trustManager", e);
            }
        }
    }
}
