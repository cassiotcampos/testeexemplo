package br.com.cassio.ribeiro.example.githubissues.model

data class IssueList (val issues: ArrayList<Issue>)
data class Issue(val url : String)