pipeline {
  agent any
    tools {
        maven "MAVEN_HOME"
    }
	environment{
		ANYPOINT_CREDENTIALS = credentials('anypoint.credentials')
        SIT_ENV = credentials('SIT_CRED')
        ORG = credentials('ORG_CRED')
		SCRIPT_PATH= "$WORKSPACE"

	}
	parameters {
		string(name:'path', defaultValue:'',description:'Path to Project')
		string(name: 'buildSteps', defaultValue: '',description:'Stages to process')
		string(name: 'gitBranch', defaultValue: 'origin/develop',description:'git branch')
		string(name: 'datadog', defaultValue: 'false',description:'Environment')
		
	}
  stages {
	
	stage('Starting Main') {
	
		steps{
			script{
				print "--------------*********-------------- Main Pipeline Started --------------*********--------------" 
				print WORKSPACE
				print params.buildSteps
				print params.gitBranch
				print params.path
				print SCRIPT_PATH
				def steps=(params.buildSteps).split(',')
			}
		}
		
	}
	stage('Datadog Integration') {
		when {
			expression {
					return params.datadog.toBoolean()
				}
		}
		steps {
			echo "--------------*********-------------- Datadog Integration Started --------------*********--------------"
			
		}
	}
	
	stage('Build') {
		steps {
			echo "--------------*********-------------- Build Started --------------*********--------------"
			
		}
	}
	
	stage('Deploy') {
		steps {
			echo "--------------*********-------------- Deployment Started --------------*********--------------"
			
		}
	}
  
  }
 
 }