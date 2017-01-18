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
import com.regalii.regaliator.utils.MD5;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geoffrey Roguelon on 17/01/2017.
 */
public class Request {
    private final Configuration configuration;

    public Request(final Configuration configuration) {
        this.configuration = configuration;
    }

    public Response get(final String endpoint) {
        return get(endpoint, new HashMap<>(0));
    }

    public Response get(final String endpoint, final Map<String, Object> params) {
        final HttpURLConnection connection = buildConnection("GET", endpoint, null);

        return new Response(connection);
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

    private Response requestWithBody(final String httpMethod, final String endpoint, final Map<String, Object> params) {
        final String json = JSON.dump(params);
        final String md5 = MD5.digest(json);
        final HttpURLConnection connection = buildConnection(httpMethod, endpoint, md5);

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

    private URL buildURL(final String endpoint) throws MalformedURLException {
        return new URL(getProtocol() + "://" + configuration.getHost() + endpoint);
    }

    private HttpURLConnection buildConnection(final String httpMethod, final String endpoint, final String md5) {
        final String date = configuration.getDate();

        try {
            final URL url = buildURL(endpoint);

            HttpURLConnection connection;
            if (configuration.isUsingSSL()) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            final String authHash = new AuthHash(configuration).generate(md5, endpoint, date);

            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Accept", configuration.getAccept());
            connection.setRequestProperty("Authorization", authHash);
            connection.setRequestProperty("Content-MD5", md5);
            connection.setRequestProperty("Content-Type", configuration.getContentType());
            connection.setRequestProperty("Date", configuration.getDate());
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
}
