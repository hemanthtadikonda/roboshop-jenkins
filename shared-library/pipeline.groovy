def call () {
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
                    expression { env.TAG_NAME != null}
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

            stage ('Realse') {
                when {
                    expression {env.TAG_NAME ==~ ".*"}
                }
                steps {
                    // sh 'upload artifact to nexus command
                    print 'OK NOOO'
                }

            }
        }

    }
}