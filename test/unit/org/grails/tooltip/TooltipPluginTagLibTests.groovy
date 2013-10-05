/* Copyright 2009 Sven Lange
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
 */

package org.grails.tooltip

import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.web.taglib.ValidationTagLib
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.junit.Before
import org.junit.Test
import org.springframework.context.support.StaticMessageSource
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.servlet.support.RequestContextUtils as RCU

/**
 * Unit test for tooltip tag lib. 
 *
 * @author Sven Lange
 */
@TestFor(TooltipPluginTagLib)
class TooltipPluginTagLibTests {

    private static final PROPERTY_KEY = "sometext"
    private static final String PROPERTY_VALUE = "yeeeeeaaaaahhhhhhhhhh"

    @Before
    void setUp() {
        final messageSource = ((GrailsWebRequest) RequestContextHolder.currentRequestAttributes()).attributes.applicationContext.messageSource as StaticMessageSource
        messageSource.addMessage(PROPERTY_KEY, RCU.getLocale(request), PROPERTY_VALUE)
        tagLib.metaClass.getG = {->
            new ValidationTagLib()
        }

        tagLib.metaClass.pluginContextPath = "/somePath"
    }

    @Test
    void resourcesWithoutStylesheet() {
        assert applyTemplate("""<tooltip:resources/>""") == """<link rel="stylesheet" type="text/css" href="/somePath/css/tooltip/tooltip.css"/>
<script type="text/javascript" src="/somePath/js/tooltip/tooltip-min.js"></script>"""
    }

    @Test
    void resourcesWithValidStylesheet() {
        assert applyTemplate("""<tooltip:resources stylesheet="ananab"/>""") == """<link rel="stylesheet" type="text/css" href="/css/tooltip/ananab.css"/>
<script type="text/javascript" src="/somePath/js/tooltip/tooltip-min.js"></script>"""
    }

    @Test
    void resourcesWithEmptyStylesheet() {
        assert applyTemplate("""<tooltip:resources stylesheet="" />""") == """<link rel="stylesheet" type="text/css" href="/somePath/css/tooltip/tooltip.css"/>
<script type="text/javascript" src="/somePath/js/tooltip/tooltip-min.js"></script>"""
    }

    @Test
    void simpleValue() {
        assert applyTemplate("""<tooltip:tip value="tzu">////&\\\\</tooltip:tip>""") == """<span onmouseover="tooltip.show('tzu');" onmouseout="tooltip.hide();">////&\\\\</span>"""
    }

    @Test
    void valueWithUmlauts() {
        assert applyTemplate('<tooltip:tip value="öüä">someTextThatWillShowTheTooltip</tooltip:tip>') == """<span onmouseover="tooltip.show('&ouml;&uuml;&auml;');" onmouseout="tooltip.hide();">someTextThatWillShowTheTooltip</span>"""
    }

    @Test
    void withCodeAttribute() {
        assert applyTemplate("""<tooltip:tip code="$PROPERTY_KEY">b</tooltip:tip>""") == """<span onmouseover="tooltip.show('$PROPERTY_VALUE');" onmouseout="tooltip.hide();">b</span>"""
    }

    @Test
    void withCodeAndValue() {
        assert applyTemplate("""<tooltip:tip value="someValue" code="$PROPERTY_KEY">b</tooltip:tip>""") == """<span onmouseover="tooltip.show('$PROPERTY_VALUE');" onmouseout="tooltip.hide();">b</span>"""
    }

    @Test
    void withoutValueAndEmptyCodeAttribute() {
        assert applyTemplate("""<tooltip:tip code=""><img src="dfkj"/></tooltip:tip>""") == """<img src="dfkj"/>"""
    }

}