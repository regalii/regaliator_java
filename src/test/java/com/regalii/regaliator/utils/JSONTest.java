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

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Geoffrey Roguelon on 18/01/2017.
 */
public class JSONTest {
    static private String JSON_STR = "{\"say\":\"Hello World!\",\"age\":1.0}";
    static private Map<String, Object> MAP_OBJ = new HashMap<>(2);

    static {
        MAP_OBJ.put("say", "Hello World!");
        MAP_OBJ.put("age", 1.0);
    }

    @Test
    public void testJSONDump() {
        Assert.assertEquals(JSON_STR, JSON.dump(MAP_OBJ));
    }

    @Test
    public void testJSONLoadToMap() {
        Assert.assertEquals(MAP_OBJ, JSON.loadToMap(JSON_STR));
    }
}
