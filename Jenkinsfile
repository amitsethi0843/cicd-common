
def ENV
def CONTINUE = true

pipeline {
    
	agent any
	
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }
	environment{
		ANYPOINT_CREDENTIALS = credentials('anypoint.credentials')
        SIT_ENV = credentials('SIT_CRED')
        SFDO_ORG = credentials('ORG_CRED')
		SCRIPT_PATH= "$WORKSPACE"
		JENKINS_SCRIPT_PATH = "/var/lib/jenkins/workspace/jenkins-scripts/"
        CPQ_UAT_KEY = credentials('CPQ_UAT_KEY')
        CPQ_UAT_SECUREKEY = credentials('CPQ_UAT_SECUREKEY')
	}
	parameters {
		string(name:'appPath', defaultValue:'',description:'Path to Project')
		string(name: 'buildSteps', defaultValue: '',description:'Stages to process')
		string(name: 'gitBranch', description:'git branch')
		string(name: 'project', description:'Project')

	}
  stages {
	
	stage('Starting Main') {
		steps{
		   
			script{
			    print "--------------*********-------------- Jenkins Main Pipeline Started --------------*********--------------" 
			    if(params.gitBranch.isBlank() || params.project.isBlank()){
			        CONTINUE = false
			    }
			    else{
			        def gitBranch = ((params.gitBranch.split("/"))[1]).toLowerCase()
    			    ENV = gitBranch.equals('main') ? 'prod' : (gitBranch.equals('uat') ? 'uat' : 'sit')
        			CONTINUE = true
			    }
			}
		}
		
	}
	stage('Datadog Integration') {
		when {
			expression {
					return (CONTINUE && (params.buildSteps.toLowerCase().split(',')).contains('datadog'))
				}
		}
		steps {
		    
			echo "--------------*********-------------- Datadog Integration Started --------------*********--------------"
			script {
			    dir(JENKINS_SCRIPT_PATH) {
			         def datadog = load "datadog.groovy"
			         try {
			             datadog.dataDogIntegration(params.appPath)
			         }
			         catch (Exception ex){
			             println "--------- Stopping further stages ---------------------"
			             CONTINUE = false
			         }
			    }
			}
			
		}
	}
	
	stage('Build') {
	    	when {
			expression {
					return (CONTINUE && (params.buildSteps.toLowerCase().split(',')).contains('build'))
				}
		}
		steps {
		    
			echo "--------------*********-------------- Build Started --------------*********--------------"
			script {
			    dir(appPath) {
			       sh "mvn clean install"
			    }
			  }
			
		}
	}
	
	stage('Deploy') {
	    	when {
			expression {
					return (CONTINUE && (params.buildSteps.toLowerCase().split(',')).contains('deploy'))
				}
		}
		steps {
		    
		    echo "--------------*********-------------- Deployment Started --------------*********--------------"
		    script {
			    dir(JENKINS_SCRIPT_PATH) {
			     //def deployParams = [chUser:ANYPOINT_CREDENTIALS_USR, chPassword:ANYPOINT_CREDENTIALS_PSW, env:ENV, chClientID:SFDO_ORG_USR, chClientSecret:SFDO_ORG_PSW, key:CPQ_UAT_KEY, secureKey:CPQ_UAT_SECUREKEY, businessGroup:] 

			        // def maven = load "maven.groovy"
			         //maven.deploy(deployParams)
			    }
			  }
			
		}
	}
  
  }
 
 }