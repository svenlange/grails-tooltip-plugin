class TooltipTagLib {

    static namespace = "tooltip"

    def resources = {attrs ->
        out << """
        <link rel="stylesheet" href="${createLinkTo(dir: pluginContextPath, file: 'css/tooltip/default/tooltip.css')}"/>
        <script type="text/javascript" src="${createLinkTo(dir: pluginContextPath, file: "js/tooltip/tooltip-min.js")}"></script>
           """
    }

    def tip = {attrs, body ->
        if (!attrs.value && !attrs.code)
            throwTagError("Tag [tooltip:tip] is missing required attribute [code] or [value]")
        
        def tooltip = attrs.code ? g.message(code: "${attrs.code}") : attrs.value
        tooltip = tooltip.encodeAsHTML()

        out << """<span onmouseover="tooltip.show('${tooltip}');" onmouseout="tooltip.hide();">${body()}</span>"""
    }

}