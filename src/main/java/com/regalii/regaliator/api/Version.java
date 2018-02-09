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

import java.util.Random;

/**
 * Created by Geoffrey Roguelon on 17/01/2017.
 */
public enum Version {
    v1_5("1.5", com.regalii.regaliator.v15.Client.class),
    v3_0("3.0", com.regalii.regaliator.v30.Client.class),
    v3_1("3.1", com.regalii.regaliator.v31.Client.class),
    v3_2("3.2", com.regalii.regaliator.v32.Client.class);

    private String version;
    private Class<? extends AbstractClient> client;

    static public Version randomVersion() {
        int pick = new Random().nextInt(Version.values().length);

        return Version.values()[pick];
    }

    Version(final String version, final Class<? extends AbstractClient> client) {
        this.version = version;
        this.client = client;
    }

    public String getVersion() {
        return version;
    }

    public Class<? extends AbstractClient> getClient() {
        return client;
    }

    @Override
    public String toString() {
        return version;
    }
}
