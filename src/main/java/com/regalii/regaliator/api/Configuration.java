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


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Geoffrey Roguelon on 17/01/2017.
 */
public class Configuration {
    static private final String CONTENT_TYPE = "application/json";
    static private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.RFC_1123_DATE_TIME;
    static private final ZoneId DATE_ZONE = ZoneId.of("GMT");

    private final Version version;

    private final String host;
    private final String apiKey;
    private final String secretKey;

    private Boolean useSSL;

    public Configuration(final Version version, final String host, final String apiKey, final String secretKey) {
        this.version = version;
        this.host = host;
        this.apiKey = apiKey;
        this.secretKey = secretKey;

        this.useSSL = true;
    }

    public AbstractClient buildClient() {
        final Class<? extends AbstractClient> clientClass = version.getClient();

        try {
            final Constructor<? extends AbstractClient> constructor = clientClass.getConstructor(Configuration.class);
            final AbstractClient client = constructor.newInstance(this);

            return client;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getAccept() {
        return "application/vnd.regalii.v" + getVersion() + "+json";
    }

    public String getDate() {
        return ZonedDateTime.now().withZoneSameInstant(DATE_ZONE).format(DATE_FORMAT);
    }

    public String getUserAgent() {
        return "Regaliator Java v" + packageVersion();
    }

    public Class<? extends AbstractClient> getClient() {
        return version.getClient();
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }

    public Version getVersion() {
        return version;
    }

    public String getHost() {
        return host;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Boolean isUsingSSL() {
        return useSSL;
    }

    public void setUseSSL(final Boolean useSSL) {
        this.useSSL = useSSL;
    }

    static public String packageVersion() {
        final String version = Request.class.getPackage().getImplementationVersion();

        return version == null ? "0.0.0-SNAPSHOT" : version;
    }
}
