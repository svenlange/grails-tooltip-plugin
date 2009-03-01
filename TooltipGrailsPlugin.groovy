class TooltipGrailsPlugin {
    def version = "0.1-alpha"
    def grailsVersion = "1.1-RC2 > *"
    def dependsOn = [:]
    def pluginExcludes = [
		"grails-app/views/error.gsp"
    ]
	
    def author = "Sven Lange"
    def authorEmail = "sv3n.lange@gmail.com"
    def title = "Tooltip Plugin"
    def description = '''Lightweight tooltip plugin.'''
    def documentation = "http://grails.org/Tooltip+Plugin"
}