package com.colab.pathgraph.location

import com.colab.pathgraph.model.Point

interface IRunTracker {
    fun getSpeed() : Double
    fun getSpeed(timeSeconds : Long) : Double
    fun getDistance() : Double
    fun getTime() : Long
    fun recalculateDistance(points : List<Point>) //recalculate distance
}