def call() {
    pipeline {
        agent any

        stages {
            stage('code compile') {
                steps {
                    sh 'env'
                    // depends upon component type
                    print 'OK'
                }
            }

            stage('Test cases') {
                when  {
                    allOf {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                steps {
                    // depends upon component type
                    print 'OK'
                }
            }

            stage ('code quality') {
                when {
                    allOf {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                //parameters {
                //    password(name: 'SONAR.PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
                //}
                steps {
                    //sh 'sonar scan command depends code type'
                    print 'OK'
                }
            }

            stage ('code security') {
                when {
                    allOf {
                        expression { env.BRANCH_NAME == "main"}
                        expression { env.TAG_NAME    == null}
                    }
                }
                //parameters {
                //    password(name: 'SONAR.PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
                //}
                steps {
                    //sh 'checkmarx sast sca'
                    print 'OK'
                }
            }

            stage ('Release App') {
                when {
                    expression {env.TAG_NAME ==~ ".*"}
                }
                //parameters {
                //    password(name: 'NEXUS.PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
                //}
                steps {
                    // sh 'upload artifact to nexus command
                    print 'Ok-Ok'
                }

            }
        }

    }
}