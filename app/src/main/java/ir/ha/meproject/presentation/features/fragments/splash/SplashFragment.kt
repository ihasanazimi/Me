package ir.ha.meproject.presentation.features.fragments.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ir.ha.meproject.common.base.BaseFragment
import ir.ha.meproject.common.espresso_util.MyCountingIdlingResource
import ir.ha.meproject.common.espresso_util.MyIdlingResource
import ir.ha.meproject.common.extensions.safeNavigate
import ir.ha.meproject.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel by viewModels<SplashFragmentVM>()

    private val byCountingIdlingResource : Boolean = false

    private val myIdlingResource by lazy { MyIdlingResource(TAG) }
    fun getIdlingResource() = myIdlingResource

    private val myCountingIdlingResource = MyCountingIdlingResource(TAG)
    fun getMyCountingIdlingResource() = myCountingIdlingResource

    override fun uiConfig() {
        super.uiConfig()


            if (byCountingIdlingResource) {

                viewLifecycleOwner.lifecycleScope.launch {
                    myCountingIdlingResource.increment()
                    delay(2000) // simulate to api call
                    myCountingIdlingResource.decrement()
                    myCountingIdlingResource.increment()
                    delay(2000) // simulate to loading
                    findNavController().safeNavigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment("Hasan")).also {
                        myCountingIdlingResource.decrement()
                    }
                }

            } else {

                viewLifecycleOwner.lifecycleScope.launch {
                    myIdlingResource.setIdleState(false)
                    delay(2000) // simulate to api call
                    myIdlingResource.setIdleState(true)

                    myIdlingResource.setIdleState(false)
                    delay(4000) // simulate to preparation of data
                    myIdlingResource.setIdleState(true)

                    myIdlingResource.setIdleState(false)
                    findNavController().safeNavigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment("Hasan")).also {
                        myIdlingResource.setIdleState(true)
                    }
                }

            }
        }





    }