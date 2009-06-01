import org.codehaus.groovy.grails.plugins.codecs.HTMLCodec
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

class TooltipPluginTagLibTests extends grails.test.TagLibUnitTestCase {

    void setUp() {
        super.setUp()

        def mockString = mockFor(String)
        mockString.demand.encodeAsHTML(1..1) { HTMLCodec.encode(delegate) }

        tagLib.metaClass.pluginContextPath = "/somePath"
        tagLib.metaClass.resource = {a -> a.dir + a.file}
    }

    void testResourceWithoutStylesheet() {
        tagLib.resources([:])
        def expected = """<link rel="stylesheet" type="text/css" href="/somePath/css/tooltip/tooltip.css"/>
<script type="text/javascript" src="/somePath/js/tooltip/tooltip-min.js"></script>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testResourceWithValidStylesheet() {
        tagLib.resources([stylesheet: "ananab"])
        def expected = """<link rel="stylesheet" type="text/css" href="css/tooltip/ananab.css"/>
<script type="text/javascript" src="/somePath/js/tooltip/tooltip-min.js"></script>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testResourceWithEmptyStylesheet() {
        tagLib.resources([stylesheet: ""])
        def expected = """<link rel="stylesheet" type="text/css" href="/somePath/css/tooltip/tooltip.css"/>
<script type="text/javascript" src="/somePath/js/tooltip/tooltip-min.js"></script>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipValue() {
        tagLib.tip([value: '<someTag>tzu</someTag>']) {"////&\\\\"}
        def expected = """<span onmouseover="tooltip.show('&lt;someTag&gt;tzu&lt;/someTag&gt;');" onmouseout="tooltip.hide();">////&\\\\</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipValueWithUmlauts() {
        tagLib.tip([value: 'öüä']) {"someTextThatWillShowTheTooltip"}
        def expected = """<span onmouseover="tooltip.show('&ouml;&uuml;&auml;');" onmouseout="tooltip.hide();">someTextThatWillShowTheTooltip</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipCode() {
        def myI18nMessage = "myI18nMessage"
        tagLib.metaClass.message = {myI18nMessage}

        tagLib.tip([code: 'someCode']) {"b"}
        def expected = """<span onmouseover="tooltip.show('$myI18nMessage');" onmouseout="tooltip.hide();">b</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipCodeAndValue() {
        def myI18nMessage = "myI18nMessage"
        tagLib.metaClass.message = {myI18nMessage}

        tagLib.tip([code: 'someCodeReturningMyI18nMessage', value: 'someValue']) {"b"}
        def expected = """<span onmouseover="tooltip.show('$myI18nMessage');" onmouseout="tooltip.hide();">b</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipWithoutCodeOrValueAttribute() {
        def msg = shouldFail(GrailsTagException) {
            tagLib.tip([:]) {"b"}
        }
        assertEquals "Tag [tooltip:tip] is missing required attribute [code] or [value]", msg
    }

}