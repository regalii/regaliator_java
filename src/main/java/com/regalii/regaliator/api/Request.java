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
import java.net.MalformedURLException;
import java.net.URL;
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
        final HttpsURLConnection connection = buildConnection("GET", endpoint, null);

        return new Response(connection);
    }

    public Response post(final String endpoint, Map<String, Object> params) throws NullPointerException {
        final String json = JSON.dump(params);
        final String md5 = MD5.digest(json);
        final HttpsURLConnection connection = buildConnection("POST", endpoint, md5);

        connection.setDoOutput(true);

        return new Response(connection);
    }

    private URL buildURL(final String endpoint) throws MalformedURLException {
        return new URL(getProtocol() + "://" + configuration.getHost() + endpoint);
    }

    private HttpsURLConnection buildConnection(final String httpMethod, final String endpoint, final String md5) {
        final String date = configuration.getDate();

        try {
            final URL url = buildURL(endpoint);
            final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            final String authHash = new AuthHash(configuration).generate(md5, endpoint, date);

            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Content-Type", configuration.getContentType());
            connection.setRequestProperty("Accept", configuration.getAccept());
            connection.setRequestProperty("User-Agent", configuration.getUserAgent());
            connection.setRequestProperty("Date", configuration.getDate());
            connection.setRequestProperty("Content-MD5", date);
            connection.setRequestProperty("Authoization", authHash);

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
