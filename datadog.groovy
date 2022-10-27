void dataDogIntegration(def targetWorkspace) {
	def targetPath =  "${targetWorkspace}/src/main/resources/"
	print "----------------Datadog Integration Started----------------"
	sh "sudo rm ${targetPath}log4j2.xml"
	print "Removed log4j2 from ${targetPath}"
	sh "sudo cp resources/log4j2.xml ${targetPath}"
	print "Copied datadog log4j2 to ${targetPath}"
	
}

return this




