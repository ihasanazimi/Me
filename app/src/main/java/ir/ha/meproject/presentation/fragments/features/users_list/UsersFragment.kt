package ir.ha.meproject.presentation.fragments.features.users_list

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import dagger.hilt.android.AndroidEntryPoint
import ir.ha.meproject.common.base.BaseFragment
import ir.ha.meproject.databinding.FragmentUsersBinding
import ir.ha.meproject.presentation.adapters.UsersAdapter
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val viewModel by viewModels<UsersFragmentVM>()
    private val userAdapter: UsersAdapter = UsersAdapter()

    override fun initializing() {
        super.initializing()
        viewModel.getAllUsers()
    }


    override fun uiConfig() {
        super.uiConfig()
        binding.recyclerView.adapter = userAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
    }


    override fun observers() {
        super.observers()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect{ allUsers ->
                userAdapter.submitList(allUsers).also {
                    Log.i(TAG, "observers: allUsers is -> $it")
                }
            }
        }

    }

}