package tsarionovtimofey.tests.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tsarionovtimofey.tests.dashboard.placeholder.PlaceholderContent

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyDashboardRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardFragment().apply {
                arguments = Bundle()
            }
    }
}