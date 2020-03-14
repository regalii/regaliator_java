/*
 * Copyright (c) 2017, Grupo Regalii, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.regalii.regaliator.api;

import com.regalii.regaliator.utils.AuthHash;
import com.regalii.regaliator.utils.JSON;

import javax.net.ssl.HttpsURLConnection;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Arrays;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Geoffrey Roguelon on 17/01/2017.
 */
public class Request {
    private final Configuration configuration;

    public Request(final Configuration configuration) {
        this.configuration = configuration;
    }

    public Response get(final String endpoint) {
        final HttpURLConnection connection = buildConnection("GET", endpoint);
        return new Response(connection);
    }

    public Response get(final String endpoint, final Map<String, Object> params) {
        if(params == null) {
            return get(endpoint);
        } else {
            final HttpURLConnection connection = buildConnection("GET", endpoint + "?" + urlEncodeUTF8(params));
            return new Response(connection);
        }
    }

    public Response patch(final String endpoint, final Map<String, Object> params) {
        return requestWithBody("PATCH", endpoint, params);
    }

    public Response post(final String endpoint) {
        return post(endpoint, new HashMap<>(0));
    }

    public Response post(final String endpoint, final Map<String, Object> params) {
        return requestWithBody("POST", endpoint, params);
    }

    public Response delete(final String endpoint) {
        final HttpURLConnection connection = buildConnection("DELETE", endpoint);
        return new Response(connection);
    }

    private Response requestWithBody(final String httpMethod, final String endpoint, final Map<String, Object> params) {
        final String json = JSON.dump(params);
        final HttpURLConnection connection = buildConnection(httpMethod, endpoint);

        connection.setDoOutput(true);
        final OutputStream os;
        try {
            os = connection.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return new Response(connection);
    }

    private static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private String urlEncodeUTF8(Map<?,?> map) {
       StringBuilder sb = new StringBuilder();
        for (Map.Entry<?,?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                urlEncodeUTF8(entry.getKey().toString()),
                urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    private URL buildURL(final String endpoint) throws MalformedURLException {
        return new URL(getProtocol() + "://" + configuration.getHost() + endpoint);
    }

    private HttpURLConnection buildConnection(final String httpMethod, final String endpoint) {
        final String date = configuration.getDate();

        try {
            final URL url = buildURL(endpoint);

            if (httpMethod.equals("PATCH")) {
                allowMethods("PATCH");
            }

            HttpURLConnection connection;
            if (configuration.isUsingSSL()) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            final String authHash = new AuthHash(configuration).generate(endpoint, date);

            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Accept", configuration.getAccept());
            connection.setRequestProperty("Authorization", authHash);
            connection.setRequestProperty("Content-Type", configuration.getContentType());
            connection.setRequestProperty("Date", date);
            connection.setRequestProperty("User-Agent", configuration.getUserAgent());

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getProtocol() {
        return configuration.isUsingSSL() ? "https" : "http";
    }

    /**
     * Solution taken from: https://stackoverflow.com/questions/25163131/httpurlconnection-invalid-http-method-patch
     */
    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/*static field*/, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
