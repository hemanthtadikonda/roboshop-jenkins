def call(){
   node('work station') {

      if (env.BRANCH_NAME == "main") {
         common.compile()
         common.test()
         common.codequality()
         common.codesecurity()
      } else {
         common.compile()
         common.test()
      }
      if (env.TAG_NAME ==~ ".*") {
         common.compile()
         common.release()
      }
   }
}