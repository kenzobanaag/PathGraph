# PathGraph
[![](https://jitpack.io/v/kenzobanaag/PathGraph.svg)](https://jitpack.io/#kenzobanaag/PathGraph)

## Usage

```kotlin

private lateinit var locationCallback: LocationCallback
private lateinit var mDrawPathBtn : Button
private lateinit var mPathGraph : PathGraph

private val pathBuilder = PathBuilder()

locationCallback = object : LocationCallback() {
  override fun onLocationResult(locationResult : LocationResult?) {
    
    locationResult ?: return
    for(location in locationResult.locations) {
      pathBuilder.logLocation(location)
    }
  }
}

mDrawPathBtn.setOnClickListener {
  mPathGraph.points = pathBuilder.buildPath()
}

```
