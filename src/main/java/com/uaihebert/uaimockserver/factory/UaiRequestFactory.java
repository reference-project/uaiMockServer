/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.uaihebert.uaimockserver.factory;

import com.uaihebert.uaimockserver.dto.model.UaiRequestDTO;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiQueryParam;
import com.uaihebert.uaimockserver.model.UaiRequest;

import java.util.List;

/**
 * This factory will create an instance of the UaiRequest.java
 */
public final class UaiRequestFactory {
    private UaiRequestFactory() {
    }

    public static UaiRequest create(final UaiRequestDTO request) {
        final Boolean bodyRequired = request.isBodyRequired();

        final long holdRequestInMilli = extractHoldRequestInMilli(request);

        final String name = request.getName();
        final String path = request.getPath();
        final String method = request.getMethod();
        final String description = request.getDescription();
        final String contentType = request.getRequiredContentType();

        final List<UaiHeader> requiredHeaderList = UaiHeaderFactory.create(request.getRequiredHeaderList());
        final List<UaiQueryParam> requiredQueryParamList = UaiQueryParamFactory.create(request.getRequiredQueryParamList());

        return new UaiRequest(name, path, method, description, contentType, holdRequestInMilli, bodyRequired, requiredHeaderList, requiredQueryParamList);
    }

    private static long extractHoldRequestInMilli(final UaiRequestDTO request) {
        if (request.getHoldRequestInMilli() != null) {
            return request.getHoldRequestInMilli();
        }

        return 0L;
    }
}