

void build(){
	sh "mvn clean install"
}

void deploy (def deployParams){
	def mandatoryKeys = ['chUser','chPassword','env','chClientID','chClientSecret','key','secureKey','businessGroup']
	mandatoryKeys.each {
	if(!deployParams[it] ?: null)
		throw new Exception(" ${it} key is missing from the deployment parameters")
		}

	sh "mvn clean deploy -DmuleDeploy -DskipTests -Dcloudhub.username=${deployParams.chUser} -P=${deployParams.env} -Dcloudhub.workers=${deployParams.totalWorkers} -Dcloudhub.worker_type=${deployParams.workerSize} -Dcloudhub.password=${deployParams.chPassword} -Dcloudhub.properties.client_id=${deployParams.chClientID} -Dcloudhub.properties.client_secret=${deployParams.chClientSecret} -Dcloudhub.properties.mule_key=${deployParams.key} -Dcloudhub.properties.mule_securekey=${deployParams.secureKey} -Dcloudhub.properties.ddApiKey=${deployParams.ddApiKey} -Dcloudhub.business_group=" + "${deployParams.businessGroup}"
}

return this
