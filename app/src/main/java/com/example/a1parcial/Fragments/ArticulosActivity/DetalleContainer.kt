@file:Suppress("DEPRECATION")

package com.example.a1parcial.Fragments.ArticulosActivity

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.a1parcial.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetalleContainer : Fragment() {

    companion object {
        fun newInstance() = DetalleContainer()
    }

    private lateinit var viewModel: DetalleContainerViewModel

    lateinit var DetalleContainerView: View
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    private val titles =
        arrayOf("Detalle", "Categoria")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DetalleContainerView = inflater.inflate(R.layout.detalle_container_fragment, container, false)

        tabLayout = DetalleContainerView.findViewById(R.id.tab_layout)

        viewPager = DetalleContainerView.findViewById(R.id.view_pager)


        return DetalleContainerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetalleContainerViewModel::class.java)
        // TODO: Use the ViewModel
    }




    override fun onStart() {
        super.onStart()


        viewPager.setAdapter(createCardAdapter())
        // viewPager.isUserInputEnabled = false
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "Detalle"
                1 -> tab.text = "Categoría"
                else -> tab.text = "undefined"
            }
        }).attach()
    }
    private fun createCardAdapter(): ViewPagerAdapter? {
        return ViewPagerAdapter(requireActivity())
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {


        override fun createFragment(position: Int): Fragment {

            return when(position){
                0 -> ArticulosDetalle()
                1 -> DetalleCategoria()

                else -> ArticulosDetalle()
            }
        }

        override fun getItemCount(): Int {
            return TAB_COUNT
        }

        companion object {
            private const val TAB_COUNT = 2
        }
    }

}
