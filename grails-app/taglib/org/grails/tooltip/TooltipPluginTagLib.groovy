/* Copyright 2012 Sven Lange
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

/**
 * Tag lib that provides tags to display tooltips.
 *
 * @author Sven Lange
 */
class TooltipPluginTagLib {

    def grailsApplication

    static namespace = "tooltip"

    final resources = { attrs ->
        String cssHref
        if (attrs.stylesheet && attrs.stylesheet != "") {
            cssHref = resource(dir: 'css/tooltip/', file: "${attrs.stylesheet}.css")
        } else if (grailsApplication.config.tooltip.defaultStyle) {
            cssHref = resource(dir: 'css/tooltip/', file: "${grailsApplication.config.tooltip.defaultStyle}.css")
        } else {
            cssHref = resource(dir: "${pluginContextPath}/css/tooltip/", file: "tooltip.css")
        }

        out << """<link rel="stylesheet" type="text/css" href="${cssHref}"/>
<script type="text/javascript" src="${resource(dir: "${pluginContextPath}/js/tooltip/", file: "tooltip-min.js")}"></script>"""
    }

    final tip = { attrs, body ->
        if (!attrs.value && !attrs.code) {
            log.info("Tag [tooltip:tip] is missing required attribute [code] or [value]")
            out << body()
        } else {
            def tooltip = attrs.code ? g.message(code: "${attrs.code}") : attrs.value
            tooltip = tooltip.encodeAsHTML()
            out << """<span onmouseover="tooltip.show('${tooltip}');" onmouseout="tooltip.hide();">${body()}</span>"""
        }
    }

}