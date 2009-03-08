import org.codehaus.groovy.grails.plugins.codecs.HTMLCodec
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

class TooltipTagLibTests extends grails.test.TagLibUnitTestCase {

    void testTipValue() {
        def mockString = mockFor(String)
        mockString.demand.encodeAsHTML(1..1) { HTMLCodec.encode(delegate) }

        tagLib.tip([value: '<someTag>tzu</someTag>']) {"////&\\\\"}
        def expected = """<span onmouseover="tooltip.show('&lt;someTag&gt;tzu&lt;/someTag&gt;');" onmouseout="tooltip.hide();">////&\\\\</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipValueWithUmlauts() {
        def mockString = mockFor(String)
        mockString.demand.encodeAsHTML(1..1) { HTMLCodec.encode(delegate) }

        tagLib.tip([value: 'φόδ']) {"someTextThatWillShowTheTooltip"}
        def expected = """<span onmouseover="tooltip.show('&ouml;&uuml;&auml;');" onmouseout="tooltip.hide();">someTextThatWillShowTheTooltip</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipCode() {
        def myI18nMessage = "myI18nMessage"
        def mockString = mockFor(String)
        mockString.demand.encodeAsHTML(1..1) { HTMLCodec.encode(delegate) }
        tagLib.metaClass.message = {myI18nMessage}

        tagLib.tip([code: 'someCode']) {"b"}
        def expected = """<span onmouseover="tooltip.show('$myI18nMessage');" onmouseout="tooltip.hide();">b</span>"""
        assertEquals expected, tagLib.out.toString()
    }

    void testTipCodeAndValue() {
        def myI18nMessage = "myI18nMessage"
        def mockString = mockFor(String)
        mockString.demand.encodeAsHTML(1..1) { HTMLCodec.encode(delegate) }
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