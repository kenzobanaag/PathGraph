package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.PathPoint
import java.util.*

/*
* Creates a path from locations.
* */
class PathBuilder : IPathBuilder{
    private val path = LinkedList<PathPoint>()
    private lateinit var firstLocation : Location

    override fun buildPath() : List<PathPoint> {
        return path
    }

    override fun logLocation(location: Location) {
        //first location, we want to set it to first
        if(path.size == 0) {
            firstLocation = location
        }
        path.add(CoordinateUtil.getPointDistance(location, firstLocation))
    }
}