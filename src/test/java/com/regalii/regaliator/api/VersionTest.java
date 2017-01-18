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

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Geoffrey Roguelon on 18/01/2017.
 */
public class VersionTest {
    @Test
    public void testVersion15() {
        final Version version = Version.v1_5;

        Assert.assertEquals("1.5", version.getVersion());
        Assert.assertEquals("1.5", version.toString());
        Assert.assertSame(com.regalii.regaliator.v15.Client.class, version.getClient());
    }

    @Test
    public void testVersion30() {
        final Version version = Version.v3_0;

        Assert.assertEquals("3.0", version.getVersion());
        Assert.assertEquals("3.0", version.toString());
        Assert.assertSame(com.regalii.regaliator.v30.Client.class, version.getClient());
    }

    @Test
    public void testVersion31() {
        final Version version = Version.v3_1;

        Assert.assertEquals("3.1", version.getVersion());
        Assert.assertEquals("3.1", version.toString());
        Assert.assertSame(com.regalii.regaliator.v31.Client.class, version.getClient());
    }

    @Test
    public void testRandomVersion() {
        Assert.assertTrue(Version.randomVersion() instanceof Version);
    }
}
