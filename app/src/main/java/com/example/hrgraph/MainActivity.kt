package com.example.hrgraph

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.Legend
import android.support.v4.os.HandlerCompat.postDelayed
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var x = 0f
        var y = 0f
        val handler = Handler()
        val delay: Long = 1000 //milliseconds


        var list = arrayListOf<Entry>()
        val rn = Random()

        var lineDataSet = LineDataSet(list, "Data Set 1")

        val color = ContextCompat.getColor(this, android.R.color.white)

        lineDataSet.color = color
        var dataSets = arrayListOf<LineDataSet>()
        dataSets.add(lineDataSet)

        var data = LineData(dataSets as List<ILineDataSet>?)
        mChart.data = data

        handler.postDelayed(object : Runnable {
            override fun run() {
                val bpm = rn.nextInt(31)+60.toFloat()
                lineDataSet.addEntry(Entry(x++, bpm))
                data.notifyDataChanged()
                mChart.notifyDataSetChanged()
                mChart.invalidate()
                tv_bpm_content.text = ""+bpm.toInt()
//                Toast.makeText(applicationContext,""+bpm,Toast.LENGTH_SHORT).show()
                handler.postDelayed(this, delay)
            }
        }, delay)

        mChart.getAxisLeft().setDrawLabels(false)
        mChart.getAxisRight().setDrawLabels(false)
        mChart.getXAxis().setDrawLabels(false)
        mChart.getAxisLeft().setDrawGridLines(false)
        mChart.getXAxis().setDrawGridLines(false)
        mChart.getAxisRight().setDrawGridLines(false)

        val xAxis = mChart.getXAxis()
        xAxis.setEnabled(false)

        val yAxis = mChart.getAxisLeft()
        yAxis.setEnabled(false)

        val yAxis2 = mChart.getAxisRight()
        yAxis2.setEnabled(false)

        mChart.setDrawBorders(false)
        mChart.setDrawGridBackground(false)

        mChart.getLegend().setEnabled(false)
        // no description text
        mChart.getDescription().setEnabled(false)
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)

        val legend = mChart.legend
        legend.isEnabled = false

        lineDataSet.lineWidth = 3f
        mChart.setViewPortOffsets(0f, 0f, 0f, 0f)
    }
}
