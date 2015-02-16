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
package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.facade.RequestValidatorFacade;
import com.uaihebert.uaimockserver.factory.LogFactory;
import com.uaihebert.uaimockserver.factory.TypeSafeConfigFactory;
import com.uaihebert.uaimockserver.factory.UaiHeaderFactory;
import com.uaihebert.uaimockserver.factory.UaiQueryParamFactory;
import com.uaihebert.uaimockserver.factory.UaiRequestFactory;
import com.uaihebert.uaimockserver.factory.UaiResponseFactory;
import com.uaihebert.uaimockserver.factory.UaiRouteFactory;
import com.uaihebert.uaimockserver.util.ConfigKeyUtil;
import com.uaihebert.uaimockserver.util.ExceptionUtil;
import com.uaihebert.uaimockserver.util.HttpServerUtil;
import com.uaihebert.uaimockserver.util.RequestHolder;
import com.uaihebert.uaimockserver.util.RouteFinderUtil;
import com.uaihebert.uaimockserver.util.RouteMapKeyUtil;
import com.uaihebert.uaimockserver.util.RouteUtil;
import com.uaihebert.uaimockserver.validator.BodyValidator;
import com.uaihebert.uaimockserver.validator.ContentTypeValidator;
import com.uaihebert.uaimockserver.validator.HeaderValidator;
import com.uaihebert.uaimockserver.validator.RequestValidator;
import com.uaihebert.uaimockserver.validator.UaiQueryParamValidator;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

public class PrivateConstructorTest {

    @Test
    public void testRouteUtilConstructor() throws Exception {
        final Constructor<RouteUtil> constructor = RouteUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RouteUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRouteFactoryConstructor() throws Exception {
        final Constructor<UaiRouteFactory> constructor = UaiRouteFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRouteFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiRequestFactoryConstructor() throws Exception {
        final Constructor<UaiRequestFactory> constructor = UaiRequestFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiRequestFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testHttpServerUtilConstructor() throws Exception {
        final Constructor<HttpServerUtil> constructor = HttpServerUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final HttpServerUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestValidatorConstructor() throws Exception {
        final Constructor<RequestValidator> constructor = RequestValidator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestValidator createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiResponseFactoryConstructor() throws Exception {
        final Constructor<UaiResponseFactory> constructor = UaiResponseFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiResponseFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testHeaderValidatorConstructor() throws Exception {
        final Constructor<HeaderValidator> constructor = HeaderValidator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final HeaderValidator createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiHeaderFactoryConstructor() throws Exception {
        final Constructor<UaiHeaderFactory> constructor = UaiHeaderFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiHeaderFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiQueryParamFactoryConstructor() throws Exception {
        final Constructor<UaiQueryParamFactory> constructor = UaiQueryParamFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiQueryParamFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testUaiQueryParamValidatorConstructor() throws Exception {
        final Constructor<UaiQueryParamValidator> constructor = UaiQueryParamValidator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final UaiQueryParamValidator createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRouteMapKeyUtilConstructor() throws Exception {
        final Constructor<RouteMapKeyUtil> constructor = RouteMapKeyUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RouteMapKeyUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testLogFactoryConstructor() throws Exception {
        final Constructor<LogFactory> constructor = LogFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final LogFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testBodyValidatorConstructor() throws Exception {
        final Constructor<BodyValidator> constructor = BodyValidator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final BodyValidator createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testExceptionUtilConstructor() throws Exception {
        final Constructor<ExceptionUtil> constructor = ExceptionUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final ExceptionUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testConfigKeyUtilConstructor() throws Exception {
        final Constructor<ConfigKeyUtil> constructor = ConfigKeyUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final ConfigKeyUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testTypeSafeConfigFactoryConstructor() throws Exception {
        final Constructor<TypeSafeConfigFactory> constructor = TypeSafeConfigFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final TypeSafeConfigFactory createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRouteFinderUtilConstructor() throws Exception {
        final Constructor<RouteFinderUtil> constructor = RouteFinderUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RouteFinderUtil createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestValidatorFacadeConstructor() throws Exception {
        final Constructor<RequestValidatorFacade> constructor = RequestValidatorFacade.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestValidatorFacade createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testContentTypeValidatorConstructor() throws Exception {
        final Constructor<ContentTypeValidator> constructor = ContentTypeValidator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final ContentTypeValidator createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }

    @Test
    public void testRequestHolderConstructor() throws Exception {
        final Constructor<RequestHolder> constructor = RequestHolder.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);

        final RequestHolder createdObject = constructor.newInstance(new Object[0]);
        assertNotNull(createdObject);
    }
}