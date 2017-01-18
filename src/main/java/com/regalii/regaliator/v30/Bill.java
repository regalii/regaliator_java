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

package com.regalii.regaliator.v30;

import com.regalii.regaliator.api.AbstractEndpoint;
import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Response;

import java.util.Map;

/**
 * Created by Geoffrey Roguelon on 17/01/2017.
 */
public class Bill extends AbstractEndpoint {
    public Bill(Configuration configuration) {
        super(configuration);
    }

    public Response create(final Map<String, Object> params) {
        return request.post("/bills", params);
    }

    public Response show(final int id) {
        return request.get("/bills/" + id);
    }

    public Response update(final int id, final Map<String, Object> params) {
        return request.patch("/bills/" + id, params);
    }

    public Response refresh(final int id) {
        return request.post("/bills/" + id + "/refresh");
    }

    public Response pay(final int id, final Map<String, Object> params) {
        return request.post("/bills/" + id + "/pay", params);
    }

    public Response xdata(final int id) {
        return request.get("/bills/" + id + "/xdata");
    }

    public Response list(final Map<String, Object> params) {
        return request.get("/bills", params);
    }
}
