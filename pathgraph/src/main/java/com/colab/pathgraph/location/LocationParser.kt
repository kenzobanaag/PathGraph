package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.Point

/*
* Parse each location object to a path point.
*
* The path point will have the parsed x,y and the distance of the logged location to the last logged
* location.
*
* note: Possible issues:
*           firstLocation is always not initialized. After first location is logged, we init a new object
*           Specially when we go in and out of the application.
*
*           Test that.
* */
class LocationParser {
    //static object
    companion object {
        private lateinit var firstLocation : Location
        private lateinit var previousLocation : Location

        fun parseLocation(location : Location) : Point {
            synchronized(this) {
                if(!(::firstLocation.isInitialized)) {
                    firstLocation = location
                    previousLocation = location
                }
                val distanceFromLastPoint = location.distanceTo(previousLocation)
                previousLocation = location
                return CoordinateUtil.getPointDistance(location, firstLocation).apply {
                    m = distanceFromLastPoint
                }
            }
        }
    }
}