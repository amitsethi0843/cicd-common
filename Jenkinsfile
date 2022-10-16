pipeline {
  agent any
    tools {
        maven "MAVEN_HOME"
    }
    
	parameters {
		string(name: 'buildSteps', defaultValue: '',description:'Stages to process')
		string(name: 'env', defaultValue: 'develop',description:'Environment')
		
	}
  stages {
	
	stage('Starting Main') {
	
		steps{
			script{
				print "--------------*********-------------- Main Pipeline Started --------------*********--------------" 
				
				print "Environment : ${params.buildSteps}"
				print "Build Steps : ${params.buildSteps}"
				def steps=(params.buildSteps).split(',')
			}
		}
		
	}
  
  }
 
 }