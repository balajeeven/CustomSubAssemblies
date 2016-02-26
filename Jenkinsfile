#!groovy

stage 'Dev'
node {
    checkout scm
    gradle 'clean package'
    dir('target') {stash name: 'war', includes: 'x.war'}
}


def gradle(args) {
    sh "${tool 'gradle-2.7'}/bin/gradle ${args}"
}

def runTests(duration) {
    node {
        sh "sleep ${duration}"
        }
    }

def deploy(id) {
    unstash 'war'
    sh "cp x.war /tmp/${id}.war"
}


