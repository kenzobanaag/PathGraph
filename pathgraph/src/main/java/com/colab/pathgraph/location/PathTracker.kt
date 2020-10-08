package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.PathPoint

/*
* Keeps track of path, distance, speed, time
* */
class PathTracker : IPathBuilder, IRunTracker{

    override fun buildPath(): List<PathPoint> {
        TODO("Not yet implemented")
    }

    override fun logLocation(location: Location) {
        TODO("Not yet implemented")
    }

    override fun getSpeed(): Double {
        TODO("Not yet implemented")
    }

    override fun getDistance(): Double {
        TODO("Not yet implemented")
    }

    override fun getTime(): Double {
        TODO("Not yet implemented")
    }
}