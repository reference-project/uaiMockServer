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

import com.typesafe.config.Config;
import com.uaihebert.uaimockserver.constants.ResponseConstants;
import com.uaihebert.uaimockserver.dto.model.UaiResponseDTO;
import com.uaihebert.uaimockserver.model.UaiBasicServerConfiguration;
import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiResponse;
import com.uaihebert.uaimockserver.util.ConfigKeyUtil;
import com.uaihebert.uaimockserver.util.StringUtils;

import java.util.List;

/**
 * This factory will create an instance of the UaiResponse.java
 */
public final class UaiResponseFactory {
    private UaiResponseFactory() {
    }

    public static UaiResponse create(final Config routeConfig) {
        final int statusCode = routeConfig.getInt(ResponseConstants.STATUS_CODE.path);

        final String body = defineBody(routeConfig);
        final String contentType = defineContentType(routeConfig);

        final List<UaiHeader> headerList = UaiHeaderFactory.create(routeConfig, ResponseConstants.REQUIRED_HEADER_LIST.path);

        return new UaiResponse(statusCode, body, contentType, headerList);
    }

    private static String defineBody(final Config routeConfig) {
        final String configBody = ConfigKeyUtil.getStringSilently(ResponseConstants.BODY.path, routeConfig);

        if (StringUtils.isBlank(configBody)) {
            return null;
        }

        return configBody;
    }

    private static String defineContentType(final Config routeConfig) {
        final String contentType = ConfigKeyUtil.getStringSilently(ResponseConstants.CONTENT_TYPE.path, routeConfig);

        if (StringUtils.isBlank(contentType)) {
            return UaiBasicServerConfiguration.getDefaultContentType();
        }

        return contentType;
    }

    public static UaiResponse create(final UaiResponseDTO response) {
        final int statusCode = response.getStatusCode();

        final String body = response.getBody();
        final String contentType = response.getContentType();

        final List<UaiHeader> headerList = UaiHeaderFactory.create(response.getHeaderList());

        return new UaiResponse(statusCode, body, contentType, headerList);
    }
}