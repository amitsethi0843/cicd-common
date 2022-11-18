

void build(){
	sh "mvn clean install"
}

void deploy (def deployParams){
	def mandatoryKeys = ['chUser','chPassword','env','chClientID','chClientSecret','key','secureKey','businessGroup']
	mandatoryKeys.each {
	if(!deployParams[it] ?: null)
		throw new Exception(" ${it} key is missing from the deployment parameters")
		}

	sh "mvn clean install deploy -DskipTests -Dcloudhub.username=${deployParams.chUser} -P=${deployParams.env} -Dcloudhub.password=${deployParams.chPassword} -DCLOUDHUB_CLIENT_ID=${deployParams.chClientID} -DCLOUDHUB_CLIENT_SECRET=${deployParams.chClientSecret} -DMULE_KEY=${deployParams.key} -DMULE_SECUREKEY=${deployParams.secureKey}  -DCLOUDHUB_BUSINESS_GROUP=${deployParams.businessGroup}"
}

return this
