class TooltipGrailsPlugin {
    def version = "0.4"
    def grailsVersion = "1.0 > 1.1.1"
    def dependsOn = [:]
    def pluginExcludes = [
		"grails-app/i18n",
        "grails-app/views"
    ]
	
    def author = "Sven Lange"
    def authorEmail = "sv3n.lange@gmail.com"
    def title = "Tooltip Plugin"
    def description = '''Lightweight tooltip plugin.'''
    def documentation = "http://grails.org/Tooltip+Plugin"
}