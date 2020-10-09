package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.Point
import java.util.*

/*
* Creates a path from locations.
* */
class PathLog : IPathLog{
    private val path = LinkedList<Point>()
    private lateinit var firstLocation : Location

    override fun getLogs() : List<Point> {
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