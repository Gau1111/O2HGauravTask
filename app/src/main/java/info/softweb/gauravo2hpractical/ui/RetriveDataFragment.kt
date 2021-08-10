package info.softweb.gauravo2hpractical.ui

import android.content.Context.CONNECTIVITY_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import android.net.ConnectivityManager
import android.view.*
import android.widget.AbsListView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import info.softweb.gauravo2hpractical.R
import info.softweb.gauravo2hpractical.adapters.DataAdapter
import info.softweb.gauravo2hpractical.databinding.FragmentRetriveDataBinding
import info.softweb.gauravo2hpractical.models.Picture
import info.softweb.gauravo2hpractical.models.UserResult
import info.softweb.gauravo2hpractical.viewmodels.DataViewModel

class RetriveDataFragment : Fragment() {
    private var scrollOutItems: Int=0
    private var totalItems: Int=0
    private var currentItems: Int=0
    private var isScrolling: Boolean=false
    lateinit var binding: FragmentRetriveDataBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var adapter: DataAdapter
    private lateinit var gridLinearLayoutManager:GridLayoutManager
    private var listData=ArrayList<UserResult>()
    private var RESULT_PER_PAGE=10
    private val args : RetriveDataFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.intializeRepository(this.requireContext())
        setHasOptionsMenu(true)
        setObservers()
        callApi()
        onBackPress()

    }

    private fun onBackPress() {
        requireActivity().onBackPressedDispatcher?.addCallback(this,object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               requireActivity().finish()
            }
        })
    }

    private fun setObservers() {
        viewModel.loading.observe(this) { isDisplay->
            if(isDisplay)
            {
                binding.progressBar.visibility=View.VISIBLE
            }
            else
            {
                binding.progressBar.visibility=View.GONE
            }
        }

        viewModel.isNetworkConnected.observe(this) { isConnected ->
            if(isConnected && isNetworkConnected())
            {
                viewModel.data.observe(this, Observer {
                    // listData.addAll(it.data as ArrayList)
                    adapter.updateData(it.results!!)
                })
            }
            else if(!isConnected && !isNetworkConnected())
            {
                if(viewModel.data.value==null || viewModel.data.value!!.results!!.isNullOrEmpty())
                {
                    viewModel.localdata?.observe(this) { localList ->
                        val tempList=ArrayList<UserResult>()
                        if(localList!!.isNotEmpty())
                        {
                            for(i in localList.indices)
                            {
                                tempList.add(UserResult(localList[i].cell,null,localList[i].email,localList[i].gender,
                                null,null,null,null,null,localList[i].phone,
                                    Picture(null,localList[i].thumbnail,null)
                                ))
                            }

                            adapter.updateData(tempList)
                        }
                    }
                }
            }
        }

    }

    private fun callApi() {
        viewModel.getServerData(RESULT_PER_PAGE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_retrive_data, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuProfile ->{
                val action=RetriveDataFragmentDirections.actionRetriveDataFragmentToProfileFragment()
                action.googleAccount=args.googleAccount
                findNavController().navigate(action)
                true
            }
            else ->{
                false
            }
        }
    }


    private fun initViews() {
        gridLinearLayoutManager = GridLayoutManager(activity, 2)
        adapter = DataAdapter(
            this.requireContext(),
            listData
        )
        binding.recyclerview.layoutManager = gridLinearLayoutManager
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    currentItems = gridLinearLayoutManager.getChildCount()
                    totalItems = gridLinearLayoutManager.getItemCount()
                    scrollOutItems = gridLinearLayoutManager.findFirstVisibleItemPosition()
                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false
                        RESULT_PER_PAGE+=10
                       callApi()
                    }
                }
            }
        })
    }

    private fun isNetworkConnected(): Boolean {
        val cm = this.requireContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}