package com.colab.pathgraph.model

/*
* var since we want to mutate path points when scaling or translating.
*
* We dont need to keep older values since they would most likely be out of bounds of phone screen.
* */
data class PathPoint(
    override var x : Float,
    override var y : Float,
    override var m : Float = 0f) : Point{
}