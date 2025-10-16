package com.example.gurshan_aulakh_stressmeter.ui.gridview

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.gurshan_aulakh_stressmeter.databinding.FragmentGridviewBinding
import com.example.gurshan_aulakh_stressmeter.R
import com.example.gurshan_aulakh_stressmeter.SelectedImage

class GridViewFragment : Fragment() {
    private val images = listOf(
        R.drawable.fish_normal017,
        R.drawable.psm_mountains11,
        R.drawable.psm_alarm_clock,
        R.drawable.psm_neutral_child,
        R.drawable.psm_alarm_clock2,
        R.drawable.psm_neutral_person2,
        R.drawable.psm_angry_face,
        R.drawable.psm_peaceful_person,
        R.drawable.psm_anxious,
        R.drawable.psm_puppy,
        R.drawable.psm_baby_sleeping,
        R.drawable.psm_puppy3,
        R.drawable.psm_bar,
        R.drawable.psm_reading_in_bed2,
        R.drawable.psm_barbed_wire2,
        R.drawable.psm_running3,
        R.drawable.psm_beach3,
        R.drawable.psm_running4,
        R.drawable.psm_bird3,
        R.drawable.psm_sticky_notes2,
        R.drawable.psm_blue_drop,
        R.drawable.psm_stressed_cat,
        R.drawable.psm_cat,
        R.drawable.psm_stressed_person,
        R.drawable.psm_clutter,
        R.drawable.psm_stressed_person12,
        R.drawable.psm_clutter3,
        R.drawable.psm_stressed_person3,
        R.drawable.psm_dog_sleeping,
        R.drawable.psm_stressed_person4,
        R.drawable.psm_exam4,
        R.drawable.psm_stressed_person6,
        R.drawable.psm_gambling4,
        R.drawable.psm_stressed_person7,
        R.drawable.psm_headache,
        R.drawable.psm_stressed_person8,
        R.drawable.psm_headache2,
        R.drawable.psm_talking_on_phone2,
        R.drawable.psm_hiking3,
        R.drawable.psm_to_do_list,
        R.drawable.psm_kettle,
        R.drawable.psm_to_do_list3,
        R.drawable.psm_lake3,
        R.drawable.psm_wine3,
        R.drawable.psm_lawn_chairs3,
        R.drawable.psm_work4,
        R.drawable.psm_lonely,
        R.drawable.psm_yoga4,
        R.drawable.psm_lonely2
    )
    private var startIndex=0
    private val pageSize=16
    private var _binding: FragmentGridviewBinding? = null
    private lateinit var randomArray: List<Int>
    private lateinit var gridViewModel: GridViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gridViewModel =
            ViewModelProvider(requireActivity()).get(GridViewModel::class.java)

        _binding = FragmentGridviewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        showImages()

        binding.moreImagesButton.setOnClickListener {
            gridViewModel.array.value=emptyList()
            showImages()

        }

        binding.gridView.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(requireContext(), "clicked image index: $position", Toast.LENGTH_SHORT).show()
            val selectedImageResourceID = gridViewModel.array.value!![position]
            val intent = Intent(requireContext(), SelectedImage::class.java)
            intent.putExtra("selectedImageResId", selectedImageResourceID)
            intent.putExtra("position",position)
            startActivity(intent)
        }

        return root
    }
    fun showImages(){
        if(gridViewModel.array.value.isNullOrEmpty()){
            gridViewModel.array.value = images.shuffled().subList(startIndex, pageSize)
        }
        binding.gridView.adapter = GridViewAdapter(requireContext(), gridViewModel.array.value!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
