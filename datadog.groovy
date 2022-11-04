void dataDogIntegration(def targetWorkspace) throws Exception {
	print "----------------Datadog Integration Started----------------"

	try{
		def targetPath =  "${targetWorkspace}/src/main/resources/"
		File originalLog = new File("${targetPath}log4j2.xml")
		originalLog.delete()
		//sh "sudo rm ${targetPath}log4j2.xml"
		print "Removed log4j2 from ${targetPath}"
		def srcLog4J = new File("/var/lib/jenkins/workspace/jenkins-scripts/resources/log4j2.xml")
		def targetLog4J = new File("${targetPath}log4j2.xml")
		targetLog4J.createNewFile()
		targetLog4J << srcLog4J.text
		//sh "sudo cp xresources/log4j2.xml ${targetPath}"
		print "Copied datadog log4j2 to ${targetPath}"
	}
	catch(Exception ex){
		print "---------- Error occured ---------- " + ex.getMessage()
		throw ex
	}
}

return this




