def compile() {
   if(env.codeType == "python" || env.codeType == "HTML"){
      return "Return, No need of compilation"
   }
   stage('compile'){
      if(env.codeType == "maven") {
         sh '/root/maven/bin/mvn package'
      }
      if(env.codeType == "nodejs"){
         sh 'npm install'
      }
   }
}

def test() {
   stage('test cases'){
      if(env.codeType == "maven"){
         //sh '/root/maven/bin/mvn test'
         print 'code test'
      }
      if(env.codeType == "nodejs"){
         //sh 'npm test'
         print 'code test'
      }
      if(env.codeType == "python"){
         //sh 'python3.6 -m unittest'
         print 'code test'
      }
   }
}

def codequality(){
   stage('code quality'){
      if(env.codeType == "maven"){
         //sh 'sonar command'
         print 'code Quality'
      }else {
         //sh 'sonar command'
         print 'code Quality'
      }
   }
   
}

def codesecurity(){
   stage('code security'){
      print 'code security'
   }
}

def release(){
   stage('Release') {
      sh 'echo ${TAG_NAME} >VERSION'
      if (env.codeType == "maven"){
         sh 'cp target/${component}-1.0.jar ${component}.jar; zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION ${schemadir}'
      }else if (env.codeTppe == "nodejs") {
      sh 'zip -r ${component}-${TAG_NAME}.zip server.js node_modules VERSION ${schemadir}'
      }else {
         sh 'zip -r ${component}-${TAG_NAME}.zip *'
      }
   }
}