
def ENV
def CONTINUE = true
def SUPPORTED_PROJECTS = ["cpq","mavenlink","salesforce devops","ciena","Enterprise Integration"]
def BUSINESS_GROUP
pipeline {
    
	agent any
	
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }
	environment{
		ANYPOINT_CREDENTIALS = credentials('anypoint.credentials')
        SIT_ENV = credentials('SIT_CRED')
        ORG_CREDS = credentials('ORG_CRED')
		SCRIPT_PATH= "$WORKSPACE"
		JENKINS_SCRIPT_PATH = "/var/lib/jenkins/workspace/jenkins-main/"
		CPQ_PROD_KEY = credentials('CPQ_PROD_KEY')
		CPQ_PROD_SECUREKEY = credentials('CPQ_PROD_SECUREKEY')
        CPQ_UAT_KEY = credentials('CPQ_UAT_KEY')
        CPQ_UAT_SECUREKEY = credentials('CPQ_UAT_SECUREKEY')
		CPQ_SIT_KEY = credentials('CPQ_SIT_KEY')
		CPQ_SIT_SECUREKEY = credentials('CPQ_SIT_SECUREKEY')
		DD_API_KEY= credentials('DD_API_KEY')
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
			    if(params.gitBranch.isBlank() || params.project.isBlank() ||  !(params.project.toLowerCase() in SUPPORTED_PROJECTS)){
			        CONTINUE = false
			    }
			    else{

			        def gitBranch = ((params.gitBranch.split("/"))[1]).toLowerCase()
    			    ENV = gitBranch.equals('main') ? 'prod' : (gitBranch.equals('uat') ? 'uat' : 'sit')
					BUSINESS_GROUP = params.project.toLowerCase() in ['cpq','mavenlink'] ? "Salesforce DevOps" : null
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
			   // dir(JENKINS_SCRIPT_PATH) {
			         def datadog = load "datadog.groovy"
			         try {
			             datadog.dataDogIntegration(params.appPath)
			         }
			         catch (Exception ex){
			             println "--------- Stopping further stages ---------------------"
			             CONTINUE = false
			         }
			   // }
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
				def mvn = load "maven.groovy"
				dir(appPath) {
					mvn.build()
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
			print ORG_CREDS_USR
		    script {
			    dir(JENKINS_SCRIPT_PATH) {
					String key = ENV.equals('prod') ? CPQ_PROD_KEY : (ENV.equals('uat') ? CPQ_UAT_KEY : CPQ_SIT_KEY)
					print "-----------------------------" ++ ENV.equals('prod') ? 'prod key' : (ENV.equals('uat') ? 'uat key' : 'sit key')
					String secureKey = ENV.equals('prod') ? CPQ_PROD_SECUREKEY : (ENV.equals('uat') ? CPQ_UAT_SECUREKEY : CPQ_SIT_SECUREKEY)
					def deployParams = [chUser:ANYPOINT_CREDENTIALS_USR, chPassword:ANYPOINT_CREDENTIALS_PSW, env:ENV, chClientID:ORG_CREDS_USR , 
										chClientSecret:ORG_CREDS_PSW, key:key, secureKey:secureKey, businessGroup: BUSINESS_GROUP, ddApiKey: DD_API_KEY] 
					def mvn = load "maven.groovy"
					dir(appPath) {
						mvn.deploy(deployParams)
					}
			    }
			  }
			
		}
	}
  
  }
 
 }