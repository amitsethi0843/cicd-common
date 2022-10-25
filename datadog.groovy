void dataDogIntegration(def targetWorkspace) {
	def targetPath =  "${targetWorkspace}/src/main/resources/"
	print "----------------Datadog Integration Started----------------"
	sh "rm ${targetPath}/log4j2.xml"
	print "Removed log4j2 from ${targetPath}"
	sh "cp resources/log4j2.xml ${targetPath}"
	print "Copied datadog log4j2 to ${targetPath}"
	
}

return this
