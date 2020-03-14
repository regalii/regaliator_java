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

package com.regalii.regaliator.utils;

import com.regalii.regaliator.api.Configuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Geoffrey Roguelon on 18/01/2017.
 */
public class AuthHashTest {
    static private final Configuration configuration = new Configuration(
            null,
            "https://api.regalii.dev",
            "key",
            "secret"
    );

    @Test
    public void testAuthHashGenerateIt() {
        final AuthHash subject = new AuthHash(configuration);

        Assert.assertEquals("APIAuth key:iGJnnkbZcltdNo+y9ga1K7BPJQw=", subject.generate(
                "/fake",
                "Wed, 18 Jan 2017 16:27:55 GMT"
        ));
    }
}
