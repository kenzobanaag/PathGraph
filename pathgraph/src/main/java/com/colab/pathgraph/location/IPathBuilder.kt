package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.PathPoint

interface IPathBuilder {
    fun buildPath() : List<PathPoint>
    fun logLocation(location : Location)
}