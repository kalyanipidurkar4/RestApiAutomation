pipeline
{
     agent any

    stages
     {
       stage('checkout code')
        {
          steps
          {
            checkout scm
          }
        }

       stage('Build & Test')
       {
         steps
         {
           bat 'mvn clean test'
         }
       }
     }

     post
     {
      always
      {
       junit 'target/surefire-reports/*.xml'
      }
     }


 }