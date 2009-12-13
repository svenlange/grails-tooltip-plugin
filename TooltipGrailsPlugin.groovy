class TooltipGrailsPlugin {

    def version = "0.5"
	
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.0.RC1 > *"
	
    // the other plugins this plugin depends on
    def dependsOn = [:]
	
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Sven Lange"
    def authorEmail = "sv3n.lange@gmail.com"
    def title = "Tooltip Plugin"
    def description = "This plugin provides a lightweight and lightning fast tooltip that is very easy to use."
    def documentation = "http://grails.org/plugin/tooltip"
}
