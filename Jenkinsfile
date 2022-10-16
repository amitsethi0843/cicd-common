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
				echo "--------------*********-------------- Main Pipeline Started --------------*********--------------"
				def steps=(params.buildSteps).split(',')
				print steps
			}
		}
		
	}
  
  }
 
 }