class TooltipGrailsPlugin {
    def version = "0.7"
    def grailsVersion = "1.2.1 > *"
    def dependsOn = [:]
    def pluginExcludes = [
            "grails-app/views/*",
            "grails-app/i18n/*",
            "grails-app/controllers/*"
    ]
    def author = "Sven Lange"
    def authorEmail = "sv3n.lange@gmail.com"
    def title = "Tooltip Plugin"
    def description = "This plugin provides a lightweight and lightning fast tooltip that is very easy to use."
    def documentation = "http://grails.org/plugin/tooltip"
}