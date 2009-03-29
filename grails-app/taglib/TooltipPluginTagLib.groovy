class TooltipPluginTagLib {

    static namespace = "tooltip"

    def resources = {attrs ->
        String cssHref
        if (attrs.stylesheet && attrs.stylesheet != "") {
            cssHref = createLinkTo(dir: 'css/tooltip/', file: "${attrs.stylesheet}.css")
        }
        else {
            cssHref = createLinkTo(dir: "${pluginContextPath}/css/tooltip/", file: "tooltip.css")
        }

        out << """<link rel="stylesheet" type="text/css" href="${cssHref}"/>
<script type="text/javascript" src="${createLinkTo(dir: "${pluginContextPath}/js/tooltip/", file: "tooltip-min.js")}"></script>"""
    }

    def tip = {attrs, body ->
        if (!attrs.value && !attrs.code)
            throwTagError("Tag [tooltip:tip] is missing required attribute [code] or [value]")

        def tooltip = attrs.code ? g.message(code: "${attrs.code}") : attrs.value
        tooltip = tooltip.encodeAsHTML()

        out << """<span onmouseover="tooltip.show('${tooltip}');" onmouseout="tooltip.hide();">${body()}</span>"""
    }

}