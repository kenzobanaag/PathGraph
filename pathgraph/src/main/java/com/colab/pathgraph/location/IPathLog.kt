package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.Point

interface IPathLog {
    fun getLogs() : List<Point>
    fun logLocation(location : Location)
}