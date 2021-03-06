package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.Point
import java.util.*

/*
* Keeps track of path, distance, speed, time
* */
class PathTracker : IPathLog, IRunTracker{
    private val path = LinkedList<Point>()
    private lateinit var firstLocation : Location
    private lateinit var previousLocation : Location
    private var startTime : Long = 0L
    private var endTime : Long = 0L
    private var estimatedDistance : Double = 0.0 // meters

    companion object {
        private const val MILES_PER_METER = 0.000621371
        private const val MILLISECONDS = 1000
        private const val SECONDS_PER_HOUR : Double = 3600.0 //needs to be a double so we dont lose information when dividing
    }

    override fun getLogs(): List<Point> {
        endTime = System.currentTimeMillis()  //stop time
        return path
    }

    override fun logLocation(location: Location) {
        if(path.size == 0) {
            firstLocation = location
            previousLocation = location
            startTime = System.currentTimeMillis() //start time
        }
        path.add(CoordinateUtil.getPointDistance(location, firstLocation))
        //add distances using prevLoc
        estimatedDistance += location.distanceTo(previousLocation)
        previousLocation = location
    }

    /*
    * Get estimate speed miles per hour?
    * */
    override fun getSpeed(): Double {
        return getSpeed(getTime())
    }

    /*
    * If time can be paused, pass in the time to get a more accurate speed
    * */
    override fun getSpeed(timeSeconds: Long): Double {
        //calculate speed by using long time and distance
        if(timeSeconds < 0)
            return 0.0
        return getDistance() / (timeSeconds / SECONDS_PER_HOUR)
    }

    /*
    * Get estimate distance in miles
    * */
    override fun getDistance(): Double {
        return estimatedDistance * MILES_PER_METER
    }

    /*
    * Get duration of logging locations in seconds
    * Still need to convert long to hh:mm:ss
    *
    * note: This method assumes that we don't stop logging locations.
    * */
    override fun getTime(): Long {
        if(endTime.compareTo(0) == 0 || startTime.compareTo(0) == 0) //timer never started or stopped
            return -1
        return (endTime - startTime) / MILLISECONDS
    }

    override fun recalculateDistance(points: List<Point>) {
        estimatedDistance = 0.0
        for(index in points.indices) {
            estimatedDistance += points[index].m
        }
    }
}