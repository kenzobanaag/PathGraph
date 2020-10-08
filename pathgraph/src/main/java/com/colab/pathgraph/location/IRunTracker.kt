package com.colab.pathgraph.location

interface IRunTracker {
    fun getSpeed() : Double
    fun getSpeed(timeSeconds : Long) : Double
    fun getDistance() : Double
    fun getTime() : Long
}