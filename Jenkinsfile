pipeline {
  agent any
    tools {
        maven "MAVEN_HOME"
    }
	environment{
		ANYPOINT_CREDENTIALS = credentials('anypoint.credentials')
        SIT_ENV = credentials('SIT_CRED')
        ORG = credentials('ORG_CRED')
	}
	parameters {
		string(name:'path', defaultValue:'',description:'Path to Project')
		string(name: 'buildSteps', defaultValue: '',description:'Stages to process')
		string(name: 'mule.env', defaultValue: '',description:'Environment')
		string(name: 'datadog', defaultValue: 'false',description:'Environment')
		
	}
  stages {
	
	stage('Starting Main') {
	
		steps{
			script{
				print "--------------*********-------------- Main Pipeline Started --------------*********--------------" 
				sh "pwd"
				print "$WORKSPACE"
				print params.buildSteps
				print env.BRANCH_NAME
				def steps=(params.buildSteps).split(',')
			}
		}
		
	}
  
  }
 
 }