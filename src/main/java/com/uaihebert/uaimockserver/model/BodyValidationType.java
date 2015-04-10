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

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.log.backend.Log;
import com.uaihebert.uaimockserver.util.StringUtils;

public enum BodyValidationType {
    VALIDATE_IF_PRESENT_ONLY {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            if (StringUtils.isNotBlank(body)) {
                return;
            }

            Log.warn(BODY_VALIDATOR_ERROR_MESSAGE, uaiRequest.method, uaiRequest.path);
            result.abortTheRequest();
        }
    },
    RAW_TEXT {
        @Override
        public void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result) {
            if (uaiRequest.body.equals(body)) {
                return;
            }

            Log.warn(WRONG_RAW_TEXT_BODY, body, uaiRequest.body);
            result.abortTheRequest();
        }
    };

    private static final String WRONG_RAW_TEXT_BODY = "We received a body with the following text in the body [%s], but the required body is [%s]";
    private static final String BODY_VALIDATOR_ERROR_MESSAGE = "%nThe Route [%s - %s] was defined with the body as mandatory. Send a body in your request or set the bodyRequired to false. %n";

    public abstract void validate(final String body, final UaiRequest uaiRequest, final RequestValidatorFacade.RequestAnalysisResult result);
}