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
package com.uaihebert.uaimockserver.validator;

import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiMockServerConfig;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;

/**
 * Will validate all the request headers if needed
 */
public final class HeaderValidator {
    private static final String HEADER_NOT_FOUND_MESSAGE = "We could not find the requested Header [%s] received in the request. \n " +
            "If the header is not mandatory, remove it from the config file.";

    private static final String HEADER_VALUE_NOT_FOUND_MESSAGE = "The required value [%s] was not found in the header [%s]";

    private HeaderValidator() {
    }

    public static void validate(final UaiRequest uaiRequest, final HttpServerExchange exchange, final UaiMockServerConfig uaiMockServerConfig) {
        for (UaiHeader uaiHeader : uaiRequest.requiredHeaderList) {
            final HeaderMap requestHeaderMap = exchange.getRequestHeaders();

            validateHeader(uaiHeader, requestHeaderMap, uaiMockServerConfig);
        }
    }

    private static void validateHeader(final UaiHeader uaiHeader, final HeaderMap requestHeaderMap, final UaiMockServerConfig uaiMockServerConfig) {
        final HeaderValues headerValueList = requestHeaderMap.get(uaiHeader.name);

        if (headerValueList == null) {
            final String errorText = String.format(HEADER_NOT_FOUND_MESSAGE, uaiHeader.name);
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText), uaiMockServerConfig.log);
        }

        if (uaiHeader.usingWildCard) {
            uaiMockServerConfig.log.infoFormatted("The header [%s] is using the wildcard. Its content will not be checked.", uaiHeader.name);
            return;
        }

        if (!headerValueList.containsAll(uaiHeader.valueList)) {
            final String errorText = String.format(HEADER_VALUE_NOT_FOUND_MESSAGE, uaiHeader.name, uaiHeader.valueList);
            ExceptionUtil.logBeforeThrowing(new IllegalArgumentException(errorText), uaiMockServerConfig.log);
        }
    }
}