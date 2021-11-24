package com.example.gitbug.UI.HomeScreen.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitbug.R

class CommentListActivity : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var body : TextView
    lateinit var IssueId : TextView
    lateinit var CommentNumber : TextView
    lateinit var recyclerView: RecyclerView
    lateinit var noCommenttxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_list)
        init()
        setIssueDetailsUIData()
    }

    private fun init() {
        title = findViewById(R.id.issueTitle)
        body = findViewById(R.id.bodytxt)
        IssueId = findViewById(R.id.issueId)
        CommentNumber = findViewById(R.id.numComment)
        recyclerView = findViewById(R.id.commentRec)
        noCommenttxt = findViewById(R.id.noCommenttxt)
    }
    private fun setIssueDetailsUIData(){
        if(intent.extras!=null) {
            var getId: Int = intent.getIntExtra("CommentId", -1)
            var IssueTitle: String? = intent.getStringExtra("title")
            var IssueBody: String? = intent.getStringExtra("body")
            var NumberOfComment: Int = intent.getIntExtra("CNum", 0)
            if(IssueBody.isNullOrEmpty()){
                IssueBody = "Description Not Available..."
            }
            title.text = IssueTitle
            body.text = IssueBody
            IssueId.text = getId.toString()
            CommentNumber.text = NumberOfComment.toString() + " Comments"
            if(NumberOfComment==0){
                noCommenttxt.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
    }
}