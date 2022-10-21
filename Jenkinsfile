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
		string(nsme:'path', defaultValue:'',description:'Path to Project')
		string(name: 'buildSteps', defaultValue: '',description:'Stages to process')
		string(name: 'environment', defaultValue: '',description:'Environment')
		string(name: 'datadogDog', defaultValue: 'false',description:'Environment')
		
	}
  stages {
	
	stage('Starting Main') {
	
		steps{
			script{
				print "--------------*********-------------- Main Pipeline Started --------------*********--------------" 
				sh "pwd"
				sh "$WORKSPACE"
				print "Environment : ${params.environment}"
				print "Build Steps : ${params.buildSteps}"
				print env.BRANCH_NAME
				def steps=(params.buildSteps).split(',')
			}
		}
		
	}
  
  }
 
 }