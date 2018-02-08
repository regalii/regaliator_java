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

package com.regalii.regaliator.v32;

import com.regalii.regaliator.api.AbstractClient;
import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Version;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Geoffrey Roguelon on 18/01/2017.
 */
public class ClientTest {
    static private final Configuration configuration = new Configuration(
    			Version.v3_2,
            "apix.casiregalii.com",
            "7d28936c2d877f0251300e8ebf361489",
    			"rafpb7lSAQ0K5r3lyOnny0ZCsWo/gOMEeEqGg8yVJ4Vb3h4qYpM/g7rt3PkasGNQkDuliaTl493rv194zi3IGQ=="
    );

    private Client subject;

    @Before
    public void setupSubject() {
        subject = new Client(configuration);
    }

    @After
    public void destroySubject() {
        subject = null;
    }

    @Test
    public void testClassInheritsFromAbstractClient() {
        Assert.assertTrue(AbstractClient.class.isAssignableFrom(Client.class));
    }
    
    @Test
    public void testAccount() {
    		Assert.assertNotNull(subject.getAccount().info());
    		Assert.assertEquals("", subject.getAccount().info().body());
    }
}
