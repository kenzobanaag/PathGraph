package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.PathPoint
import com.colab.pathgraph.model.Point

class CoordinateUtil {
    companion object {
        private const val METER_PER_DEGREE = 111045 //distance in meters per coordinate degree

        /*
        * Get the distance between 2 coordinates as an x,y coordinate
        * */
        fun getPointDistance(x1 : Double, y1 : Double, x2 : Double, y2 : Double) : Point {
            return PathPoint(getDistance(x1,x2),getDistance(y1,y2))
        }

        fun getPointDistance(location1 : Location, location2 : Location) : Point {
            return PathPoint(getDistance(location1.longitude, location2.longitude),
                getDistance(location1.latitude, location2.latitude))
        }

        private fun getDistance(coordinate1 : Double, coordinate2 : Double) : Float{
            return ((coordinate1 - coordinate2) * METER_PER_DEGREE).toFloat()
        }

        fun translation(point : Point, translationPoint : Point) : Point{
            return PathPoint(point.x + translationPoint.x,point.y + translationPoint.y)
        }

        fun computeTranslationPoint(point : Point, desiredPoint : Point) : Point {
            return PathPoint(desiredPoint.x - point.x,desiredPoint.y - point.y)
        }
    }
}