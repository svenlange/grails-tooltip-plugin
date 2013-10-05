class TooltipGrailsPlugin {
    def version = "0.8"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
            "grails-app/views/*",
            "grails-app/i18n/*",
            "grails-app/controllers/*"
    ]
    def title = "Tooltip Plugin"
    def author = "Sven Lange"
    def authorEmail = "email@svenlange.co.za"
    def description = "This plugin provides a lightweight and lightning fast tooltip that is very easy to use."
    def documentation = "http://grails.org/plugin/tooltip"
    def license = "APACHE"
    def issueManagement = [system: "JIRA", url: "http://jira.grails.org/browse/GPTOOLTIP"]
    def scm = [url: "https://github.com/svenlange/grails-tooltip-plugin.git"]
}