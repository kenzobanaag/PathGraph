package com.colab.pathgraph.location

import android.location.Location
import com.colab.pathgraph.model.PathPoint

class CoordinateUtil {
    companion object {
        private const val METER_PER_DEGREE = 111045 //distance in meters per coordinate degree

        /*
        * Get the distance between 2 coordinates as an x,y coordinate
        * */
        fun getPointDistance(x1 : Double, y1 : Double, x2 : Double, y2 : Double) : PathPoint {
            return PathPoint(getDistance(x1,x2),getDistance(y1,y2))
        }

        fun getPointDistance(location1 : Location, location2 : Location) : PathPoint {
            return PathPoint(getDistance(location1.longitude, location2.longitude),
                getDistance(location1.latitude, location2.latitude))
        }

        private fun getDistance(coordinate1 : Double, coordinate2 : Double) : Float{
            return ((coordinate1 - coordinate2) * METER_PER_DEGREE).toFloat()
        }

        fun translation(point : PathPoint, translationPoint : PathPoint) : PathPoint{
            return PathPoint(point.x + translationPoint.x,point.y + translationPoint.y)
        }

        fun computeTranslationPoint(point : PathPoint, desiredPoint : PathPoint) : PathPoint {
            return PathPoint(desiredPoint.x - point.x,desiredPoint.y - point.y)
        }
    }
}