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

import com.regalii.regaliator.utils.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Created by Geoffrey Roguelon on 17/01/2017.
 */
public class Response {
    protected final HttpURLConnection connection;

    public Response(HttpURLConnection connection) {
        this.connection = connection;
    }

    public boolean isSuccess() {
        return httpCode() >= 200 && httpCode() < 300;
    }

    public int httpCode() {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Map<String, Object> body() {
        final String raw = rawBody();

        //noinspection unchecked
        return (Map<String, Object>) JSON.loadToMap(raw);
    }

    private String rawBody() {
        try {
            final StringBuilder response = new StringBuilder();
            BufferedReader reader;
            String inputLine;

            if(isSuccess()) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else{
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
