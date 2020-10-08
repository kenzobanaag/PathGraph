package com.colab.pathgraph.location

import com.colab.pathgraph.model.PathPoint

interface IRunTracker {
    fun getSpeed() : Double
    fun getSpeed(timeSeconds : Long) : Double
    fun getDistance() : Double
    fun getTime() : Long
    fun recalculateDistance(points : List<PathPoint>) //recalculate distance
}