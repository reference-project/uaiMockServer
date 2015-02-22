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
package com.uaihebert.uaimockserver.model;

import java.util.List;

/**
 * Class that will hold all the request data
 */
public final class UaiRequest {
    public final String name;
    public final String path;
    public final String method;
    public final String description;
    public final String requiredContentType;

    public final long holdRequestInMilli;

    public final boolean isBodyRequired;

    public final List<UaiHeader> requiredHeaderList;
    public final List<UaiQueryParam> requiredQueryParamList;

    public UaiRequest(final String name, final String path, final String method, final String description, final String requiredContentType, final long holdRequestInMilli, final boolean isBodyRequired, final List<UaiHeader> requiredHeaderList, final List<UaiQueryParam> requiredQueryParamList) {
        this.name = name;
        this.path = path;
        this.method = method;
        this.description = description;
        this.requiredContentType = requiredContentType;
        this.holdRequestInMilli = holdRequestInMilli;
        this.isBodyRequired = isBodyRequired;
        this.requiredHeaderList = requiredHeaderList;
        this.requiredQueryParamList = requiredQueryParamList;
    }
}