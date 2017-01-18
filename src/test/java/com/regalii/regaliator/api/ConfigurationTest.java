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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geoffrey Roguelon on 18/01/2017.
 */
public class ConfigurationTest {
    static private final Version VERSION = null;
    static private final String HOST = "api.regalii.dev";
    static private final String API_KEY = "key";
    static private final String SECRET_KEY = "secret";

    static private final Map<Version, Class<? extends AbstractClient>> VERSIONS = new HashMap<>(Version.values().length);

    static {
        VERSIONS.put(Version.v1_5, com.regalii.regaliator.v15.Client.class);
        VERSIONS.put(Version.v3_0, com.regalii.regaliator.v30.Client.class);
        VERSIONS.put(Version.v3_1, com.regalii.regaliator.v31.Client.class);
    }

    private Configuration subject;

    @Before
    public void setSubject() {
        this.subject = new Configuration(VERSION, HOST, API_KEY, SECRET_KEY);
    }

    @After
    public void unsetSubject() {
        this.subject = null;
    }

    @Test
    public void testContructorSetsAttributesAndDefaults() {
        Assert.assertEquals(VERSION, subject.getVersion());
        Assert.assertEquals(HOST, subject.getHost());
        Assert.assertEquals(API_KEY, subject.getApiKey());
        Assert.assertEquals(SECRET_KEY, subject.getSecretKey());
        Assert.assertTrue(subject.isUsingSSL());
    }

    @Test
    public void testBuildClientReturnsClient() {
        for (Map.Entry<Version, Class<? extends AbstractClient>> pair : VERSIONS.entrySet()) {
            final Configuration subject = new Configuration(pair.getKey(), HOST, API_KEY, SECRET_KEY);

            Assert.assertEquals(pair.getValue(), subject.buildClient().getClass());
        }
    }

    @Test
    public void testGetAcceptReturnsString() {
        final Configuration subject = new Configuration(Version.randomVersion(), HOST, API_KEY, SECRET_KEY);

        Assert.assertTrue(subject.getAccept().matches("^application/vnd\\.regalii\\.v[0-9]\\.[0-9]\\+json$"));
    }

    @Test
    public void testGetDateReturnsString() {
        Assert.assertTrue(subject.getDate().matches("^(?i)[a-z]{3}, \\d{1,2} (?i)[a-z]{3} \\d{4} \\d{2}:\\d{2}:\\d{2} GMT$"));
    }

    @Test
    public void testGetUserAgentReturnsString() {
        Assert.assertTrue(subject.getUserAgent().matches("^Regaliator Java v.+"));
    }

    @Test
    public void testGetClientReturnsClient() {
        final Configuration subject = new Configuration(Version.randomVersion(), HOST, API_KEY, SECRET_KEY);

        Assert.assertSame(subject.getClient(), subject.getVersion().getClient());
    }

    @Test
    public void testGetContentTypeReturnsString() {
        Assert.assertEquals("application/json", subject.getContentType());
    }

    @Test
    public void testGetVersionReturnsVersions() {
        final Version version = Version.randomVersion();
        final Configuration subject = new Configuration(version, HOST, API_KEY, SECRET_KEY);

        Assert.assertSame(version, subject.getVersion());
    }

    @Test
    public void testSetUseSSLSetsBoolean() {
        Assert.assertTrue(subject.isUsingSSL());
        subject.setUseSSL(false);
        Assert.assertFalse(subject.isUsingSSL());
    }

    @Test
    public void testPackageVersionReturnsString() {
        Assert.assertTrue(Configuration.packageVersion().matches("^\\d\\.\\d\\.\\d(-SNAPSHOT)?$"));
    }
}
