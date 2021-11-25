package com.example.gitbug.UI.HomeScreen.Home

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitbug.Adapter.CommentListAdapter
import com.example.gitbug.ApiService.ApiServices
import com.example.gitbug.R
import com.example.gitbug.Repository.CommentRepository
import com.example.gitbug.ViewModal.CommentViewModel.CommentViewModel
import com.example.gitbug.ViewModal.CommentViewModel.CommentViewModelFactory
import androidx.lifecycle.Observer


class CommentListActivity : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var body : TextView
    lateinit var IssueId : TextView
    lateinit var CommentNumber : TextView
    lateinit var recyclerView: RecyclerView
    lateinit var noCommenttxt : TextView
    lateinit var viewModel: CommentViewModel
    lateinit var progressBar: ProgressBar
    private val retrofitService = ApiServices.getInstance()

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
        progressBar = findViewById(R.id.cprogressBar)
        viewModel =
            ViewModelProvider(this, CommentViewModelFactory(CommentRepository(retrofitService))).get(
                CommentViewModel::class.java
            )
    }
    private fun setIssueDetailsUIData(){
        if(intent.extras!=null) {
            var getId: Int = intent.getIntExtra("CommentId", -1)
            var IssueTitle: String? = intent.getStringExtra("title")
            var IssueBody: String? = intent.getStringExtra("body")
            var NumberOfComment: Int = intent.getIntExtra("CNum", 0)

            title.text = IssueTitle
            IssueId.text = getId.toString()
            CommentNumber.text = NumberOfComment.toString() + " Comments"
            if(NumberOfComment==0){
                noCommenttxt.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            if(IssueBody.isNullOrEmpty()){
                IssueBody = getString(R.string.description_not_available)
            }
            if(NumberOfComment>0) {
                progressBar.visibility = View.VISIBLE
                fetchAllComment(getId)
            }
            body.text = IssueBody
        }
    }

    private fun fetchAllComment(id:Int) {
        viewModel.commentList.observe(this, Observer {
            val recyclerViewAdapter = CommentListAdapter(this, it)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = recyclerViewAdapter
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "Error -----------------------> ")
        })
        viewModel.getAllCommentList(id)
        progressBar.visibility = View.GONE
    }

}