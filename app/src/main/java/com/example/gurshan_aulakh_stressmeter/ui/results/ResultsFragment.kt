package com.example.gurshan_aulakh_stressmeter.ui.results

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.example.gurshan_aulakh_stressmeter.databinding.FragmentGridviewBinding
import com.example.gurshan_aulakh_stressmeter.databinding.FragmentResultsBinding
import com.example.gurshan_aulakh_stressmeter.ui.gridview.GridViewModel
import java.io.File
import com.example.gurshan_aulakh_stressmeter.R
import java.math.BigDecimal


class ResultsFragment: Fragment() {
    private var _binding: FragmentResultsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getDataFromCSV()
        setupLineChart()
        return root
    }

    private fun setupLineChart() {
        var x = BigDecimal.ZERO
        val step = BigDecimal("0.6")
        val anyChartView: AnyChartView = binding.lineChartView
        val cartesian = AnyChart.line() // Create line chart
        val data = ArrayList<DataEntry>()
        val file = File(requireContext().filesDir,"stress_timestamp.csv")
        file.forEachLine { line->
            x+=step
          val parts = line.split(",")
          //val x = parts[0]
          val y = parts[1].toInt()
            data.add(ValueDataEntry(x,y))
        }

        val series = cartesian.line(data) // add data to chart
        series.name("Stress Level")
        series.hovered().markers().enabled(true)
        series.hovered().markers().type(MarkerType.CIRCLE)
        series.hovered().markers().size(4.0)
        series.tooltip()
            .positionMode(TooltipPositionMode.POINT)
            .anchor(Anchor.LEFT_TOP)
            .offsetX(5.0)
            .offsetY(5.0)

//        cartesian.title("A graph showing your Stress Levels")
        cartesian.yAxis(0).title("Stress Level")
        cartesian.xAxis(0).title("Instances")

        anyChartView.setChart(cartesian)
    }
    fun getDataFromCSV(){
        val file = File(requireContext().filesDir,"stress_timestamp.csv")
        val tableLayout = binding.tableView
        file.forEachLine { line->
            val parts = line.split(",")
            val row = TableRow(context).apply{
                setBackgroundResource(R.drawable.table_row_border)
            }
            val textViewForTimestamp = TextView(context).apply{
                text = parts[0]
                layoutParams =TableRow.LayoutParams(0,WRAP_CONTENT, 1f)
            }
            val textViewForStress = TextView(context).apply{
                text = parts[1]
                layoutParams =TableRow.LayoutParams(0,WRAP_CONTENT, 1f)
                setBackgroundResource(R.drawable.table_row_border)
            }
            textViewForTimestamp.text = parts[0]
            textViewForTimestamp.setPadding(10,0,0,0)
            textViewForStress.setPadding(10,0,0,0)
            row.addView(textViewForTimestamp)
            row.addView(textViewForStress)
            tableLayout.addView(row)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}